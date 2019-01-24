package com.axp.action.professional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.SellerDAO;
import com.axp.model.Seller;
import com.axp.model.SellerMainPage;
import com.axp.service.professional.SellerMainPageService;
import com.axp.service.professional.SellerService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@RequestMapping("/professional/sellerMainPage")
@Controller
public class SellerMainPageAction {
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private SellerService sellerService;
	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private SellerMainPageService sellerMainPageService;

	@IsItem(firstItemName = "业务管理", secondItemName = "店铺主页管理")
	@NeedPermission(permissionName = "店铺主页列表", classifyName = "业务管理")
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
		return "professional/sellerMainPage/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Integer id){
		Seller seller = sellerDAO.findById(id);
		
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("seller.id", id,"sellerId");
		SellerMainPage sellerMainPage = (SellerMainPage) dateBaseDAO.findOne(SellerMainPage.class, model);
		request.setAttribute("seller", seller);
		request.setAttribute("sellerMainPage", sellerMainPage);
		
		return "professional/sellerMainPage/add";
	}
	
	@RequestMapping("/save")
	public String save(Seller formSeller,HttpServletRequest request,Integer id ,Integer pageId,@RequestParam(value="url",required=false) String[] usrls,
			@RequestParam(value="imgurls",required=false) String[] imgurls ) throws ParseException{
		
		//店铺介绍
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		//营业时间
		String beginTime = request.getParameter("beginTime")==null?"":request.getParameter("beginTime");
		String endTime = request.getParameter("endTime")==null?"":request.getParameter("endTime");
		
		//店铺名LOGO
		String logo = request.getParameter("imageurls1")==null?"":request.getParameter("imageurls1");
		//店铺视频
		String view = request.getParameter("imageurls2")==null?"":request.getParameter("imageurls2");
		String viewurl = request.getParameter("viewurl")==null?"":request.getParameter("viewurl");
		//横幅广告
		String banner = request.getParameter("imageurls3")==null?"":request.getParameter("imageurls3");
		String bannerurl = request.getParameter("bannerurl")==null?"":request.getParameter("bannerurl");
		
		QueryModel queryModel = new QueryModel();
		SellerMainPage  sellerMainPage = null;
		if(pageId==null){
			sellerMainPage = new SellerMainPage();
			sellerMainPage.setIsValid(true);
			queryModel.clearQuery();
			queryModel.combPreEquals("id", id);
			Seller seller = (Seller) dateBaseDAO.findOne(Seller.class,queryModel);
			sellerMainPage.setSeller(seller);
		}else{
			queryModel.clearQuery();
			queryModel.combPreEquals("id", pageId);
			sellerMainPage = (SellerMainPage) dateBaseDAO.findOne(SellerMainPage.class, queryModel);
		}
		
		
		//营业时间
		sellerMainPage.setBeginTime(beginTime);
		sellerMainPage.setEndTime(endTime);
		//
		if(StringUtils.isNotBlank(remark)){
			sellerMainPage.setRemark(remark);
		}
		
		sellerMainPage.setSellerLogo(logo);
		
		if(imgurls!=null){
			List<Map<String,String>> ad_list = new ArrayList<Map<String,String>>();
			Map<String,String> ad_map = null;
			for(int i=0;i<imgurls.length;i++){
				if(imgurls[i]!=null&&StringUtils.isNotBlank(imgurls[i])){
					ad_map = new HashMap<String, String>();
					ad_map.put("img", imgurls[i]);
					ad_map.put("url", usrls[i].equals("NOCHAR")?"":usrls[i]);
					ad_list.add(ad_map);
				}
			}
			JSONArray ad_json = JSONArray.fromObject(ad_list);
			sellerMainPage.setTopCarouselAd(ad_json.toString());
		}
			List<Map<String,String>> view_list = new ArrayList<Map<String,String>>();
			Map<String,String> view_map = new HashMap<String, String>();
			view_map.put("img", view);
			view_map.put("url", viewurl==null?"":viewurl);
			view_list.add(view_map);
			JSONArray view_json = JSONArray.fromObject(view_list);
			sellerMainPage.setSellerView(view_json.toString());

			List<Map<String,String>> banner_list = new ArrayList<Map<String,String>>();
			Map<String,String> banner_map = new HashMap<String, String>();
			banner_map.put("img", banner);
			banner_map.put("url", bannerurl==null?"":bannerurl);
			banner_list.add(banner_map);
			JSONArray banner_json = JSONArray.fromObject(banner_list);
			sellerMainPage.setBannerAd(banner_json.toString());
			
		sellerMainPageService.saveSellerMainPage(sellerMainPage);
		
		return "redirect:list";
	}
}
