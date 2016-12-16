package com.example.hante.newprojectsum.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 *  自动安装应用
 *  setServiceInfo(AccessibilityServiceInfo)为其配置信息,
 *  除此之外,通过该方法可以在运行期间动态修改服务配置.
 *  需要注意,该方法只能用来配置动态属性:eventTypes,feedbackType,
 *  flags,notificaionTimeout及packageNames.
 *   可以新建XML 进行辅助说明
 */

public class AutoAccessibilityService extends AccessibilityService{
    private static final String TAG = "AutoAccessibilityServic";
    private static String PACKAGE_INSTALL = "com.android.packageinstaller";
    /**
     * 在此设置XML 中的信息
     * 可以新建XML 进行辅助说明
     */
    @Override
    protected void onServiceConnected () {
        super.onServiceConnected();
        AccessibilityServiceInfo serviceInfo = new AccessibilityServiceInfo();
        serviceInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        serviceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        serviceInfo.notificationTimeout = 1000;
        serviceInfo.packageNames = new String[]{PACKAGE_INSTALL};
        setServiceInfo(serviceInfo);
    }

    @Override
    public void onAccessibilityEvent (AccessibilityEvent event) {
         /*
         * 在弹出安装界面时会发生 TYPE_WINDOW_STATE_CHANGED 事件，其属主
         * 是系统安装器com.android.packageinstaller
         */
        if(event.getSource() == null) {
            return;
        }
        int eventType = event.getEventType();
        Log.d(TAG, "onAccessibilityEvent: 事件类型" + eventType);
        boolean equals = event.getPackageName().equals(PACKAGE_INSTALL);
        if(equals){
            installAPK(event);
        }
    }

    /**
     * 安装apk
     * @param event 安装事件
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void installAPK (AccessibilityEvent event) {
        AccessibilityNodeInfo source = getRootInActiveWindow();
        List<AccessibilityNodeInfo> goOn = source.findAccessibilityNodeInfosByText("继续");
        nextClick(goOn);
        List<AccessibilityNodeInfo> nextStep = source.findAccessibilityNodeInfosByText("下一步");
        nextClick(nextStep);
        List<AccessibilityNodeInfo> install = source.findAccessibilityNodeInfosByText("安装");
        nextClick(install);
        List<AccessibilityNodeInfo> finish = source.findAccessibilityNodeInfosByText("完成");
        nextClick(finish);
        runInBack(event);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void nextClick (List<AccessibilityNodeInfo> goOn) {
        if (goOn != null)
            for (AccessibilityNodeInfo info : goOn) {
                if (info.isEnabled() && info.isClickable())
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void runInBack (AccessibilityEvent event) {
        event.getSource().performAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    @Override
    public void onInterrupt () {

    }
}
