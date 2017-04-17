package com.jianfei.w.base.interceptor;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.jianfei.w.entity.common.Page;

public class PageObjectWrapperFactory implements ObjectWrapperFactory {

    public boolean hasWrapperFor(Object object) {
        if(object instanceof Page){
            return true;
        }
        return false;
    }

    @SuppressWarnings({"rawtypes" })
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        if(object instanceof Page){
            return new PageObjectWrapper((Page)object);
        }
        throw new ReflectionException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }

}
