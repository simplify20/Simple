package com.simple.creact.library.framework.datasource.impl;

import android.support.annotation.NonNull;

import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.ParameterFactory;
import com.simple.creact.library.framework.datasource.DataFetcher;


/**
 * @author:YJJ
 * @date:2016/3/16
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDataFetcher<R> implements DataFetcher<R> {

    protected ParameterFactory parameterFactory;

    public BaseDataFetcher(ParameterFactory parameterFactory) {
        this.parameterFactory = parameterFactory;
    }

    public BaseDataFetcher() {
        this.parameterFactory = this;
    }

    @Override
    public R fetchData(IParameter extra, String... values) throws Exception {
        return fetchDataImpl((RequestParameter) parameterFactory.create(extra, values));
    }

    @Override
    public IParameter create(IParameter extra, String... values) {
        RequestParameter requestParameter;
        if (extra != null && extra instanceof RequestParameter) {
            requestParameter = (RequestParameter) extra;
        } else {
            requestParameter = new RequestParameter();
        }
        if (values == null || values.length == 0) {
            return requestParameter;
        }
        return putValues(requestParameter, values);
    }

    @Override
    public boolean isClose() {
        return false;
    }

    public abstract IParameter putValues(@NonNull IParameter<String, String> extra, @NonNull String... values);

    public abstract R fetchDataImpl(@NonNull RequestParameter requestParameter) throws Exception;
}
