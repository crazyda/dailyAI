package com.axp.service.system;


import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;
import com.axp.model.MembersBonusList;
import com.axp.model.ProviderBonus;
import org.logicalcobwebs.proxool.admin.Admin;

import java.util.List;

public interface AdminUserService extends IBaseService {

    void list(HttpServletRequest request);

    void add(Integer id, HttpServletRequest request);

    String save(Integer id, Integer quantity, Integer levelname, Integer isZoneLimit, Integer zonesId, Integer sellerId, Double latitude, Double longitude, Double radius, String username, String phone, String contacts, String address, String loginName, ProviderBonus adminScale, MembersBonusList membersBonusList, HttpServletRequest request);

    /**
     * 根据id寻找后台用户；
     *
     * @param adminUserId
     * @return
     */
    AdminUser findById(Integer adminUserId);

    /**
     * 判断用户是否是超级管理员；
     *
     * @param request 请求对象；
     * @return
     */
    boolean isSuperAdmin(HttpServletRequest request);

    /**
     * 商家绑定手机号；
     *
     * @param adminUserId
     * @param phoneNumber
     */
    Boolean bindPhone(Integer adminUserId, String phoneNumber);

    /**
     * 获取给定的后台用户自身及其自己底下的所有的后台用户；
     *
     * @param adminUser 给定的后台用户对象；
     * @return
     */
    List<AdminUser> getAllCildAdminUsers(AdminUser adminUser);
    
    /**
     * 获取给定的后台用户自己底下的所有的运营商用户；
     *
     * @param adminUser 给定的后台用户对象；
     * @return
     */
    List<AdminUser> getLevel(Integer level);
    
    /**
     * 获取给定的后台用户自己底下的所有的代理商用户；
     *
     * @param adminUser 给定的后台用户对象；
     * @return
     */
    List<AdminUser> getlevel(Integer level);
    
    /**
     * 获取给定的后台用户自己地区的所有的代理商用户；
     *
     * @param adminUser 给定的后台用户对象；
     * @return
     */
    List<AdminUser> getUserByZone(Integer level,Integer adminUserId);
    
    
    void addGroup(AdminUser adminUser);

	/**
	 * 查询对应的商家
	 * @return
	 */
	List<AdminUser> findBySeller();
    
}