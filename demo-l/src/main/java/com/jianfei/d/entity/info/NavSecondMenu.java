package com.jianfei.d.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;

/******
 * 栏目扩展实体类_下辖二级菜单
 * @author ATH
 *
 */
@Getter
@Setter
public class NavSecondMenu extends BaseEntity{
	
	private static final long serialVersionUID = 5154096983542390464L;

	private String menuHeadIcon;//头部图片路径

    private Long menuListTemplateId;//列表模板ID

    private Long menuContentTemplateId;//内容模板ID

    private Long navId;//所属基础栏目ID
    private String navName;//所属栏目名称

    private Date createTime;

    private Date updateTime;
    
    //列表模板
    private Template menuListTemplate = null;
    
    //内容模板
    private Template menuContentTemplate = null;
    
}