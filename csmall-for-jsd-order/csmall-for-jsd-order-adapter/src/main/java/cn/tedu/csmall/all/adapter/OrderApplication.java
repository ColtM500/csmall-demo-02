package cn.tedu.csmall.all.adapter;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//在启动类中开启Dubbo相关配置注解
@EnableDubbo
@SpringBootApplication
@ComponentScan(basePackages = {"cn.tedu.csmall.all","cn.tedu.csmall.commons.exception"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
