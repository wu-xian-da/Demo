package com.jianfei.d.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.MenuDao;
import com.jianfei.d.entity.system.Menu;

@Service
public class MenuService extends CrudService<MenuDao, Menu>{

    public List<Menu> getParent(){
        return this.dao.getParent();
    }
    
    public List<Menu> findParentChild(){
        return this.dao.findParentChild();
    }
    
}
