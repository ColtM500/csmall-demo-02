package cn.tedu.csmall.all.adapter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {

    // 修改(减少)指定商品库存数的方法
    @Update("update stock_tbl set count=count-#{reduceCount} where " +
            " commodity_code=#{commodityCode} and count>=#{reduceCount}")
    int updateStockCount(@Param("commodityCode") String commodityCode,
                         @Param("reduceCount") Integer reduceCount);


    @Select("select count(id) from stock_tbl where commodity_code=#{code}")
    int selectStockCountByCommodityCode(@Param("code")String code);
}
