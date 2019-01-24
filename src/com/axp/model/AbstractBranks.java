package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractBranks implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id ;
	private String branksName ;
	private String branksImg ;
	private Integer branksId ;
	
	
	
	public AbstractBranks() {
		super();
	}
	public AbstractBranks(Integer id, String branksName, String branksImg,
			Integer branksId) {
		super();
		this.id = id;
		this.branksName = branksName;
		this.branksImg = branksImg;
		this.branksId = branksId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBranksName() {
		return branksName;
	}
	public void setBranksName(String branksName) {
		this.branksName = branksName;
	}
	public String getBranksImg() {
		return branksImg;
	}
	public void setBranksImg(String branksImg) {
		this.branksImg = branksImg;
	}
	public Integer getBranksId() {
		return branksId;
	}
	public void setBranksId(Integer branksId) {
		this.branksId = branksId;
	}
	
	
	
	
}
