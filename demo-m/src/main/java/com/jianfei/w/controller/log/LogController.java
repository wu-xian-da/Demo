package com.jianfei.w.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jianfei.w.controller.base.BaseController;
import com.jianfei.w.entity.system.LogAccess;
import com.jianfei.w.entity.system.LogLogin;
import com.jianfei.w.service.system.LogAccessService;
import com.jianfei.w.service.system.LogLoginService;

/**
 * 登陆日志&访问日志
* @author ZhangBo   
* @date 2017年4月14日 上午11:17:46
 */
@Controller
@RequestMapping("/sys/log")
public class LogController extends BaseController{
    
    @Autowired
    private LogLoginService logLoginService;
    
    @Autowired
    private LogAccessService logAccessService;
    
    @GetMapping
    public String index(){
        return super.getBaseIndex("/sys/log");
    }
    
    @RequestMapping("/login")
    public String login(Model model, LogLogin log){
        model.addAttribute("page", this.logLoginService.findPage(log));
        return "log/login";
    }
    
    @RequestMapping("/access")
    public String access(Model model, LogAccess log){
        model.addAttribute("page", this.logAccessService.findPage(log));
        return "log/access";
    }
    
}
