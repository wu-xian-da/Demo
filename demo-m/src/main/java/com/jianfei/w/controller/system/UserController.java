package com.jianfei.w.controller.system;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.w.common.config.Constants;
import com.jianfei.w.common.utils.PasswordHelper;
import com.jianfei.w.controller.base.BaseController;
import com.jianfei.w.entity.common.UserStatus;
import com.jianfei.w.entity.system.User;
import com.jianfei.w.service.system.DepartmentService;
import com.jianfei.w.service.system.RoleService;
import com.jianfei.w.service.system.UserService;

/**
 * 系统用户
* @author ZhangBo   
* @date 2017年4月6日 上午10:31:21
 */
@Controller
@RequestMapping(value = "/sys/system/user")
public class UserController extends BaseController{
    
    @Autowired
	private UserService userService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private PasswordHelper passwordHelper;
    
    @Autowired
    private RoleService roleService;
    
    private void setModel(Model model){
        model.addAttribute("roles", this.roleService.findAll());
        model.addAttribute("departments", this.departmentService.findAll());
    }

	@GetMapping("/create")
	public String createForm(Model model) {
	    setModel(model);
		return "system/user/form";
	}

	@PostMapping("/create")
	public String create(@Valid User user, BindingResult result, Model model, RedirectAttributes attrs) {
	    if(result.hasErrors()){
	        setModel(model);
            return "system/user/form";
        }
	    
	    //判断登陆名是否重复
	    User temp = this.userService.findByLoginName(user.getLoginName());
        if(temp != null){
            super.addError(result, "user", "loginName", "登陆名已存在，请更换");
            setModel(model);
            return "system/user/form";
        }
	    
        passwordHelper.encryptPassword(user);//加密密码
        user.setCreateDate(new Date());
		userService.save(user);
		super.addMessage(attrs, "保存用户成功");
		return "redirect:/sys/system/user";
	}

	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id, Model model) {
		model.addAttribute("user", userService.get(id));
		setModel(model);
		return "system/user/form";
	}
	
	@PostMapping("/update/{pid}")
    public String update(@Valid User user, BindingResult result, Model model, RedirectAttributes attrs) {
        if(result.hasErrors()){
            boolean tag = false;
            for(FieldError f : result.getFieldErrors()){
                if(f.getField().equals("password") || f.getField().equals("salt")){
                    continue;
                }
                tag = true;
            }
            if(tag){
                setModel(model);
                return "system/user/form";
            }
        }
        
        userService.save(user);
        super.addMessage(attrs, "更新用户成功");
        return "redirect:/sys/system/user";
    }

	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs) {
	    User user = userService.get(id);
	    if(user != null && user.getLoginName().equals(Constants.ADMIN)){
	        super.addMessage(attrs, "管理员用户不可以删除");
	        return "redirect:/sys/system/user";
	    }
	    
	    this.userService.delete(id);
        super.addMessage(attrs, "删除用户成功");
		return "redirect:/sys/system/user";
	}
	
	//初始化密码
	@GetMapping("/init")
    public String initPassword(User user, RedirectAttributes attrs) {
	    user.filterUsers();
        for(User u : user.getUsers()){
            u.setPassword(Constants.INIT_PASSWORD);
            passwordHelper.encryptPassword(u);//加密密码
        }
        this.userService.initPasswordBatch(user.getUsers());
        super.addMessage(attrs, user.getLoginName() + "密码初始化成功, 默认为：" + Constants.INIT_PASSWORD);
        return "redirect:/sys/system/user";
    }
	
	//启用
	@GetMapping("/open")
	public String open(User user, RedirectAttributes attrs) {
	    user.filterUsers();
	    for(User u : user.getUsers()){
	        u.setStatus(UserStatus.OPEN);
	    }
	    
	    this.userService.updateUserStatusBatch(user.getUsers());
	    super.addMessage(attrs, "启用成功");
	    return "redirect:/sys/system/user";
	}
	
	//禁用
    @GetMapping("/close")
    public String close(User user, RedirectAttributes attrs) {
        user.filterUsers();
        for(User u : user.getUsers()){
            u.setStatus(UserStatus.CLOSE);
        }
        
        this.userService.updateUserStatusBatch(user.getUsers());
        super.addMessage(attrs, "禁用成功");
        return "redirect:/sys/system/user";
    }
	
	@RequestMapping
    public String list(Model model, User user) {
	    model.addAttribute("page", this.userService.findPage(user));
	    model.addAttribute("admin", Constants.ADMIN);
	    this.setModel(model);
        return "system/user/list";
    }
}