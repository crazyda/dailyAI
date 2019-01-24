/**
 * 2018-10-15
 * Administrator
 */
package com.axp.model;

import java.sql.Timestamp;

/**
 * @author da
 * @data 2018-10-15下午5:32:32
 */
public class GameActivity extends AbstractGameActivity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameActivity(Integer id, Timestamp createTime, Boolean isValid,
			Boolean isStart, Integer drawNum, Integer drawScore,
			Scoretypes drawYlassify, Integer score,
			ReGoodsOfSellerMall reGoodsOfSellerMall, Double chanceScore,
			CommodityType commodityType, String coverPics, String content,
			String oneScore, AdminUser seller, String detail,
			ProvinceEnum provinceEnum, AdminUser adminUser) {
		super(id, createTime, isValid, isStart, drawNum, drawScore, drawYlassify,
				score, reGoodsOfSellerMall, chanceScore, commodityType, coverPics,
				content, oneScore, seller, detail, provinceEnum, adminUser);
		// TODO Auto-generated constructor stub
	}

	
	
	
	



	
	
}
