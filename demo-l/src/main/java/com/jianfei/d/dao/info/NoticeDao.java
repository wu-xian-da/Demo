package com.jianfei.d.dao.info;

import java.util.List;

import com.jianfei.d.base.dao.CrudDao;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.Notice;

public interface NoticeDao extends CrudDao<Notice>{

	public int updateNoticeStatusBatch(List<Notice> notices);
	
	public int updateNoticePushStatusBatch(List<Notice> notices);
	
	public List<Notice> getTopNByStatus(InfoStatus status);
	
	public List<Notice> getListByStatus(InfoStatus status);
	
}
