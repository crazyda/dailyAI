package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * ItalkGroup entity. @author MyEclipse Persistence Tools
 */
public class ItalkGroup extends AbstractItalkGroup implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ItalkGroup() {
	}

	/** minimal constructor */
	public ItalkGroup(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public ItalkGroup(AdminUser user, Integer groupId,Integer groupType,String name, String discription, Boolean isValid, Timestamp createTime, Timestamp lastTime, Set italkGroupNotices, Set italkGroupMembers) {
		super(user,groupId, groupType,name, discription, isValid, createTime, lastTime, italkGroupNotices, italkGroupMembers);
	}

}
