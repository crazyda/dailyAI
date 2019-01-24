package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;


/**
 * FreeVoucher entity. @author MyEclipse Persistence Tools
 */
public class FreeVoucher extends AbstractFreeVoucher implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public FreeVoucher() {
    }

    
    /** full constructor */
    public FreeVoucher(AdminUser adminUser, String name, String remark, Integer type, Integer days, Timestamp createTime, Timestamp lastTime, Boolean isValid, Set freeVoucherRecords) {
        super(adminUser, name, remark, type, days, createTime, lastTime, isValid, freeVoucherRecords);        
    }
   
}
