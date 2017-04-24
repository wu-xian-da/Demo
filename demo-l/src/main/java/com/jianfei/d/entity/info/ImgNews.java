package com.jianfei.d.entity.info;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoStatus;

/******
 * 图片新闻实体类
 * @author ATH
 *
 */
@Getter
@Setter
public class ImgNews extends BaseEntity {
	
	private static final long serialVersionUID = 1892982395026689338L;

	//@NotBlank(message="图片标题不能为空")
   // @Length(max=500, message="图片标题长度不能超过500")
	@FormQuery
    private String title;//图片标题

    private String imgPath;//图片路径

    @FormQuery
    private InfoStatus status;//状态(待审核;审核通过;已上刊;已下刊)

    private Date checkTime;//发布时间(审核时间)
    
    private Integer orderNum;//排序

    private InfoPushStatus pushStatus = InfoPushStatus.WTS;//推送状态(已推送;未推送)
    
    private String content;//内容

    private Date createTime;

    private Date updateTime;
    
    //上刊、下刊、审核通过等
    private List<ImgNews> imgNewss = null;
    
    @SuppressWarnings("null")
	public void fileterImgNewss(){
    	if(null == this.imgNewss){
    		return;
    	}
    	
    	Iterator<ImgNews> iter =  this.imgNewss.iterator();
    	while(iter.hasNext()){
    		ImgNews r = iter.next();
    		if(null == r && null == r.getId()){
    			iter.remove();
    		}
    	}
    }
    
    @FormQuery
    private String beginCheckTime;
    
    @FormQuery
    private String endCheckTime;
}