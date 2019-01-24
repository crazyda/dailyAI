package com.axp.action.professional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.service.professional.ShopService;

@Controller
@RequestMapping("/professional/sellershop")
public class ShopAction extends BaseAction{
	@Resource
	private ShopService shopservice;
	
	
	@NeedPermission(permissionName="商家管理",classifyName="业务管理")
	@IsItem(firstItemName="业务管理",secondItemName="商家管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		shopservice.getSellerList(request, response);
		return "/professional/sellerShop/list";
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Object del(Integer id,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map =new HashMap<String, Object>();
		if(shopservice.delete(request, id)){
			map.put("result", true);
		}
		else{
			map.put("result", false);
		}
		return map;
	}

	
	
	
	
}
