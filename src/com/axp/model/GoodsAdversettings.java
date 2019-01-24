package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * GoodsAdversettings entity. @author MyEclipse Persistence Tools
 */
public class GoodsAdversettings extends AbstractGoodsAdversettings implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsAdversettings() {
	}

	/** minimal constructor */
	public GoodsAdversettings(Goods goods, Integer scount, Integer ocount,
			Integer acount, Boolean isvalid, Timestamp createtime) {
		// super(goods, scount, ocount, acount, isvalid, createtime);
	}

	/** full constructor */
	public GoodsAdversettings(Goods goods, Integer scount, Integer ocount,
			Integer acount, Boolean isvalid, Timestamp createtime,
			Set goodsAdverrecordses) {
		// super(goods, scount, ocount, acount, isvalid, createtime,
		// goodsAdverrecordses);
	}

}
