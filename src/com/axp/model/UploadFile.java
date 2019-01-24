package com.axp.model;

import java.sql.Timestamp;

/**
 * UploadFile entity. @author MyEclipse Persistence Tools
 */
public class UploadFile extends AbstractUploadFile implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UploadFile() {
	}

	/** minimal constructor */
	public UploadFile(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public UploadFile(String tableName, String propertyName, String type, String url, String size, String remark, Boolean isValid, Timestamp createTime, String createUser, String lastUser,
			Timestamp lastTime) {
		super(tableName, propertyName, type, url, size, remark, isValid, createTime, createUser, lastUser, lastTime);
	}

}
