package com.simple.creact.library.framework.datasource;


import com.simple.creact.library.framework.Closeable;
import com.simple.creact.library.framework.IParameter;
import com.simple.creact.library.framework.ParameterFactory;

/**
 * R-DataFetcher从DB/network/File System 获得的原始数据类型
 * P-Parameter Type
 *
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface DataFetcher<R> extends Closeable, ParameterFactory {

    R fetchData(IParameter extra, String... values) throws Exception;
}
