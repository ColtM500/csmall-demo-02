package cn.tedu.csmall.starter.test.config;

import cn.tedu.csmall.starter.test.Bean.Bean01;
import org.springframework.context.annotation.*;


/**
 * 在spring容器里，使用注解标注当前类是配置类
 */
@Configuration

/**
 * 如果使用扫描注解，不指定扫描范围，默认扫描当前配置类的包
 * 如果想要指定，需要提供扫描包路径的string[]
 * 通过注解的属性传递
 */

@ComponentScan(basePackages = {
        "cn.tedu.csmall.starter.test.Bean",
        "cn.tedu.csmall.starter.test.config.condition"})

/**
 * 读取自定义properties或者yml文件
 */
@PropertySource(value = {"demo.properties"})

/**
 * 通过import导入想要导入的其他配置类 同时在容器中生效
 */
@Import(value = {MyConfiguration02.class})
public class Myconfiguration01 {
    //打桩，验证是否被容器加载
    public Myconfiguration01(){
        System.out.println("配置类01被容器加载了");
    }

    @Bean(name = "bean01")//单例的情况下可以不给
    public Bean01 bean01(){
        return new Bean01();
    }
}
