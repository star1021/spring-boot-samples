package com.gupaoedu.springwebmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Demo Controller
 *
 * @Author: LIH
 * @Date: 2020/11/8 17:20
 */
@RestController // = @Controller + @ResponseBody
public class RestDemoController {

    @RequestMapping("/index")
    // @PostMapping     // Post     请求  @RequestMapping(method = RequestMethod.Post) Create(C)
    // @GetMapping      // GET      请求  @RequestMapping(method = RequestMethod.GET) Read(R)
    // @PutMapping      // Put      请求  @RequestMapping(method = RequestMethod.PUT) Update(U)
    // @DeleteMapping   // Delete   请求  @RequestMapping(method = RequestMethod.DELETE) Delete(D)
    // Windows 通过 PostMan 来测试
    // curl -X POST
    public String index() {
        return "Hello,World";
    }

    @GetMapping("/npe")
    public String npe() {
        throw new NullPointerException("故意抛异常！");
    }
}
