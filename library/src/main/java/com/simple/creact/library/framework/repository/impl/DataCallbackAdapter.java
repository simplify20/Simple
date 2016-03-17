package com.simple.creact.library.framework.repository.impl;


import com.simple.creact.library.framework.repository.DataCallback;

/**
 *
 *
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class DataCallbackAdapter<C> extends UIDataCallback<C>{

    public DataCallbackAdapter(DataCallback<C> proxyTarget) {
        super(proxyTarget);
    }

    public DataCallbackAdapter() {
    }

    @Override
    protected void postUISuccess(C t) {

    }

    @Override
    protected void postUIError(Throwable e) {

    }

    @Override
    protected void postUIComplete() {

    }
}
