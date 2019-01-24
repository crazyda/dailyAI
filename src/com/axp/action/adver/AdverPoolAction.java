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
@RequestMapping("adverPool")
public class AdverPoolAction extends BaseAction {
	
	@RequestMapping("playAdver")
	public String playAdver(Integer id, Integer adver_id, HttpServletRequest request){
		adverPoolService.playAdver(id, adver_id, request);
		return "adverPool/playAdver";
	}
	
	@RequestMapping("savePlayAdver")
	public String savePlayAdver(Integer id, Integer adver_id, Integer playtotal,
			Integer ishige, String starttime, Integer isplay,
			HttpServletRequest request, HttpServletResponse response){
		return adverPoolService.savePlayAdver(id, adver_id, playtotal, ishige, starttime, isplay, request, response);
	}
	
	@NeedPermission(permissionName = "广告池管理", classifyName = "广告管理")
	@IsItem(firstItemName = "广告管理", secondItemName = "广告池管理")
	@RequestMapping("poolList")
	public String poolList(HttpServletRequest request){
		adverPoolService.poolList(request);
		return "adverPool/poolList";
	}
	
	@NeedPermission(permissionName = "已过期广告池", classifyName = "广告管理")
	@IsItem(firstItemName = "广告管理", secondItemName = "已过期广告池")
	@RequestMapping("outtimeList")
	public String outtimeList(HttpServletRequest request){
		adverPoolService.outtimeList(request);
		return "adverPool/poolList";
	}
	
	@RequestMapping("delPool")
	public String delPool(Integer id, HttpServletRequest request){
		adverPoolService.delPool(id,request);
		return "redirect:poolList";
	}
	
	
}



