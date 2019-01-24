package com.axp.model;

/**
 * ReGoodsDetails entity. @author MyEclipse Persistence Tools
 */

public class ReGoodsDetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private ReGoodsOfBase goods;
	private String content;
	private Boolean isValid;
	private Integer isNew;
	// Constructors

	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	/** default constructor */
	public ReGoodsDetails() {
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReGoodsOfBase getGoods() {
		return goods;
	}

	public void setGoods(ReGoodsOfBase goods) {
		this.goods = goods;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}






}