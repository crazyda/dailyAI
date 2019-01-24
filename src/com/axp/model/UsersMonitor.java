package com.axp.model;

public class UsersMonitor extends ReBaseDomain {
	private Users user;
	private String name;//账号；
	private String pwd;//密码；
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	

}
