package com.axp.action.cashShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;

@Controller
@RequestMapping("cashShop/secKillTimes")
public class SecKillTimesAction extends BaseAction{

	@NeedPermission(permissionName = "秒杀时段管理列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "秒杀时段管理")
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		secKillTimesService.list(request);
		return "cashShop/secKillTimes/list";
	}
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response){
		secKillTimesService.add(request);
		return "cashShop/secKillTimes/add";
	}
	
	@RequestMapping("edit")
	public String edit(Integer id, HttpServletRequest request, HttpServletResponse response){
		secKillTimesService.edit(id, request);
		return "cashShop/secKillTimes/edit";
	}
	
	@RequestMapping("save")
	public String save(HttpServletRequest request, HttpServletResponse response){
		secKillTimesService.save(request);
		return "redirect:list";
	}
	
	@RequestMapping("update")
	public String update(Integer id, String endTime, String startTime, HttpServletRequest request, HttpServletResponse response){
		secKillTimesService.update(id, endTime, startTime, request);
		return "redirect:list";
	}
	
	@RequestMapping("delete")
	public String delete(Integer id,HttpServletRequest request, HttpServletResponse response){
		secKillTimesService.delete(id, request);
		return "redirect:list";
	}
	

	
}
