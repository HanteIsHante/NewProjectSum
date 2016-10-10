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

    /**
     *
     * @return START_NOT_STICKY
     *  当Service因内存不足而被系统kill后，即使系统内存再次空闲时，
        系统也不会尝试重新创建此Service。除非程序中再次调用startService启动此Service，
        这是最安全的选项，可以避免在不必要时以及应用能够轻松重启所有未完成的作业时运行服务
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        Log.i(TAG, "onStartCommand: NAME: " + name);
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
