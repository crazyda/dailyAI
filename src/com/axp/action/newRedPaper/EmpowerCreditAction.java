package com.axp.action.newRedPaper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperAssetlog;
import com.axp.model.RePermissionRole;
import com.axp.model.Seller;
import com.axp.service.newRedPaper.EmpowerCreditService;
import com.axp.service.newRedPaper.impl.EmpowerCreditServiceImpl;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Controller
@RequestMapping("/newRedPaper/empowerCredit")
public class EmpowerCreditAction extends BaseAction{

	@Resource
	private EmpowerCreditService empowerCreditService;

	//额度列表
	@IsItem(firstItemName = "红包管理", secondItemName = "授权红包额度")
	@NeedPermission(permissionName = "授权红包额度列表", classifyName = "红包管理")
	@SuppressWarnings("static-access")
	@RequestMapping("/assetList")
	public String assetList(HttpServletRequest request) {

		String pagestr = request.getParameter("page");//页码
		StringBuffer paramString = new StringBuffer();//页码条件
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		int level = (Integer) request.getSession().getAttribute("userLevel");//用户等级

		QueryModel model = new QueryModel();
		if (level < StringUtil.ADMIN) {//如果是总部以下的用户，找出他所有下级用户与商家
			String childIds = empowerCreditService.getChild(current_user_id, level);
//			String sellerIds = empowerCreditService.getSeller(current_user_id + "");
//			model.combCondition("(" + model.subVals("adminUser.id", new StringBuffer(childIds)) + " or "
//					+ model.subVals("seller.id", new StringBuffer(sellerIds)) + ")");
			model.combCondition(model.subVals("adminUser.id", new StringBuffer(childIds)));
		}
		model.combPreEquals("isValid", true);

		PageInfo pageInfo = new PageInfo();
		Integer count = 0;
		count = dateBaseDao.findCount(NewRedPaperAsset.class, model);

		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		List<NewRedPaperAsset> assetList = dateBaseDao.findPageList(NewRedPaperAsset.class, model, start, end);
		
		for(NewRedPaperAsset asset : assetList){
			AdminUser user = asset.getAdminUser();
			if(user==null){
				continue;
			}
			RePermissionRole role = null;
			if(user.getRePermissionRoleId()!=null){
				role = rePermissionRoleService.get(user.getRePermissionRoleId());
			}else{
				role = new RePermissionRole();
				role.setName(StringUtil.getLevelsHash().get(user.getLevel()+""));
			}
			user.setRole(role);
		}

		pageInfo.setParam(paramString.toString() + "&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("assetList", assetList);
		request.setAttribute("count", count);
		return "newRedPaper/empowerCredit/list";
	}

	//额度编辑
	@RequestMapping("/detailUI")
	public String detailUI(HttpServletRequest request) {
		Integer id = request.getParameter("id") == null ? null : Integer.parseInt(request.getParameter("id"));
		Integer adminUserId = request.getParameter("adminUserId") == null ? null : Integer.parseInt(request.getParameter("adminUserId"));
		Integer sellerId = request.getParameter("sellerId") == null ? null : Integer.parseInt(request.getParameter("sellerId"));
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		

		NewRedPaperAsset parentAsset = empowerCreditService.findValidAssetByAdminUserId(current_user_id);
		NewRedPaperAsset asset = empowerCreditService.getAssetDetail(request, id, adminUserId, sellerId);

		request.setAttribute("parentAsset", parentAsset);
		request.setAttribute("asset", asset);

		return "newRedPaper/empowerCredit/detail";
	}

	//添加额度
	@RequestMapping("/saveDetail")
	public String saveAssetDetail(HttpServletRequest request, String errorstr, Double positionTotal, String endTime, String remark,
			Integer id, Integer adminUserId, Integer sellerId) {
		
		NewRedPaperAsset asset = new NewRedPaperAsset();
		asset.setPositionTotal(positionTotal);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date end = null;
		try {
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asset.setEndTime(new Timestamp(end.getTime()));
		asset.setRemark(remark);
		if (id != null) {
			asset.setId(id);
		}
		QueryModel model = new QueryModel();
		if (sellerId != null) {
			model.clearQuery();
			model.combPreEquals("id", sellerId);
			List<Seller> sellerList = dateBaseDao.findLists(Seller.class, model);
			asset.setSeller(sellerList.size() > 0 ? sellerList.get(0) : null);
		} else {
			asset.setSeller(new Seller());
		}
		if (adminUserId != null) {
			model.clearQuery();
			model.combPreEquals("id", adminUserId);
			List<AdminUser> adminUsers = dateBaseDao.findLists(AdminUser.class, model);
			asset.setAdminUser(adminUsers.size() > 0 ? adminUsers.get(0) : null);
		} else {
			asset.setAdminUser(new AdminUser());
		}

		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		String error = empowerCreditService.saveAssetDetail(current_user_id, asset);
		if (StringUtils.isNotBlank(error)) {
			errorstr = error;
			return "error/error";
		}
		return "redirect:assetList";
	}

	//额度使用历史记录
	@SuppressWarnings("static-access")
	@RequestMapping("/getAssetRecord")
	public String getAssetRecord(HttpServletRequest request, Integer id) {
		if(id==null){
			request.setAttribute("count", 0);
			return "newRedPaper/empowerCredit/record";
		}
		String pagestr = request.getParameter("page");//页码
		StringBuffer paramString = new StringBuffer();//页码条件
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("id", id);
		NewRedPaperAsset asset = (NewRedPaperAsset) dateBaseDao.findOne(NewRedPaperAsset.class, queryModel);
		QueryModel model = new QueryModel();
		model.combPreEquals("asset.id", id, "assetId");
		PageInfo pageInfo = new PageInfo();
		int count = 0;
		count = dateBaseDao.findCount(NewRedPaperAssetlog.class, model);
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		List<NewRedPaperAssetlog> logList = dateBaseDao.findPageList(NewRedPaperAssetlog.class, model, start, end);

		pageInfo.setParam(paramString.toString() + "&page=");
		request.setAttribute("count", count);
		request.setAttribute("asset", asset);
		request.setAttribute("logList", logList);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());

		return "newRedPaper/empowerCredit/record";
	}

	@RequestMapping("/invalidAsset")
	@ResponseBody
	public void invalidAsset(Integer id) {
		if (id != null) {
			empowerCreditService.updatePropertyByID("status", EmpowerCreditServiceImpl.INVALIDSTATUS, id, NewRedPaperAsset.class);
		}

	}

	@RequestMapping("/getAssetPosition")
	@ResponseBody
	public Map<String, Object> getAssetPosition(HttpServletRequest request, HttpServletResponse response) {

		String adminUserId = request.getParameter("adminId");
		String sellerId = request.getParameter("sellerId");
		//response.setContentType("text/html; charset=UTF-8");
		//response.setCharacterEncoding("UTF-8");
		NewRedPaperAsset asset = empowerCreditService.getAsset(sellerId, adminUserId);
		double surplus = 0.00;
		String endTime = "";
		Integer id = null;
		if (asset != null) {
			surplus = asset.getPositionSurplus();
			endTime = DateUtil.formatDate("yyyy年MM月dd日", asset.getEndTime());
			id = asset.getId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("surplus", surplus);
		map.put("endTime", endTime);
		map.put("id", id);

		return map;
	}

}
