package com.axp.model;

import java.sql.Timestamp;

/**
 * GoodsAdverrecords entity. @author MyEclipse Persistence Tools
 */
public class GoodsAdverrecords extends AbstractGoodsAdverrecords implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsAdverrecords() {
	}

	/** full constructor */
	public GoodsAdverrecords(GoodsAdversettings goodsAdversettings,
			Integer scount, Integer ocount, Boolean isvalid,
			Timestamp createtime) {
		// super(goodsAdversettings, scount, ocount, isvalid, createtime);
	}

}
