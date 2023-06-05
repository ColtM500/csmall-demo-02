package cn.tedu.csmall.all.adapter.service.impl;

import cn.tedu.csmall.all.adapter.mapper.CartMapper;
import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;
import cn.tedu.csmall.commons.pojo.cart.entity.Cart;
import cn.tedu.mall.cart.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {
    // cart模块的业务逻辑层实现类要装配cart模块的持久层来实现业务
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void cartAdd(CartAddDTO cartAddDTO) {
        // 业务逻辑层方法的参数是CartAddDTO,但是操作数据库进行新增购物车的方法参数是Cart
        // 所以我们要将参数CartAddDTO类型对象中的属性赋值转换为Cart类型对象
        // 实例化Cart类型对象
        Cart cart=new Cart();
        // 使用BeanUtils工具类将同名属性赋值
        BeanUtils.copyProperties(cartAddDTO,cart);
        // cart对象属性被赋值后,就可以执行数据库新增操作了
        int row=cartMapper.insertCart(cart);
        // 下面可以根据row的值是否为1,判断新增是否成功
        log.info("新增购物车商品完成!{}",cart);
    }

    @Override
    public void deleteUserCart(String userId, String commodityCode) {
        // 删除购物车中商品的方法直接调用即可
        cartMapper.deleteCartByUserIdAndCommodityCode(userId,commodityCode);
        // 日志输出
        log.info("购物车删除完成!");
    }
}
