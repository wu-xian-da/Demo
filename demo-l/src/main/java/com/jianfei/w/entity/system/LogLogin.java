package com.jianfei.w.entity.system;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jianfei.w.base.annotation.FormQuery;
import com.jianfei.w.base.entity.BaseEntity;
import com.jianfei.w.entity.common.LoginStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogLogin extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    @FormQuery
    private String loginName;
    
    private String params;
    
    private Date date;
    
    private String userAgent;
    
    @FormQuery
    private String ip;
    
    private LoginStatus status;
    
    
    
}
