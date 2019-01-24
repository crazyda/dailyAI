package com.axp.model;

import java.sql.Timestamp;

/**
 * PromoterGoods entity. @author MyEclipse Persistence Tools
 */
public class PromoterGoods extends AbstractPromoterGoods implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PromoterGoods() {
	}

	/** full constructor */
	public PromoterGoods(String name, String remark, Double price, String imgUrl, Integer count, Timestamp createTime, Boolean isValid, Boolean isShare, String property, Double levelOneEarn, Double levelTwoEarn,
			Double levelThreeEarn) {
		super(name, remark, price, imgUrl, count, createTime, isValid, isShare, property, levelOneEarn, levelTwoEarn, levelThreeEarn);
	}

}
