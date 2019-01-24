package com.axp.action.professional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.SellerDAO;
import com.axp.model.AdminUser;
import com.axp.service.professional.SeliveService;
import com.axp.service.professional.SellerService;
@Controller
@RequestMapping("/professional/seLive")
public class SeliveAction extends BaseAction{
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private AdminUserDAO adminUserDAO;
	@Resource
	private SeliveService seliveService;
	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private SellerService sellerService;
	@NeedPermission(permissionName="直播管理",classifyName="业务管理")
	@IsItem(firstItemName="业务管理",secondItemName="直播管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		seliveService.getPageList(request, response);
		return"professional/selive/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Integer id){
		seliveService.add(request, id);
		return "professional/selive/add";
	}
	
	@RequestMapping("/save")
	public String save(Integer id, Integer sId,String livename, String image, String liveUri,
			String sellerName, String name,String sellerAddress, String sellerLogo,String remark,
			Timestamp createtime, Timestamp begintime,Timestamp endtime,String imgRecommend,
			HttpServletRequest request, HttpServletResponse response,String istop){
		seliveService.save(id, sId, livename, image, liveUri, sellerName, sellerAddress, sellerLogo, remark, begintime, endtime, imgRecommend, request, response, name,istop);
		return "redirect:list";
	}
	@RequestMapping("/del")
	public String del(String ids, HttpServletRequest request, HttpServletResponse response){
		seliveService.del(ids);
		return "redirect:list";
	}
	@ResponseBody 
	@RequestMapping("/toTop")
	public String ajaxSubmit(Integer sid,HttpServletRequest request, HttpServletResponse response,String istop){
		Map<String,Object> map =new HashMap<String,Object>();
		try{
			Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
	        AdminUser adminUser = adminUserService.findById(adminUserId);
	        if(adminUser.getLevel()>=75){
	        	if("1".equals(istop)){
					seliveService.ajaxTop(sid,true);
				}else{
					seliveService.ajaxTop(sid,false);
				}
	        	map.put("msg",true);
	        	map.put("message","success!");
	        }else{
	        	map.put("msg",false);
	        	map.put("message","access denied!");
	        }
		}catch(Exception e){
		   map.put("msg",false);
		   map.put("message","fail！");
		}
		Object obj = JSONObject.fromObject(map);
		obj.toString();
		return obj.toString();
	}

	
}
