package com.axp.dao;

import java.util.List;

import com.axp.model.ProvinceEnum;

public interface ProvinceEnumDAO extends IBaseDao<ProvinceEnum> {
	
	/**\
	 * 拼接上下级
	 * @param provinceEnum
	 * @return
	 */
	public String findLevel2ProvinceEnum(ProvinceEnum provinceEnum);

	
	String findProvinceEnumByAdminUser(ProvinceEnum provinceEnum);
	

	/**
	 * 根据用户查找管辖范围 或 根据传入的地区查找管辖范围  2选1
	 * @param adminUserId
	 * @param zoneId
	 * @return
	 */
	List<ProvinceEnum> findProvinceEnumByZoneid(Integer adminLevel,Integer zoneId);
}