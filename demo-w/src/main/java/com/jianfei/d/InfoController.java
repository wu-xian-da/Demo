package com.jianfei.d;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianfei.d.base.BaseController;
import com.jianfei.d.entity.common.InfoStatus;
import com.jianfei.d.entity.info.NavContent;
import com.jianfei.d.entity.info.NavInfo;
import com.jianfei.d.entity.info.NavSecondMenu;
import com.jianfei.d.service.info.NavContentService;
import com.jianfei.d.service.info.NavInfoService;
import com.jianfei.d.service.info.NavSecondMenuService;

/******
 * 栏目新闻
 * @author ATH
 *
 */
@Controller
@RequestMapping("/info")
public class InfoController extends BaseController{
	
	@Autowired
	private NavInfoService navInfoService;
	
	@Autowired
	private NavSecondMenuService navSecondMenuService;
	
	@Autowired
	private NavContentService navContentService;
	
	/******
	 * 二级菜单信息列表
	 * @param secNavId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toList/{secNavId}")
	public ModelAndView toList(@PathVariable("secNavId") Long secNavId, Model model){
		//获取渲染列表模板
		NavSecondMenu navSec = navSecondMenuService.getByNavId(secNavId);
		List<NavInfo> infoList = navInfoService.getListByNavIdAndStatus(secNavId, InfoStatus.YSK);
		
		model.addAttribute("navSec", navSec);
		model.addAttribute("infoList", infoList);
		
		String template = navSec.getMenuListTemplate().getFilePath();
		if(StringUtils.isNoneBlank(template)){
			return new ModelAndView(template);
		}else{
			return null;
		}
		//return new ModelAndView("info/list.jsp");
	}
	
	/******
	 * 二级菜单信息详情
	 * @param secNavId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toDetail/{navSecId}/{infoId}")
	public ModelAndView toDetail(@PathVariable("navSecId") Long navSecId, @PathVariable("infoId") Long infoId, Model model){
		//获取渲染内容模板
		NavContent navContent = navContentService.getByNavId(navSecId);
		NavInfo infoDetail = navInfoService.get(infoId);
		
		model.addAttribute("navContent", navContent);
		model.addAttribute("infoDetail", infoDetail);
		
		String template = navContent.getContentTemplate().getFilePath();
		if(StringUtils.isNoneBlank(template)){
			return new ModelAndView(template);
		}else{
			return null;
		}
		//return new ModelAndView("info/detail.jsp");
	}
	
	/******
	 * 无二级菜单的栏目下有多篇文章的处理
	 * @param navId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toContent/{navId}")
	public ModelAndView toContent(@PathVariable("navId") Long navId, Model model){
		//获取渲染内容模板
		NavContent navContent = navContentService.getByNavId(navId);
		List<NavInfo> infoList = navInfoService.getListByNavIdAndStatus(navId, InfoStatus.YSK);
		
		model.addAttribute("navContent", navContent);
		if(infoList.size() > 1){
			model.addAttribute("infoList", infoList);
		}else if(infoList.size() == 1){
			model.addAttribute("infoDetail", infoList.get(0));
		}
		
		String template = navContent.getContentTemplate().getFilePath();
		if(StringUtils.isNoneBlank(template)){
			return new ModelAndView(template);
		}else{
			return null;
		}
		//return new ModelAndView("info/detail.jsp");
	}
}
