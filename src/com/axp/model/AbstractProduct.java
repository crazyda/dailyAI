package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String productName;
	private String productImg;
	private Users users ; 
	private AdminUser adminUser ;//收分用户
	private Integer score ;
	private Branks brank;
	private Timestamp createTime;
	private Boolean isValid;
	private Integer num;//总数量
	private Integer useNum;//已兑换的
	private String remark;
	public AbstractProduct() {
		super();
	}


	

	



	public AbstractProduct(Integer id, String productName, String productImg,
			Users users, AdminUser adminUser, Integer score, Branks brank,
			Timestamp createTime, Boolean isValid, Integer num, Integer useNum,
			String remark) {
		super();
		this.id = id;
		this.productName = productName;
		this.productImg = productImg;
		this.users = users;
		this.adminUser = adminUser;
		this.score = score;
		this.brank = brank;
		this.createTime = createTime;
		this.isValid = isValid;
		this.num = num;
		this.useNum = useNum;
		this.remark = remark;
	}








	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public Integer getUseNum() {
		return useNum;
	}



	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}


	


	public Boolean getIsValid() {
		return isValid;
	}








	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}








	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Branks getBrank() {
		return brank;
	}

	public void setBrank(Branks brank) {
		this.brank = brank;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
