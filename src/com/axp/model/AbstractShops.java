package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractShops entity provides the base persistence definition of the Shops
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractShops implements java.io.Serializable {

	// Fields

	private Integer id;
	private Zones zones;
	private Shoptypes shoptypes;
	private String name;
	private Boolean isvalid;
	private Timestamp createtime;
	private String phone;
	private String address;
	private Integer userId;

	// Constructors

	/** default constructor */
	public AbstractShops() {
	}

	/** minimal constructor */
	public AbstractShops(Integer id, Zones zones, Shoptypes shoptypes,
			String name, Boolean isvalid, Timestamp createtime, String phone,
			Integer userId) {
		this.id = id;
		this.zones = zones;
		this.shoptypes = shoptypes;
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.phone = phone;
		this.userId = userId;
	}

	/** full constructor */
	public AbstractShops(Integer id, Zones zones, Shoptypes shoptypes,
			String name, Boolean isvalid, Timestamp createtime, String phone,
			String address, Integer userId) {
		this.id = id;
		this.zones = zones;
		this.shoptypes = shoptypes;
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.phone = phone;
		this.address = address;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Zones getZones() {
		return this.zones;
	}

	public void setZones(Zones zones) {
		this.zones = zones;
	}

	public Shoptypes getShoptypes() {
		return this.shoptypes;
	}

	public void setShoptypes(Shoptypes shoptypes) {
		this.shoptypes = shoptypes;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}