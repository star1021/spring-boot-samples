package com.gupaoedu.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LIH
 * @Date: 2020/7/9 15:32
 */
@RestController
public class IndexController {

    /**
     * tomcat 1 调用 /index
     * @param request
     * @return
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        request.getSession().setAttribute("LIH", "hello");
        return "success";
    }

    /**
     * tomcat 2 调用 /getValue
     * @param request
     * @return
     */
    @GetMapping("/getValue")
    public String getValue(HttpServletRequest request) {
        String str = (String) request.getSession().getAttribute("LIH");
        return str;
    }
}
