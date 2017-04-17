package com.jianfei.w.dao.info;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.ImgNews;

public interface ImgNewsDao extends CrudDao<ImgNews>{

	public int updateImgNewStatusBatch(List<ImgNews> imgNewss);
	
	public int updateImgNewPushStatusBatch(List<ImgNews> imgNewss);
	
}
