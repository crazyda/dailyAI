package com.axp.service.taoke.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.PartnerInformDao;
import com.axp.model.PartnerInform;
import com.axp.model.TkldPid;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.PartnerInformService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("partnerInformService")
public class PartnerInformServiceImpl extends BaseServiceImpl
 implements
PartnerInformService	 {

	@Autowired
	private PartnerInformDao partnerInformDao;
	
	@Autowired
	private DateBaseDAO dateBaseDao;
	
	@Override
	public List<PartnerInform> findAll(HttpServletRequest request) {
		
		String pagestr=request.getParameter("page");
		StringBuffer param = new StringBuffer();
		QueryModel queryModel=new QueryModel();
		queryModel.combPreEquals("isValid", true);
		PageInfo pageInfo=new PageInfo();
		queryModel.setOrder("id desc");
		Integer count= dateBaseDao.findCount(PartnerInform.class, queryModel);
		Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
		
	    int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
	     int end = pageInfo.getPageSize();
	     pageInfo.setParam(param + "&page=");
	     
	     List<PartnerInform> list = dateBaseDao.findPageList(PartnerInform.class, queryModel, start,end );
		 request.setAttribute("count", count);
	     request.setAttribute("list", list);
	     request.setAttribute("page", pagestr);
	     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	     
		return list;
	}

	@Override
	public boolean addPartner(Integer id) {
		
			try {
			PartnerInform partnerInform = partnerInformDao.findById(id);
			partnerInform.setIsValid(false);
			partnerInform.setCreatetime(new Timestamp(System.currentTimeMillis()));

			//List<TkldPid> tkldPidList=tkldPidDao.findByPropertyWithValid("users.id", partnerInform.getUsers().getId());
			QueryModel queryModel=new QueryModel();
			if(partnerInform.getMode()==0 || partnerInform.getMode() == null){//旧版本
				List<TkldPid> tkldPidList=tkldPidDao.findByPropertyWithValid("users.id", partnerInform.getCauseUsers().getId());
				
				if(tkldPidList.size()>0){
					queryModel.combEquals("level", 3);
					queryModel.combEquals("tkldPid.id", tkldPidList.get(0).getId());
					queryModel.combCondition("users.id is null");
					List<TkldPid> list = dateBaseDao.findLists(TkldPid.class, queryModel);
					if(list.size()>0){
						TkldPid tkldPid2 = list.get(0);
						tkldPid2.setUsers(partnerInform.getUsers());
						tkldPid2.setUsersRemark("补充合伙人");
						tkldPid2.setTkldPid(tkldPidList.get(0));
						tkldPid2.setCreateTime(new Timestamp(System.currentTimeMillis()));
						partnerInformDao.update(partnerInform);
						tkldPidDao.update(tkldPid2);
					}
				}else{
					return false;
				}
			}else if(partnerInform.getMode()==1){
				queryModel.clearQuery();
				queryModel.combEquals("level", 3);
				queryModel.combEquals("tkldPid.id", 0);
				queryModel.combCondition("users.id is null");
				List<TkldPid> list = dateBaseDao.findLists(TkldPid.class, queryModel);
				if(list.size()>0){
					TkldPid tkldPid2 = list.get(0);
					tkldPid2.setUsers(partnerInform.getUsers());
					tkldPid2.setLevel(partnerInform.getLevel());
					tkldPid2.setUsersRemark("补充合伙人");
					if(partnerInform.getCauseUsers()==null){
						tkldPid2.setTkldPid(null);
					}else{
						List<TkldPid> tkldPidList=tkldPidDao.findByPropertyWithValid("users.id", partnerInform.getCauseUsers().getId());
						if(tkldPidList!=null && tkldPidList.size()>0){
							tkldPid2.setTkldPid(tkldPidList.get(0));
						}else{
							tkldPid2.setTkldPid(null);
						}
						
						
					}
					
					
					tkldPid2.setCreateTime(new Timestamp(System.currentTimeMillis()));
					partnerInformDao.update(partnerInform);
					tkldPidDao.update(tkldPid2);
				}
			}
			
			
			
			} catch (Exception e) {
				e.printStackTrace();
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
		
		return true;
	}

}