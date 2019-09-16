package com.esop.airport.infrastructure;

import com.esop.airport.common.JsonResult;
import com.esop.airport.common.MemUserCache;
import com.esop.airport.common.ResultDef;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.model.TToken;
import com.esop.airport.domain.service.TokenService;
import com.esop.airport.domain.service.UserService;
import com.esop.airport.infrastructure.annotation.IgnoreAuthentication;
import com.esop.airport.utils.FilterUtils;
import com.esop.airport.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 小程序端鉴权拦截器
 * Created by liliqiang on 2018/1/31.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        if (o instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) o;

            IgnoreAuthentication ignoreAuthentication = handlerMethod.getMethodAnnotation(IgnoreAuthentication.class);

            //检测到不需要权限 拦截器进行放行
            if (!Objects.isNull(ignoreAuthentication)) {
                return true;
            }
        }
//        String xmlResult = IOUtils.toString(httpServletRequest.getInputStream(), httpServletRequest.getCharacterEncoding());
        HttpServletRequest httpRequest = httpServletRequest;
        String accesstoken = httpRequest.getHeader("AccessToken");


        if (StringUtils.isEmpty(accesstoken)) {
            accesstoken = httpRequest.getParameter("accessToken");
            if (StringUtils.isEmpty(accesstoken)) {
                FilterUtils.out(httpServletResponse, new JsonResult<>(ResultDef.CERR_TOKEN.code, ResultDef.CERR_TOKEN.msg));
                return false;
            }
        }

        TToken token = tokenService.findTokenByCode(accesstoken);

        if (token != null) {//如果存在token  有权限的话根据token绑定的id查询用户user信息，绑定到线程变量上(在自己独自的内存，线程安全)
            TBasUser user = userService.findUserById(token.getBindId());
            if (user != null) {
                MemUserCache.set(MemUserCache.ORG_WX_USER, user);
                return true;
            }
        }

        FilterUtils.out(httpServletResponse, new JsonResult<>(ResultDef.CERR_TOKEN.code, ResultDef.CERR_TOKEN.msg));
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        HttpServletRequest httpRequest = httpServletRequest;
        String accesstoken = httpRequest.getHeader("access_token");

        MemUserCache.remove();

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
