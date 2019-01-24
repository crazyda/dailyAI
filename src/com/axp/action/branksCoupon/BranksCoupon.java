/**
 * 2018-12-24
 * Administrator
 */
package com.axp.action.branksCoupon;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.util.httpClient.HttpRequest;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.BranksDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProductDAO;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.Branks;
import com.axp.model.Product;
import com.axp.model.Users;
import com.axp.service.BranksCoupon.AgentProductService;
import com.axp.service.BranksCoupon.BranksCouponService;
import com.axp.service.BranksCoupon.ProductService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;
import com.weixin.util.MD5Util;


@RequestMapping("/BranksCoupon")
@Controller
public class BranksCoupon extends BaseAction{
	
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private BranksCouponService branksCouponService;
	@Resource
	private ProductService productService;
	@Resource
	private BranksDAO branksDAO;
	@Resource
	private AgentProductService agentProductService;
	
	
	
	
	@NeedPermission(permissionName = "银行兑换产品列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "银行兑换产品列表")
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		if(adminUser == null){
			request.getSession().setAttribute("errorstr", "您尚未登录");
          return "error/error";//登录出错情况；
		}
		branksCouponService.findProduct(request,response,adminUser);
		request.setAttribute("adminUser", adminUser);
		
		return "/branksCoupon/ProductList";
		
	}
	
