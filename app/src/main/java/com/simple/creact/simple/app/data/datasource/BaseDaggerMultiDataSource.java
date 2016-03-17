package com.simple.creact.simple.app.data.datasource;


import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.datasource.DataFetcher;
import com.simple.creact.library.framework.datasource.impl.MultiStrategyDataSource;
import com.simple.creact.library.framework.util.Logger;

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
    public ListenableFuture<S> getData(IParameter extra, String... values) {
        if (dataFetchers == null)
            return null;
        for (DataFetcher<ListenableFuture<R>> dataFetcher : dataFetchers) {
            if (!dataFetcher.isClose()) {
                try {
                    Logger.d("BaseDaggerMultiDataSource", "fetch data from " + dataFetcher.getClass().getSimpleName());
                    ListenableFuture<R> rowData = dataFetcher.fetchData(extra, values);
                    //如果当前Fetcher fetch数据失败，继续循环,知道成功或循环结束
                    if (rowData == null) {
                        Logger.d("BaseDaggerMultiDataSource", " failed");
                        continue;
                    }
                    return Futures.transform(rowData, new AsyncFunction<R, S>() {
                        @Override
                        public ListenableFuture<S> apply(R input) throws Exception {
                            return Futures.immediateFuture(convert(input));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public abstract S convert(R input);
}
