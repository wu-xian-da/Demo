package com.jianfei.w.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.info.TemplateDao;
import com.jianfei.w.entity.common.TemplateType;
import com.jianfei.w.entity.info.Template;

@Service
public class TemplateService extends CrudService<TemplateDao, Template>{

	/******
	 * 根据模板类型查询模板
	 * @param type
	 * @return
	 */
	public List<Template> getListByType(TemplateType type){
		return this.dao.getListByType(type);
	}
	
}
