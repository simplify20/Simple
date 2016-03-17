package com.simple.creact.simple.app.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author:YJJ
 * @date:2016/3/11
 * @email:yangjianjun@117go.com
 */
public final class DeviceUtil {

    private DeviceUtil() {
    }

    public static final void toggleSoftInput(View tokenView, boolean isHide) {
        if (tokenView == null)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) tokenView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isHide)
            inputMethodManager.hideSoftInputFromWindow(tokenView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        else
            inputMethodManager.showSoftInput(tokenView, 0);
    }
}
