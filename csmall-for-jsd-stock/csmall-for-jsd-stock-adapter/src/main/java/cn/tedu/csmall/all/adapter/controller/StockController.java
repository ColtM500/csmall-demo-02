package cn.tedu.csmall.all.adapter.controller;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.mall.stock.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/stock")
@Api(tags = "库存管理模块")
public class StockController {
    //读取自定义属性
    @Value("${tedu.class.name}")
    private String className;
    @Value("${tedu.project.name}")
    private String projectName;

    @Autowired
    private IStockService stockService;
    @GetMapping("/reduce/count")
    @ApiOperation("减少指定商品的库存数")
    public JsonResult reduceCommodityCount(
            StockReduceCountDTO stockReduceCountDTO){
        // 调用业务逻辑层方法
        stockService.reduceCommodityCount(stockReduceCountDTO);
        return JsonResult.ok("库存减少完成!");
    }
}
