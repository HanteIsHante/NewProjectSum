package com.example.hante.newprojectsum.setting.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.bean.RemindList;
import com.example.hante.newprojectsum.setting.service.AlarmService;
import com.example.hante.newprojectsum.sqlite.DBEntry;
import com.example.hante.newprojectsum.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SettingActivity";
    @Bind(R.id.button)
    Button mButton;
    private DBHelper mDbHelper;
    private List<RemindList> mRemindList;
    private RemindList mRemind;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mDbHelper = new DBHelper(getApplicationContext());
        initUI();
    }

    private void initUI () {
        mRemind = new RemindList();
        mRemindList = new ArrayList<>();
        mRemind.setTitle("Title");
        mRemind.setContent("Content");
        String time =  "1479473405165";
        Log.d(TAG, "initUI: 当前时间" + System.currentTimeMillis());
        mRemind.setData(Long.parseLong(time));
        mRemindList.add(mRemind);
        mButton.setOnClickListener(this);
    }


    public void addData () {
        SQLiteDatabase mDB = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Calendar c = Calendar.getInstance();//获取当前时间，为了判断添加时间是否已经过时
        for(int i = 0; i < mRemindList.size(); i++) {
            Log.d(TAG, "addData: " + mRemindList.get(i).getTitle());
            if(!mRemindList.get(i).getData().toString().equals("") &&
                    Long.parseLong(String.valueOf(mRemindList.get(i).getData())) > c.getTimeInMillis()) {
                values.put(DBEntry.RemindTable.REMIND_DATA, Long.parseLong(String.valueOf
                        (mRemindList
                                .get(i)
                                .getData())));
                values.put(DBEntry.RemindTable.REMIND_TITLE, mRemindList.get(i).getTitle());
                values.put(DBEntry.RemindTable.REMIND_CONTENT, mRemindList.get(i).getContent());
                mDB.insert(DBEntry.RemindTable.TABLE_NAME, null, values);
                Log.d(TAG, "addData: Insert");
                Intent iAlarmService = new Intent(this, AlarmService.class);
                startService(iAlarmService);
            } else {
                Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
                values.clear();
            }
        }
    }

    @Override
    public void onClick (View view) {
        if(view == mButton){
            addData();
        }
    }

}
