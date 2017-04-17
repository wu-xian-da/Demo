package com.jianfei.w.dao.info;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.NavUrl;

public interface NavUrlDao extends CrudDao<NavUrl>{

	public int deleteByNavId(Long navId);
	
	public int updateByNavId(NavUrl navUrl);
	
	public NavUrl getByNavId(Long navId);
	
}
