package com.axp.model;

import java.sql.Timestamp;

/**
 * 商品类型：
 * 该类用来记录商家商城中，商家对自己经营的商品的分类；
 * 产品类型只分两层，如：衣服-->童装，男装等；
 */
public abstract class AbstractSellerMallGoodsType implements
		java.io.Serializable {

	private static final long serialVersionUID = -6766917953496437612L;

	// 字段；
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private String name; // 此分类的名称；
	private String picture; // 此分类的图片；
	private String url; // 此分类的连接地址；
	private SellerMallGoodsType parentType; // 此分类的父分类；

	// getAndSet
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public SellerMallGoodsType getParentType() {
		return parentType;
	}

	public void setParentType(SellerMallGoodsType parentType) {
		this.parentType = parentType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}