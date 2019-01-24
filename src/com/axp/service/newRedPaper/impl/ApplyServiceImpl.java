package com.axp.service.newRedPaper.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.NewRedPaperAssetDAO;
import com.axp.dao.NewRedPaperAssetapplyDao;
import com.axp.dao.NewRedPaperAssetlogDAO;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperAssetapply;
import com.axp.model.NewRedPaperAssetlog;
import com.axp.service.newRedPaper.ApplyService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("applyService")
public class ApplyServiceImpl extends BaseNewRedPaperServiceImpl implements ApplyService{

	@Autowired
	private NewRedPaperAssetlogDAO assetlogDAO;
	
	@Autowired  
	private NewRedPaperAssetDAO assetDAO;
	
	@Autowired
	private NewRedPaperAssetapplyDao assetapplyDao;
	
	@Override
	public void saveApplyLog(HttpServletRequest request) {
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		String positions = request.getParameter("positions");
		String remark = request.getParameter("remark");
		
		NewRedPaperAssetapply assetlog = new NewRedPaperAssetapply();
		assetlog.setIsValid(true);
		
		AdminUser adminUser = adminUserDAO.findById(current_user_id);
		assetlog.setAdminUser(adminUser);
		
		if(adminUser.getParentId()!=null){
			assetlog.setRemitter(adminUser.getParentAdminUser());
		}
		
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
		queryModel.combPreEquals("isValid", true);
		NewRedPaperAsset asset = (NewRedPaperAsset) dateBaseDao.findOne(NewRedPaperAsset.class, queryModel);
		if(asset!=null){
			assetlog.setAsser(asset);
		}
		
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		assetlog.setCreateTime(nowTime);
		
		assetlog.setPositons(Double.valueOf(positions));
		assetlog.setRemark(remark);
		assetlog.setStatus(NewRedPaperAssetlog.STATUS_weishenhe);
		
		assetapplyDao.save(assetlog);
	}

	@Override
	public void list(HttpServletRequest request) {
		
		String pagestr = request.getParameter("page");//页码
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");//用户id
		AdminUser adminUser = adminUserDAO.findById(current_user_id);
		
		
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
		queryModel.setOrder("id desc");
		int count = dateBaseDao.findCount(NewRedPaperAssetapply.class, queryModel);
		PageInfo pageInfo = new PageInfo();
		
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		List<NewRedPaperAssetapply> redpaperList = dateBaseDao.findPageList(NewRedPaperAssetapply.class, queryModel, start, end);
		request.setAttribute("count", count);
		request.setAttribute("redpaperList", redpaperList);
	}

}
