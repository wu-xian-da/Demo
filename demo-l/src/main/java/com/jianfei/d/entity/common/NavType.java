package com.jianfei.d.entity.common;

/******
 * 栏目类型
 * @author ATH
 *
 */
public enum NavType {
	
	XXEJCD("下辖二级菜单"), WEJCD("无二级菜单"), URLWL("URL外链");
	
	private String name;
	
	private NavType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
