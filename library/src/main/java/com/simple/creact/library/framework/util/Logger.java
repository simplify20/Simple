package com.simple.creact.library.framework.util;


/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public class Logger {

    public static void d(String tag, String message) {
        System.out.println(tag + ": " + message);
    }

    public static void i(String tag, String message) {
        d(tag, message);
    }

    public static void e(String tag, String message) {
        System.err.println(tag + ": " + message);
    }
}
