package com.axp.util;

import java.util.HashMap;
import java.util.Map;

public class RoleUtil {

	private static HashMap<String, String> admin;
	public static Map<String,String>getArrayMap(Integer level){
		Map<String,String> map = new HashMap<String, String>();
		switch (level) {
		case 65:map.put("60", "商家"); break;
		case 99:;
		case 95:map.put("85", "运营中心");map.put("60", "商家");	
		case 85:map.put("75", "城市代理");		
		case 75:map.put("65", "联盟合伙人");		
				
		case 60:break;	
		default:
			break;
		}
		return map;
	}
	
	
	public static HashMap<String, String> getAdmin() {
		if (admin == null) {
			admin = new HashMap<String, String>();
			//admin.put("90", "礼品运营中心");
			admin.put("99", "超级管理员");
			admin.put("95", "总部");
			admin.put("85", "运营中心");
			admin.put("75", "代理商");
			admin.put("65", "联盟合伙人");
			admin.put("60", "供应商");
		}
		return admin;
	}

}
