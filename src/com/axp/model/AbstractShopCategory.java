package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractShoptypes entity provides the base persistence definition of the
 * Shoptypes entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractShopCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;//店铺的类别；1，店铺的类别是由我们定好的，商家不能自定义类别；2，使用json的格式记录店铺的类别；
	private Timestamp createtime;
	private Seller seller;
	private Boolean isValid; 
	private ShopCategory shopCategory;
	private Integer level;
	// Constructors

	/** default constructor */
	public AbstractShopCategory() {
	}


	/** full constructor */
	public AbstractShopCategory(Integer id,String name, Boolean isValid,
			Timestamp createtime,String type,Seller seller,ShopCategory shopCategory,Integer level) {
		this.id= id;
		this.isValid =isValid;
		this.name =name;
		this.createtime =createtime;
		this.type =type;
		this.seller =seller;
		this.shopCategory = shopCategory;
		this.level = level;
	}

	// Property accessors
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Timestamp getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}


	public Seller getSeller() {
		return seller;
	}


	public void setSeller(Seller seller) {
		this.seller = seller;
	}


	public Boolean getIsValid() {
		return isValid;
	}


	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}




	public ShopCategory getShopCategory() {
		return shopCategory;
	}


	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}

	
	

	
}