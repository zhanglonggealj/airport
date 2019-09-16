package com.esop.airport.common;

/**
 * 自定义异常，方便在统一异常拦截的地方 做统一处理
 * Created by liliqiang on 2017/9/19.
 */
public class TokenRuntimeException extends RuntimeException {

    /**
     *  传入异常错误原因
     * @param message
     */
    public TokenRuntimeException(String message) {
        super(message);
    }
}
