package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractNrpOrderLog implements java.io.Serializable{
	// Fields

		private Integer id;
		private NewRedPaperLog nrpl;
		private UserCashshopRecord userCR;
		private Integer status;
		private Boolean isvalid;
		private Double userMoney;
		private Timestamp createTime;
		
		private Integer relateId;
		private String relateBean;
		
		public static int STATUS_PAY = 1;	//支付
		public static int STATUS_DRAWBACK = 0;	//退款 
		
		public static String RELATEBEAN_reGoodsorderItem = "reGoodsorderItem";//子订单
		
		public AbstractNrpOrderLog() {
		}
		public AbstractNrpOrderLog(Integer id, NewRedPaperLog nrpl,
				UserCashshopRecord userCR, Integer status, Boolean isvalid,
				Double userMoney, Timestamp createTime) {
			this.id = id;
			this.nrpl = nrpl;
			this.userCR = userCR;
			this.status = status;
			this.isvalid = isvalid;
			this.userMoney = userMoney;
			this.createTime = createTime;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public NewRedPaperLog getNrpl() {
			return nrpl;
		}
		public void setNrpl(NewRedPaperLog nrpl) {
			this.nrpl = nrpl;
		}
		public UserCashshopRecord getUserCR() {
			return userCR;
		}
		public void setUserCR(UserCashshopRecord userCR) {
			this.userCR = userCR;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Boolean getIsvalid() {
			return isvalid;
		}
		public void setIsvalid(Boolean isvalid) {
			this.isvalid = isvalid;
		}
		public Double getUserMoney() {
			return userMoney;
		}
		public void setUserMoney(Double userMoney) {
			this.userMoney = userMoney;
		}
		public Timestamp getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}
		public Integer getRelateId() {
			return relateId;
		}
		public void setRelateId(Integer relateId) {
			this.relateId = relateId;
		}
		public String getRelateBean() {
			return relateBean;
		}
		public void setRelateBean(String relateBean) {
			this.relateBean = relateBean;
		}


	

		
}
