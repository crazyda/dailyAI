package com.axp.model;

import java.util.Date;

/**
 * 用户记录当天的各种数据 如：当天用户获取得总分数，总红包数等；
 * 
 */
public abstract class AbstractTodayRecord implements java.io.Serializable {

	private Integer id;
	private Boolean isValid;
	private Integer userId;
	private Date updateTime;
	private Integer todayScore;// 用户今天获取得总分数
	private Integer todayRedpaper;// 用户今天获取的总部红包数；
	private Integer todaySellerRedpaper;// 用户今天获取得商家红包数；
	public Integer getTodayReferee() {
		return todayReferee;
	}

	public void setTodayReferee(Integer todayReferee) {
		this.todayReferee = todayReferee;
	}

	public Double getTodayCash() {
		return todayCash;
	}

	public void setTodayCash(Double todayCash) {
		this.todayCash = todayCash;
	}

	public Integer getRedPaper() {
		return redPaper;
	}

	public void setRedPaper(Integer redPaper) {
		this.redPaper = redPaper;
	}

	private Integer todayReferee;//今日推荐
	private Double todayCash;//今日代金券
	private Integer redPaper;//今日红包

	// construction
	public AbstractTodayRecord() {
		super();
	}

	public AbstractTodayRecord(Integer id, Boolean isValid, Integer userId,

			Date updateTime, Integer todayScore,Integer todayReferee,Double todayCash,Integer redPaper) {


		super();
		this.id = id;
		this.isValid = isValid;
		this.userId = userId;
		this.updateTime = updateTime;
		this.todayScore = todayScore;

		this.todayReferee = todayReferee;
		this.todayCash = todayCash;
		this.redPaper = redPaper;

	}

	// Property accessors
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getTodayScore() {
		return todayScore;
	}

	public void setTodayScore(Integer todayScore) {
		this.todayScore = todayScore;
	}

	public Integer getTodayRedpaper() {
		return todayRedpaper;
	}

	public void setTodayRedpaper(Integer todayRedpaper) {
		this.todayRedpaper = todayRedpaper;
	}

	public Integer getTodaySellerRedpaper() {
		return todaySellerRedpaper;
	}

	public void setTodaySellerRedpaper(Integer todaySellerRedpaper) {
		this.todaySellerRedpaper = todaySellerRedpaper;
	}


}