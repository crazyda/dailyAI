/**
 * 2018-10-17
 * Administrator
 */
package com.axp.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author da
 * @data 2018-10-17下午5:10:12
 */
public abstract class AbstractUserDarw implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer score;
	private String name;
	private Boolean isvalid;
	private Integer type;//后天奖品设置选项
	private Integer drawNum;//每天抽奖次数
	private Users users;
	private Integer surplus;//每天剩余次数
	private String details;
	private String coverPic;
	
	public AbstractUserDarw() {
		super();
	}







	public AbstractUserDarw(Integer id, Integer score, String name,
			Boolean isvalid, Integer type, Integer drawNum, Users users,
			Integer surplus, String details, String coverPic) {
		super();
		this.id = id;
		this.score = score;
		this.name = name;
		this.isvalid = isvalid;
		this.type = type;
		this.drawNum = drawNum;
		this.users = users;
		this.surplus = surplus;
		this.details = details;
		this.coverPic = coverPic;
	}







	public String getCoverPic() {
		return coverPic;
	}



	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}



	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(Integer drawNum) {
		this.drawNum = drawNum;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	

}
