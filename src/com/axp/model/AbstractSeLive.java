package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractSeLive entity provides the base persistence definition of the SeLive
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSeLive implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer adminuserId;
	private Integer sellerId;
	private String livename;
	private String image;
	private String liveUri;
	private Boolean istop;
	private Timestamp begintime;
	private Timestamp endtime;
	private Boolean isvalid;
	private String remark;
	private String sellerName;
	private String sellerAddress;
	private String sellerLogo;
	private String imgRecommend;
	private AdminUser adminUser;
	private Seller seller;

	// Constructors

	/** default constructor */
	public AbstractSeLive() {
	}

	/** full constructor */
	public AbstractSeLive(Integer adminuserId, Integer sellerId,
			String livename, String image, String liveUri, Boolean istop,
			Timestamp begintime,Timestamp endtime,Boolean isvalid, String remark,
			String sellerName, String sellerAddress, String sellerLogo,
		    String imgRecommend) {
		this.adminuserId = adminuserId;
		this.sellerId = sellerId;
		this.livename = livename;
		this.image = image;
		this.liveUri = liveUri;
		this.istop = istop;
		this.begintime=begintime;
		this.endtime=endtime;
		this.isvalid = isvalid;
		this.remark = remark;
		this.sellerName = sellerName;
		this.sellerAddress = sellerAddress;
		this.sellerLogo = sellerLogo;
		this.imgRecommend = imgRecommend;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getLivename() {
		return this.livename;
	}

	public void setLivename(String livename) {
		this.livename = livename;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLiveUri() {
		return this.liveUri;
	}

	public void setLiveUri(String liveUri) {
		this.liveUri = liveUri;
	}

	public Boolean getIstop() {
		return this.istop;
	}

	public void setIstop(Boolean istop) {
		this.istop = istop;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSellerName() {
		return this.sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAddress() {
		return this.sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getSellerLogo() {
		return this.sellerLogo;
	}

	public void setSellerLogo(String sellerLogo) {
		this.sellerLogo = sellerLogo;
	}


	public String getImgRecommend() {
		return this.imgRecommend;
	}

	public void setImgRecommend(String imgRecommend) {
		this.imgRecommend = imgRecommend;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Timestamp getBegintime() {
		return begintime;
	}

	public void setBegintime(Timestamp begintime) {
		this.begintime = begintime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

}