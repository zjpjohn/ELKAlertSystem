package com.alert.vo;

/**
 * Project: ConfigServer
 * Module Desc:com.juntu.config.vo
 * User: zjprevenge
 * Date: 2016/11/21
 * Time: 16:30
 */
public class JsonResult<T> {

    private int code;
    private String msg;
    private T data;

    public JsonResult() {
    }

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success(String msg) {
        return new JsonResult(200, msg, null);
    }

    public static JsonResult failure(String msg) {
        return new JsonResult(500, msg, null);
    }

    public int getCode() {
        return code;
    }

    public JsonResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResult setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
