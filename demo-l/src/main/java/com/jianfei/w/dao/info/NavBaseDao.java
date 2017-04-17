package com.jianfei.w.dao.info;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.NavBase;

public interface NavBaseDao extends CrudDao<NavBase>{
	
	public List<NavBase> getParentList();
	
	public List<NavBase> getleafList();
	
	public List<NavBase> getListByParentId(Long parentId);
	
	public int updateNavBaseStatusBatch(List<NavBase> navBases);
	
}
