package com.gupaoedu.springwebmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LIH
 * @Date: 2020/11/9 22:47
 */
public class DefaultHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.printf("handler object : %s \n",handler.toString());
        return true;
    }
}
