package com.example.hante.newprojectsum.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by handan on 2016/8/30.
 */
public class NetUtil {

    /**
     * 检查是否有网络
     */
    public static boolean isNetAvailable (Context context ){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null){
            return networkInfo.isAvailable();
        }
        return false;
    }
    /*
    *
    * 检查是否是WIFI
    *
    * */
    public static boolean isWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
        }
        return false;
    }

    /**
     * 检查是否是移动网络
     *
     * */
    public static boolean isMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
        }
        return false;
    }


    private static NetworkInfo getNetworkInfo(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
