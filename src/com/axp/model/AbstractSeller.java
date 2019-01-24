package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;




/**
 * AbstractSeller entity provides the base persistence definition of the Seller
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSeller implements java.io.Serializable {

	// Fields
	private ProvinceEnum provinceEnum;
	private Integer id;
	private Integer version;
	private Users users;//与商家对应的一级粉丝；
	private AdminUser adminUser;
	private Zones zones;
	private String name;
	private Shoptypes shoptypes;
	private String category;//店铺种类
	private String shopcategoryId;
	private ShopCategory shopCategory;//店铺种类实体
	private String sellerIdCard;
	private Integer verifyStatus;//开店审核资料审核状态
	private String businessLicencePic;
	private String loginname;
	private String password;
	private String websites;
	private String inviteCode;
	private String websitespc;
	private String qr;
	private String phone;
	private String voucherRemark;
	private String address;
	private String remark;
	private String contacts;
	private Boolean isvalid;
	private Boolean hasVoucher;
	private Timestamp createtime;
	private String logo;
	private Boolean istop;
	private String code;
	private Double latitude;
	private Double longitude;
	private String detailedAddress;
	private Double cashPoints = 0d;;
	private Double money = 0d;
	private Integer score = 0;
	private Integer quantity = 0;
	private Double alreadyWithdraw;// 商家已提现总额；
	private Double totalRedpaper;// 商家总发放红包金额；
	private AdminUser advertwo;
	private Integer edition;
	private Seller parentSeller;
	private Set scaninfos = new HashSet(0);
	private Set plans = new HashSet(0);
	private Set adverses = new HashSet(0);
	private Set userCashshopRecords = new HashSet(0);
	private Set cashshops = new HashSet(0);
	private Set playses = new HashSet(0);
	private Set distributions = new HashSet(0);
	private Set goodses = new HashSet(0);
	private Set shopcategroys =new HashSet(0);
	private Integer level; //粉丝评分数量

	private Float sellerRating; // 粉丝评分
	private Integer count; // 粉丝评分数量

	private Boolean openSellerMall;// 是否开启了商家商城；
	private String headImg;// 显示在商城的头像；
	private SellerMallIndexModel indexModel;// 商家商城的首页模板；
	private Integer listDisplayType;// 列表显示界面的类型；
	private Integer goodsDisplayType;// 商品显示界面的类型；
	private Set<SellerMallGoodsType> goodsTypes = new HashSet<SellerMallGoodsType>();// 此商家对应的所有一级分类；
	private Set<SellerMallDefinedAttribute> attributes = new HashSet<SellerMallDefinedAttribute>();// 此商家定义的商品属性；
	private Set<SellerMallOrder> orders = new HashSet<SellerMallOrder>();// 此商家对应的商家商城的订单对象；


	public static Integer EDITION_NEW = 1;//新版本，seller相当于店铺
	public static Integer LEVEL_MAIN = 0;//主店
	public static Integer LEVEL_BRANCH = 1;//分店
	
	private Integer integral;//商家获取到的积分 
	private Timestamp zhidingTime;
	
	// Constructors

	public Timestamp getZhidingTime() {
		return zhidingTime;
	}

	public void setZhidingTime(Timestamp zhidingTime) {
		this.zhidingTime = zhidingTime;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	/** default constructor */
	public AbstractSeller() {
	}

	/** minimal constructor */
	public AbstractSeller(AdminUser adminUser, Shoptypes shoptypes,ShopCategory shopCategory,String shopcategoryId,
			String category,
			Zones zones, String name, String websites, String voucherRemark,
			Boolean isvalid, Boolean hasVoucher, Timestamp createtime,Set shopcategroys) {
		this.adminUser = adminUser;
		this.shoptypes = shoptypes;
		this.zones = zones;
		this.voucherRemark = voucherRemark;
		this.name = name;
		this.websites = websites;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.hasVoucher = hasVoucher;
		this.category = category;
		this.shopcategoryId =shopcategoryId;
		this.shopCategory = shopCategory;
		this.shopcategroys=shopcategroys;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	/** full constructor */
	public AbstractSeller(ProvinceEnum provinceEnum, Integer id,
			Integer version, Users users, AdminUser adminUser,
			Shoptypes shoptypes, Zones zones, String name, String loginname,
			String sellerIdCard,String businessLicencePic,Integer verifyStatus,
			String password, String websites, String inviteCode,
			String websitespc, String qr, String phone, String voucherRemark,
			String address, String remark, String contacts, Boolean isvalid,
			Boolean hasVoucher, Timestamp createtime, String logo,
			Boolean istop, String code, Double latitude, Double longitude,
			String detailedAddress, Double cashPoints, Double money,
			Integer score, Integer quantity, Double alreadyWithdraw,
			Double totalRedpaper, AdminUser advertwo, Integer edition,
			Set scaninfos, Set plans, Set adverses, Set userCashshopRecords,
			Set cashshops, Set playses, Set distributions, Set goodses,
			Integer level, Float sellerRating, Integer count,
			Boolean openSellerMall, String headImg,
			SellerMallIndexModel indexModel, Integer listDisplayType,
			Integer goodsDisplayType, Set<SellerMallGoodsType> goodsTypes,
			Set<SellerMallDefinedAttribute> attributes,
			Set<SellerMallOrder> orders) {
		super();
		this.provinceEnum = provinceEnum;
		this.id = id;
		this.version = version;
		this.users = users;
		this.adminUser = adminUser;
		this.shoptypes = shoptypes;
		this.zones = zones;
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.websites = websites;
		this.inviteCode = inviteCode;
		this.websitespc = websitespc;
		this.qr = qr;
		this.phone = phone;
		this.voucherRemark = voucherRemark;
		this.address = address;
		this.remark = remark;
		this.contacts = contacts;
		this.isvalid = isvalid;
		this.hasVoucher = hasVoucher;
		this.createtime = createtime;
		this.logo = logo;
		this.istop = istop;
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.detailedAddress = detailedAddress;
		this.cashPoints = cashPoints;
		this.money = money;
		this.score = score;
		this.quantity = quantity;
		this.alreadyWithdraw = alreadyWithdraw;
		this.totalRedpaper = totalRedpaper;
		this.advertwo = advertwo;
		this.edition = edition;
		this.scaninfos = scaninfos;
		this.plans = plans;
		this.adverses = adverses;
		this.userCashshopRecords = userCashshopRecords;
		this.cashshops = cashshops;
		this.playses = playses;
		this.distributions = distributions;
		this.goodses = goodses;
		this.level = level;
		this.sellerRating = sellerRating;
		this.count = count;
		this.openSellerMall = openSellerMall;
		this.headImg = headImg;
		this.indexModel = indexModel;
		this.listDisplayType = listDisplayType;
		this.goodsDisplayType = goodsDisplayType;
		this.goodsTypes = goodsTypes;
		this.attributes = attributes;
		this.orders = orders;
		this.sellerIdCard = sellerIdCard;
		this.businessLicencePic =businessLicencePic;
		this.verifyStatus = verifyStatus;
	}


	public AbstractSeller(ProvinceEnum provinceEnum, Integer id,
			Integer version, Users users, AdminUser adminUser, Zones zones,
			String name, Shoptypes shoptypes, String category,
			String shopcategoryId, ShopCategory shopCategory,
			String sellerIdCard, Integer verifyStatus,
			String businessLicencePic, String loginname, String password,
			String websites, String inviteCode, String websitespc, String qr,
			String phone, String voucherRemark, String address, String remark,
			String contacts, Boolean isvalid, Boolean hasVoucher,
			Timestamp createtime, String logo, Boolean istop, String code,
			Double latitude, Double longitude, String detailedAddress,
			Double cashPoints, Double money, Integer score, Integer quantity,
			Double alreadyWithdraw, Double totalRedpaper, AdminUser advertwo,
			Integer edition, Seller parentSeller, Set scaninfos, Set plans,
			Set adverses, Set userCashshopRecords, Set cashshops, Set playses,
			Set distributions, Set goodses, Set shopcategroys, Integer level,
			Float sellerRating, Integer count, Boolean openSellerMall,
			String headImg, SellerMallIndexModel indexModel,
			Integer listDisplayType, Integer goodsDisplayType,
			Set<SellerMallGoodsType> goodsTypes,
			Set<SellerMallDefinedAttribute> attributes,
			Set<SellerMallOrder> orders, Integer integral, Timestamp zhidingTime) {
		super();
		this.provinceEnum = provinceEnum;
		this.id = id;
		this.version = version;
		this.users = users;
		this.adminUser = adminUser;
		this.zones = zones;
		this.name = name;
		this.shoptypes = shoptypes;
		this.category = category;
		this.shopcategoryId = shopcategoryId;
		this.shopCategory = shopCategory;
		this.sellerIdCard = sellerIdCard;
		this.verifyStatus = verifyStatus;
		this.businessLicencePic = businessLicencePic;
		this.loginname = loginname;
		this.password = password;
		this.websites = websites;
		this.inviteCode = inviteCode;
		this.websitespc = websitespc;
		this.qr = qr;
		this.phone = phone;
		this.voucherRemark = voucherRemark;
		this.address = address;
		this.remark = remark;
		this.contacts = contacts;
		this.isvalid = isvalid;
		this.hasVoucher = hasVoucher;
		this.createtime = createtime;
		this.logo = logo;
		this.istop = istop;
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.detailedAddress = detailedAddress;
		this.cashPoints = cashPoints;
		this.money = money;
		this.score = score;
		this.quantity = quantity;
		this.alreadyWithdraw = alreadyWithdraw;
		this.totalRedpaper = totalRedpaper;
		this.advertwo = advertwo;
		this.edition = edition;
		this.parentSeller = parentSeller;
		this.scaninfos = scaninfos;
		this.plans = plans;
		this.adverses = adverses;
		this.userCashshopRecords = userCashshopRecords;
		this.cashshops = cashshops;
		this.playses = playses;
		this.distributions = distributions;
		this.goodses = goodses;
		this.shopcategroys = shopcategroys;
		this.level = level;
		this.sellerRating = sellerRating;
		this.count = count;
		this.openSellerMall = openSellerMall;
		this.headImg = headImg;
		this.indexModel = indexModel;
		this.listDisplayType = listDisplayType;
		this.goodsDisplayType = goodsDisplayType;
		this.goodsTypes = goodsTypes;
		this.attributes = attributes;
		this.orders = orders;
		this.integral = integral;
		this.zhidingTime = zhidingTime;
	}

	public SellerMallIndexModel getIndexModel() {
		return indexModel;
	}

	
	public void setIndexModel(SellerMallIndexModel indexModel) {
		this.indexModel = indexModel;
	}

	public Integer getListDisplayType() {
		return listDisplayType;
	}

	public void setListDisplayType(Integer listDisplayType) {
		this.listDisplayType = listDisplayType;
	}

	public Integer getGoodsDisplayType() {
		return goodsDisplayType;
	}

	public void setGoodsDisplayType(Integer goodsDisplayType) {
		this.goodsDisplayType = goodsDisplayType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Shoptypes getShoptypes() {
		return this.shoptypes;
	}

	public void setShoptypes(Shoptypes shoptypes) {
		this.shoptypes = shoptypes;
	}

	public Zones getZones() {
		return this.zones;
	}

	public String getVoucherRemark() {
		return voucherRemark;
	}

	public void setVoucherRemark(String voucherRemark) {
		this.voucherRemark = voucherRemark;
	}

	public void setZones(Zones zones) {
		this.zones = zones;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsites() {
		return this.websites;
	}

	public void setWebsites(String websites) {
		this.websites = websites;
	}

	public String getWebsitespc() {
		return this.websitespc;
	}

	public void setWebsitespc(String websitespc) {
		this.websitespc = websitespc;
	}

	public String getQr() {
		return this.qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
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

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Boolean getIstop() {
		return this.istop;
	}

	public void setIstop(Boolean istop) {
		this.istop = istop;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getDetailedAddress() {
		return this.detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public Double getCashPoints() {
		return this.cashPoints;
	}

	public void setCashPoints(Double cashPoints) {
		this.cashPoints = cashPoints;
	}

	public Set getScaninfos() {
		return this.scaninfos;
	}

	public void setScaninfos(Set scaninfos) {
		this.scaninfos = scaninfos;
	}

	public Set getPlans() {
		return this.plans;
	}

	public void setPlans(Set plans) {
		this.plans = plans;
	}

	public Set getAdverses() {
		return this.adverses;
	}

	public void setAdverses(Set adverses) {
		this.adverses = adverses;
	}

	public Set getUserCashshopRecords() {
		return this.userCashshopRecords;
	}

	public void setUserCashshopRecords(Set userCashshopRecords) {
		this.userCashshopRecords = userCashshopRecords;
	}

	public Set getCashshops() {
		return this.cashshops;
	}

	public void setCashshops(Set cashshops) {
		this.cashshops = cashshops;
	}

	public Set getPlayses() {
		return this.playses;
	}

	public void setPlayses(Set playses) {
		this.playses = playses;
	}

	public Set getDistributions() {
		return this.distributions;
	}

	public void setDistributions(Set distributions) {
		this.distributions = distributions;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

	public Boolean getHasVoucher() {
		return hasVoucher;
	}

	public void setHasVoucher(Boolean hasVoucher) {
		this.hasVoucher = hasVoucher;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Float getSellerRating() {
		return sellerRating;
	}

	public void setSellerRating(Float sellerRating) {
		this.sellerRating = sellerRating;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getLevel() {
		return level;
	}

	public Double getAlreadyWithdraw() {
		return alreadyWithdraw;
	}

	public Set<SellerMallGoodsType> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(Set<SellerMallGoodsType> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Boolean getOpenSellerMall() {
		return openSellerMall;
	}

	public void setOpenSellerMall(Boolean openSellerMall) {
		this.openSellerMall = openSellerMall;
	}

	public Set<SellerMallDefinedAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<SellerMallDefinedAttribute> attributes) {
		this.attributes = attributes;
	}

	public Set<SellerMallOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<SellerMallOrder> orders) {
		this.orders = orders;
	}


	public void setAlreadyWithdraw(Double alreadyWithdraw) {
		this.alreadyWithdraw = alreadyWithdraw;
	}

	public Double getTotalRedpaper() {
		return totalRedpaper;
	}

	public void setTotalRedpaper(Double totalRedpaper) {
		this.totalRedpaper = totalRedpaper;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AdminUser getAdvertwo() {
		return advertwo;
	}

	public void setAdvertwo(AdminUser advertwo) {
		this.advertwo = advertwo;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public Seller getParentSeller() {
		return parentSeller;
	}

	public void setParentSeller(Seller parentSeller) {
		this.parentSeller = parentSeller;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShopcategoryId() {
		return shopcategoryId;
	}

	public void setShopcategoryId(String shopcategoryId) {
		this.shopcategoryId = shopcategoryId;
	}

	public ShopCategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public String getSellerIdCard() {
		return sellerIdCard;
	}

	public void setSellerIdCard(String sellerIdCard) {
		this.sellerIdCard = sellerIdCard;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getBusinessLicencePic() {
		return businessLicencePic;
	}

	public void setBusinessLicencePic(String businessLicencePic) {
		this.businessLicencePic = businessLicencePic;
	}

	public Set getShopcategroys() {
		return shopcategroys;
	}

	public void setShopcategroys(Set shopcategroys) {
		this.shopcategroys = shopcategroys;
	}

	
}