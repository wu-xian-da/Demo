package com.jianfei.w.common.vo;

import lombok.Getter;

@Getter
public enum MessageStatus {

    SUC("info"),WARN("warning"),ERROR("danger");
    
    private String name;
    
    private MessageStatus(String name){
        this.name = name;
    }
}
