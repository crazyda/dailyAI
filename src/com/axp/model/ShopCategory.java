package com.axp.model;

import java.sql.Timestamp;

public class ShopCategory extends AbstractShopCategory implements java.io.Serializable{

	public ShopCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopCategory(Integer id,String name, Boolean isValid,
			Timestamp createtime,String type,Seller seller,ShopCategory shopCategory,Integer level) {
		super(id, name, isValid, createtime, type, seller,shopCategory,level);
		// TODO Auto-generated constructor stub
	}
}
