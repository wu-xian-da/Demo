package com.jianfei.d.dao.info;

import java.util.List;
import java.util.Map;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.info.NavInfo;

public interface NavInfoDao extends CrudDao<NavInfo>{
	
	public List<NavInfo> getListByNavId(Long navId);

	public int updateNavInfoStatusBatch(List<NavInfo> navInfos);
	
	public int updateNavInfoPushStatusBatch(List<NavInfo> navInfos);
	
	public List<NavInfo> getListByNavIdAndStatus(Map<String,Object> map);
	
}
