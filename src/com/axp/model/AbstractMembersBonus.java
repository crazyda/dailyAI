package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractMembersBonus entity provides the base persistence definition of the
 * MembersBonus entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMembersBonus implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer membersBonusId;
	private MembersTypeIncome membersTypeIncome;
	private Double levelOneScale;
	private Double levelTwoScale;
	private Double levelThreeScale;
	private Boolean isValid;
	private Boolean membersBonusIsValid;
	private Timestamp createTime;
	private AdminUser provider;
	private Integer providerId;
	private Integer membersTypeIncomeId;

	// Constructors

	/** default constructor */
	public AbstractMembersBonus() {
	}

	/** minimal constructor */
	public AbstractMembersBonus(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractMembersBonus(MembersTypeIncome membersTypeIncome,
			Double levelOneScale, Double levelTwoScale, Double levelThreeScale,
			Boolean isValid, Timestamp createTime) {
		this.membersTypeIncome = membersTypeIncome;
		this.levelOneScale = levelOneScale;
		this.levelTwoScale = levelTwoScale;
		this.levelThreeScale = levelThreeScale;
		this.isValid = isValid;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MembersTypeIncome getMembersTypeIncome() {
		return this.membersTypeIncome;
	}

	public void setMembersTypeIncome(MembersTypeIncome membersTypeIncome) {
		this.membersTypeIncome = membersTypeIncome;
	}

	public Double getLevelOneScale() {
		return this.levelOneScale;
	}

	public void setLevelOneScale(Double levelOneScale) {
		this.levelOneScale = levelOneScale;
	}

	public Double getLevelTwoScale() {
		return this.levelTwoScale;
	}

	public void setLevelTwoScale(Double levelTwoScale) {
		this.levelTwoScale = levelTwoScale;
	}

	public Double getLevelThreeScale() {
		return this.levelThreeScale;
	}

	public void setLevelThreeScale(Double levelThreeScale) {
		this.levelThreeScale = levelThreeScale;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public AdminUser getProvider() {
		return provider;
	}

	public void setProvider(AdminUser provider) {
		this.provider = provider;
	}

	public Integer getMembersBonusId() {
		membersBonusId = this.id;
		return membersBonusId;
	}

	public void setMembersBonusId(Integer membersBonusId) {
		this.membersBonusId = membersBonusId;
		this.id = membersBonusId;
	}

	public Boolean getMembersBonusIsValid() {
		membersBonusIsValid = this.isValid;
		return membersBonusIsValid;
	}

	public void setMembersBonusIsValid(Boolean membersBonusIsValid) {
		this.membersBonusIsValid = membersBonusIsValid;
		this.isValid = membersBonusIsValid;
	}

	public Integer getProviderId() {
		providerId = this.provider.getId();
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getMembersTypeIncomeId() {
		membersTypeIncomeId = this.membersTypeIncome.getId();
		return membersTypeIncomeId;
	}

	public void setMembersTypeIncomeId(Integer membersTypeIncomeId) {
		this.membersTypeIncomeId = membersTypeIncomeId;
	}

}