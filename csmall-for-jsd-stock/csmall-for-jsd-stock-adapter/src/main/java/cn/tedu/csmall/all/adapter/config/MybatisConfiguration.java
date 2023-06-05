package cn.tedu.csmall.all.adapter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// MyBatis框架要求扫描指定的包,才能使框架生效
@MapperScan("cn.tedu.csmall.cart.adapter.mapper")
public class MybatisConfiguration {
}
