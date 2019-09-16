package com.esop.airport.infrastructure;

import com.esop.airport.common.BaseController;
import com.esop.airport.common.JsonResult;
import com.esop.airport.common.ResultDef;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.infrastructure.annotation.IgnoreAuthentication;
import com.esop.airport.utils.FilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 商户老板权限拦截器
 * Created by liliqiang on 2018/8/29.
 */
@Component
public class GeneralInterceptor  implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(GeneralInterceptor.class);

    @Autowired
    ConsService consService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        if (o instanceof HandlerMethod) {//有注解的放行。

            HandlerMethod handlerMethod = (HandlerMethod) o;

            IgnoreAuthentication ignoreAuthentication = handlerMethod.getMethodAnnotation(IgnoreAuthentication.class);

            //检测到不需要权限 拦截器进行放行
            if (!Objects.isNull(ignoreAuthentication)) {
                return true;
            }

        }

        TBasUser user = new BaseController().getUser();
        if (user != null) {
            List<TBasCons> consList = consService.findConsListByPhone(user.getMobilePhone());//拿到电话绑定的商户信息
            if (consList != null && consList.size() > 0) {
                return true;
            }
        }

        FilterUtils.out(httpServletResponse, new JsonResult<>(ResultDef.CERR_NOT_PERMISSION.code, ResultDef.CERR_NOT_PERMISSION.msg));
        return false;

    }


}
