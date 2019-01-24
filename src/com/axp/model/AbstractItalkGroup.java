package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractItalkGroup entity provides the base persistence definition of the
 * ItalkGroup entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItalkGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	//private Integer userId;
//da
	private Integer groupId;
	private Integer groupType;
	private AdminUser user;
	private String name;
	private String discription;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Set italkGroupNotices = new HashSet(0);
	private Set italkGroupMembers = new HashSet(0);
	private Integer isMyGroup;
	private String imgUrl;
	// Constructors

	/** default constructor */
	public AbstractItalkGroup() {
	}

	/** minimal constructor */
	public AbstractItalkGroup(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractItalkGroup(AdminUser user, Integer groupId,Integer groupType,String name, String discription, Boolean isValid, Timestamp createTime, Timestamp lastTime, Set italkGroupNotices, Set italkGroupMembers) {
		this.user = user;
		this.groupId = groupId;
		this.groupType = groupType;
		this.name = name;
		this.discription = discription;
		this.isValid = isValid;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.italkGroupNotices = italkGroupNotices;
		this.italkGroupMembers = italkGroupMembers;
	}

	// Property accessors
	
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

	
	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}

	
	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	

/*	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}*/

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return this.discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Set getItalkGroupNotices() {
		return this.italkGroupNotices;
	}

	public void setItalkGroupNotices(Set italkGroupNotices) {
		this.italkGroupNotices = italkGroupNotices;
	}

	public Set getItalkGroupMembers() {
		return this.italkGroupMembers;
	}

	public void setItalkGroupMembers(Set italkGroupMembers) {
		this.italkGroupMembers = italkGroupMembers;
	}

	public Integer getIsMyGroup() {
		return isMyGroup;
	}

	public void setIsMyGroup(Integer isMyGroup) {
		this.isMyGroup = isMyGroup;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}