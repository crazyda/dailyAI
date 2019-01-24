package com.axp.model;

import java.util.Set;

/**
 * Scoretypes entity. @author MyEclipse Persistence Tools
 */
public class Scoretypes extends AbstractScoretypes implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Scoretypes() {
	}

	

	public Scoretypes(Integer id, Integer score, String name, Boolean isvalid,
			Integer type, Integer drawNum, Set userScoretypeses) {
		super(id, score, name, isvalid, type, drawNum, userScoretypeses);
		// TODO Auto-generated constructor stub
	}



	public Scoretypes(Integer score, String name, Boolean isvalid) {
		super(score, name, isvalid);
		// TODO Auto-generated constructor stub
	}

	

}
