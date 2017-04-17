package com.jianfei.w.dao.info;

import java.util.List;
import java.util.Map;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.NavSecondMenu;

public interface NavSecondMenuDao extends CrudDao<NavSecondMenu>{

	public int deleteByNavId(Long navId);
	
	public int updateByNavId(NavSecondMenu navSecondMenu);
	
	public NavSecondMenu getByNavId(Long navId);
	
	public List<NavSecondMenu> getListByTemplateId(Map<String, Object> map);
	
}
