package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractOrderEvaluate entity provides the base persistence definition of the
 * OrderEvaluate entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrderEvaluate extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cashshop cashshop;
	private Users users;
	private String content;
	private String imgUrl;
	private Integer score;
	private Boolean isValid;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public AbstractOrderEvaluate() {
	}

	/** full constructor */
	public AbstractOrderEvaluate(Cashshop cashshop, Users users,
			String content, String imgUrl, Boolean isValid, Timestamp createTime,Integer score) {
		this.cashshop = cashshop;
		this.users = users;
		this.content = content;
		this.imgUrl = imgUrl;
		this.isValid = isValid;
		this.createTime = createTime;
		this.score=score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Cashshop getCashshop() {
		return cashshop;
	}

	public void setCashshop(Cashshop cashshop) {
		this.cashshop = cashshop;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}