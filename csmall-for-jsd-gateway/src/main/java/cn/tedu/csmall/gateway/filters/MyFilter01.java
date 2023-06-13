package cn.tedu.csmall.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 网关实现的第一个过滤器
 * 必须实现一个接口GlobalFilter
 * 如果想定义多个过滤器的顺序
 * 还要实现接口Ordered
 */
@Component
@Slf4j
public class MyFilter01 implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //处理请求参数 先要在过滤器拿到请求对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //对request做参数的获取
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //http://localhost:8888/csmall-for-jsd-cart/aaa?age=18
        //http://localhost:8888/csmall-for-jsd-cart/aaa?name=王翠花
        List<String> paramsList = queryParams.get("name");
        if (paramsList==null||paramsList.size()==0){
            log.error("当前请求没有携带name参数");
            //如果当前参数没有name 拦截这个请求 返回自定义数据
            //定义当前响应写出数据格式 添加一个响应头
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            //封装在response JsonResult对象 解析成json字符串
            //封装好一个写好响应数据的Mono对象作为返回值
            response.writeWith(Mono.fromSupplier(()->{
                //数据流写进response
                return response.bufferFactory()
                        .wrap("你好，你没有携带name".getBytes(StandardCharsets.UTF_8));
            }));
        } else {
            log.info("当前请求携带了name参数:{}", paramsList.get(0));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
