package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractGoodsAuctionRecord entity provides the base persistence definition of
 * the GoodsAuctionRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsAuctionRecord implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private GoodsAuctionPlayer goodsAuctionPlayer;
	private Integer score;
	private Integer type;
	private String remark;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractGoodsAuctionRecord() {
	}

	/** full constructor */
	public AbstractGoodsAuctionRecord(GoodsAuctionPlayer goodsAuctionPlayer,
			Integer score, Integer type, String remark, Timestamp createTime,
			Boolean isValid) {
		this.goodsAuctionPlayer = goodsAuctionPlayer;
		this.score = score;
		this.type = type;
		this.remark = remark;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GoodsAuctionPlayer getGoodsAuctionPlayer() {
		return this.goodsAuctionPlayer;
	}

	public void setGoodsAuctionPlayer(GoodsAuctionPlayer goodsAuctionPlayer) {
		this.goodsAuctionPlayer = goodsAuctionPlayer;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

}