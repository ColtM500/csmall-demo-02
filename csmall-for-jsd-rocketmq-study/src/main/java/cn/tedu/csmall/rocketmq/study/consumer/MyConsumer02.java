package cn.tedu.csmall.rocketmq.study.consumer;

import cn.tedu.csmall.rocketmq.study.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * springboot整合rocketmq,可以
 * 利用容器bean对象,实现远程队列的消费端绑定
 * 完成消息接收和消费
 * 1.consumer必须是spring容器bean对象
 * 2.实现接口RocketMQListener<T> T的含义就是要处理,转化的消息
 * 2.1 T类型可以是2种常见,String,直接接收body MessageExt 消息接收的整体对象
 * 3.在类上使用监听消息的注解,指定一些消费端配置数据,例如主题,tags,分组
 */
@Component
@Slf4j
@RocketMQMessageListener(
        consumerGroup = "consumer-group-1",
        topic = "rocket-test-topic",
        selectorExpression = "*")
public class MyConsumer02 implements RocketMQListener<String> {
    /**
     * 接收到消息后,调用的方法
     * @param message 类型和泛型相同
     * 方法没有返回值,根据方法执行是否抛异常,底层消费对象返回
     * 消费结果的
     */
    //调用业务代码
    @Autowired
    private HelloService helloService;
    @Override
    public void onMessage(String message) {
        log.debug("接收到消息数据:{}",message);
        helloService.doSayHi(message);
    }
}
