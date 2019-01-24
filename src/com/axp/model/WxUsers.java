package com.axp.model;

import java.sql.Timestamp;

/**
 * WxUsers entity. @author MyEclipse Persistence Tools
 */
public class WxUsers extends AbstractWxUsers implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public WxUsers() {
	}

	/** full constructor */
	public WxUsers(Users users, Integer wxUserid, String wxUsername,
			String wxWeixinName, String wxUserphone, Integer wxSex,
			Timestamp wxBirthday, String wxWeixinHeadimgurl) {
		super(users, wxUserid, wxUsername, wxWeixinName, wxUserphone, wxSex,
				wxBirthday, wxWeixinHeadimgurl);
	}

}
