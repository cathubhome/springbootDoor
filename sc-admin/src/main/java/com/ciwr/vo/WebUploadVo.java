package com.ciwr.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
@Data
public class WebUploadVo {

    private String type;

    private String fileName;

    private String fileMd5;

    private String chunk;

    private String fileSize;

    private String formData;

    private String name;

    private String chunks;

    private String chunkSize;

    public static Map<String, Object> success(String msg) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("rtn", 0);
        value.put("msg", msg);
        return value;
    }

    public static Map<String, Object> success(String msg, Object obj) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("rtn", 0);
        value.put("msg", msg);
        value.put("obj", obj);
        return value;
    }

    public static Map<String, Object> fail(String msg) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("rtn", 1);
        value.put("msg", msg);
        return value;
    }

    public static Map<String, Object> fail(String msg, Object obj) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("rtn", 1);
        value.put("msg", msg);
        value.put("obj", obj);
        return value;
    }

}
