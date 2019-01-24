package com.axp.service.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;
import com.axp.model.Seller;


public interface SelectTagService extends IBaseService{

	void initSelectTag(HttpServletRequest request, AdminUser au,
			AdminUser objectAminUser);

	void initSelectTagBySeller(HttpServletRequest request, AdminUser au,
			Seller objectSeller);

	  /**
     * 根据当前登录用户查询所有下级
     * @param adminUser
     * @return 所有下级集合 key为SellerId 返回Seller对象
     */
	 List<AdminUser> findNextLevel(AdminUser adminUser);
	 
	 /**
	     * 查找下级查询字符串  用于queryModel.combCondition(sb.toString());
	     * 目标表为Seller  如果想从adminUser表开始 那么只需要去掉 seller.adminuser即可
	     * @param adminUser
	     * @return String 拼接字符串
	     */
	 String findNextLevelStr(AdminUser adminUser);
}
