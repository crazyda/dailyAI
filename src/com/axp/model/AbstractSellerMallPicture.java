package com.axp.model;

import java.sql.Timestamp;

/**
 * 图片类：
 * 此类用来记录图片信息，包括产品的产品的图片，介绍的图片等；
 */
public abstract class AbstractSellerMallPicture implements java.io.Serializable {
	private static final long serialVersionUID = -7945054574728063961L;

	private Integer id;
	private Boolean isValid = true;
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());

	private String picture; // 图片地址；
	private String url; // 图片对应的连接地址；
	private String intro; // 图片的文字说明；
	private Integer picIndex; // 图片排序位置；（在set中的位置）
	private Integer status; // 表示图片的状态，在获取商品图片时使用，使用状态区分图片属于什么类型的图片；1表示商品的介绍图片；2表示商品的详细介绍图片；
	private SellerMallGoods goods;// 表示此图片对应的商品；

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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getPicIndex() {
		return picIndex;
	}

	public void setPicIndex(Integer picIndex) {
		this.picIndex = picIndex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SellerMallGoods getGoods() {
		return goods;
	}

	public void setGoods(SellerMallGoods goods) {
		this.goods = goods;
	}

}