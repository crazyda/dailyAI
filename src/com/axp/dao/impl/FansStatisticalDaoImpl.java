package com.axp.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.axp.dao.DateBaseDAO;
import com.axp.dao.FansStatisticalDao;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.model.ProvinceEnum;
import com.axp.model.UserLoginRecord;
import com.axp.model.UsersOperationRecord;
import com.axp.util.QueryModel;

@Repository
public class FansStatisticalDaoImpl extends BaseDaoImpl<UsersOperationRecord> implements FansStatisticalDao{

	@Autowired
	DateBaseDAO dateBaseDAO;
	@Autowired
	ProvinceEnumDAO provinceEnumDAO;
	@Override
	public List<UsersOperationRecord> getOperation(HttpServletRequest request,Integer start,Integer end) {
		 Session session = sessionFactory.getCurrentSession(); 
		 String zoneId=request.getParameter("zoneId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 StringBuffer sb=new StringBuffer();
		 if (StringUtils.isNotBlank(zoneId)) {
			sb.append(" and zoneId=").append(zoneId);
		}
		 if (StringUtils.isNotBlank(startTM)&&StringUtils.isNotBlank(endTM)) {
			sb.append(" and createtime>'").append(startTM).append("'");
			sb.append(" and createtime<'").append(endTM).append("'");
		 }
		 
		//查询总条数；
	    Query query1=session.createQuery("select a from UsersOperationRecord a where a.createtime in(select max(b.createtime)from UsersOperationRecord b group by b.userId ) "+sb+" and a.userId is not NUll group by a.userId ");   
		 //返回结果；
	    List<UsersOperationRecord> list = query1.setFirstResult(start).setMaxResults(end).list();
	    return list;
	}

	
	//计算总条数分页
	@Override
	public Integer getCount(HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession(); 
		 String zoneId=request.getParameter("zoneId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 StringBuffer sb=new StringBuffer();
		 if (StringUtils.isNotBlank(zoneId)) {
			sb.append(" and zoneId=").append(zoneId);
		}
		 if (StringUtils.isNotBlank(startTM)&&StringUtils.isNotBlank(endTM)) {
			sb.append(" and createtime>'").append(startTM).append("'");
			sb.append(" and createtime<'").append(endTM).append("'");
		 }
		 
	    Query query1=session.createQuery("select count(a) from UsersOperationRecord a where a.createtime in(select max(b.createtime)from UsersOperationRecord b group by b.userId ) "+sb+" and a.userId is not NUll");   
		return Integer.parseInt(query1.uniqueResult().toString());
	}

//============================start-ZHANGLU============================//
	@Override
	public List<UserLoginRecord> getRecordList(HttpServletRequest request,
			Integer start, Integer end) {

		Session session = sessionFactory.getCurrentSession(); 
		 String zoneId=request.getParameter("zoneId");
		 String userId=request.getParameter("userId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 StringBuffer sb=new StringBuffer();
		 
		 if (StringUtils.isNotBlank(zoneId)) {
			 ProvinceEnum enum1 = provinceEnumDAO.findById(Integer.valueOf(zoneId));
			 if (enum1.getLevel()==2) {
				 QueryModel queryModel = new QueryModel();
					queryModel.combPreEquals("isValid", true);
					queryModel.combPreEquals("provinceEnum.id", Integer.parseInt(zoneId), "parentId");
					List<ProvinceEnum> plist = dateBaseDAO.findLists(ProvinceEnum.class, queryModel);
					if (plist!=null&&plist.size()>0) {
						sb.append(" and zoneId in(");
						for(int i=0;i<plist.size();i++){
							if(i==(plist.size()-1)){
								sb.append(plist.get(i).getId().toString());
							}else{
								sb.append(plist.get(i).getId().toString()+",");
							}
						}
						if(sb.indexOf(",")!=-1){
						sb.append(","+zoneId.toString());
						}else{
							sb.append(zoneId.toString());	
						}
						sb.append(")");
						
					}
			 }else{
					sb.append(" and zoneId=").append(zoneId);
			 }
			 
			}
		 if (StringUtils.isNoneBlank(userId)) {
			sb.append(" and users.name=").append(userId);
		}
		 if (StringUtils.isNotBlank(startTM)) {
			sb.append(" and lasttime>'").append(startTM).append("'");
		 }
		 if (StringUtils.isNotBlank(endTM)) {
			 sb.append(" and lasttime<'").append(endTM).append("'");
		}
		 
		 Query query1=session.createQuery("from UserLoginRecord where id in"
				+ " (select id from UserLoginRecord where users.id is not null order by id desc )  "+sb+" group by users.id  order by id desc ) ");
		 
		 
		//查询总条数；
	    //Query query1=session.createQuery("from UserLoginRecord where users.id is not NUll"+sb);   
		 //返回结果；
	    List<UserLoginRecord> list = query1.setFirstResult(start).setMaxResults(end).list();
	    return list;
	}


	@Override
	public Integer getSum(HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession(); 
		 String zoneId=request.getParameter("zoneId");
		 String userId=request.getParameter("userId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 StringBuffer sb=new StringBuffer();
		 
		 
		 if (StringUtils.isNotBlank(zoneId)) {
			 ProvinceEnum enum1 = provinceEnumDAO.findById(Integer.valueOf(zoneId));
			 if (enum1.getLevel()==2) {
				 QueryModel queryModel = new QueryModel();
					queryModel.combPreEquals("isValid", true);
					queryModel.combPreEquals("provinceEnum.id", Integer.parseInt(zoneId), "parentId");
					List<ProvinceEnum> plist = dateBaseDAO.findLists(ProvinceEnum.class, queryModel);
					if (plist!=null&&plist.size()>0) {
						sb.append(" and zoneId in(");
						for(int i=0;i<plist.size();i++){
							if(i==(plist.size()-1)){
								sb.append(plist.get(i).getId().toString());
							}else{
								sb.append(plist.get(i).getId().toString()+",");
							}
						}
						if(sb.indexOf(",")!=-1){
						sb.append(","+zoneId.toString());
						}else{
							sb.append(zoneId.toString());	
						}
						sb.append(")");
						
					}
			 }else{
					sb.append(" and zoneId=").append(zoneId);
			 }
		 }
		 if (StringUtils.isNoneBlank(userId)) {
				sb.append(" and users.name=").append(userId);
			}
		 if (StringUtils.isNotBlank(startTM)) {
				sb.append(" and lasttime>'").append(startTM).append("'");
			 }
			 if (StringUtils.isNotBlank(endTM)) {
				 sb.append(" and lasttime<'").append(endTM).append("'");
			}
		 
			 Query query1=session.createQuery(" from UserLoginRecord a where id in"
						+ " (select id from UserLoginRecord where users.id is not null order by id desc )  "+sb+" group by users.id   ) ");
		return query1.list().size();
	}
//==========================end==============================//
}
