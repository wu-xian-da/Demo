package com.jianfei.w.dao.system;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.system.Department;

public interface DepartmentDao extends CrudDao<Department>{

    public List<Department> getParent();
    
}
