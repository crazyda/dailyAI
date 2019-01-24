package com.axp.model;


/**
 * SellerMainPage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSellerMainPage implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private String beginTime;
	private String endTime;
	private String topCarouselAd;
	private String sellerView;
	private String bannerAd;
	private Boolean isValid;
	private String sellerLogo;
	private String remark;
	// Constructors

	/** default constructor */
	public AbstractSellerMainPage() {
	}
	public AbstractSellerMainPage(Integer id, Seller seller,
			String sellerView, String bannerAd, Boolean isValid,
			String sellerLogo, String remark) {
		this.id = id;
		this.seller = seller;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.topCarouselAd = topCarouselAd;
		this.sellerView = sellerView;
		this.bannerAd = bannerAd;
		this.isValid = isValid;
		this.sellerLogo = sellerLogo;
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTopCarouselAd() {
		return topCarouselAd;
	}
	public void setTopCarouselAd(String topCarouselAd) {
		this.topCarouselAd = topCarouselAd;
	}
	public String getSellerView() {
		return sellerView;
	}
	public void setSellerView(String sellerView) {
		this.sellerView = sellerView;
	}
	public String getBannerAd() {
		return bannerAd;
	}
	public void setBannerAd(String bannerAd) {
		this.bannerAd = bannerAd;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public String getSellerLogo() {
		return sellerLogo;
	}
	public void setSellerLogo(String sellerLogo) {
		this.sellerLogo = sellerLogo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}