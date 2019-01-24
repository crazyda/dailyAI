package com.axp.model;

import java.sql.Timestamp;

/**
 * AdminuserRedpaperUsersReceive entity. @author MyEclipse Persistence Tools
 */
public class AdminuserRedpaperUsersReceive extends
		AbstractAdminuserRedpaperUsersReceive implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public AdminuserRedpaperUsersReceive() {
	}

	/** minimal constructor */
	public AdminuserRedpaperUsersReceive(Integer id) {
		super(id);
	}

	/** full constructor */
	public AdminuserRedpaperUsersReceive(Integer id, Users users,
			AdminuserRedpaper adminuserRedpaper, Timestamp createtime,
			Boolean isvalid, Double money, ProvinceEnum provinceEnum) {
		super(id, users, adminuserRedpaper, createtime, isvalid, money, provinceEnum);
		// TODO Auto-generated constructor stub
	}


}
