package com.axp.model;

import java.sql.Timestamp;

import com.axp.util.JsonDescript;

/**
 * AbstractPromoterIncomeRecord entity provides the base persistence definition
 * of the PromoterIncomeRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPromoterIncomeRecord implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Promoter promoter;
	private Double income;
	private Integer incomeFromProId;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private String discript;
	
	private Integer fromUserId;//来源用户id
	private String fromRealName;//来源用户昵称
	private String proPromoterUserRealName;//上级推广员的昵称
	private String sign ;//签名
	private String userImg;//用户图像
	private Integer types;
	private String userIsVaild;
	// Constructors

	/** default constructor */
	public AbstractPromoterIncomeRecord() {
	}
	/** default constructor */
	public AbstractPromoterIncomeRecord(Promoter promoter) {
		this.promoter = promoter;
	}

	/** full constructor */
	public AbstractPromoterIncomeRecord(Promoter promoter, Double income,
			Integer incomeFromProId, Timestamp createTime, Timestamp lastTime,
			Boolean isValid) {
		this.promoter = promoter;
		this.income = income;
		this.incomeFromProId = incomeFromProId;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Double getIncome() {
		return this.income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}
	@JsonDescript
	public Integer getIncomeFromProId() {
		return this.incomeFromProId;
	}

	public void setIncomeFromProId(Integer incomeFromProId) {
		this.incomeFromProId = incomeFromProId;
	}
	@JsonDescript(isShow=true,dateFormat="yyyy-MM-dd")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@JsonDescript
	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	@JsonDescript
	public Boolean getIsValid() {
		return this.isValid;
	}
	public String getDiscript() {
		return discript;
	}

	public void setDiscript(String discript) {
		this.discript = discript;
	}
	@JsonDescript
	public Promoter getPromoter() {
		return promoter;
	}


	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}


	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getFromRealName() {
		return fromRealName;
	}
	public void setFromRealName(String fromRealName) {
		this.fromRealName = fromRealName;
	}
	public String getProPromoterUserRealName() {
		return proPromoterUserRealName;
	}
	public void setProPromoterUserRealName(String proPromoterUserRealName) {
		this.proPromoterUserRealName = proPromoterUserRealName;
	}
	public Integer getTypes() {
		return types;
	}
	public void setTypes(Integer types) {
		this.types = types;
	}
	public String getSign() {
		return sign;
	}
	public String getUserIsVaild() {
		return userIsVaild;
	}
	public void setUserIsVaild(String userIsVaild) {
		this.userIsVaild = userIsVaild;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

}