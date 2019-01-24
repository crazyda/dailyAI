package com.alipay.bean;

import java.io.Serializable;

/**
 * 支付宝支付订单参数 实体
 * @author Maijial
 * @Date 2015/12/02
 */
public class AlipayOrder implements Serializable{

	private static final long serialVersionUID = 1436947675625934287L;
	
	//订单名称
	private String subject;
	//付款金额
	private Double totalFee;
	//订单描述
	private String body;
	
	//商品展示地址 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
	private String showUrl;
	
	//服务器异步通知页面路径
	private String notifyUrl;
	//需http://格式的完整路径，不能加?id=123这类自定义参数

	//页面跳转同步通知页面路径
	private String returnUrl;
	//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	
	//默认网银
	private String defaultBank;
	
	//公用回传参数
	private String extraCommonParam;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getDefaultBank() {
		return defaultBank;
	}

	public void setDefaultBank(String defaultBank) {
		this.defaultBank = defaultBank;
	}

	public String getExtraCommonParam() {
		return extraCommonParam;
	}

	public void setExtraCommonParam(String extraCommonParam) {
		this.extraCommonParam = extraCommonParam;
	}
	
	
}
