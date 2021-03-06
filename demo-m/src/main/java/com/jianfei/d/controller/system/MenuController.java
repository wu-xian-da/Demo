package com.jianfei.d.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.common.vo.MessageStatus;
import com.jianfei.d.controller.base.BaseController;
import com.jianfei.w.entity.common.MenuType;
import com.jianfei.w.entity.common.Page;
import com.jianfei.w.entity.system.Menu;
import com.jianfei.w.service.system.MenuService;

/**
 * 系统菜单
* @author ZhangBo   
* @date 2017年4月5日 下午5:56:12
 */
@Controller
@RequestMapping("/sys/system/menu")
public class MenuController extends BaseController{
    
    @Autowired
    private MenuService menuService;
    
    private void setModel(Model model){
        model.addAttribute("types", MenuType.values());
        model.addAttribute("parents", this.menuService.getParent());
    }
    
    @GetMapping("/create")
    public String createForm(Model model){
        this.setModel(model);
        return "system/menu/form";
    }
    
    @PostMapping("/create")
    public String create(Menu menu, BindingResult result, Model model, RedirectAttributes attrs){
        this.menuService.save(menu);
        super.addMessage(attrs, "菜单保存成功");
        return "redirect:/sys/system/menu";
    }
    
    @GetMapping("/update/{pid}")
    public String updateForm(@PathVariable("pid") Long id, Model model){
        Menu menu = this.menuService.get(id);
        model.addAttribute("menu", menu);
        this.setModel(model);
        return "system/menu/form";
    }
    
    @PostMapping("/update/{pid}")
    public String update(Menu menu, BindingResult result, Model model, RedirectAttributes attrs){
        this.menuService.update(menu);
        super.addMessage(attrs, "菜单更新成功");
        return "redirect:/sys/system/menu";
    }
    
    @GetMapping("/delete/{pid}")
    public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs){
        try{
            this.menuService.delete(id);
            super.addMessage(attrs, "菜单删除成功");
        }
        catch(Exception e){
            super.addMessage(attrs, MessageStatus.ERROR, "当前菜单已绑定角色，请先解除绑定");
        }
        return "redirect:/sys/system/menu";
    }
    
    @RequestMapping
    public String list(Model model, Menu menu){
        Page<Menu> menus = this.menuService.findPage(menu);
        model.addAttribute("page", menus);
        return "system/menu/list";
    }
}