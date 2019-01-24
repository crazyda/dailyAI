package com.axp.model;

import com.axp.util.StringUtil;

/**
 * Plays entity. @author MyEclipse Persistence Tools
 */
public class Plays extends AbstractPlays implements java.io.Serializable {
	private String typestr;
	private String statusstr;

	public String getStatusstr() {
		if (this.getStatus() != null) {
			statusstr = StringUtil.getStatus().get(this.getStatus());
		}
		return statusstr;
	}

	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}

	public String getTypestr() {
		if (this.getType() != null) {
			typestr = StringUtil.getPlays_type().get(this.getType());
		}
		return typestr;
	}

	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}

	// Constructors

}
