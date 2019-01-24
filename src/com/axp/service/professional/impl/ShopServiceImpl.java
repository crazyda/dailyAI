package com.axp.service.professional.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.ShopDao;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.Seller;
import com.axp.service.professional.ShopService;
import com.axp.service.system.AdminUserService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("ShopService")
public class ShopServiceImpl extends ProfessionalServiceImpl implements ShopService{

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private ShopDao shopDao;
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Override
	public List<ProvinceEnum> getZoneById(Integer id, Integer level) {
		return null;
	}

	@Override
	public void getSellerList(HttpServletRequest request,
			HttpServletResponse response) {
		String id = (String)request.getParameter("seller_id");
		String search_name = (String)request.getParameter("search_name");
		Integer current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
        List<Seller> sellerlist =sellerDAO.getSellerListByAdminId(current_user_id);
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Integer current_level= adminUser.getLevel();
        Seller seller =null;;
		if(sellerlist!=null&&sellerlist.size()>0)
			seller = sellerlist.get(0);
		StringBuffer param = new StringBuffer();//页码条件
	    QueryModel model = new QueryModel();
		model.combPreEquals("id", id );
		model.combPreEquals("isvalid", true);
		model.combPreEquals("adminUser.id", current_user_id, "adminUserId");
		model.combCompare("adminUser.level", current_level, 2);
		
		//id
	    if(!StringUtils.isEmpty(id )){
	    	model.combPreEquals("id", Integer.parseInt(id ));
	    	param.append("&seller_id="+id );
	    }
	    //搜索
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("name like'%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
	    String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(Seller.class, model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<Seller> sellerlist1 = dateBaseDao.findPageList(Seller.class, model, start, end);
        request.setAttribute("seller", seller);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("sellerlist", sellerlist1);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public boolean delete(HttpServletRequest request, Integer id) {
		QueryModel queryModel = new QueryModel();
		queryModel.combEquals("id", id);
		Seller  seller=(Seller)dateBaseDao.findOne(Seller.class, queryModel);
		if (seller.getUsers()==null) {
			shopDao.del(id);
			return true;
		}
		return false;
	}

}
