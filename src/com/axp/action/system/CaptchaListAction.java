package com.axp.action.system;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.service.system.CaptchaService;


/*
 * 验证码信息列表展示
 * @author Administrator
 * */
@Controller
@RequestMapping("Captcha")
public class CaptchaListAction extends BaseAction{
	
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private CaptchaService captchaService;
	@Resource
	private AdminUserDAO adminUserDAO;
	@NeedPermission(permissionName="验证码",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="验证码")
	@RequestMapping("CaptchaList")
	public String getList(String phone,String sTM,String eTM,HttpServletRequest request){
		captchaService.getCaptchaList(null, phone, "", sTM, eTM, request);
		return "baseinfo/Captchalist"; 
		
	}
	
	
}
