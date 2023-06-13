package com.tarena.csmall.starter.properties.config;

import com.tarena.csmall.starter.properties.bean.UserUtils;
import com.tarena.csmall.starter.properties.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * 条件:必须提供tarena.user.enable=true属性和值
 * 配置逻辑:开启properties属性注入,导入当前bean创建方法
 * 生成一个容器管理的bean对象UserUtils;
 */
@Configuration
@ConditionalOnProperty(
        prefix = "tarena.user",
        value = "enable",
        havingValue = "true"
)
//开启属性注入注解,可以让容器中存在一个bean对象UserProperties
@EnableConfigurationProperties(UserProperties.class)
@Slf4j
public class UserUtilsAutoConfiguration {
    //通过@Bean创建UserUtils对象
    @Bean
    public UserUtils createUserUtils(UserProperties userProperties){
        //userProperties这个bean对象不一定存在
        if (userProperties == null){
            log.error("userProperties can not be null");
        }
        //name和jwt有可能在userProperties中读不到
        else if (StringUtils.isEmpty(userProperties.getJwt())){
            log.error("jwt value can not be null");
        }else if(StringUtils.isEmpty(userProperties.getName())){
            log.error("name value can not be null");
        }
        //构建一个对象
        UserUtils userUtils=new UserUtils();
        userUtils.setName(userProperties.getName());
        userUtils.setJwt(userProperties.getJwt());
        return userUtils;
    }
}
