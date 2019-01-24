package com.axp.model;

import java.sql.Timestamp;


/**
 * AbstractAdver entity provides the base persistence definition of the Adver
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSharePartner implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	private Users users;
	
	private ProvinceEnum provinceEnum;

	private boolean isValid;
	
	private Timestamp createTime;
	
	private String name;
	
	private String phone;
	
	private String message;
	
	private Integer status;
	
	private boolean isCheck;

	private Integer level;
	
	

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public Integer getLevel() {
		return level;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public Users getUsers() {
		return users;
	}

	public boolean getIsCheck() {
		return isCheck;
	}


	public void setIsCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}



	public AbstractSharePartner() {
		super();
	}

	public AbstractSharePartner(Integer id, Users users,
			ProvinceEnum provinceEnum, boolean isValid, Timestamp createTime,
			String name, String phone, String message, Integer status,
			boolean isCheck, Integer level) {
		super();
		this.id = id;
		this.users = users;
		this.provinceEnum = provinceEnum;
		this.isValid = isValid;
		this.createTime = createTime;
		this.name = name;
		this.phone = phone;
		this.message = message;
		this.status = status;
		this.isCheck = isCheck;
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	

}