package com.jianfei.w.base.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.jianfei.w.entity.system.Menu;
import com.jianfei.w.service.system.MenuService;

/**
 * 初始化 数据库 所有权限
* @author ZhangBo   
* @date 2014年10月23日 下午4:26:46
 */
@Service
@Lazy(false)
public class ShiroFilerChainManager {
    
    @Autowired
    private DefaultFilterChainManager filterChainManager;
    
    @Autowired
    private MenuService menuService;
    
    private Map<String, NamedFilterList> defaultFilterChains;

    @PostConstruct
    public void init() {
        defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
        List<Menu> menus = this.menuService.findAll();
        initFilterChains(menus);
    }
    
    public void initFilterChains(List<Menu> menus) {
        filterChainManager.getFilterChains().clear();
        
        if (defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
        
        for (Menu m : menus) {
            if (StringUtils.isNotBlank(m.getPermission()) && StringUtils.isNotBlank(m.getHref())) {
                filterChainManager.addToChain(m.getHref(), "perms", m.getPermission());
            }
        }
        filterChainManager.addToChain("/sys/**", "user");
    }
}