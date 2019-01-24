package com.axp.model;

import java.sql.Timestamp;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
public class Orders extends AbstractOrders implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(Integer userId, Integer tcount, Integer score,
			Integer status, String ordernum, Integer goodId, Boolean isvalid,
			Timestamp createtime) {
		// super(userId, tcount, score, status, ordernum, goodId, isvalid,
		// createtime);
	}

}
