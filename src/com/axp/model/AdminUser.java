package com.axp.model;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.impl.AdminUserDAOImpl;
import com.axp.util.StringUtil;

/**
 * AdminUser entity. @author MyEclipse Persistence Tools
 */
public class AdminUser extends AbstractAdminUser implements java.io.Serializable {

	private String levelname;

	private String fathername;
	private String proxyonename;

	private String centername;

	private Integer rePermissionRoleId;
	
	private RePermissionRole role;
	
	private Integer score;
	

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	private Boolean isTeam;

	public Boolean getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(Boolean isTeam) {
		this.isTeam = isTeam;
	}

	public String getProxyonename() {
		if(this.getProxyOne()!=null && this.getProxyOne()>0){
			AdminUserDAO adminUserDAO = new AdminUserDAOImpl();
	     	AdminUser user = adminUserDAO.findById(this.getProxyOne());
	     	if(user!=null){
	     		proxyonename = user.getUsername()==null?"":user.getUsername();
	     	}
		}else{
			proxyonename="";
		}
		return proxyonename;
	}

	public void setProxyonename(String proxyonename) {
		this.proxyonename = proxyonename;
	}

	public String getCentername() {
		if(this.getCenter()!=null && this.getCenter()>0){
			AdminUserDAO adminUserDAO = new AdminUserDAOImpl();
	     	AdminUser user = adminUserDAO.findById(this.getCenter());
	     	if(user!=null){
	     		centername = user.getUsername()==null?"":user.getUsername();
	     	}else{
	     		centername="";
	     	}
		}else{
			centername="";
		}
		
		return centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	public String getFathername() {
		if(this.getParentAdminUser()!=null){
			fathername = this.getParentAdminUser().getUsername();
		}
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	

	public String getLevelname() {
		if(this.getLevel() !=null && this.getLevel() >0){
			levelname = StringUtil.getLevelsHash().get(this.getLevel().toString());
		}
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	/** default constructor */
	public AdminUser() {
	}

	public Integer getRePermissionRoleId() {
		return rePermissionRoleId;
	}

	public void setRePermissionRoleId(Integer rePermissionRoleId) {
		this.rePermissionRoleId = rePermissionRoleId;
	}

	public RePermissionRole getRole() {
		return role;
	}

	public void setRole(RePermissionRole role) {
		this.role = role;
	}
	
	
}
