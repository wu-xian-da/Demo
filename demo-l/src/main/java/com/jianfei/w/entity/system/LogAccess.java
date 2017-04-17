package com.jianfei.w.entity.system;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jianfei.w.base.annotation.FormQuery;
import com.jianfei.w.base.entity.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogAccess extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    @FormQuery("user.loginName")
    private User user;
    
    private Date date;
    
    private String requestUrl;
    
    private String params;
    
    @FormQuery
    private String ip;

}
