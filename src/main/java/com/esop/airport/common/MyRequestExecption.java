package com.esop.airport.common;

/**
 * 自定义事物异常
 * Created by liliqiang on 2018/3/2.
 */
public class MyRequestExecption extends RuntimeException{

    public MyRequestExecption(String msg) {
        super(msg);
    }
}
