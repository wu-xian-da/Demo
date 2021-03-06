package com.jianfei.d.controller.info;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jianfei.d.controller.base.BaseController;
import com.jianfei.w.entity.common.InfoStatus;
import com.jianfei.w.entity.common.InfoType;
import com.jianfei.w.entity.info.NavBase;
import com.jianfei.w.entity.info.NavInfo;
import com.jianfei.w.service.info.NavBaseService;
import com.jianfei.w.service.info.NavInfoService;

/******
 * 栏目文章
 * @author ATH
 *
 */
@Controller
@RequestMapping(value = "/sys/info/navinfo")
public class NavInfoController extends BaseController{

	@Autowired
	private NavInfoService navInfoService;
	
	@Autowired
	private NavBaseService navBaseService;
	
	private void setBases(Model model) {
		//所属栏目
		List<NavBase> navList = navBaseService.getleafList();
		
		//信息类型
		model.addAttribute("infoTypes", InfoType.values());
		model.addAttribute("navList", navList);
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		setBases(model);
		
		return "info/navinfo/form";
	}

	@PostMapping("/create")
	public String create(@Valid NavInfo navInfo, BindingResult result,
			Model model, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			setBases(model);
			
			return "info/navinfo/form";
		}
		
		int r = navInfoService.save(navInfo);
		if(r > 0){
			super.addMessage(attrs, "保存栏目信息成功");
		}else{
			super.addMessage(attrs, "保存栏目信息失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}

	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id, Model model) {
        setBases(model);
        model.addAttribute("navInfo", navInfoService.get(id));
		
		return "info/navinfo/form";
	}

	@PostMapping("/update/{pid}")
	public String update(@Valid NavInfo navInfo, BindingResult result,
			Model model, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			setBases(model);
			
			return "info/navinfo/form";
		}
		
		int r = navInfoService.update(navInfo);
		if(r > 0){
			super.addMessage(attrs, "修改栏目信息成功");
		}else{
			super.addMessage(attrs, "修改栏目信息失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}

	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs) {
		int r = navInfoService.delete(id);
		if(r > 0){
			super.addMessage(attrs, "删除栏目信息成功");
		}else{
			super.addMessage(attrs, "删除栏目信息失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}
	
	//审核通过
	@GetMapping("/check/shtg")
	public String checkSHTG(NavInfo navInfo, RedirectAttributes attrs){
		navInfo.fileterNavInfos();
		
		for (NavInfo n : navInfo.getInfos()) {
			n.setStatus(InfoStatus.YSK);
			n.setCheckTime(new Date());
		}
		
		int result = navInfoService.updateNavInfoStatusBatch(navInfo.getInfos());
		if(result > 0){
			super.addMessage(attrs, "批量审核通过成功");
		}else{
			super.addMessage(attrs, "批量审核通过失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}
	
	//下刊
	@GetMapping("/check/yxk")
	public String checkYXK(NavInfo navInfo, RedirectAttributes attrs){
		navInfo.fileterNavInfos();
		
		for (NavInfo n : navInfo.getInfos()) {
			n.setStatus(InfoStatus.YXK);
		}
		
		int result = navInfoService.updateNavInfoStatusBatch(navInfo.getInfos());
		if(result > 0){
			super.addMessage(attrs, "批量下刊成功");
		}else{
			super.addMessage(attrs, "批量下刊失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}
	
	//上刊
	@GetMapping("/check/ysk")
	public String checkYSK(NavInfo navInfo, RedirectAttributes attrs){
		navInfo.fileterNavInfos();
		
		for (NavInfo n : navInfo.getInfos()) {
			n.setStatus(InfoStatus.YSK);
		}
		
		int result = navInfoService.updateNavInfoStatusBatch(navInfo.getInfos());
		if(result > 0){
			super.addMessage(attrs, "批量上刊成功");
		}else{
			super.addMessage(attrs, "批量上刊失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}
	
	//恢复上刊
	@GetMapping("/check/hfsk")
	public String checkHFSK(NavInfo navInfo, RedirectAttributes attrs){
		navInfo.fileterNavInfos();
		
		for (NavInfo n : navInfo.getInfos()) {
			n.setStatus(InfoStatus.YSK);
		}
		
		int result = navInfoService.updateNavInfoStatusBatch(navInfo.getInfos());
		if(result > 0){
			super.addMessage(attrs, "批量恢复上刊成功");
		}else{
			super.addMessage(attrs, "批量恢复上刊失败，请重试！");
		}
		
		return "redirect:/sys/info/navinfo";
	}

	@RequestMapping
	public String list(Model model, NavInfo navInfo) {
		setBases(model);
		model.addAttribute("infoStatuss", InfoStatus.values());
		model.addAttribute("page", navInfoService.findPage(navInfo));
		
		return "info/navinfo/list";
	}
}
