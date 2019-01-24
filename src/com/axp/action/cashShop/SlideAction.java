package com.axp.action.cashShop;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.SlidesDAO;
import com.axp.model.AdminUser;
import com.axp.model.Slides;
import com.axp.service.cashShop.SlideService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Controller
@RequestMapping("/cashShop/slide")
public class SlideAction {

	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private SlidesDAO slidesDAO;
	@Resource
	private SlideService slideService;
	
	@Resource
	private AdminUserDAO adminUserDAO;

	@NeedPermission(permissionName = "商城广告列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "商城广告")
	@RequestMapping("list")
	public String list(HttpServletRequest request) {

		QueryModel model = new QueryModel();

		String pagestr = request.getParameter("page");

		PageInfo pageInfo = new PageInfo();
		int count = 0;
		//String con="isvalid=true";
		HttpSession session = request.getSession();
		int current_user_id = (Integer) session.getAttribute("currentUserId");

		model.clearQuery();
		model.combPreEquals("id", current_user_id);
		AdminUser au = (AdminUser) dateBaseDAO.findOne(AdminUser.class, model);

		StringBuffer con = new StringBuffer("isvalid=true");
		List<Object> valuesList = new ArrayList<Object>();//用于存储参数值的

		if (au.getLevel() < StringUtil.ADMIN) {
			con.append(" and adminUser.id = ?");
			valuesList.add(current_user_id);
		}
		Object[] value = new Object[valuesList.size()];
		for (int i = 0; i < valuesList.size(); i++) {
			value[i] = valuesList.get(i);
		}

		try {
			//count = sdao.getAllCountAndCon(Slides.class,con);
			count = slidesDAO.getCountsByCon(Slides.class, con.toString(), value);

		} catch (Exception e) {

		}
		Utility.setPageInfomation(pageInfo, pagestr, "20", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		pageInfo.setParam("&page=");
		//List<Slides> slst = sdao.findByPageOrderAndCon(Slides.class, con, "id", "desc",start,end);
		List<Slides> slst = slidesDAO.getListByCon(Slides.class, con.toString(), start, end, " order by id desc", value);
		request.setAttribute("slst", slst);
		request.setAttribute("count", count);
		request.setAttribute("au", au);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		return "/cashShop/slide/list";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, Slides slides) {
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		if (StringUtils.isNotBlank(id) && Integer.parseInt(id) > 0) {
			QueryModel model = new QueryModel();
			model.clearQuery();
			model.combPreEquals("id", Integer.parseInt(id));
			Slides sc = (Slides) dateBaseDAO.findOne(Slides.class, model);
			slides = sc;
			request.setAttribute("sc", sc);
		}
		HttpSession session = request.getSession();
	
		int current_user_id = (Integer) session.getAttribute("currentUserId");
		
		AdminUser au  =adminUserDAO.findById(current_user_id);
		Hashtable<Integer, String> typelist=null;
		if(au.getLevel()==StringUtil.ADVERONE){  
			 typelist=StringUtil.getTypes();
		}else if(au.getLevel()>=95){
			 typelist = StringUtil.getType();
		}
		request.setAttribute("typelist", typelist);
		request.setAttribute("au", au);
		return "/cashShop/slide/add";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request, Integer id, String imgurls, String linkurl, int type) {

		HttpSession session = request.getSession();
		int current_user_id = (Integer) session.getAttribute("currentUserId");

		QueryModel queryModel = new QueryModel();
		Slides sc = new Slides();
		if (id != null) {
			queryModel.clearQuery();
			queryModel.combPreEquals("id", id);
			sc = (Slides) dateBaseDAO.findOne(Slides.class, queryModel);
		}
		sc.setImgurls(imgurls);
		sc.setIsvalid(true);
		sc.setLinkurl(linkurl);
		sc.setType(type);
		queryModel.clearQuery();
		queryModel.combPreEquals("id", current_user_id);
		AdminUser adminUser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);
		sc.setAdminUser(adminUser);
		sc.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		slideService.saveSlides(sc);
		return "redirect:list";
	}

	@RequestMapping("del")
	public String del(Integer id) {
		if (id != null) {
			slideService.updateSlidesIsValidByID(id);

		}

		return "redirect:list";
	}
	
	@RequestMapping("dels")
	public String dels(String ids) {
		if (ids != null&& StringUtils.isNotBlank(ids)){
			String[] idStr = ids.split(",");
			for(String id : idStr){
				slideService.updateSlidesIsValidByID(Integer.parseInt(id));
			}
		}

		return "redirect:list";
	}
}
