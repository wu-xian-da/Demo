package com.jianfei.d.dao.system;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.system.Role;

public interface RoleDao extends CrudDao<Role>{

    public int deleteRoleMenu(Long id);

    public int insertRoleMenu(Role role);
    
    public Integer getCountByRoleName(Role role);
    
    public Role getRoleMenus(Long id);
    
}
