package cn.tedu.csmall.rocketmq.study.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.tomcat.jni.User;
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

    /**
     * 定义个测试事务消息的接口
     * 请求地址:/half
     * 请求方式：GET
     * 请求参数：String msg 发送给后端消费者的消息
     * 请求返回值：SUCCESS固定字符串
     * localhost:9999/half?msg=消息内容
     */
    @GetMapping("/half")
    public String sendHalf(String msg) {
        //将msg作为消息数据 进行半消息发送 但是半消息不会直接进入队列
        //参数解释： destination 目的地：主题名字
        //参数解释 message Message 类型的消息对象
        //参数解释 Object 业务处理的数据
        Message message = MessageBuilder.withPayload(msg).build();
        rocketMQTemplate.sendMessageInTransaction
                ("rocket-test-transaction-topic",
                        message,
                       new User());
        return "SUCCESS";
    }
}
