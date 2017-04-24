package com.jianfei.d.controller.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jianfei.d.controller.base.BaseController;

/******
 * 信息模块
 * @author ATH
 *
 */
@Controller("InfoIndexController")
public class IndexController extends BaseController{
    
    @GetMapping("/sys/info")
    public String index(){
        return super.getBaseIndex("/sys/info");
    }
}
