/**
 * 2018-12-25
 * Administrator
 */
package com.axp.service.BranksCoupon;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;
import com.axp.service.invoke.IBaseService;


/**
 * @author da
 * @data 2018-12-25下午2:50:12
 */
public interface RedeemCodeService extends IBaseService{

	/**
	 * 有兑换码的产品列表
	 * @param request 
	 * @param adminUser
	 * @param branksId
	 * @param search
	 * @param pagestr 
	 */
	void getRedeemCodeList(HttpServletRequest request, AdminUser adminUser, String branksId, String search, String pagestr);

	/**
	 * 收分用户查看兑换码
	 * @param valueOf
	 * @param isVerify 
	 * @return
	 */
	Map<String, Object> getRedeemCode(Integer valueOf, String isVerify);
	
	
	
	
	
}
