package cn.tedu.mall.stock.service;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;

public interface IStockService {

    // 减少库存数的业务逻辑层方法
    void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO);

    void returnStock(String commodityCode, Integer count);
}

