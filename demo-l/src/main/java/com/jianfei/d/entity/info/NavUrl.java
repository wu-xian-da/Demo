package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/******
 * 栏目扩展实体类_外链
 * @author ATH
 *
 */
@Getter
@Setter
public class NavUrl extends BaseEntity{
	
	private static final long serialVersionUID = 7957020283169712979L;

	private String url;//外链URL

    private String target;//_blank:新页面打开;_self:当前页打开;

    private Long navId;//所属基础栏目ID

    private Date createTime;

    private Date updateTime;
    
}