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

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.SellerDAO;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.Seller;
import com.axp.model.SellerAccountNumber;
import com.axp.model.Shoptypes;
import com.axp.model.Users;
import com.axp.service.professional.SellerService;
import com.axp.service.professional.UserService;
import com.axp.util.LngAndLatUtil;
import com.axp.util.QueryModel;

@RequestMapping("/professional/seller")
@Controller
public class SellerAction extends BaseAction {

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

	@IsItem(firstItemName = "业务管理", secondItemName = "店铺管理")
	@NeedPermission(permissionName = "店铺管理", classifyName = "业务管理")
	@RequestMapping("/add")
	public String add(HttpServletRequest request){
		QueryModel queryModel = new QueryModel();
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		Seller seller = sellerService.getSellerByAdmin(current_user_id);
		if(seller==null){
			return "professional/seller/error";
		}
		//获取所有省份
		List<ProvinceEnum> zoneList = sellerService.getZone(1);
		//获取所有商家类型
		List<Shoptypes> shoptypeList = sellerService.getShopTypeList();
		//获取主商家账号	
		queryModel.clearQuery();
		queryModel.combPreEquals("seller.id", seller.getId(),"sellerId");
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("level", SellerAccountNumber.LEVEL_MAIN);
		SellerAccountNumber sanMain = (SellerAccountNumber) dateBaseDAO.findOne(SellerAccountNumber.class, queryModel);
		//获取商家子账号
		queryModel.clearQuery();
		queryModel.combPreEquals("seller.id", seller.getId(),"sellerId");
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("level", SellerAccountNumber.LEVEL_SECOND);
		List<SellerAccountNumber> sanList = dateBaseDAO.findLists(SellerAccountNumber.class, queryModel);
		int count = 0;
		if(sanList!=null){
			count = sanList.size();
		}
		request.setAttribute("seller", seller);
		request.setAttribute("shoptypeList", shoptypeList);
		request.setAttribute("zoneList", zoneList);
		request.setAttribute("sanMain", sanMain);
		request.setAttribute("sanList", sanList);
		request.setAttribute("count", count);
		return "professional/seller/add";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,Integer id){
		//获取定位城市
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String contacts = request.getParameter("contacts")==null?"":request.getParameter("contacts");
		String phone = request.getParameter("phone")==null?"":request.getParameter("phone");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		String county = request.getParameter("county")==null?"":request.getParameter("county");
		String longitude = request.getParameter("longitude")==null?"":request.getParameter("longitude");
		String latitude = request.getParameter("latitude")==null?"":request.getParameter("latitude");
		//String advertwo = request.getParameter("advertwo")==null?"":request.getParameter("advertwo");
		String typeid = request.getParameter("typeid")==null?"":request.getParameter("typeid");
		String mainnum = request.getParameter("mainnum")==null?"":request.getParameter("mainnum");
		String imageurls_logo = request.getParameter("imageurls_logo")==null?"":request.getParameter("imageurls_logo");
		String[] nums = request.getParameterValues("nums");
		//查找店铺
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("id",id);	
		Seller seller = (Seller) dateBaseDAO.findOne(Seller.class, queryModel);
		seller.setName(name);
		seller.setContacts(contacts);
		seller.setPhone(phone);
		seller.setAddress(address);
		seller.setVerifyStatus(1);
		seller.setLongitude(Double.parseDouble(longitude));
		seller.setLatitude(Double.parseDouble(latitude));
		
		sellerService.updateSeller(seller,mainnum,nums);
		return "redirect:add";
	}
	
	@RequestMapping("/resettingpwd")
	public String resettingpwd(Integer id){
		userService.updateAdPWD(id);
		return "redirect:add";
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
				queryModel.combPreEquals("isValid", true);
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
	
	
	
	
	@RequestMapping("/checkAdminUserNameByAjax")
	@ResponseBody
	public List<String> checkAdminUserNameByAjax(HttpServletRequest request) {
	
		String loginname = request.getParameter("loginname");
		String adId = request.getParameter("adId");
		String str = "true";
		QueryModel qm = new QueryModel();

		if(adId=="0"){
			qm.clearQuery();
			qm.combPreEquals("loginname", loginname);
			qm.combPreEquals("isValid",true);
			List<AdminUser> adLists = dateBaseDAO.findLists(AdminUser.class, qm);
			if(adLists!=null&&adLists.size()>0){
				str = "false";
			}
		}else{
			StringBuffer sb = new StringBuffer("select id from admin_user where loginname = ? and id != ? and isValid = ?");
			List<Object> values = new ArrayList<Object>();
			values.add(loginname);
			values.add(adId);
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
	
	@IsItem(firstItemName = "业务管理", secondItemName = "商家绑定")
	@NeedPermission(permissionName = "商家绑定", classifyName = "业务管理")
	@RequestMapping("/binding")
	public String binding(HttpServletRequest request){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		request.setAttribute("adminUser", adminUser);
		if(adminUser.getSellerId()!=null){
			Seller seller = sellerDAO.findById(adminUser.getSellerId());
			request.setAttribute("seller", seller);
		}
		return "/professional/seller/binding";
	}
	
	@RequestMapping("/bindingSave")
	@ResponseBody
	public Map<String, Object> bindingSave(Integer isNewSeller, Integer sellerId, String sellerName, HttpServletRequest request){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		Seller seller = null;
		if(adminUser.getSellerId()==null){
			if(isNewSeller!=null&&isNewSeller==1){
				seller = sellerDao.findByName(sellerName);
				if(seller!=null){
					QueryModel model = new QueryModel();
					model.combPreEquals("sellerId", seller.getId());
					model.combPreEquals("isvalid", true);
					List<AdminUser> orther = dateBaseDao.findPageList(AdminUser.class, model, 0, 1);
					if(orther.size() > 0){
						statusMap.put("status", -2);
						statusMap.put("message", "该商家已被绑定");
						return statusMap;
					}
					seller.setAdminUser(adminUser);
					sellerDAO.update(seller);
				}else{
					statusMap.put("status", -1);
					statusMap.put("message", "不存在名为\""+sellerName+"\"的商家店铺！");
					return statusMap;
				}
			}else{
				seller = new Seller();
				seller.setIsvalid(true);
				seller.setName(sellerName);
				seller.setAdminUser(adminUser);
				seller.setCreatetime(new Timestamp(System.currentTimeMillis()));
				seller.setCashPoints(0.0);
				seller.setLevel(Seller.LEVEL_MAIN);
				seller.setHasVoucher(false);
				seller.setEdition(Seller.EDITION_NEW);
				sellerDAO.save(seller);
			}
			adminUser.setSellerId(seller.getId());
			adminUserDao.update(adminUser);
			statusMap.put("status", 1);
			statusMap.put("message", "绑定成功");
		}else{
			seller = sellerDao.findById(sellerId);
			seller.setAdminUser(null);
			adminUser.setSellerId(null);
			sellerDAO.update(seller);
			adminUserDao.update(adminUser);
			statusMap.put("status", 1);
			statusMap.put("message", "解绑成功");
		}
		return statusMap;
	}
	
	
}
