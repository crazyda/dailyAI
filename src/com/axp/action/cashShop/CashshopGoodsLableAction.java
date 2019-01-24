package com.axp.action.cashShop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ICashshopGoodsLableDao;
import com.axp.model.CashshopGoodsLable;
import com.axp.service.cashShop.CashshopGoodsLableService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;


@RequestMapping("/cashShop/goodsLable")
@Controller
public class CashshopGoodsLableAction {
	@Autowired
	private ICashshopGoodsLableDao cashshopGoodsLableDao;
	@Autowired
	private DateBaseDAO dateBaseDAO;
	@Autowired
	private CashshopGoodsLableService cashshopGoodsLableService;

	@NeedPermission(permissionName = "商品标签列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "商品标签管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		StringBuffer con = new StringBuffer("isValid = true");
		String search_name = request.getParameter("search_name")==null?"":request.getParameter("search_name");
		if(StringUtils.isNotBlank(search_name)){
			con.append(" and name like '%"+search_name+"%'");
		}
		int count = cashshopGoodsLableDao.getAllCountAndCon(CashshopGoodsLable.class, con.toString());

		// ---------分页----------
		String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		Utility.setPageInfomation(pageInfo, pagestr, "10", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		pageInfo.setParam("&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		// ---------分页----------
		List<CashshopGoodsLable> list = cashshopGoodsLableDao.findByPageOrderAndCon(CashshopGoodsLable.class, con.toString(), "id", "desc", start, end);
		request.setAttribute("list", list);
		return "cashShop/goodsLable/lableList";
	}	
	@RequestMapping("/addLable")
	public String addLable(HttpServletRequest request, Integer lableId) {

		if (lableId != null) {
			CashshopGoodsLable lable = (CashshopGoodsLable) cashshopGoodsLableDao.findByCon(CashshopGoodsLable.class,
					" isValid = true and id = " + lableId).get(0);
			request.setAttribute("lable", lable);
		}
		List<CashshopGoodsLable> lableList = cashshopGoodsLableDao.findByCon(CashshopGoodsLable.class, "isValid = true and cashshopGoodsLable is null");
		request.setAttribute("lableList", lableList);
		return "cashShop/goodsLable/addLable";
	}
	@RequestMapping("/lablesave")
	public String lablesave(HttpServletRequest request, Integer lableId) {
		CashshopGoodsLable cashshopGoodsLable = null;
		String lableParentId = request.getParameter("lableParentId");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if (lableId != 0) {
			cashshopGoodsLable = (CashshopGoodsLable) cashshopGoodsLableDao.findByCon(CashshopGoodsLable.class, " isValid = true and id = " + lableId).get(0);

		} else {
			cashshopGoodsLable = new CashshopGoodsLable();
			cashshopGoodsLable.setIsValid(true);
		}
		if (lableParentId != null && lableParentId.length() > 0) {
			CashshopGoodsLable cashshopGoodsLable2 = (CashshopGoodsLable) cashshopGoodsLableDao.findByCon(CashshopGoodsLable.class,
					" isValid = true and id = " + Integer.parseInt(lableParentId)).get(0);
			cashshopGoodsLable.setCashshopGoodsLable(cashshopGoodsLable2);
		} else {
			cashshopGoodsLable.setCashshopGoodsLable(null);
		}
		cashshopGoodsLable.setName(name);	
		cashshopGoodsLableService.saveCashshopGoodsLable(cashshopGoodsLable);

		return "redirect:list";
	}
	@RequestMapping("/labledel")
	public String labledel(Integer lableId){	
		if(lableId !=null){
			cashshopGoodsLableService.delCashshopGoodsLable(lableId);
		}		
		return "redirect:list";
	}
	@RequestMapping("/checkNameByAjax")
	@ResponseBody
	public List<String> checkNameByAjax(HttpServletRequest request) {
	
		String name = request.getParameter("name");
		String lableId = request.getParameter("lableId");
		String str = "true";
		QueryModel qm = new QueryModel();

		if(lableId.equals("0")){
			qm.clearQuery();
			qm.combPreEquals("name", name);
			qm.combPreEquals("isValid",true);
			List<CashshopGoodsLable> cglLists = dateBaseDAO.findLists(CashshopGoodsLable.class, qm);
			if(cglLists!=null && cglLists.size()>0){
				str = "false";
			}
		}else{
			StringBuffer sb = new StringBuffer("select id from cashshop_goods_lable where name = ? and id != ? and isValid = ?");
			List<Object> values = new ArrayList<Object>();
			values.add(name);
			values.add(lableId);
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
}
