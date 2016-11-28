package com.example.hante.newprojectsum.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 监测屏幕广播
 * 接收系统广播事件，
 * 屏幕在三种状态（开屏、锁屏、解锁）之间变换的时候，系统都会发送广播
 */

public class ScreenBroadcastReceiver extends BroadcastReceiver{

    private static final String TAG = "ScreenBroadcastReceiver";
    private String mAction = null;
    @Override
    public void onReceive (Context context, Intent intent) {
            mAction = intent.getAction();
      if(Intent.ACTION_SCREEN_ON.equals(mAction)){
          // 开屏
      } else if (Intent.ACTION_SCREEN_OFF.equals(mAction)){
          // 锁屏
      } else if (Intent.ACTION_USER_PRESENT.equals(mAction)){
          // 解锁
      }
    }
}
