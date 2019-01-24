package com.axp.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.MessageCenter;
import com.axp.service.system.MessageService;

@Controller
@RequestMapping("messageCenter")
public class MessageAction extends BaseAction {
	
	@Autowired
	MessageService messageService;

	@IsItem(firstItemName = "业务管理", secondItemName = "消息中心")
	@NeedPermission(permissionName = "消息中心列表", classifyName = "业务管理")
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		messageService.list(request,response);
		return "message/list";
	}
	
	@RequestMapping("add")
    public String add(Integer id, HttpServletRequest request){
		messageService.add(id,request);
		return "message/add";
	}
	
	@RequestMapping("messageAjax")
	@ResponseBody
    public MessageCenter messageAjax(Integer id, Integer type, HttpServletRequest request){
		return messageService.getMessage(id, type);
	}
	
	@RequestMapping("save")
	public String save(Integer id, String author, String title,String remark, String cover,
			Integer type,Integer isTimer, String fixTime,String content, HttpServletRequest request) {
		messageService.save(id, author, title, remark, cover, type, isTimer, fixTime,content, request);
		return "redirect:list";
	}
	
	@RequestMapping("del")
    public String del(String ids, HttpServletRequest request){
		messageService.del(ids,request);
		return "redirect:list";
	}
	
	
	
}
