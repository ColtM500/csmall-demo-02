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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String LOCK_KEY = "ORDER:ADD:";

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

    public boolean tryLock(String lockKey, String randCode){
        int tryTimes = 0;
        //redis抢锁 操作String类型
        ValueOperations stringOperations = redisTemplate.opsForValue();
        //setIfAbsent 就是setnex lockKey randCode EX 10
        Boolean hadLock = stringOperations.setIfAbsent(lockKey, randCode, 10, TimeUnit.SECONDS);
        if (hadLock){
            return true;
        } else {
            do {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tryTimes++;
                tryLock(lockKey, randCode);
            } while (tryTimes>=4);
            return false;
            }
    }
}
