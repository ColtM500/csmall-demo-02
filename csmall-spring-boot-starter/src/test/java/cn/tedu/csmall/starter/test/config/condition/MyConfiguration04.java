package cn.tedu.csmall.starter.test.config.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;


@Configuration
@ConditionalOnClass(value = {Jedis.class})
public class MyConfiguration04 {
    public MyConfiguration04(){
        /**
         * 测试代码 当前结构 一定满足这个条件
         * 但是写的工程打成jar包 或者maven资源给别人使用
         * 在运行时，条件为满足
         */
        System.out.println("条件满足当前系统Jedis.class依赖."
                +"配置类MyConfiguration04");
    }
}
