package com.axp.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractScoretypes entity provides the base persistence definition of the
 * Scoretypes entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractScoretypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer score;
	private String name;
	private Boolean isvalid;
	private Integer type;//后天奖品设置选项
	private Integer drawNum;//每天抽奖次数
	private Set userScoretypeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractScoretypes() {
	}

	/** minimal constructor */
	public AbstractScoretypes(Integer score, String name, Boolean isvalid) {
		this.score = score;
		this.name = name;
		this.isvalid = isvalid;
	}

	


	

	// Property accessors

	public AbstractScoretypes(Integer id, Integer score, String name,
			Boolean isvalid, Integer type, Integer drawNum, Set userScoretypeses) {
		super();
		this.id = id;
		this.score = score;
		this.name = name;
		this.isvalid = isvalid;
		this.type = type;
		this.drawNum = drawNum;
		this.userScoretypeses = userScoretypeses;
	}

	public Integer getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Set getUserScoretypeses() {
		return this.userScoretypeses;
	}

	public void setUserScoretypeses(Set userScoretypeses) {
		this.userScoretypeses = userScoretypeses;
	}

}