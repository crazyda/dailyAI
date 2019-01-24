package com.axp.action.cashShop;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.ICommodityTypeDao;
import com.axp.model.CommodityType;
import com.axp.service.cashShop.GoodsTypeService;
import com.axp.util.PageInfo;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

/**
 * 商品分类管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cashShop/goodsType")
public class GoodsTypeAction extends BaseAction {
	@Resource
	private ICommodityTypeDao commodityTypeDAO;
	@Resource
	private GoodsTypeService goodsTypeService;

	@NeedPermission(permissionName = "商品分类管理", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "商品分类管理")
	@RequestMapping("/typelist")
	public String typelist(HttpServletRequest request, String search_name,String modelId) {

		//Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		// 查询符合条件的总记录数
		StringBuffer param = new StringBuffer();//页码条件
		String con = "isValid = true";
		
		if (search_name != null && search_name.length() > 0) {
			/*if("GET".equalsIgnoreCase(request.getMethod())){
				try {
					search_name = new String(search_name.getBytes("iso8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}*/
			con = con + " and name like '%" + search_name + "%'";
			param.append("&search_name="+search_name);
			request.setAttribute("search_name", search_name);
		}
		if(StringUtils.isNoneBlank(modelId)){
			//con += " and modelId = "+modelId;
			
			if("2".equals(modelId)){
				con += " and modelId = 1 and isLocal=1";
			}else{
				con += " and modelId = 1";
			}
			param.append("&modelId="+modelId);
			request.setAttribute("curruModelId", modelId);
		}

		int count = commodityTypeDAO.getAllCountAndCon(CommodityType.class, con);

		// ---------分页----------
		String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		Utility.setPageInfomation(pageInfo, pagestr, "10", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		pageInfo.setParam(param+"&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		// ---------分页----------	
		List<CommodityType> typeList = commodityTypeDAO.findByPageOrderAndCon(CommodityType.class, con, "id", "desc", start, end);
		request.setAttribute("typeList", typeList);
		//所属模块
		Hashtable<Integer,String> module = StringUtil.getModule();
		request.setAttribute("page", pagestr);
		request.setAttribute("count", count);
		request.setAttribute("modules", module);
		return "cashShop/goodsType/typelist";
	}

	@RequestMapping("/typeadd")
	public String typeadd(HttpServletRequest request, Integer typeId) {
		Integer modelId = null;
		if (typeId != null) {
			CommodityType commodity_type = (CommodityType) commodityTypeDAO.findByCon(CommodityType.class,
					" isValid = true and id = " + typeId).get(0);
			modelId = commodity_type.getModelId();
			request.setAttribute("commodity_type", commodity_type);
		}
		List<CommodityType> typeList = commodityTypeDAO.findByCon(CommodityType.class, "isValid = true and commodityType is null and modelId ="+modelId);
		//所有模块
		//Hashtable<Integer,String> module = StringUtil.getModule();
		//request.setAttribute("modules",module);
		request.setAttribute("typeList", typeList);
		return "cashShop/goodsType/typeadd";
	}
	
	@RequestMapping("/getTypeByModelId")
	@ResponseBody
	public List<CommodityType> getTypeByModelId(HttpServletRequest request, Integer modelId){
		return commodityTypeDAO.findByCon(CommodityType.class, "isValid = true and commodityType is null and modelId ="+modelId);
	}
	
	@RequestMapping("/getTypeByLevel")
	@ResponseBody
	public List<CommodityType> getTypeByLevel(HttpServletRequest request, Integer parentId,Integer modelId){
		StringBuffer sb=new StringBuffer();
		sb.append(" isValid = true and modelId = ").append(modelId).append(" and commodityType.id = ").append(parentId);
		List<CommodityType> list = commodityTypeDAO.findByCon(CommodityType.class, sb.toString());
		return list;
	}

	@RequestMapping("/typesave")
	public String typesave(HttpServletRequest request, Integer typeId) {
		CommodityType commodityType = null;
		String typeParentId = request.getParameter("typeParentId");
		String imgurls = request.getParameter("imgurls");
		String name = request.getParameter("name");
		String modelId = request.getParameter("modelId");
		Boolean local=Boolean.valueOf(request.getParameter("local")==null?false:true);
		if (typeId != null) {
			commodityType = (CommodityType) commodityTypeDAO.findByCon(CommodityType.class, " isValid = true and id = " + typeId).get(0);

		} else {
			commodityType = new CommodityType();
		}
		if (typeParentId != null && typeParentId.length() > 0) {
			CommodityType commodityType2 = (CommodityType) commodityTypeDAO.findByCon(CommodityType.class,
					" isValid = true and id = " + typeParentId).get(0);
			commodityType.setCommodityType(commodityType2);
			commodityType.setLevel(commodityType2.getLevel()+1);	
		} else {
			commodityType.setCommodityType(null);
			commodityType.setLevel(1);
		}
		commodityType.setIsLocal(local);
		//commodityType.setModelId(Integer.parseInt(modelId));
		commodityType.setModelId(1);
		commodityType.setImgUrl(imgurls);
		commodityType.setName(name);
		commodityType.setIsValid(true);
		commodityType.setCreateTime(new Timestamp(System.currentTimeMillis()));
		goodsTypeService.saveCommodityType(commodityType);

		return "redirect:typelist";
	}

	@RequestMapping("/typedel")
	public String typedel(Integer typeId) { // 删除
		if (typeId != null) {
			goodsTypeService.delCommodityType(typeId);

		}
		return "redirect:typelist";
	}
}
