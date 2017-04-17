package com.jianfei.w.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.system.MenuDao;
import com.jianfei.w.entity.system.Menu;

@Service
public class MenuService extends CrudService<MenuDao, Menu>{

    public List<Menu> getParent(){
        return this.dao.getParent();
    }
    
    public List<Menu> findParentChild(){
        return this.dao.findParentChild();
    }
    
}
