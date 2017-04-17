package com.jianfei.w.entity.common;

/******
 * 信息栏目状态
 * @author ATH
 *
 */
public enum NavStatus {
	
	ZS("展示"), QXZS("取消展示");
	
	private String name;
	
	private NavStatus(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
