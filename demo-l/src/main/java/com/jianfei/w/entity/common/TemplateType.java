package com.jianfei.w.entity.common;

/******
 * 模板类型
 * @author ATH
 *
 */
public enum TemplateType {
	
	LB("列表页模板"), NR("内容页模板");
	
	private String name;
	
	private TemplateType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
