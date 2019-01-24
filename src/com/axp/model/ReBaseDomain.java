package com.axp.model;

import java.sql.Timestamp;

/**
 * 重构的基础类，包含了每个类都应该有的三个基础属性；
 * @author Administrator
 *
 */
public class ReBaseDomain {

	private Integer id;//主键值；
	private Boolean isValid = true;//是否可用；
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());//创建时间；

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

}
