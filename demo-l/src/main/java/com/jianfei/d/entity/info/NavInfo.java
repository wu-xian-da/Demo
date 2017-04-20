package com.jianfei.d.entity.info;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.common.InfoType;

/******
 * 栏目下信息实体类
 * @author ATH
 *
 */
@Getter
@Setter
public class NavInfo extends BaseEntity{
	
	private static final long serialVersionUID = -7790186226216712582L;

	@NotBlank(message="信息名称不能为空")
    @Length(max=500, message="信息名称长度不能超过500")
	@FormQuery
	private String title;//标题

	@NotBlank(message="信息类型不能为空")
	@FormQuery
    private InfoType type;//类型(交通类;文章类等)

    @FormQuery
    private InfoStatus status = InfoStatus.DSH;//状态(待审核;审核通过;已上刊;已下刊)

    private Long navId;//所属栏目ID
    @FormQuery("navBase.id")
    private NavBase navBase;//所属栏目

    private Date checkTime;//发布时间(审核时间)

    private InfoPushStatus pushStatus = InfoPushStatus.WTS;//推送状态(已推送;未推送)
    
    private String content;

    private Date createTime;

    private Date updateTime;
    
    //上刊、下刊、审核通过等
    private List<NavInfo> infos = null;
    
    @SuppressWarnings("null")
	public void fileterNavInfos(){
    	if(null == this.infos){
    		return;
    	}
    	
    	Iterator<NavInfo> iter =  this.infos.iterator();
    	while(iter.hasNext()){
    		NavInfo r = iter.next();
    		if(null == r && null == r.getId()){
    			iter.remove();
    		}
    	}
    }
    
    
}