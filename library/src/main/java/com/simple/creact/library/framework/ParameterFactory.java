package com.simple.creact.library.framework;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface ParameterFactory {
    /**
     * @param extra  附加参数
     * @param values 参数值
     * @return
     */
    IParameter create(IParameter extra, String... values);
}
