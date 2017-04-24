package com.jianfei.d.dao.system;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.TreeVo;
import com.jianfei.d.entity.system.Department;

public interface DepartmentDao extends CrudDao<Department>{

    //public List<Department> getParent();
	public List<TreeVo> findTree();
    
}
