package cn.tedu.csmall.sentinel.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalExceptionHandler {

    public static String sayHiBlockHandle(String name, BlockException e){
        /**
         * 当前工程项目所有可能限流的降级处理全部放到这个类中
         */
        log.error(e.getMessage());
        return "sorry,error happened," +name;
    }
}
