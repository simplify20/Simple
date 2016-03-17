package com.simple.creact.library.framework.datasource;


import com.simple.creact.library.framework.Closeable;
import com.simple.creact.library.framework.IParameter;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface DataSource<S> extends Closeable {
    S getData(IParameter extra, String... values);
}
