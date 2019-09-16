package com.esop.airport.common;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Json统一格式返回
 * Created by arvin on 2016/12/14.
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 5454414760727428343L;
    /**
     * 是否成功
     */
    @JSONField(ordinal = 0)
    private int resultCode;
    /**
     * 信息
     */
    @JSONField(ordinal = 1)
    private String message;
    /**
     * 数据
     */
    @JSONField(ordinal = 2)
    private T data;


    public JsonResult(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
