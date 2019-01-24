package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AbstractMessageCenter entity provides the base persistence definition of the
 * MessageCenter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMessageCenter implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String cover;
	private String content;
	private Timestamp createTime;
	private Boolean isValid;
	private String author;
	private String remark;
	private Integer checkStatus;
	private Integer centerStatus;
	private Integer isTimer;
	private Integer type;
	private Timestamp fixTime;
	private Set adminUserMessages = new HashSet(0);
	private AdminUser adminUser;
	
	public static final int TYPE_MESSAGE = 0;//普通消息
	public static final int TYPE_INSTALLATION = 10;//安装协议
	public static final int TYPE_STRATEGY = 20;//必看攻略
	public static final int TYPE_ABOUTUS = 30;//关于我们

	// Constructors

	/** default constructor */
	public AbstractMessageCenter() {
	}

	/** full constructor */
	public AbstractMessageCenter(String title, String cover, String content, Timestamp createTime, Boolean isValid, String author,Integer type,
					String remark,Set adminUserMessages,Integer checkStatus,Integer centerStatus,Integer isTimer,Timestamp fixTime,AdminUser adminUser) {
		this.title = title;
		this.cover = cover;
		this.content = content;
		this.createTime = createTime;
		this.isValid = isValid;
		this.author = author;
		this.remark = remark;
		this.adminUserMessages = adminUserMessages;
		this.checkStatus = checkStatus;
		this.centerStatus = centerStatus;
		this.isTimer = isTimer;
		this.fixTime = fixTime;
		this.adminUser = adminUser;
		this.type=type;
	}

	// Property accessors
	@JsonProperty("messageId")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JsonIgnore
	public Set getAdminUserMessages() {
		return adminUserMessages;
	}

	public void setAdminUserMessages(Set adminUserMessages) {
		this.adminUserMessages = adminUserMessages;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getCenterStatus() {
		return centerStatus;
	}

	public void setCenterStatus(Integer centerStatus) {
		this.centerStatus = centerStatus;
	}

	public Integer getIsTimer() {
		return isTimer;
	}

	public void setIsTimer(Integer isTimer) {
		this.isTimer = isTimer;
	}
	@JsonIgnore
	public Timestamp getFixTime() {
		return fixTime;
	}

	public void setFixTime(Timestamp fixTime) {
		this.fixTime = fixTime;
	}
	@JsonIgnore
	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	

}