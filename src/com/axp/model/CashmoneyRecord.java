package com.axp.model;

import java.sql.Timestamp;


/**
 * CashmoneyRecord entity. @author MyEclipse Persistence Tools
 */
public class CashmoneyRecord extends AbstractCashmoneyRecord implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public CashmoneyRecord() {
    }

    
    /** full constructor */
    public CashmoneyRecord(Users usersByFromUsers, Users usersByUserId, Double beforeMoney, Double money, Double afterMoney, String remark, Boolean isValid, Timestamp createTime, String orderString) {
        super(usersByFromUsers, usersByUserId, beforeMoney, money, afterMoney, remark, isValid, createTime, orderString);        
    }
   
}
