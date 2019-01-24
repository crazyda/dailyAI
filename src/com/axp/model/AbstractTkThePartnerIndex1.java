package com.axp.model;

/**
 * AbstractTkThePartnerIndex1 entity provides the base persistence definition of
 * the TkThePartnerIndex1 entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTkThePartnerIndex1 implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer pidId;
	private String usname;
	private String id_1;
	private String uid;
	private String allFeeYesterday;
	private String selfFeeYesterday;
	private String allFeeToweek;
	private String selfFeeToweek;
	private String allFeeThismonth;
	private String selfFeeThismonth;
	private String allFeeLastmonth;
	private String selfFeeLastmonth;
	private String orderCountYesterday;
	private String selfCountYesterday;
	private String orderCountToweek;
	private String selfCountToweek;
	private String orderCountThismonth;
	private String selfCountThismonth;
	private String orderCountLastmonth;
	private String selfCountLastmonth;
	private String date;
	private String allFeeToday;
	private String selfFeeToday;
	private String orderCountToday;
	private String selfCountToday;
	private String userprice;
	private String lastMonthPrice;
	private String thisMonthPrice;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractTkThePartnerIndex1() {
	}

	/** minimal constructor */
	public AbstractTkThePartnerIndex1(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractTkThePartnerIndex1(Integer userId, Integer pidId,
			String usname, String id_1, String uid, String allFeeYesterday,
			String selfFeeYesterday, String allFeeToweek, String selfFeeToweek,
			String allFeeThismonth, String selfFeeThismonth,
			String allFeeLastmonth, String selfFeeLastmonth,
			String orderCountYesterday, String selfCountYesterday,
			String orderCountToweek, String selfCountToweek,
			String orderCountThismonth, String selfCountThismonth,
			String orderCountLastmonth, String selfCountLastmonth, String date,
			String allFeeToday, String selfFeeToday, String orderCountToday,
			String selfCountToday, String userprice, String lastMonthPrice,
			String thisMonthPrice, Boolean isValid) {
		this.userId = userId;
		this.pidId = pidId;
		this.usname = usname;
		this.id_1 = id_1;
		this.uid = uid;
		this.allFeeYesterday = allFeeYesterday;
		this.selfFeeYesterday = selfFeeYesterday;
		this.allFeeToweek = allFeeToweek;
		this.selfFeeToweek = selfFeeToweek;
		this.allFeeThismonth = allFeeThismonth;
		this.selfFeeThismonth = selfFeeThismonth;
		this.allFeeLastmonth = allFeeLastmonth;
		this.selfFeeLastmonth = selfFeeLastmonth;
		this.orderCountYesterday = orderCountYesterday;
		this.selfCountYesterday = selfCountYesterday;
		this.orderCountToweek = orderCountToweek;
		this.selfCountToweek = selfCountToweek;
		this.orderCountThismonth = orderCountThismonth;
		this.selfCountThismonth = selfCountThismonth;
		this.orderCountLastmonth = orderCountLastmonth;
		this.selfCountLastmonth = selfCountLastmonth;
		this.date = date;
		this.allFeeToday = allFeeToday;
		this.selfFeeToday = selfFeeToday;
		this.orderCountToday = orderCountToday;
		this.selfCountToday = selfCountToday;
		this.userprice = userprice;
		this.lastMonthPrice = lastMonthPrice;
		this.thisMonthPrice = thisMonthPrice;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPidId() {
		return this.pidId;
	}

	public void setPidId(Integer pidId) {
		this.pidId = pidId;
	}

	public String getUsname() {
		return this.usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	public String getId_1() {
		return this.id_1;
	}

	public void setId_1(String id_1) {
		this.id_1 = id_1;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAllFeeYesterday() {
		return this.allFeeYesterday;
	}

	public void setAllFeeYesterday(String allFeeYesterday) {
		this.allFeeYesterday = allFeeYesterday;
	}

	public String getSelfFeeYesterday() {
		return this.selfFeeYesterday;
	}

	public void setSelfFeeYesterday(String selfFeeYesterday) {
		this.selfFeeYesterday = selfFeeYesterday;
	}

	public String getAllFeeToweek() {
		return this.allFeeToweek;
	}

	public void setAllFeeToweek(String allFeeToweek) {
		this.allFeeToweek = allFeeToweek;
	}

	public String getSelfFeeToweek() {
		return this.selfFeeToweek;
	}

	public void setSelfFeeToweek(String selfFeeToweek) {
		this.selfFeeToweek = selfFeeToweek;
	}

	public String getAllFeeThismonth() {
		return this.allFeeThismonth;
	}

	public void setAllFeeThismonth(String allFeeThismonth) {
		this.allFeeThismonth = allFeeThismonth;
	}

	public String getSelfFeeThismonth() {
		return this.selfFeeThismonth;
	}

	public void setSelfFeeThismonth(String selfFeeThismonth) {
		this.selfFeeThismonth = selfFeeThismonth;
	}

	public String getAllFeeLastmonth() {
		return this.allFeeLastmonth;
	}

	public void setAllFeeLastmonth(String allFeeLastmonth) {
		this.allFeeLastmonth = allFeeLastmonth;
	}

	public String getSelfFeeLastmonth() {
		return this.selfFeeLastmonth;
	}

	public void setSelfFeeLastmonth(String selfFeeLastmonth) {
		this.selfFeeLastmonth = selfFeeLastmonth;
	}

	public String getOrderCountYesterday() {
		return this.orderCountYesterday;
	}

	public void setOrderCountYesterday(String orderCountYesterday) {
		this.orderCountYesterday = orderCountYesterday;
	}

	public String getSelfCountYesterday() {
		return this.selfCountYesterday;
	}

	public void setSelfCountYesterday(String selfCountYesterday) {
		this.selfCountYesterday = selfCountYesterday;
	}

	public String getOrderCountToweek() {
		return this.orderCountToweek;
	}

	public void setOrderCountToweek(String orderCountToweek) {
		this.orderCountToweek = orderCountToweek;
	}

	public String getSelfCountToweek() {
		return this.selfCountToweek;
	}

	public void setSelfCountToweek(String selfCountToweek) {
		this.selfCountToweek = selfCountToweek;
	}

	public String getOrderCountThismonth() {
		return this.orderCountThismonth;
	}

	public void setOrderCountThismonth(String orderCountThismonth) {
		this.orderCountThismonth = orderCountThismonth;
	}

	public String getSelfCountThismonth() {
		return this.selfCountThismonth;
	}

	public void setSelfCountThismonth(String selfCountThismonth) {
		this.selfCountThismonth = selfCountThismonth;
	}

	public String getOrderCountLastmonth() {
		return this.orderCountLastmonth;
	}

	public void setOrderCountLastmonth(String orderCountLastmonth) {
		this.orderCountLastmonth = orderCountLastmonth;
	}

	public String getSelfCountLastmonth() {
		return this.selfCountLastmonth;
	}

	public void setSelfCountLastmonth(String selfCountLastmonth) {
		this.selfCountLastmonth = selfCountLastmonth;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAllFeeToday() {
		return this.allFeeToday;
	}

	public void setAllFeeToday(String allFeeToday) {
		this.allFeeToday = allFeeToday;
	}

	public String getSelfFeeToday() {
		return this.selfFeeToday;
	}

	public void setSelfFeeToday(String selfFeeToday) {
		this.selfFeeToday = selfFeeToday;
	}

	public String getOrderCountToday() {
		return this.orderCountToday;
	}

	public void setOrderCountToday(String orderCountToday) {
		this.orderCountToday = orderCountToday;
	}

	public String getSelfCountToday() {
		return this.selfCountToday;
	}

	public void setSelfCountToday(String selfCountToday) {
		this.selfCountToday = selfCountToday;
	}

	public String getUserprice() {
		return this.userprice;
	}

	public void setUserprice(String userprice) {
		this.userprice = userprice;
	}

	public String getLastMonthPrice() {
		return this.lastMonthPrice;
	}

	public void setLastMonthPrice(String lastMonthPrice) {
		this.lastMonthPrice = lastMonthPrice;
	}

	public String getThisMonthPrice() {
		return this.thisMonthPrice;
	}

	public void setThisMonthPrice(String thisMonthPrice) {
		this.thisMonthPrice = thisMonthPrice;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

}