package com.jianfei.d.controller.system;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.controller.base.BaseController;
import com.jianfei.d.common.vo.MessageStatus;
import com.jianfei.d.entity.system.Role;
import com.jianfei.d.service.system.MenuService;
import com.jianfei.d.service.system.RoleService;
import com.jianfei.d.service.system.UserService;

/**
 * 系统角色
* @author ZhangBo   
* @date 2017年4月6日 上午10:31:14
 */
@Controller
@RequestMapping("/sys/system/role")
public class RoleController extends BaseController{
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private UserService userService;
    
    private void setModel(Model model){
        model.addAttribute("menus", this.menuService.findParentChild());
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        setModel(model);
        return "system/role/form";
    }

    @PostMapping("/create")
    public String create(Role role, BindingResult result, Model model, RedirectAttributes attrs) {
        Integer count = this.roleService.getCountByRoleName(role);
        if(count != null && count > 0){
            super.addMessage(model, MessageStatus.WARN, "角色名称已存在，请更换");
            setModel(model);
            return "system/role/form";
        }
        
        if(role.getMenus() == null || role.getMenus().isEmpty()){
            super.addMessage(attrs, MessageStatus.WARN, "角色没有选择授权信息");
            return "redirect:/sys/system/role";
        }
        role.filterResource(); //去除空元素
        
        this.roleService.save(role);
        super.addMessage(attrs, "保存角色成功");
        return "redirect:/sys/system/role";
    }
    
    @GetMapping("/update/{pid}")
    public String updateForm(@PathVariable("pid") Long id, Model model){
        Role role = this.roleService.get(id);
        model.addAttribute("role", role);
        setModel(model);
        return "system/role/form";
    }
    
    @PostMapping("/update/{pid}")
    public String update(Role role, BindingResult result, Model model, RedirectAttributes attrs){
        if(!role.getName().equals(role.getOldName())){
            Integer count = this.roleService.getCountByRoleName(role);
            if(count != null && count > 0){
                super.addMessage(attrs, MessageStatus.WARN, "角色名称已存在，请更换");
                setModel(model);
                return "system/role/form";
            }
        }
        
        if(role.getMenus() == null || role.getMenus().isEmpty()){
            super.addMessage(attrs, MessageStatus.WARN, "角色没有选择授权信息");
            return "redirect:/sys/system/role";
        }
        role.filterResource(); //去除空元素
        
        this.roleService.update(role);
        super.addMessage(attrs, "角色更新成功");
        return "redirect:/sys/system/role";
    }
    
    @GetMapping("/delete/{pid}")
    public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs){
        try{
            this.roleService.delete(id);
            super.addMessage(attrs, "角色删除成功");
        }
        catch(Exception e){
            super.addMessage(attrs, MessageStatus.ERROR, "角色删除失败，有用户正在使用当前角色");
        }
        return "redirect:/sys/system/role";
    }
    
    @RequestMapping
    public String list(Model model, Role role){
        model.addAttribute("page", this.roleService.findPage(role));
        return "system/role/list";
    }
    
}