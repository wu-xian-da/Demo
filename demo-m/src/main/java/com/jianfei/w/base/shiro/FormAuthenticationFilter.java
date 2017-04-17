package com.jianfei.w.base.shiro;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jianfei.w.common.config.Constants;
import com.jianfei.w.common.utils.HttpUtils;
import com.jianfei.w.entity.common.LoginStatus;
import com.jianfei.w.entity.system.LogLogin;
import com.jianfei.w.service.system.LogLoginService;

/**
 * 表单验证（包含验证码）过滤类
* @author ZhangBo   
* @date 2015年6月21日 下午9:23:28
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    
    private static final String UA = "User-Agent";
    
    @Autowired
    private LogLoginService logLoginService;
    

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
    	ae.printStackTrace();
    	request.setAttribute(Constants.MESSAGE, "登陆出错");
        super.setFailureAttribute(request, ae);
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
            ServletResponse response) throws Exception {
        
        this.saveLogLogin(request, LoginStatus.Success);
        return super.onLoginSuccess(token, subject, request, response);
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
            ServletResponse response) {
        
        this.saveLogLogin(request, LoginStatus.Fail);
        return super.onLoginFailure(token, e, request, response);
    }
    
    private void saveLogLogin(ServletRequest request, LoginStatus status){
        ShiroHttpServletRequest httpRequest = (ShiroHttpServletRequest)request;
        try{
            this.logLoginService.save(new LogLogin(
                    getUsername(request), 
                    request.getParameterMap().toString(), 
                    new Date(), 
                    httpRequest.getHeader(UA), 
                    HttpUtils.getRemoteAddr(httpRequest), 
                    status));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}