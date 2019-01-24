package com.axp.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AbstractScorerecords entity provides the base persistence definition of the
 * Scorerecords entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public abstract class AbstractJphScorerecords implements java.io.Serializable {

	// Fields

	
	public static final Integer SHIKA=16;
	
	private Integer id;
	private Users users;
	private Integer beforeScore;
	private Integer score;
	private Integer afterScore;
	private String remark;
	private Integer foreignId;
	private Integer type;
	private Scoretypes scoretypes;
	private String typeName;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer adminuserId;
	private String scoretype;
	private Timestamp validityTime;
	private Integer surplusScore;
	private String isUserTimes;

	// Constructors

	public Integer getSurplusScore() {
		return surplusScore;
	}

	public void setSurplusScore(Integer surplusScore) {
		this.surplusScore = surplusScore;
	}

	/** default constructor */
	public AbstractJphScorerecords() {
	}

	/** minimal constructor */
	public AbstractJphScorerecords(Users users, Integer score, Boolean isvalid,
			Timestamp createtime, Integer adminuserId, String scoretype) {
		this.users = users;
		this.score = score;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.adminuserId = adminuserId;
		this.scoretype = scoretype;
	}

	/** full constructor */
	public AbstractJphScorerecords(Users users, Integer beforeScore,
			Integer score, Integer afterScore, String remark,
			Integer foreignId, Integer type, Boolean isvalid,
			Timestamp createtime, Integer adminuserId, String scoretype) {
		this.users = users;
		this.beforeScore = beforeScore;
		this.score = score;
		this.afterScore = afterScore;
		this.remark = remark;
		this.foreignId = foreignId;
		this.type = type;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.adminuserId = adminuserId;
		this.scoretype = scoretype;
	}

	// Property accessors
	@JsonProperty("recordId")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Users getUsers() {
		return this.users;
	}
	
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@JsonIgnore
	public Integer getBeforeScore() {
		return this.beforeScore;
	}

	public void setBeforeScore(Integer beforeScore) {
		this.beforeScore = beforeScore;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	@JsonIgnore
	public Integer getAfterScore() {
		return this.afterScore;
	}

	public void setAfterScore(Integer afterScore) {
		this.afterScore = afterScore;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonIgnore
	public Integer getForeignId() {
		return this.foreignId;
	}

	public void setForeignId(Integer foreignId) {
		this.foreignId = foreignId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@JsonIgnore
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
	
	@JsonFormat(pattern="yyyy年MM月dd日 HH:mm:ss",timezone="GMT+8")
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@JsonIgnore
	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}
	@JsonIgnore
	public String getScoretype() {
		return this.scoretype;
	}

	public void setScoretype(String scoretype) {
		this.scoretype = scoretype;
	}
	
	@JsonIgnore
	public Scoretypes getScoretypes() {
		return scoretypes;
	}

	public void setScoretypes(Scoretypes scoretypes) {
		this.scoretypes = scoretypes;
	}

	public String getTypeName(){
		if(this.scoretypes==null){
			return this.remark;
		}else{
			return this.scoretypes.getName();
		}
		
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Timestamp getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Timestamp validityTime) {
		this.validityTime = validityTime;
	}

	public String getIsUserTimes() {
		return isUserTimes;
	}

	public void setIsUserTimes(String isUserTimes) {
		this.isUserTimes = isUserTimes;
	}
	
	

}