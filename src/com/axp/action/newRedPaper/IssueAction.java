package com.axp.action.newRedPaper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperLog;
import com.axp.model.NewRedPaperSetting;
import com.axp.model.ProvinceEnum;
import com.axp.model.UploadFile;
import com.axp.service.newRedPaper.impl.IssueServiceImpl;
import com.axp.service.system.SelectTagService;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.UploadImg;
import com.axp.util.Utility;

@Controller
@RequestMapping("/newRedPaper/issue")
public class IssueAction extends BaseAction{

	@Autowired
	private SelectTagService selectTagService;
	
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;

	//红包设定列表
	@IsItem(firstItemName = "红包管理", secondItemName = "派发红包")
	@NeedPermission(permissionName = "派发红包列表列表", classifyName = "红包管理")
	@SuppressWarnings("static-access")
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		String pagestr = request.getParameter("page");//页码
		StringBuffer paramString = new StringBuffer();//页码条件
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		//int level = (Integer) request.getSession().getAttribute("userLevel");//用户等级

		QueryModel model = new QueryModel();
		model.combPreEquals("adminUser.id", current_user_id, "adminUserId");
		model.combPreEquals("isValid", true);

		PageInfo pageInfo = new PageInfo();

		int count = dateBaseDao.findCount(NewRedPaperSetting.class, model);
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		List<NewRedPaperSetting> redpaperList = dateBaseDao.findPageList(NewRedPaperSetting.class, model, start, end);
		for (NewRedPaperSetting s : redpaperList) {
			if (s.getBeginTime().before(DateUtil.getNow()) && s.getEndTime().after(DateUtil.getNow())) {
				s.setDateStatus(IssueServiceImpl.ISSUEING);
			} else if (s.getEndTime().before(DateUtil.getNow())) {
				s.setDateStatus(IssueServiceImpl.TIMEOUT);
			} else {
				s.setDateStatus(0);
			}
		}

		pageInfo.setParam(paramString.toString() + "&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("redpaperList", redpaperList);
		request.setAttribute("count", count);

		request.setAttribute("CHECKING", IssueServiceImpl.CHECKING);//未审核
		request.setAttribute("PASS", IssueServiceImpl.PASS);//审核通过
		request.setAttribute("FAIL", IssueServiceImpl.FAIL);//审核不通过
		request.setAttribute("ISSUEING", IssueServiceImpl.ISSUEING);//发放中
		request.setAttribute("TIMEOUT", IssueServiceImpl.TIMEOUT);//过期
		request.setAttribute("INVALID", IssueServiceImpl.INVALID);//失效
		request.setAttribute("NOWDATE", new Timestamp(System.currentTimeMillis()));//失效

		return "newRedPaper/issue/list";
	}

	//红包派发详情
	@SuppressWarnings("static-access")
	@RequestMapping("/newRedPaperLogList")
	public String newRedPaperLogList(HttpServletRequest request, String num, String id, String page) {
		int level = (Integer) request.getSession().getAttribute("userLevel");

		QueryModel qm = new QueryModel();

		//获取红包设定表信息
		qm.clearQuery();
		qm.combEquals("id", Integer.valueOf(id));
		NewRedPaperSetting nrps = dateBaseDao.findLists(NewRedPaperSetting.class, qm).get(0);
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" setting.id =  " + id);
		if ("1".equals(num) && level < StringUtil.ADMIN) {
			sb.append(" and (users.adminUser.id = ? or users.adminUser.parentId = ? or users.adminUser.parentAdminUser.parentId = ?)");
			valueList.add(Integer.parseInt(nrps.getAdminUser().getId().toString()));
			valueList.add(Integer.parseInt(nrps.getAdminUser().getId().toString()));
			valueList.add(Integer.parseInt(nrps.getAdminUser().getId().toString()));
		}

		Object[] values = new Object[valueList.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = valueList.get(i);
		}
		PageInfo pageInfo = new PageInfo();
		int count = 0;

