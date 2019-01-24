package com.axp.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * AbstractAdver entity provides the base persistence definition of the Adver
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminuserTaokePid implements java.io.Serializable {

	// Fields

	public String fengpeis;
	public String fengpei;
	private Integer id;
	private String pid;
	private String pidname;
	private Integer status;
	private Boolean isValid;
	private Timestamp createtime;
	private String tkLoginLoginname;
	private String tkLoginPassword;
	private String tkLoginUsername;
	// Constructors

	/** default constructor */
	public AbstractAdminuserTaokePid() {
	}

	/** full constructor */
	public AbstractAdminuserTaokePid( Integer id,String pid,String pidname,String fengpeis,
			 Integer status, Boolean isValid,Timestamp createtime,String fengpei,
			 String tkLoginLoginname,String tkLoginPassword,String tkLoginUsername) {
		this.id=id;
		this.status = status;
		this.isValid = isValid;
		this.createtime = createtime;
		this.pid=pid;
		this.pidname=pidname;
		this.tkLoginLoginname=tkLoginLoginname;
		this.tkLoginPassword=tkLoginPassword;
		this.tkLoginUsername=tkLoginUsername;
		this.fengpei=fengpei;
		this.fengpeis=fengpeis;
	}

	// Property accessors

	
	public String getFengpei() {
		return fengpei;
	}

	public String getFengpeis() {
		return fengpeis;
	}

	public void setFengpeis(String fengpeis) {
		this.fengpeis = fengpeis;
	}

	public void setFengpei(String fengpei) {
		this.fengpei = fengpei;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPidname() {
		return pidname;
	}

	public void setPidname(String pidname) {
		this.pidname = pidname;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getTkLoginLoginname() {
		return tkLoginLoginname;
	}

	public void setTkLoginLoginname(String tkLoginLoginname) {
		this.tkLoginLoginname = tkLoginLoginname;
	}

	public String getTkLoginPassword() {
		return tkLoginPassword;
	}

	public void setTkLoginPassword(String tkLoginPassword) {
		this.tkLoginPassword = tkLoginPassword;
	}

	public String getTkLoginUsername() {
		return tkLoginUsername;
	}

	public void setTkLoginUsername(String tkLoginUsername) {
		this.tkLoginUsername = tkLoginUsername;
	}



	
}