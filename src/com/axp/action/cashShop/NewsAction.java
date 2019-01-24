package com.axp.action.cashShop;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.cashShop.NewsService;

@Controller
@RequestMapping("/cashShop/press")
public class NewsAction {
	@Resource
	NewsService newsService;
	
	@NeedPermission(permissionName = "新闻管理", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "新闻管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		newsService.getList(request, response);
		return "cashShop/press/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Integer id){
		newsService.add(request, id);
		return "cashShop/press/add";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,Integer id,String title,String url,Timestamp createTime,Integer zoneId,Integer adminuserId){
		newsService.save(request, id, title, url, createTime, zoneId, adminuserId);
		return "redirect:list";
	}
	
	@RequestMapping("/del")
	public String del(HttpServletRequest request,Integer id){
		newsService.del(request, id);
		return "redirect:list";
	}
}
