package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractWxUsers entity provides the base persistence definition of the
 * WxUsers entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractWxUsers implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer wxUserid;
	private String wxUsername;
	private String wxWeixinName;
	private String wxUserphone;
	private Integer wxSex;
	private Timestamp wxBirthday;
	private String wxWeixinHeadimgurl;

	// Constructors

	/** default constructor */
	public AbstractWxUsers() {
	}

	/** full constructor */
	public AbstractWxUsers(Users users, Integer wxUserid, String wxUsername,
			String wxWeixinName, String wxUserphone, Integer wxSex,
			Timestamp wxBirthday, String wxWeixinHeadimgurl) {
		this.users = users;
		this.wxUserid = wxUserid;
		this.wxUsername = wxUsername;
		this.wxWeixinName = wxWeixinName;
		this.wxUserphone = wxUserphone;
		this.wxSex = wxSex;
		this.wxBirthday = wxBirthday;
		this.wxWeixinHeadimgurl = wxWeixinHeadimgurl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getWxUserid() {
		return this.wxUserid;
	}

	public void setWxUserid(Integer wxUserid) {
		this.wxUserid = wxUserid;
	}

	public String getWxUsername() {
		return this.wxUsername;
	}

	public void setWxUsername(String wxUsername) {
		this.wxUsername = wxUsername;
	}

	public String getWxWeixinName() {
		return this.wxWeixinName;
	}

	public void setWxWeixinName(String wxWeixinName) {
		this.wxWeixinName = wxWeixinName;
	}

	public String getWxUserphone() {
		return this.wxUserphone;
	}

	public void setWxUserphone(String wxUserphone) {
		this.wxUserphone = wxUserphone;
	}

	public Integer getWxSex() {
		return this.wxSex;
	}

	public void setWxSex(Integer wxSex) {
		this.wxSex = wxSex;
	}

	public Timestamp getWxBirthday() {
		return this.wxBirthday;
	}

	public void setWxBirthday(Timestamp wxBirthday) {
		this.wxBirthday = wxBirthday;
	}

	public String getWxWeixinHeadimgurl() {
		return this.wxWeixinHeadimgurl;
	}

	public void setWxWeixinHeadimgurl(String wxWeixinHeadimgurl) {
		this.wxWeixinHeadimgurl = wxWeixinHeadimgurl;
	}

}