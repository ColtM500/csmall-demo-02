package cn.tedu.csmall.sentinel.controller;

import cn.tedu.csmall.sentinel.block.GlobalExceptionHandler;
import cn.tedu.csmall.sentinel.service.HelloService;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * get请求
     * String name
     * String result
     */
    @GetMapping("/hello")
    //定义代码片段为资源
    @SentinelResource(
            value="sayHi",
            blockHandler = "sayHiBlockHandle",
            blockHandlerClass = GlobalExceptionHandler.class
    )

    public String sayHi(String name){
        //准备资源入口
//        Entry entry = null;
        String sayHelloWords = null;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sayHelloWords = helloService.doSayHi(name);
//        try {
//            //开启资源保护的入口 entry赋值
//            entry = SphU.entry("sayHi");
//            sayHelloWords = helloService.doSayHi(name);
//        } catch (BlockException e) {
//            //更具我们定义的规则 流控规则 需要对资源使用
//            //进行流控异常的捕获
//            //可以异常捕获时，表示当前资源访问已经违反，达到规则的临界点
//            sayHelloWords="达到限流规则阈值，blocked";
//        } finally {
//            //释放资源入口
//            if (entry!=null){
//                entry.exit();
//            }
//        }
        return sayHelloWords;
    }

}
