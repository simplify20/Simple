package com.simple.creact.library.framework.repository.impl;

import android.os.Handler;
import android.os.Looper;

import com.simple.creact.library.framework.repository.DataCallback;
import com.simple.creact.library.framework.util.Logger;


/**
 * A ui thread callback implementation ,you can use a common DataCallback to construct a UIDataCallback
 *
 * @author:YJJ
 * @date:2016/3/15
 * @email:yangjianjun@117go.com
 */
public abstract class UIDataCallback<C> implements DataCallback<C> {
    protected DataCallback<C> proxyTarget;
    /**
     * Communicate with UI thread
     */
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private static String TAG = "UIDataCallback";

    public UIDataCallback(DataCallback<C> proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    public UIDataCallback() {
    }

    @Override
    public final void onSuccess(final C t) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            dispatchSuccess(t);
        } else
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    dispatchSuccess(t);
                }
            });
    }

    private void dispatchSuccess(C t) {
        Logger.d(TAG, "dispatchSuccess");
        if (proxyTarget != null)
            proxyTarget.onSuccess(t);
        else
            postUISuccess(t);
    }

    @Override
    public final void onError(final Throwable e) {
        if (Looper.myLooper() == Looper.getMainLooper())
            dispatchError(e);
        else
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    dispatchError(e);
                }
            });
    }

    private void dispatchError(Throwable e) {
        Logger.d(TAG, "dispatchError,e:" + e.getMessage());
        if (proxyTarget != null)
            proxyTarget.onError(e);
        else
            postUIError(e);
    }

    @Override
    public final void onComplete() {
        if (Looper.myLooper() == Looper.getMainLooper())
            dispatchComplete();
        else
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    dispatchComplete();
                }
            });
    }


    private void dispatchComplete() {
        Logger.d(TAG, "dispatchComplete");
        if (proxyTarget != null)
            proxyTarget.onComplete();
        else
            postUIComplete();
    }


    protected abstract void postUISuccess(C t);

    protected abstract void postUIError(Throwable e);

    protected abstract void postUIComplete();


}
