package cn.tedu.csmall.all.adapter.mapper;

import cn.tedu.csmall.commons.pojo.order.entity.Order;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    // 新增订单的方法
    @Insert("insert into order_tbl(user_id,commodity_code,count,money) values" +
            "(#{userId},#{commodityCode},#{count},#{money})")
    int insertOrder(Order order);

    // 分页显示所有订单的方法
    // PageHelper框架完成分页的原理是在运行的sql语句后自动添加limit关键字
    // 所以我们在编写查询方法之前,无需关注分页操作,持久层编写分页查询和普通查询没有区别
    // 无论注解还是xml文件都没有区别
    @Select("select id,user_id,commodity_code,count,money from order_tbl")
    List<Order> findAllOrders();

    @Select("select * from order_tbl " +
            "where user_id=#{userId} and commodity_code=#{commodityCode}")
    Order selectOrderByMsg(@Param("userId") String userId
            , @Param("commodityCode") String commodityCode);
}







