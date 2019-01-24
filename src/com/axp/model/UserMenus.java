package com.axp.model;

import java.util.Set;

/**
 * UserMenus entity. @author MyEclipse Persistence Tools
 */
public class UserMenus extends AbstractUserMenus implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserMenus() {
	}

	/** minimal constructor */
	public UserMenus(String name, Integer parentMenusId, String url, Boolean isValid) {
		super(name, parentMenusId, url, isValid);
	}

	/** full constructor */
	public UserMenus(String name, Integer parentMenusId, String describe,
			String url, Boolean isValid, Set adminUserMenuses) {
		super(name, parentMenusId, describe, url, isValid, adminUserMenuses);
	}

}
