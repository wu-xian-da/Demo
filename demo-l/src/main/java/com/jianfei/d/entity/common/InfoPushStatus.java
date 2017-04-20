package com.jianfei.d.entity.common;

/******
 * 信息推送状态(包括：图片新闻、紧急公告、栏目信息等)
 * @author ATH
 *
 */
public enum InfoPushStatus {
	
	YTS("已推送"), WTS("未推送");
	
	private String name;
	
	private InfoPushStatus(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
