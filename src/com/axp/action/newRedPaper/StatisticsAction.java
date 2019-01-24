package com.axp.action.newRedPaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.form.newRedPaper.NewRedPaperTotal;
import com.axp.model.AdminUser;
import com.axp.model.Seller;
import com.axp.service.newRedPaper.StatisticsService;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Controller
@RequestMapping("/newRedPaper/statistics")
public class StatisticsAction {

	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private StatisticsService statisticsService;

	@IsItem(firstItemName = "红包管理", secondItemName = "红包概况统计")
	@NeedPermission(permissionName = "红包概况统计", classifyName = "红包管理")
	@RequestMapping("/newRedPaperCountList")
	public String newRedPaperCountList(HttpServletRequest request){
		List<NewRedPaperTotal> tList = statisticsService.newRedPaperCountList(request);
		request.setAttribute("list", tList);
		return "newRedPaper/statistics/newRedPaperCountList";
	}
	
	
	/*public String newRedPaperCountList(HttpServletRequest request,String search_id,String search_name){
		request.setAttribute("search_id", search_id);
		request.setAttribute("search_name", search_name);
		String cityId = request.getParameter("cityId")==""?null:request.getParameter("cityId");		
		String allianceId = request.getParameter("allianceId")==""?null:request.getParameter("allianceId");	
		String sellerId = request.getParameter("sellerId")==""?null:request.getParameter("sellerId");
		String centerId = request.getParameter("centerId")==""?null:request.getParameter("centerId");

		QueryModel qm = new QueryModel();
		if(search_name!=null){
			try {	
				search_name=new String((search_name).getBytes("ISO-8859-1"),"utf8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
			//回显功能
		if(StringUtils.isNotBlank(cityId)){
			if(Integer.valueOf(cityId).intValue()!=-1){
				qm.clearQuery();
				qm.combEquals("id",cityId);
				AdminUser allia = dateBaseDAO.findLists(AdminUser.class, qm).get(0);
				request.setAttribute("cityKey", allia.getId());
				request.setAttribute("cityValue", allia.getUsername());
				}
			}
			if(StringUtils.isNotBlank(allianceId)){
				if(Integer.valueOf(allianceId).intValue()!=-1){
				qm.clearQuery();
				qm.combEquals("id",allianceId);
				AdminUser allia = dateBaseDAO.findLists(AdminUser.class, qm).get(0);
				request.setAttribute("allianceKey", allia.getId());
				request.setAttribute("allianceValue", allia.getUsername());
				}
			}
			if(StringUtils.isNotBlank(sellerId)){
				if(Integer.valueOf(sellerId).intValue()!=-1){
				qm.clearQuery();
				qm.combEquals("id",sellerId);
				Seller se = dateBaseDAO.findLists(Seller.class, qm).get(0);
				request.setAttribute("sellerKey", se.getId());
				request.setAttribute("sellerValue", se.getName());
				}
			}
					
			List<NewRedPaperTotal> tList = statisticsService.newRedPaperCountList(search_id, search_name, sellerId, allianceId, cityId, centerId);
			request.setAttribute("list", tList);
				
			
			//拿到运营中心列表；
			qm.clearQuery();
			qm.combEquals("level",85);
			qm.combPreEquals("isvalid",true);
			List<AdminUser> adUserList = dateBaseDAO.findLists(AdminUser.class, qm);
						
			request.setAttribute("centerList", adUserList);  
			return "newRedPaper/statistics/newRedPaperCountList";
	  }*/
	
	@RequestMapping("/findCity")
	@ResponseBody
	public Map<String, String> findCity(HttpServletResponse response,Integer centerId) {
		
		response.setContentType("text/html; charset=UTF-8");
		QueryModel qm = new QueryModel();
		qm.combEquals("level", StringUtil.ADVERONE);
		qm.combEquals("parentId", centerId);
		List<AdminUser> adList = dateBaseDAO.findLists(AdminUser.class,qm);
		Map<String, String> map = new HashMap<String, String>();
		for(AdminUser a :adList){
			map.put(Integer.valueOf(a.getId()).toString(), a.getUsername());
		}
			
		return map;
	}
  @RequestMapping("/findAlliance")
  @ResponseBody
  public Map<String, String> findAlliance(HttpServletResponse response,Integer cityId){
		response.setContentType("text/html; charset=UTF-8");
		QueryModel qm = new QueryModel();
		qm.combEquals("level", StringUtil.ADVERTWO);
		qm.combEquals("parentId", cityId);
		List<AdminUser> adList = dateBaseDAO.findLists(AdminUser.class,qm);
		Map<String, String> map = new HashMap<String, String>();
		for(AdminUser a :adList){
			map.put(Integer.valueOf(a.getId()).toString(), a.getUsername());
		}
		return map;	
  }
  @RequestMapping("/findSeller")
  @ResponseBody
  public Map<String, String> findSeller(HttpServletResponse response,Integer allianceId){
		response.setContentType("text/html; charset=UTF-8");
		QueryModel qm = new QueryModel();
		qm.combPreEquals("adminUser.id", allianceId,"adminUserId");
		List<Seller> sellerList = dateBaseDAO.findLists(Seller.class,qm);
		Map<String, String> map = new HashMap<String, String>();
		for(Seller sell :sellerList){
			map.put(Integer.valueOf(sell.getId()).toString(), sell.getName());
		}		
		return map;
  }
}
