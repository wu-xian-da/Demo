package com.jianfei.w.base.filter.xss;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 特殊字符过滤
* @author ZhangBo   
* @date 2015年5月26日 下午6:14:54
 */
@WebFilter(urlPatterns="/*",filterName="filter0")
public class XssFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        filterChain.doFilter(new XssRequestWrapper(request), response);
    }

}
