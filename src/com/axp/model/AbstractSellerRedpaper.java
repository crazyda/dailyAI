package com.axp.model;

import java.util.Date;

/**
 * AbstractSellerRedpaper entity provides the base persistence definition of the
 * SellerRedpaper entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSellerRedpaper implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private double totalCash;
	private Integer count;//红包总数；
	private Date createTime;
	private boolean isValid;
	private String descript;
	private Integer surplusCount;//剩余红包总数；

	// Constructors

	public Integer getSurplusCount() {
		return surplusCount;
	}

	public void setSurplusCount(Integer surplusCount) {
		this.surplusCount = surplusCount;
	}

	/** default constructor */
	public AbstractSellerRedpaper() {
	}

	/** minimal constructor */
	public AbstractSellerRedpaper(Seller seller) {
		this.seller = seller;
	}

	/** full constructor */
	public AbstractSellerRedpaper(Seller seller, double totalCash,
			Integer count, Date createTime, boolean isValid, String descript,Integer surplusCount) {
		this.seller = seller;
		this.totalCash = totalCash;
		this.count = count;
		this.createTime = createTime;
		this.isValid = isValid;
		this.descript = descript;
		this.surplusCount = surplusCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public double getTotalCash() {
		return this.totalCash;
	}

	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}