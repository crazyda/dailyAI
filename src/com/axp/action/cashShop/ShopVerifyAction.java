package com.axp.action.cashShop;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.RePermissionRoleDAO;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.RePermissionRole;
import com.axp.service.cashShop.ShopCategoryService;
import com.axp.service.order.ReGoodsorderService;
import com.axp.service.professional.UserService;
import com.axp.util.QueryModel;

@Controller
@RequestMapping("/cashShop/store")
public class ShopVerifyAction extends BaseAction{
	@Resource
	private UserService userService;
	@Resource
	private ShopCategoryService shopCategoryService;
	@Resource
	private RePermissionRoleDAO rePermissionRoleDao;
	@Resource
	private  AdminUserDAO adminUserDAO;
	@Resource
	private ProvinceEnumDAO provinceEnumDAO;
	@Resource
	private ReGoodsorderService reGoodsorderService;
	@NeedPermission(permissionName = "已审核店铺", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "已审核店铺")
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response){
		sellerService.getVerifycompletedList(request, response);
		return "cashShop/storeVerify/StoreVerifyInfo";
	}
	
	@NeedPermission(permissionName = "店铺审核", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "店铺审核")
	@RequestMapping("/listOfStoreVerify")
	public String listOfStoreVerify(HttpServletRequest request,HttpServletResponse response){
         sellerService.getSellerList(request, response);
		return "cashShop/storeVerify/listOfStoreVerify";
	}
	
	@RequestMapping("/detailOfStoreVerify")
	public String detailOfStoreVerify(HttpServletRequest request, Integer sellerId){
		sellerService.detailOfStoreVerify(request, sellerId);
		return "cashShop/storeVerify/detailOfStoreVerify";
	}
	
	
	@RequestMapping("/submitSellerReview2")
	@ResponseBody
	public Map<String, Object> submitSellerReview2(Integer id, Integer verifyStatus ,Integer parent,Integer adminuserId,HttpServletRequest request,Integer zoneId){
		
		
		Map<String, Object> returnMap = new HashMap<>();
		 if (id == null || id <= 0) {
	            returnMap.put("message", "参数错误：没有id值；");
	            return returnMap;
	        }
		 try {
			returnMap = sellerService.doReview(returnMap, id, verifyStatus, adminuserId, request,zoneId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnMap;
	}
	
	
	/*
	 * 管理用户
	 * */
	@RequestMapping("changeAdminByDistribute")
	@ResponseBody
	public Map<String, Object> changeAdminByDistribute (Integer adminuserId,Integer zoneId){
		AdminUser adminUser = adminUserDAO.findById(adminuserId);
		ProvinceEnum enum1 = null;
		enum1 = adminUser.getProvinceEnum();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> zone1 = new HashMap<String,String>();
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("isValid", true);
		if (enum1!=null&&enum1.getLevel()==2) {
			queryModel.combPreEquals("parentId", enum1.getId());
		}else{
			queryModel.combPreEquals("id", enum1.getId());
		}
		List<ProvinceEnum> plist = dateBaseDao.findLists(ProvinceEnum.class, queryModel);
		for (ProvinceEnum enum2 :plist) {
			zone1.put(enum2.getId().toString(), enum2.getName());
		}
		
		
		List<String> keys = new ArrayList<String>();
		keys.add("isValid = ?");
		keys.add("adminusers.id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(true);
		values.add(adminUser.getId());
		List<RePermissionRole> list=adminUserDao.findListByCustom(RePermissionRole.class, 
				keys, values, null, null, null);
		Map<String,String> role = new HashMap<String,String>();
		for (RePermissionRole r : list) {
			if (r.getAdminusers()!=null) {
				role.put(r.getId().toString(), r.getName());
			}
		} 
		map.put("role",role);
		map.put("zone",zone1);
		return map;
	}
	
	@RequestMapping("/submitSellerReview")
	@ResponseBody
	public Map<String, Object> submitSellerReview(Integer id, Integer verifyStatus ,Integer parent,Integer adminuserId,HttpServletRequest request) throws Exception {
		//返回值；
        Map<String, Object> returnMap = new HashMap<>();
        //检查参数；
        if (id == null || id <= 0) {
            returnMap.put("message", "参数错误：没有id值；");
            return returnMap;
        }
        returnMap.put("message", "参数错误：没有id值；");
        return returnMap;

        //审核操作；
		//return sellerService.doReview(returnMap, id, verifyStatus,adminuserId,request);
	}
	
	
	@NeedPermission(permissionName = "店铺类别管理", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "店铺类别管理")
	@RequestMapping("/typeOflist")
	public String typeOflist(HttpServletRequest request,HttpServletResponse response){
		shopCategoryService.list(request,response);
		return "cashShop/storeVerify/StoreTypeOflist";
	}
	
	@RequestMapping("/add")
	public String add(Integer id,HttpServletRequest request,HttpServletResponse response){
		shopCategoryService.add(id, request);
		return "cashShop/storeVerify/addStoreType";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,Integer id,Integer level){
		shopCategoryService.save(id, level, request);
		return "redirect:typeOflist";
	}
	
	@RequestMapping("/del")
	public String del(HttpServletRequest request,Integer id,Integer level){
		shopCategoryService.del(id,request);
		return "redirect:typeOflist";
	}
	
	
	@NeedPermission(permissionName = "店铺销量", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "店铺销量")
	@RequestMapping("/storeSales")
	public String storeSales(HttpServletRequest request, String mallType,Integer currentPage,Integer pageSize,String sTM,String eTM){
		
		try {
			
			
		if(currentPage==null){
			currentPage=1;
		}
		if(pageSize==null)
		{
			pageSize=10;
		}
		if(mallType==null||mallType.equals("0")){
			mallType="xx";
			return "cashShop/storeVerify/storeSales";
		}
		
		reGoodsorderService.getOrderSales(request,mallType,currentPage,pageSize,sTM,eTM);
		
		} catch (Exception e) {
			e.printStackTrace();
			}
		
		return "cashShop/storeVerify/storeSales";
		
	}
	
	
	@NeedPermission(permissionName="修改销量",classifyName="商城管理")
	@IsItem(firstItemName="商城管理", secondItemName="修改销量")
	@RequestMapping("/editSalesVolume")
	public String editSalesVolume(HttpServletRequest request,HttpServletResponse response,String type,String sellerName){
		reGoodsorderService.editSalesVolume(request, response, type, sellerName);
		return "cashShop/storeVerify/editSalesVolume";
	}
	
	
	@RequestMapping("/saveSalesVolume")
	@ResponseBody
	public Object saveSalesVolume(HttpServletRequest request,HttpServletResponse response,String[] ids,String type){
		ids =request.getParameterValues("ids[]");
		type= request.getParameter("type");
		reGoodsorderService.saveSalesVolume(request, response, ids,type);
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("result", "修改成功");
		return res;
	}
	
	 @RequestMapping("/zhiding")
    @ResponseBody
    public Map<String, Object> zhiding(HttpServletRequest request, HttpServletResponse response) {

        return reGoodsorderService.zhiding(request, response);
    }
	
	
	
	
	
}
