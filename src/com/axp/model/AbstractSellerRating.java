package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractSellerRating implements java.io.Serializable {
	private Integer id;
	private Users users;
	private Seller seller;
	private Float score;
	private Boolean isValid;
	private Timestamp createTime;

	public AbstractSellerRating() {
	}

	public AbstractSellerRating(Integer id, Users users, Seller seller,
			Float score, Boolean isValid, Timestamp createTime) {
		super();
		this.id = id;
		this.users = users;
		this.seller = seller;
		this.score = score;
		this.isValid = isValid;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
