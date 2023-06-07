package cn.tedu.csmall.all.adapter.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/business")
@Api(tags = "业务触发模块")
@Slf4j
public class BusinessController {
    //读取自定义属性
    @Value("${tedu.class.name}")
    private String className;
    @Value("${tedu.project.name}")
    private String projectName;

    @Autowired
    private IBusinessService businessService;

    @PostMapping("/buy")
    @ApiOperation("执行业务的触发")
    public JsonResult buy(){
        try{
            businessService.buy();
        }catch (NullPointerException e){
            log.error("购买失败,原因:购买注入的OrderService实现未空");
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"OrderService为空!");
        }
        return JsonResult.ok("购买完成!");
    }

}




