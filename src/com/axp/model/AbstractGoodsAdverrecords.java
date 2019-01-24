package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractGoodsAdverrecords entity provides the base persistence definition of
 * the GoodsAdverrecords entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsAdverrecords implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private GoodsAdversettings goodsAdversettings;
	private Integer scount;
	private Integer ocount;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractGoodsAdverrecords() {
	}

	/** full constructor */
	public AbstractGoodsAdverrecords(AdminUser adminUser,
			GoodsAdversettings goodsAdversettings, Integer scount,
			Integer ocount, Boolean isvalid, Timestamp createtime) {
		this.adminUser = adminUser;
		this.goodsAdversettings = goodsAdversettings;
		this.scount = scount;
		this.ocount = ocount;
		this.isvalid = isvalid;
		this.createtime = createtime;
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

	public GoodsAdversettings getGoodsAdversettings() {
		return this.goodsAdversettings;
	}

	public void setGoodsAdversettings(GoodsAdversettings goodsAdversettings) {
		this.goodsAdversettings = goodsAdversettings;
	}

	public Integer getScount() {
		return this.scount;
	}

	public void setScount(Integer scount) {
		this.scount = scount;
	}

	public Integer getOcount() {
		return this.ocount;
	}

	public void setOcount(Integer ocount) {
		this.ocount = ocount;
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

}