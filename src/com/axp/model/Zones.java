package com.axp.model;

import java.sql.Timestamp;

/**
 * Zones entity. @author MyEclipse Persistence Tools
 */
public class Zones extends AbstractZones implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Zones() {
	}

	/** full constructor */
	public Zones(String name, Boolean isvalid, Timestamp createtime,
			String englishChar) {
		super(name, isvalid, createtime, englishChar);
	}

}
