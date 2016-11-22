package com.example.hante.newprojectsum.setting.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hante.newprojectsum.setting.broadcast.AlarmReceiver;
import com.example.hante.newprojectsum.sqlite.DBEntry;
import com.example.hante.newprojectsum.sqlite.DBHelper;

/**
 *  提醒service 服务
 */

public class AlarmService extends Service{

    private static final String TAG = "AlarmService";
    private DBHelper mDbHelper;
    private AlarmManager mAlarmManager;
    private PendingIntent mPi;
    private Long mTime;
    private String mTitle;
    private String mContent;

    @Nullable
    @Override
    public IBinder onBind (Intent intent) {
        return null;
    }

    @Override
    public void onCreate () {
        super.onCreate();
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        getAlarmTime();
        //这里为了提高优先级，选择START_REDELIVER_INTENT 没那么容易被内存清理时杀死
        return START_REDELIVER_INTENT;
    }

    private void getAlarmTime () {
        Log.d(TAG, "getAlarmTime: 当前时间 启动service");
        mDbHelper = new DBHelper(this);
        SQLiteDatabase writableDatabase = mDbHelper.getReadableDatabase();
        Cursor query = writableDatabase.query(DBEntry.RemindTable.TABLE_NAME,
                null, null, null, null, null, null);
        if(query.moveToFirst()){
            //遍历数据库的表，拿出一条，选择最近的时间赋值，作为第一条提醒数据。
            mTime = query.getLong(query.getColumnIndexOrThrow(DBEntry.RemindTable.REMIND_DATA));
            mTitle = query.getString(query.getColumnIndexOrThrow(DBEntry.RemindTable.REMIND_TITLE));
            mContent = query.getString(query.getColumnIndexOrThrow(DBEntry.RemindTable
                    .REMIND_CONTENT));
            Log.d(TAG, "getAlarmTime: 数据输出 " + mTime + mTitle + mContent);
            do {
                if(mTime > query.getLong(query.getColumnIndexOrThrow(DBEntry.RemindTable.REMIND_DATA))){
                    mTime = query.getLong(query.getColumnIndexOrThrow(DBEntry.RemindTable.REMIND_DATA));
                    mTitle = query.getString(query.getColumnIndexOrThrow(DBEntry.RemindTable.REMIND_TITLE));
                    mContent = query.getString(query.getColumnIndexOrThrow(DBEntry.RemindTable
                            .REMIND_CONTENT));
                }
            } while(query.moveToNext());
        } else {
            mTime = null;
        }
        //删除已经发送提醒的时间
        writableDatabase.delete(DBEntry.RemindTable.TABLE_NAME, DBEntry.RemindTable.REMIND_DATA +
                " =?", new String[]{String.valueOf(mTime)});
        query.close();
        // 启动广播
        Intent startNotify = new Intent(this, AlarmReceiver.class);
        startNotify.putExtra("title", mTitle);
        startNotify.putExtra("content", mContent);
        startNotify.setAction("android.intent.action.ALARM_SERVICE");
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mPi = PendingIntent.getBroadcast(this, 0 ,startNotify,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if(mTime != null){
            Log.d(TAG, "getAlarmTime: 开启广播");
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, mTime, mPi);  //提交事件，发送给 广播接收器
        } else {
            //当提醒时间为空的时候，关闭服务，下次添加提醒时再开启
            stopService(new Intent(this, AlarmService.class));
        }
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }
}
