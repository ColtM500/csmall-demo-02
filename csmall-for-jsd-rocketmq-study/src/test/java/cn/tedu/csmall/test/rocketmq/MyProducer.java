package cn.tedu.csmall.test.rocketmq;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 学习rocketmq得所有生产消息得逻辑
 */
public class MyProducer {

    /**
     * 向rocketmq发送第一条消息
     */
    @Test
    public void sendTest01() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.准备一个连接nameserver的生产者对象
        //创建生产者 默认对象
        DefaultMQProducer producer = new DefaultMQProducer();
        //对当前分组进行设置
        producer.setProducerGroup("first-group-a");
        //提供属性 告知nameserver地址
        producer.setNamesrvAddr("localhost:9876");
        //开启底层连接
        producer.start();
        //2.准备消息对象 消息对象有很多的属性
        //创建new一个消息对象 rocketmq底层消息对象
        Message message = new Message();
        //消息主体body 我想要携带的 和我业务代码逻辑相关的字符串
        String msg = "我的第一条消息";
        message.setBody(msg.getBytes(StandardCharsets.UTF_8));
        //设置消息的主题 不同业务的消息 不同逻辑的消息 不同目标的消息 主题是不同的
        //例如 短信可以用电话号码做主题 邮件用邮箱做主题 电商订单是一个主题 商品同理
        message.setTopic("first-topic-a");
        //设置消息标签 在消费端体现的
        message.setTags("TagA");
        //设置消息的标识key 总是使用和业务有关的数据来填充的 比如商品id 订单id 订单编号
        message.setKeys("1");
        //3.执行发送，获取返回结果
        SendResult result = producer.send(message);
        //获取的result数据 可以包括 消息id 发送成功失败的状态 保存这条消息的
        System.out.println("发送状态:" + result.getSendStatus());
        System.out.println("消息id" + result.getMsgId());
        System.out.println("所在队列id" + result.getMessageQueue());
    }

    /**
     * 延迟消息发送
     * 绝大部分代码和上边普通消息完全一致 只需要多添加一个延迟级别属性properties
     * rocketmq的java客户端就将这种添加属性的功能 包装到了api中
     * 最好测试 重新定义分组 主题 过滤tag 消费者组
     * 生产者组： 主题 1：n
     * 主题： 消费者组 1：n
     */
    @Test
    public void sendDelayMsg() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.生产者
        DefaultMQProducer producer = new DefaultMQProducer(
                "second-producer-group"
        );
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        System.out.println("当前发送消息时间:" + LocalDateTime.now());//为了测试延迟时间
        //2.组织封装消息
        Message message = new Message("second-topic-a",
                "你好这是我的第一条延迟消息:".getBytes(StandardCharsets.UTF_8));
        //缺少延迟属性 如果不填写延迟属性 延迟级别0 不延迟
        message.setDelayTimeLevel(3);//设置延迟属性
        message.setTags("TagB");
        //3.发送拿到返回结果
        SendResult result = producer.send(message);
        //获取的result数据 可以包括 消息id 发送成功失败的状态 保存这条消息的
        System.out.println("发送状态:" + result.getSendStatus());
        System.out.println("消息id" + result.getMsgId());
        System.out.println("所在队列id" + result.getMessageQueue());
    }
}
