package com.axp.action.newRedPaper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.newRedPaper.CheckApplyService;

@Controller
@RequestMapping("/newRedPaper/checkApply")
public class CheckApplyAction extends BaseAction{

	@Autowired
	private CheckApplyService checkApplyService;
	
	@IsItem(firstItemName = "红包管理", secondItemName = "审核额度列表")
	@NeedPermission(permissionName = "审核额度列表", classifyName = "红包管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		checkApplyService.list(request);
		return "newRedPaper/checkApply/list";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request){
		checkApplyService.edit(request);
		return "newRedPaper/checkApply/edit";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
		checkApplyService.save(request);
		return "redirect:list";
	}
}
