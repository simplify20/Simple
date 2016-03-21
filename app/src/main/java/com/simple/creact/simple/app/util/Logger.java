package com.simple.creact.simple.app.util;

import android.util.Log;

/**
 * @author:YJJ
 * @date:2016/3/21
 * @email:yangjianjun@117go.com
 */
public class Logger {

    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }
}
