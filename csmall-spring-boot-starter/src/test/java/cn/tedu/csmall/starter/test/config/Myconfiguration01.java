package cn.tedu.csmall.starter.test.config;

import org.springframework.context.annotation.Configuration;

/**
 * 在spring容器里，使用注解标注当前类是配置类
 */
@Configuration
public class Myconfiguration01 {
    //打桩，验证是否被容器加载
    public Myconfiguration01(){
        System.out.println("配置类01被容器加载了");
    }
}
