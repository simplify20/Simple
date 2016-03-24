package com.simple.creact.library.framework.util;


/**
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public class Logger {
    private static final String SCOPE = "Simple Library";

    public static void d(String tag, String message) {
        System.out.println(SCOPE + "->" + tag + ": " + message);
    }

    public static void i(String tag, String message) {
        d(tag, message);
    }

    public static void e(String tag, String message) {
        System.err.println(SCOPE + "->" + tag + ": " + message);
    }
}
