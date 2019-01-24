package com.axp.model;

import java.sql.Timestamp;

public class AdminUserScoreRecord {

	public final static Integer RECHARGE=10;  //在线充值
	public final static Integer LARGESS=20;   //赠送
	public final static Integer VENDITION=30; //销售商品 
	public final static Integer SHIKA=40; //实卡充值
	public final static Integer QRRECEIVE=50; //扫码接收
	
	
	private AdminUser adminUser;
	private Integer afterScore;
	private Integer beforeScore;
	private String cardCode;
	private Timestamp createTime;
	private AdminUser fromAdminUser;
	private Integer id;
	private Boolean isValid;
	private ReGoodsorderItem item;
	private String remark;
	private Integer score;
	private Integer surplusScore;
	private Integer type;
	private Timestamp validityTime;
	private String isUserTimes;
	public AdminUser getAdminUser() {
		return adminUser;
	}
	public Integer getAfterScore() {
		return afterScore;
	}
	public Integer getBeforeScore() {
		return beforeScore;
	}
	public String getCardCode() {
		return cardCode;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public AdminUser getFromAdminUser() {
		return fromAdminUser;
	}
	public Integer getId() {
		return id;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public ReGoodsorderItem getItem() {
		return item;
	}
	public String getRemark() {
		return remark;
	}
	public Integer getScore() {
		return score;
	}
	public Integer getSurplusScore() {
		return surplusScore;
	}
	public Integer getType() {
		return type;
	}
	public Timestamp getValidityTime() {
		return validityTime;
	}
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	public void setAfterScore(Integer afterScore) {
		this.afterScore = afterScore;
	}
	public void setBeforeScore(Integer beforeScore) {
		this.beforeScore = beforeScore;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setFromAdminUser(AdminUser fromAdminUser) {
		this.fromAdminUser = fromAdminUser;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	} 
	public void setItem(ReGoodsorderItem item) {
		this.item = item;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public void setSurplusScore(Integer surplusScore) {
		this.surplusScore = surplusScore;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setValidityTime(Timestamp validityTime) {
		this.validityTime = validityTime;
	}
	public String getIsUserTimes() {
		return isUserTimes;
	}
	public void setIsUserTimes(String isUserTimes) {
		this.isUserTimes = isUserTimes;
	}
	
	
}
