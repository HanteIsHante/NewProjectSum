package com.example.hante.newprojectsum.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by handan on 2016/10/9.
 */

public class SimpleService extends Service {

    private static final String TAG = "SimpleService";
    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        Log.i(TAG, "onStartCommand: NAME: " + name);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
