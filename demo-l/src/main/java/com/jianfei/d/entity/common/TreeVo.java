/**
  *project demo-l
  *@author changchun.wu
  *2017年4月24日上午11:42:46
  */
package com.jianfei.d.entity.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jianfei.d.entity.common.TreeVo;

@Setter
@Getter
public class TreeVo {
	 public static final SimplePropertyPreFilter parentFilter = new SimplePropertyPreFilter(TreeVo.class, "value", "label");
	    
	 public static final SimplePropertyPreFilter childFilter = new SimplePropertyPreFilter(TreeVo.class, "value", "label", "level");

	 private String label;
	 
	 private Long value;
	 
	 private int level;
	 
	 private List<TreeVo> childs;
}
