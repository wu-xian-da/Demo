package com.jianfei.w.base.interceptor;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

import com.jianfei.w.base.filter.page.PageContext;
import com.jianfei.w.entity.common.Page;
import com.jianfei.w.entity.common.PageParam;

/**
 * 对象封装把Mybatis List数据 封装为 Page对象
* @author ZhangBo   
* @date 2017年3月30日 下午4:57:31
 */
@SuppressWarnings("rawtypes")
public class PageObjectWrapper implements ObjectWrapper {
    
    private Page page;
    
    public PageObjectWrapper(Page page){
        this.page = page;
    }
    
    @SuppressWarnings("unchecked")
    public <E> void addAll(List<E> element) {
        PageParam param = PageContext.getPageParam();
        this.page.setData(element);
        this.page.setPageNo(param.getPn());
        this.page.setPageSize(param.getPs());
        this.page.setTotalRecord(param.getTotalRecord());
    }

    public Object get(PropertyTokenizer prop) {
        return null;
    }

    public void set(PropertyTokenizer prop, Object value) {
        // TODO Auto-generated method stub
    }

    public String findProperty(String name, boolean useCamelCaseMapping) {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] getGetterNames() {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] getSetterNames() {
        // TODO Auto-generated method stub
        return null;
    }

    public Class<?> getSetterType(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public Class<?> getGetterType(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean hasSetter(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean hasGetter(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isCollection() {
        // TODO Auto-generated method stub
        return false;
    }

    public void add(Object element) {
        // TODO Auto-generated method stub

    }

}
