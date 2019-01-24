package com.axp.action.professional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.model.AdminUser;
import com.axp.service.professional.TaokeService;
import com.axp.service.professional.UserService;

@Controller
@RequestMapping("/professional/taoke")
public class TaokeAction extends BaseAction{
	
	@Resource
	private TaokeService taokeService;
	@Resource
	private UserService userService;
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;
	@Autowired
	private AdminUserDAO adminUserDAO;
	
	@NeedPermission(permissionName="地区PID管理",classifyName="淘客管理")
	@IsItem(firstItemName="业务管理",secondItemName="地区PID管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		taokeService.getTaokeInfo(request, response);
		return "professional/taoke/list";
	}
	
	@RequestMapping("/del")
	public String del(String ids,HttpServletRequest request,HttpServletResponse response){
		taokeService.del(ids);
		return "redirect:list";
	}
	@RequestMapping("/add")
	public String add(HttpServletRequest request, Integer id){
		taokeService.add(request, id);
		return "professional/taoke/add";
	}
	@RequestMapping("/save")
	public String save(Integer id, Integer zoneid, Timestamp createtime,
			String bak_uri, Integer centerid,HttpServletRequest request){
		taokeService.save(id, zoneid, createtime, bak_uri, centerid, request);
		return "redirect:list";
	}
	
	@RequestMapping("/changeDistribute")
	@ResponseBody
	public Map<String, String> changeDistribute (Integer adminuserId){
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("parentId = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(adminuserId);
		List<AdminUser> list = adminUserDao.findListByCustom(AdminUser.class,
				keys, values, null, null, null);
		Map<String,String> map = new HashMap<String,String>();
		
		for (AdminUser a : list) {
			map.put(a.getId().toString(), a.getLoginname());
		} 
		return map;
	}
}
