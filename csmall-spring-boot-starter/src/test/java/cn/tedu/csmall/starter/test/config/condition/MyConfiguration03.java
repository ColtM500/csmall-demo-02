package cn.tedu.csmall.starter.test.config.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
/**
 * 测试我们条件注解 和配置属性有关
 * 属性中必须有这个属性的值为true的时候,条件才满足
 * tedu.starter.enable: true
 */
@ConditionalOnProperty(prefix = "tedu.starter",value="enable",havingValue = "true")
public class MyConfiguration03 {
    public MyConfiguration03() {
        System.out.println("条件满足当前系统存在tedu.starter.enable:true的属性." +
                "配置类MyConfiguration03被加载了");
    }
}