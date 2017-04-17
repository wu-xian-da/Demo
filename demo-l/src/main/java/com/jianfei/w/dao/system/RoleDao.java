package com.jianfei.w.dao.system;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.system.Role;

public interface RoleDao extends CrudDao<Role>{

    public int deleteRoleMenu(Long id);

    public int insertRoleMenu(Role role);
    
    public Integer getCountByRoleName(Role role);
    
    public Role getRoleMenus(Long id);
    
}
