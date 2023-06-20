package cn.tedu.csmall.all.adapter.listener;

import cn.tedu.csmall.all.adapter.mapper.OrderMapper;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.mall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcException;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQTransactionListener
public class OrderAddTransactionListener implements RocketMQLocalTransactionListener {

    //远程调用
    @DubboReference
    private IStockService stockService;

    //本地持久层
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        //减库存commodityCode商品id 减库存count数量
        //新增订单userId commodityCode count
        //业务逻辑
        StockReduceCountDTO stockReduceCountDTO = new StockReduceCountDTO();
        OrderAddDTO orderAddDTO = (OrderAddDTO) o;
        stockReduceCountDTO.setReduceCount(orderAddDTO.getCount());
        stockReduceCountDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        try {
            stockService.reduceCommodityCount(stockReduceCountDTO);
        } catch (CoolSharkServiceException e) {
            //确定库存没了
            log.error("msg:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        } catch (RpcException e) {
            log.error("msg:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception e) {
            log.error("msg:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        //增订单

        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return null;
    }
}
