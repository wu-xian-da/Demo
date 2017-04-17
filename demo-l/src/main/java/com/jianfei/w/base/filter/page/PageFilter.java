package com.jianfei.w.base.filter.page;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.jianfei.w.entity.common.PageParam;

/**
 * 分页过滤器
 * 拦截浏览器分页参数
* @author ZhangBo   
* @date 2017年3月28日 下午5:26:28
 */
@WebFilter(urlPatterns="/*",filterName="filter1")
public class PageFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        PageContext.setPageParam(this.getPageParam(request));
        try {
            filterChain.doFilter(request, response);
        } finally {
            PageContext.clearPageParam();
        }
    }
    
    
    /**
     * 获取页码/页面大小
     * @param request
     * @return
     */
    private PageParam getPageParam(HttpServletRequest request){
        String pn = request.getParameter(PageParam.PAGE_NO_NAME);
        String ps = request.getParameter(PageParam.PAGE_SIZE_NAME);
        
        PageParam param = new PageParam();
        try{
            param.setPn(Integer.valueOf(pn));
            param.setPs(Integer.valueOf(ps));
        }
        catch(Exception e){
        }
        return param;
    }
    

}
