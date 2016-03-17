package com.simple.creact.simple.app.util;

import android.widget.Toast;

import com.simple.creact.simple.app.SimpleApplication;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class ToastUtil {

    public static void toastShortMsg(String msg) {
        Toast.makeText(SimpleApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
