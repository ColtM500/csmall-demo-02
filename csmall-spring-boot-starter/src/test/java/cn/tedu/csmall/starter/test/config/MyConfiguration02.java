package cn.tedu.csmall.starter.test.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration02 {

    public MyConfiguration02(){
        System.out.println("配置类02被容器加载了");
    }
}
