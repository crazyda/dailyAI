package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Redpaper entity. @author MyEclipse Persistence Tools
 */
public class Redpaper extends AbstractRedpaper implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Redpaper() {
	}

	/** minimal constructor */
	public Redpaper(Double probability) {
		super(probability);
	}

	/** full constructor */
	public Redpaper(Double probability, Double minMoney, Double maxMoney,
			Timestamp createTime, Timestamp lastTime, Boolean isValid,
			Integer adminUserId, String descript, String title,
			Integer getMaxTimes, Double spaceTime, Integer maxPresendNumber,
			Boolean status, Double maxPresendMoney, Set redpaperlogs) {
		super(probability, minMoney, maxMoney, createTime, lastTime, isValid,
				adminUserId, descript, title, getMaxTimes, spaceTime,
				maxPresendNumber, status, maxPresendMoney, redpaperlogs);
	}

}
