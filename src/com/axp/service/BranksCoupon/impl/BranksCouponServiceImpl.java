/**
 * 2018-12-24
 * Administrator
 */
package com.axp.service.BranksCoupon.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.model.Product;
import com.axp.model.Seller;
import com.axp.service.BranksCoupon.BranksCouponService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

/**
 * @author da
 * @data 2018-12-24下午4:58:39
 */
@Service("branksCouponService")
public class BranksCouponServiceImpl extends BaseServiceImpl implements  BranksCouponService{
	
	@Resource
	private DateBaseDAO dateBaseDAO;
	
	@Override
	public void findProduct(HttpServletRequest request,
			HttpServletResponse response,AdminUser adminUser) {
		String brankId = request.getParameter("brankId");
		String search = request.getParameter("search");
		String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		Integer pageSize = 10;
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("isValid", true);
		
		if(brankId != null){
			model.combPreEquals("brank.id", Integer.valueOf(brankId),"brankId");
		}
		if(search != null){
			model.combPreLike("productName", search);
		}
		if(adminUser.getLevel() == 55){ //收分用户请求
			model.combPreEquals("adminUser.id", adminUser.getParentId(),"adminUserId");
		}
		int count = dateBaseDao.findCount(Product.class, model);
		Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        StringBuffer param = new StringBuffer();//页码条件
        pageInfo.setParam(param + "&page=");
        List<Seller> slist1 = dateBaseDao.findPageList(Product.class, model, start, end);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("list", slist1);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public Product findByProduct(Integer value) {
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("id", value);
		return (Product) dateBaseDAO.findOne(Product.class, model);
	}

}
