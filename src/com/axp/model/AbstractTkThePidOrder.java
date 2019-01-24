package com.axp.model;

import java.sql.Timestamp;

import com.axp.model.ReGoodsorderItem;
import com.axp.model.Users;


public abstract class AbstractTkThePidOrder implements
		java.io.Serializable {

		private Integer id;
		
		private TkldPid tkldPid;
		
		private String orderNumber;
		
		private Double payMoney;
		
		private Double distributeMoney;
		
		private Integer orderStatus;
		
		private Timestamp orderDate;
		
		private String orderName;
		
		private String orderPic;
		
		private Integer isAmount;

		private Integer level;
		
		private String statusDesc;
		
		private String userAccount;
		
		private String userName;
		
		private String userIcon;
		
		private String goodsId;
		
		private Integer sortTime;
		
		
		
		
		public Integer getSortTime() {
			return sortTime;
		}

		public void setSortTime(Integer sortTime) {
			this.sortTime = sortTime;
		}

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public String getStatusDesc() {
			return statusDesc;
		}

		public void setStatusDesc(String statusDesc) {
			this.statusDesc = statusDesc;
		}

		public String getUserAccount() {
			return userAccount;
		}

		public void setUserAccount(String userAccount) {
			this.userAccount = userAccount;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserIcon() {
			return userIcon;
		}

		public void setUserIcon(String userIcon) {
			this.userIcon = userIcon;
		}

		
		
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public TkldPid getTkldPid() {
			return tkldPid;
		}

		public void setTkldPid(TkldPid tkldPid) {
			this.tkldPid = tkldPid;
		}

		public String getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}

		public Double getPayMoney() {
			return payMoney;
		}

		public void setPayMoney(Double payMoney) {
			this.payMoney = payMoney;
		}

		public Double getDistributeMoney() {
			return distributeMoney;
		}

		public void setDistributeMoney(Double distributeMoney) {
			this.distributeMoney = distributeMoney;
		}

		public Integer getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}

		public Timestamp getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Timestamp orderDate) {
			this.orderDate = orderDate;
		}

		public String getOrderName() {
			return orderName;
		}

		public void setOrderName(String orderName) {
			this.orderName = orderName;
		}

		public String getOrderPic() {
			return orderPic;
		}

		public void setOrderPic(String orderPic) {
			this.orderPic = orderPic;
		}

		public Integer getIsAmount() {
			return isAmount;
		}

		public void setIsAmount(Integer isAmount) {
			this.isAmount = isAmount;
		}
		
		
	

}