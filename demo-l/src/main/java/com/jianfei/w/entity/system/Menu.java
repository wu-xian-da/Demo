package com.jianfei.w.entity.system;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jianfei.w.base.entity.BaseEntity;
import com.jianfei.w.entity.common.MenuType;

/**
 * 系统菜单
* @author ZhangBo   
* @date 2014年10月23日 上午11:18:49
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper=true,of={Menu.ID2})
public class Menu extends BaseEntity{
    
    static final String ID2 = "id";

    private static final long serialVersionUID = 6965086422964894144L;

    @NotBlank(message="名称不能为空")
    @Length(max=50, message="长度不能超过50")
    private String name; //资源名称 
    
    @Length(max=200, message="长度不能超过200")
    private String href; //资源路径
    
    @Length(max=80, message="长度不能超过80")
    private String permission; //权限字符串
    
    @NotNull(message="类型不能为空")
    private MenuType type;
    
    @NotNull(message="排序号不能为空")
    @Digits(integer=4,fraction=0, message="超出4位数字最大限制")
    private Integer sort = 1; //排序号
    
    private String icon; //图标
    
    private Menu parent;  //父编号
    
    private List<Menu> childs;

}
