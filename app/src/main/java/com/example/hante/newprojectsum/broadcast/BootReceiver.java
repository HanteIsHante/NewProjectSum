package com.example.hante.newprojectsum.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hante.newprojectsum.activitys.WelcomeActivity;

/**
 * 开机启动 监听广播
 */

public class BootReceiver  extends BroadcastReceiver{

    private static final String TAG = "BootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            Log.d(TAG, "onReceive: 开机启动");
            Log.i(TAG, "onReceive: 开机启动");
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



        }
    }
}
