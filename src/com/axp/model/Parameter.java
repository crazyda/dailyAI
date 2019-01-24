package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.axp.util.QueryModel;

/**
 * Parameter entity. @author MyEclipse Persistence Tools
 */
public class Parameter extends AbstractParameter implements java.io.Serializable {

	// Constructors
	private Set paraChilds;

	/** default constructor */
	public Parameter() {
	}

	/** minimal constructor */
	public Parameter(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public Parameter(AdminUser adminUser, Parameter parameter, String name, Boolean isValid, Timestamp createTime, String description, String remark, Set parameters, Set cashshopParameterRecords) {
		super(adminUser, parameter, name, isValid, createTime, description, remark, parameters, cashshopParameterRecords);
	}
	
	public Set getParaChilds() {
		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		model.combPreEquals("parameter.id", this.getId(),"parameterId");
		paraChilds = new HashSet<Parameter>();
		paraChilds.addAll(dateBaseDAO.findLists(Parameter.class, model));
		return paraChilds;
	}

}
