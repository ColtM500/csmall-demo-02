package cn.tedu.csmall.all.adapter.consumer;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.entity.Order;
import cn.tedu.mall.order.service.IOrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RocketMQMessageListener(
        consumerGroup = "business-add-order-group",
        topic = "business-add-order-topic",
        selectorExpression = "*"
)
public class BusinessOrderAddConsumer implements RocketMQListener<MessageExt> {
    @DubboReference
    private IOrderService orderService;

    @Override
    public void onMessage(MessageExt messageExt) {
        String msgId = messageExt.getMsgId();//msgId用来处理分布式锁
        //解析messageExt对象 解析byte数组 拿到orderAddJson
        byte[] body = messageExt.getBody();
        String orderAddJson = new String(body, StandardCharsets.UTF_8);
        //调用orderService的远程 orderAdd方法
        OrderAddDTO orderAddDTO = JSON.toJavaObject(JSON.parseObject(orderAddJson), OrderAddDTO.class);
        orderService.orderAdd(orderAddDTO);
    }
}
