package com.jianfei.w.dao.info;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.common.TemplateType;
import com.jianfei.w.entity.info.Template;

public interface TemplateDao extends CrudDao<Template> {

	public List<Template> getListByType(TemplateType type);
	
}
