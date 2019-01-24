package com.axp.model;

import com.axp.util.StringUtil;

/**
 * Machine entity. @author MyEclipse Persistence Tools
 */
public class Machine extends AbstractMachine implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Machine() {
	}

	private String showtypeview;

	public String getShowtypeview() {
		showtypeview = StringUtil.getMachine_show_type()
				.get(this.getShowtype());
		return showtypeview;
	}

	public void setShowtypeview(String showtypeview) {
		this.showtypeview = showtypeview;
	}

}
