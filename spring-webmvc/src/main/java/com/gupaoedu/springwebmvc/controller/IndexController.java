package com.gupaoedu.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: LIH
 * @Date: 2020/11/19 21:54
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }
}
