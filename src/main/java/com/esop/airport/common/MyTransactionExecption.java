package com.esop.airport.common;

/**
 * 自定义事物异常
 * Created by liliqiang on 2018/3/2.
 */
public class MyTransactionExecption extends RuntimeException{

    public MyTransactionExecption(String msg) {
        super(msg);
    }
}
