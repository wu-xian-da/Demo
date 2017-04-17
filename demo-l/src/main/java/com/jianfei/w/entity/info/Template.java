package com.jianfei.w.entity.info;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.w.base.entity.BaseEntity;
import com.jianfei.w.entity.common.TemplateType;

/******
 * 模板实体
 * @author ATH
 *
 */
@Getter
@Setter
public class Template extends BaseEntity{
	
	private static final long serialVersionUID = -7626314977483923843L;

	private String name;//名称

    private String imgPath;//模板图片路径

    private String filePath;//模板文件路径

    private TemplateType type;//类型(列表页模板;内容页模板;)

    private Date createTime;

    private Date updateTime;
    
}