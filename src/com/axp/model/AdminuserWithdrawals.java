package com.axp.model;

import java.sql.Timestamp;

/**
 * AdminUser entity. @author MyEclipse Persistence Tools
 */
public class AdminuserWithdrawals extends AbstractAdminuserWithdrawals implements java.io.Serializable {

	public static final Integer shen_qing = 0;//申请状态（等待审核）；
    public static final Integer shen_he_tong_guo = 5;//审核通过状态；
    public static final Integer shen_he_bu_tong_guo = -1;//审核不通过状态；
    public static final Integer ti_xian_wan_cheng = 10;//提现完成状态；  //已支付
    public static final Integer  yi_zhi_fu=10; //已支付
    public static final Integer zhi_fu_cheng_gong=20;//支付成功
    public static final Integer  zhi_fu_shi_bai=30; //支付失败
    
	// Constructors
	public AdminuserWithdrawals(){
		
	}
	

	/** default constructor */
	public AdminuserWithdrawals(
			Integer id,
			Integer adminuserId,
			Integer dataId,
			Integer bankId,
			AdminUser adminUser, 
			Double money,
			Timestamp createtime,Boolean isValid,Integer state,
			Timestamp checktime, Integer checker, String remark,
			Double counterFee,
			AdminuserWithdrawalsBank adminuserWithdrawalsBank,
			AdminuserWithdrawalsData adminuserWithdrawalsData) {
		super(adminuserId, adminuserId, dataId, bankId, adminUser, money, createtime, isValid, state, checktime, checker, remark, counterFee, adminuserWithdrawalsBank, adminuserWithdrawalsData);
	}

}
