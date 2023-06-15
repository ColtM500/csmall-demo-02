package cn.tedu.csmall.sentinel.fallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalFallbackHandler {

    public static String fallbackDoSayHi(String name, Throwable e){
        log.error("进入熔断降级规则，获取到异常类型:{}",e.getClass().getName());
        return "对不起 service进入了熔断:"+name;
    }
}
