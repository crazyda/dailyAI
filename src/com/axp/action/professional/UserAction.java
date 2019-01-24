package com.axp.action.professional;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.AdminUserZonesDAO;
import com.axp.dao.DataBaseDao;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.RePermissionRoleDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.Bonus;
import com.axp.model.DLScoreMark;
import com.axp.model.Product;
import com.axp.model.ProvinceEnum;
import com.axp.model.RePermissionRole;
import com.axp.model.ScoreMark;
import com.axp.model.Seller;
import com.axp.model.Shoptypes;
import com.axp.model.SignCalc;
import com.axp.model.Users;
import com.axp.service.professional.UserService;
import com.axp.util.CalcUtil;
import com.axp.util.LngAndLatUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.ResponseResult;
import com.axp.util.RoleUtil;
import com.axp.util.StringUtil;
import com.axp.util.Utility;
import com.rongcloud.methods.User;
import com.weixin.util.MD5Util;

@RequestMapping("/professional/user")
@Controller
public class UserAction {

	@Autowired
	private DateBaseDAO dateBaseDAO;
	@Resource
	private UserService userService;
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;
	@Autowired
	private AdminUserDAO adminUserDAO;
	@Autowired
	private RePermissionRoleDAO rePermissionRoleDAO;
	@Autowired
	private AdminUserZonesDAO auzDAO;
	@Autowired
	private SellerDAO sellerDAO;
	@IsItem(firstItemName = "业务管理", secondItemName = "联盟合伙人设置")
	@NeedPermission(permissionName = "联盟合伙人列表", classifyName = "业务管理")
	@RequestMapping("/list")
	public String list(Integer backState ,HttpServletRequest request,HttpServletResponse response){	
		String pagestr = request.getParameter("page");//页码
        StringBuffer paramString = new StringBuffer();//页码条件
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");//用户id
        int level = (Integer)request.getSession().getAttribute("userLevel");//用户等级
        String search_name = request.getParameter("search_name")==null?"":request.getParameter("search_name");
        String levelname = request.getParameter("levelname")==null?"":request.getParameter("levelname");
       
        
        AdminUser aus = adminUserDAO.findById(current_user_id);
        	if(!StringUtils.isEmpty(search_name)){
        		search_name =search_name.trim();
        	}
        Hashtable<String, String> leveltable = null;
        switch (level) {
        case StringUtil.SUPERADMIN:
            leveltable = StringUtil.getSuperadmin();
            break;
        case StringUtil.ADMIN:
            leveltable = StringUtil.getAdmin();
            break;
        case StringUtil.ADVERCENTER:
            leveltable = StringUtil.getAdvercenter();
            break;
        case StringUtil.ADVERONE:
            leveltable = StringUtil.getAdverporxyone();
            break;
        case StringUtil.ADVERTWO:
            leveltable = StringUtil.getAdverporxytwo();
            break;
        default:
            break;

    }
		request.setAttribute("leveltable", leveltable);
		
		request.setAttribute("levelid", levelname);
        
        
        QueryModel queryModel = new QueryModel();
        if(StringUtils.isNotBlank(levelname)){
        	queryModel.combPreEquals("level", Integer.parseInt(levelname));
        }
        queryModel.combPreEquals("isValid", true);
        
        PageInfo pageInfo = new PageInfo();
        Utility utility = new Utility();
        
        
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
       
		if(backState==null||backState==20){

			
			if(search_name!=null&&StringUtils.isNotBlank(search_name)){
	        	queryModel.combLike("loginname", search_name);
	        }
	       
			
		        if(level<=StringUtil.ADMIN){//如果是总部以下的用户，找出他所有下级用户
		        	String childIds = null;
		        	if(StringUtils.isNotBlank(levelname)){
		        		childIds = userService.getChildsAdminUserByLevel(current_user_id,Integer.parseInt(levelname));	
		        	}else{
		        		childIds = userService.getChildsAdminUser(current_user_id);
		        	}
		        		queryModel.combIn("id", childIds);
		        		
		        }
		        Integer count = 0;
		        count = dateBaseDAO.findCount(AdminUser.class, queryModel);
		       
		        utility.setPageInfomation(pageInfo,pagestr, "50",count);
		        List<AdminUser> asList = dateBaseDAO.findPageList(AdminUser.class, queryModel, start, end);
		        Map<String,String> levelMap = RoleUtil.getAdmin();
		        for(AdminUser au : asList){
		        	if(au.getRePermissionRoleId()!=null){
		        		RePermissionRole role =  rePermissionRoleDAO.findById(au.getRePermissionRoleId());
		        		au.setLevelname(role==null?"":role.getName());
		        	}else{
		        		au.setLevelname(levelMap.get(au.getLevel()+""));
		        	}
		        }
		        queryModel.clearQuery();
		        queryModel.combPreEquals("adminUser.id", current_user_id,"adminUserId");
				double maid = dateBaseDAO.findSum(Bonus.class, "maid", queryModel);
		        pageInfo.setParam("&levelname="+levelname+"&search_name="+search_name+"&page=");
		        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		        request.setAttribute("asList", asList);
		        request.setAttribute("count", count);
		        request.setAttribute("levelMap", levelMap);
		        request.setAttribute("search_name", search_name);
		        request.setAttribute("aus", aus);
		        request.setAttribute("maid", CalcUtil.mul(maid, 0.5/100, 4));
			return "professional/users/list_1";
		}else{
			
			if(search_name!=null&&StringUtils.isNotBlank(search_name)){
	        	queryModel.combLike("name", search_name);
	        }
			 
			 aus.getInvitecode();
			 QueryModel model = new QueryModel();
			 model.clearQuery();
			 model.combPreEquals("isValid", true);
			 model.combPreEquals("invitecode", aus.getInvitecode());
			 int count = dateBaseDAO.findCount(Users.class, model);
		     utility.setPageInfomation(pageInfo,pagestr, "10",count);
		     List<User> users = dateBaseDAO.findPageList(Users.class, model, start, end);
		     queryModel.clearQuery();
			double maid = dateBaseDAO.findSum(Bonus.class, "maid", queryModel);
		     
		     
		     pageInfo.setParam("&levelname="+levelname+"&search_name="+search_name+"&page=");
	        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	        request.setAttribute("asList", users);
	        request.setAttribute("count", count);
	        request.setAttribute("search_name", search_name);
	        request.setAttribute("aus", aus);
	        request.setAttribute("maid", maid);
			return "professional/users/list_0";
			
		}
	}
	
	
	@IsItem(firstItemName = "业务管理", secondItemName = "积分池列表")
	@NeedPermission(permissionName = "积分池列表", classifyName = "业务管理")
	@RequestMapping("/teamList")
	public String teamList(HttpServletRequest request,HttpServletResponse response){	
		String pagestr = request.getParameter("page");//页码
        StringBuffer paramString = new StringBuffer();//页码条件
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");//用户id
        int level = (Integer)request.getSession().getAttribute("userLevel");//用户等级
        
        
        Hashtable<String, String> leveltable = null;
        switch (level) {
        case StringUtil.SUPERADMIN:
            leveltable = StringUtil.getSuperadmin();
            break;
        case StringUtil.ADMIN:
            leveltable = StringUtil.getAdmin();
            break;
        case StringUtil.ADVERCENTER:
            leveltable = StringUtil.getAdvercenter();
            break;
        case StringUtil.ADVERONE:
            leveltable = StringUtil.getAdverporxyone();
            break;
        case StringUtil.ADVERTWO:
            leveltable = StringUtil.getAdverporxytwo();
            break;
        default:
            break;

    }
		request.setAttribute("leveltable", leveltable);
		
		AdminUser aus = adminUserDAO.findById(current_user_id);
        
        QueryModel queryModel = new QueryModel();
        if(level==StringUtil.ADVERONE ){ //代理
        	queryModel.combPreEquals("adminUser.id", current_user_id,"adminuserId");
        }else if(level==StringUtil.ADVERTWO){ //合伙人
        	
        	queryModel.combPreEquals("adminUser.id", aus.getParentId(),"adminuserId");
        }
       
    	PageInfo pageInfo = new PageInfo();
		int count = 0;
		count = dateBaseDAO.findCount(ScoreMark.class, queryModel);
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo,pagestr, "50",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		queryModel.setOrder("refreshTime DESC");
		List<ScoreMark> asList = dateBaseDAO.findPageList(ScoreMark.class, queryModel, start, end);
		Map<String,String> levelMap = RoleUtil.getAdmin();
		pageInfo.setParam("&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("asList", asList);
		request.setAttribute("count", count);
        request.setAttribute("levelMap", levelMap);
        request.setAttribute("aus", aus);
		return "professional/users/teamList";
	}
	
	@RequestMapping("/updateTeamStatus")
	public void updateTeamStatus(HttpServletRequest request,HttpServletResponse response,Integer id,Integer currentPage,String search_name,Boolean isTeam){
			AdminUser adminUser = adminUserDAO.findByIdValid(id);
			adminUser.setIsTeam(isTeam);
			adminUserDAO.merge(adminUser);
			//Map<String,String> levelMap = RoleUtil.getAdmin();
			//String levelname = request.getParameter("levelname")==null?"":request.getParameter("levelname");
		/*	request.setAttribute("search_name", search_name);
			request.setAttribute("levelMap", levelMap);
			request.setAttribute("levelname", levelname);
			request.setAttribute("currentPage", currentPage==null?0:currentPage);*/
			
			//return "forward:/professional/user/teamList";
			
			//如果想要回显数据 就把参数拼成url  然后ajax成功后就直接跳转到url
			
		    ResponseResult.printResult(response);
		
	}
	
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Integer id){
		Integer level =  (Integer) request.getSession().getAttribute("userLevel");
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		//获取当前级别所能创建的角色
		List<RePermissionRole> levelMap = rePermissionRoleDAO.findByPropertyWithValid("adminusers.id", current_user_id);
		List<ProvinceEnum> zoneList=null;
		List<ProvinceEnum> distrtList=null;
		AdminUser au = adminUserDAO.findById(current_user_id);
		request.setAttribute("iscity", "no");
		List<AdminUser> lmsjs = null;
		if(level>=95){
		//获取所有省份
			zoneList = userService.getZone(1);
		}else if(level==75){
			if (au.getProvinceEnum().getLevel2()==2&&au.getProvinceEnum().getLevel()==3||au.getProvinceEnum().getLevel()==3) { //说明是 县级市 查自己
				distrtList = provinceEnumDAO.findByPropertyWithValid("id", au.getProvinceEnum().getId());
			}else{
				distrtList = provinceEnumDAO.findByPropertyWithValid("provinceEnum2.id", au.getProvinceEnum().getId());
			}  
			request.setAttribute("iscity", "yes");
			
			//获取代理下的联盟合伙人
			 lmsjs = adminUserDAO.findByunion(current_user_id);
			
		}else {
			 zoneList = userService.getZoneById(au.getProvinceEnum().getId(), level);
		}
		//获取所有商家类型
		List<Shoptypes> shoptypeList = userService.getShopTypeList();
		
		//获取用户为商家的数据
		List<Seller> sellerList = sellerDAO.getSellerListByAdminId(id);
		
		
		
		if(id!=null){
			AdminUser adminUser = adminUserDAO.findById(id);
			request.setAttribute("adminUser", adminUser);
			request.setAttribute("show", "yes");
		}
		request.setAttribute("issq", "no");
		Hashtable<String, String> leveltable = null;
		switch (level) {
        case StringUtil.SUPERADMIN:
            leveltable = StringUtil.getSuperadmin();
            break;
        case StringUtil.ADMIN:
            leveltable = StringUtil.getAdmin();
            break;
        case StringUtil.ADVERCENTER:
            leveltable = StringUtil.getAdvercenter();
            break;
        case StringUtil.ADVERONE:
            leveltable = StringUtil.getAdverporxyone();
            break;
        case StringUtil.ADVERTWO:
            leveltable = StringUtil.getAdverporxytwo();
            request.setAttribute("issq", "yes");
            break;
        default:
            break;

    }
		
		if(sellerList!=null && sellerList.size()>0){
			request.setAttribute("sellername", sellerList.get(0).getName());
		}
		request.setAttribute("lmsjs", lmsjs);
		request.setAttribute("current_user", au);
		request.setAttribute("leveltable", leveltable);
		request.setAttribute("level", levelMap);
		request.setAttribute("zoneList", zoneList);
		request.setAttribute("distrtList", distrtList);
		request.setAttribute("shoptypeList", shoptypeList);
		request.setAttribute("sellerList", sellerList);
		if(level == 75){
			return "professional/users/addhhr";
		}else{
			return "professional/users/add";
			
		}
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		Integer userLevel = (Integer) request.getSession().getAttribute("userLevel");
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("id", current_user_id);
		AdminUser parentAuser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
		
		String loginname = request.getParameter("loginname")==null?"":request.getParameter("loginname");
		String username = request.getParameter("username")==null?"":request.getParameter("username");
		String sellername = request.getParameter("sellername")==null?"":request.getParameter("sellername");
		String contacts = request.getParameter("contacts")==null?"":request.getParameter("contacts");
		String phone = request.getParameter("phone")==null?"":request.getParameter("phone");
		String level = request.getParameter("level");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		String pw = request.getParameter("pw")==null?"":request.getParameter("pw");
		String levelname = request.getParameter("levelname");
		String province = request.getParameter("province")==null?"":request.getParameter("province");
		String city = request.getParameter("city")==null?"":request.getParameter("city");
		String county = request.getParameter("county")==null?"":request.getParameter("county");
		String integral = request.getParameter("integral");
		String scoreMax = request.getParameter("integralMax");//改代理最大的积分数
		String union = request.getParameter("union")==null?"0.0":request.getParameter("union");//积分占比
		String id = request.getParameter("adminUserId");
		if(integral==null || integral==""  ){
			integral = "0";
		}
		if(scoreMax==null || scoreMax==""){
			scoreMax = "0";
		}
		Integer zoneId;
		if (levelname.equals("75")&&StringUtils.isNotBlank(county)) {
			ProvinceEnum enum1 = provinceEnumDAO.findById(Integer.parseInt(county));
			if (enum1.getLevel2()==2) {
				zoneId = userService.getZoneId(province, city, county);
			}else{
				zoneId=userService.getZoneId(province,city,null);
			}
		}else{
			zoneId = userService.getZoneId(province,city,county);
		}
		String longitude =  StringUtils.isEmpty(request.getParameter("longitude"))?"0.0":request.getParameter("longitude");
		String latitude =  StringUtils.isEmpty(request.getParameter("latitude"))?"0.0":request.getParameter("latitude");
		String typeid =  StringUtils.isEmpty(request.getParameter("typeid"))?"":request.getParameter("typeid");
		String advertwo = request.getParameter("advertwo")==null?"":request.getParameter("advertwo");
		String radius = StringUtils.isEmpty(request.getParameter("radius"))?"0":request.getParameter("radius");
		//获取店铺所属位置
		queryModel.clearQuery();
		queryModel.combPreEquals("id",zoneId );
		ProvinceEnum provinceEnum = (ProvinceEnum) dateBaseDAO.findOne(ProvinceEnum.class, queryModel);
		AdminUser adminUser;
		Seller seller = null;
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		if(id!=null){
			queryModel.clearQuery();
			queryModel.combPreEquals("id", Integer.valueOf(id));
			adminUser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
			adminUser.setScore((adminUser.getScore()==null?0:adminUser.getScore())+Integer.valueOf(integral)); //添加申请的积分
			
		}else{
			adminUser = new AdminUser();
			adminUser.setIsvalid(true);
			adminUser.setPassword(Utility.MD5("888888"));
			adminUser.setCreatetime(nowTime);
			adminUser.setCenter(0);
			adminUser.setProxyOne(0);
			adminUser.setQuantity(0);
			adminUser.setHasVoucher(false);	
			adminUser.setScore(Integer.valueOf(integral)); //添加申请的积分
			adminUser.setScoreMax(Integer.valueOf(scoreMax));
			
		}
	
		if(Integer.parseInt(levelname)==60){
			//获取定位城市
			if(adminUser.getSellerId()!=null){
				//创建店铺
				queryModel.clearQuery();
				queryModel.combPreEquals("id",adminUser.getSellerId());
				seller = sellerDAO.findById(adminUser.getSellerId());
			}else{
				seller = new Seller();
			}
			
			seller.setIsvalid(true);
			seller.setName(sellername);//商家名称
			seller.setAdminUser(adminUser);
			seller.setCreatetime(nowTime);
			seller.setCashPoints(0.0);
			seller.setHasVoucher(false);
			seller.setLevel(0);
			
			if(provinceEnum!=null){
				seller.setProvinceEnum(provinceEnum);
			}
			//经度
			seller.setLongitude(Double.parseDouble(longitude));
			//纬度
			seller.setLatitude(Double.parseDouble(latitude));
			//商家类型
			queryModel.clearQuery();
			queryModel.combPreEquals("id", 1);
			Shoptypes shoptypes = (Shoptypes) dateBaseDAO.findOne(Shoptypes.class, queryModel);
			//所属商圈
			if(StringUtils.isNotBlank(advertwo)){
				queryModel.clearQuery();
				queryModel.combPreEquals("id",Integer.parseInt(advertwo));
				AdminUser adver = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
				seller.setAdvertwo(adver);
			}
			seller.setShoptypes(shoptypes);
			seller.setEdition(Seller.EDITION_NEW);
			seller.setVerifyStatus(1);
			
		}
		adminUser.setParentAdminUser(parentAuser);
		adminUser.setParentId(current_user_id);
		adminUser.setAddress(address);
		adminUser.setLevel(Integer.parseInt(levelname));
		adminUser.setRePermissionRoleId(Integer.parseInt(level));
		adminUser.setLasttime(new Timestamp(System.currentTimeMillis()));
		adminUser.setLoginname(loginname);
		adminUser.setUsername(username);
		adminUser.setContacts(contacts);
		adminUser.setPhone(phone);
		adminUser.setInvitecode("1"+phone);//这个用户的邀请码
		adminUser.setStatus(AdminUser.STATUS_NOAUDITING);
		adminUser.setMoney(0.0d);
		adminUser.setScoreSurplus((adminUser.getScoreSurplus()==null?0:adminUser.getScoreSurplus())+Integer.valueOf(integral)); //积分累计添加
		if(provinceEnum!=null){
			adminUser.setProvinceEnum(provinceEnum);
		}
		//经度
		adminUser.setLongitude(Double.parseDouble(longitude));
		//纬度
		adminUser.setLatitude(Double.parseDouble(latitude));
		adminUser.setRadius(Double.parseDouble(radius));
		
		
		String[] provinceIds=request.getParameterValues("provinceIds");
		
		
		userService.saveAdminUserByP(adminUser,seller,provinceIds);
		
		//联盟合伙人 关联用户版账号 这里计算他的 占比
		if("65".equals(levelname)){
			Double unions = CalcUtil.div(Double.valueOf(union),parentAuser.getScoreMax(), 6);
			adminUser.setLmUnion(unions);
			adminUser.setLoginname(phone);
			userService.saveAdminUserByUser(adminUser,seller,phone);
			
		}
		
		if( userLevel==StringUtil.SUPERADMIN ||userLevel==StringUtil.ADMIN || userLevel==StringUtil.ADVERONE  ){
			
			userService.saveIntegralRecord(adminUser,Integer.valueOf(integral),userLevel);
		}
		return "redirect:list";
	}
	
