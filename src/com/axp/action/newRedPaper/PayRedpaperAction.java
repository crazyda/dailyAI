package com.axp.action.newRedPaper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.newRedPaper.AdminuserRedpaperReceiveService;
import com.axp.service.newRedPaper.PayRedpaperService;

@Controller
@RequestMapping("/newRedPaper/payCondition")
public class PayRedpaperAction extends BaseAction{
	@Resource
	PayRedpaperService payRedpaperService;
	@Resource
	AdminuserRedpaperReceiveService adminuserRedpaperReceiveService;
	@IsItem(firstItemName = "红包管理", secondItemName = "红包派发情况")
	@NeedPermission(permissionName = "红包派发情况", classifyName = "红包管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		payRedpaperService.getlist(request, response);
		return "newRedPaper/payCondition/list";
	}
	
	@RequestMapping("/getRedpaperDetail")
	public String getRedpaperDetail(HttpServletRequest request,Integer redpId){
		adminuserRedpaperReceiveService.getRedpaperDetailById(request, redpId);
		return "newRedPaper/payCondition/detail";
	}
	@RequestMapping("/isPay")
	public String isPay(HttpServletRequest request,Integer redpaperId){
		payRedpaperService.isPay(request, redpaperId);
		return "redirect:list";
	}
}
