package com.jianfei.w.dao.info;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.NavInfo;

public interface NavInfoDao extends CrudDao<NavInfo>{
	
	public List<NavInfo> getListByNavId(Long navId);

	public int updateNavInfoStatusBatch(List<NavInfo> navInfos);
	
	public int updateNavInfoPushStatusBatch(List<NavInfo> navInfos);
	
}
