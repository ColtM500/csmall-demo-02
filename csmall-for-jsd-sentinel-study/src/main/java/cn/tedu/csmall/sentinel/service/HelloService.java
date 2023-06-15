package cn.tedu.csmall.sentinel.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String doSayHi(String name){
        return "hello "+name+";nice to meet you";
    }
}
