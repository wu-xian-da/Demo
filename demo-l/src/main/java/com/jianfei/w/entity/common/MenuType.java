package com.jianfei.w.entity.common;

import lombok.Getter;

@Getter
public enum MenuType {

    MENU("菜单"),BUTTON("按钮");
    
    private String name;
    
    MenuType(String name){
        this.name = name;
    }
    
}
