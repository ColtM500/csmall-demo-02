package cn.tedu.csmall.rocketmq.study.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * uri/send
     * method GET
     * param String msg
     * return String "SUCCESS" "FAIL"
     *
     * @param msg
     * @return
     */
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public String sayHi(String msg) {
        //同步调用helloService
        //同步调用的方法出错 当前sayHi也得处理错误 同步成功 当前可以处理成功
        //发送消息 测试
        //pay Load = 底层客户端body headers = 底层客户端properties
        Message<String> message = MessageBuilder.withPayload(msg).build();
        rocketMQTemplate.send("rocket-test-topic", message);//没有指明主题
        return "SUCCESS";
    }
}
