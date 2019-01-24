package com.alipay.bean;

import java.io.Serializable;

import com.alipay.config.AlipayConfig;

/**
 * 支付宝支付签名参数 实体
 * 
 * @author Maijial
 * @Date 2015/12/02
 */
public class AlipayRefund implements Serializable {
	
	//必填
	private String app_id;//支付宝分配给开发者的应用ID
	private String method;//接口名称，例：alipay.trade.refund
	private String charset;//请求使用的编码格式，如utf-8,gbk,gb2312等	utf-8
	private String sign_type;//商户生成签名字符串所使用的签名算法类型，目前支持RSA	RSA
	private String version;//调用的接口版本，固定为：1.0	1.0
	
	private String sign;//商户请求参数的签名串
	private String biz_content;//请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档	
	private String timestamp;//发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"	2014-07-24 03:07:50
	private String out_trade_no;//订单支付时传入的商户订单号（二选一）
	private String trade_no;//支付宝交易号（二选一）
	private String refund_amount;//需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
	
	
	//选填
	private String format; //仅支持JSON，例：JSON
	private String refund_reason;//退款的原因说明
	private String out_request_no;//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	private String operator_id;//商户的操作员编号
	private String store_id;//商户的门店编号
	private String terminal_id;//商户的终端编号
	private String app_auth_token;//详见应用授权概述	
	
	public AlipayRefund(){
		app_id = AlipayConfig.partner;
		method = "alipay.trade.refund";
		format = "JSON";
		charset = "utf-8";
		sign_type = "RSA2"; //da
		version = "1.0";
	}
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBiz_content() {
		return biz_content;
	}
	public void setBiz_content(String biz_content) {
		this.biz_content = biz_content;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public String getOut_request_no() {
		return out_request_no;
	}
	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	public String getApp_auth_token() {
		return app_auth_token;
	}
	public void setApp_auth_token(String app_auth_token) {
		this.app_auth_token = app_auth_token;
	}

	
}
