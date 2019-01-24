package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPromoterGoods entity provides the base persistence definition of the
 * PromoterGoods entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPromoterGoods implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String remark;
	private Double price;
	private String imgUrl;
	private Integer count;
	private Timestamp createTime;
	private Boolean isValid;
	private Boolean isShare;
	private String property;
	private Double levelOneEarn;
	private Double levelTwoEarn;
	private Double levelThreeEarn;
	private Boolean isShareQuantity;
	private Integer quantity;

	// Constructors

	/** default constructor */
	public AbstractPromoterGoods() {
	}

	/** full constructor */
	public AbstractPromoterGoods(String name, String remark, Double price, String imgUrl, Integer count, Timestamp createTime, Boolean isValid, Boolean isShare, String property, Double levelOneEarn,
			Double levelTwoEarn, Double levelThreeEarn) {
		this.name = name;
		this.remark = remark;
		this.price = price;
		this.imgUrl = imgUrl;
		this.count = count;
		this.createTime = createTime;
		this.isValid = isValid;
		this.isShare = isShare;
		this.property = property;
		this.levelOneEarn = levelOneEarn;
		this.levelTwoEarn = levelTwoEarn;
		this.levelThreeEarn = levelThreeEarn;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Double getLevelOneEarn() {
		return this.levelOneEarn;
	}

	public void setLevelOneEarn(Double levelOneEarn) {
		this.levelOneEarn = levelOneEarn;
	}

	public Double getLevelTwoEarn() {
		return this.levelTwoEarn;
	}

	public void setLevelTwoEarn(Double levelTwoEarn) {
		this.levelTwoEarn = levelTwoEarn;
	}

	public Double getLevelThreeEarn() {
		return this.levelThreeEarn;
	}

	public void setLevelThreeEarn(Double levelThreeEarn) {
		this.levelThreeEarn = levelThreeEarn;
	}

	public Boolean getIsShare() {
		return isShare;
	}

	public void setIsShare(Boolean isShare) {
		this.isShare = isShare;
	}

	public Boolean getIsShareQuantity() {
		return isShareQuantity;
	}

	public void setIsShareQuantity(Boolean isShareQuantity) {
		this.isShareQuantity = isShareQuantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	
}