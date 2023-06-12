package cn.tedu.csmall.starter.test;

import cn.tedu.csmall.starter.test.config.Myconfiguration01;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunTest {
    /**
     * 通过 加载配置类的方式
     * 启动运行一个spring容器
     */
    @Test
    public void test(){
        //通过加载配置启动spring需要使用一个上下文对象
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Myconfiguration01.class);

        //上下文对象功能之一，就是可以拿到容器任何你想用的对象
        Object bean = context.getBean("bean02");
        System.out.println(bean.toString());
    }
}
