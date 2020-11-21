package com.gupaoedu.springwebmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LIH
 * @Date: 2020/11/16 22:42
 */

/**
 * Spring Mvc 处理错误页面
 */
@RestControllerAdvice(basePackages = "com.gupaoedu.springwebmvc.controller")
public class RestControllerAdvicer {

    @ExceptionHandler(NullPointerException.class)
    public Object handleNPE(HttpServletRequest request, Throwable throwable) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        return map;
//        return throwable.getMessage();
    }
}
