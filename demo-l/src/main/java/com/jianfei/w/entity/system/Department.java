package com.jianfei.w.entity.system;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.w.base.annotation.FormQuery;
import com.jianfei.w.base.entity.BaseEntity;

/**
 * 用户所属部门
* @author ZhangBo   
* @date 2017年4月1日 下午2:53:35
 */
@Getter
@Setter
public class Department extends BaseEntity{

    private static final long serialVersionUID = 8119449526252120186L;

    @FormQuery
    private String name; //部门名称
    
    @FormQuery("parent.id")
    private Department parent; //上级部门
    
    private Integer sort = 1; //排序号(默认1)
    
}
