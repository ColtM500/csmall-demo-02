package cn.tedu.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /**
     * 请求地址 /hello 参数String name
     * 返回值 hello+name
     */
    @GetMapping("/hello")
    public String sayHello(String name){
        return "hello:"+name;
    }
}
