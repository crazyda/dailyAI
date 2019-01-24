package com.axp.service.help;

import java.util.List;
import java.util.Map;

import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;

public interface HelpService {
	/**
	 * 查找当前用户下的所有 省市区
	 * @param adminUser
	 * @return
	 */
	public Map<Integer,List<ProvinceEnum>> getUserNextLevelArea(AdminUser adminUser);
	
	/**
	 * 查找当前用户下的所有市区商品
	 * @param adminUser
	 * @return
	 */
	public String findGoodsByZone(Integer adminUserId);
}
