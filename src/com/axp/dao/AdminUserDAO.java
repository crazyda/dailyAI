package com.axp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;

public interface AdminUserDAO extends IBaseDao<AdminUser> {

    AdminUser getLoginAdminUser(String loginname);

    void updateInviteCode(AdminUser adminUser);

    AdminUser getCurrentUser(HttpServletRequest request);

    AdminUser getHQ();

    /**
     * 根据给定的角色id，在adminUser表中寻找这个角色被多少后台用户使用；
     *
     * @param roleId 角色id值；
     * @return
     */
    Long getCountByRoleId(Integer roleId);

	AdminUser getLoginAdminUserBySellerId(Integer sellerId);

	List<AdminUser> getChilds(Integer adminUserId);
	List<AdminUser> getChildsByLevel(Integer adminUserId,Integer level);
	

	List<AdminUser> findByRoleId(Integer roleId);
	List<AdminUser> findByLevel(Integer level);

	/**
	 * 代理下的联盟商家
	 * @param current_user_id
	 * @return
	 */
	List<AdminUser> findByunion(Integer current_user_id);

}
