package com.simple.creact.simple.app.biz;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.datasource.DataSource;
import com.simple.creact.library.framework.repository.impl.BaseRepository;
import com.simple.creact.library.framework.repository.impl.DataCallbackAdapter;
import com.simple.creact.library.framework.repository.impl.UIDataCallback;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDaggerRepository<C, S> extends BaseRepository<C, ListenableFuture<S>> {

    private String TAG = "DaggerBaseRepository--";
    private DataSource<ListenableFuture<S>> dataSource;
    private ListenableFuture<S> mFuture;

    public BaseDaggerRepository(DataSource<ListenableFuture<S>> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ListenableFuture<S> getData(IParameter extra, String... values) {
        super.getData(extra, values);
        mFuture = dataSource.getData(extra, values);
        Futures.addCallback(mFuture, new FutureCallback<S>() {
            //this code is not running on ui thread
            @Override
            public void onSuccess(S result) {
                if (callback != null) {
                    if (callback instanceof UIDataCallback) {
                        callback.onSuccess(convert(result));
                        callback.onComplete();
                    } else {
                        DataCallbackAdapter<C> dataCallbackAdapter = new DataCallbackAdapter<>(callback);
                        dataCallbackAdapter.onSuccess(convert(result));
                        dataCallbackAdapter.onComplete();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (callback != null) {
                    if (callback instanceof UIDataCallback) {
                        callback.onError(t);
                    } else {
                        DataCallbackAdapter dataCallbackAdapter = new DataCallbackAdapter<>(callback);
                        dataCallbackAdapter.onError(t);
                    }
                }
            }
        });
        return mFuture;
    }

    @Override
    public void close() {
        super.close();
        if (dataSource != null)
            dataSource.close();
    }

    @Override
    public boolean isClose() {
        return false;
    }

    public abstract C convert(S s);
}
