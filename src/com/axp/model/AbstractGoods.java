package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractGoods entity provides the base persistence definition of the Goods
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoods extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private ProvinceEnum provinceEnum;
	private Zones zones;
	private Websites websites;
	private Shoptypes shoptypes;
	private Seller seller;
	private String imgurls;
	private String name;
	private Integer needScore;
	private Double money;
	private Integer mode;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer tcount;
	private String description;
	private String remark;
	private String phone;
	private String latitude;
	private String longitude;
	private String adverImgurls;
	private Integer adverStatus;
	private Integer asort;
	private Integer status;
	private Integer adverImgurlSize;
	private String adverImgurlsSmall;
	private Integer type;
	private String outWebsite;
	private Integer playtotal;
	private Timestamp starttime;
	private Timestamp endtime;
	private String checkstr;
	private Integer surplus;
	private Set goodsAdversettingses = new HashSet(0);
	private Set orderses = new HashSet(0);
	private Set userrecores = new HashSet(0);
	private Set adverpools = new HashSet(0);
	private Set scoreExchangeses = new HashSet(0);
	private Double exchangeInterval;
	private Integer exchangeAmount;
	
	private double cash;

	// Constructors

	/** default constructor */
	public AbstractGoods() {
	}

	/** minimal constructor */
	public AbstractGoods(Websites websites, Seller seller, String imgurls, String name, Integer needScore, Double money, Boolean isvalid, Timestamp createtime, Integer tcount, String phone,
			Integer type) {
		this.websites = websites;
		this.seller = seller;
		this.imgurls = imgurls;
		this.name = name;
		this.needScore = needScore;
		this.money = money;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.tcount = tcount;
		this.phone = phone;
		this.type = type;
	}

	/** full constructor */
	public AbstractGoods(AdminUser adminUser, ProvinceEnum provinceEnum, Zones zones, Websites websites, Shoptypes shoptypes, Seller seller, String imgurls, String name, Integer needScore,
			Double money, Boolean isvalid, Timestamp createtime,Integer mode, Integer tcount, String description, String remark, String phone, String latitude, String longitude, String adverImgurls,
			Integer adverStatus, Integer asort, Integer status, Integer adverImgurlSize, String adverImgurlsSmall, Integer type, String outWebsite, Integer playtotal, Timestamp starttime,
			Timestamp endtime, String checkstr, Integer surplus, Set goodsAdversettingses, Set orderses, Set userrecores, Set adverpools, Set scoreExchangeses) {
		this.adminUser = adminUser;
		this.provinceEnum = provinceEnum;
		this.zones = zones;
		this.websites = websites;
		this.shoptypes = shoptypes;
		this.seller = seller;
		this.imgurls = imgurls;
		this.name = name;
		this.needScore = needScore;
		this.money = money;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.mode=mode;
		this.tcount = tcount;
		this.description = description;
		this.remark = remark;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.adverImgurls = adverImgurls;
		this.adverStatus = adverStatus;
		this.asort = asort;
		this.status = status;
		this.adverImgurlSize = adverImgurlSize;
		this.adverImgurlsSmall = adverImgurlsSmall;
		this.type = type;
		this.outWebsite = outWebsite;
		this.playtotal = playtotal;
		this.starttime = starttime;
		this.endtime = endtime;
		this.checkstr = checkstr;
		this.surplus = surplus;
		this.goodsAdversettingses = goodsAdversettingses;
		this.orderses = orderses;
		this.userrecores = userrecores;
		this.adverpools = adverpools;
		this.scoreExchangeses = scoreExchangeses;
	}

	// Property accessors
	@JSONField(name="goodsId")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Zones getZones() {
		return this.zones;
	}

	public void setZones(Zones zones) {
		this.zones = zones;
	}

	public Websites getWebsites() {
		return this.websites;
	}

	public void setWebsites(Websites websites) {
		this.websites = websites;
	}

	public Shoptypes getShoptypes() {
		return this.shoptypes;
	}

	public void setShoptypes(Shoptypes shoptypes) {
		this.shoptypes = shoptypes;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getImgurls() {
		return this.imgurls;
	}

	public void setImgurls(String imgurls) {
		this.imgurls = imgurls;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNeedScore() {
		return this.needScore;
	}

	public void setNeedScore(Integer needScore) {
		this.needScore = needScore;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public Integer getTcount() {
		return this.tcount;
	}

	public void setTcount(Integer tcount) {
		this.tcount = tcount;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAdverImgurls() {
		return this.adverImgurls;
	}

	public void setAdverImgurls(String adverImgurls) {
		this.adverImgurls = adverImgurls;
	}

	public Integer getAdverStatus() {
		return this.adverStatus;
	}

	public void setAdverStatus(Integer adverStatus) {
		this.adverStatus = adverStatus;
	}

	public Integer getAsort() {
		return this.asort;
	}

	public void setAsort(Integer asort) {
		this.asort = asort;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdverImgurlSize() {
		return this.adverImgurlSize;
	}

	public void setAdverImgurlSize(Integer adverImgurlSize) {
		this.adverImgurlSize = adverImgurlSize;
	}

	public String getAdverImgurlsSmall() {
		return this.adverImgurlsSmall;
	}

	public void setAdverImgurlsSmall(String adverImgurlsSmall) {
		this.adverImgurlsSmall = adverImgurlsSmall;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOutWebsite() {
		return this.outWebsite;
	}

	public void setOutWebsite(String outWebsite) {
		this.outWebsite = outWebsite;
	}

	public Integer getPlaytotal() {
		return this.playtotal;
	}

	public void setPlaytotal(Integer playtotal) {
		this.playtotal = playtotal;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getCheckstr() {
		return this.checkstr;
	}

	public void setCheckstr(String checkstr) {
		this.checkstr = checkstr;
	}

	public Integer getSurplus() {
		return this.surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public Set getGoodsAdversettingses() {
		return this.goodsAdversettingses;
	}

	public void setGoodsAdversettingses(Set goodsAdversettingses) {
		this.goodsAdversettingses = goodsAdversettingses;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

	public Set getUserrecores() {
		return this.userrecores;
	}

	public void setUserrecores(Set userrecores) {
		this.userrecores = userrecores;
	}

	public Set getAdverpools() {
		return this.adverpools;
	}

	public void setAdverpools(Set adverpools) {
		this.adverpools = adverpools;
	}

	public Set getScoreExchangeses() {
		return this.scoreExchangeses;
	}

	public void setScoreExchangeses(Set scoreExchangeses) {
		this.scoreExchangeses = scoreExchangeses;
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

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	
}