package com.weixin.bean;

import com.weixin.config.WeixinConfig;

public class RefundParameter {
	//必须
	private String appid;//微信开放平台审核通过的应用APPID
	private String mch_id;//微信支付分配的商户号
	private String nonce_str;//随机字符串，不长于32位。
	private String sign;//签名
	private String transaction_id;//微信订单号（二选一）
	private String out_trade_no;//商户订单号（二选一）
	private String out_refund_no;//商户退款单号。商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	private int total_fee;//总金额。订单总金额，单位为分，只能为整数，详见支付金额
	private int refund_fee;//退款金额。退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
	private String op_user_id;//操作员。操作员帐号, 默认为商户号
	
	//选填
	private String device_info;//终端设备号
	private String refund_fee_type;//货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String refund_account;//退款资金来源，REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户,REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
	
	public RefundParameter(){
		appid = WeixinConfig.appid;
		mch_id = WeixinConfig.mch_id;
		op_user_id = WeixinConfig.mch_id;
	}
	
	/**
	 * 通过构造函数来区分 App微信支付 还是公众号支付
	 * @param weixinPublic
	 */
	public RefundParameter(boolean isPublic){
		if(isPublic){
			appid = WeixinConfig.gzh_appid;
			mch_id = WeixinConfig.gzh_mch_id;
			op_user_id = WeixinConfig.gzh_mch_id;
		}else{
			appid = WeixinConfig.appid;
			mch_id = WeixinConfig.mch_id;
			op_user_id = WeixinConfig.mch_id;
		}
	}
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	public String getRefund_account() {
		return refund_account;
	}
	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}

	
	
}