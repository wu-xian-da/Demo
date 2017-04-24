package com.jianfei.d.controller.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.jianfei.d.common.config.Constants;
import com.jianfei.d.common.utils.SessionUtils;
import com.jianfei.d.common.vo.Message;
import com.jianfei.d.common.vo.MessageStatus;
import com.jianfei.d.entity.common.TreeVo;
import com.jianfei.w.entity.system.Menu;

public class BaseController {
    
    protected Logger log = LoggerFactory.getLogger(getClass());
    
    /**
     * 添加Flash消息
     * @param message
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        if(model instanceof RedirectAttributes){
            RedirectAttributes redirectAttributes = (RedirectAttributes)model;
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, new Message(sb.toString(), MessageStatus.SUC));
        }
        else{
            model.addAttribute(Constants.MESSAGE, new Message(sb.toString(), MessageStatus.SUC));
        }
    }
    
    /**
     * 添加Flash消息
     * @param message
     */
    protected void addMessage(Model model, MessageStatus status, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        if(model instanceof RedirectAttributes){
            RedirectAttributes redirectAttributes = (RedirectAttributes)model;
            redirectAttributes.addFlashAttribute(Constants.MESSAGE, new Message(sb.toString(), status));
        }
        else{
            model.addAttribute(Constants.MESSAGE, new Message(sb.toString(), status));
        }
    }
    
    /**
     * 添加错误
     * @param result
     * @param objectName
     * @param defaultMessage
     */
    protected void addError(BindingResult result, String objectName, String field, String defaultMessage){
        FieldError error = new FieldError(objectName, field, defaultMessage);
        result.addError(error);
    }
    
    @SuppressWarnings("unchecked")
    protected String getBaseIndex(String baseUrl){
        List<Menu> userMenus = ((List<Menu>)SessionUtils.getSessionAttribute(Constants.USER_MENUS));
        for(Menu m : userMenus){
            if(m.getHref().equals(baseUrl)){
                List<Menu> childs = m.getChilds();
                if(childs.size() > 0){
                    return "redirect:" + childs.get(0).getHref();
                }
            }
        }
        return null;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(2048);
        //日期转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true)); 
    }
    
    protected String buildTree(List<TreeVo> tree){
        if(tree == null || tree.isEmpty()){
            return null;
        }
        List<String> list = new ArrayList<String>();
        for(TreeVo parent : tree){
            int level = 1;
            list.add(JSONObject.toJSONString(parent, TreeVo.parentFilter));
            if(parent.getChilds() != null && !parent.getChilds().isEmpty()){
                this.buildChild(parent.getChilds(), list, level);
            }
        }
        return list.toString();
    }
    
    private void buildChild(List<TreeVo> childs, List<String> list, int level){
        for(TreeVo child : childs){
            child.setLevel(++level);
            list.add(JSONObject.toJSONString(child, TreeVo.childFilter));
            if(child.getChilds() != null && !child.getChilds().isEmpty()){
                this.buildChild(child.getChilds(), list, level);
            }
        }
    }
    
}

