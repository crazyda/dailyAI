package com.axp.model;

/**
 * AbstractTkPartnerSettlement entity provides the base persistence definition
 * of the TkPartnerSettlement entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTkPartnerSettlement implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer pidId;
	private Double settlementAmount;
	private Boolean isValid; 
	// Constructors

	/** default constructor */
	public AbstractTkPartnerSettlement() {
	}

	/** minimal constructor */
	public AbstractTkPartnerSettlement(Integer userId, Double settlementAmount) {
		this.userId = userId;
		this.settlementAmount = settlementAmount;
	}

	/** full constructor */
	public AbstractTkPartnerSettlement(Integer userId, Integer pidId,
			Double settlementAmount,Boolean isValid) {
		this.userId = userId;
		this.pidId = pidId;
		this.settlementAmount = settlementAmount;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPidId() {
		return this.pidId;
	}

	public void setPidId(Integer pidId) {
		this.pidId = pidId;
	}

	public Double getSettlementAmount() {
		return this.settlementAmount;
	}

	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
}