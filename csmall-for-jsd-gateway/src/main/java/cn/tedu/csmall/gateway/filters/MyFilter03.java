package cn.tedu.csmall.gateway.filters;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import cn.tedu.csmall.gateway.filters.JsonResult;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
public class MyFilter03 implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //拿到请求头对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //拿到对象中的数据
        HttpHeaders headers = request.getHeaders();
        //从headers中拿到想要的头数据 直接调用api
        InetSocketAddress host = headers.getHost();//hostName+port打印
        List<String> list = headers.get("Host");//用List中get
        //打印数据1
        log.warn("从api方法getHost中获取Host包含ip:{},和端口:{}", host.getHostName(), host.getPort());
        log.info("从api方法中get('Host')获取数据:{}", list.get(0));
        //从headers中拿到Authorization
        // 所有携带Authorization的头,放行,没携带的,拦截响应认证失败的JJsonResult
        List<String> list1 = headers.get("Authorization");
        //拿到这个值数据有可能为空
        if (list1 == null || list1.size() == 0) {
            //JsonResult 准备 {"status":-999,"message":"您当前未认证","data":null}
            JsonResult jsonResult = new JsonResult();
            jsonResult.setState(-999);
            jsonResult.setMessage("您当前未认证");
            jsonResult.setData(null);
            String jsonData = JSON.toJSONString(jsonResult);
//            String jsonData ="{\"status\":-999,\"message\":\"您当前未认证\",\"data\":null}";
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            //Mono.fromSupplier() 函数创建了一个异步处理单元，它会在需要时执行其参数中的操作并返回一个Mono对象
            //Mono对象被传递 以触发响应输出流的写入操作
            //在Mono的回调函数中wrap() 方法将一段字符串转换成字节数组，并将其包装为DataBuffer对象
            //由wrap() 方法返回的DataBuffer对象会被写入到HTTP响应输出流中，完成响应的构建
            return response.writeWith(Mono.fromSupplier(()->{
                return response.bufferFactory().wrap(jsonData.getBytes(StandardCharsets.UTF_8));
            }));
        } else {
            //过滤器放行，执行后续的内容
            return chain.filter(exchange);//生成一个继续执行过滤器后的功能Mono
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
