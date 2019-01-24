package com.axp.action.cashShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.CashShopTypeDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ICommodityTypeDao;
import com.axp.model.AdminUser;
import com.axp.model.CashshopType;
import com.axp.model.CommodityType;
import com.axp.model.ImageType;
import com.axp.service.cashShop.CashShopTypeService;
import com.axp.service.cashShop.CommodityTypeService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;



@RequestMapping("/cashShop/cashShopType")
@Controller
public class CashShopTypeAction {

	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private CashShopTypeDAO cashShopTypeDAO;
	@Resource
	private CashShopTypeService cashShopTypeService;
	@Resource
	private ICommodityTypeDao commodityTypeDao;
	@Resource
	private CommodityTypeService commodityTypeService;
	@NeedPermission(permissionName = "图文管理", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "图文管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,String search_name){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("id", current_user_id);
		AdminUser user = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);

		String param = "";
        String pagestr = request.getParameter("page");
        List<String> keys = new ArrayList<String>();
 		keys.add("isValid = ?");
 		List<Object> values = new ArrayList<Object>();
 		values.add(true);
 		if(user!=null&&user.getLevel()!=null&&user.getLevel()<StringUtil.ADMIN){
 			keys.add("adminUser.id=?");
 			values.add(user.getId());
 		}
 		if(StringUtils.isNotBlank(search_name)){
 			keys.add(" remark like ?");
 			values.add("%"+search_name+"%");
 			param += "&search_name="+search_name;
 		}
 		
		PageInfo pageInfo = new PageInfo();
		int count = 0;

		try{
	        count = Integer.parseInt(cashShopTypeDAO.findCountByCustom(CashshopType.class,keys, values).toString());
		}catch(Exception ee){
		}
		Utility.setPageInfomation(pageInfo, pagestr, "20",
				count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		pageInfo.setParam("&page="+param);
		List<CashshopType> list = cashShopTypeDAO.findListByCustom(CashshopType.class, keys, values, start,end," order by createTime desc ");
		request.setAttribute("list", list);
		request.setAttribute("pageFoot", pageInfo
				.getCommonDefaultPageFootView());
		return "cashShop/cashShopType/typeList";
	}
	
	
	
	
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request ,Integer id){	
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		List<ImageType> typeList = cashShopTypeService.getImageType(current_user_id);
		CommodityType commodityType = null;
		if(id!=null){
			CashshopType cashshopType = cashShopTypeDAO.findById(id);
			commodityType = cashshopType.getCommodityType();
			Integer typeId = cashshopType.getImageType().getId();
			request.setAttribute("cashshopType", cashshopType);
			request.setAttribute("typeId", typeId);
			request.setAttribute("commodityType", commodityType);
		}
		
		
		List<CommodityType> clist =  commodityTypeService.getType(1);
		request.setAttribute("clist", clist);
		request.setAttribute("typeList", typeList);
		
		return "cashShop/cashShopType/add";
	}
	@RequestMapping("/save")
	public String save(HttpServletRequest request,CashshopType cashshopType,Integer id, Integer typeId,Integer typeid){
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		String img = request.getParameter("img");
		String img2 = request.getParameter("img2");
		String name = request.getParameter("name");
		String remark = request.getParameter("remark");
		String url = request.getParameter("url");
		
		ImageType imageType= null;
		QueryModel queryModel =new QueryModel();
		queryModel.combPreEquals("id", current_user_id);
		AdminUser  adminUser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
		CommodityType commodityType = null;
		if (id != null ) {
			queryModel.clearQuery();
			queryModel.combPreEquals("id", id);
			cashshopType = (CashshopType) dateBaseDAO.findOne(CashshopType.class, queryModel);
		} else {
			cashshopType = new CashshopType();
		}
		if(typeId !=null ){
			queryModel.clearQuery();
			queryModel.combPreEquals("id", typeId);
			imageType = (ImageType) dateBaseDAO.findOne(ImageType.class,queryModel);
		}
		
		if (typeid!=null) {
			 commodityType = commodityTypeDao.findById(typeid);
		}
		cashshopType.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		cashshopType.setImg(img);
		if(StringUtils.isNotBlank(img2)&&typeId<4){
			cashshopType.setImg2(img2);
		}
		cashshopType.setRemark(remark);
		cashshopType.setName(name);
		cashshopType.setIsValid(true);
		cashshopType.setAdminUser(adminUser);
		cashshopType.setImageType(imageType);
		cashshopType.setCommodityType(commodityType);
		
		if(StringUtils.isNotEmpty(url)){
			if(url.startsWith("http")||url.startsWith("https")){
				cashshopType.setUrl(url);					
			}
		}	
		cashShopTypeService.saveCashShopType(cashshopType);	
		return "redirect:list";
	}
	
	@RequestMapping("/del")
	public String del(Integer id){	
		if(id!=null){		
			cashShopTypeDAO.updatePropertyByID("isValid", false, id, CashshopType.class);		
		}
		return "redirect:list";
	}
	
	
	
	@RequestMapping("/changeType")
	@ResponseBody
	public Map<String, String> changeType (Integer typeid){
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("commodityType.id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(typeid);
		List<CommodityType> list = commodityTypeDao.findListByCustom(CommodityType.class,
				keys, values, null, null, null);
		Map<String,String> map = new HashMap<String,String>();
		
		for (CommodityType c : list) {
			map.put(c.getId().toString(), c.getName());
		} 
		return map;
	}
}
