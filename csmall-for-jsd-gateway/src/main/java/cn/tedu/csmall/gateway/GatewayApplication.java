package cn.tedu.csmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        //另一种启动代码的编写方式
        //1.新建一个springboot的容器上下文对象 其中包含着spring的上下文
        SpringApplication springApplication = new SpringApplication(GatewayApplication.class);
        //2.运行启动 run方法结束 容器和web实例就启动成功了
        springApplication.run(args);
        //3.可以通过上下文检查当前系统的一些条件，例如监听器
//        springApplication.addListeners();
//        SpringApplication.run(GatewayApplication.class, args);
    }
}
