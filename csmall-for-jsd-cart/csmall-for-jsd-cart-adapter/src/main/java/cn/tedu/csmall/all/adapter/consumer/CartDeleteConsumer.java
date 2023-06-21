package cn.tedu.csmall.all.adapter.consumer;

import cn.tedu.csmall.all.adapter.mapper.CartMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.color.CMMException;

@Component
@Slf4j
@RocketMQMessageListener(
        consumerGroup = "consumer-group-3",
        topic = "order-add-topic",
        selectorExpression = "OrderAddTag"
)
public class CartDeleteConsumer implements RocketMQListener<String> {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void onMessage(String message) {
        //1.解析消息
        String userId = message.split(":")[0];
        String commodityCode = message.split(":")[1];
        //2.调用删除

    }
}
