package com.axp.model;

import java.sql.Timestamp;

/**
 * ReFeedback entity. @author MyEclipse Persistence Tools
 */

public class ReFeedback implements java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;
	private String content;
	private String images;
	private Users user;
	private String connectPhone;

	// Constructors

	public String getConnectPhone() {
		return connectPhone;
	}

	public void setConnectPhone(String connectPhone) {
		this.connectPhone = connectPhone;
	}

	/** default constructor */
	public ReFeedback() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}



}