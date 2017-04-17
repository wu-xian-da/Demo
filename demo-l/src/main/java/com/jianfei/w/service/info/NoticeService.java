package com.jianfei.w.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.info.NoticeDao;
import com.jianfei.w.entity.info.Notice;

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
	
}
