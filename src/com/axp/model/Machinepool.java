package com.axp.model;

import java.sql.Timestamp;

/**
 * Machinepool entity. @author MyEclipse Persistence Tools
 */
public class Machinepool extends AbstractMachinepool implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Machinepool() {
	}

	/** minimal constructor */
	public Machinepool(Boolean isvalid, Timestamp cretetime) {
		super(isvalid, cretetime);
	}

}
