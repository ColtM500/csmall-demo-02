package cn.tedu.csmall.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyFilter02 implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //在第一个过滤器中打桩，时间点检测一下，pre post地点
        System.out.println("filter02执行pre");
        //过滤器放行 执行后续的内容
        Mono<Void> result = chain.filter(exchange);//生成一个继续执行过滤器后的功能Mono
        //chain.filter()调用之后编写的逻辑代码,都属于post时间点
        System.out.println("filter02执行post");
        return result;
    }

    @Override
    public int getOrder() {
        return -5;
    }
}
