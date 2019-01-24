package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public abstract class AbstractCommissionRate implements java.io.Serializable {

	public AbstractCommissionRate() {
		
	}

	private Integer id;
	
	private Double terrace;
	
	private Double agent;
	
	private Double career;
	
	private Double career2;

	private Double partner;
	
	private Timestamp createTime;

	private boolean isValid;
	
	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Double getTerrace() {
		return terrace;
	}

	public void setTerrace(Double terrace) {
		this.terrace = terrace;
	}

	public Double getAgent() {
		return agent;
	}

	public void setAgent(Double agent) {
		this.agent = agent;
	}

	public Double getCareer() {
		return career;
	}

	public void setCareer(Double career) {
		this.career = career;
	}

	public Double getCareer2() {
		return career2;
	}

	public void setCareer2(Double career2) {
		this.career2 = career2;
	}

	public Double getPartner() {
		return partner;
	}

	public void setPartner(Double partner) {
		this.partner = partner;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}