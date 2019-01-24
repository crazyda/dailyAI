package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Cashshop entity. @author MyEclipse Persistence Tools
 */
public class Cashshop extends AbstractCashshop implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Cashshop() {
	}

	/** minimal constructor */
	public Cashshop(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public Cashshop(Seller seller, CommodityType commodityType, AdminUser adminUser, ProvinceEnum provinceEnum, String name, Boolean isValid, Double price,Integer cashpointLimit,Double priceCash,Double priceCoupon,String imgUrl, String imgUrlRemark,
			Integer tcount, Integer surplus, Timestamp createTime, String description, String remark, Timestamp startTime, Timestamp endTime, String parameterId, String imgUrlSmall,
			Set userCashshopRecords,Integer status,Integer mode,String checkstr,Integer mallType,Double priceByReset,Double priceCashReset,Double priceCouponReset,Integer cashpointLimitReset) {
		super(seller, commodityType, adminUser, provinceEnum, name, isValid, price, cashpointLimit,priceCash,priceCoupon,imgUrl, imgUrlRemark, tcount, surplus, createTime, description, remark, startTime, endTime, parameterId,
				imgUrlSmall, userCashshopRecords,status,mode,checkstr,mallType,priceByReset,priceCashReset,priceCouponReset,cashpointLimitReset);


	}

}
