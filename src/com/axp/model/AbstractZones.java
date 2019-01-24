package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractZones entity provides the base persistence definition of the Zones
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractZones implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Boolean isvalid;
	private Timestamp createtime;
	private String englishChar;
	private Set shopses = new HashSet(0);
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractZones() {
	}

	/** minimal constructor */
	public AbstractZones(String name, Boolean isvalid, Timestamp createtime,
			String englishChar) {
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.englishChar = englishChar;
	}

	/** full constructor */
	public AbstractZones(String name, Boolean isvalid, Timestamp createtime,
			String englishChar, Set shopses, Set goodses) {
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.englishChar = englishChar;
		this.shopses = shopses;
		this.goodses = goodses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEnglishChar() {
		return this.englishChar;
	}

	public void setEnglishChar(String englishChar) {
		this.englishChar = englishChar;
	}

	public Set getShopses() {
		return this.shopses;
	}

	public void setShopses(Set shopses) {
		this.shopses = shopses;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

}