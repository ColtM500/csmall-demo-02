package com.tarena.csmall.starter.properties.bean;

import lombok.Data;


@Data
public class UserUtils {
    //UserUtils使用的属性值 name jwt
    private String name;
    private String jwt;
    public void getRequestIps(){
        System.out.println();
    }
}