package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractFreeVoucher entity provides the base persistence definition of the FreeVoucher entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFreeVoucher  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private AdminUser adminUser;
     private String name;
     private String remark;
     private Integer type;
     private Integer days;
     private Timestamp createTime;
     private Timestamp lastTime;
     private Boolean isValid;
     private Set freeVoucherRecords = new HashSet(0);
     private String typeChar;


    // Constructors

    /** default constructor */
    public AbstractFreeVoucher() {
    }

    
    /** full constructor */
    public AbstractFreeVoucher(AdminUser adminUser, String name, String remark, Integer type, Integer days, Timestamp createTime, Timestamp lastTime, Boolean isValid, Set freeVoucherRecords) {
        this.adminUser = adminUser;
        this.name = name;
        this.remark = remark;
        this.type = type;
        this.days = days;
        this.createTime = createTime;
        this.lastTime = lastTime;
        this.isValid = isValid;
        this.freeVoucherRecords = freeVoucherRecords;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public AdminUser getAdminUser() {
        return this.adminUser;
    }
    
    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDays() {
        return this.days;
    }
    
    public void setDays(Integer days) {
        this.days = days;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastTime() {
        return this.lastTime;
    }
    
    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public Boolean getIsValid() {
        return this.isValid;
    }
    
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Set getFreeVoucherRecords() {
        return this.freeVoucherRecords;
    }
    
    public void setFreeVoucherRecords(Set freeVoucherRecords) {
        this.freeVoucherRecords = freeVoucherRecords;
    }


	public String getTypeChar() {
		return typeChar;
	}


	public void setTypeChar(String typeChar) {
		this.typeChar = typeChar;
	}
   








}