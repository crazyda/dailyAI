package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdverpool entity provides the base persistence definition of the
 * Adverpool entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdverpool implements java.io.Serializable {

	// Fields

	private Integer id;
	private Goods goods;
	private AdminUser adminUser;
	private Integer playtotal;
	private Integer batch;
	private Boolean higelevel;
	private Boolean isvalid;
	private Timestamp createtime;
	private Timestamp starttime;
	private Timestamp endtime;
	private Integer isplay;
	private Integer playZone;
	
	// Constructors

	/** default constructor */
	public AbstractAdverpool() {
	}

	/** minimal constructor */
	public AbstractAdverpool(Goods goods, AdminUser adminUser,
			Integer playtotal, Integer batch, Boolean higelevel,
			Boolean isvalid, Timestamp createtime) {
		this.goods = goods;
		this.adminUser = adminUser;
		this.playtotal = playtotal;
		this.batch = batch;
		this.higelevel = higelevel;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	/** full constructor */
	public AbstractAdverpool(Goods goods, AdminUser adminUser,
			Integer playtotal, Integer batch, Boolean higelevel,
			Boolean isvalid, Timestamp createtime, Timestamp starttime,
			Timestamp endtime, Integer isplay,Integer playZone) {
		this.goods = goods;
		this.adminUser = adminUser;
		this.playtotal = playtotal;
		this.batch = batch;
		this.higelevel = higelevel;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.starttime = starttime;
		this.endtime = endtime;
		this.isplay = isplay;
		this.playZone = playZone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getPlaytotal() {
		return this.playtotal;
	}

	public void setPlaytotal(Integer playtotal) {
		this.playtotal = playtotal;
	}

	public Integer getBatch() {
		return this.batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	public Boolean getHigelevel() {
		return this.higelevel;
	}

	public void setHigelevel(Boolean higelevel) {
		this.higelevel = higelevel;
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

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Integer getIsplay() {
		return this.isplay;
	}

	public void setIsplay(Integer isplay) {
		this.isplay = isplay;
	}

	public Integer getPlayZone() {
		return playZone;
	}

	public void setPlayZone(Integer playZone) {
		this.playZone = playZone;
	}

}