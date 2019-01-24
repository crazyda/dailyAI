package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * ImageType entity. @author MyEclipse Persistence Tools
 */
public class ImageType extends AbstractImageType implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ImageType() {
	}

	/** full constructor */
	public ImageType(String name, Integer type, Timestamp createTime,
			Boolean isValid, Set cashshopTypes) {
		super(name, type, createTime, isValid, cashshopTypes);
	}

}
