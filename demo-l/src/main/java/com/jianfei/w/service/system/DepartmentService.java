package com.jianfei.w.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.system.DepartmentDao;
import com.jianfei.w.entity.system.Department;

@Service
public class DepartmentService extends CrudService<DepartmentDao, Department>{

    public List<Department> getParent(){
        return this.dao.getParent();
    }
    
}
