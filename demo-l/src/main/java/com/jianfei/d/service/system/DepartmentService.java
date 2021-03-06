package com.jianfei.d.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.DepartmentDao;
import com.jianfei.d.entity.common.TreeVo;
import com.jianfei.d.entity.system.Department;

@Service
public class DepartmentService extends CrudService<DepartmentDao, Department>{

   /* public List<Department> getParent(){
        return this.dao.getParent();
    }*/
	public List<TreeVo> findTree(){
		return this.dao.findTree();
	}
    
}
