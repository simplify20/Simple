package com.simple.creact.simple.app.data.datasource;


import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureFallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.datasource.DataFetcher;
import com.simple.creact.library.framework.datasource.impl.MultiStrategyDataSource;
import com.simple.creact.simple.app.util.Logger;

import java.util.Iterator;

/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDaggerMultiDataSource<R, S> extends MultiStrategyDataSource<ListenableFuture<R>, ListenableFuture<S>> {
    public BaseDaggerMultiDataSource(DataFetcher<ListenableFuture<R>>... dataFetchers) {
        super(dataFetchers);
    }

    @Override
    public ListenableFuture<S> getData(final IParameter extra, final String... values) {
        if (dataFetchers == null || dataFetchers.size() == 0)
            return null;
        ListenableFuture<R> withFallback = null;
        Iterator<DataFetcher<ListenableFuture<R>>> iterator = dataFetchers.iterator();
        try {
            DataFetcher<ListenableFuture<R>> first = iterator.next();
            Logger.d("BaseDaggerMultiDataSource", "fetch data from " + first.getClass().getSimpleName());
            withFallback = first.fetchData(extra, values);
        } catch (Exception e) {
            Logger.d("BaseDaggerMultiDataSource", " first exception: " + e.getMessage());
        }
        for (; iterator.hasNext(); ) {
            final DataFetcher<ListenableFuture<R>> dataFetcher = iterator.next();
            if (!dataFetcher.isClose()) {

                withFallback = Futures.withFallback(withFallback, new FutureFallback<R>() {
                    @Override
                    public ListenableFuture<R> create(Throwable t) throws Exception {
                        Logger.d("BaseDaggerMultiDataSource", "exception: " + t.getMessage());
                        Logger.d("BaseDaggerMultiDataSource", "fetch data from " + dataFetcher.getClass().getSimpleName());
                        return dataFetcher.fetchData(extra, values);
                    }
                });

            }
        }
        return Futures.transform(withFallback, new AsyncFunction<R, S>() {
            @Override
            public ListenableFuture<S> apply(R input) throws Exception {
                return Futures.immediateFuture(convert(input));
            }
        });
    }


    public abstract S convert(R input);
}
