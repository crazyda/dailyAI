package com.axp.model;

import com.axp.util.StringUtil;

/**
 * Advers entity. @author MyEclipse Persistence Tools
 */
public class Advers extends AbstractAdvers implements java.io.Serializable {

	private String typename;

	// Constructors

	public String getTypename() {
		if (this.getType() != null && this.getType() > 0) {
			typename = StringUtil.getAdver_type().get(this.getType());
		}
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	/** default constructor */
	public Advers() {
	}

}
