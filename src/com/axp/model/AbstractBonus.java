package com.axp.model;

import java.sql.Timestamp;



/**
 * AbstractAdver entity provides the base persistence definition of the Adver
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBonus implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private AdminUser adminUser; //归属代理
	private Users user; //积分使用者
	private AdminUser adminUserBuy;//充值商家
	private Timestamp createTime; //创建时间
	private double maid;//奖金池
	private double maiddl;//代理收益
	private double maidhhr;//合伙人收益
	private double maidsj;//商家收益 
	private Integer score;//消费积分
	private Integer recoveryScore;//回收积分
	
	
	
	
	
	public AbstractBonus() {
		super();
	}
	public AbstractBonus(Integer id, AdminUser adminUser, Users user,
			AdminUser adminUserBuy, Timestamp createTime, double maid,
			double maiddl, double maidhhr, double maidsj, Integer score,
			Integer recoveryScore) {
		super();
		this.id = id;
		this.adminUser = adminUser;
		this.user = user;
		this.adminUserBuy = adminUserBuy;
		this.createTime = createTime;
		this.maid = maid;
		this.maiddl = maiddl;
		this.maidhhr = maidhhr;
		this.maidsj = maidsj;
		this.score = score;
		this.recoveryScore = recoveryScore;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AdminUser getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public AdminUser getAdminUserBuy() {
		return adminUserBuy;
	}
	public void setAdminUserBuy(AdminUser adminUserBuy) {
		this.adminUserBuy = adminUserBuy;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public double getMaid() {
		return maid;
	}
	public void setMaid(double maid) {
		this.maid = maid;
	}
	public double getMaiddl() {
		return maiddl;
	}
	public void setMaiddl(double maiddl) {
		this.maiddl = maiddl;
	}
	public double getMaidhhr() {
		return maidhhr;
	}
	public void setMaidhhr(double maidhhr) {
		this.maidhhr = maidhhr;
	}
	public double getMaidsj() {
		return maidsj;
	}
	public void setMaidsj(double maidsj) {
		this.maidsj = maidsj;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getRecoveryScore() {
		return recoveryScore;
	}
	public void setRecoveryScore(Integer recoveryScore) {
		this.recoveryScore = recoveryScore;
	}
	
	
	
	
}