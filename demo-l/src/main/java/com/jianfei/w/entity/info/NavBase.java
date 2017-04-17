package com.jianfei.w.entity.info;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.w.base.annotation.FormQuery;
import com.jianfei.w.base.entity.BaseEntity;
import com.jianfei.w.entity.common.NavLevel;
import com.jianfei.w.entity.common.NavStatus;
import com.jianfei.w.entity.common.NavType;

/******
 * 栏目基础实体类
 * @author ATH
 *
 */
@Getter
@Setter
public class NavBase extends BaseEntity{

	private static final long serialVersionUID = 2620640114510574465L;

	@NotBlank(message="栏目名称不能为空")
    @Length(max=200, message="栏目名称长度不能超过200")
	@FormQuery
	private String navName;//名称

	@NotBlank(message="栏目类型不能为空")
    private NavType navType;//类型(下辖二级菜单;无二级菜单;URL外链)

    private NavLevel navLevel;//层级(一级栏目;二级栏目;)

    private String navIcon;//图标路径

    private Integer navOrderNum;//排序权重

    private NavStatus navStatus = NavStatus.ZS;//状态(展示;取消展示;)

    private Long parentId;//父级栏目ID
    
    @FormQuery("parent.id")
    private NavBase parent = null;//父级栏目

    private Date createTime;

    private Date updateTime;
    
    //取消展示、恢复展示
    private List<NavBase> navBases = null;
    
    public void filterNavBases(){
    	if(null == this.navBases){
    		return;
    	}
        
    	Iterator<NavBase> iter = this.navBases.iterator();
        while(iter.hasNext()){
        	NavBase r = iter.next();  
            if(null == r || null == r.getId()){
                iter.remove();
            }
        }  
    }

	//下辖二级菜单内容
    private NavSecondMenu navSecondMenu = null;
    
    //无二级菜单内容
    private NavContent navContent = null;
    
    //URL外链内容
    private NavUrl navUrl = null;
    
}