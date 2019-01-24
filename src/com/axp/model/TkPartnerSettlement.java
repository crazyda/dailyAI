package com.axp.model;

/**
 * TkPartnerSettlement entity. @author MyEclipse Persistence Tools
 */
public class TkPartnerSettlement extends AbstractTkPartnerSettlement implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TkPartnerSettlement() {
	}

	/** minimal constructor */
	public TkPartnerSettlement(Integer userId, Double settlementAmount) {
		super(userId, settlementAmount);
	}

	/** full constructor */
	public TkPartnerSettlement(Integer userId, Integer pidId,
			Double settlementAmount,Boolean isValid) {
		super(userId, pidId, settlementAmount, isValid);
	}

}
