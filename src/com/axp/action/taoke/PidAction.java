package com.axp.action.taoke;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.service.taoke.CityPidDistributeService;

@Controller
@RequestMapping("/taoke/pid")
public class PidAction extends BaseAction{
	@Resource
	CityPidDistributeService cityPidDistributeService;
	@NeedPermission(permissionName="pid管理",classifyName="淘客管理")
	@IsItem(firstItemName="淘客管理",secondItemName="pid管理")
	@RequestMapping("/list")
	public String getInvitecodeList(HttpServletRequest request,HttpServletResponse response){
		taokePidService.getPidList(request, response);
		return "taoke/pid/list";
	}
	
	@RequestMapping("/del")
	public String del(String ids, HttpServletRequest request, HttpServletResponse response){
		taokePidService.del(ids);
		return "redirect:list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Integer id){
		taokePidService.add(request, id);
		return "taoke/pid/add";
	}

	@RequestMapping("/save")
	public String save(Integer id,String pid, String pidname, Timestamp createtime,
			HttpServletRequest request, HttpServletResponse response,
			Integer status,String tkLoginLoginname,String tkLoginPassword,String tkLoginUsername){
		taokePidService.save(id, pid, pidname, createtime, request, response, status, tkLoginLoginname, tkLoginPassword, tkLoginUsername);
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
	
	//单条分配
	@RequestMapping("/isDistribute")
	public String isDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.isDistribute(ids, request, response);
		return "taoke/pid/distribute";
	}
	
	//批量分配
	@RequestMapping("/allDistribute")
	public String allDistribute(String ids,HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.allDistribute(ids, request, response);
		return "taoke/pid/allDistribute";
	}
	
	//取消分配
	@RequestMapping("/ofDistribute")
	public String ofDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.ofDistribute(ids, request, response);
		return "redirect:list";
	}
	
	//强制回收
	@RequestMapping("/recycleDistribute")
	public String recycleDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.recycleDistribute(ids, request, response);
		return "redirect:list";
	}
	
	//保存单条分配
	@RequestMapping("/saveDistribute")
	public String saveDistribute(Integer id,Integer pid,String pidId,Integer adminuserId,Timestamp createtime,
			HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.saveDistribute(id, pid, pidId, adminuserId, createtime, request, response);
		return "redirect:list";
	}
	
	
	//批量保存分配
	@RequestMapping("/saveAll")
	public String saveAll(String ids,Integer pid,String pidId,Integer adminuserId,Timestamp createtime,
			HttpServletRequest request,HttpServletResponse response){
		cityPidDistributeService.saveAll(ids, pid, pidId, adminuserId, createtime, request, response);
		return "redirect:list";
	}
}
