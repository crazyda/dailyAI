package com.axp.model;

import java.util.List;
import java.util.Map;

import com.axp.util.QueryModel;


/**
 * Seller entity. @author MyEclipse Persistence Tools
 */
public class Seller extends AbstractSeller implements java.io.Serializable {
    //==============商家提现========================
    private ReSellerWithdrawData withdrawData;//和商家体现相关的信息；
    public Integer withdrawStatus;//商家提现状态；

    //商家提现状态；
    public static final Integer 未确认手机 = 1;
    public static final Integer 未填写提现资料 = 2;
    public static final Integer 提现资料审核中 = 3;
    public static final Integer 提现资料审核不通过 = 4;
    public static final Integer 提现资料审核通过 = 5;
    //==============商家提现========================、

   
  //==============资料审核========================
    public static final Integer unVerify = 0;//未审核；
    public static final Integer pass = 2;
    public static final Integer unPass = -2;
    
    //============== getter and setter==================
    public ReSellerWithdrawData getWithdrawData() {
        return withdrawData;
    }

    public void setWithdrawData(ReSellerWithdrawData withdrawData) {
        this.withdrawData = withdrawData;
    }

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }
    // Constructors

    /**
     * default constructor
     */
    public Seller() {
    }
    
    	
}
