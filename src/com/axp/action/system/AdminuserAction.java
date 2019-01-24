package com.axp.action.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.axp.action.BaseAction;
import com.axp.model.MembersBonusList;
import com.axp.model.ProviderBonus;

@Controller
@RequestMapping("adminUser")
public class AdminuserAction extends BaseAction {
	
	@RequestMapping("list")
	public String list(HttpServletRequest request){
		adminUserService.list(request);
		return "adminUser/list";
	}
	
	@RequestMapping("add")
    public String add(Integer id, HttpServletRequest request){
		adminUserService.add(id,request);
		return "adminUser/add";
	}
	
	@RequestMapping("save")
	public String save(
			Integer id,
			Integer quantity,
			Integer levelname,
			Integer isZoneLimit,
			Integer zonesId,
			Integer sellerId,
			Double latitude,
			Double longitude,
			Double radius,
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "phone", defaultValue = "") String phone,
			@RequestParam(value = "contacts", defaultValue = "")String contacts,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "loginName", defaultValue = "") String loginName,
			ProviderBonus adminScale,MembersBonusList membersBonusList, HttpServletRequest request) {
		adminUserService.save(id, quantity, levelname, isZoneLimit, zonesId,
				sellerId, latitude, longitude, radius, username, phone,
				contacts, address, loginName, adminScale,membersBonusList, request);
		return "redirect:list";
	}
	
}
