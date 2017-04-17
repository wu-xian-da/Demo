package com.jianfei.w.entity.common;

import lombok.Getter;

@Getter
public enum UserStatus {

    OPEN("启用"),
    CLOSE("禁用");
    
    private String name;
    
    UserStatus(String name){
        this.name = name;
    }

}
