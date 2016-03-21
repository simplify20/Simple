package com.simple.creact.library.framework.datasource.impl;

import com.simple.creact.library.framework.IParameter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO:实现对象池模型
 *
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public class RequestParameter implements IParameter<String, String> {
    protected LinkedHashMap<String, String> keyValues;
    private String action = "";

    public static RequestParameter newActionParameter(String action) {
        RequestParameter result = new RequestParameter();
        result.setAction(action);
        return result;
    }

    /**
     * use to construct a Parameter
     */
    public RequestParameter(IParameter<String, String> extra) {
        this.keyValues = new LinkedHashMap<>();
        putAll(extra.getAll());
    }

    public RequestParameter() {
        keyValues = new LinkedHashMap<>();
    }

    public final boolean hasAction() {
        return !(action == null || action == "");
    }

    public final String getAction() {
        return action;
    }

    public final void setAction(String action) {
        this.action = action;
        put("action", action);
    }

    public void put(String key, String value) {
        keyValues.put(key, value);
    }

    /**
     * safe return
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        String val = keyValues.get(key);
        if (val == null)
            return "";
        return val;
    }

    @Override
    public void putAll(Map<String, String> putMap) {
        if (putMap == null)
            putMap = new HashMap<>();
        keyValues.putAll(putMap);
    }

    @Override
    public Map<String, String> getAll() {
        return keyValues;
    }

    @Override
    public IParameter<String, String> addParameter(IParameter<String, String> added) {
        if (added != null)
            putAll(added.getAll());
        return this;
    }

}
