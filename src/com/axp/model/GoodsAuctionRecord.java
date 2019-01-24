package com.axp.model;

import java.sql.Timestamp;

/**
 * GoodsAuctionRecord entity. @author MyEclipse Persistence Tools
 */
public class GoodsAuctionRecord extends AbstractGoodsAuctionRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsAuctionRecord() {
	}

	/** full constructor */
	public GoodsAuctionRecord(GoodsAuctionPlayer goodsAuctionPlayer,
			Integer score, Integer type, String remark, Timestamp createTime,
			Boolean isValid) {
		super(goodsAuctionPlayer, score, type, remark, createTime, isValid);
	}

}
