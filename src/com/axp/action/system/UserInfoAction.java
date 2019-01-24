package com.axp.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;

@Controller
@RequestMapping("/system/users")
public class UserInfoAction extends BaseAction{
	@NeedPermission(permissionName="粉丝管理",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="粉丝管理")
	@RequestMapping("/list")
	public String getUserInfo(HttpServletRequest request,HttpServletResponse response,String mixM,String maxM){
		userInfoService.getUserInfo(request, response, mixM, maxM);
		return "baseinfo/userInfolist";
	}
	
	@RequestMapping("/del")
	public String del(String ids, HttpServletRequest request, HttpServletResponse response){
		userInfoService.del(ids);
		return "redirect:list";
	}
}
