package com.ngjackson.jegy.model.form;

import java.util.HashMap;
import java.util.Map;

public abstract class Form {

    private final Map<String, Object> params;

    public Form() {
        params = new HashMap<>();
    }

    protected Map<String, Object> getParams() {
        return params;
    }

    protected void setParam(String paramName, Object paramValue) {
        params.put(paramName, paramValue);
    }

    protected Object getParam(String paramName) {
        return params.get(paramName);
    }
}
