package com.axp.model;

public abstract class AbstractCashshopGoodsLable implements java.io.Serializable{

	private Integer id;
	private CashshopGoodsLable cashshopGoodsLable;
	private String name;
	private Boolean isValid;
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
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	public CashshopGoodsLable getCashshopGoodsLable() {
		return cashshopGoodsLable;
	}
	public void setCashshopGoodsLable(CashshopGoodsLable cashshopGoodsLable) {
		this.cashshopGoodsLable = cashshopGoodsLable;
	}
	/** default constructor */
	public AbstractCashshopGoodsLable() {
	}
	
	/** minimal constructor */
	public AbstractCashshopGoodsLable(String name, Boolean isValid) {
		this.name = name;
		this.isValid = isValid;
	}
	/** full constructor */	
	public AbstractCashshopGoodsLable(
			CashshopGoodsLable cashshopGoodsLable, String name, Boolean isValid) {
		this.cashshopGoodsLable = cashshopGoodsLable;
		this.name = name;
		this.isValid = isValid;
	}

}