	@RequestMapping("/bonusList")
	public String bonus(HttpServletRequest request,HttpServletResponse response){
		
		String pagestr = request.getParameter("page");//页码
        StringBuffer paramString = new StringBuffer();//页码条件
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");//用户id
        int level = (Integer)request.getSession().getAttribute("userLevel");//用户等级
        
        Hashtable<String, String> leveltable = null;
        switch (level) {
        case StringUtil.SUPERADMIN:
            leveltable = StringUtil.getSuperadmin();
            break;
        case StringUtil.ADMIN:
            leveltable = StringUtil.getAdmin();
            break;
        case StringUtil.ADVERCENTER:
            leveltable = StringUtil.getAdvercenter();
            break;
        case StringUtil.ADVERONE:
            leveltable = StringUtil.getAdverporxyone();
            break;
        case StringUtil.ADVERTWO:
            leveltable = StringUtil.getAdverporxytwo();
            break;
        default:
            break;

    }
		request.setAttribute("leveltable", leveltable);
		AdminUser aus = adminUserDAO.findById(current_user_id);
        
        QueryModel queryModel = new QueryModel();
        if(level==StringUtil.ADVERONE ){ //代理
        	queryModel.combPreEquals("adminUser.id", current_user_id,"adminuserId");
        }else if(level==StringUtil.ADVERTWO){ //合伙人
        	queryModel.combPreEquals("adminUser.id", aus.getParentId(),"adminuserId");
        }
        PageInfo pageInfo = new PageInfo();
		Integer count = 0;
		count = dateBaseDAO.findCount(Bonus.class, queryModel);
		double maid = dateBaseDAO.findSum(Bonus.class, "maid", queryModel);
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo,pagestr, "10",count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		queryModel.setOrder("createTime DESC");
		List<Bonus> asList = dateBaseDAO.findPageList(Bonus.class, queryModel, start, end);
		
		//maid奖金池总收益 以一定比例分给代理人们 和合伙人们 然后在各自平分 
		//代理级别用户 75
//		queryModel.clearQuery();
//		queryModel.combPreEquals("level", 75);
//		queryModel.combPreEquals("isvalid", true);
//		int dlCount = dateBaseDAO.findCount(AdminUser.class, queryModel);
//		double maiddl = CalcUtil.div(CalcUtil.mul(maid, 0.5, 4),dlCount , 4);
		
		//合伙人级别用户 65
//		queryModel.clearQuery();
//		queryModel.combPreEquals("level", 65);
//		queryModel.combPreEquals("isvalid", true);
//		int hhrCount = dateBaseDAO.findCount(AdminUser.class, queryModel);
//		double maidhhr = CalcUtil.div(CalcUtil.mul(maid, 0.5, 4), 0.03, 4);
		
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("adminUser.id", current_user_id,"adminuserId");
		queryModel.combIsNull("adminUserSeller.id");
		int scoreCount = dateBaseDAO.findCount(DLScoreMark.class, queryModel); // 代理池总积分(可用积分)
		
		Map<String,String> levelMap = RoleUtil.getAdmin();
		pageInfo.setParam("&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("asList", asList);
		request.setAttribute("count", count);
        request.setAttribute("levelMap", levelMap);
        request.setAttribute("aus", aus);
        request.setAttribute("maid", maid);
        request.setAttribute("scoreCount", scoreCount);
		return "professional/users/list_2";
		
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id){
		userService.deleteAd(id);
		return "redirect:list";
	}
	
	
	
	@RequestMapping("/resettingpwd")
	public String resettingpwd(Integer id){
		userService.updateAdPWD(id);
		return "redirect:list";
	}
	
	@RequestMapping("/checkSellerNum")
	@ResponseBody
	public Map<String,Object> checkSellerNum(HttpServletRequest request){
		Integer level = (Integer)request.getSession().getAttribute("userLevel");//用户等级
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(level == StringUtil.ADVERTWO){
			Integer count = userService.getAdminUserNum(current_user_id)==null?0:userService.getAdminUserNum(current_user_id);
			map.put("num", count);
			if(count>300){
				map.put("check", false);
			}else{
				map.put("check",true);
			}
		}
		return map;
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
	
	@RequestMapping("/checkAdminUserNameByAjax")
	@ResponseBody
	public List<String> checkAdminUserNameByAjax(HttpServletRequest request) {
	
		String loginname = request.getParameter("loginname");
		String adId = request.getParameter("adId");
		String str = "true";
		QueryModel qm = new QueryModel();

		if(adId=="0" || adId == null){
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
	

	@RequestMapping("/checkphoneByAjax")
	@ResponseBody
	public Map<String,Object> checkphoneByAjax(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String loginname = request.getParameter("phone");
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("phone", loginname);
		model.combPreEquals("isValid", true);
		List<Users> findBySql = dateBaseDAO.findLists(Users.class, model);
		if(findBySql!=null&&findBySql.size()>0){
			model.clearQuery();
			model.combPreEquals("fansId", findBySql.get(0).getId());
			model.combPreEquals("isValid", true);
			List<AdminUser> findBySqlToAdminUser = dateBaseDAO.findLists(AdminUser.class, model);
			if(findBySqlToAdminUser!=null&&findBySqlToAdminUser.size()>0){
				map.put("message", "该手机号用户已经是合伙人了!");
				map.put("code", -1);
			}
		}else{
			map.put("message", "该号码还不是每天积分用户,请先使用每天积分APP注册登录!");
			map.put("code", -1);
		}
		
		return map;
	
	
	}
	@RequestMapping("/signList")
	@ResponseBody
	public String signList(HttpServletRequest request){
		String pagestr = request.getParameter("page");//页码
        StringBuffer paramString = new StringBuffer();//页码条件
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");//用户id
		AdminUser aus = adminUserDAO.findById(current_user_id);
        
        QueryModel queryModel = new QueryModel();
        queryModel.combPreEquals("", current_user_id);
        PageInfo pageInfo = new PageInfo();
		Integer count = 0;
		count = dateBaseDAO.findCount(SignCalc.class, queryModel);
		double maid = dateBaseDAO.findSum(Bonus.class, "maid", queryModel);
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo,pagestr, "10",count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		queryModel.setOrder("createTime DESC");
		List<Bonus> asList = dateBaseDAO.findPageList(Bonus.class, queryModel, start, end);
		
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("adminUser.id", current_user_id,"adminuserId");
		queryModel.combIsNull("adminUserSeller.id");
		int scoreCount = dateBaseDAO.findCount(DLScoreMark.class, queryModel); // 代理池总积分(可用积分)
		
		Map<String,String> levelMap = RoleUtil.getAdmin();
		pageInfo.setParam("&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("asList", asList);
		request.setAttribute("count", count);
        request.setAttribute("levelMap", levelMap);
        request.setAttribute("aus", aus);
        request.setAttribute("maid", maid);
        request.setAttribute("scoreCount", scoreCount);
		return "professional/users/list_2";
	}
	
	
	//新增上游收分用户
	@IsItem(firstItemName = "业务管理", secondItemName = "收分用户列表")
	@NeedPermission(permissionName = "收分用户列表", classifyName = "业务管理")
	@RequestMapping("/shoufenyUser")
	public String shuofenyUserList(HttpServletRequest request,HttpServletResponse response){
		
		String pagestr = request.getParameter("page");//页码
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");//用户id
        String search_name = request.getParameter("search_name")==null?"":request.getParameter("search_name");
        AdminUser aus = adminUserDAO.findById(current_user_id);
    	if(!StringUtils.isEmpty(search_name)){
    		search_name =search_name.trim();
    	}
    	  
        PageInfo pageInfo = new PageInfo();
        Utility utility = new Utility();
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        
        QueryModel model = new QueryModel();
        model.clearQuery();
        if(!StringUtils.isEmpty(search_name)){
    		search_name =search_name.trim();
    		model.combLike("loginname", search_name);
    	}
        Integer count = 0;
        model.combPreEquals("level", 55);
        model.combPreEquals("isValid", true);
        count = dateBaseDAO.findCount(AdminUser.class, model);
        utility.setPageInfomation(pageInfo,pagestr, "50",count);	
	    List<AdminUser> asList = dateBaseDAO.findPageList(AdminUser.class, model, start, end);
	    request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	    request.setAttribute("asList", asList);
	    request.setAttribute("count", count);
	    request.setAttribute("search_name", search_name);
        
		return "professional/users/shoufenyUsers";
		
	}
	
	
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request,HttpServletResponse response){
		Integer current_user_id = (Integer)request.getSession().getAttribute("currentUserId");//用户id
		List<RePermissionRole> levelMap = rePermissionRoleDAO.findByPropertyWithValid("adminusers.id", current_user_id);
		if(current_user_id == null){
			request.getSession().setAttribute("errorstr", "您尚未登录");
          return "error/error";//登录出错情况；
		}
		String id = request.getParameter("id");	
		request.setAttribute("level", levelMap);
		request.setAttribute("leveltable", StringUtil.getAdmin());
		if("" == id || null == id){
			return "professional/users/addsfUser";
		}else{
			AdminUser adminUser = adminUserDAO.findById(Integer.valueOf(id));
			request.setAttribute("result", adminUser);
			return "professional/users/addsfUser";
		}
		
		
	}
	
	@RequestMapping("/delUser")
	@ResponseBody
	public Map<String,Object> delUser(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");	
		Map<String, Object> result= new HashMap<String,Object>();
		if("" == id || null == id){
			result.put("success",-1);
			result.put("msg","删除失败");
		}else{
			AdminUser adminUser = adminUserDAO.findById(Integer.valueOf(id));
			adminUser.setIsvalid(false);
			adminUser.setLasttime(new Timestamp(System.currentTimeMillis()));
			adminUserDAO.saveOrUpdate(adminUser);
			result.put("success",1);
			result.put("msg","删除成功");
		}
		return result;
		
		
		
	}
	
	/**
	 * 新增上游用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveUser")
	public String saveUser(HttpServletRequest request, HttpServletResponse response){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser au = adminUserDAO.findById(current_user_id);
		String id = request.getParameter("id");
		String loginname = request.getParameter("loginname");
		String contacts = request.getParameter("contacts");
		String phone = request.getParameter("phone");
		String level = request.getParameter("level");
		String levelname = request.getParameter("levelname");
		String totalMoney = request.getParameter("totalMoney");
		AdminUser adminUser = new AdminUser();
		
		if(!"".equals(id)){
			adminUser = adminUserDAO.findById(Integer.valueOf(id));
		}
		adminUser.setCashPoints(0d);
		adminUser.setCreatetime(new Timestamp(System.currentTimeMillis()));
		adminUser.setIsvalid(true);
		adminUser.setScore(0);
		adminUser.setPhone(phone);
		adminUser.setContacts(contacts);
		adminUser.setLoginname(loginname);
		adminUser.setUsername(contacts);
		adminUser.setPassword(MD5Util.GetMD5Code("888888"));
		adminUser.setMoney(0.0);
		adminUser.setTotalMoney(Double.valueOf(totalMoney));
		adminUser.setParentAdminUser(au);
		adminUser.setParentId(current_user_id);
		adminUser.setLevel(Integer.parseInt(levelname));
		adminUser.setInvitecode("1"+phone);
		adminUser.setScoreMax(0);
		adminUser.setScoreSurplus(0);
		adminUser.setRePermissionRoleId(Integer.parseInt(level));
		adminUserDAO.saveOrUpdate(adminUser);
		
		return "redirect:shoufenyUser";
		
	}
	
	
}

