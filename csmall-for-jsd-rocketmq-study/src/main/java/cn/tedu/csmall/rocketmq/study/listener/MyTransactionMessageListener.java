package cn.tedu.csmall.rocketmq.study.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 写法和消费者类似 区别在于
 * consumer接收普通消息 这个监听器接收的是半消息
 * 不会对接rocketmq中任何的主题 必须写在本地（哪发送的半消息 哪写这个监听器）
 */
@Component
@Slf4j
@RocketMQTransactionListener
public class MyTransactionMessageListener implements RocketMQLocalTransactionListener {


    /**
     * 本地业务方法
     * 消息事务协调器会根据返回值 决定流程
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.debug("业务：当前系统时间:{}"+LocalDateTime.now());
        log.debug("消息本地业务处理开始执行，本次返回COMMIT");
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.debug("回调：当前系统时间:{}"+LocalDateTime.now());
        log.debug("消息事务进入回调方法，本次回调方法返回COMMIT");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
