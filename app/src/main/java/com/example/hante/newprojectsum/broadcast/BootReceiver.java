package com.example.hante.newprojectsum.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.hante.newprojectsum.activitys.WelcomeActivity;
import com.example.hante.newprojectsum.setting.service.AlarmService;

/**
 * 开机启动 监听广播
 */

public class BootReceiver  extends BroadcastReceiver{

    private static final String TAG = "BootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            // context, AutoRun.class
            Intent newIntent = new Intent(context, WelcomeActivity.class);

            /* MyActivity action defined in AndroidManifest.xml */
            newIntent.setAction("android.intent.action.MAIN");

            /* MyActivity category defined in AndroidManifest.xml */
            newIntent.addCategory("android.intent.category.LAUNCHER");

            /*
             * If activity is not launched in Activity environment, this flag is
             * mandatory to set
             */
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            /* if you want to start a service, follow below method */
            context.startActivity(newIntent);

            // 开机启动Service
            Intent iService = new Intent(context, AlarmService.class);
            context.startActivity(iService);
        }
    }
}
