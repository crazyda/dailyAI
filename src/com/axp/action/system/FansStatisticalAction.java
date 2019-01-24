package com.axp.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.model.ProvinceEnum;

@Controller
@RequestMapping("/system/FansStatistical")
public class FansStatisticalAction extends BaseAction{
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;
	
	@NeedPermission(permissionName="粉丝查询",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="粉丝查询")
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpServletResponse response,String sTM,String eTM,String zoneId,String userId){
		fansStatisticalService.getfansList(request, response, sTM, eTM, zoneId,userId);
		return "baseinfo/fansStatistical/list";
	}
	
	
	@RequestMapping("/changeZone")
	@ResponseBody
	public Map<String, String> changeZone (Integer zoneId){
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("provinceEnum.id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(zoneId);
		List<ProvinceEnum> list = provinceEnumDAO.findListByCustom(ProvinceEnum.class,
				keys, values, null, null, null);
		Map<String,String> map = new HashMap<String,String>();
		
		for (ProvinceEnum s : list) {
			map.put(s.getId().toString(), s.getName());
		}
		return map;
	}
	
}
