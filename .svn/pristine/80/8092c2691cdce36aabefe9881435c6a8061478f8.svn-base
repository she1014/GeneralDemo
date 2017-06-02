package com.techfly.demo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtil {
    public static void putSharePreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        Editor localEditor = settings.edit();
        localEditor.putString(key, value);
        localEditor.commit();
    }

    public static String getSharePreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    public static String getSharePreference(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void putIntegerSharePreference(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        Editor localEditor = settings.edit();
        localEditor.putInt(key, value);
        localEditor.commit();
    }

    public static int getIntegerSharePreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        return settings.getInt(key, 10);
    }

    /**
     * @return
     */
    public static void putBooleanSharePreference(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public static boolean getBooleanSharePreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    //退出登录清除此条数据
    public static void clearBooleanSharePreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * @return
     */
    public static boolean getBooleanSharePreference(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences("serverInfo", Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

}
