package com.simple.creact.library.framework.repository;

/**
 * C-客户端需要的数据类型
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public interface DataCallback<C> {

    void onSuccess(C t);
    void onError(Throwable e);
    void onComplete();
}
