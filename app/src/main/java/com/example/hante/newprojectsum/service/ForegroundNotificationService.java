package com.example.hante.newprojectsum.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.hante.newprojectsum.NewProjectSumHomeActivity;
import com.example.hante.newprojectsum.R;

/**
 * Created by handan on 2016/10/10.
 */

public class ForegroundNotificationService extends Service{
    private static final String TAG = "ForegroundNotificationS";
    private static final int NOTIFICATION_DOWNLOAD_PROGRESS_ID = 0x0001;

    private boolean ifRemove = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     *
     * @param title  标题
     * @param content 通知内容
     */
    public void createNotification(String title,String content){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // 大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap
                .topmenu_icn_level));
        builder.setSmallIcon(R.mipmap.topmenu_icn_level);
        //设置通知栏右下角图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.topmenu_icn_clock));
        builder.setContentTitle(title);
        builder.setContentInfo(content);
        //禁止用户点击删除按钮删除
        builder.setAutoCancel(false);
        //禁止滑动删除
        builder.setOngoing(true);
        //右上角的时间显示
        builder.setShowWhen(true);
        // 点击通知栏跳转
        Intent slipIntent = new Intent(getApplicationContext(),NewProjectSumHomeActivity.class);
        slipIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingintent = PendingIntent.getActivity(getApplicationContext(),0,slipIntent,PendingIntent
                .FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingintent);
        //创建通知
        Notification notification = builder.build();
        startForeground(NOTIFICATION_DOWNLOAD_PROGRESS_ID,notification);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ifClose = intent.getExtras().getInt("ifClose");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        if(ifClose == 0){
            if(!ifRemove){
                createNotification(title,content);
            }
            ifRemove = true;
        } else {
            if(ifRemove){
                stopForeground(true);
            }
            ifRemove = false;
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if(ifRemove){
            stopForeground(true);
        }
        ifRemove = false;
        super.onDestroy();
    }
}
