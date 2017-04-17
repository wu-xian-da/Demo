package com.jianfei.w.dao.system;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.system.Menu;

public interface MenuDao extends CrudDao<Menu>{

    public List<Menu> getParent();
    
    public List<Menu> findParentChild();
    
}
