package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * ProvinceEnum entity. @author MyEclipse Persistence Tools
 */
public class ProvinceEnum extends AbstractProvinceEnum implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProvinceEnum() {
	}

	/** full constructor */
	public ProvinceEnum(ProvinceEnum provinceEnum, String name, Integer zoneId, Timestamp createTime, Boolean isValid, Integer level, String englishChar, Set cashshops, Set provinceEnums) {
		super(provinceEnum, name, zoneId, createTime, isValid, level, englishChar, cashshops, provinceEnums);
	}

}
