package com.weixin.bean;

import com.axp.util.CalcUtil;
import com.weixin.config.WeixinConfig;
import com.weixin.sign.MD5;

public class PayParameter {
	private String attach;
	private String body;
	private String totalPrice;
	private String orderCode;
	private String baseIp;
	private String notifyUrl;

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		Double fee = Double.parseDouble(totalPrice)*100;
		int price = fee.intValue();
		this.totalPrice = price+"";
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getBaseIp() {
		return baseIp;
	}

	public void setBaseIp(String baseIp) {
		this.baseIp = baseIp;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getNonce_str() {
		return getNonceStr(attach, body);
	}
	
	private String getNonceStr(String attach, String body) {
		return MD5.getNonceStr("attach=" + attach + "&body=" + body
				+ "&device_info=" + WeixinConfig.device_info + "&mch_id="
				+ WeixinConfig.mch_id + "&key=" + WeixinConfig.api_key);
	}
}