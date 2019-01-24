package com.axp.model;

import java.sql.Timestamp;

/**
 * StatisticsBase entity. @author MyEclipse Persistence Tools
 */
public class StatisticsBase extends AbstractStatisticsBase implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public StatisticsBase() {
	}

	/** full constructor */
	public StatisticsBase(String name, Integer score, Integer type, Timestamp createTime, Boolean isValid) {
		super(name, score, type, createTime, isValid);
	}

}
