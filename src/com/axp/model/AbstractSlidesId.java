package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractSlidesId entity provides the base persistence definition of the
 * SlidesId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSlidesId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String imgurls;
	private Boolean isvalid;
	private Integer userId;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractSlidesId() {
	}

	/** full constructor */
	public AbstractSlidesId(Integer id, String imgurls, Boolean isvalid,
			Integer userId, Timestamp createtime) {
		this.id = id;
		this.imgurls = imgurls;
		this.isvalid = isvalid;
		this.userId = userId;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgurls() {
		return this.imgurls;
	}

	public void setImgurls(String imgurls) {
		this.imgurls = imgurls;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractSlidesId))
			return false;
		AbstractSlidesId castOther = (AbstractSlidesId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getImgurls() == castOther.getImgurls()) || (this
						.getImgurls() != null && castOther.getImgurls() != null && this
						.getImgurls().equals(castOther.getImgurls())))
				&& ((this.getIsvalid() == castOther.getIsvalid()) || (this
						.getIsvalid() != null && castOther.getIsvalid() != null && this
						.getIsvalid().equals(castOther.getIsvalid())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& ((this.getCreatetime() == castOther.getCreatetime()) || (this
						.getCreatetime() != null
						&& castOther.getCreatetime() != null && this
						.getCreatetime().equals(castOther.getCreatetime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getImgurls() == null ? 0 : this.getImgurls().hashCode());
		result = 37 * result
				+ (getIsvalid() == null ? 0 : this.getIsvalid().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getCreatetime() == null ? 0 : this.getCreatetime()
						.hashCode());
		return result;
	}

}