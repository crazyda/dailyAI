package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractNewRedPaperSetting entity provides the base persistence definition of
 * the NewRedPaperSetting entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewRedPaperSetting implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Seller seller;
	private String name;
	private Integer rangeid;
	private Double allMoney;
	private Double allMoneyUsed;
	private Integer allNum;
	private Integer allNumColl;
	private Integer allNumUsed;
	private Integer maxPutout;
	private Integer todaySurplus;
	private Double minParvalue;
	private Double maxParvalue;
	private Integer limits;
	private Timestamp beginTime;
	private Double cyc;
	private Timestamp endTime;
	private String description;
	private String headimg;
	private String link;
	private Integer status;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private Integer dateStatus;
	private Integer zoneId;
	// Constructors

	/** default constructor */
	public AbstractNewRedPaperSetting() {
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRangeid() {
		return this.rangeid;
	}

	public void setRangeid(Integer rangeid) {
		this.rangeid = rangeid;
	}

	public Double getAllMoney() {
		return this.allMoney;
	}

	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}

	public Double getAllMoneyUsed() {
		return this.allMoneyUsed;
	}

	public void setAllMoneyUsed(Double allMoneyUsed) {
		this.allMoneyUsed = allMoneyUsed;
	}

	public Integer getAllNum() {
		return this.allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getAllNumColl() {
		return this.allNumColl;
	}

	public void setAllNumColl(Integer allNumColl) {
		this.allNumColl = allNumColl;
	}

	public Integer getAllNumUsed() {
		return this.allNumUsed;
	}

	public void setAllNumUsed(Integer allNumUsed) {
		this.allNumUsed = allNumUsed;
	}

	public Integer getMaxPutout() {
		return this.maxPutout;
	}

	public void setMaxPutout(Integer maxPutout) {
		this.maxPutout = maxPutout;
	}

	public Double getMinParvalue() {
		return this.minParvalue;
	}

	public void setMinParvalue(Double minParvalue) {
		this.minParvalue = minParvalue;
	}

	public Double getMaxParvalue() {
		return this.maxParvalue;
	}

	public void setMaxParvalue(Double maxParvalue) {
		this.maxParvalue = maxParvalue;
	}

	public Integer getLimits() {
		return this.limits;
	}

	public void setLimits(Integer limits) {
		this.limits = limits;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Double getCyc() {
		return this.cyc;
	}

	public void setCyc(Double cyc) {
		this.cyc = cyc;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeadimg() {
		return this.headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getTodaySurplus() {
		return todaySurplus;
	}

	public void setTodaySurplus(Integer todaySurplus) {
		this.todaySurplus = todaySurplus;
	}

	public Integer getDateStatus() {
		return dateStatus;
	}

	public void setDateStatus(Integer dateStatus) {
		this.dateStatus = dateStatus;
	}


	public Integer getZoneId() {
		return zoneId;
	}


	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

}