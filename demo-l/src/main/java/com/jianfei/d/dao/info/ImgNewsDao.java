package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.ImgNews;

public interface ImgNewsDao extends CrudDao<ImgNews>{

	public int updateImgNewStatusBatch(List<ImgNews> imgNewss);
	
	public int updateImgNewPushStatusBatch(List<ImgNews> imgNewss);
	
	//web
	public List<ImgNews> getListByStatus (InfoStatus status);
	
}
