package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.TemplateType;
import com.jianfei.d.entity.info.Template;

public interface TemplateDao extends CrudDao<Template> {

	public List<Template> getListByType(TemplateType type);
	
}
