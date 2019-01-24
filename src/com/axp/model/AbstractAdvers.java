package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAdvers entity provides the base persistence definition of the Advers
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdvers implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private AdminUser adminUser;
	private String imges;
	private String imgesVertical;
	private Integer type;
	private Integer status;
	private String mv;
	private String location;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer category;
	private String cateType;
	private String name;
	private Integer checker;
	private String checkname;
	private String opinion;
	private Timestamp endtime;
	private String description;
	private Set machinepools = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractAdvers() {
	}

	/** full constructor */
	public AbstractAdvers(Seller seller, AdminUser adminUser, String imges,
			String imgesVertical, Integer type, Integer status, String mv,
			String location, Boolean isvalid, Timestamp createtime,
			Integer category, String cateType, String name, Integer checker,
			String checkname, String opinion, Timestamp endtime,
			String description, Set machinepools) {
		this.seller = seller;
		this.adminUser = adminUser;
		this.imges = imges;
		this.imgesVertical = imgesVertical;
		this.type = type;
		this.status = status;
		this.mv = mv;
		this.location = location;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.category = category;
		this.cateType = cateType;
		this.name = name;
		this.checker = checker;
		this.checkname = checkname;
		this.opinion = opinion;
		this.endtime = endtime;
		this.description = description;
		this.machinepools = machinepools;
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

	public String getImges() {
		return this.imges;
	}

	public void setImges(String imges) {
		this.imges = imges;
	}

	public String getImgesVertical() {
		return this.imgesVertical;
	}

	public void setImgesVertical(String imgesVertical) {
		this.imgesVertical = imgesVertical;
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

	public String getMv() {
		return this.mv;
	}

	public void setMv(String mv) {
		this.mv = mv;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCateType() {
		return this.cateType;
	}

	public void setCateType(String cateType) {
		this.cateType = cateType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getMachinepools() {
		return this.machinepools;
	}

	public void setMachinepools(Set machinepools) {
		this.machinepools = machinepools;
	}

}