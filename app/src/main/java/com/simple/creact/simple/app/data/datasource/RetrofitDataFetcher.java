package com.simple.creact.simple.app.data.datasource;


import com.simple.creact.library.framework.datasource.impl.BaseDataFetcher;

import retrofit2.Call;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class RetrofitDataFetcher<R> extends BaseDataFetcher<R> {
    protected Call<R> call;

    public RetrofitDataFetcher(Call<R> call) {
        this.call = call;
    }

    public RetrofitDataFetcher() {
    }

    public Call<R> getCall() {
        return call;
    }

    public void setCall(Call<R> call) {
        this.call = call;
    }
    /**
     * Subclass don't need to implements this method
     */
    @Override
    public void close() {
        if (call != null)
            call.cancel();
    }
}
