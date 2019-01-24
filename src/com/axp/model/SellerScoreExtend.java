package com.axp.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.axp.util.QueryModel;


/**
 * Seller entity. @author MyEclipse Persistence Tools
 */
public class SellerScoreExtend extends AbstractSellerScoreExtend implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SellerScoreExtend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellerScoreExtend(Integer id, String name, Seller seller,
			Double money, Integer score, Integer extendNum, String extendImage,
			Timestamp validityTime, Timestamp createtime, Boolean isvalid,
			String remark) {
		super(id, name, seller, money, score, extendNum, extendImage, validityTime,
				createtime, isvalid, remark);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
