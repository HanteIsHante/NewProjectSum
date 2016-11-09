package com.example.hante.newprojectsum.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 广播监听应用 卸载安装
 */

public class InstallAppReceiver extends BroadcastReceiver{
    private static final String TAG = "InstallAppReceiver";
    @Override
    public void onReceive (Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
            // install
            String dataString = intent.getDataString();
            Log.d(TAG, "onReceive: 安装了 " + dataString);
        } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")){// uninstall
            String dataString = intent.getDataString();
            Log.d(TAG, "onReceive: 卸载了 " + dataString);
        }
    }
}
