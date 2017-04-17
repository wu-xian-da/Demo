package com.jianfei.w.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.info.NavContentDao;
import com.jianfei.w.entity.info.NavContent;

@Service
public class NavContentService extends CrudService<NavContentDao, NavContent>{

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
     * @param navContent
     * @return
     */
	public int updateByNavId(NavContent navContent){
		return this.dao.updateByNavId(navContent);
	}
	
	/******
	 * 根据navId查询
	 * @param navId
	 * @return
	 */
	public NavContent getByNavId(Long navId){
		return this.dao.getByNavId(navId);
	}
	
	/******
	 * 内容模板删除时使用
	 * @param contentTemplateId
	 * @return
	 */
	public List<NavContent> getListByTemplateId(Long contentTemplateId){
		return this.dao.getListByTemplateId(contentTemplateId);
	}
}
