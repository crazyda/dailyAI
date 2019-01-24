package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractPlays entity provides the base persistence definition of the Plays
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPlays implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private AdminUser adminUser;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer type;
	private Integer status;
	private String url;
	private String remark;
	private String imgUrl;
	private Integer checker;
	private String checkname;
	private String name;
	private Set playspools = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractPlays() {
	}

	/** full constructor */
	public AbstractPlays(Seller seller, AdminUser adminUser, Boolean isvalid,
			Timestamp createtime, Integer type, Integer status, String url,
			String remark, String imgUrl, Integer checker, String checkname,
			String name, Set playspools) {
		this.seller = seller;
		this.adminUser = adminUser;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.type = type;
		this.status = status;
		this.url = url;
		this.remark = remark;
		this.imgUrl = imgUrl;
		this.checker = checker;
		this.checkname = checkname;
		this.name = name;
		this.playspools = playspools;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getChecker() {
		return this.checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public String getCheckname() {
		return this.checkname;
	}

	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getPlayspools() {
		return this.playspools;
	}

	public void setPlayspools(Set playspools) {
		this.playspools = playspools;
	}

}