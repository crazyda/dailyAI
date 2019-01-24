package com.axp.model;

import java.sql.Timestamp;

/**
 * BaseinfosId entity. @author MyEclipse Persistence Tools
 */
public class BaseinfosId extends AbstractBaseinfosId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public BaseinfosId() {
	}

	/** full constructor */
	public BaseinfosId(Integer id, String content, Boolean isvalid,
			Timestamp createtime) {
		super(id, content, isvalid, createtime);
	}

}
