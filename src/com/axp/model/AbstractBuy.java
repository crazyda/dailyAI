package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractBuy entity provides the base persistence definition of the Buy
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBuy implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Integer sell;
	private Integer status;
	private Boolean isvalid;
	private Timestamp createtime;
	private String sellname;
	private Integer quantity;
	private Integer checker;
	private String checkstr;
	private Timestamp checktime;
	private Integer level;

	// Constructors

	/** default constructor */
	public AbstractBuy() {
	}

	/** minimal constructor */
	public AbstractBuy(AdminUser adminUser, Integer sell, Integer status, Boolean isvalid, Timestamp createtime, String sellname, Integer quantity) {
		this.adminUser = adminUser;
		this.sell = sell;
		this.status = status;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.sellname = sellname;
		this.quantity = quantity;
	}

	/** full constructor */
	public AbstractBuy(AdminUser adminUser, Integer sell, Integer status, Boolean isvalid, Timestamp createtime, String sellname, Integer quantity, Integer checker, String checkstr,
			Timestamp checktime, Integer level) {
		this.adminUser = adminUser;
		this.sell = sell;
		this.status = status;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.sellname = sellname;
		this.quantity = quantity;
		this.checker = checker;
		this.checkstr = checkstr;
		this.checktime = checktime;
		this.level = level;
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

	public Integer getSell() {
		return this.sell;
	}

	public void setSell(Integer sell) {
		this.sell = sell;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getSellname() {
		return this.sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getChecker() {
		return this.checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public String getCheckstr() {
		return this.checkstr;
	}

	public void setCheckstr(String checkstr) {
		this.checkstr = checkstr;
	}

	public Timestamp getChecktime() {
		return this.checktime;
	}

	public void setChecktime(Timestamp checktime) {
		this.checktime = checktime;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}