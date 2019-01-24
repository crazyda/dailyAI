package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractTkldPid  implements java.io.Serializable {

	
	
	private Integer id;
	
	private String pId;
	
	private Integer level;
	
	private TkldPid tkldPid;
	
	private String ldLoginName;
	
	private String ldLoginPwd;
	
	private String ldLoginReamrk;
	
	private String remark;
	
	private Timestamp bindingTime;
	
	public Timestamp getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(Timestamp bindingTime) {
		this.bindingTime = bindingTime;
	}

	private AdminUser adminUser;

	private Users users;



	private ProvinceEnum provinceEnum;
	
	private String usersRemark;
	
	private String adminUserReamrk;
	
	private boolean isValid;
	
	private Timestamp createTime;
	
	private Integer isCareerPartner;
	
	public AbstractTkldPid() {
	}
	
	public AbstractTkldPid(Integer id, String pId, Integer level,
			TkldPid tkldPid, String ldLoginName, String ldLoginPwd,
			String ldLoginReamrk, String remark, Timestamp bindingTime,
			AdminUser adminUser, Users users, ProvinceEnum provinceEnum,
			String usersRemark, String adminUserReamrk, boolean isValid,
			Timestamp createTime) {
		super();
		this.id = id;
		this.pId = pId;
		this.level = level;
		this.tkldPid = tkldPid;
		this.ldLoginName = ldLoginName;
		this.ldLoginPwd = ldLoginPwd;
		this.ldLoginReamrk = ldLoginReamrk;
		this.remark = remark;
		this.bindingTime = bindingTime;
		this.adminUser = adminUser;
		this.users = users;
		this.provinceEnum = provinceEnum;
		this.usersRemark = usersRemark;
		this.adminUserReamrk = adminUserReamrk;
		this.isValid = isValid;
		this.createTime = createTime;
	}

	public TkldPid getTkldPid() {
		return tkldPid;
	}

	public void setTkldPid(TkldPid tkldPid) {
		this.tkldPid = tkldPid;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public String getAdminUserReamrk() {
		return adminUserReamrk;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public String getLdLoginName() {
		return ldLoginName;
	}

	public String getLdLoginPwd() {
		return ldLoginPwd;
	}

	public String getLdLoginReamrk() {
		return ldLoginReamrk;
	}

	public Integer getLevel() {
		return level;
	}


	public String getpId() {
		return pId;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public String getRemark() {
		return remark;
	}


	public Users getUsers() {
		return users;
	}

	public String getUsersRemark() {
		return usersRemark;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public void setAdminUserReamrk(String adminUserReamrk) {
		this.adminUserReamrk = adminUserReamrk;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public void setLdLoginName(String ldLoginName) {
		this.ldLoginName = ldLoginName;
	}


	public void setLdLoginPwd(String ldLoginPwd) {
		this.ldLoginPwd = ldLoginPwd;
	}

	public void setLdLoginReamrk(String ldLoginReamrk) {
		this.ldLoginReamrk = ldLoginReamrk;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}



	
	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setUsersRemark(String usersRemark) {
		this.usersRemark = usersRemark;
	}

	public Integer getIsCareerPartner() {
		return isCareerPartner;
	}

	public void setIsCareerPartner(Integer isCareerPartner) {
		this.isCareerPartner = isCareerPartner;
	}

	
}
