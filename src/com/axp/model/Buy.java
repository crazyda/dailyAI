package com.axp.model;

import java.sql.Timestamp;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
public class Buy extends AbstractBuy implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Buy() {
	}

	/** minimal constructor */
	public Buy(AdminUser adminUser, Integer sell, Integer status, Boolean isvalid, Timestamp createtime, String sellname, Integer quantity) {
		super(adminUser, sell, status, isvalid, createtime, sellname, quantity);
	}

	/** full constructor */
	public Buy(AdminUser adminUser, Integer sell, Integer status, Boolean isvalid, Timestamp createtime, String sellname, Integer quantity, Integer checker, String checkstr, Timestamp checktime,
			Integer level) {
		super(adminUser, sell, status, isvalid, createtime, sellname, quantity, checker, checkstr, checktime, level);
	}

}
