package com.jianfei.w.base.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jianfei.w.common.utils.HttpUtils;
import com.jianfei.w.common.utils.SessionUtils;
import com.jianfei.w.entity.system.LogAccess;
import com.jianfei.w.entity.system.User;
import com.jianfei.w.service.system.LogAccessService;

/**
 * 访问日志拦截
* @author ZhangBo   
* @date 2017年4月14日 下午2:18:11
 */
public class LogAccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogAccessService logAccessService;
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        
        User user = SessionUtils.getUser();
        try{
            this.logAccessService.save(new LogAccess(
                    user, 
                    new Date(), 
                    request.getRequestURI(), 
                    request.getParameterMap().toString(),
                    HttpUtils.getRemoteAddr(request)));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
