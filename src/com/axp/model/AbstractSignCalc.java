/**
 * 2018-10-13
 * Administrator
 */
package com.axp.model;

import java.sql.Timestamp;

/**
 * @author da
 * @data 2018-10-13上午10:09:27
 */
public abstract class AbstractSignCalc implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Users users;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp signCalcTime; //签到时间
	private String signDatil; //签到详情
	private Integer reward;//签到奖励
	
	
	
	public AbstractSignCalc() {
		super();
	}
	public AbstractSignCalc(Integer id, Users users, Boolean isValid,Timestamp createTime,
			Timestamp signCalcTime, String signDatil, Integer reward) {
		super();
		this.id = id;
		this.users = users;
		this.isValid = isValid;
		this.createTime = createTime;
		this.signCalcTime = signCalcTime;
		this.signDatil = signDatil;
		this.reward = reward;
	}
	
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getSignCalcTime() {
		return signCalcTime;
	}
	public void setSignCalcTime(Timestamp signCalcTime) {
		this.signCalcTime = signCalcTime;
	}
	public String getSignDatil() {
		return signDatil;
	}
	public void setSignDatil(String signDatil) {
		this.signDatil = signDatil;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}
	
	
	
	
}
