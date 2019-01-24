package com.axp.action.hhh;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.CashShopTypeDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.model.CashshopType;
import com.axp.model.ImageType;
import com.axp.service.cashShop.CashShopTypeService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;



@RequestMapping("/hhh/hhh")
@Controller
public class HhhAction {

	

	@NeedPermission(permissionName = "换货会列表", classifyName = "换货会管理")
	@IsItem(firstItemName = "换货会管理", secondItemName = "换货会列表")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/meeting/index?user_id="+current_user_id);
		
		return "hhh/show";
	}
	
	@NeedPermission(permissionName = "添加换货会", classifyName = "换货会管理")
	@IsItem(firstItemName = "换货会管理", secondItemName = "添加换货会")
	@RequestMapping("/list2")
	public String list2(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/meeting/add?user_id="+current_user_id);
		
		return "hhh/show";
	}
	
	@NeedPermission(permissionName = "换货商品审核 列表", classifyName = "换货会管理")
	@IsItem(firstItemName = "换货会管理", secondItemName = "换货商品审核 列表")
	@RequestMapping("/list3")
	public String list3(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/Goods/unIndex?user_id="+current_user_id);
		
		return "hhh/show";
	}
	
	@NeedPermission(permissionName = "换货会已审核 商品列表", classifyName = "换货会管理")
	@IsItem(firstItemName = "换货会管理", secondItemName = "换货会已审核 商品列表")
	@RequestMapping("/list4")
	public String list4(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/goods/index?user_id="+current_user_id);
		
		return "hhh/show";
	}
	
	@NeedPermission(permissionName = "换货会协议", classifyName = "换货会管理")
	@IsItem(firstItemName = "换货会管理", secondItemName = "换货会协议")
	@RequestMapping("/list5")
	public String list5(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/meeting/rule?user_id="+current_user_id);
		
		return "hhh/show";
	}
	
	@NeedPermission(permissionName = "换货会订单", classifyName = "换货会管理")
	@IsItem(firstItemName = "换货会管理", secondItemName = "换货会订单")
	@RequestMapping("/list6")
	public String list6(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/orders?user_id="+current_user_id);
		
		return "hhh/show";
	}
	
	
	
	
}
