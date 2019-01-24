package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractGoodsAuctionPlayer entity provides the base persistence definition of
 * the GoodsAuctionPlayer entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsAuctionPlayer implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private GoodsAuction goodsAuction;
	private Users users;
	private Integer margin;
	private Integer totalScore;
	private Integer auctionResult;
	private String remark;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private Integer version;
	private String name;
	private String address;
	private String phone;
	private Set goodsAuctionRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractGoodsAuctionPlayer() {
	}

	/** full constructor */
	public AbstractGoodsAuctionPlayer(GoodsAuction goodsAuction, Users users,
			Integer margin, Integer totalScore, Integer auctionResult,
			String remark, Timestamp createTime, Timestamp lastTime,
			Boolean isValid, Integer version, Set goodsAuctionRecords) {
		this.goodsAuction = goodsAuction;
		this.users = users;
		this.margin = margin;
		this.totalScore = totalScore;
		this.auctionResult = auctionResult;
		this.remark = remark;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isValid = isValid;
		this.version = version;
		this.goodsAuctionRecords = goodsAuctionRecords;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GoodsAuction getGoodsAuction() {
		return this.goodsAuction;
	}

	public void setGoodsAuction(GoodsAuction goodsAuction) {
		this.goodsAuction = goodsAuction;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getMargin() {
		return this.margin;
	}

	public void setMargin(Integer margin) {
		this.margin = margin;
	}

	public Integer getTotalScore() {
		return this.totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getAuctionResult() {
		return this.auctionResult;
	}

	public void setAuctionResult(Integer auctionResult) {
		this.auctionResult = auctionResult;
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Set getGoodsAuctionRecords() {
		return this.goodsAuctionRecords;
	}

	public void setGoodsAuctionRecords(Set goodsAuctionRecords) {
		this.goodsAuctionRecords = goodsAuctionRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}