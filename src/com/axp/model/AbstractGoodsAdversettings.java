package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractGoodsAdversettings entity provides the base persistence definition of
 * the GoodsAdversettings entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsAdversettings implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Goods goods;
	private Integer scount;
	private Integer ocount;
	private Integer acount;
	private Boolean isvalid;
	private Timestamp createtime;
	private Timestamp begintime;
	private Timestamp endtime;
	private Integer proxyZoneId;
	private Integer status;
	private Set goodsAdverrecordses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractGoodsAdversettings() {
	}

	/** minimal constructor */
	public AbstractGoodsAdversettings(Users users, Goods goods, Integer scount,
			Integer ocount, Integer acount, Boolean isvalid,
			Timestamp createtime, Timestamp begintime, Timestamp endtime,
			Integer proxyZoneId, Integer status) {
		this.users = users;
		this.goods = goods;
		this.scount = scount;
		this.ocount = ocount;
		this.acount = acount;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.proxyZoneId = proxyZoneId;
		this.status = status;
	}

	/** full constructor */
	public AbstractGoodsAdversettings(Users users, Goods goods, Integer scount,
			Integer ocount, Integer acount, Boolean isvalid,
			Timestamp createtime, Timestamp begintime, Timestamp endtime,
			Integer proxyZoneId, Integer status, Set goodsAdverrecordses) {
		this.users = users;
		this.goods = goods;
		this.scount = scount;
		this.ocount = ocount;
		this.acount = acount;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.proxyZoneId = proxyZoneId;
		this.status = status;
		this.goodsAdverrecordses = goodsAdverrecordses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getScount() {
		return this.scount;
	}

	public void setScount(Integer scount) {
		this.scount = scount;
	}

	public Integer getOcount() {
		return this.ocount;
	}

	public void setOcount(Integer ocount) {
		this.ocount = ocount;
	}

	public Integer getAcount() {
		return this.acount;
	}

	public void setAcount(Integer acount) {
		this.acount = acount;
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

	public Timestamp getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Timestamp begintime) {
		this.begintime = begintime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Integer getProxyZoneId() {
		return this.proxyZoneId;
	}

	public void setProxyZoneId(Integer proxyZoneId) {
		this.proxyZoneId = proxyZoneId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getGoodsAdverrecordses() {
		return this.goodsAdverrecordses;
	}

	public void setGoodsAdverrecordses(Set goodsAdverrecordses) {
		this.goodsAdverrecordses = goodsAdverrecordses;
	}

}