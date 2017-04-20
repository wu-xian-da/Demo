package com.jianfei.d.entity.system;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.entity.common.UserStatus;

/**
 * 系统用户
* @author ZhangBo   
* @date 2014年7月10日 上午10:44:48
 */
@Getter
@Setter
public class User extends BaseEntity{
    
    private static final long serialVersionUID = -2328940840754591148L;

    //真实姓名
    @NotBlank(message="真实姓名不能为空")
    @Length(max=20, message="真实姓名长度不能超过20")
    private String name;

    //登陆名
    @NotBlank(message="登陆名不能为空")
    @Length(max=20, message="登陆名长度不能超过20")
    @FormQuery
	private String loginName;
    
    @Length(max=20, message="联系电话长度不能超过20")
    private String tel;
	
    //密码
    @NotBlank(message="密码不能为空")
    @Length(min=6, max=32, message="密码长度(6-32)位")
	private String password;
    
	//角色
    @NotNull
    @FormQuery("role.id")
	private Role role;
    
    //部门
    @FormQuery("department.id")
    private Department department;
    
    //启用、禁用
    private UserStatus status = UserStatus.OPEN;
    
    //创建时间
    private Date createDate;
    
    //加密密码的盐
    private String salt;
    
    
    
    /** VO start  **/
    //重复密码
    private String rePassword;
    
    //启用、禁用、密码初始化使用
    private List<User> users;
    
    public void filterUsers(){
        if(users == null){
            return;
        }
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()){
            User r = iter.next();  
            if(r == null || r.getId() == null){
                iter.remove();
            }
        }  
    }

    //加密盐组合
    public String getCredentialsSalt() {
        return loginName + salt;
    }
    
    /** Vo end **/
}