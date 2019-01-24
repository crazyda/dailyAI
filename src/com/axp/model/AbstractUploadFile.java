package com.axp.model;

import java.io.File;
import java.sql.Timestamp;


/**
 * AbstractUploadFile entity provides the base persistence definition of the
 * UploadFile entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUploadFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tableName;
	private String propertyName;
	private String type;
	private String url;
	private String size;
	private String remark;
	private Boolean isValid;
	private Timestamp createTime;
	private String createUser;
	private String lastUser;
	private Timestamp lastTime;
	private File file;
	private Integer relatedId;
	private String smallUrl;
	// Constructors

	/** default constructor */
	public AbstractUploadFile() {
	}

	/** minimal constructor */
	public AbstractUploadFile(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractUploadFile(String tableName, String propertyName, String type, String url, String size, String remark, Boolean isValid, Timestamp createTime, String createUser, String lastUser,
			Timestamp lastTime) {
		this.tableName = tableName;
		this.propertyName = propertyName;
		this.type = type;
		this.url = url;
		this.size = size;
		this.remark = remark;
		this.isValid = isValid;
		this.createTime = createTime;
		this.createUser = createUser;
		this.lastUser = lastUser;
		this.lastTime = lastTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastUser() {
		return this.lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public File getFile() {
		return file;
	}

	public String getSmallUrl() {
		String url = this.url;
		String imgSrc = url.substring(0,url.lastIndexOf("/"));
		String imgName = url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
		return imgSrc+"/small"+imgName+".jpg";
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public Integer getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Integer relatedId) {
		this.relatedId = relatedId;
	}

	public void setFile(File file) {
		this.file = file;
	}
}