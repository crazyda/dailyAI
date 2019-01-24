package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdminUser entity provides the base persistence definition of the
 * AdminUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccountManage implements java.io.Serializable {
	public Integer id;
	public Integer type;
	public Boolean isValid;
	
	public String appId;
	public String appSecret;
	public String apiKey;
	public String mchId;
	public AccountReceive accountReceive;
	public Timestamp createTime;
	public Integer payType;
	
	//支付宝
	public String AliSellerId;
	public String AliPrivateKey;
	public String AliPrivateKeyPkcs8;
	public String AliPublicKey;
	public String AliKey;
	public String AliAppId;
	
	public AbstractAccountManage() {
		super();
	}
	
	public AbstractAccountManage(Integer id, Integer type, boolean isValid, String appId, String appSecret,
			String apiKey, String mchId,AccountReceive accountReceive,Timestamp createTime,Integer payType,
			String AliSellerId,String AliPrivateKey,String AliPrivateKeyPkcs8,String AliPublicKey,String AliKey,String AliAppId) {
		super();
		this.id = id;
		this.type = type;
		this.isValid = isValid;
		this.appId = appId;
		this.appSecret = appSecret;
		this.apiKey = apiKey;
		this.mchId = mchId;
		this.accountReceive = accountReceive;
		this.createTime =createTime;
		this.payType = payType;
		this.AliAppId =AliAppId;
		this.AliKey = AliKey;
		this.AliPrivateKey = AliPrivateKey;
		this.AliPrivateKeyPkcs8 = AliPrivateKeyPkcs8;
		this.AliPublicKey = AliPublicKey;
		this.AliSellerId = AliSellerId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public AccountReceive getAccountReceive() {
		return accountReceive;
	}

	public void setAccountReceive(AccountReceive accountReceive) {
		this.accountReceive = accountReceive;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getAliSellerId() {
		return AliSellerId;
	}

	public void setAliSellerId(String aliSellerId) {
		AliSellerId = aliSellerId;
	}

	public String getAliPrivateKey() {
		return AliPrivateKey;
	}

	public void setAliPrivateKey(String aliPrivateKey) {
		AliPrivateKey = aliPrivateKey;
	}

	public String getAliPrivateKeyPkcs8() {
		return AliPrivateKeyPkcs8;
	}

	public void setAliPrivateKeyPkcs8(String aliPrivateKeyPkcs8) {
		AliPrivateKeyPkcs8 = aliPrivateKeyPkcs8;
	}

	public String getAliPublicKey() {
		return AliPublicKey;
	}

	public void setAliPublicKey(String aliPublicKey) {
		AliPublicKey = aliPublicKey;
	}

	public String getAliKey() {
		return AliKey;
	}

	public void setAliKey(String aliKey) {
		AliKey = aliKey;
	}

	public String getAliAppId() {
		return AliAppId;
	}

	public void setAliAppId(String aliAppId) {
		AliAppId = aliAppId;
	}
	
}