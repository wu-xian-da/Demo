package com.jianfei.w.base.interceptor;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import com.jianfei.w.entity.common.Page;

/**
 * 创建分页对象，Page 按照 集合处理
* @author ZhangBo   
* @date 2017年3月30日 下午4:55:35
 */
public class PageObjectFactory extends DefaultObjectFactory {

    private static final long serialVersionUID = -6257561829423391180L;

    public <T> boolean isCollection(Class<T> type) {
        if (type == Page.class) {
            return true;
        }
        return super.isCollection(type);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T create(Class<T> type) {
        if (type == Page.class) {
            return (T)new Page();
        }
        return super.create(type);
    }

}
