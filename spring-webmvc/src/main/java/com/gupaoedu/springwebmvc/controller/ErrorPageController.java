package com.gupaoedu.springwebmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LIH
 * @Date: 2020/11/16 22:09
 */
@RestController
public class ErrorPageController {

    @GetMapping("/404.html")
    public Map<String, Object> handle(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("exceptionType", request.getAttribute("javax.servlet.error.exception_type"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        map.put("exception", request.getAttribute("javax.servlet.error.exception"));
        map.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
        map.put("servletName", request.getAttribute("javax.servlet.error.servlet_name"));
        return map;
    }
}
