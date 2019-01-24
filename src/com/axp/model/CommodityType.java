package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * CommodityType entity. @author MyEclipse Persistence Tools
 */
public class CommodityType extends AbstractCommodityType implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CommodityType() {
	}

	/** minimal constructor */
	public CommodityType(String name, Boolean isValid) {
		super(name, isValid);
	}

	/** full constructor */
	public CommodityType(Integer id, Boolean isValid, Timestamp createTime,
			Integer level, String name, String imgUrl,
			CommodityType commodityType, Set commodityTypes, Set cashshops,
			Integer modelId) {
		super(id, isValid, createTime, level, name, imgUrl, commodityType,
				commodityTypes, cashshops, modelId);
		// TODO Auto-generated constructor stub
	}

 
}
