package com.axp.model;


/**
 * Cashshop entity. @author MyEclipse Persistence Tools
 */
public class CashshopGoodsLable extends AbstractCashshopGoodsLable implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CashshopGoodsLable() {
	}
	
	/** minimal constructor */
	public CashshopGoodsLable(String name,Boolean isValid) {
		super(name,isValid);
	}
	
	/** full constructor */
	public CashshopGoodsLable(CashshopGoodsLable cashshopGoodsLable,String name, Boolean isValid) {
		super(cashshopGoodsLable,name,isValid);
	}

}
