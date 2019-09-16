package com.esop.airport.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component
public class CheckAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.esop.airport.api..*.*Controller.*(..))")
    public void rokenCheck() {
    }

    @Before("rokenCheck()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("CheckAspect >>>, URL={}", request.getRequestURL().toString());
        logger.info("CheckAspect >>>, HTTP_METHOD={}", request.getMethod());
        logger.info("CheckAspect >>>, HTTP_HEAD={}", request.getHeader("AccessToken"));
        logger.info("CheckAspect >>>, IP={}", request.getHeader("X-Forwarded-For"));
        logger.info("CheckAspect >>>, CLASS_METHOD={}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info("CheckAspect >>>, ARGS={} ", Arrays.toString(joinPoint.getArgs()));

    }


    /**
     * 处理完请求，返回结果
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "rokenCheck()")
    public void doAfterReturning(Object ret) throws Throwable {
        if (ret != null) {
            logger.info("CheckAspect <<<, RESPONSE={} ",ret.toString());
        }
    }

}