	@RequestMapping("/addProduct")
	public String addProduct(HttpServletRequest request, HttpServletResponse response){
		String pid = request.getParameter("pid");
		QueryModel model  = new QueryModel();
		model.clearQuery();
		List<Branks> branks = dateBaseDAO.findLists(Branks.class , model);
		model.clearQuery();
		model.combPreEquals("isValid", true);
		model.combPreEquals("level", 55);
		request.setAttribute("branks", branks);
		List<AdminUser> adminUsers = dateBaseDAO.findLists(AdminUser.class, model);
		request.setAttribute("adminUsers", adminUsers);
		if(pid == null || "".equals(pid)){
			
			return "/branksCoupon/addProduct";
		}else{
			
			String bid = request.getParameter("bid");
			String aid = request.getParameter("aid");
			Product product = branksCouponService.findByProduct(Integer.valueOf(pid));
			Branks brank = branksDAO.findById(Integer.valueOf(bid));
			AdminUser adminUser = adminUserDao.findById(Integer.valueOf(aid));
			request.setAttribute("brank", brank);
			request.setAttribute("adminUser", adminUser);
			request.setAttribute("product", product);
			return "/branksCoupon/addProduct";
		}
		
	}
	
	
	@RequestMapping("/saveProduct")
	@ResponseBody
	public Map<String,Object> saveProduct(HttpServletRequest request, HttpServletResponse response){
		String adminUserId = request.getParameter("adminUserId");
		String brankId = request.getParameter("brankId");
		String productName = request.getParameter("productName");
		String[] productImg = request.getParameterValues("productImg[]");
		String score = request.getParameter("score");
		String num = request.getParameter("num");
		String remark = request.getParameter("remark");
		String id = request.getParameter("id");
		Map<String,Object> result = productService.saveProduct(id,adminUserId,brankId,productName,productImg,score,num,remark);
		
		return result;
		
	}
	/**
	 * 总部删除商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Map<String,Object> result = productService.delete(id);
		
		return result;
		
	}
	
	
	
	//代理获得产品
	@NeedPermission(permissionName = "代理可兑换产品", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "代理可兑换产品")
	@RequestMapping("/getProductToAgent")
	public String getProductToAgent(HttpServletRequest request, HttpServletResponse response){
		int adminUserId = (Integer)request.getSession().getAttribute("currentUserId");//用户id 
		String search = request.getParameter("search_name");
		String branksId = request.getParameter("branksId");
		String dlId = request.getParameter("dlId");
		String pagestr = request.getParameter("page");//页码
		AdminUser adminUser = adminUserDao.findById(adminUserId);
		request.setAttribute("adminUser", adminUser);
		Branks brank = null;
		if(branksId != "-1" && branksId != null){
			brank = branksDAO.findById(Integer.valueOf(branksId));
			
		}
		List<AdminUser> adminUsers = null ;
		if(adminUser.getLevel()>=95){
			adminUsers = adminUserDao.findByLevel(75);
			request.setAttribute("adminUsers", adminUsers);
		}else if(adminUser.getLevel() == 75){
			request.setAttribute("adminUser", adminUser);
			
		}else if(adminUser.getLevel() == 65){ //联盟合伙人
			adminUser  = adminUser.getParentAdminUser();
			
		}
		if(!"-1".equals(dlId) && dlId != null && dlId != ""){
			adminUser = adminUserDao.findById(Integer.valueOf(dlId));
		}
		if(pagestr == null){
			pagestr = "1";
		}
		productService.getProductToAgent(request,adminUser,search,brank,pagestr);
		request.setAttribute("branks",branksDAO.findAll());
		request.setAttribute("branksId", branksId);		
		request.setAttribute("dlId", dlId);
		return "/branksCoupon/agentProduct";
		
		
	}
	//代理 添加产品
	@RequestMapping("/saveAgentProduct")
	@ResponseBody
	public Map<String,Object> saveAgentProduct(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		
		if(adminUser == null){
			map.put("code", -1);
			map.put("msg", "您尚未登录");
          return map;//登录出错情况；
		}
		String productId = request.getParameter("pid");
		
		map = agentProductService.saveAgentProduct(adminUser,productId);
		return map;
		
	}
	/**
	 * 代理删除商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/del_dl")
	@ResponseBody
	public Map<String,Object> del_dl(HttpServletRequest request, HttpServletResponse response){
		
		String id = request.getParameter("id");
		Map<String,Object> result = agentProductService.del(id);
		
		return result;
		
	}
	/**
	 * 合伙人添加产品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(HttpServletRequest request, HttpServletResponse response){
		String productId = request.getParameter("id");
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		Map<String,Object> map = agentProductService.addHhrProduct(adminUser,productId);
		return map;
		
	}
	//合伙人产品库
	@NeedPermission(permissionName = "合伙人产品库", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "合伙人产品库")
	@RequestMapping("/ProductLibrary")
	public String ProductLibrary(HttpServletRequest request, HttpServletResponse response){
		int adminUserId = (Integer)request.getSession().getAttribute("currentUserId");//用户id 
		String search = request.getParameter("search_name");
		String branksId = request.getParameter("branksId");
		String pagestr = request.getParameter("page");//页码
		AdminUser adminUser = adminUserDao.findById(adminUserId);
		request.setAttribute("adminUser", adminUser);
		Branks brank = null;
		if(branksId != "-1" && branksId != null){
			brank = branksDAO.findById(Integer.valueOf(branksId));
			
		}
		if(pagestr == null){
			pagestr = "1";
		}
		productService.getProductToAgent(request, adminUser, search, brank, pagestr);
		//agentProductService.getHhrProduct(request,adminUser,search,brank,pagestr);
		request.setAttribute("branks",branksDAO.findAll());
		request.setAttribute("branksId", branksId);	
		request.setAttribute("adminUser", adminUser);
		return "/branksCoupon/hhrProduct";
		
		
	}
	
	//合伙人填兑换码
	@RequestMapping("/addCode")
	@ResponseBody
	public Map<String ,Object> addCode(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		int adminUserId = (Integer)request.getSession().getAttribute("currentUserId");//用户id 
		String phone = request.getParameter("phone");
		String id=  request.getParameter("id");
		String redeemCode = request.getParameter("redeemCode");
		
		AdminUser adminUser = adminUserDao.findById(adminUserId);
		if(adminUser == null){
			map.put("code", -1);
			map.put("msg", "您尚未登录");
          return map;
		}
		Users user = usersDAO.findByPhone(phone);
		if(user == null){
			map.put("code", -2);
			map.put("msg", "手机号码还不是每天积分用户!");
			return map;
		}
		
		map = agentProductService.addCode(adminUser,user,id,redeemCode);
		return map;
		
	}
	
	//上游收分用户 查看兑换码并验证
	@NeedPermission(permissionName = "兑换码列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "兑换码列表")
	@RequestMapping("/codeList")
	public String codeList(HttpServletRequest request, HttpServletResponse response){
		int adminUserId = (Integer)request.getSession().getAttribute("currentUserId");//用户id 
		AdminUser adminUser = adminUserDao.findById(adminUserId);
		String branksId = request.getParameter("branksId")=="" ?"0":request.getParameter("branksId");
		String search = request.getParameter("search_name");
		String pagestr = request.getParameter("page");
		if(adminUser == null){
			request.getSession().setAttribute("errorstr", "您尚未登录");
          return "error/error";//登录出错情况；
		}
		if(pagestr == null){
			pagestr = "1";
		}
		redeemCodeService.getRedeemCodeList(request,adminUser,branksId,search,pagestr);
		request.setAttribute("level", adminUser.getLevel());
		
		return "/branksCoupon/codeList";
		
	}
	
	@RequestMapping("/seeCode")
	@ResponseBody
	public Map<String,Object> seeCode(HttpServletRequest request, HttpServletResponse response){
		Map<String ,Object> map = new HashMap<String,Object>();
		int adminUserId = (Integer)request.getSession().getAttribute("currentUserId");//用户id 
		AdminUser adminUser = adminUserDao.findById(adminUserId);
		if(adminUser == null){
			map.put("code", -1);
			map.put("msg", "您尚未登录");
          return map;
		}else if(adminUser.getLevel() != 55){
			map.put("code", -3);
			map.put("msg", "不是收分用户,无权查看");
          return map;
		}
		String id = request.getParameter("id");
		String isVerify = request.getParameter("isVerify");
		map = redeemCodeService.getRedeemCode(Integer.valueOf(id),isVerify);
		
		
		return map;
		
	}
	
	
	
	
}
