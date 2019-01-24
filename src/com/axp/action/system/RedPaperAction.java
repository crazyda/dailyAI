package com.axp.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.system.RedpaperService;

@Controller
@RequestMapping("/system/redpaper")
public class RedPaperAction extends BaseAction{

	@Autowired
	private RedpaperService redpaperService;
	
	@NeedPermission(permissionName="红包管理",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="红包管理")
	@RequestMapping(value="list")
	public String redpaperInfo(HttpServletRequest request,HttpServletResponse response){
		redpaperService.redpaperInfo(request,response);
		return "/baseinfo/redpaper";
	}
	
	@RequestMapping(value="getRedpaperDetail")
	public String getRedpaperDetail(HttpServletRequest request,@RequestParam(value="redpaperId",required=true) Integer redpaperId){
		redpaperService.getRedpaperDetail(request,redpaperId);
		return "/baseinfo/redpaper/redpaperDetail";
	}
}
