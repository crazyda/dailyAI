/**
 * 2018-12-25
 * Administrator
 */
package com.axp.service.BranksCoupon;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;
import com.axp.model.Branks;
import com.axp.model.Users;
import com.axp.service.system.IBaseService;

/**
 * @author da
 * @data 2018-12-25下午2:50:12
 */
public interface AgentProductService extends IBaseService{

	/**
	 * 代理添加 产品
	 * @param adminUser
	 * @param productId
	 */
	Map<String,Object> saveAgentProduct(AdminUser adminUser, String productId);

	/**
	 * 删除某个产品
	 * @param id
	 * @return
	 */
	Map<String, Object> del(String id);

	/**
	 * 合伙人添加产品
	 * @param adminUser
	 * @param productId
	 * @return
	 */
	Map<String, Object> addHhrProduct(AdminUser adminUser, String productId);

	/**
	 * 查询合伙人产品
	 * @param request
	 * @param adminUser
	 * @param search
	 * @param brank
	 * @param pagestr
	 */
	void getHhrProduct(HttpServletRequest request, AdminUser adminUser,
			String search, Branks brank, String pagestr);

	/**
	 * 合伙人 填写兑换码
	 * @param adminUser
	 * @param productId
	 * @param redeemCode
	 * @return
	 */
	Map<String, Object> addCode(AdminUser adminUser,Users user, String productId,
			String redeemCode);

	
	
	
}
