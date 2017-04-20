package com.jianfei.d.base.filter.page;

import com.jianfei.d.entity.common.PageParam;


/**
 * Page 上下文 传递
* @author ZhangBo   
* @date 2017年3月31日 下午3:46:40
 */
public class PageContext {

    private static ThreadLocal<PageParam> PAGE_PARAM = new ThreadLocal<PageParam>();
    
    public static PageParam getPageParam() {
        return PAGE_PARAM.get();
    }

    public static void setPageParam(PageParam _pageParam) {
        if(_pageParam == null){
            return;
        }
        PAGE_PARAM.set(_pageParam);
    }

    public static void clearPageParam() {
        PAGE_PARAM.remove();
    }
    
}
