package com.gupaoedu.springbootbeanvalidation.web.controller;

import com.gupaoedu.springbootbeanvalidation.domain.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: LIH
 * @Date: 2020/9/12 21:47
 */
@RestController
public class UserController {

    @PostMapping("/user/save")
    public User save(@Valid @RequestBody User user) {
        return user;
    }

    @PostMapping("/user/save2")
    public User save2(@RequestBody User user) {

        //API 调用的方式
        Assert.hasText(user.getName(), "名称不能为空");
        //JVM 断言 加jvm参数 -ea
        assert user.getId() <= 10000;
        return user;
    }
}
