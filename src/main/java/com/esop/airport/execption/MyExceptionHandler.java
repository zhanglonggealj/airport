package com.esop.airport.execption;

import com.esop.airport.common.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常统一拦截处理
 * Created by liqiang on 16/12/22.
 */
//@ControllerAdvice("cn.sharing.www.bloodsociety.appointment")   //具体异常根据包名拦截
@ControllerAdvice
public class MyExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler
    @ResponseBody
    public Object handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        logger.error("caught Errors: {}", ex.getMessage(), ex);

        MemUserCache.remove();

        if (ex instanceof MyRequestExecption) {
            return new JsonResult<>(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg, ex.getMessage());
        }

        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return new JsonResult<>(ResultDef.CERR.code, "请求的URI不存在", ex.getMessage());
        }

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new JsonResult<>(ResultDef.CERR.code, "请求模式不被支持", ex.getMessage());
        }

        if (ex instanceof MyTransactionExecption) {
            return new JsonResult<>(ResultDef.CERR_CONS_PUSH_ERR.code, ResultDef.CERR_CONS_PUSH_ERR.msg, ex.getMessage());
        }

        if (ex instanceof org.springframework.dao.DuplicateKeyException) {
            return new JsonResult<>(ResultDef.CERR.code, "数据异常 经检查是否有关键数据重复 如电表ID", ex.getMessage() == null ? ex.getStackTrace().toString() : ex.getMessage());
        }

        return new JsonResult<>(ResultDef.SERR.code, "服务器压力太大了放过它吧~~~~~", ex.getMessage() == null ? ex.getStackTrace().toString() : ex.getMessage());

    }

    protected String getMessage(String code) {
        return getMessage(code, null);
    }

    protected String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
