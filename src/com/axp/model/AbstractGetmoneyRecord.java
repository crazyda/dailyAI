package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractGetmoneyRecord entity provides the base persistence definition of the
 * GetmoneyRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGetmoneyRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Promoter promoter;
	private Double money;
	private String remark;
	private Integer status;
	private Boolean isValid;
	private Timestamp createTime;
	private Users users;
	private Integer type;
	private String name;
	private String account;
	private String address;
	private Double  counterFee;
	private Timestamp checktime;
	private String batchNo; //易联支付批次号 用来查询
	private String message; //支付&查询返回描述
	private  String replyStatus; //支付&查询返回状态
	// Constructors
	private Timestamp yiLianPayTime;
	public Timestamp getYiLianPayTime() {
		return yiLianPayTime;
	}

	public void setYiLianPayTime(Timestamp yiLianPayTime) {
		this.yiLianPayTime = yiLianPayTime;
	}

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/** default constructor */
	public AbstractGetmoneyRecord() {
	}

	/** full constructor */
	public AbstractGetmoneyRecord(Promoter promoter, Double money,
			String remark, Integer status, Boolean isValid, Timestamp createTime,Timestamp checktime) {
		this.promoter = promoter;
		this.money = money;
		this.remark = remark;
		this.status = status;
		this.isValid = isValid;
		this.createTime = createTime;
		this.checktime=checktime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Promoter getPromoter() {
		return this.promoter;
	}

	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getCounterFee() {
		return counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}

	public Timestamp getChecktime() {
		return checktime;
	}

	public void setChecktime(Timestamp checktime) {
		this.checktime = checktime;
	}
	
}