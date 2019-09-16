package com.esop.airport.utils;

import okhttp3.Request;

import java.io.IOException;

/**
 * Created by liliqiang on 2018/8/3.
 */
public interface IDataCallBack {

    void requestFailure(Request request, IOException e);

    void requestSuccess(String result) throws Exception;
}
