package com.axp.model;

/**
 * AbstractUserScoretypes entity provides the base persistence definition of the
 * UserScoretypes entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserScoretypes implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Scoretypes scoretypes;
	private Integer score;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public AbstractUserScoretypes() {
	}

	/** full constructor */
	public AbstractUserScoretypes(Users users, Scoretypes scoretypes,
			Integer score, Boolean isvalid) {
		this.users = users;
		this.scoretypes = scoretypes;
		this.score = score;
		this.isvalid = isvalid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Scoretypes getScoretypes() {
		return this.scoretypes;
	}

	public void setScoretypes(Scoretypes scoretypes) {
		this.scoretypes = scoretypes;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}