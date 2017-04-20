package com.jianfei.d.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记为Form查询字段类型
* @author ZhangBo   
* @date 2017年4月6日 下午3:58:56
 */
@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME) 
public @interface FormQuery {

    String[] value() default {};
}
