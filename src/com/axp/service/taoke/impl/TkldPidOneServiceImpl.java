package com.axp.service.taoke.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.TkPartnerSettlementDao;
import com.axp.dao.TkThePartnerIndex1Dao;
import com.axp.dao.TkldPidDao;
import com.axp.dao.TkldPidOneDao;
import com.axp.model.AdminUser;
import com.axp.model.TkThePartnerIndex1;
import com.axp.model.TkldPidOne;
import com.axp.model.UserLoginRecord;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.TkldPidOneService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;
@Service
public class TkldPidOneServiceImpl  extends BaseServiceImpl implements TkldPidOneService{
	
	@Resource
	private TkldPidOneDao tkldPidDao;
	@Resource
	private TkPartnerSettlementDao tkPartnerSettlementDao;
	@Resource
	private TkThePartnerIndex1Dao tkThePartnerIndex1Dao;
	
	
	
	@Resource
	com.axp.dao.PartnerInformDao PartnerInformDao;
	
	@Override
	public boolean checkPid(String pid) {
		return tkldPidDao.checkPid(pid);
	}

	@Override
	public List<TkldPidOne> findParentTkldPid(Integer level) {
			//减一是找上级
		return  tkldPidDao.findParentTkldPid(level-1);
		
	}

	@Override
	public boolean saveTkldPid(HttpServletRequest request) {
		try {
			Integer level = Integer.parseInt( request.getParameter("level"));
			String pId = request.getParameter("pId");
			String ldLoginName = request.getParameter("ldLoginName");
			String ldLoginPwd = request.getParameter("ldLoginPwd");
			String ldLoginReamrk = request.getParameter("ldLoginReamrk");
			String remark = request.getParameter("remark");
			Integer parentPidId =request.getParameter("parentPidId")==""?2:Integer.parseInt( request.getParameter("parentPidId"));
			Integer id =request.getParameter("id")==""?null:Integer.parseInt( request.getParameter("id"));
			
			TkldPidOne tkldPid=new TkldPidOne();
			if(id==null){
			tkldPid.setLevel(level);
			tkldPid.setpId(pId);
			tkldPid.setLdLoginName(ldLoginName);
			tkldPid.setLdLoginPwd(ldLoginPwd);
			tkldPid.setLdLoginReamrk(ldLoginReamrk);
			tkldPid.setRemark(remark);
			tkldPid.setCreateTime(new Timestamp(new Date().getTime()));
			tkldPid.setTkldPid(tkldPid.getTkldPid()==null?null:tkldPid.getTkldPid());
			tkldPid.setIsValid(true);
			TkldPidOne parentTkldPid=new TkldPidOne();
			parentTkldPid.setId(parentPidId);
			tkldPid.setTkldPid(parentTkldPid);
			tkldPidDao.save(tkldPid);
			}
			else{
				TkldPidOne tkldModel = tkldPidDao.findById(id);
				tkldModel.setLevel(level);
				tkldModel.setpId(pId);
				tkldModel.setLdLoginName(ldLoginName);
				tkldModel.setLdLoginPwd(ldLoginPwd);
				tkldModel.setLdLoginReamrk(ldLoginReamrk);
				tkldModel.setRemark(remark);
				tkldModel.setIsValid(true);
				tkldModel.setCreateTime(new Timestamp(System.currentTimeMillis()));
				TkldPidOne parentTkldPid=new TkldPidOne();
				parentTkldPid.setId(parentPidId);
				tkldModel.setTkldPid(parentTkldPid);
				tkldPidDao.update(tkldModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
			
		return true;
		
	}
	
	
	@Override
	public void SearchPid(HttpServletRequest request) {
		Integer adminUserId =Integer.parseInt( request.getSession().getAttribute("currentUserId").toString());
		AdminUser adminUser = adminUserDAO.findById(adminUserId);
		Integer userPidLevel=null;
		if(adminUser.getLevel()>=95){
			userPidLevel=-1;
		}else{
			userPidLevel=1;
		}
		
		request.setAttribute("userPidLevel",userPidLevel);
	}
	
	

	@Override
	public void findTkldPidList(HttpServletRequest request) {
		
				List<TkldPidOne> list=new ArrayList<TkldPidOne>();
				String pagestr=request.getParameter("page");
				String level=request.getParameter("level");
				String search_name=request.getParameter("search_name");
				Integer userPidLevel=null;
				Integer adminUserId =Integer.parseInt( request.getSession().getAttribute("currentUserId").toString());
				AdminUser adminUser = adminUserDAO.findById(adminUserId);
				QueryModel queryModel=new QueryModel();
				
				boolean isValid=true;
				if(level==null){
						if(adminUser.getLevel()>=95){
							level="1";
						}else if(adminUser.getLevel()<95){
							level="2";
						}
						
				}
				StringBuffer param = new StringBuffer();
				queryModel.clearQuery();
				queryModel.combPreEquals("isValid", true);
				queryModel.combEquals("level",level); 
				if(adminUser.getLevel()<95){
					if(level.equals("2")){ //查出所有该运营商下的事业
							  queryModel.combPreEquals("tkldPid.adminUser.id", adminUser.getId(),"Aid");
					}else if(level.equals("3")){//查出所有该运营商下的合伙人
						queryModel.combPreEquals("tkldPid.tkldPid.adminUser.id", adminUser.getId(),"Aid");
					}
					
				}
				
				
				if(search_name!=null){
					queryModel.combLike("remark", search_name, QueryModel.MATCH_ALL);
					param.append("&search_name="+search_name);
				}
				
				if(level!=null){
					param.append("&level="+level);
				}
				param.append("&level="+level);
				PageInfo pageInfo=new PageInfo();
				queryModel.setOrder("id desc");
				Integer count=0;
				if(isValid){
					 count= dateBaseDao.findCount(TkldPidOne.class, queryModel);
				}
		     Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
		     
		     int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		     int end = pageInfo.getPageSize();
		     pageInfo.setParam(param + "&page=");
		     
		     if(isValid){
		    	 list = dateBaseDao.findPageList(TkldPidOne.class, queryModel, start,end );
		     }
			 request.setAttribute("adminUser", adminUser);
			 request.setAttribute("count", 0);
		     request.setAttribute("list", list);
		     request.setAttribute("page", pagestr);
		     request.setAttribute("level", level);
		     request.setAttribute("search_name", search_name);
		     request.setAttribute("userPidLevel", userPidLevel);
		     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}												   
	
	
	public TkldPidOne getTkldPidById(Integer id){
		QueryModel queryModel=new QueryModel();
		queryModel.combPreEquals("isValid", true);
		TkldPidOne tkldPid = this.tkldPidDao.findById(id);
		return tkldPid;
	}

	@Override
	public List<AdminUser> findAdminUserByParentId(Integer parentId) {
			QueryModel queryModel=new QueryModel();
			queryModel.combPreEquals("isValid", true);
			if(parentId==null){
				//运营商
				queryModel.combEquals("level",StringUtil.ADVERCENTER);
			}
			else{
				queryModel.combEquals("parentId", parentId);
			}
				List<AdminUser> list = (List<AdminUser>) dateBaseDao.findList(AdminUser.class, queryModel);
		return list;
	}

	@Override
	public boolean updateDistributePid(Integer userId, Integer daiLiPid,
			String desc) {
		try {
			
			boolean flag=true;
			//根据当前userId找到AdminUserId 是否存在
   			List<TkldPidOne> list = tkldPidDao.findByCon(TkldPidOne.class, "adminUser.id="+userId+" and isValid=1");
   			if(list.size()>0){ //重复 
   					if(list.get(0).getAdminUser().getId()!=userId){
   				 		flag=false;
   				 	}else{
   				 		flag=true;
   				 	}
   			}
   			TkldPidOne tkldPid = tkldPidDao.findById(daiLiPid);
			if(!flag){
				return false;
			}
			AdminUser adminUser= adminUserDAO.findById(userId);
			
			tkldPid.setAdminUser(adminUser);
			tkldPid.setAdminUserReamrk(desc);
			tkldPid.setBindingTime(new Timestamp(System.currentTimeMillis()));
			tkldPidDao.update(tkldPid);
		} catch (Exception e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean checkPidByAdminUserId(Integer id) {
			List<TkldPidOne> list = tkldPidDao.findByCon(TkldPidOne.class, "adminUser.id="+id+" and isValid=1");
		return list.size()>0?true:false;
		
		
		
	}

	@Override
	public void findAdminUserSelect(Integer id,HttpServletRequest request) {
		
		TkldPidOne tkldPid=tkldPidDao.findById(id);
		AdminUser adminUser = adminUserDAO.findById(tkldPid.getAdminUser().getId()); //当前用户
			//所有运营商
			List<AdminUser> list = findAdminUserByParentId(null);
			request.setAttribute("list", list);
			
			List<AdminUser> adveroneList =new ArrayList<AdminUser>();
			//代理   如果当前用户是代理 那么就查出当前用户运营商下的所有代理
			if(adminUser.getLevel()==StringUtil.ADVERONE){
				adveroneList= findAdminUserByParentId(adminUser.getParentId());
				request.setAttribute("adveroneList", adveroneList);
			}
			
			
			//如果当前用户是商圈级别
			//那么就找出所有当前用户代理商的所有商圈
			else if(adminUser.getLevel()==StringUtil.ADVERTWO){
				//所有商圈
				List<AdminUser> advertwoList = findAdminUserByParentId(adminUser.getParentId());
				//代理商
				AdminUser adverone=adminUserDAO.findById(adminUser.getParentId());
				//代理商List
				adveroneList = findAdminUserByParentId(adverone.getParentId());
				//商圈集合
				request.setAttribute("advertwoList", advertwoList);
				
				//商圈id
				request.setAttribute("advertwoId", adminUser.getId());
				//代理商id
				request.setAttribute("adveroneId", adverone.getId());
				
			}
			
			//代理商
			AdminUser adverone=adminUserDAO.findById(adminUser.getParentId());
			//代理商对象
			request.setAttribute("adverone", adverone);
			//代理集合
			request.setAttribute("adveroneList", adveroneList);
			
			request.setAttribute("tkldPid",tkldPid);
			
	}

	@Override
	public void editAddPid(Integer id, HttpServletRequest request) {
		TkldPidOne tkldPid= tkldPidDao.findById(id);
			if(tkldPid.getLevel()!=1){
				List<TkldPidOne> list=tkldPidDao.findParentTkldPid(tkldPid.getLevel()-1);
				request.setAttribute("list", list);
			}
			request.setAttribute("parent",tkldPid.getTkldPid());
			request.setAttribute("tkldPid", tkldPid);
	}

	@Override
	public boolean cancelDistributePid(Integer id, HttpServletResponse response) {
			try {
				
				
				TkldPidOne tkldPid=tkldPidDao.findById(id);
				tkldPid.setCreateTime(new Timestamp(new Date().getTime()));
				
				TkThePartnerIndex1 partnerIndex1 = null;
				QueryModel model = new QueryModel();
				model.clearQuery();
				model.combPreEquals("isValid", true);
				model.combPreEquals("pid_id", id, "pidId");
				List<TkThePartnerIndex1> list2 = dateBaseDao.findLists(TkThePartnerIndex1.class, model);
				if(tkldPid.getLevel()==2||tkldPid.getLevel()==3){
					tkldPid.setUsers(null);
					tkldPid.setUsersRemark(null);
					tkldPid.setTkldPid(tkldPidDao.findById(tkldPid.getTkldPid().getId()));
					tkldPid.setProvinceEnum(null);
					tkldPid.setBindingTime(null);
					tkldPidDao.update(tkldPid);
					
					if (list2.size()>0) {
						partnerIndex1 = list2.get(0);
						partnerIndex1.setIsValid(false);
						tkThePartnerIndex1Dao.update(partnerIndex1);
					}
				}
				
				if(tkldPid.getLevel()==1){
					tkldPid.setAdminUser(null);
					tkldPid.setAdminUserReamrk(null);
					tkldPid.setBindingTime(null);
					tkldPidDao.update(tkldPid);
					
					if (list2.size()>0) {
						partnerIndex1 = list2.get(0);
						partnerIndex1.setIsValid(false);
						tkThePartnerIndex1Dao.update(partnerIndex1);
					}
				}
			} catch (Exception e) {
					e.printStackTrace();
		        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return false;
			}
			
		return true;
	}
	
	
	

	@Override
	public boolean delTkldPid(Integer id) {
			try {
				TkldPidOne tkldPid = tkldPidDao.findById(id);
				tkldPid.setIsValid(false);
				tkldPid.setCreateTime(new Timestamp(new Date().getTime()));
				tkldPidDao.update(tkldPid);
				List<TkThePartnerIndex1> tkThePartnerIndex1List = tkThePartnerIndex1Dao.findByPropertyWithValid("pidId", tkldPid.getId());
				if(tkThePartnerIndex1List.size()>0){
					tkThePartnerIndex1List.get(0).setIsValid(false);
					tkThePartnerIndex1Dao.update(tkThePartnerIndex1List.get(0));
				}
				else{
					new RuntimeException("tkPartnerSettlementList++++tkThePartnerIndex1List表数据异常,tkldPid:id"+tkldPid.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			return true;
	}

	@Override
	public Map<String, String> saveOrUpdatePartnerPid(Integer id, String userPhone,
			String usersRemark,Integer checkbox,Integer current_user_id ) {
			Users users=null;
			Map<String, String> map=new HashMap<String, String>();
			
			List<Users> userList =usersDAO.findByPropertyWithvalid("phone", userPhone);
			TkldPidOne tkldPid = tkldPidDao.findById(id);
			if(userList.size()<=0){
				map.put("flag", "手机号码不存在");
				return map;
			}
			else{
				users=userList.get(0);
					QueryModel queryModel = new QueryModel();
					queryModel.combPreEquals("isValid", true);
					queryModel.combPreEquals("users.id", users.getId(),"userId");
					int count = dateBaseDao.findCount(TkldPidOne.class, queryModel);
						if (count > 0) {
							map.put("flag", "该用户已经录入过了!");
							return map;
						}

			}
			AdminUser currentUser = adminUserDAO.findById(current_user_id);
			
			tkldPid.setCreateTime(new Timestamp(new Date().getTime()));
			tkldPid.setUsers(users);
			tkldPid.setUsersRemark(usersRemark);
			tkldPid.setUsers(users);
			tkldPid.setBindingTime(new Timestamp(System.currentTimeMillis())); //绑定时间 
			if(checkbox!=null&&checkbox==1){
				if(currentUser.getProvinceEnum()!=null){
					tkldPid.setProvinceEnum(currentUser.getProvinceEnum());
				}
			}
			tkldPidDao.update(tkldPid);
			map.put("flag", "1");
			
			return map;
	}

	@Override
	public Map<String, Integer> currentCityIsValid(HttpServletRequest request) {
		Integer currentUserId =(Integer) request.getSession().getAttribute("currentUserId");
		Map<String, Integer> map=new HashMap<String, Integer>();
		AdminUser currentUser = adminUserDAO.findById(currentUserId);
		QueryModel queryModel=new QueryModel();
		queryModel.combPreEquals("isValid", true);
		if(currentUser.getProvinceEnum()==null){
			 map.put("flag", 3);  //当前人物  地址为空
			 return map;
		}
		queryModel.combPreEquals("provinceEnum.id", currentUser.getProvinceEnum().getId(),"zoneId");
		Integer count= dateBaseDao.findCount(TkldPidOne.class, queryModel);
			map.put("flag", count>0?1:2);
			return map;
	}

	@Override
	public void findPidByCondition(HttpServletRequest request) {
		
		List<TkldPidOne> list=new ArrayList<TkldPidOne>();
		
		Integer adminUserId =Integer.parseInt( request.getSession().getAttribute("currentUserId").toString());
		AdminUser adminUser = adminUserDAO.findById(adminUserId);
		Integer userPidLevel=null;
		boolean isValid=true;
		
		if(adminUser.getLevel()>=95){
			userPidLevel=-1;
		}else{
			userPidLevel=1;
				
		}
		
		request.setAttribute("userPidLevel",userPidLevel);
		
		
		String pId= request.getParameter("pId");
		String userNames= request.getParameter("userNames");
		String adminNames= request.getParameter("adminNames");
		Integer level=Integer.parseInt( request.getParameter("level"));
		String pagestr = request.getParameter("page");
		StringBuffer param = new StringBuffer();
		
		
		DetachedCriteria criteria=DetachedCriteria.forClass(TkldPidOne.class,"t");
		
		if(isValid){
		
		Disjunction disjunction=Restrictions.disjunction();
		if(StringUtil.hasLength(pId)){
				criteria.add(Restrictions.eq("t.pId", pId));
				param.append("&pid="+pId);
		}
		
		if(StringUtil.hasLength(userNames)){
				criteria.createAlias("users","u");
				param.append("&userNames="+userNames);
				disjunction.add(Restrictions.eq("u.phone", userNames));
				//disjunction.add(Restrictions.eq("t.users.name", userName));
				disjunction.add(Restrictions.ilike("t.usersRemark", userNames,MatchMode.ANYWHERE));
				disjunction.add(Restrictions.eq("u.mycode", userNames));
		}
		
		if(StringUtil.hasLength(adminNames)){
				param.append("&adminNames="+adminNames);
				disjunction.add(Restrictions.ilike("t.adminUserReamrk", adminNames,MatchMode.ANYWHERE));
				disjunction.add(Restrictions.eq("t.ldLoginName", adminNames));
		}
	
		
		if(userPidLevel==-1){
				//criteria.createAlias("tkldPid","tt");
				if(level==null){
					level=1;
				}
				criteria.add(Restrictions.eq("t.level",level));	
				
		}else{
			
			if(level==null){
				level=2;
			}
			criteria.add(Restrictions.eq("t.level",level));	
			
			if(level==2){
				criteria.createAlias("tkldPid","tt");
				criteria.add(Restrictions.eq("tt.adminUser.id",adminUser.getId()));
			}
			if(level==3){
				criteria.createAlias("tkldPid","tt");
				criteria.createAlias("tt.tkldPid","ttt");
				criteria.add(Restrictions.eq("ttt.adminUser.id",adminUser.getId()));
			}
		}
		param.append("&level="+level);
	
		criteria.add(Restrictions.eq("t.isValid", true));
		
		
		 PageInfo pageInfo = new PageInfo();
		
		criteria.add(disjunction);
		
		criteria.setProjection(Projections.rowCount());
		int count =0;
		if(isValid){
			count = tkldPidDao.findCountByCondition(criteria).intValue();
		}
		 Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
	     int start =pageInfo.getCurrentPage() * pageInfo.getPageSize();  
	     int end = pageInfo.getPageSize();
	     pageInfo.setParam(param + "&page=");
		criteria.setProjection(null);
		
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY); 
		if(isValid){
			list= tkldPidDao.findPidByCondition(criteria,start, end);
		}
		 request.setAttribute("count", count);
	     request.setAttribute("list", list);
	     request.setAttribute("page", pagestr);
	     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	     request.setAttribute("level", level);
	     request.setAttribute("userNames", userNames);
	     request.setAttribute("adminNames", adminNames);
	     request.setAttribute("pId", pId);
		}
	}

	@Override
	public void partnerDetail(Integer id,HttpServletRequest request) {
		
		QueryModel queryModel=new QueryModel();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("tkldPid.id", id,"id");
		String pagestr = request.getParameter("page");
		StringBuffer param = new StringBuffer();
		
		param.append("&id="+id);
		 PageInfo pageInfo = new PageInfo();
		 int count = dateBaseDao.findCount(TkldPidOne.class, queryModel);
		 Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
	     int start =pageInfo.getCurrentPage() * pageInfo.getPageSize();  
	     int end = pageInfo.getPageSize();
	     pageInfo.setParam(param + "&page=");
		List<TkldPidOne> list = dateBaseDao.findPageList(TkldPidOne.class, queryModel, start,end );
	     	  request.setAttribute("list", list);
	 	     request.setAttribute("page", pagestr);
	 	     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public boolean upgradeCareer(HttpServletRequest request) {
			String id = request.getParameter("id");
			String level=request.getParameter("level"); //以后用来升级代理
			//升级 事业合伙人 
			try {
				TkldPidOne tkldPid = tkldPidDao.findById(Integer.parseInt(id));
				tkldPid.setTkldPid(tkldPid.getTkldPid().getTkldPid());
				tkldPid.setCreateTime(new Timestamp(System.currentTimeMillis()));
				tkldPid.setLevel(2);
				tkldPidDao.update(tkldPid);
			} catch (Exception e) {
				e.printStackTrace();
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return false;
			}
			return true;
	
	}

	@Override
	public PageResult<Users> findNextLevelByUserId(HttpServletRequest request,Integer currentPage, Integer pageSize,Integer  userId) {
		
			Users users = usersDAO.findById(userId);
		
			QueryModel queryModel=new QueryModel();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("invitecode", users.getMycode());
			Integer count= dateBaseDao.findCount(Users.class, queryModel);

			
			queryModel.setOrder("id asc");
				
			List<Users> list=dateBaseDao.findPageList(Users.class, queryModel,(currentPage-1),pageSize);
			
			StringBuilder sb=new StringBuilder();
			
			for(int i=0;i<list.size();i++){
				
				sb.append(list.get(i).getId()).append(",");
			}
			String uids="";
			if(sb.length()>0){
				 uids=sb.substring(0,sb.lastIndexOf(","));
			}
			
			queryModel.clearQuery();
			queryModel.combCondition("userId is not null");
			queryModel.combIn("userId",uids);
			queryModel.setGroup("group by userId");
			queryModel.setOrder("lasttime desc,userId asc");
			
			List<UserLoginRecord> loginRecord = dateBaseDao.findLists(UserLoginRecord.class, queryModel);
			
			Map<Integer, Object> map=new HashMap<Integer, Object>();
			
			for (UserLoginRecord userLoginRecord : loginRecord) {
				map.put(userLoginRecord.getUsers().getId(), userLoginRecord);
			}
			
			PageResult<Users> rst=new PageResult<Users>(count, pageSize, currentPage, list);
			request.setAttribute("record", map);
			
		return rst;
	}


}
