package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSystemMessageList entity provides the base persistence definition of
 * the SystemMessageList entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSystemMessageList implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private MessageType messageType;
	private String content;
	private Timestamp time;
	private Boolean isValid;
	private Timestamp createtime;
	private Users users;
	private Double money;
	private Integer moneyState;
	private AdminUser adminUser;
	private ProvinceEnum provinceEnum;
	private String image;
	private String icon;
	private String iconMax;
	private AdminUser senAdminUser;

	// Constructors
	
	public AdminUser getSenAdminUser() {
		return senAdminUser;
	}

	public void setSenAdminUser(AdminUser senAdminUser) {
		this.senAdminUser = senAdminUser;
	}


	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	/** default constructor */
	public AbstractSystemMessageList() {
	}

	/** full constructor */
	public AbstractSystemMessageList(MessageType messageType, String content,Double money,Integer moneyState,
			Timestamp time,  Boolean isValid, Timestamp createtime,String title,Users users) {
		this.messageType = messageType;
		this.content = content;
		this.time = time;
		this.isValid = isValid;
		this.createtime = createtime;
		this.title = title;
		this.users = users;
		this.moneyState =moneyState;
		this.money = money;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MessageType getMessageType() {
		return this.messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}


	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getMoneyState() {
		return moneyState;
	}

	public void setMoneyState(Integer moneyState) {
		this.moneyState = moneyState;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconMax() {
		return iconMax;
	}

	public void setIconMax(String iconMax) {
		this.iconMax = iconMax;
	}
	
}