package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractMachine entity provides the base persistence definition of the
 * Machine entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMachine implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Users users;
	private String name;
	private String address;
	private String remarks;
	private String code;
	private Double longitude;
	private Double latitude;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer showtype;
	private Set scaninfos = new HashSet(0);
	private Set machinepools = new HashSet(0);
	private Set distributions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractMachine() {
	}

	/** minimal constructor */
	public AbstractMachine(Boolean isvalid, Timestamp createtime) {
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	/** full constructor */
	public AbstractMachine(AdminUser adminUser, Users users, String name,
			String address, String remarks, String code, Double longitude,
			Double latitude, Boolean isvalid, Timestamp createtime,
			Integer showtype, Set scaninfos, Set machinepools, Set distributions) {
		this.adminUser = adminUser;
		this.users = users;
		this.name = name;
		this.address = address;
		this.remarks = remarks;
		this.code = code;
		this.longitude = longitude;
		this.latitude = latitude;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.showtype = showtype;
		this.scaninfos = scaninfos;
		this.machinepools = machinepools;
		this.distributions = distributions;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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

	public Integer getShowtype() {
		return this.showtype;
	}

	public void setShowtype(Integer showtype) {
		this.showtype = showtype;
	}

	public Set getScaninfos() {
		return this.scaninfos;
	}

	public void setScaninfos(Set scaninfos) {
		this.scaninfos = scaninfos;
	}

	public Set getMachinepools() {
		return this.machinepools;
	}

	public void setMachinepools(Set machinepools) {
		this.machinepools = machinepools;
	}

	public Set getDistributions() {
		return this.distributions;
	}

	public void setDistributions(Set distributions) {
		this.distributions = distributions;
	}

}