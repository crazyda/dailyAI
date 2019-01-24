package com.axp.service.cashShop.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.axp.model.ImageType;
import com.axp.model.ShopCategory;
import com.axp.service.cashShop.ShopCategoryService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("ShopCategoryService")
public class ShopCategoryServiceImpl extends BaseServiceImpl implements ShopCategoryService {

	@Override
	public void list(HttpServletRequest request,HttpServletResponse response) {
		 	String pagestr = request.getParameter("page");
			PageInfo pageInfo = new PageInfo();
			int count = 0;
		    count = shopCategoryDao.getAllCountAndCon(ShopCategory.class,"isValid=true");
			Utility.setPageInfomation(pageInfo, pagestr, "20",count);
			int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
			int end = pageInfo.getPageSize();
			pageInfo.setParam("&page=");
			List<ShopCategory> shoplist = shopCategoryDao.findByPageOrderAndCon(ShopCategory.class, " isValid=true ", "id", "desc",start,end);
			request.setAttribute("shoplist", shoplist);
			request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public void add(Integer id,HttpServletRequest request) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("id", id);
		queryModel.combPreEquals("isValid", true);
		if(id!=null){
			ShopCategory sc = (ShopCategory) dateBaseDao.findOne(ShopCategory.class, queryModel);	
			request.setAttribute("sc", sc);
		}else{
			List<ShopCategory> slist= dateBaseDao.findPageList(ShopCategory.class, queryModel, 0, 0);
			request.setAttribute("slist", slist);
		}
	}

	@Override
	public void save(Integer id,Integer level,HttpServletRequest request) {
		String name = request.getParameter("name");
		QueryModel queryModel = new QueryModel();
		ShopCategory sc = null;
		if(id!=null&&id>0){
			queryModel.clearQuery();
			queryModel.combPreEquals("id",id);
			sc = (ShopCategory) dateBaseDao.findOne(ShopCategory.class, queryModel);
		}else{
			sc = new ShopCategory();
		}
		sc.setName(name);
		sc.setIsValid(true);
		sc.setLevel(1);
		if(level!=null&&level>0){
			sc.setLevel(2);
			queryModel.clearQuery();
			queryModel.combPreEquals("id",level);
			ShopCategory temp = (ShopCategory) dateBaseDao.findOne(ShopCategory.class, queryModel);
			sc.setShopCategory(temp);
		}
		sc.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		if(sc.getId()==null){
			shopCategoryDao.save(sc);
		}else{
			shopCategoryDao.merge(sc);
		}
	}

	//======================ZL===============================//
	@Override
	public void del(Integer id,HttpServletRequest request) {
		ShopCategory shopCategory=shopCategoryDao.findById(id);
		if (shopCategory.getLevel()==1) {//删除一级类别，连同下级子类一起删除
			QueryModel model=new QueryModel();
			model.combEquals("parentId", id);
			List<ShopCategory> list =dateBaseDao.findLists(ShopCategory.class, model);
			if (list!=null&&list.size()>0) {
				for (ShopCategory shopCategory2:list) {
					shopCategory2.setIsValid(false);
					shopCategoryDao.merge(shopCategory2);
				}
			}
			shopCategory.setIsValid(false);
			shopCategoryDao.merge(shopCategory);
		}
		shopCategory.setIsValid(false);
		shopCategoryDao.merge(shopCategory);
	}

}
