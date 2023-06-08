package cn.tedu.csmall.all.adapter;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//开启Dubbo配置注解
@EnableDubbo
@SpringBootApplication
@ComponentScan(basePackages = {"cn.tedu.csmall.all","cn.tedu.csmall.commons.exception"})
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
