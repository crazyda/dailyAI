package com.axp.model;

import java.sql.Timestamp;

/**
 * CashshopType entity. @author MyEclipse Persistence Tools
 */
public class CashshopType extends AbstractCashshopType implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public CashshopType() {
	}

	/** full constructor */
	public CashshopType(AdminUser adminUser, ImageType imageType, String name,
			String remark, String img, Timestamp createTime, Boolean isValid,String url,CommodityType commodityType) {
		super(adminUser, imageType, name, remark, img, createTime, isValid,url,commodityType);
	}

}
