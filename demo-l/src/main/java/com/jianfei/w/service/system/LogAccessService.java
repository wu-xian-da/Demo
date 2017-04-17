package com.jianfei.w.service.system;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.system.LogAccessDao;
import com.jianfei.w.entity.system.LogAccess;

@Service
public class LogAccessService extends CrudService<LogAccessDao, LogAccess>{

}
