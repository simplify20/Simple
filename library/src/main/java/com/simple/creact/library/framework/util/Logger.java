package com.simple.creact.library.framework.util;

import android.util.Log;

/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public class Logger {

    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
    }
}
