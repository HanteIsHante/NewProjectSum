package com.example.hante.newprojectsum.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;

import com.example.hante.newprojectsum.R;

/**
 *  通知栏 样式
 *  各标志符介绍
 *  Notification.FLAG_SHOW_LIGHTS              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
 *  Notification.FLAG_ONGOING_EVENT          //发起正在运行事件（活动中）
 *  Notification.FLAG_INSISTENT   //让声音、振动无限循环，直到用户响应 （取消或者打开）
 *  Notification.FLAG_ONLY_ALERT_ONCE  //发起Notification后，铃声和震动均只执行一次
 *  Notification.FLAG_AUTO_CANCEL      //用户单击通知后自动消失
 *  Notification.FLAG_NO_CLEAR          //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
 *  Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务
 *
 * .setDefaults(int defaults)
 * 方法解释：向通知添加声音、闪灯和振动效果的最简单、使用默认（defaults）属性，可以组合多个属性
 * Notification.DEFAULT_VIBRATE //添加默认震动提醒 需要 VIBRATE permission
 * Notification.DEFAULT_SOUND // 添加默认声音提醒
 * Notification.DEFAULT_LIGHTS// 添加默认三色灯提醒
 * Notification.DEFAULT_ALL// 添加默认以上3种全部提醒
 *
 *
 */

public class NotificationManager {

    /**
     * 通知栏
     * 设置提醒标志符Flags
     * 方法解释：提醒标志符，向通知添加声音、闪灯和振动效果等设置达到通知提醒效果，可以组合多个属性
     * 创建通知栏之后通过给他添加.flags属性赋值。
     * @param context 上下文
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Notification notificationManager(@NonNull Context context){
        NotificationCompat.Builder mBuilder;
        Notification mNotification;
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Title");
        mBuilder.setContentText("Content Text");
        mBuilder.setNumber(1);
        mBuilder.setTicker("通知");//通知首次出现在通知栏，带上升动画效果的
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setPriority(Notification.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);//设置这个标志当用户单击面板就可以让通知将自动取消
        mBuilder.setOngoing(false);//TRUE，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,
        // 因此占用设备(如一个文件下载,同步操作,主动网络连接)
        mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);//向通知添加声音、闪灯和振动效果的最简单
        // 、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
        mBuilder.setSmallIcon(R.drawable.leak_canary_icon);
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        mNotification = mBuilder.build();
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        return mNotification;
    }

    /**
     *
     * 点击通知栏进行跳转
     * @param context 上下文
     * @param activity 跳转activity的Intent
     * @return 返回值 PendingIntent
     */
    public PendingIntent getPendingIntent(@NonNull Context context, int requestCode, @NonNull
            Intent activity){
        return PendingIntent
                .getActivity(context, requestCode, activity, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
