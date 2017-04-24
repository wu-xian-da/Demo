package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavBase;

public interface NavBaseDao extends CrudDao<NavBase>{
	
	public List<NavBase> getParentList();
	
	public List<NavBase> getleafList();
	
	public List<NavBase> getListByParentId(Long parentId);
	
	public int updateNavBaseStatusBatch(List<NavBase> navBases);
	
	//web
	public List<NavBase> getShowFirstNavList();
	
	
	public NavBase getShowFirstNavById(Long id);
	
	public List<NavBase> getShowSecNavListByParentId(Long parentId);
}
