package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractVoucherRecord entity provides the base persistence definition of the
 * VoucherRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractVoucherRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Voucher voucher;//代金券；
	private Users users;//前台用户；
	private Timestamp createTime;
	private Boolean isValid;
	private Double beforeScore;
	private Double score;
	private Double afterScore;

	// Constructors

	/** default constructor */
	public AbstractVoucherRecord() {
	}

	/** full constructor */
	public AbstractVoucherRecord(Voucher voucher, Users users, Timestamp createTime, Boolean isValid, Double beforeScore, Double score, Double afterScore) {
		this.voucher = voucher;
		this.users = users;
		this.createTime = createTime;
		this.isValid = isValid;
		this.beforeScore = beforeScore;
		this.score = score;
		this.afterScore = afterScore;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Voucher getVoucher() {
		return this.voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

	public Double getBeforeScore() {
		return this.beforeScore;
	}

	public void setBeforeScore(Double beforeScore) {
		this.beforeScore = beforeScore;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getAfterScore() {
		return this.afterScore;
	}

	public void setAfterScore(Double afterScore) {
		this.afterScore = afterScore;
	}

}