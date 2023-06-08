package cn.tedu.csmall.all.adapter.service.impl;


import cn.tedu.csmall.all.adapter.mapper.OrderMapper;

import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.entity.Order;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonPage;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.mall.cart.service.ICartService;
import cn.tedu.mall.order.service.IOrderService;
import cn.tedu.mall.stock.service.IStockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// order模块是具备生产者特征的,它会被business模块调用,所以也要加@DubboService注解
@DubboService
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    //添加@DubboReference注解,表示当前业务逻辑层中要消费其他模块的服务了
    // 注解后声明的应该是Dubbo注册到Nacos其他模块声明的业务逻辑层接口
    // 业务逻辑层接口的实现类会在Dubbo框架下自动获取

//    @Autowired(required = false)
    @DubboReference
    private IStockService stockService;

//    @Autowired(required = false)
    @DubboReference
    private ICartService cartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        // 1.先减去订单中商品的库存数(调用Stock模块减少库存的方法)
        // 库存减少方法需要参数类型是StockReduceCountDTO,我们需要先实例化它
        StockReduceCountDTO countDTO=new StockReduceCountDTO();
        countDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        countDTO.setReduceCount(orderAddDTO.getCount());
        // 利用Dubbo调用stock模块减少库存的业务逻辑层方法实现功能
        stockService.reduceCommodityCount(countDTO);

        // 2.从购物车中删除用户选中的商品(调用Cart模块删除购物车中商品的方法)
        // 利用dubbo调用cart模块删除购物车中商品的方法实现功能
        cartService.deleteUserCart(orderAddDTO.getUserId(),
                                    orderAddDTO.getCommodityCode());
        // 3.新增当前订单信息
        Order order=new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        // 下面执行新增
        orderMapper.insertOrder(order);
        log.info("新增订单信息为:{}",order);

        // 为了实现Seata的回滚效果,在这里随机抛出异常
//        if( Math.random()<0.5){
//            // 抛出异常
//            throw new CoolSharkServiceException(
//                    ResponseCode.INTERNAL_SERVER_ERROR,"发送随机异常");
//        }
    }

    // 分页查询所有订单的业务逻辑层方法
    // page是页码,pageSize是每页条数
    public JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize){
        // PageHelper框架实现分页的核心操作:
        // 在要执行分页的查询运行之前,设置分页的条件
        // 设置的方式如下(固定的格式,PageHelper框架设计的)
        // PageHelper设置page为1就是查询第一页
        PageHelper.startPage(page,pageSize);
        // 下面开始持久层方法的调用
        // 此方法运行时因为上面设置了分页条件,sql语句中会自动出现limit关键字
        List<Order> list = orderMapper.findAllOrders();
        // 查询结果list中包含的就是分页查询范围的数据了
        // 但是这个数据不包含分页信息(总页数,总条数,是否是首页,是否是末页等)
        // 我们要利用PageHelper框架提供的PageInfo类型,来进行返回
        // PageInfo对象可以既包含分页数据,又包含分页信息
        // 这些信息会在PageInfo对象实例化时自动计算,并赋值到PageInfo对象中
        return JsonPage.restPage(new PageInfo<>(list));
    }



}







