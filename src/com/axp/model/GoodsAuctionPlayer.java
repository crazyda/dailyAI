package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * GoodsAuctionPlayer entity. @author MyEclipse Persistence Tools
 */
public class GoodsAuctionPlayer extends AbstractGoodsAuctionPlayer implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsAuctionPlayer() {
	}

	/** full constructor */
	public GoodsAuctionPlayer(GoodsAuction goodsAuction, Users users,
			Integer margin, Integer totalScore, Integer auctionResult,
			String remark, Timestamp createTime, Timestamp lastTime,
			Boolean isValid, Integer version, Set goodsAuctionRecords) {
		super(goodsAuction, users, margin, totalScore, auctionResult, remark,
				createTime, lastTime, isValid, version, goodsAuctionRecords);
	}

}
