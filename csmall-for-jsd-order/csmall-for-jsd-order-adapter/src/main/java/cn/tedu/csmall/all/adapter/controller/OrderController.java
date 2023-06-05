package cn.tedu.csmall.all.adapter.controller;

import cn.tedu.csmall.all.service.IOrderService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.entity.Order;
import cn.tedu.csmall.commons.restful.JsonPage;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/order")
@Api(tags = "订单管理模块")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @PostMapping("/add")
    @ApiOperation("新增订单的功能")
    public JsonResult orderAdd(OrderAddDTO orderAddDTO){
        try{
            orderService.orderAdd(orderAddDTO);
        }catch (NullPointerException e){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"订单新增业务注入对象为空");
        }
        return JsonResult.ok("新增订单完成!");
    }
    @GetMapping("/page")
    @ApiOperation("分页查询所有订单")
    @ApiImplicitParams({
            @ApiImplicitParam(value ="页码",name="page",example = "1"),
            @ApiImplicitParam(value ="每页条数",name="pageSize",example = "10")
    })
    public JsonResult<JsonPage<Order>> pageOrder(Integer page, Integer pageSize){
        JsonPage<Order> jsonPage=
                orderService.getAllOrdersByPage(page,pageSize);
        return JsonResult.ok("查询完成",jsonPage);
    }
}
