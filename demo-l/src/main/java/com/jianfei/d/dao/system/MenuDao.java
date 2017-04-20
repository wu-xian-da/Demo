package com.jianfei.d.dao.system;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.system.Menu;

public interface MenuDao extends CrudDao<Menu>{

    public List<Menu> getParent();
    
    public List<Menu> findParentChild();
    
}
