package com.ciwr.global.common.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class G extends HashMap<String,Object> implements Serializable
{
    private static final long serialVersionUID = 1L;
    public G() 
    {
        put("code", 200);
        put("msg", "success");
    }

    public static G error() 
    {
        return error(500, "未知异常，请联系管理员");
    }

    public static G error(String msg) 
    {
        return error(500, msg);
    }

    public static G error(int code, String msg) 
    {
        G r = new G();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static G ok(String msg) 
    {
        G r = new G();
        r.put("msg", msg);
        return r;
    }

    public static G ok(Map<String, Object> map) 
    {
        G r = new G();
        r.putAll(map);
        return r;
    }

    public static G ok() 
    {
        return new G();
    }

    @Override
    public G put(String key, Object value) 
    {
        super.put(key, value);
        return this;
    }
}
