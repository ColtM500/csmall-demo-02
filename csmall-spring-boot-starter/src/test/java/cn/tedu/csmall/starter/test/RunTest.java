package cn.tedu.csmall.starter.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

public class RunTest {
    /**
     * 通过 加载配置类的方式
     * 启动运行一个spring容器
     */
    @Test
    public void test(){
        //通过加载配置启动spring需要使用一个上下文对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    }
}
