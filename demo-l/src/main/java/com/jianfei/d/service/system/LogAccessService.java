package com.jianfei.d.service.system;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.LogAccessDao;
import com.jianfei.d.entity.system.LogAccess;

@Service
public class LogAccessService extends CrudService<LogAccessDao, LogAccess>{

}
