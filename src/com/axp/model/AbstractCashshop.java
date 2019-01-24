package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCashshop entity provides the base persistence definition of the
 * Cashshop entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashshop implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private CommodityType commodityType;
	private AdminUser adminUser;
	private ProvinceEnum provinceEnum;
	private String name;
	private Boolean isValid;
	private Integer status;
	private Integer mode;
	private String checkstr;
	private Double price;
	private Double priceByReset;
	private Double priceCashReset;
	private Double priceCouponReset;
	private Integer cashpointLimitReset;
	private Integer cashpointLimit;
	private Integer mallType;
	private Integer cmCount;
	private double cmScore;
	private Double buyerScale;
	private Double priceCash;
	private Double priceCoupon;
	private String imgUrl;
	private String imgUrlRemark;
	private Integer tcount;
	private Integer surplus;
	private Timestamp createTime;
	private String description;
	private String remark;
	private Timestamp startTime;
	private Timestamp endTime;
	private String parameterId;
	private String imgUrlSmall;
	private String idS;
	private Set userCashshopRecords = new HashSet(0);
	private Double exchangeInterval;
	private Integer exchangeAmount;
	private Integer temporaryUserId;
	private String hint;
	private Integer freeVoucher;
	// Constructors

	/** default constructor */
	public AbstractCashshop() {
	}

	/** minimal constructor */
	public AbstractCashshop(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractCashshop(Seller seller, CommodityType commodityType, AdminUser adminUser, ProvinceEnum provinceEnum, String name, Boolean isValid, Double price,Integer cashpointLimit,Double priceCash,Double priceCoupon,String imgUrl, String imgUrlRemark,
			Integer tcount, Integer surplus, Timestamp createTime, String description, String remark, Timestamp startTime, Timestamp endTime, String parameterId, String imgUrlSmall,
			Set userCashshopRecords,Integer status,Integer mode,String checkstr,Integer mallType,Double priceByReset,Double priceCashReset,Double priceCouponReset,Integer cashpointLimitReset) {

		this.seller = seller;
		this.commodityType = commodityType;
		this.adminUser = adminUser;
		this.provinceEnum = provinceEnum;
		this.name = name;
		this.isValid = isValid;
		this.price = price;
		this.cashpointLimit=cashpointLimit;
		this.priceCash=priceCash;
		this.priceCoupon=priceCoupon;
		this.imgUrl = imgUrl;
		this.imgUrlRemark = imgUrlRemark;
		this.tcount = tcount;
		this.surplus = surplus;
		this.createTime = createTime;
		this.description = description;
		this.remark = remark;
		this.startTime = startTime;
		this.endTime = endTime;
		this.parameterId = parameterId;
		this.imgUrlSmall = imgUrlSmall;
		this.userCashshopRecords = userCashshopRecords;
		this.status=status;
		this.mode=mode;
		this.checkstr=checkstr;
		this.mallType=mallType;
		this.priceByReset=priceByReset;
		this.priceCashReset=priceCashReset;
		this.priceCouponReset=priceCouponReset;
		this.cashpointLimitReset=cashpointLimitReset;

	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public CommodityType getCommodityType() {
		return this.commodityType;
	}

	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public ProvinceEnum getProvinceEnum() {
		return this.provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgUrlRemark() {
		return this.imgUrlRemark;
	}

	public void setImgUrlRemark(String imgUrlRemark) {
		this.imgUrlRemark = imgUrlRemark;
	}

	public Integer getTcount() {
		return this.tcount;
	}

	public void setTcount(Integer tcount) {
		this.tcount = tcount;
	}

	public Integer getSurplus() {
		return this.surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public String getIdS() {
		return this.id.toString();
	}

	public void setIdS(String idS) {
		this.idS = idS;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getImgUrlSmall() {
		return this.imgUrlSmall;
	}

	public void setImgUrlSmall(String imgUrlSmall) {
		this.imgUrlSmall = imgUrlSmall;
	}

	public Set getUserCashshopRecords() {
		return this.userCashshopRecords;
	}

	public void setUserCashshopRecords(Set userCashshopRecords) {
		this.userCashshopRecords = userCashshopRecords;
	}

	public Double getExchangeInterval() {
		return exchangeInterval;
	}

	public void setExchangeInterval(Double exchangeInterval) {
		this.exchangeInterval = exchangeInterval;
	}

	public Integer getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Integer exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getCheckstr() {
		return checkstr;
	}

	public void setCheckstr(String checkstr) {
		this.checkstr = checkstr;
	}

	public Integer getTemporaryUserId() {
		return temporaryUserId;
	}

	public void setTemporaryUserId(Integer temporaryUserId) {
		this.temporaryUserId = temporaryUserId;
	}

	public Integer getCashpointLimit() {
		return cashpointLimit;
	}

	public void setCashpointLimit(Integer cashpointLimit) {
		this.cashpointLimit = cashpointLimit;
	}

	public Double getPriceCash() {
		return priceCash;
	}

	public void setPriceCash(Double priceCash) {
		this.priceCash = priceCash;
	}

	public Double getPriceCoupon() {
		return priceCoupon;
	}

	public void setPriceCoupon(Double priceCoupon) {
		this.priceCoupon = priceCoupon;
	}


	public Double getPriceByReset() {
		return priceByReset;
	}

	public void setPriceByReset(Double priceByReset) {
		this.priceByReset = priceByReset;
	}

	public Double getPriceCashReset() {
		return priceCashReset;
	}

	public void setPriceCashReset(Double priceCashReset) {
		this.priceCashReset = priceCashReset;
	}

	public Double getPriceCouponReset() {
		return priceCouponReset;
	}

	public void setPriceCouponReset(Double priceCouponReset) {
		this.priceCouponReset = priceCouponReset;
	}

	public Integer getCashpointLimitReset() {
		return cashpointLimitReset;
	}

	public void setCashpointLimitReset(Integer cashpointLimitReset) {
		this.cashpointLimitReset = cashpointLimitReset;
	}


	public Integer getMallType() {
		return mallType;
	}

	public void setMallType(Integer mallType) {
		this.mallType = mallType;
	}

	public Integer getCmCount() {
		return cmCount;
	}

	public void setCmCount(Integer cmCount) {
		this.cmCount = cmCount;
	}

	public double getCmScore() {
		return cmScore;
	}

	public void setCmScore(double cmScore) {
		this.cmScore = cmScore;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Integer getFreeVoucher() {
		return freeVoucher;
	}

	public void setFreeVoucher(Integer freeVoucher) {
		this.freeVoucher = freeVoucher;
	}

	public Double getBuyerScale() {
		return buyerScale;
	}

	public void setBuyerScale(Double buyerScale) {
		this.buyerScale = buyerScale;
	}

	
}