package com.jianfei.d;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jianfei.d.base.BaseController;
import com.jianfei.d.entity.info.NavBase;
import com.jianfei.d.service.info.NavBaseService;

/******
 * 栏目导航
 * @author ATH
 *
 */
@Controller
@RequestMapping("/nav")
public class NavController extends BaseController{
	
	@Autowired
	private NavBaseService navBaseService;
	
	@RequestMapping("/toList/{pid}")
	public ModelAndView toList(@PathVariable("pid") Long id, Model model){
		NavBase nav = navBaseService.getShowFirstNavById(id);
		List<NavBase> secNavList = navBaseService.getShowSecNavListByParentId(id);
		
		model.addAttribute("nav", nav);
		model.addAttribute("secNavList", secNavList);
		return new ModelAndView("nav/list.jsp");
	}
	
}
