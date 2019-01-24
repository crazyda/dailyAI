package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 模板模块类：
 * 此类用来记录首页模板中的分类信息；
 */
public abstract class AbstractSellerMallModelModule implements
		java.io.Serializable {
	private static final long serialVersionUID = -7945054574728063961L;

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private String name; // 此分类名称；
	private String picture; // 图片地址；
	private String url; // 此分类对应的分类连接地址；

	private Set<SellerMallPicture> pictures = new HashSet<SellerMallPicture>();// 此分类中的图片；

	// getAndSet
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<SellerMallPicture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<SellerMallPicture> pictures) {
		this.pictures = pictures;
	}

}