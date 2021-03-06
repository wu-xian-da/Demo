package com.jianfei.d.controller.info;

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

import com.jianfei.d.controller.base.BaseController;
import com.jianfei.w.entity.common.InfoStatus;
import com.jianfei.w.entity.info.Notice;
import com.jianfei.w.service.info.NoticeService;

/******
 * 紧急公告
 * @author ATH
 *
 */
@Controller
@RequestMapping(value = "/sys/info/notice")
public class NoticeController extends BaseController{
	
	@Autowired
	private NoticeService noticeService;

	private void setBases(Model model) {
		model.addAttribute("infoStatuss", InfoStatus.values());
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		return "info/notice/form";
	}

	@PostMapping("/create")
	public String create(@Valid Notice notice, BindingResult result,
			Model model, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "info/notice/form";
		}
		
		int r = noticeService.save(notice);
		if(r > 0){
			super.addMessage(attrs, "保存紧急公告成功");
		}else{
			super.addMessage(attrs, "保存紧急公告失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}

	@GetMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") Long id, Model model) {
		model.addAttribute("notice", noticeService.get(id));
		
		return "info/notice/form";
	}

	@PostMapping("/update/{pid}")
	public String update(@Valid Notice notice, BindingResult result,
			Model model, RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "info/notice/form";
		}
		
		int r = noticeService.update(notice);
		if(r > 0){
			super.addMessage(attrs, "修改紧急公告成功");
		}else{
			super.addMessage(attrs, "修改紧急公告失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}

	@GetMapping("/delete/{pid}")
	public String delete(@PathVariable("pid") Long id, RedirectAttributes attrs) {
		int r = noticeService.delete(id);
		if(r > 0){
			super.addMessage(attrs, "删除紧急公告成功");
		}else{
			super.addMessage(attrs, "删除紧急公告失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}
	
	//审核通过
	@GetMapping("/check/shtg")
	public String checkSHTG(Notice notice, RedirectAttributes attrs){
		notice.fileterNotices();
		
		for (Notice n : notice.getNotices()) {
			n.setStatus(InfoStatus.YSK);
			n.setCheckTime(new Date());
		}
		
		int result = noticeService.updateNoticeStatusBatch(notice.getNotices());
		if(result > 0){
			super.addMessage(attrs, "批量审核通过成功");
		}else{
			super.addMessage(attrs, "批量审核通过失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}
	
	//下刊
	@GetMapping("/check/yxk")
	public String checkYXK(Notice notice, RedirectAttributes attrs){
		notice.fileterNotices();
		
		for (Notice n : notice.getNotices()) {
			n.setStatus(InfoStatus.YXK);
		}
		
		int result = noticeService.updateNoticeStatusBatch(notice.getNotices());
		if(result > 0){
			super.addMessage(attrs, "批量下刊成功");
		}else{
			super.addMessage(attrs, "批量下刊失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}
	
	//上刊
	/*@GetMapping("/check/ysk")
	public String checkYSK(Notice notice, RedirectAttributes attrs){
		notice.fileterNotices();
		
		for (Notice n : notice.getNotices()) {
			n.setStatus(InfoStatus.YSK);
		}
		
		int result = noticeService.updateNoticeStatusBatch(notice.getNotices());
		if(result > 0){
			super.addMessage(attrs, "批量上刊成功");
		}else{
			super.addMessage(attrs, "批量上刊失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}*/
	
	//恢复上刊
	@GetMapping("/check/hfsk")
	public String checkHFSK(Notice notice, RedirectAttributes attrs){
		notice.fileterNotices();
		
		for (Notice n : notice.getNotices()) {
			n.setStatus(InfoStatus.YSK);
		}
		
		int result = noticeService.updateNoticeStatusBatch(notice.getNotices());
		if(result > 0){
			super.addMessage(attrs, "批量恢复上刊成功");
		}else{
			super.addMessage(attrs, "批量恢复上刊失败，请重试！");
		}
		
		return "redirect:/sys/info/notice";
	}

	@RequestMapping
	public String list(Model model, Notice notice) {
		setBases(model);
		model.addAttribute("page", noticeService.findPage(notice));
		
		return "info/notice/list";
	}
}
