package com.jianfei.w.entity.common;

/******
 * 栏目层级
 * @author ATH
 *
 */
public enum NavLevel {
	
	YJLM("一级栏目"), EJLM("二级栏目");
	
	private String name;
	
	private NavLevel(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
