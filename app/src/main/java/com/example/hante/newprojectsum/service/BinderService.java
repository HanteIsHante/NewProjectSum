package com.example.hante.newprojectsum.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by handan on 2016/10/9.
 * 绑定服务
 */

public class BinderService extends Service{

    private static final String TAG = "BinderService";

    private LocalBinder binder = new LocalBinder();
    /**
     * 创建Binder对象，返回给客户端即Activity使用，提供数据交换的接口
     */
    public class LocalBinder extends Binder {
        // 声明一个方法，getService()（提供给客户端调用）
        BinderService getService(){
            // 返回当前对象LocalService,这样我们就可在客户端端调用Service的公共方法了
            return BinderService.this;
        }
    }
    /**
     * 把Binder类返回给客户端
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 解除绑定时调用
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
