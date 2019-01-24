package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractProvinceEnum entity provides the base persistence definition of the
 * ProvinceEnum entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProvinceEnum implements java.io.Serializable {

	// Fields

	private Integer id;
	private ProvinceEnum provinceEnum;
	private String name;
	private Integer zoneId;
	private Timestamp createTime;
	private Boolean isValid;
	private Integer level;
	private String englishChar;
	

	private Set cashshops = new HashSet(0);
	private Set provinceEnums = new HashSet(0);
	private Integer level2;
	private ProvinceEnum provinceEnum2;
	
	// Constructors

	/** default constructor */
	public AbstractProvinceEnum() {
	}

	/** full constructor */
	public AbstractProvinceEnum(ProvinceEnum provinceEnum, String name, Integer zoneId, Timestamp createTime, Boolean isValid, Integer level, String englishChar, Set cashshops, Set provinceEnums) {
		this.provinceEnum = provinceEnum;
		this.name = name;
		this.zoneId = zoneId;
		this.createTime = createTime;
		this.isValid = isValid;
		this.level = level;
		this.englishChar = englishChar;
		this.cashshops = cashshops;
		this.provinceEnums = provinceEnums;
	}

	
	public Integer getLevel2() {
		return level2;
	}

	public void setLevel2(Integer level2) {
		this.level2 = level2;
	}

	public ProvinceEnum getProvinceEnum2() {
		return provinceEnum2;
	}

	public void setProvinceEnum2(ProvinceEnum provinceEnum2) {
		this.provinceEnum2 = provinceEnum2;
	}
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProvinceEnum getProvinceEnum() {
		return this.provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getEnglishChar() {
		return this.englishChar;
	}

	public void setEnglishChar(String englishChar) {
		this.englishChar = englishChar;
	}

	public Set getCashshops() {
		return this.cashshops;
	}

	public void setCashshops(Set cashshops) {
		this.cashshops = cashshops;
	}

	public Set getProvinceEnums() {
		return this.provinceEnums;
	}

	public void setProvinceEnums(Set provinceEnums) {
		this.provinceEnums = provinceEnums;
	}

}