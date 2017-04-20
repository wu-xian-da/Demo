package com.jianfei.d.entity.common;

import lombok.Getter;
import lombok.Setter;




/**
 * Page 传参
* @author ZhangBo   
* @date 2015年7月28日 下午5:45:09
 */
@Getter
@Setter
public class PageParam {
	
    //页面大小参数
    public static final String PAGE_SIZE_NAME = "ps"; 
    //页码参数
    public static final String PAGE_NO_NAME = "pn";
    
    private int ps = 10; //默认值
    
    private int pn = 1; //默认值
    
    private long totalRecord; //总记录数
    
}
