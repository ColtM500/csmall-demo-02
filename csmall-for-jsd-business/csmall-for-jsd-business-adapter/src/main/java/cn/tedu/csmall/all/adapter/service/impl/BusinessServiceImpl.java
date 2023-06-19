package cn.tedu.csmall.all.adapter.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.order.service.IOrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BusinessServiceImpl implements IBusinessService {
    //business作为业务入口,要调用orderService 目前无法注入
    //无法调用.

    //此处为同步调用
//    @DubboReference
//    private IOrderService dubboOrderService;

    @Autowired(required = false)
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void buy() {
        // 模拟触发购买业务
        // 先实例化一个用于新增订单的DTO
        OrderAddDTO orderAddDTO=new OrderAddDTO();
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setMoney(100);
        orderAddDTO.setCount(2);
        // 暂时只能进行输出,后期有微服务支持可以调用其他模块
        log.info("新增订单信息为:{}",orderAddDTO);
        // dubbo调用order模块新增订单的方法
        // 将上面实例化的orderAddDTO当做参数,让它在数据库中生效
//        dubboOrderService.orderAdd(orderAddDTO);

        //异步调用
        //orderAddDTO生成的数据 保证生产端对数据进行序列化 消费端可以实现反序列化
        String orderAddJson = JSON.toJSONString(orderAddDTO);
        rocketMQTemplate.convertAndSend("business-add-order-topic", orderAddJson);

    }


}
