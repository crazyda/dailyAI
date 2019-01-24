package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractShoptypes entity provides the base persistence definition of the
 * Shoptypes entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractShoptypes implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Boolean isvalid;
	private Timestamp createtime;
	private String imgurl;
	private Integer rank;
	private Set shopses = new HashSet(0);
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractShoptypes() {
	}

	/** minimal constructor */
	public AbstractShoptypes(String name, Boolean isvalid, Timestamp createtime) {
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	/** full constructor */
	public AbstractShoptypes(String name, Boolean isvalid,
			Timestamp createtime, String imgurl, Set shopses, Set goodses) {
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.imgurl = imgurl;
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

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}