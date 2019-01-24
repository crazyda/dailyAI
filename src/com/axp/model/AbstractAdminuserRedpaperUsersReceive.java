package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdminuserRedpaperUsersReceive entity provides the base persistence
 * definition of the AdminuserRedpaperUsersReceive entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractAdminuserRedpaperUsersReceive implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private AdminuserRedpaper adminuserRedpaper;
	private Timestamp createtime;
	private Boolean isvalid;
	private Double money;
	private ProvinceEnum provinceEnum;
	// Constructors

	/** default constructor */
	public AbstractAdminuserRedpaperUsersReceive() {
	}

	/** minimal constructor */
	public AbstractAdminuserRedpaperUsersReceive(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractAdminuserRedpaperUsersReceive(Integer id, Users users,
			AdminuserRedpaper adminuserRedpaper, Timestamp createtime,
			Boolean isvalid, Double money, ProvinceEnum provinceEnum) {
		this.id = id;
		this.users = users;
		this.adminuserRedpaper = adminuserRedpaper;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.money = money;
		this.provinceEnum = provinceEnum;
	}

	// Property accessors
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public AdminuserRedpaper getAdminuserRedpaper() {
		return adminuserRedpaper;
	}

	public void setAdminuserRedpaper(AdminuserRedpaper adminuserRedpaper) {
		this.adminuserRedpaper = adminuserRedpaper;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}
 
	

	
}