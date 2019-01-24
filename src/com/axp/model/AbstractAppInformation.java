package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAppInformation entity provides the base persistence definition of the
 * AppInformation entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAppInformation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String appVersion;
	private String describe;
	private Float AScore;
	private Integer ADownloads;
	private Float ASize;
	private String ADirectDownload;
	private String ADirectUrl;
	private String AMarketDownload;
	private String AMarketUrl;
	private String AVersion;
	private Float IScore;
	private Integer IDownloads;
	private Float ISize;
	private String IMarketDownload;
	private String IMarketUrl;
	private String INewVersionContents;
	private Integer AppType;

	// Constructors

	/** default constructor */
	public AbstractAppInformation() {
	}

	/** minimal constructor */
	public AbstractAppInformation(Boolean isValid, String appVersion,
			String describe, Float AScore, Integer ADownloads, Float ASize,
			String ADirectDownload, String ADirectUrl, String AMarketDownload,
			String AMarketUrl, String AVersion, Float IScore,
			Integer IDownloads, Float ISize, String IMarketDownload,
			String IMarketUrl, String INewVersionContents,Integer AppType) {
		this.isValid = isValid;
		this.appVersion = appVersion;
		this.describe = describe;
		this.AScore = AScore;
		this.ADownloads = ADownloads;
		this.ASize = ASize;
		this.ADirectDownload = ADirectDownload;
		this.ADirectUrl = ADirectUrl;
		this.AMarketDownload = AMarketDownload;
		this.AMarketUrl = AMarketUrl;
		this.AVersion = AVersion;
		this.IScore = IScore;
		this.IDownloads = IDownloads;
		this.ISize = ISize;
		this.IMarketDownload = IMarketDownload;
		this.IMarketUrl = IMarketUrl;
		this.INewVersionContents = INewVersionContents;
		this.AppType = AppType;
	}

	/** full constructor */
	public AbstractAppInformation(Boolean isValid, Timestamp createTime,
			Timestamp updateTime, String appVersion, String describe,
			Float AScore, Integer ADownloads, Float ASize,
			String ADirectDownload, String ADirectUrl, String AMarketDownload,
			String AMarketUrl, String AVersion, Float IScore,
			Integer IDownloads, Float ISize, String IMarketDownload,
			String IMarketUrl, String INewVersionContents,Integer AppType) {
		this.isValid = isValid;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.appVersion = appVersion;
		this.describe = describe;
		this.AScore = AScore;
		this.ADownloads = ADownloads;
		this.ASize = ASize;
		this.ADirectDownload = ADirectDownload;
		this.ADirectUrl = ADirectUrl;
		this.AMarketDownload = AMarketDownload;
		this.AMarketUrl = AMarketUrl;
		this.AVersion = AVersion;
		this.IScore = IScore;
		this.IDownloads = IDownloads;
		this.ISize = ISize;
		this.IMarketDownload = IMarketDownload;
		this.IMarketUrl = IMarketUrl;
		this.INewVersionContents = INewVersionContents;
		this.AppType = AppType;
	
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getAppType() {
		return AppType;
	}

	public void setAppType(Integer appType) {
		AppType = appType;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Float getAScore() {
		return this.AScore;
	}

	public void setAScore(Float AScore) {
		this.AScore = AScore;
	}

	public Integer getADownloads() {
		return this.ADownloads;
	}

	public void setADownloads(Integer ADownloads) {
		this.ADownloads = ADownloads;
	}

	public Float getASize() {
		return this.ASize;
	}

	public void setASize(Float ASize) {
		this.ASize = ASize;
	}

	public String getADirectDownload() {
		return this.ADirectDownload;
	}

	public void setADirectDownload(String ADirectDownload) {
		this.ADirectDownload = ADirectDownload;
	}

	public String getADirectUrl() {
		return this.ADirectUrl;
	}

	public void setADirectUrl(String ADirectUrl) {
		this.ADirectUrl = ADirectUrl;
	}

	public String getAMarketDownload() {
		return this.AMarketDownload;
	}

	public void setAMarketDownload(String AMarketDownload) {
		this.AMarketDownload = AMarketDownload;
	}

	public String getAMarketUrl() {
		return this.AMarketUrl;
	}

	public void setAMarketUrl(String AMarketUrl) {
		this.AMarketUrl = AMarketUrl;
	}

	public String getAVersion() {
		return this.AVersion;
	}

	public void setAVersion(String AVersion) {
		this.AVersion = AVersion;
	}

	public Float getIScore() {
		return this.IScore;
	}

	public void setIScore(Float IScore) {
		this.IScore = IScore;
	}

	public Integer getIDownloads() {
		return this.IDownloads;
	}

	public void setIDownloads(Integer IDownloads) {
		this.IDownloads = IDownloads;
	}

	public Float getISize() {
		return this.ISize;
	}

	public void setISize(Float ISize) {
		this.ISize = ISize;
	}

	public String getIMarketDownload() {
		return this.IMarketDownload;
	}

	public void setIMarketDownload(String IMarketDownload) {
		this.IMarketDownload = IMarketDownload;
	}

	public String getIMarketUrl() {
		return this.IMarketUrl;
	}

	public void setIMarketUrl(String IMarketUrl) {
		this.IMarketUrl = IMarketUrl;
	}

	public String getINewVersionContents() {
		return this.INewVersionContents;
	}

	public void setINewVersionContents(String INewVersionContents) {
		this.INewVersionContents = INewVersionContents;
	}

}