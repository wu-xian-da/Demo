package com.jianfei.d.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.info.NoticeDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.Notice;

@Service
public class NoticeService extends CrudService<NoticeDao, Notice>{

	/******
	 * 批量修改状态
	 * @param notices
	 * @return
	 */
	public int updateNoticeStatusBatch(List<Notice> notices){
		return this.dao.updateNoticeStatusBatch(notices);
	}
	
	/******
	 * 批量修改推送状态
	 * @param notices
	 * @return
	 */
	public int updateNoticePushStatusBatch(List<Notice> notices){
		return this.dao.updateNoticePushStatusBatch(notices);
	}
	
	//web
	public List<Notice> getTopNByStatus(InfoStatus status){
		return this.dao.getTopNByStatus(status);
	}
	
	/**
	 * 根据状态获取数据
	 * @param status
	 * @return
	 */
	public List<Notice> getListByStatus(InfoStatus status){
		return this.dao.getListByStatus(status);
	}
}
