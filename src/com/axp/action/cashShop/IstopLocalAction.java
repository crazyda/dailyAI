package com.axp.action.cashShop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.service.cashShop.LocalIstopService;
import com.axp.service.system.AdminUserService;

@RequestMapping("/cashShop/Istoplocal")
@Controller
public class IstopLocalAction {
	@Resource
	LocalIstopService localIstopService;
	@Resource
	AdminUserService adminUserService;

	@NeedPermission(permissionName = "特产置顶", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "特产置顶")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		localIstopService.getList(request, response);
		return "cashShop/localIstop/list";
	}
	
	@ResponseBody 
	@RequestMapping("/toTop")
	public String ajaxTop(Integer sid,HttpServletRequest request,HttpServletResponse response,String istop){
		Map<String,Object> map =new HashMap<String,Object>();
		try{
			Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
	        AdminUser adminUser = adminUserService.findById(adminUserId);
	        if(adminUser.getLevel()>=95){
	        	if("1".equals(istop)){
					localIstopService.ajaxTop(sid,true);
				}else{
					localIstopService.ajaxTop(sid,false);
				}
	        	map.put("msg",true);
	        	map.put("message","success!");
	        }else{
	        	map.put("msg",false);
	        	map.put("message","access denied!");
	        }
		}catch(Exception e){
		   map.put("msg",false);
		   map.put("message","fail！");
		}
		Object obj = JSONObject.fromObject(map);
		obj.toString();
		return obj.toString();
	}
}
