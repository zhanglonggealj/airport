package com.esop.airport.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liliqiang on 2018/1/30.
 */
//@Component
@WebFilter
@Order(value = 1)
public class CorsFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("static", "manage", "swagger")));

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;


        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        boolean allowedPath = false;

        for (String str : ALLOWED_PATHS) {
            allowedPath = path.contains(str);
            if (allowedPath) {
                break;
            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Max-Age", "86400");
//        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With, Token, X-PINGOTHER");

        

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(200);
            response.flushBuffer();
            return;
        }

//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With, Token");

        if (allowedPath) {
            response.setContentType("text/html;charset=utf-8");
            chain.doFilter(req, res);
        } else {
            response.setContentType("application/json;charset=utf-8");
            chain.doFilter(req, res);
        }

    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}

