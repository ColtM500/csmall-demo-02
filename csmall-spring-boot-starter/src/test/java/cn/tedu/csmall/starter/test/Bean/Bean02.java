package cn.tedu.csmall.starter.test.Bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 模拟spring boot自定义个一些业务
 * 类 比如service controller
 */
@Component(value = "bean02")

/**
 * 读取属性，注入当前bean
 */
@ConfigurationProperties(prefix = "tedu.class")
@EnableConfigurationProperties
public class Bean02 {

    //读取的属性就是name 属性名称 要保证和properties key值相同
    private String name;
    private String age;
    private List<String> boyfriends;

    public Bean02(){
        System.out.println("Bean02被spring容器加载了，是通过扫描类注解加载的");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getBoyfriends() {
        return boyfriends;
    }

    public void setBoyfriends(List<String> boyfriends) {
        this.boyfriends = boyfriends;
    }

    @Override
    public String toString() {
        return "Bean02{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", boyfriends=" + boyfriends +
                '}';
    }
}
