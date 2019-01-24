package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractLevels entity provides the base persistence definition of the Levels
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLevels implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer num;
	private Integer level;
	private Double fee;
	private Double generalProfit;
	private Double proxyProfit;
	private Double otherProfit;
	private Integer adverNum;
	private Integer giftNum;
	private Set proxyuserses = new HashSet(0);
	private Set userses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractLevels() {
	}

	/** minimal constructor */
	public AbstractLevels(String name, Boolean isvalid, Timestamp createtime,
			Integer num, Integer level, Double fee, Double generalProfit,
			Double proxyProfit, Double otherProfit, Integer adverNum,
			Integer giftNum) {
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.num = num;
		this.level = level;
		this.fee = fee;
		this.generalProfit = generalProfit;
		this.proxyProfit = proxyProfit;
		this.otherProfit = otherProfit;
		this.adverNum = adverNum;
		this.giftNum = giftNum;
	}

	/** full constructor */
	public AbstractLevels(String name, Boolean isvalid, Timestamp createtime,
			Integer num, Integer level, Double fee, Double generalProfit,
			Double proxyProfit, Double otherProfit, Integer adverNum,
			Integer giftNum, Set proxyuserses, Set userses) {
		this.name = name;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.num = num;
		this.level = level;
		this.fee = fee;
		this.generalProfit = generalProfit;
		this.proxyProfit = proxyProfit;
		this.otherProfit = otherProfit;
		this.adverNum = adverNum;
		this.giftNum = giftNum;
		this.proxyuserses = proxyuserses;
		this.userses = userses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getGeneralProfit() {
		return this.generalProfit;
	}

	public void setGeneralProfit(Double generalProfit) {
		this.generalProfit = generalProfit;
	}

	public Double getProxyProfit() {
		return this.proxyProfit;
	}

	public void setProxyProfit(Double proxyProfit) {
		this.proxyProfit = proxyProfit;
	}

	public Double getOtherProfit() {
		return this.otherProfit;
	}

	public void setOtherProfit(Double otherProfit) {
		this.otherProfit = otherProfit;
	}

	public Integer getAdverNum() {
		return this.adverNum;
	}

	public void setAdverNum(Integer adverNum) {
		this.adverNum = adverNum;
	}

	public Integer getGiftNum() {
		return this.giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public Set getProxyuserses() {
		return this.proxyuserses;
	}

	public void setProxyuserses(Set proxyuserses) {
		this.proxyuserses = proxyuserses;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

}