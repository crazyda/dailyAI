package com.axp.model;

/**
 * AbstractAdminUserMenus entity provides the base persistence definition of the
 * AdminUserMenus entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminUserMenus implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserMenus userMenus;
	private AdminUser adminUser;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractAdminUserMenus() {
	}

	/** full constructor */
	public AbstractAdminUserMenus(UserMenus userMenus, AdminUser adminUser,
			Boolean isValid) {
		this.userMenus = userMenus;
		this.adminUser = adminUser;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(UserMenus userMenus) {
		this.userMenus = userMenus;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

}