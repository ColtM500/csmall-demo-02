package cn.tedu.csmall.all.adapter.service.impl;

import cn.tedu.csmall.all.adapter.mapper.StockMapper;
import cn.tedu.csmall.all.service.IStockService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements IStockService {
    @Autowired
    private StockMapper stockMapper;
    @Override
    public void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO) {
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
}
