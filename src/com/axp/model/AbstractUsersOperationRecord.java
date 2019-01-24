package com.axp.model;

import java.sql.Timestamp;

import com.axp.dao.UsersDAO;
import com.axp.dao.impl.UsersDAOImpl;

/**
 * AbstractUsersOperationRecord entity provides the base persistence definition
 * of the UsersOperationRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsersOperationRecord implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Timestamp createtime;
	private String requsetUri;
	private String data;
	private Double lng;
	private Double lat;
	private String channelId;
	private String os;
	private String machineCode;
	private String appVersion;
	private Integer zoneId;
	private Integer adminuserId;

	// Constructors

	/** default constructor */
	public AbstractUsersOperationRecord() {
	}

	/** full constructor */
	public AbstractUsersOperationRecord(Integer userId, Timestamp createtime,
			String requsetUri, String data, Double lng, Double lat,
			String channelId, String os, String machineCode, String appVersion,
			Integer zoneId, Integer adminuserId) {
		this.userId = userId;
		this.createtime = createtime;
		this.requsetUri = requsetUri;
		this.data = data;
		this.lng = lng;
		this.lat = lat;
		this.channelId = channelId;
		this.os = os;
		this.machineCode = machineCode;
		this.appVersion = appVersion;
		this.zoneId = zoneId;
		this.adminuserId = adminuserId;
	}
	
	// Property accessors


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRequsetUri() {
		return this.requsetUri;
	}

	public void setRequsetUri(String requsetUri) {
		this.requsetUri = requsetUri;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getLng() {
		return this.lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getMachineCode() {
		return this.machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}


	
}