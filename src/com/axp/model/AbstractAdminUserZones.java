package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractBuy entity provides the base persistence definition of the Buy
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminUserZones implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private  ProvinceEnum provinceEnum;
	private Boolean isvalid;
	private Timestamp createtime;


	// Constructors

	/** default constructor */
	public AbstractAdminUserZones() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public AdminUser getAdminUser() {
		return adminUser;
	}


	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}


	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}


	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}


	public Boolean getIsvalid() {
		return isvalid;
	}


	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}


	public Timestamp getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** minimal constructor */
	


}