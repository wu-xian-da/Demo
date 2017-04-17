package com.jianfei.w.dao.system;

import java.util.List;

import com.jianfei.w.base.dao.CrudDao;
import com.jianfei.w.entity.system.User;

public interface UserDao extends CrudDao<User>{

    public int initPasswordBatch(List<User> users);
    
    public int updateUserStatusBatch(List<User> users);
    
    public User findByLoginName(String loginName);
    
}
