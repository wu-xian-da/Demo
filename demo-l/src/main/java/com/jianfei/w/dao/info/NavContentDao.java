package com.jianfei.w.dao.info;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.NavContent;

public interface NavContentDao extends CrudDao<NavContent>{

	public int deleteByNavId(Long navId);
	
	public int updateByNavId(NavContent navContent);
	
	public NavContent getByNavId(Long navId);
	
	public List<NavContent> getListByTemplateId(Long contentTemplateId);
	
}
