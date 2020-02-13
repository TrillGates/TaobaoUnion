package com.sunofbeaches.taobaounion.utils;

import android.util.Log;

public class LogUtils {

    private static int currentLev = 4;
    private static final int DEBUG_LEV = 4;
    private static final int INFO_LEV = 3;
    private static final int WARNING_LEV = 2;
    private static final int ERROR_LEV = 1;


    public static void d(Class clazz,String log) {
        if(currentLev >= DEBUG_LEV) {
            Log.d(clazz.getSimpleName(),log);
        }
    }

    public static void i(Class clazz,String log) {
        if(currentLev >= INFO_LEV) {
            Log.i(clazz.getSimpleName(),log);
        }
    }

    public static void w(Class clazz,String log) {
        if(currentLev >= WARNING_LEV) {
            Log.w(clazz.getSimpleName(),log);
        }
    }

    public static void e(Class clazz,String log) {
        if(currentLev >= ERROR_LEV) {
            Log.e(clazz.getSimpleName(),log);
        }
    }

}
