package cn.tedu.csmall.sentinel.service;

import cn.tedu.csmall.sentinel.block.GlobalExceptionHandler;
import cn.tedu.csmall.sentinel.fallback.GlobalFallbackHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HelloService {

    /**
     * 如果资源进行熔断配置时，需要计算异常数据
     * 不能使用BlockHandle来进行降级处理,
     * 针对熔断 sentinel提供的降级方法是fallback
     * @param name
     * @return
     */

    @SentinelResource(
            value="sayHiService",
            fallback = "fallbackDoSayHi",
            fallbackClass = GlobalFallbackHandler.class,
            exceptionsToIgnore = RuntimeException.class
    )
    public String doSayHi(String name) throws RuntimeException, IOException {
        if(name!=null&&name.equals("成恒")){
            throw new RuntimeException("自定义运行时异常");
        }else if(name!=null &&name.equals("范传奇")){
            throw new IOException("自定义IO异常");
        }
        return "hello "+name+";nice to meet you";
    }
}
