package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractBaseinfosId entity provides the base persistence definition of the
 * BaseinfosId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBaseinfosId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractBaseinfosId() {
	}

	/** full constructor */
	public AbstractBaseinfosId(Integer id, String content, Boolean isvalid,
			Timestamp createtime) {
		this.id = id;
		this.content = content;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractBaseinfosId))
			return false;
		AbstractBaseinfosId castOther = (AbstractBaseinfosId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getIsvalid() == castOther.getIsvalid()) || (this
						.getIsvalid() != null && castOther.getIsvalid() != null && this
						.getIsvalid().equals(castOther.getIsvalid())))
				&& ((this.getCreatetime() == castOther.getCreatetime()) || (this
						.getCreatetime() != null
						&& castOther.getCreatetime() != null && this
						.getCreatetime().equals(castOther.getCreatetime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getIsvalid() == null ? 0 : this.getIsvalid().hashCode());
		result = 37
				* result
				+ (getCreatetime() == null ? 0 : this.getCreatetime()
						.hashCode());
		return result;
	}

}