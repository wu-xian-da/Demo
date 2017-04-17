package com.jianfei.w.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.info.NavInfoDao;
import com.jianfei.w.entity.info.NavInfo;

@Service
public class NavInfoService extends CrudService<NavInfoDao, NavInfo>{
	
	/******
	 * 获取某一栏目下的栏目信息
	 * @param navId
	 * @return
	 */
	public List<NavInfo> getListByNavId(Long navId){
		return this.dao.getListByNavId(navId);
	}

	/******
	 * 批量修改状态
	 * @param navInfos
	 * @return
	 */
	public int updateNavInfoStatusBatch(List<NavInfo> navInfos){
		return this.dao.updateNavInfoStatusBatch(navInfos);
	}
	
	public int updateNavInfoPushStatusBatch(List<NavInfo> navInfos){
		return this.dao.updateNavInfoPushStatusBatch(navInfos);
	}
	
}
