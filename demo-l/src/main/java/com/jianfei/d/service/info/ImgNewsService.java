package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.ImgNewsDao;
import com.jianfei.d.entity.info.ImgNews;

@Service
public class ImgNewsService extends CrudService<ImgNewsDao, ImgNews>{

	/******
	 * 批量修改状态
	 * @param imgNewss
	 * @return
	 */
	public int updateImgNewStatusBatch(List<ImgNews> imgNewss){
		return this.dao.updateImgNewStatusBatch(imgNewss);
	}
	
	/******
	 * 批量修改推送状态
	 * @param imgNewss
	 * @return
	 */
	public int updateImgNewPushStatusBatch(List<ImgNews> imgNewss){
		return this.dao.updateImgNewPushStatusBatch(imgNewss);
	}
	
}
