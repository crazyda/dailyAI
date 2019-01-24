package com.axp.action.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.model.AppInformation;
import com.axp.service.appAdver.AppInformationService;



@Controller
@RequestMapping("/system/appVersion")
public class NewAppVersionAction {
	@Autowired
	private AppInformationService appInformationService;
	
	
	
	
	@NeedPermission(permissionName = "App版本管理", classifyName = "系统管理")
	@IsItem(firstItemName = "系统管理", secondItemName = "App版本管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		//查找appinform 列表
		appInformationService.findAppInformationList(request,response);
		
		
		
		return "baseinfo/app/show";
	}
	
	@RequestMapping("/add")
	public String edit(){
		return "baseinfo/app/edit";
	}
	
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,HttpServletResponse response,Integer id){
		
		appInformationService.findAppInformation(request, response, id);
		return "baseinfo/app/edit";
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object>  del(Integer id){
		
		return appInformationService.delAppInformation(id);
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object>  save(HttpServletRequest request,
										Integer id,
										String AScore,
										String ADownloads,
										String ASize,
										String ADirectDownload,
										String ADirectUrl,
										String AMarketDownload,
										String AMarketUrl,
										String AVersion,String IScore,String IDownloads,
										String ISize,
										String IMarketDownload,
										String IMarketUrl,
										String appVersion,
										String describe,
										String INewVersionContents,
										Integer appType){
		
		return appInformationService.save(request,id,AScore,ADownloads,ASize,ADirectDownload,ADirectUrl,AMarketDownload,AMarketUrl,
				AVersion,IScore,IDownloads,IMarketDownload,IMarketUrl,appVersion,describe,INewVersionContents,appType,ISize);
	}
	
	
}
