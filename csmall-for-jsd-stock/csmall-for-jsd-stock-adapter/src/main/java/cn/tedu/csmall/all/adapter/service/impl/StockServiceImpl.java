package cn.tedu.csmall.all.adapter.service.impl;

import cn.tedu.csmall.all.adapter.mapper.StockMapper;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.mall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 添加一个provider角色的dubbo注解，底层在创建bean对象时，
 * 会包裹这个对象启动一个server服务端，接收consumer调用
 */
@DubboService(loadbalance = "roundrobin",weight = 1)
@Service
@Slf4j
public class StockServiceImpl implements IStockService {
    @Value("${server.port}")
    private String port;

    @Autowired
    private StockMapper stockMapper;
    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
        System.out.println(
                "*********目前provider调用的实例,运行在server.port:"+port+"*********");
        int count=stockMapper.selectStockCountByCommodityCode(
                stockReduceCountDTO.getCommodityCode());
        if (count==0){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"库存不存在"+stockReduceCountDTO.getCommodityCode());
        }
        // 调用减少库存的持久层方法
        int row=stockMapper.updateStockCount(
                stockReduceCountDTO.getCommodityCode(),
                stockReduceCountDTO.getReduceCount());
        // 可以在这里判断row的值
        if(row==0){
            throw new CoolSharkServiceException(
                        ResponseCode.BAD_REQUEST,"库存不足");
        }
        log.info("库存减少完成!");

    }

    @Override
    public void returnStock(String commodityCode, Integer count) {
        //直接调用mapper处理还库存逻辑
        stockMapper.incrStockCount(commodityCode, count);
    }
}
