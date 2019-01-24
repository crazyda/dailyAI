package com.axp.model;

import java.sql.Timestamp;



public class SellerRating  extends AbstractSellerRating implements java.io.Serializable{
	

	public SellerRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerRating(Integer id, Users users, Seller seller,
			Float score, Boolean isValid, Timestamp createTime) {
		super(id, users, seller, score, isValid, createTime);
		// TODO Auto-generated constructor stub
	}
	
	
		
}
