package com.axp.model;

import java.sql.Timestamp;


public class AbstractAdminuserZoneidTaoke implements java.io.Serializable{

	private Integer id;
	private Integer zoneid;
	private Integer adminuserId;
	private String uri;
	private Timestamp createtime;
	private Boolean isValid;
	private ProvinceEnum provinceEnum;
	private AdminUser adminUser;
	private String bak_uri;
	
	
	public String getBak_uri() {
		return bak_uri;
	}



	public void setBak_uri(String bak_uri) {
		this.bak_uri = bak_uri;
	}



	public AbstractAdminuserZoneidTaoke() {
		
	}
	
	
	
	public AbstractAdminuserZoneidTaoke(Integer id, Integer zoneid, String uri,
			Timestamp createtime, Boolean isValid,ProvinceEnum provinceEnum,Integer adminuserId,AdminUser adminUser) {
		this.id=id;
		this.zoneid = zoneid;
		this.uri = uri;
		this.createtime = createtime;
		this.isValid = isValid;
		this.provinceEnum=provinceEnum;
		this.adminuserId = adminuserId;
		this.adminUser = adminUser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getZoneid() {
		return zoneid;
	}
	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
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



	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}



	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}



	public Integer getAdminuserId() {
		return adminuserId;
	}



	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}



	public AdminUser getAdminUser() {
		return adminUser;
	}



	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}



	
	
	
}
