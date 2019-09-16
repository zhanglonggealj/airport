package com.esop.airport.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 *
 * @author wxb
 * @date 2018/8/18 11:43
 **/
public class TaskExceptionHandler implements AsyncUncaughtExceptionHandler {

	Logger log = LoggerFactory.getLogger(TaskExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable t, Method m, Object... params) {
		log.error("task handler exception :{}", t.getMessage());
	}
}
