package cn.tedu.csmall.test.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class MyConsumer {

    @Test
    public void consume01() throws MQClientException {
        //1.创建一个consumer对象 连接nameserver
        //创建一个消费对象 提供nameserver连接地址 和 消费者分组
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("first-consumer-group");
        consumer.setNamesrvAddr("localhost:9876");
        //定义监听队列所属的主题 和 过滤的Tags
        consumer.subscribe("first-topic-a", "*");
        //2.监听队列 实现消费 队列是从主题中获取的
        consumer.setMessageListener(new MessageListenerConcurrently() {
            /**
             * 每次拿到消息 都调用consumeMessage方法
             * @param list 消息列表 包含消费者拿到的消费对象 每次列表里 只包装一个消息
             * @param context 消费环境 消费逻辑的上下文对象 返回消费的结果
             * @return
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                //获取消息解析
                System.out.println("当前消费的消息个数：" + list.size());
                MessageExt messageExt = list.get(0);
                //执行消费逻辑
                byte[] body = messageExt.getBody();
                String msgStr = new String(body, StandardCharsets.UTF_8);
                messageExt.getMsgId();
                //执行消费逻辑
                System.out.println("消费者拿到消费信息" + msgStr);
                //返回成功失败
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //建立和nameserver长连接
        consumer.start();
        while (true) ;
    }

    /**
     * 这是第二组消费者
     *
     * @throws MQClientException
     */
    @Test
    public void consume02() throws MQClientException {
        //1.创建一个consumer对象 连接nameserver
        //创建一个消费对象 提供nameserver连接地址 和 消费者分组
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("second-consumer-group");
        consumer.setNamesrvAddr("localhost:9876");
        //定义监听队列所属的主题 和 过滤的Tags
        consumer.subscribe("second-topic-a", "TagA");
        //2.监听队列 实现消费 队列是从主题中获取的
        consumer.setMessageListener(new MessageListenerConcurrently() {
            /**
             * 每次拿到消息 都调用consumeMessage方法
             * @param list 消息列表 包含消费者拿到的消费对象 每次列表里 只包装一个消息
             * @param context 消费环境 消费逻辑的上下文对象 返回消费的结果
             * @return
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                //获取消息解析
                System.out.println("当前消费的消息个数：" + list.size());
                MessageExt messageExt = list.get(0);
                //执行消费逻辑
                byte[] body = messageExt.getBody();
                String msgStr = new String(body, StandardCharsets.UTF_8);
                messageExt.getMsgId();
                //执行消费逻辑
                System.out.println("消费者拿到消费信息" + msgStr);
                //返回成功失败
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //建立和nameserver长连接
        consumer.start();
        while (true) ;
    }

    /**
     * 这是第二组消费者
     *
     * @throws MQClientException
     */
    @Test
    public void consume03() throws MQClientException {
        //1.创建一个consumer对象 连接nameserver
        //创建一个消费对象 提供nameserver连接地址 和 消费者分组
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("third-consumer-group");
        consumer.setNamesrvAddr("localhost:9876");
        //定义监听队列所属的主题 和 过滤的Tags
        consumer.subscribe("second-topic-a", "*");
        //2.监听队列 实现消费 队列是从主题中获取的
        consumer.setMessageListener(new MessageListenerConcurrently() {
            /**
             * 每次拿到消息 都调用consumeMessage方法
             * @param list 消息列表 包含消费者拿到的消费对象 每次列表里 只包装一个消息
             * @param context 消费环境 消费逻辑的上下文对象 返回消费的结果
             * @return
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                //获取消息解析
                System.out.println("当前消费的消息个数：" + list.size());
                MessageExt messageExt = list.get(0);
                //执行消费逻辑
                byte[] body = messageExt.getBody();
                String msgStr = new String(body, StandardCharsets.UTF_8);
                messageExt.getMsgId();
                //执行消费逻辑
                System.out.println("消费者拿到消费信息" + msgStr + LocalDateTime.now());
                //返回成功失败
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //建立和nameserver长连接
        consumer.start();
        while (true) ;
    }
}
