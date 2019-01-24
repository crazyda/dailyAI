package com.axp.model;

import java.sql.Timestamp;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
public class Product extends AbstractProduct implements java.io.Serializable {

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String productName, String productImg,
			Users users, AdminUser adminUser, Integer score, Branks brank,
			Timestamp createTime, Boolean isValid, Integer num, Integer useNum,
			String remark) {
		super(id, productName, productImg, users, adminUser, score, brank, createTime,
				isValid, num, useNum, remark);
		// TODO Auto-generated constructor stub
	}

	

	
	


}
