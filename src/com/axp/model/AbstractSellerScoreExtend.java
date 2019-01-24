package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public abstract class AbstractSellerScoreExtend implements java.io.Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name; //积分券名字
	private Seller seller;//所属商家
	private Double money ;//积分券价值
	private Integer score ;//积分券积分数量
	private Integer extendNum;// 积分券数量
	private String extendImage;//积分券图片
	private Timestamp validityTime;//积分券有效时间
	private Timestamp createtime;
	private Boolean isvalid;
	private String remark;//积分券说明
	
	
	
	
	public AbstractSellerScoreExtend() {
		super();
	}




	public AbstractSellerScoreExtend(Integer id, String name, Seller seller,
			 Double money, Integer score,
			Integer extendNum, String extendImage, Timestamp validityTime,
			Timestamp createtime, Boolean isvalid, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.seller = seller;
		this.money = money;
		this.score = score;
		this.extendNum = extendNum;
		this.extendImage = extendImage;
		this.validityTime = validityTime;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.remark = remark;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Seller getSeller() {
		return seller;
	}




	public void setSeller(Seller seller) {
		this.seller = seller;
	}






	public Double getMoney() {
		return money;
	}




	public void setMoney(Double money) {
		this.money = money;
	}




	public Integer getScore() {
		return score;
	}




	public void setScore(Integer score) {
		this.score = score;
	}




	public Integer getExtendNum() {
		return extendNum;
	}




	public void setExtendNum(Integer extendNum) {
		this.extendNum = extendNum;
	}




	public String getExtendImage() {
		return extendImage;
	}




	public void setExtendImage(String extendImage) {
		this.extendImage = extendImage;
	}




	public Timestamp getValidityTime() {
		return validityTime;
	}




	public void setValidityTime(Timestamp validityTime) {
		this.validityTime = validityTime;
	}




	public Timestamp getCreatetime() {
		return createtime;
	}




	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}




	public Boolean getIsvalid() {
		return isvalid;
	}




	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
	
}