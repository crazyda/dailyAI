package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdminuserRedpaper entity provides the base persistence definition of
 * the AdminuserRedpaper entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminuserRedpaper implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private String name;
	private Double totalMoney;
	private Integer totalQuantity;
	private String reamrk;
	private Double surplusMoney;
	private Integer surplusQuantity;
	private Boolean isvalid;
	private Timestamp creattime;
	private Integer type;
	private ProvinceEnum provinceEnum;
	private Integer status;

	// Constructors

	/** default constructor */
	public AbstractAdminuserRedpaper() {
	}

	/** full constructor */
	public AbstractAdminuserRedpaper(AdminUser adminUser,
			String name, Double totalMoney, Integer totalQuantity,
			String reamrk, Double surplusMoney, Integer surplusQuantity,
			Boolean isvalid, Timestamp creattime, Integer type, ProvinceEnum provinceEnum,
			Integer status) {
		this.adminUser = adminUser;
		this.name = name;
		this.totalMoney = totalMoney;
		this.totalQuantity = totalQuantity;
		this.reamrk = reamrk;
		this.surplusMoney = surplusMoney;
		this.surplusQuantity = surplusQuantity;
		this.isvalid = isvalid;
		this.creattime = creattime;
		this.type = type;
		this.provinceEnum = provinceEnum;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getTotalQuantity() {
		return this.totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getReamrk() {
		return this.reamrk;
	}

	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}

	public Double getSurplusMoney() {
		return this.surplusMoney;
	}

	public void setSurplusMoney(Double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public Integer getSurplusQuantity() {
		return this.surplusQuantity;
	}

	public void setSurplusQuantity(Integer surplusQuantity) {
		this.surplusQuantity = surplusQuantity;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}


	public Timestamp getCreattime() {
		return creattime;
	}

	public void setCreattime(Timestamp creattime) {
		this.creattime = creattime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}
	
}