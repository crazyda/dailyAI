package com.axp.model;

import java.sql.Timestamp;



/**
 * Adver entity. @author MyEclipse Persistence Tools
 */
public class AdminuserTaokePid extends AbstractAdminuserTaokePid implements java.io.Serializable {

	// Constructors
	

	/** default constructor */
	public AdminuserTaokePid() {
	}

	/** full constructor */
	public AdminuserTaokePid(Integer id,String pid,String pidname,String fengpeis,
			 Integer status, Boolean isValid,Timestamp createtime,String fengpei,
			 String tkLoginLoginname,String tkLoginPassword,String tkLoginUsername) {
		super(id, pid, pidname, fengpeis, status, isValid, createtime, fengpei, tkLoginUsername, tkLoginLoginname, tkLoginPassword);
	}

}
