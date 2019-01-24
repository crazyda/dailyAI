package com.axp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractGoodsAuction entity provides the base persistence definition of the
 * GoodsAuction entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsAuction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Goods goods;
	private Integer baseScore;
	private Integer unitScore;
	private Integer maxScore;
	private Integer status;
	private String remark;
	private Timestamp startTime;
	private Timestamp endTime;
	private Double cycle;
	private Timestamp createTime;
	private Boolean isValid;
	private Boolean isActive;
	private Integer numbers;
	private Integer version;

	// Constructors

	/** default constructor */
	public AbstractGoodsAuction() {
	}

	/** full constructor */
	public AbstractGoodsAuction(Goods goods, Integer baseScore,
			Integer unitScore, String remark, Timestamp startTime,
			Double cycle, Timestamp createTime, Boolean isValid) {
		this.goods = goods;
		this.baseScore = baseScore;
		this.unitScore = unitScore;
		this.remark = remark;
		this.startTime = startTime;
		this.cycle = cycle;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors
	@JSONField(name="auctionId")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getBaseScore() {
		return this.baseScore;
	}

	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}

	public Integer getUnitScore() {
		return this.unitScore;
	}

	public void setUnitScore(Integer unitScore) {
		this.unitScore = unitScore;
	}
	@JSONField(name="auctionRemark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Double getCycle() {
		return this.cycle;
	}

	public void setCycle(Double cycle) {
		this.cycle = cycle;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}