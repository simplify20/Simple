package com.simple.creact.library.framework;

import java.util.Map;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface IParameter<K,V> {

    void put(K key, V value);
    V get(K key);
    void putAll(Map<K, V> putMap);
    Map<K,V> getAll();
    IParameter<K,V> addParameter(IParameter<K, V> added);
}
