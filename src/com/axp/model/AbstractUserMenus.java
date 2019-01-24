package com.axp.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUserMenus entity provides the base persistence definition of the
 * UserMenus entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserMenus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer parentMenusId;
	private String describe;
	private String url;
	private Boolean isValid;
	private Set adminUserMenuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUserMenus() {
	}

	/** minimal constructor */
	public AbstractUserMenus(String name, Integer parentMenusId, String url,
			Boolean isValid) {
		this.name = name;
		this.parentMenusId = parentMenusId;
		this.url = url;
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractUserMenus(String name, Integer parentMenusId, String describe,
			String url, Boolean isValid, Set adminUserMenuses) {
		this.name = name;
		this.parentMenusId = parentMenusId;
		this.describe = describe;
		this.url = url;
		this.isValid = isValid;
		this.adminUserMenuses = adminUserMenuses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Set getAdminUserMenuses() {
		return this.adminUserMenuses;
	}

	public void setAdminUserMenuses(Set adminUserMenuses) {
		this.adminUserMenuses = adminUserMenuses;
	}

	public Integer getParentMenusId() {
		return parentMenusId;
	}

	public void setParentMenusId(Integer parentMenusId) {
		this.parentMenusId = parentMenusId;
	}

}