		try {
			count = dateBaseDao.getCountsByCon(NewRedPaperLog.class, sb.toString(), values);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, page, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		pageInfo.setParam("&id=" + id + "&num=" + num + "&page=");
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		List<NewRedPaperLog> nrplList = dateBaseDao.getListByCon(NewRedPaperLog.class, sb.toString(), start, end, " order by id desc ",
				values);
		request.setAttribute("id", id);
		request.setAttribute("nrplList", nrplList);
		request.setAttribute("nrps", nrps);
		return "newRedPaper/issue/newRedPaperLogList";
	}

	//新增红包设定
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, Integer id, NewRedPaperSetting redPaperSetting, Integer hour, Integer minute,
			Integer second, Integer type) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		request.setAttribute("basePath", basePath);
		AdminUser adminUser = adminUserDao.findById((Integer) request.getSession().getAttribute("currentUserId"));
		
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("level",1);
		List<ProvinceEnum> provinceEnumList = dateBaseDao.findLists(ProvinceEnum.class, queryModel);
		request.setAttribute("provinceEnumList", provinceEnumList);
		try {
			AdminUser range = null;
			if (id != null) {
				redPaperSetting = (NewRedPaperSetting) issueService.findById(NewRedPaperSetting.class, id);
				range = (AdminUser) issueService.findById(AdminUser.class, redPaperSetting.getRangeid());
				request.setAttribute("range", range);
				
				queryModel.clearQuery();
				queryModel.combPreEquals("id", redPaperSetting.getZoneId());
				ProvinceEnum  provinceEnum = (ProvinceEnum) dateBaseDao.findOne(ProvinceEnum.class, queryModel);
				request.setAttribute("provinceEnum", provinceEnum);
				if (redPaperSetting.getCyc() != null) {
					double cycDouble = redPaperSetting.getCyc();
					Long cycLong = (long) CalcUtil.mul(cycDouble, 60 * 60 * 1000);

					hour = (int) (cycLong / (60 * 60 * 1000));
					minute = (int) (cycLong / (60 * 1000) - hour * 60);
					second = (int) (cycLong / 1000 - minute * 60 - hour * 60 * 60);

				}
			}
			//运营中心查询条件
			selectTagService.initSelectTag(request, adminUser, range);
			//获得发放人额度
			NewRedPaperAsset asset = issueService.getAsset(null, adminUser.getId() + "");
			if (asset != null) {
				request.setAttribute("positionSurplus", asset.getPositionSurplus());
			} else {
				request.setAttribute("positionSurplus", 0.00);
			}
			request.setAttribute("redPaperSetting", redPaperSetting);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type != null && type == 2) {
			return "newRedPaper/issue/details";
		} else {
			return "newRedPaper/issue/edit";
		}
	}

	//保存红包设定
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, String bTime, String cTime, Integer hour, Integer minute, Integer second, String msg,
			NewRedPaperSetting redPaperSetting) {
		bTime = bTime == null ? "" : bTime;
		cTime = cTime == null ? "" : cTime;
		String headImg = request.getParameter("imageurls1");
		//找到发放人
		if (StringUtils.isNotBlank(bTime)) {
			Date bDate = DateUtil.getDate(bTime, "yyyy-MM-dd");
			redPaperSetting.setBeginTime(new Timestamp(bDate.getTime()));
		}
		if (StringUtils.isNotBlank(cTime)) {
			Date cDate = DateUtil.getDate(cTime, "yyyy-MM-dd");
			redPaperSetting.setCreateTime(new Timestamp(cDate.getTime()));
		}
		if(headImg!=null&&StringUtils.isNotBlank(headImg)){
			redPaperSetting.setHeadimg(headImg);
		}
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = (AdminUser) issueService.findById(AdminUser.class, current_user_id);

		//派发范围
		String cityId = request.getParameter("cityId");
		
		
		//计算限制时间
		Integer hLongTime = hour * 1000 * 60 * 60;
		Integer mLongTime = minute * 1000 * 60;
		Integer sLongTime = second * 1000;
		Integer cycLonTime = hLongTime + mLongTime + sLongTime;
		Double cycDouble = CalcUtil.div((double) cycLonTime, 1000 * 60 * 60, 2);
		//获得发放人额度
		NewRedPaperAsset asset = issueService.getAsset(null, adminUser.getId() + "");
		Double cashPoints = 0.00;
		if (asset != null) {
			cashPoints = asset.getPositionSurplus();
		}
		//红包总金额超出用户限额
		if (cashPoints < redPaperSetting.getAllMoney() && adminUser.getLevel() < StringUtil.ADMIN) {
			msg = "红包总金额超出用户限额[" + cashPoints + "]，请重新确认！";
			return "editPage";
		}

		//保存默认图片url;
		//redPaperSetting.setHeadimg("images/loading2.png");

		//有效期
		Calendar cl = Calendar.getInstance();
		cl.setTime(redPaperSetting.getBeginTime());
		cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + 4);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		redPaperSetting.setEndTime(new Timestamp(cl.getTimeInMillis()));

		redPaperSetting.setZoneId(Integer.parseInt(cityId));
		redPaperSetting.setStatus(0);
		redPaperSetting.setAdminUser(adminUser);
		redPaperSetting.setCyc(cycDouble);
		Double balance = 0.0;
		if (redPaperSetting.getId() != null && redPaperSetting.getId() != 0) {
			NewRedPaperSetting oldPaper = (NewRedPaperSetting) issueService.findById(NewRedPaperSetting.class, redPaperSetting.getId());
			balance = redPaperSetting.getAllMoney() - oldPaper.getAllMoney();
			issueService.mergeSetting(redPaperSetting);
			;
			msg = "修改红包信息成功！";
		} else {
			redPaperSetting.setAdminUser(adminUser);
			redPaperSetting.setAllMoneyUsed(0.00);
			redPaperSetting.setAllNumColl(0);
			redPaperSetting.setAllNumUsed(0);
			redPaperSetting.setTodaySurplus(redPaperSetting.getMaxPutout());
			dateBaseDao.addData(redPaperSetting);
			balance = redPaperSetting.getAllMoney();
			msg = "派发红包成功！";
		}
		//保存图片路径
		String base64s = request.getParameter("imgBaseData");
		if (StringUtils.isNotBlank(base64s)) {
			String fileUrl = "new_red_paper_setting";
			UploadFile uploadFile = new UploadFile();
			uploadFile.setRelatedId(redPaperSetting.getId());
			uploadFile.setTableName("new_red_paper_setting");
			uploadFile.setRemark("商品详情图片");
			// 上传图片
			UploadImg uf = new UploadImg(310, 310);
			List<String> pathList = uf.uploadForPath(base64s, fileUrl, uploadFile, request);
			if (pathList.size() > 0) {
				redPaperSetting.setHeadimg(pathList.get(0));
				issueService.mergeSetting(redPaperSetting);
			}
		}
		//修改或派发红包后更新用户红包额度
		//总部发放红包无需额度
		if (adminUser.getLevel() < StringUtil.ADMIN && balance != 0) {
			balance = balance * -1;
			if (balance < 0) {
				issueService.addAssetSurplus(adminUser, balance, "新增红包扣除额度余额");
			} else {
				issueService.addAssetSurplus(adminUser, balance, "调整红包总额返还余额");
			}

		}
		return "redirect:list";
	}

	//冻结额度
	@RequestMapping("/invalid")
	public void invalid(Integer id) {
		NewRedPaperSetting setting = (NewRedPaperSetting) issueService.findById(NewRedPaperSetting.class, id);
		//修改红包状态--无效
		setting.setStatus(IssueServiceImpl.INVALID);
		issueService.mergeSetting(setting);
		//修改用户红包额度
		Double unsued = setting.getAllMoney() - setting.getAllMoneyUsed();
		issueService.addAssetSurplus(setting.getAdminUser(), unsued, "停用红包返还额度余额");
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

}
