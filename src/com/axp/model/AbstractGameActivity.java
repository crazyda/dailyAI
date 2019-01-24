/**
 * 2018-10-15
 * Administrator
 */
package com.axp.model;

import java.sql.Timestamp;

/**
 * @author da
 * @data 2018-10-15下午5:47:05
 */
public class AbstractGameActivity implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Timestamp createTime;
	private Boolean isValid;
	private Boolean isStart;//是否启动
	private Integer drawNum;//每天抽奖次数
	private Integer drawScore; //参与积分
	private Scoretypes drawYlassify;//翻牌子的奖品类别
	private	 Integer score ; //获得积分数
	private ReGoodsOfSellerMall reGoodsOfSellerMall; //获得商品的
	private Double chanceScore;//针对以上两个奖品获得概率
	
	private CommodityType commodityType;//游戏类型 266 翻牌子  268 代理签到
	private String coverPics;  //广告5张
	private String content; //牌子8张
	private String oneScore;//每一天的积分
	private AdminUser seller;//签到提供积分的商家
	private String detail; //说明
	private ProvinceEnum provinceEnum;//归属地区
	private AdminUser adminUser;
	public AbstractGameActivity() {
		super();
	}

	







	public AbstractGameActivity(Integer id, Timestamp createTime,
			Boolean isValid, Boolean isStart, Integer drawNum,
			Integer drawScore, Scoretypes drawYlassify, Integer score,
			ReGoodsOfSellerMall reGoodsOfSellerMall, Double chanceScore,
			CommodityType commodityType, String coverPics, String content,
			String oneScore, AdminUser seller, String detail,
			ProvinceEnum provinceEnum, AdminUser adminUser) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.isValid = isValid;
		this.isStart = isStart;
		this.drawNum = drawNum;
		this.drawScore = drawScore;
		this.drawYlassify = drawYlassify;
		this.score = score;
		this.reGoodsOfSellerMall = reGoodsOfSellerMall;
		this.chanceScore = chanceScore;
		this.commodityType = commodityType;
		this.coverPics = coverPics;
		this.content = content;
		this.oneScore = oneScore;
		this.seller = seller;
		this.detail = detail;
		this.provinceEnum = provinceEnum;
		this.adminUser = adminUser;
	}



	public AdminUser getSeller() {
		return seller;
	}

	public void setSeller(AdminUser seller) {
		this.seller = seller;
	}


	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public String getCoverPics() {
		return coverPics;
	}

	public void setCoverPics(String coverPics) {
		this.coverPics = coverPics;
	}


	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getOneScore() {
		return oneScore;
	}




	public void setOneScore(String oneScore) {
		this.oneScore = oneScore;
	}




	public CommodityType getCommodityType() {
		return commodityType;
	}


	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}


	public Boolean getIsStart() {
		return isStart;
	}
	public void setIsStart(Boolean isStart) {
		this.isStart = isStart;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Integer getDrawNum() {
		return drawNum;
	}
	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}
	public Integer getDrawScore() {
		return drawScore;
	}
	public void setDrawScore(Integer drawScore) {
		this.drawScore = drawScore;
	}
	
	
	public Scoretypes getDrawYlassify() {
		return drawYlassify;
	}
	public void setDrawYlassify(Scoretypes drawYlassify) {
		this.drawYlassify = drawYlassify;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Double getChanceScore() {
		return chanceScore;
	}
	public void setChanceScore(Double chanceScore) {
		this.chanceScore = chanceScore;
	}
	public ReGoodsOfSellerMall getReGoodsOfSellerMall() {
		return reGoodsOfSellerMall;
	}
	public void setReGoodsOfSellerMall(ReGoodsOfSellerMall reGoodsOfSellerMall) {
		this.reGoodsOfSellerMall = reGoodsOfSellerMall;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
