package com.axp.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;

@Controller
@RequestMapping("/system/feedback")
public class FeedbackAction extends BaseAction{

	@NeedPermission(permissionName="意见反馈",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="意见反馈")
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		feedbackService.list(request, response);
		return "baseinfo/feedback/list";
	}
}
