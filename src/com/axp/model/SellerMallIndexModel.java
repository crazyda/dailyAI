package com.axp.model;

import java.sql.Timestamp;

public class SellerMallIndexModel extends AbstractSellerMallIndexModel implements java.io.Serializable {
	private static final long serialVersionUID = 7910463584886809182L;

	// 构造函数；
	public SellerMallIndexModel() {
		super();
	}

	public SellerMallIndexModel(Timestamp time) {
		super(time);
	}

}
