package com.jianfei.w.service.info;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.info.NavUrlDao;
import com.jianfei.w.entity.info.NavUrl;

@Service
public class NavUrlService extends CrudService<NavUrlDao, NavUrl>{

	/******
	 * 操作前：先根据navId查询，如果存在，根据navId修改，如果不存在，新增
	 */
	
	/******
	 * 根据navId删除
	 * @param navId
	 * @return
	 */
	public int deleteByNavId(Long navId){
		return this.dao.deleteByNavId(navId);
	}
	
	/******
	 * 根据navId修改
	 * @param navUrl
	 * @return
	 */
	public int updateByNavId(NavUrl navUrl){
		return this.dao.updateByNavId(navUrl);
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavUrl getByNavId(Long navId){
		return this.dao.getByNavId(navId);
	}
}
