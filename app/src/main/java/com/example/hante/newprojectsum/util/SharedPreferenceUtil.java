package com.example.hante.newprojectsum.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by handan on 2016/8/31.
 */
public class SharedPreferenceUtil {
    private static final String welcomeSpf = "welcome";
    public static final String  First_Open = "first_open";


    public static void putBoolean (Context context,String strKey,
                                   Boolean isfirst){
        SharedPreferences sp = context.getSharedPreferences(welcomeSpf, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(strKey,isfirst);
        edit.commit();
    }

    public static boolean getBoolean (Context context,String strKey,
                                      Boolean isfirst){
        SharedPreferences sharedPreferences = context.getSharedPreferences(welcomeSpf, Context.MODE_PRIVATE);
        boolean aBoolean = sharedPreferences.getBoolean(strKey, isfirst);
        return aBoolean;
    }
}
