package com.zl.myappbasicframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtil {
    private static SharedPreferences mSp;

    private static SharedPreferences getSharedPref(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return mSp;
    }

    public static void putBoolean(Context context, String key, boolean value) {
        
        getSharedPref(context).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        
        return getSharedPref(context).getBoolean(key, defValue);
    }
    
    public static void putString(Context context, String key, String value) {
        getSharedPref(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        return getSharedPref(context).getString(key, defValue);
    }
    
    public static void removeString(Context context, String key) {
        getSharedPref(context).edit().remove(key).commit();
    }
    
    public static void putInt(Context context, String key, int value) {
        getSharedPref(context).edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        return getSharedPref(context).getInt(key, defValue);
    }

    public static void putLong(Context context, String key, long value) {
        getSharedPref(context).edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String key, long defValue) {
        return getSharedPref(context).getLong(key, defValue);
    }
}
