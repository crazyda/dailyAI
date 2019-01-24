package com.axp.model;

import java.sql.Timestamp;

/**
 * GoodsAuction entity. @author MyEclipse Persistence Tools
 */
public class GoodsAuction extends AbstractGoodsAuction implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsAuction() {
	}

	/** full constructor */
	public GoodsAuction(Goods goods, Integer baseScore, Integer unitScore,
			String remark, Timestamp startTime, Double cycle,
			Timestamp createTime, Boolean isValid) {
		super(goods, baseScore, unitScore, remark, startTime, cycle,
				createTime, isValid);
	}

}
