package com.axp.service.newRedPaper.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.NewRedPaperAssetDAO;
import com.axp.dao.NewRedPaperAssetapplyDao;
import com.axp.dao.NewRedPaperAssetlogDAO;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperAssetapply;
import com.axp.model.NewRedPaperAssetlog;
import com.axp.service.newRedPaper.CheckApplyService;
import com.axp.service.newRedPaper.EmpowerCreditService;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service("/checkApplyService")
public class CheckApplyServiceImpl extends BaseNewRedPaperServiceImpl implements CheckApplyService{

	@Autowired
	private NewRedPaperAssetlogDAO assetlogDAO;
	@Autowired
	private NewRedPaperAssetDAO assetDAO;
	@Resource
	private EmpowerCreditService empowerCreditService;
	@Autowired
	private NewRedPaperAssetapplyDao assetapplyDao;
	
	@Override
	public void list(HttpServletRequest request) {
		String pagestr = request.getParameter("page");//页码
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		AdminUser adminUser = adminUserDAO.findById(current_user_id);
		
		
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("remitter.id", adminUser.getId(),"parentId");
		queryModel.setOrder("id desc");
		int count = dateBaseDao.findCount(NewRedPaperAssetapply.class, queryModel);
		PageInfo pageInfo = new PageInfo();
		
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "20", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		List<NewRedPaperAssetlog> redpaperList = dateBaseDao.findPageList(NewRedPaperAssetapply.class, queryModel, start, end);
		request.setAttribute("count", count);
		request.setAttribute("redpaperList", redpaperList);
	}

	@Override
	public void edit(HttpServletRequest request) {
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		AdminUser adminUser = adminUserDAO.findById(current_user_id);
		String id = request.getParameter("id");
		NewRedPaperAssetapply assetlog = assetapplyDao.findById(Integer.parseInt(id));
		
		
		NewRedPaperAsset parentAsset = empowerCreditService.findValidAssetByAdminUserId(current_user_id);
		
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("adminUser.id", assetlog.getAdminUser().getId(),"adminUserId");
		NewRedPaperAsset asset = (NewRedPaperAsset) dateBaseDao.findOne(NewRedPaperAsset.class, queryModel);
		if(asset==null){
			asset = new NewRedPaperAsset();
			asset.setAdminUser(assetlog.getAdminUser());
			asset.setIsValid(true);
			asset.setPositionSurplus(0.0);
			asset.setPositionTotal(0.0);
			asset.setPositionUsed(0.0);
			asset.setStatus(0);
			Timestamp nowTime = new Timestamp(System.currentTimeMillis());
			asset.setBeginTime(nowTime);
				
		}
		
		
		
		asset.setEndTime(new Timestamp(DateUtil.addMonth2Date(3, new Date()).getTime()));
		assetDAO.saveOrUpdate(asset);
		
		request.setAttribute("assetlog", assetlog);
		request.setAttribute("parentAsset", parentAsset);
		request.setAttribute("asset", asset);
		
		
	}

	@Override
	public void save(HttpServletRequest request) {
		String id = request.getParameter("assetlogId");
		String assetId = request.getParameter("asset");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		
		NewRedPaperAssetapply assetlog = assetapplyDao.findById(Integer.parseInt(id));
		assetlog.setStatus(Integer.parseInt(status));
		assetapplyDao.update(assetlog);
		//审核通过
		if(status.equals("1")){
		NewRedPaperAsset asset = new NewRedPaperAsset();
		asset.setPositionTotal(assetlog.getPositons());
		asset.setId(Integer.parseInt(assetId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date end = null;
		try {
			end = sdf.parse(endTime);
		} catch (ParseException e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		asset.setEndTime(new Timestamp(end.getTime()));
		asset.setAdminUser(assetlog.getAdminUser());
		
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		 saveAssetDetail(current_user_id, asset);
		}
	}
	
	public String saveAssetDetail(Integer current_user_id, NewRedPaperAsset asset) {
		String error = "";
		try {
			AdminUser admin = adminUserDAO.findById(current_user_id);

			NewRedPaperAsset parentAsset = newRedPaperAssetDAO.findValidAssetByAdminUserId(current_user_id);
			if (admin.getLevel() < StringUtil.ADMIN && (parentAsset == null || parentAsset.getPositionSurplus() < asset.getPositionTotal())) {
				error = "当前额度不足，请先向上级申请额度发放！";
				return error;
			}
			NewRedPaperAsset newAsset = newRedPaperAssetDAO.findById(asset.getId());
			double positionTotal = newAsset.getPositionTotal();
			double positionSurplus = newAsset.getPositionSurplus();
			positionTotal = CalcUtil.add(asset.getPositionTotal(), positionTotal, 2);//增加总额度
			positionSurplus = CalcUtil.add(asset.getPositionTotal(), positionSurplus, 2);//增加总额度
			newAsset.setPositionSurplus(positionSurplus);
			newAsset.setPositionTotal(positionTotal);
			newAsset.setEndTime(asset.getEndTime());
			newAsset.setRemark(asset.getRemark());
			newAsset.setStatus(VALIDSTATUS);
			newRedPaperAssetDAO.merge(newAsset);
			
			if (admin.getLevel() < StringUtil.ADMIN) {
				parentAsset.setPositionSurplus(parentAsset.getPositionSurplus() - asset.getPositionTotal());
				newRedPaperAssetDAO.update(parentAsset);
				saveLog(parentAsset, admin, asset.getPositionTotal() * -1, "授权额度扣除余额");
			}
			saveLog(newAsset, admin, asset.getPositionTotal(), "获得上级的额度发放(" + asset.getPositionTotal() + ")");

		} catch (Exception e) {
			e.printStackTrace();
			error = "系统出错！";
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return error;
	}


}
