package com.axp.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.axp.util.JsonDescript;
import com.axp.util.QueryModel;

/**
 * AbstractPromoter entity provides the base persistence definition of the
 * Promoter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPromoter extends BaseModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Boolean isvalid;
	private Timestamp createtime;
	private Timestamp lasttime;
	private String alipayCode;
	private Double commission;
	private Promoter parentPromoter;
	private List<Promoter> chirldrenPromoters;
	private Set<GetmoneyRecord> getMoneyRecords;
	private Integer pId;
	private String realname ;//昵称
	private int fansCount;//粉丝数量
	private int userId;//用户id
	
	private String sign ;//签名
	private String userImg;//用户图像
	// Constructors

	/** default constructor */
	public AbstractPromoter() {
	}

	/** minimal constructor */
	public AbstractPromoter(Users users, Boolean isvalid, Timestamp createtime, String alipayCode,
			Double commission) {
		this.users = users;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.commission = commission;
	}

	/** full constructor */
	public AbstractPromoter(Users users, Boolean isvalid, Timestamp createtime, String alipayCode,
			Timestamp lasttime, Double commission) {
		this.users = users;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.commission = commission;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JsonDescript
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	@JsonDescript
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
	@JsonDescript(isShow = true,dateFormat = "yyyy-MM-dd")
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	@JsonDescript
	public Timestamp getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}
	
	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}
	@JsonDescript
	public Promoter getParentPromoter() {
		QueryModel model = new QueryModel();
		if(this.getpId() == null)
			model.combCondition("1 != 1");
		else
		{
			model.combPreEquals("id", this.getpId());
		}
		
		model.combPreEquals("isvalid", true);
		
		Object obj = dateBaseDAO.findOne(Promoter.class, model);
		return obj == null ? null :(Promoter)obj;
	}

	public void setParentPromoter(Promoter parentPromoter) {
		this.pId = parentPromoter.getId();
		this.parentPromoter = parentPromoter;
	}
	@JsonDescript
	public List<Promoter> getChirldrenPromoters() {
		QueryModel model = new QueryModel();
		model.combPreEquals("isvalid", true);
		model.combPreEquals("pId", this.getId());
		return dateBaseDAO.findLists(Promoter.class, model);
	}
	@JsonDescript
	public List<Promoter> getChirlProAndUserValid() {
		QueryModel model = new QueryModel();
		model.combPreEquals("isvalid", true);
		model.combPreEquals("pId", this.getId());
		model.combPreEquals("users.isvalid", Boolean.TRUE,"userValid");
		return dateBaseDAO.findLists(Promoter.class, model);
	}
	public void setChirldrenPromoters(List<Promoter> chirldrenPromoters) {
		this.chirldrenPromoters = chirldrenPromoters;
	}
	@JsonDescript
	public Set<GetmoneyRecord> getGetMoneyRecords() {
		return getMoneyRecords;
	}

	public void setGetMoneyRecords(Set<GetmoneyRecord> getMoneyRecords) {
		this.getMoneyRecords = getMoneyRecords;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public int getUserId() {
		return userId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAlipayCode() {
		return alipayCode;
	}

	public void setAlipayCode(String alipayCode) {
		this.alipayCode = alipayCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
}