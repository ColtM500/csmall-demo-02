package cn.tedu.csmall.all.adapter.service.impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BusinessServiceImpl implements IBusinessService {
    //business作为业务入口,要调用orderService 目前无法注入
    //无法调用.
    private IOrderService dubboOrderService;
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
        dubboOrderService.orderAdd(orderAddDTO);
    }


}
