package com.axp.action.adver;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.dao.IBuyDao;
import com.axp.model.AdminUser;
import com.axp.model.Buy;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;


@Controller
@RequestMapping("/adverBuy")
public class AdverBuyAction extends BaseAction{

	@Autowired
	private IBuyDao buyDao;
	
	
	@IsItem(firstItemName = "广告管理", secondItemName = "我的购买")
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
         String pagestr = request.getParameter("page");
         
     	int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
     	AdminUser user = adminUserDao.findById(current_user_id);
		PageInfo pageInfo = new PageInfo();
		int count = 0;
		

		StringBuffer con = new StringBuffer("isValid=true and buy = "+user.getId() +" ");
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combCondition(con.toString());
		queryModel.setOrder(" id desc");
		count = dateBaseDao.findCount(Buy.class, queryModel);
		
		Utility.setPageInfomation(pageInfo, pagestr, "10",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		pageInfo.setParam("&page=");
		Hashtable<Integer, String> quantityHash = StringUtil.getQuantityHash();
		List<Buy> buylist = dateBaseDao.findPageList(Buy.class, queryModel, start, end);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("buylist", buylist);
		request.setAttribute("quantityHash", quantityHash);
		return "adverBuy/list";
	}
	
	@RequestMapping("/add")
	 public ModelAndView add(HttpServletRequest request){
			ModelAndView model = new ModelAndView();
			model.setViewName("adverBuy/add");		
			return model;
	}
	
	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request){
		ModelAndView model = new ModelAndView("redirect:list");
        String pagestr = request.getParameter("page");
 
    	int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
    	String quantity = request.getParameter("quantity");
    	AdminUser user = adminUserDao.findById(current_user_id);
    	
		Buy sc = new Buy();
		
		if(user.getParentId()!=null){
			sc.setSell(user.getParentId());
			sc.setSellname("上级用户");
		}else{
			sc.setSell(47);
			sc.setSellname("总部");
			
		}
		sc.setQuantity(Integer.parseInt(quantity));
		sc.setStatus(1);
		sc.setAdminUser(user);
		sc.setIsvalid(true);
		sc.setLevel(65);
		sc.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		
		buyDao.save(sc);	
		
		return model;
	}
}
