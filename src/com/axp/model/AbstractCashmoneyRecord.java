package com.axp.model;

import java.sql.Timestamp;


/**
 * AbstractCashmoneyRecord entity provides the base persistence definition of the CashmoneyRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashmoneyRecord  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Users usersByFromUsers;
     private Users usersByUserId;
     private Double beforeMoney;
     private Double money;
     private Double afterMoney;
     private String remark;
     private Boolean isValid;
     private Timestamp createTime;
     private String orderString;
     private Integer type;
     private Integer relateId;
     private String relateName;
     private String account;
     private String accountType;
     
     


    // Constructors

 


	/** default constructor */
    public AbstractCashmoneyRecord() {
    }

    
    /** full constructor */
    public AbstractCashmoneyRecord(Users usersByFromUsers, Users usersByUserId, Double beforeMoney, Double money, Double afterMoney, String remark, Boolean isValid, Timestamp createTime, String orderString
    								) {
        this.usersByFromUsers = usersByFromUsers;
        this.usersByUserId = usersByUserId;
        this.beforeMoney = beforeMoney;
        this.money = money;
        this.afterMoney = afterMoney;
        this.remark = remark;
        this.isValid = isValid;
        this.createTime = createTime;
        this.orderString = orderString;
        
    }

   
    // Property accessors
    
  


    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUsersByFromUsers() {
        return this.usersByFromUsers;
    }
    
    public void setUsersByFromUsers(Users usersByFromUsers) {
        this.usersByFromUsers = usersByFromUsers;
    }

    public Users getUsersByUserId() {
        return this.usersByUserId;
    }
    
    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public Double getBeforeMoney() {
        return this.beforeMoney;
    }
    
    public void setBeforeMoney(Double beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public Double getMoney() {
        return this.money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getAfterMoney() {
        return this.afterMoney;
    }
    
    public void setAfterMoney(Double afterMoney) {
        this.afterMoney = afterMoney;
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

    public String getOrderString() {
        return this.orderString;
    }
    
    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getRelateId() {
		return relateId;
	}


	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}


	public String getRelateName() {
		return relateName;
	}


	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
   








}