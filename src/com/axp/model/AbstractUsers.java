package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUsers entity provides the base persistence definition of the Users
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsers implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Seller parentSeller;
	private Levels levels;
	private String name;
	private String phone;
	private String pwd;
	private Boolean isvalid;
	private Boolean isVisitor;
	private Timestamp createtime;
	private Timestamp lasttime;
	private String address;
	private Integer score;
	private String invitecode;
	private Double savemoney;
	private String realname;
	private Timestamp birthday;
	private Integer sex;
	private Double money;
	private String lat;
	private String lng;
	private Integer proxyzoneId;
	private Integer proxyId;
	private String mycode;
	private Integer level;
	private Boolean islogin;
	private String channelid;
	private String userid;
	private String ccpsubaccountid;
	private String ccpsubaccountpwd;
	private String ccpvoipaccountid;
	private String ccpvoipaccountpwd;
	private String ccpcallsid;
	private Double cashPoints;
	private String headimage;
	private String imgUrl;
	private Integer visitorId;
	private String sign;
	private Integer version;
	private Double redPaperSum;
	
	//da
	private Integer jphScore;
	private String unionId  ; 
	
	
	private Set messagesesForFromUserId = new HashSet(0);
	private Set goodsAdversettingses = new HashSet(0);
	private Set userrecores = new HashSet(0);
	private Set wxUserses = new HashSet(0);
	private Set feeRecordsesForUserId = new HashSet(0);
	private Set messagesesForUserId = new HashSet(0);
	private Set scoreExchangeses = new HashSet(0);
	private Set inviteRecordsesForUserId = new HashSet(0);
	private Set voucherRecords = new HashSet(0);
	private Set cashpointsRecords = new HashSet(0);
	private Set proxyinfoses = new HashSet(0);
	private Set extendResultses = new HashSet(0);
	private Set applies = new HashSet(0);
	private Set userProfitses = new HashSet(0);
	private Set orderses = new HashSet(0);
	private Set scorerecordses = new HashSet(0);
	private Set inviteRecordsesForInviteUserId = new HashSet(0);
	private Set userDrawCashRecords = new HashSet(0);
	private Set feeRecordsesForOpUserId = new HashSet(0);
	private Set plans = new HashSet(0);
	private Set proxyuserses = new HashSet(0);
	private Set userSettingses = new HashSet(0);
	private Set userCashshopRecords = new HashSet(0);
	private Set machines = new HashSet(0);
	private Set sellers = new HashSet(0);
	private Set userScoretypeses = new HashSet(0);
	private Set promoters = new HashSet(0);
	private String devicetoken;
	
	
	
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getJphScore() {
		return jphScore;
	}

	public void setJphScore(Integer jphScore) {
		this.jphScore = jphScore;
	}

	public String getDevicetoken() {
		return devicetoken;
	}

	public void setDevicetoken(String devicetoken) {
		this.devicetoken = devicetoken;
	}

	private OrderMessageList ordermessageLists;
	private String openId;//第三方登录用于区分唯一用户的id值；

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	private Set<SellerMallShoppingCar> sellerMallShoppingCar = new HashSet<SellerMallShoppingCar>();// 该用户对应的商家商城中的购物车对象；
	private Integer sellerId;// 此用户对应的商家
	private Boolean isSeller;// 此用户是否是商家；

	// Constructors

	/** default constructor */
	public AbstractUsers() {
	}

	/** minimal constructor */
	public AbstractUsers(String name, String pwd, Boolean isvalid, Timestamp createtime, Integer score,
			String invitecode) {
		this.name = name;
		this.pwd = pwd;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.score = score;
		this.invitecode = invitecode;
	}

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** full constructor */
/*	public AbstractUsers(AdminUser adminUser, Levels levels, String name, String phone, String pwd, Boolean isvalid,
			Boolean isVisitor, Timestamp createtime, Timestamp lasttime, String address, Integer score,
			String invitecode, Double savemoney, String realname, Timestamp birthday, Integer sex, Double money,
			String lat, String lng, Integer proxyzoneId, Integer proxyId, String mycode, Integer level,
			Boolean islogin, String channelid, String userid, String ccpsubaccountid, String ccpsubaccountpwd,
			String ccpvoipaccountid, String ccpvoipaccountpwd, String ccpcallsid, Double cashPoints, String sign,
			Set messagesesForFromUserId, Set goodsAdversettingses, Set userrecores, Set wxUserses,
			Set feeRecordsesForUserId, Set messagesesForUserId, Set scoreExchangeses, Set inviteRecordsesForUserId,
			Set voucherRecords, Set cashpointsRecords, Set proxyinfoses, Set extendResultses, Set applies,
			Set userProfitses, Set orderses, Set scorerecordses, Set inviteRecordsesForInviteUserId,
			Set userDrawCashRecords, Set feeRecordsesForOpUserId, Set plans, Set proxyuserses, Set userSettingses,
			Set userCashshopRecords, Set machines, Set sellers, Set userScoretypeses) {
		this.adminUser = adminUser;
		this.levels = levels;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.address = address;
		this.score = score;
		this.invitecode = invitecode;
		this.savemoney = savemoney;
		this.realname = realname;
		this.birthday = birthday;
		this.sex = sex;
		this.isVisitor = isVisitor;
		this.money = money;
		this.lat = lat;
		this.lng = lng;
		this.proxyzoneId = proxyzoneId;
		this.proxyId = proxyId;
		this.mycode = mycode;
		this.level = level;
		this.islogin = islogin;
		this.channelid = channelid;
		this.userid = userid;
		this.ccpsubaccountid = ccpsubaccountid;
		this.ccpsubaccountpwd = ccpsubaccountpwd;
		this.ccpvoipaccountid = ccpvoipaccountid;
		this.ccpvoipaccountpwd = ccpvoipaccountpwd;
		this.ccpcallsid = ccpcallsid;
		this.cashPoints = cashPoints;
		this.sign = sign;
		this.messagesesForFromUserId = messagesesForFromUserId;
		this.goodsAdversettingses = goodsAdversettingses;
		this.userrecores = userrecores;
		this.wxUserses = wxUserses;
		this.feeRecordsesForUserId = feeRecordsesForUserId;
		this.messagesesForUserId = messagesesForUserId;
		this.scoreExchangeses = scoreExchangeses;
		this.inviteRecordsesForUserId = inviteRecordsesForUserId;
		this.voucherRecords = voucherRecords;
		this.cashpointsRecords = cashpointsRecords;
		this.proxyinfoses = proxyinfoses;
		this.extendResultses = extendResultses;
		this.applies = applies;
		this.userProfitses = userProfitses;
		this.orderses = orderses;
		this.scorerecordses = scorerecordses;
		this.inviteRecordsesForInviteUserId = inviteRecordsesForInviteUserId;
		this.userDrawCashRecords = userDrawCashRecords;
		this.feeRecordsesForOpUserId = feeRecordsesForOpUserId;
		this.plans = plans;
		this.proxyuserses = proxyuserses;
		this.userSettingses = userSettingses;
		this.userCashshopRecords = userCashshopRecords;
		this.machines = machines;
		this.sellers = sellers;
		this.userScoretypeses = userScoretypeses;
	}*/
	public AbstractUsers(AdminUser adminUser, Levels levels, String name,
			String phone, String pwd, Boolean isvalid, Boolean isVisitor,
			Timestamp createtime, Timestamp lasttime, String address,
			Integer score, String invitecode, Double savemoney,
			String realname, Timestamp birthday, Integer sex, Double money,
			String lat, String lng, Integer proxyzoneId, Integer proxyId,
			String mycode, Integer level, Boolean islogin, String channelid,
			String userid, String ccpsubaccountid, String ccpsubaccountpwd,
			String ccpvoipaccountid, String ccpvoipaccountpwd,
			String ccpcallsid, Double cashPoints, String headimage,
			String imgUrl, Integer visitorId, String sign, Integer version,
			Double redPaperSum, Set messagesesForFromUserId,
			Set goodsAdversettingses, Set userrecores, Set wxUserses,
			Set feeRecordsesForUserId, Set messagesesForUserId,
			Set scoreExchangeses, Set inviteRecordsesForUserId,
			Set voucherRecords, Set cashpointsRecords, Set proxyinfoses,
			Set extendResultses, Set applies, Set userProfitses, Set orderses,
			Set scorerecordses, Set inviteRecordsesForInviteUserId,
			Set userDrawCashRecords, Set feeRecordsesForOpUserId, Set plans,
			Set proxyuserses, Set userSettingses, Set userCashshopRecords,
			Set machines, Set sellers, Set userScoretypeses, Set promoters,
			OrderMessageList ordermessageLists, String openId,
			Set<SellerMallShoppingCar> sellerMallShoppingCar, Integer sellerId,
			Boolean isSeller,Integer jphScore) {
		super();
		
		this.adminUser = adminUser;
		this.levels = levels;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.isvalid = isvalid;
		this.isVisitor = isVisitor;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.address = address;
		this.score = score;
		this.invitecode = invitecode;
		this.savemoney = savemoney;
		this.realname = realname;
		this.birthday = birthday;
		this.sex = sex;
		this.money = money;
		this.lat = lat;
		this.lng = lng;
		this.proxyzoneId = proxyzoneId;
		this.proxyId = proxyId;
		this.mycode = mycode;
		this.level = level;
		this.islogin = islogin;
		this.channelid = channelid;
		this.userid = userid;
		this.ccpsubaccountid = ccpsubaccountid;
		this.ccpsubaccountpwd = ccpsubaccountpwd;
		this.ccpvoipaccountid = ccpvoipaccountid;
		this.ccpvoipaccountpwd = ccpvoipaccountpwd;
		this.ccpcallsid = ccpcallsid;
		this.cashPoints = cashPoints;
		this.headimage = headimage;
		this.imgUrl = imgUrl;
		this.visitorId = visitorId;
		this.sign = sign;
		this.version = version;
		this.redPaperSum = redPaperSum;
		this.messagesesForFromUserId = messagesesForFromUserId;
		this.goodsAdversettingses = goodsAdversettingses;
		this.userrecores = userrecores;
		this.wxUserses = wxUserses;
		this.feeRecordsesForUserId = feeRecordsesForUserId;
		this.messagesesForUserId = messagesesForUserId;
		this.scoreExchangeses = scoreExchangeses;
		this.inviteRecordsesForUserId = inviteRecordsesForUserId;
		this.voucherRecords = voucherRecords;
		this.cashpointsRecords = cashpointsRecords;
		this.proxyinfoses = proxyinfoses;
		this.extendResultses = extendResultses;
		this.applies = applies;
		this.userProfitses = userProfitses;
		this.orderses = orderses;
		this.scorerecordses = scorerecordses;
		this.inviteRecordsesForInviteUserId = inviteRecordsesForInviteUserId;
		this.userDrawCashRecords = userDrawCashRecords;
		this.feeRecordsesForOpUserId = feeRecordsesForOpUserId;
		this.plans = plans;
		this.proxyuserses = proxyuserses;
		this.userSettingses = userSettingses;
		this.userCashshopRecords = userCashshopRecords;
		this.machines = machines;
		this.sellers = sellers;
		this.userScoretypeses = userScoretypeses;
		this.promoters = promoters;
		this.ordermessageLists = ordermessageLists;
		this.openId = openId;
		this.sellerMallShoppingCar = sellerMallShoppingCar;
		this.sellerId = sellerId;
		this.isSeller = isSeller;
		this.jphScore = jphScore;
	}

	

	// Property accessors

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

	public Levels getLevels() {
		return this.levels;
	}

	public Boolean getIsVisitor() {
		return isVisitor;
	}

	public void setIsVisitor(Boolean isVisitor) {
		this.isVisitor = isVisitor;
	}

	public void setLevels(Levels levels) {
		this.levels = levels;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public Timestamp getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getInvitecode() {
		return this.invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}

	public Double getSavemoney() {
		return this.savemoney;
	}

	public void setSavemoney(Double savemoney) {
		this.savemoney = savemoney;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getProxyzoneId() {
		return this.proxyzoneId;
	}

	public void setProxyzoneId(Integer proxyzoneId) {
		this.proxyzoneId = proxyzoneId;
	}

	public Integer getProxyId() {
		return this.proxyId;
	}

	public void setProxyId(Integer proxyId) {
		this.proxyId = proxyId;
	}

	public String getMycode() {
		return this.mycode;
	}

	public void setMycode(String mycode) {
		this.mycode = mycode;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIslogin() {
		return this.islogin;
	}

	public void setIslogin(Boolean islogin) {
		this.islogin = islogin;
	}

	public String getChannelid() {
		return this.channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCcpsubaccountid() {
		return this.ccpsubaccountid;
	}

	public void setCcpsubaccountid(String ccpsubaccountid) {
		this.ccpsubaccountid = ccpsubaccountid;
	}

	public String getCcpsubaccountpwd() {
		return this.ccpsubaccountpwd;
	}

	public void setCcpsubaccountpwd(String ccpsubaccountpwd) {
		this.ccpsubaccountpwd = ccpsubaccountpwd;
	}

	public String getCcpvoipaccountid() {
		return this.ccpvoipaccountid;
	}

	public void setCcpvoipaccountid(String ccpvoipaccountid) {
		this.ccpvoipaccountid = ccpvoipaccountid;
	}

	public String getCcpvoipaccountpwd() {
		return this.ccpvoipaccountpwd;
	}

	public void setCcpvoipaccountpwd(String ccpvoipaccountpwd) {
		this.ccpvoipaccountpwd = ccpvoipaccountpwd;
	}

	public String getCcpcallsid() {
		return this.ccpcallsid;
	}

	public void setCcpcallsid(String ccpcallsid) {
		this.ccpcallsid = ccpcallsid;
	}

	public Double getCashPoints() {
		return this.cashPoints;
	}

	public void setCashPoints(Double cashPoints) {
		this.cashPoints = cashPoints;
	}

	public Set getMessagesesForFromUserId() {
		return this.messagesesForFromUserId;
	}

	public void setMessagesesForFromUserId(Set messagesesForFromUserId) {
		this.messagesesForFromUserId = messagesesForFromUserId;
	}

	public Set getGoodsAdversettingses() {
		return this.goodsAdversettingses;
	}

	public void setGoodsAdversettingses(Set goodsAdversettingses) {
		this.goodsAdversettingses = goodsAdversettingses;
	}

	public Set getUserrecores() {
		return this.userrecores;
	}

	public void setUserrecores(Set userrecores) {
		this.userrecores = userrecores;
	}

	public Set getWxUserses() {
		return this.wxUserses;
	}

	public void setWxUserses(Set wxUserses) {
		this.wxUserses = wxUserses;
	}

	public Set getFeeRecordsesForUserId() {
		return this.feeRecordsesForUserId;
	}

	public void setFeeRecordsesForUserId(Set feeRecordsesForUserId) {
		this.feeRecordsesForUserId = feeRecordsesForUserId;
	}

	public Set getMessagesesForUserId() {
		return this.messagesesForUserId;
	}

	public void setMessagesesForUserId(Set messagesesForUserId) {
		this.messagesesForUserId = messagesesForUserId;
	}

	public Set getScoreExchangeses() {
		return this.scoreExchangeses;
	}

	public void setScoreExchangeses(Set scoreExchangeses) {
		this.scoreExchangeses = scoreExchangeses;
	}

	public Set getInviteRecordsesForUserId() {
		return this.inviteRecordsesForUserId;
	}

	public void setInviteRecordsesForUserId(Set inviteRecordsesForUserId) {
		this.inviteRecordsesForUserId = inviteRecordsesForUserId;
	}

	public Set getVoucherRecords() {
		return this.voucherRecords;
	}

	public void setVoucherRecords(Set voucherRecords) {
		this.voucherRecords = voucherRecords;
	}

	public Set getCashpointsRecords() {
		return this.cashpointsRecords;
	}

	public void setCashpointsRecords(Set cashpointsRecords) {
		this.cashpointsRecords = cashpointsRecords;
	}

	public Set getProxyinfoses() {
		return this.proxyinfoses;
	}

	public void setProxyinfoses(Set proxyinfoses) {
		this.proxyinfoses = proxyinfoses;
	}

	public Set getExtendResultses() {
		return this.extendResultses;
	}

	public void setExtendResultses(Set extendResultses) {
		this.extendResultses = extendResultses;
	}

	public Set getApplies() {
		return this.applies;
	}

	public void setApplies(Set applies) {
		this.applies = applies;
	}

	public Set getUserProfitses() {
		return this.userProfitses;
	}

	public void setUserProfitses(Set userProfitses) {
		this.userProfitses = userProfitses;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

	public Set getScorerecordses() {
		return this.scorerecordses;
	}

	public void setScorerecordses(Set scorerecordses) {
		this.scorerecordses = scorerecordses;
	}

	public Set getInviteRecordsesForInviteUserId() {
		return this.inviteRecordsesForInviteUserId;
	}

	public void setInviteRecordsesForInviteUserId(Set inviteRecordsesForInviteUserId) {
		this.inviteRecordsesForInviteUserId = inviteRecordsesForInviteUserId;
	}

	public Set getUserDrawCashRecords() {
		return this.userDrawCashRecords;
	}

	public void setUserDrawCashRecords(Set userDrawCashRecords) {
		this.userDrawCashRecords = userDrawCashRecords;
	}

	public Set getFeeRecordsesForOpUserId() {
		return this.feeRecordsesForOpUserId;
	}

	public void setFeeRecordsesForOpUserId(Set feeRecordsesForOpUserId) {
		this.feeRecordsesForOpUserId = feeRecordsesForOpUserId;
	}

	public Set getPlans() {
		return this.plans;
	}

	public void setPlans(Set plans) {
		this.plans = plans;
	}

	public Set getProxyuserses() {
		return this.proxyuserses;
	}

	public void setProxyuserses(Set proxyuserses) {
		this.proxyuserses = proxyuserses;
	}

	public Set getUserSettingses() {
		return this.userSettingses;
	}

	public void setUserSettingses(Set userSettingses) {
		this.userSettingses = userSettingses;
	}

	public Set getUserCashshopRecords() {
		return this.userCashshopRecords;
	}

	public void setUserCashshopRecords(Set userCashshopRecords) {
		this.userCashshopRecords = userCashshopRecords;
	}

	public Set getMachines() {
		return this.machines;
	}

	public void setMachines(Set machines) {
		this.machines = machines;
	}

	public Set getSellers() {
		return this.sellers;
	}

	public void setSellers(Set sellers) {
		this.sellers = sellers;
	}

	public Set getUserScoretypeses() {
		return this.userScoretypeses;
	}

	public void setUserScoretypeses(Set userScoretypeses) {
		this.userScoretypeses = userScoretypeses;
	}

	public Set getPromoters() {
		return this.promoters;
	}

	public void setPromoters(Set promoters) {
		this.promoters = promoters;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Double getRedPaperSum() {
		return redPaperSum;
	}

	public void setRedPaperSum(Double redPaperSum) {
		this.redPaperSum = redPaperSum;
	}

	private Integer getVersion() {
		return version;
	}

	private void setVersion(Integer version) {
		this.version = version;
	}

	public Set<SellerMallShoppingCar> getSellerMallShoppingCar() {
		return sellerMallShoppingCar;
	}

	public void setSellerMallShoppingCar(Set<SellerMallShoppingCar> sellerMallShoppingCar) {
		this.sellerMallShoppingCar = sellerMallShoppingCar;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Boolean getIsSeller() {
		return isSeller;
	}

	public void setIsSeller(Boolean isSeller) {
		this.isSeller = isSeller;
	}
	
	public Seller getParentSeller() {
		return parentSeller;
	}

	public void setParentSeller(Seller parentSeller) {
		this.parentSeller = parentSeller;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public OrderMessageList getOrdermessageLists() {
		return ordermessageLists;
	}

	public void setOrdermessageLists(OrderMessageList ordermessageLists) {
		this.ordermessageLists = ordermessageLists;
	}
     

     
	

	

}