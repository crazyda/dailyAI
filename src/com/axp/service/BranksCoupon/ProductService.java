/**
 * 2018-12-25
 * Administrator
 */
package com.axp.service.BranksCoupon;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;
import com.axp.model.Branks;
import com.axp.service.system.IBaseService;

/**
 * @author da
 * @data 2018-12-25下午2:50:12
 */
public interface ProductService extends IBaseService{

	/**
	 * 保存产品信息
	 * @param id
	 * @param adminUserId
	 * @param brankId
	 * @param productName 
	 * @param productImg
	 * @param score
	 * @param num
	 * @param remark
	 * @return
	 */
	Map<String, Object> saveProduct(String id, String adminUserId,
			String brankId, String productName, String[] productImg, String score, String num,
			String remark);

	/**
	 * 删除产品信息
	 * @param id
	 * @return
	 */
	Map<String, Object> delete(String id);

	/**
	 * 查询代理对应的 商品
	 * @param request 
	 * @param adminUser
	 * @param search
	 * @param brank
	 * @param pagestr 
	 */
	void getProductToAgent(HttpServletRequest request, AdminUser adminUser, String search, Branks brank, String pagestr);

}
