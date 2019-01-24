package com.axp.action.wechat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;

@Controller
@RequestMapping("/wechat/wechat")
public class WechatPubAction {

	@NeedPermission(permissionName = "公众号/小程序权限", classifyName = "公众号/小程序管理")
	@IsItem(firstItemName = "公众号/小程序管理", secondItemName = "公众号/小程序权限")
	@RequestMapping("/Authority")
	public String Authority(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("uri", "http://seller.aixiaoping.com/Wechat/Index/power");
		return "weChat/show";
	}
	
	
	@NeedPermission(permissionName="公众号/小程序商城列表" ,classifyName="公众号/小程序管理")
	@IsItem(firstItemName="公众号/小程序管理",secondItemName="公众号/小程序商城列表")
	@RequestMapping("/wechatList")
	public String wechatList(HttpServletRequest request,HttpServletResponse response){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://seller.aixiaoping.com/Wechat/Admin/wechatList.html?admin_id="+current_user_id);
		return "weChat/show";
	}
	
	
	@NeedPermission(permissionName="公众号/小程序基础配置",classifyName="公众号/小程序管理")
	@IsItem(firstItemName="公众号/小程序管理" ,secondItemName="公众号/小程序基础配置")
	@RequestMapping("/wechatInfo")
	public String wechatInfo(HttpServletRequest request,HttpServletResponse response){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://seller.aixiaoping.com/Wechat/Admin/wechatInfo.html?admin_id="+current_user_id);
		return "weChat/show";
	}
	
	@NeedPermission(permissionName="上传模块轮播图",classifyName="公众号/小程序管理")
	@IsItem(firstItemName="公众号/小程序管理" ,secondItemName="上传模块轮播图")
	@RequestMapping("/upShopImages")
	public String shopImages(HttpServletRequest request,HttpServletResponse response){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://seller.aixiaoping.com/Wechat/Admin/shopImages.html?admin_id="+current_user_id);
		return "weChat/show";
	}
	
}
