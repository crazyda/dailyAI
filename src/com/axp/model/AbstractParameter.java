package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractParameter entity provides the base persistence definition of the
 * Parameter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractParameter extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Parameter parameter;
	private String name;
	private Boolean isValid;
	private Timestamp createTime;
	private String description;
	private String remark;
	private Set parameters = new HashSet(0);
	private Set cashshopParameterRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractParameter() {
	}

	/** minimal constructor */
	public AbstractParameter(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractParameter(AdminUser adminUser, Parameter parameter, String name, Boolean isValid, Timestamp createTime, String description, String remark, Set parameters,
			Set cashshopParameterRecords) {
		this.adminUser = adminUser;
		this.parameter = parameter;
		this.name = name;
		this.isValid = isValid;
		this.createTime = createTime;
		this.description = description;
		this.remark = remark;
		this.parameters = parameters;
		this.cashshopParameterRecords = cashshopParameterRecords;
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

	public Parameter getParameter() {
		return this.parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getParameters() {
		return this.parameters;
	}

	public void setParameters(Set parameters) {
		this.parameters = parameters;
	}

	public Set getCashshopParameterRecords() {
		return this.cashshopParameterRecords;
	}

	public void setCashshopParameterRecords(Set cashshopParameterRecords) {
		this.cashshopParameterRecords = cashshopParameterRecords;
	}

}