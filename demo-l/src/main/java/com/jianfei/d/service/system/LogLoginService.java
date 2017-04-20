package com.jianfei.d.service.system;

import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.dao.system.LogLoginDao;
import com.jianfei.d.entity.system.LogLogin;

@Service
public class LogLoginService extends CrudService<LogLoginDao, LogLogin>{

}
