package com.axp.action.newRedPaper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.newRedPaper.ApplyService;

@Controller
@RequestMapping("/newRedPaper/apply")
public class ApplyAction extends BaseAction{

	@Autowired
	private ApplyService applyService;
	
	@IsItem(firstItemName = "红包管理", secondItemName = "申请红包额度")
	@NeedPermission(permissionName = "申请红包额度列表", classifyName = "红包管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		applyService.list(request);
		return "newRedPaper/apply/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request){
		return "newRedPaper/apply/add";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
		
		applyService.saveApplyLog(request);
		return "redirect:list";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request){
		return "newRedPaper/apply/edit";
	}
}
