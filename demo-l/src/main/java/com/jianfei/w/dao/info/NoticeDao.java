package com.jianfei.w.dao.info;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.info.Notice;

public interface NoticeDao extends CrudDao<Notice>{

	public int updateNoticeStatusBatch(List<Notice> notices);
	
	public int updateNoticePushStatusBatch(List<Notice> notices);
	
}
