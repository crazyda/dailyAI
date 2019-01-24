package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractTkldPidOne  implements java.io.Serializable {

	
	
	public AbstractTkldPidOne() {
		super();
	}

	private Integer id;
	
	private String pId;
	
	private Integer level;
	
	private TkldPidOne tkldPid;
	
	private String ldLoginName;
	
	private String ldLoginPwd;
	
	private String ldLoginReamrk;
	
	private String remark;
	
	private Timestamp bindingTime;
	

	public AbstractTkldPidOne(Integer id, String pId, Integer level,
			TkldPidOne tkldPid, String ldLoginName, String ldLoginPwd,
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

	private AdminUser adminUser;

	private Users users;



	private ProvinceEnum provinceEnum;
	
	private String usersRemark;
	
	private String adminUserReamrk;
	
	private boolean isValid;
	
	private Timestamp createTime;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public TkldPidOne getTkldPid() {
		return tkldPid;
	}

	public void setTkldPid(TkldPidOne tkldPid) {
		this.tkldPid = tkldPid;
	}

	public String getLdLoginName() {
		return ldLoginName;
	}

	public void setLdLoginName(String ldLoginName) {
		this.ldLoginName = ldLoginName;
	}

	public String getLdLoginPwd() {
		return ldLoginPwd;
	}

	public void setLdLoginPwd(String ldLoginPwd) {
		this.ldLoginPwd = ldLoginPwd;
	}

	public String getLdLoginReamrk() {
		return ldLoginReamrk;
	}

	public void setLdLoginReamrk(String ldLoginReamrk) {
		this.ldLoginReamrk = ldLoginReamrk;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(Timestamp bindingTime) {
		this.bindingTime = bindingTime;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public String getUsersRemark() {
		return usersRemark;
	}

	public void setUsersRemark(String usersRemark) {
		this.usersRemark = usersRemark;
	}

	public String getAdminUserReamrk() {
		return adminUserReamrk;
	}

	public void setAdminUserReamrk(String adminUserReamrk) {
		this.adminUserReamrk = adminUserReamrk;
	}





	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	






	
}
