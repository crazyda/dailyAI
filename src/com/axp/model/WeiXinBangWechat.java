package com.axp.model;


public class WeiXinBangWechat {

private Integer id;
	
	private Boolean isValid;
	
	private String 	mchCert;
	
	public String getMchCert() {
		return mchCert;
	}

	public void setMchCert(String mchCert) {
		this.mchCert = mchCert;
	}

	private AdminUser adminUser;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
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

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public String getXcx_AppId() {
		return xcx_AppId;
	}

	public void setXcx_AppId(String xcx_AppId) {
		this.xcx_AppId = xcx_AppId;
	}

	public String getXcx_AppSecret() {
		return xcx_AppSecret;
	}

	public void setXcx_AppSecret(String xcx_AppSecret) {
		this.xcx_AppSecret = xcx_AppSecret;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	private String appId;
	
	private String appSecret;
	
	private String encodingAesKey;
	
	private String xcx_AppId;
	
	private String xcx_AppSecret;
	
	private String mchId;
	
	private String mchKey;
}
