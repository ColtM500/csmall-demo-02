package com.tarena.csmall.starter.properties.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性自动注入的Properties对象
 * 为创建UserUtils对象准备属性
 */

/**
 * 这里如果看到红线,不是严格的编译异常,而是idea的校验提示
 * 由于idea对于这里的语法校验标准error,所以提示红线
 * 可以通过alt+enter进入调整,将error错误标准改成warning
 * 不提示
 * 与次类似的问题,mapper的注入问题.
 */
@ConfigurationProperties(prefix = "tarena.user")
@Data
public class UserProperties {
    //UserUtils使用的属性值 name jwt
    private String name;
    private String jwt;

}
