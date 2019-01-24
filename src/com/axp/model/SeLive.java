package com.axp.model;

import java.sql.Timestamp;

/**
 * SeLive entity. @author MyEclipse Persistence Tools
 */
public class SeLive extends AbstractSeLive implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SeLive() {
	}

	/** full constructor */
	public SeLive(Integer adminuserId, Integer sellerId, String livename,
			String image, String liveUri, Boolean istop,
			Timestamp begintime,Timestamp endtime,
			Boolean isvalid, String remark, String sellerName,
			String sellerAddress, String sellerLogo ,String imgRecommend) {
		super(adminuserId, sellerId, livename, image, liveUri, istop,
				begintime,endtime, isvalid, remark, sellerName, sellerAddress,
				sellerLogo, imgRecommend);
		
	}

}
