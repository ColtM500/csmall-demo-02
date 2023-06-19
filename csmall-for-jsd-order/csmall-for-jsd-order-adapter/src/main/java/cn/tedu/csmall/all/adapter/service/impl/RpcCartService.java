package cn.tedu.csmall.all.adapter.service.impl;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.cart.service.ICartService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RpcCartService {

    @Autowired(required = false)
    private ICartService cartService;

    public void cartDeleteFallback(OrderAddDTO orderAddDTO, Throwable e){
        log.error("购物车对user:{},商品:{} 删除失败,e:{}",orderAddDTO.getUserId(),orderAddDTO.getCommodityCode(),e);
    }

    //购物车删除 远程调用
    @SentinelResource(value = "cartDelete", fallback = "cartDeleteFallback")
    public void deleteUserCart(OrderAddDTO orderAddDTO){
        //delete Cart
        cartService.deleteUserCart(orderAddDTO.getUserId(), orderAddDTO.getCommodityCode());
    }
}
