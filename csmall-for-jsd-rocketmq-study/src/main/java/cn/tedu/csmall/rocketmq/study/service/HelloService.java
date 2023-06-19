package cn.tedu.csmall.rocketmq.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {

    public boolean doSayHi(String msg) {
        //打印一下msg
        try {
            //业务方法
            System.out.println("业务层接收到消息msg:" + msg);
            return true;
        } catch (Exception e) {
            log.error("系统异常:{}", e.getMessage());
            return false;
        }
    }
}
