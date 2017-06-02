package com.techfly.demo.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.techfly.demo.activity.application.MyApplication;

import java.util.Date;


public class ContentUtil {

    /**
     * Toast显示
     *
     * @param context
     * @param text
     */
    public static void makeToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void makeTestToast(Context context, String text) {
        if (MyApplication.isTest == true) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * LOG显示
     *
     * @param tag
     * @param msg
     */
    public static void makeLog(String tag, String msg) {
        if (MyApplication.isTest == true) {
            Log.i(tag, msg);
        }

    }

    public static String toString(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String CalendarToString(Date d) {
        String t;
        t = String.valueOf(d.getMonth() + 1) + "/"
                + String.valueOf(d.getDate()) + "/"
                + String.valueOf(d.getHours());
        return t;
    }
}
