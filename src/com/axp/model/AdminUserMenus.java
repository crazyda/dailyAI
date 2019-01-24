package com.axp.model;

/**
 * AdminUserMenus entity. @author MyEclipse Persistence Tools
 */
public class AdminUserMenus extends AbstractAdminUserMenus implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AdminUserMenus() {
	}

	/** full constructor */
	public AdminUserMenus(UserMenus userMenus, AdminUser adminUser,
			Boolean isValid) {
		super(userMenus, adminUser, isValid);
	}

}
