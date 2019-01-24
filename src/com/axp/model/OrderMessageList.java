package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * OrderMessageList entity. @author MyEclipse Persistence Tools
 */
public class OrderMessageList extends AbstractOrderMessageList implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OrderMessageList() {
	}

	/** full constructor */
	public OrderMessageList( MessageType messageType,
			String content, Timestamp time, Boolean isValid,
			Timestamp createtime, String title, ReGoodsorder reGoodsorder
			,Users users) {
		super( messageType, content, time, isValid, createtime, content, reGoodsorder,  users);
	}

}
