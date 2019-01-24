package com.axp.action.professional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.ZonesDAO;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.Seller;
import com.axp.model.SellerAccountNumber;
import com.axp.model.Shoptypes;
import com.axp.model.Users;
import com.axp.service.professional.BranchesService;
import com.axp.service.professional.SellerService;
import com.axp.service.professional.UserService;
import com.axp.util.LngAndLatUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@RequestMapping("/professional/branches")
@Controller
public class BranchesAction {
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private SellerService sellerService;
	@Resource
	private SellerDAO sellerDAO;
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;
	@Resource
	private UserService userService;
	@Resource
	private BranchesService branchesService;
	@Resource
	private ZonesDAO zonesDAO;

	
	@IsItem(firstItemName = "业务管理", secondItemName = "分店管理")
	@NeedPermission(permissionName = "分店管理", classifyName = "业务管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		String search_name = request.getParameter("search_name")==null?"":request.getParameter("search_name");
		String pagestr = request.getParameter("page");//页码
		
		Seller seller = sellerService.getSellerByAdmin(current_user_id);
		if(seller==null){
			return "professional/seller/error";
		}
		
        StringBuffer paramString = new StringBuffer();//页码条件
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		if(StringUtils.isNotBlank(search_name)){
			queryModel.combPreLike("name",search_name);
		}

		
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("edition", Seller.EDITION_NEW);
		queryModel.combPreEquals("level", Seller.LEVEL_BRANCH);
		queryModel.combPreEquals("adminUser.id", current_user_id,"adminUserId");
		
		PageInfo pageInfo = new PageInfo();
		Integer count = 0;
		count = dateBaseDAO.findCount(Seller.class, queryModel);
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo,pagestr, "10",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		List<Seller> sellerList = dateBaseDAO.findPageList(Seller.class, queryModel, start, end);
		
		pageInfo.setParam(paramString.toString()+"&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("count", count);
		request.setAttribute("sellerList", sellerList);
		return "professional/branches/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Integer id){
		QueryModel queryModel = new QueryModel();
		
		Seller seller = null;
		if(id!=null)
		seller = sellerDAO.findById(id);
		//获取所有省份
		List<ProvinceEnum> zoneList = branchesService.getZone(1);
		//获取所有商家类型
		List<Shoptypes> shoptypeList = branchesService.getShopTypeList();
		//获取商家子账号
		List<SellerAccountNumber> sanList = null;
		int count = 0;
		if(seller!=null){
			queryModel.clearQuery();
			queryModel.combPreEquals("seller.id", seller.getId(),"sellerId");
			queryModel.combPreEquals("level", SellerAccountNumber.LEVEL_SECOND);
			sanList = dateBaseDAO.findLists(SellerAccountNumber.class, queryModel);
			
			count = sanList.size();
		}
		request.setAttribute("seller", seller);
		request.setAttribute("shoptypeList", shoptypeList);
		request.setAttribute("zoneList", zoneList);
		request.setAttribute("sanList", sanList);
		request.setAttribute("count", count);

		return "professional/branches/add";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,Integer id){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		//获取定位城市
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String contacts = request.getParameter("contacts")==null?"":request.getParameter("contacts");
		String phone = request.getParameter("phone")==null?"":request.getParameter("phone");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		String county = request.getParameter("county")==null?"":request.getParameter("county");
		String longitude = request.getParameter("longitude")==null?"":request.getParameter("longitude");
		String latitude = request.getParameter("latitude")==null?"":request.getParameter("latitude");
		String advertwo = request.getParameter("advertwo")==null?"":request.getParameter("advertwo");
		String typeid = request.getParameter("typeid")==null?"":request.getParameter("typeid");
		String[] nums = request.getParameterValues("nums");
		String remark = request.getParameter("remark");
		//查找店铺
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("id",id);	
		Seller seller = (Seller) dateBaseDAO.findOne(Seller.class, queryModel);
		//获取地理位置
		queryModel.clearQuery();
		queryModel.combPreEquals("id", Integer.parseInt(county));
		ProvinceEnum provinceEnum = (ProvinceEnum) dateBaseDAO.findOne(ProvinceEnum.class, queryModel);
		//所属商圈
		queryModel.clearQuery();
		queryModel.combPreEquals("id",Integer.parseInt(advertwo));
		AdminUser adver = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
		//商家类型
		queryModel.clearQuery();
		queryModel.combPreEquals("id", Integer.parseInt(typeid));
		Shoptypes shoptypes = (Shoptypes) dateBaseDAO.findOne(Shoptypes.class, queryModel);
		
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		if(seller==null){
			//对接负责人
			queryModel.clearQuery();
			queryModel.combPreEquals("id", current_user_id);
			AdminUser adminUser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
			seller = new Seller();
			seller.setIsvalid(true);
			seller.setCreatetime(nowTime);
			seller.setCashPoints(0.0);
			seller.setLevel(Seller.LEVEL_BRANCH);
			seller.setEdition(Seller.EDITION_NEW);
			seller.setAdminUser(adminUser);
			seller.setHasVoucher(false);
			seller.setZones(zonesDAO.findById(1));
		}
		
		seller.setAdvertwo(adver);
		seller.setName(name);
		seller.setContacts(contacts);
		seller.setPhone(phone);
		seller.setAddress(address);
		seller.setProvinceEnum(provinceEnum);
		seller.setLongitude(Double.parseDouble(longitude));
		seller.setLatitude(Double.parseDouble(latitude));
		seller.setShoptypes(shoptypes);
		seller.setRemark(remark);
		branchesService.saveSeller(seller,nums);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id){
		Seller seller = sellerDAO.findById(id);
		branchesService.deleteSeller(seller);
		return "redirect:list";
	}
	
	
	
	
	
	
	/**
	 * 判断分店名称是否重复
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkAdminUserNameByAjax")
	@ResponseBody
	public List<String> checkAdminUserNameByAjax(HttpServletRequest request) {
	
		String name = request.getParameter("name");
		String adId = request.getParameter("adId");
		String str = "true";
		QueryModel qm = new QueryModel();

		if(adId.equals("0")){
			qm.clearQuery();
			qm.combPreEquals("name", name);
			qm.combPreEquals("isValid",true);
			List<Seller> adLists = dateBaseDAO.findLists(Seller.class, qm);
			if(adLists!=null&&adLists.size()>0){
				str = "false";
			}
		}else{
			StringBuffer sb = new StringBuffer("select id from seller where name = ? and id != ? and isValid = ?");
			List<Object> values = new ArrayList<Object>();
			values.add(name);
			values.add(Integer.parseInt(adId));
			values.add(true);
				
			List<Object> findBySql = dateBaseDAO.findBySql(sb.toString(), values);
			if(findBySql!=null&&findBySql.size()>0){
					str = "false";
				}
		}
		List<String> strlist = new ArrayList<String>();
		strlist.add(str);
		return strlist;
	}
	/**
	 * 判断子账号是否重复
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkNumByAjax")
	@ResponseBody
	public List<String> checkNumByAjax(HttpServletRequest request){
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String id = request.getParameter("id");
		List<String> list = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		if(StringUtils.isNotBlank(name)){
			queryModel.clearQuery();
			queryModel.combPreEquals("name", name);
			queryModel.combPreNotEquals("seller.id", Integer.parseInt(id),"sellerId");
			Integer count = dateBaseDAO.findCount(SellerAccountNumber.class, queryModel);
			if(count>0){
				list.add("1");
				return list;
			}
			queryModel.clearQuery();
			queryModel.combPreEquals("loginname", name);
			Integer count2 = dateBaseDAO.findCount(AdminUser.class, queryModel);
			if(count2>0){
				list.add("1");
				return list;
			}
		}
		list.add("0");
		return list;
	}
	
	@RequestMapping("/changeZone")
	@ResponseBody
	public Map<String,String> changeZone(Integer zonesid) {
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("provinceEnum.id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(zonesid);
		List<ProvinceEnum> list = provinceEnumDAO.findListByCustom(ProvinceEnum.class,
				keys, values, null, null, null);
		Map<String,String> map = new HashMap<String,String>();
		
		for (ProvinceEnum s : list) {
			map.put(s.getId().toString(), s.getName());
		}
		return map;
	}
	/**
	 * 获取经纬度
	 * @param address
	 * @return
	 */
	@RequestMapping("/getAddressPoint")
	@ResponseBody
	public Map<String, Double> getAddressPoint(String address){

		Map<String, Double> map = LngAndLatUtil.getLngAndLat(address);// 获取地址的经纬度

		return map;
	}
	
	@RequestMapping("/getadvertwo")
	@ResponseBody
	public Map<String, String> getadvertwo(Integer zonesid){
		List<AdminUser> list = userService.getAdminTwo(zonesid);
		Map<String,String> map = new HashMap<String,String>();
	
		for (AdminUser s : list) {
			map.put(s.getId().toString(), s.getLoginname());
		}
		return map;
	}
	@RequestMapping("/checkUserNameByAjax")
	@ResponseBody
	public List<String> checkUserNameByAjax(HttpServletRequest request){
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String id = request.getParameter("id");
		List<String> list = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		if(StringUtils.isNotBlank(name)){
			queryModel.clearQuery();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("name", name);
			Users users = (Users) dateBaseDAO.findOne(Users.class, queryModel);
			//是否存在该粉丝
			if(users!=null){
				//判断该账号是否已绑定其他主账户
				queryModel.clearQuery();
				queryModel.combPreEquals("user.id", users.getId(),"userId");
				queryModel.combPreEquals("level", SellerAccountNumber.LEVEL_MAIN);
				SellerAccountNumber sellerAccountNumber  = (SellerAccountNumber) dateBaseDAO.findOne(SellerAccountNumber.class, queryModel);
				if(sellerAccountNumber!=null){
					if(sellerAccountNumber.getSeller().getId()!=Integer.parseInt(id)){
						//该账号已绑定其他店铺
						list.add("2");
						return list;
					}
				}
			}else{
				//不存在该粉丝账号则返回"1"
				list.add("1");
				return list;

			}
		}
		list.add("0");

		return list;
	}
	
}
