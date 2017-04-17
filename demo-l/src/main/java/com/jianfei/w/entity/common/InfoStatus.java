package com.jianfei.w.entity.common;

/******
 * 信息状态(包括：图片新闻、紧急公告、栏目信息等)
 * @author ATH
 *
 */
public enum InfoStatus {
	
	DSH("待审核"), SHTG("审核通过"), YSK("已上刊"), YXK("已下刊");
	
	private String name;
	
	private InfoStatus(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
