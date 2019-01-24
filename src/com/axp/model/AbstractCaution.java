package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractCaution implements java.io.Serializable {

	private Integer cid;
	private String phone;
	private Timestamp createtime;
	private Double exceedmoney;
	private Boolean isvalid;
	private Integer type;
	private String receiverPhone;
	
	
	public String getReceiverPhone() {
		return receiverPhone;
	}


	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public AbstractCaution(){}
	

	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Double getExceedmoney() {
		return exceedmoney;
	}
	public void setExceedmoney(Double exceedmoney) {
		this.exceedmoney = exceedmoney;
	}
	public Boolean getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
	
	
	
}
