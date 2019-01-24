package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractExtendResults entity provides the base persistence definition of the
 * ExtendResults entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractExtendResults implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer oneNum;
	private Integer twoNum;
	private Integer threeNum;
	private Integer fourNum;
	private Integer fiveNum;
	private Integer sixNum;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractExtendResults() {
	}

	/** full constructor */
	public AbstractExtendResults(Users users, Integer oneNum, Integer twoNum,
			Integer threeNum, Integer fourNum, Integer fiveNum, Integer sixNum,
			Boolean isvalid, Timestamp createtime) {
		this.users = users;
		this.oneNum = oneNum;
		this.twoNum = twoNum;
		this.threeNum = threeNum;
		this.fourNum = fourNum;
		this.fiveNum = fiveNum;
		this.sixNum = sixNum;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getOneNum() {
		return this.oneNum;
	}

	public void setOneNum(Integer oneNum) {
		this.oneNum = oneNum;
	}

	public Integer getTwoNum() {
		return this.twoNum;
	}

	public void setTwoNum(Integer twoNum) {
		this.twoNum = twoNum;
	}

	public Integer getThreeNum() {
		return this.threeNum;
	}

	public void setThreeNum(Integer threeNum) {
		this.threeNum = threeNum;
	}

	public Integer getFourNum() {
		return this.fourNum;
	}

	public void setFourNum(Integer fourNum) {
		this.fourNum = fourNum;
	}

	public Integer getFiveNum() {
		return this.fiveNum;
	}

	public void setFiveNum(Integer fiveNum) {
		this.fiveNum = fiveNum;
	}

	public Integer getSixNum() {
		return this.sixNum;
	}

	public void setSixNum(Integer sixNum) {
		this.sixNum = sixNum;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}