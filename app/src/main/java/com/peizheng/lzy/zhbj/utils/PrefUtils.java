package com.peizheng.lzy.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LZY on 2016/11/5.
 */

public class PrefUtils {

    public static final String PREF_NAME = "config";

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }
}
