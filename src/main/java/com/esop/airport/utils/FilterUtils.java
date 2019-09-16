package com.esop.airport.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**

 * Shiro Filter 工具类
 *
 * @author liliqiang arvin
 */
public class FilterUtils {

	private final static Logger logger = LoggerFactory.getLogger(FilterUtils.class);

	/**
	 * 是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))
				|| "application/json".equalsIgnoreCase(((HttpServletRequest) request).getHeader("Content-Type"));
	}

	/**
	 * response 输出 字符串
	 * @param response
	 * @param json
	 * @throws IOException
	 */
	public static void out(ServletResponse response, String json){

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(json);
		} catch (Exception e) {
			logger.error("输出JSON报错。",e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * response 输出 字符串
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public static void out(ServletResponse response, Object obj){

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.toJSON(obj));
		} catch (Exception e) {
			logger.error("输出JSON报错。",e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
