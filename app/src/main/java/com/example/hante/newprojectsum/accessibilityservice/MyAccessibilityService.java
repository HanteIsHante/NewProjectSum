package com.example.hante.newprojectsum.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 *  目的是辅助人们去使用Android设备和应用。它在后台运行，可以接收系统的回调。
 *  无障碍服务，自动执行一些点击事件(静默安装，自动抢红包）
 *  AccessibilityService可以拦截到系统发出的一些消息
 *  （比如窗体状态的改变，通知栏状态的改变，View被点击了等等），
 *  当拦截到这些事件我们就可以去做一些我们想做的事儿了
 *
 *
 *
 *
 *
 *
 *
 */

public class MyAccessibilityService extends AccessibilityService{

    private static final String TAG = "InstallAccessibilitySer";
    @Override
    protected void onServiceConnected () {
        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent (AccessibilityEvent event) {
        Log.d(TAG, "-------------------------------------------------------------");
        int eventType = event.getEventType();//事件类型
        Log.d(TAG, "packageName:" + event.getPackageName() + "");//响应事件的包名，也就是哪个应用才响应了这个事件
        Log.d(TAG, "source:" + event.getSource() + "");//事件源信息
        Log.d(TAG, "source class:" + event.getClassName() + "");//事件源的类名，比如android.widget.TextView
        Log.d(TAG, "event type(int):" + eventType + "");
        switch(eventType){
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点
                break;

        }
        for(CharSequence charSequence : event.getText()) {
            Log.d(TAG, "onAccessibilityEvent: 文本" + charSequence);
        }
    }

    @Override
    public void onInterrupt () {

    }
}
