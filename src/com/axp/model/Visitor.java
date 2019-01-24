package com.axp.model;

import java.sql.Timestamp;

/**
 * Visitor entity. @author MyEclipse Persistence Tools
 */
public class Visitor extends AbstractVisitor implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Visitor() {
	}

	/** full constructor */
	public Visitor(String name, String password, String machineCode, String inviteCode, Integer parentId, Integer score, Timestamp createTime, Timestamp lastTime, Boolean isValid) {
		super(name, password, machineCode, inviteCode, parentId, score, createTime, lastTime, isValid);
	}

}
