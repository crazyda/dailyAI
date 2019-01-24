/**
 * 2018-12-24
 * Administrator
 */
package com.axp.service.BranksCoupon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AdminUser;
import com.axp.model.Product;
import com.axp.service.system.IBaseService;

/**
 * @author da
 * @data 2018-12-24下午4:57:45
 */
public interface BranksCouponService extends IBaseService{

	/**
	 * 查询所有的优惠券兑换产品
	 * @param request
	 * @param response
	 * @param adminUser 
	 */
	void findProduct(HttpServletRequest request, HttpServletResponse response, AdminUser adminUser);

	/**
	 * 查询某条数据
	 * @param valueOf
	 * @return
	 */
	Product findByProduct(Integer valueOf);

}
