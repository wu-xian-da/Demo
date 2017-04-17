package com.jianfei.w.service.system;

import org.springframework.stereotype.Service;

import com.jianfei.w.base.service.CrudService;
import com.jianfei.w.dao.system.LogLoginDao;
import com.jianfei.w.entity.system.LogLogin;

@Service
public class LogLoginService extends CrudService<LogLoginDao, LogLogin>{

}
