package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractAdminUser entity provides the base persistence definition of the
 * AdminUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminuserWithdrawals implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer adminuserId;
	private Integer dataId;
	private Integer bankId;
	private AdminUser adminUser;
	private Double money;
	private Timestamp  createtime;
	private Boolean isValid;
	private Integer state;
	private Timestamp checktime;
	private Integer checker;
	private String remark;
	private Double counterFee;
	private AdminuserWithdrawalsBank adminuserWithdrawalsBank;
	private AdminuserWithdrawalsData adminuserWithdrawalsData;
	private String payName;//支付账户开户名
	private String payCode;//支付账号
	private String payAddress;//支付账号名称
	private String batchNo; //易联代付批次号  查询使用
	private String message; //易联查询代付返回状态描述
	private String replyStatus; //易联支付&&查询 应答状态
	private Timestamp yiLianPayTime;
	public Timestamp getYiLianPayTime() {
		return yiLianPayTime;
	}


	public void setYiLianPayTime(Timestamp yiLianPayTime) {
		this.yiLianPayTime = yiLianPayTime;
	}


	public String getReplyStatus() {
		return replyStatus;
	}


	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPayName() {
		return payName;
	}


	public void setPayName(String payName) {
		this.payName = payName;
	}


	public String getPayCode() {
		return payCode;
	}


	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}


	public String getPayAddress() {
		return payAddress;
	}


	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
	}


	public static final Integer shen_qing = 0;//申请状态（等待审核）；
    public static final Integer shen_he_tong_guo = 5;//审核通过状态；
    public static final Integer shen_he_bu_tong_guo = -1;//审核不通过状态；
   // public static final Integer ti_xian_wan_cheng = 10;//提现完成状态；
    public static final Integer  yi_zhi_fu=10; //已支付
    public static final Integer zhi_fu_cheng_gong=20; //支付成功
    public static final Integer  zhi_fu_shi_bai=30;   //支付失败
    /**
     * 获取状态字符串；
     *
     * @return
     */
    public String getStatusString() {
        if (state == shen_qing) {
            return "申请状态";
        } else if (state == shen_he_tong_guo) {
            return "审核通过";
        } else if (state == shen_he_bu_tong_guo) {
            return "审核不通过";
        } else if (state == yi_zhi_fu) {
            return "已支付";
        } else if (state == zhi_fu_cheng_gong) {
	        return "支付成功";
	    }else if (state == zhi_fu_shi_bai) {
		    return "支付失败";
		    }
        return "未知状态";
    }

	
	// Constructors

	/** default constructor */
	public AbstractAdminuserWithdrawals() {
	}


	/** full constructor */
	public AbstractAdminuserWithdrawals(Integer id,
			Integer adminuserId,
			Integer dataId,
			Integer bankId,
			AdminUser adminUser,
			Double money,
			Timestamp createtime, Boolean isValid, Integer state,
			Timestamp checktime, Integer checker, String remark,
			Double counterFee,
			AdminuserWithdrawalsBank adminuserWithdrawalsBank,
			AdminuserWithdrawalsData adminuserWithdrawalsData) {
		this.id = id;
		this.adminuserId=adminuserId;
		this.bankId=bankId;
		this.dataId=dataId;
		this.adminUser = adminUser;
		this.money = money;
		this.createtime = createtime;
		this.isValid = isValid;
		this.state = state;
		this.checktime = checktime;
		this.checker = checker;
		this.remark = remark;
		this.counterFee = counterFee;
		this.adminuserWithdrawalsBank=adminuserWithdrawalsBank;
		this.adminuserWithdrawalsData=adminuserWithdrawalsData;
	}
	

	// Property accessors
	public Integer getId() {
		return id;
	}

	

	public Integer getAdminuserId() {
		return adminuserId;
	}


	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}


	public Integer getDataId() {
		return dataId;
	}


	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}


	public Integer getBankId() {
		return bankId;
	}


	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}


	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getChecktime() {
		return checktime;
	}

	public void setChecktime(Timestamp checktime) {
		this.checktime = checktime;
	}

	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getCounterFee() {
		return counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}


	public AdminuserWithdrawalsBank getAdminuserWithdrawalsBank() {
		return adminuserWithdrawalsBank;
	}


	public void setAdminuserWithdrawalsBank(
			AdminuserWithdrawalsBank adminuserWithdrawalsBank) {
		this.adminuserWithdrawalsBank = adminuserWithdrawalsBank;
	}


	public AdminuserWithdrawalsData getAdminuserWithdrawalsData() {
		return adminuserWithdrawalsData;
	}


	public void setAdminuserWithdrawalsData(
			AdminuserWithdrawalsData adminuserWithdrawalsData) {
		this.adminuserWithdrawalsData = adminuserWithdrawalsData;
	}


	
	
	
}