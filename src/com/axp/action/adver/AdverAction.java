package com.axp.action.adver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;

/**
 * 基础商品的操作；
 *
 * @author Administrator
 */
@Controller
@RequestMapping("adver")
public class AdverAction extends BaseAction {
	
	@NeedPermission(permissionName = "广告素材管理", classifyName = "广告管理")
	@IsItem(firstItemName = "广告管理", secondItemName = "广告素材管理")
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		adverService.getPageList(request, response);
		return "adver/list";
	}
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response){
		adverService.add(request, response);
		return "adver/add";
	}
	
	@RequestMapping("save")
	public String save(Integer id, Integer type, String name, String description, Integer website_id, HttpServletRequest request, HttpServletResponse response){
		adverService.save(id, type, name, description, website_id, request, response);
		return "redirect:list";
	}
	
	@RequestMapping("del")
	public String del(String ids, HttpServletRequest request, HttpServletResponse response){
		adverService.del(ids);
		return "redirect:list";
	}
	
	@RequestMapping("addAdverImg")
	public String addAdverImg(Integer id, HttpServletRequest request, HttpServletResponse response){
		adverService.addAdverImg(id, request);
		return "adver/adverimgs";
	}
	
	@RequestMapping("saveAdverimgs")
	public String saveAdverImg(Integer id,Integer playtotal,String adver_imageurls,
			String adver_imageurls_small, String adver_imgurl_size, 
			HttpServletRequest request, HttpServletResponse response){
		adverService.saveAdverImg(id, playtotal, adver_imageurls, adver_imageurls_small, adver_imgurl_size, request);
		return "redirect:list";
	}
	
	@NeedPermission(permissionName = "广告素材审核", classifyName = "广告管理")
	@IsItem(firstItemName = "广告管理", secondItemName = "广告素材审核")
	@RequestMapping("checkAdverList")
	public String checkAdverList(HttpServletRequest request){
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
		adverService.checkAdverList(request,adminUserId);
		return "adver/checkList";
	}
	
	@RequestMapping("checkAdver")
	public String checkAdver(Integer id,HttpServletRequest request){
		adverService.checkAdver(id,request);
		return "adver/check";
	}
	
	@RequestMapping("saveCheck")
	public String saveCheck(Integer id, Integer adver_status, String checkstr,HttpServletRequest request){
		adverService.saveCheck(id, adver_status, checkstr, request);
		return "redirect:checkAdverList";
	}
	
}



