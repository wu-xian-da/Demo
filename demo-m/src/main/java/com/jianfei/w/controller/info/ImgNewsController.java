package com.jianfei.w.controller.info;

import java.util.Date;

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

import com.jianfei.w.controller.base.BaseController;
import com.jianfei.w.entity.common.InfoStatus;
import com.jianfei.w.entity.info.ImgNews;
import com.jianfei.w.service.info.ImgNewsService;

/******
 * 图片新闻
 * @author ATH
 *
 */
@Controller
@RequestMapping(value = "/sys/info/news")
public class ImgNewsController extends BaseController{

	@Autowired
	private ImgNewsService imgNewsService;
	
	private void setBases(Model model) {
		model.addAttribute("infoStatuss", InfoStatus.values());
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		return "info/news/form";
	}

	@PostMapping("/create")
	public String create(@Valid ImgNews imgNew, BindingResult result,
			Model model, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "info/news/form";
		}
		
		int r = imgNewsService.save(imgNew);
		if(r > 0){
			super.addMessage(attrs, "保存图片新闻成功");
		}else{
			super.addMessage(attrs, "保存图片新闻失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}

	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id, Model model) {
		model.addAttribute("news", imgNewsService.get(id));
		
		return "info/news/form";
	}

	@PostMapping("/update/{pid}")
	public String update(@Valid ImgNews imgNew, BindingResult result,
			Model model, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			
			return "info/news/form";
		}
		
		int r = imgNewsService.update(imgNew);
		if(r > 0){
			super.addMessage(attrs, "修改图片新闻成功");
		}else{
			super.addMessage(attrs, "修改图片新闻失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}

	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs) {
		int r = imgNewsService.delete(id);
		if(r > 0){
			super.addMessage(attrs, "删除图片新闻成功");
		}else{
			super.addMessage(attrs, "删除图片新闻失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}
	
	//审核通过
	@GetMapping("/check/shtg")
	public String checkSHTG(ImgNews imgNews, RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		
		for (ImgNews news : imgNews.getImgNewss()) {
			news.setStatus(InfoStatus.SHTG);
			news.setCheckTime(new Date());
		}
		
		int result = imgNewsService.updateImgNewStatusBatch(imgNews.getImgNewss());
		if(result > 0){
			super.addMessage(attrs, "批量审核通过成功");
		}else{
			super.addMessage(attrs, "批量审核通过失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}
	
	//下刊
	@GetMapping("/check/yxk")
	public String checkYXK(ImgNews imgNews, RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		
		for (ImgNews news : imgNews.getImgNewss()) {
			news.setStatus(InfoStatus.YXK);
		}
		
		int result = imgNewsService.updateImgNewStatusBatch(imgNews.getImgNewss());
		if(result > 0){
			super.addMessage(attrs, "批量下刊成功");
		}else{
			super.addMessage(attrs, "批量下刊失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}
	
	//上刊
	@GetMapping("/check/ysk")
	public String checkYSK(ImgNews imgNews, RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		
		for (ImgNews news : imgNews.getImgNewss()) {
			news.setStatus(InfoStatus.YSK);
		}
		
		int result = imgNewsService.updateImgNewStatusBatch(imgNews.getImgNewss());
		if(result > 0){
			super.addMessage(attrs, "批量上刊成功");
		}else{
			super.addMessage(attrs, "批量上刊失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}
	
	//恢复上刊
	@GetMapping("/check/hfsk")
	public String checkHFSK(ImgNews imgNews, RedirectAttributes attrs){
		imgNews.fileterImgNewss();
		
		for (ImgNews news : imgNews.getImgNewss()) {
			news.setStatus(InfoStatus.YSK);
		}
		
		int result = imgNewsService.updateImgNewStatusBatch(imgNews.getImgNewss());
		if(result > 0){
			super.addMessage(attrs, "批量恢复上刊成功");
		}else{
			super.addMessage(attrs, "批量恢复上刊失败，请重试！");
		}
		
		return "redirect:/sys/info/news";
	}

	@RequestMapping
	public String list(Model model, ImgNews imgNew) {
		setBases(model);
		model.addAttribute("page", imgNewsService.findPage(imgNew));
		
		return "info/news/list";
	}
}
