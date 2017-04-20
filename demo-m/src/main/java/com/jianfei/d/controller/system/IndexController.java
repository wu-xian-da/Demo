package com.jianfei.d.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jianfei.d.controller.base.BaseController;

/**
 * 模块首页
* @author ZhangBo   
* @date 2017年4月14日 上午11:17:46
 */
@Controller("SystemIndexController")
public class IndexController extends BaseController{
    
    @GetMapping("/sys/system")
    public String index(){
        return super.getBaseIndex("/sys/system");
    }
}
