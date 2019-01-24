package com.axp.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminuserWithdrawalsDao;
import com.axp.dao.SellerWithdrawalsDao;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.GetmoneyRecord;
import com.axp.model.UserLoginRecord;
import com.axp.query.PageResult;


@Repository
public class SellerWithdrawalsImpl extends BaseDaoImpl<AdminuserWithdrawals> implements SellerWithdrawalsDao{

	@Override
	public PageResult<AdminuserWithdrawals> getCheckList(Integer currentPage,
			Integer pageSize,Integer state) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount;
	        String queryList;

	       
	            queryCount = "select count(r) from AdminuserWithdrawals r where r.isValid=1 and ( r.state = "+AdminuserWithdrawals.yi_zhi_fu+" or r.state="+AdminuserWithdrawals.zhi_fu_cheng_gong+" or r.state="+AdminuserWithdrawals.zhi_fu_shi_bai+" )";
	            queryList = "from AdminuserWithdrawals r where r.isValid=1 and (  r.state = "+AdminuserWithdrawals.yi_zhi_fu+" or r.state="+AdminuserWithdrawals.zhi_fu_cheng_gong+" or r.state="+AdminuserWithdrawals.zhi_fu_shi_bai+"  ) order by r.id desc";
	        

	        Long count;
	        List<AdminuserWithdrawals> list;
	        
	            //先查询总条数；
	            count = (Long) session.createQuery(queryCount).uniqueResult();
	            //计算当前页的数据；
	            list = session.createQuery(queryList)
	                    .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                    .list();
	       

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	    }

	@Override
	public PageResult<AdminuserWithdrawals> getCheckListByStatus(
			Integer currentPage, Integer pageSize, Integer state) {
		
		 Session session = sessionFactory.getCurrentSession();

		 //先获取查询条件；
        String queryCount;
        String queryList;

       
            queryCount = "select count(r) from AdminuserWithdrawals r where r.isValid=1 and r.state = 0 ";
            queryList = "from AdminuserWithdrawals r where  r.isValid=1 and r.state = 0  order by r.id desc";
        

        Long count;
        List<AdminuserWithdrawals> list;
        
            //先查询总条数；
            count = (Long) session.createQuery(queryCount).uniqueResult();
            //计算当前页的数据；
            list = session.createQuery(queryList)
                    .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
                    .list();
       

        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

	
	@Override
	public PageResult<AdminuserWithdrawals> getSellerPayList(
			Integer currentPage, Integer pageSize) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount;
	        String queryList;

	       
	            queryCount = "select count(r) from AdminuserWithdrawals r where r.isValid=1 and r.state ="+AdminuserWithdrawals.shen_he_tong_guo+"";
	            queryList = "from AdminuserWithdrawals r where r.isValid=1 and r.state = "+AdminuserWithdrawals.shen_he_tong_guo+" order by r.id desc";
	        

	        Long count;
	        List<AdminuserWithdrawals> list;
	        
	            //先查询总条数；
	            count = (Long) session.createQuery(queryCount).uniqueResult();
	            //计算当前页的数据；
	            list = session.createQuery(queryList)
	                    .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                    .list();
	       

	        //返回结果；
	        return new PageResult<AdminuserWithdrawals>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
		
		
		
	}

	/**
	 * 查询商家提现已支付的列表
	 */
	@Override
	public List<AdminuserWithdrawals> getSellerPayListByStatus() {
		Session session = sessionFactory.getCurrentSession();
		String queryList = "from AdminuserWithdrawals r where r.isValid=1 and  r.state="+AdminuserWithdrawals.zhi_fu_cheng_gong;
		return session.createQuery(queryList).list();
	}

	@Override
	public Integer getSum(HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		String state=request.getParameter("state");
		 String userId=request.getParameter("userId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 String type=request.getParameter("type");
		 String btn = request.getParameter("btn");
		 StringBuffer sb=new StringBuffer();
		
		
		 Query query1=null;
		 if (StringUtils.isNotBlank(type) && type.equals("1") && StringUtils.isNotBlank(btn)) {
			 if (StringUtils.isNotBlank(state)) {
					sb.append(" and state=").append(state);
				}
			 if (StringUtils.isNotBlank(userId)) {
					sb.append(" and adminUser.loginname='").append(userId).append("'");
				}
			 if (StringUtils.isNotBlank(startTM)) {
					sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
				 }
				 if (StringUtils.isNotBlank(endTM)) {
					 sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
				}
			 if (StringUtils.isNotBlank(startTM)&&StringUtils.isNotBlank(endTM)) {
				sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
				sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
			 }
			 query1=session.createQuery("select count(a) from AdminuserWithdrawals a where isValid=1 and a.state=10"+sb);
		}else if(StringUtils.isNotBlank(type) && type.equals("2") && StringUtils.isNotBlank(btn)){
			 if (StringUtils.isNotBlank(state)) {
					sb.append(" and status=").append(state);
				}
			 if (StringUtils.isNotBlank(userId)) {
					sb.append(" and users.name='").append(userId).append("'");
				}
			 if (StringUtils.isNotBlank(startTM)) {
					sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
				 }
				 if (StringUtils.isNotBlank(endTM)) {
					 sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
				}
			 if (StringUtils.isNotBlank(startTM)&&StringUtils.isNotBlank(endTM)) {
				sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
				sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
			 }
			query1=session.createQuery("select count(a) from GetmoneyRecord a where isValid=1 and a.status=10"+sb);
		}
		   
		return Integer.parseInt(query1.uniqueResult().toString());
	}

	@Override
	public List<AdminuserWithdrawals> getSellerWithdrawalList(HttpServletRequest request, Integer start, Integer end) {
		
		Session session = sessionFactory.getCurrentSession();
		String state=request.getParameter("state");
		 String userId=request.getParameter("userId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 StringBuffer sb=new StringBuffer();
		 
		 if (StringUtils.isNotBlank(state)) {
			sb.append(" and state=").append(state);
		}
		 if (StringUtils.isNotBlank(userId)) {
			sb.append(" and adminUser.loginname='").append(userId).append("'");
		}
		 if (StringUtils.isNotBlank(startTM)) {
				sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
			 }
		 if (StringUtils.isNotBlank(endTM)) {
			 sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
		}
		 if (StringUtils.isNotBlank(startTM)&&StringUtils.isNotBlank(endTM)) {
				sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
				sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
		 }
			 Query query1=session.createQuery("from  AdminuserWithdrawals a where a.isValid=1 and a.state=10"+sb+"order by id desc");   
			 List<AdminuserWithdrawals> list = query1.setFirstResult(start).setMaxResults(end).list();
			 return list;
	}

	@Override
	public List<GetmoneyRecord> getUserWithdrawalList(HttpServletRequest request, Integer start, Integer end) {
		Session session = sessionFactory.getCurrentSession();
		String state=request.getParameter("state");
		 String userId=request.getParameter("userId");
		 String startTM=request.getParameter("sTM");
		 String endTM=request.getParameter("eTM");
		 StringBuffer sb=new StringBuffer();
		 
		 if (StringUtils.isNotBlank(state)) {
			sb.append(" and status=").append(state);
		}
		 if (StringUtils.isNotBlank(userId)) {
			sb.append(" and users.name='").append(userId).append("'");
		}
		 if (StringUtils.isNotBlank(startTM)) {
				sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
			 }
			 if (StringUtils.isNotBlank(endTM)) {
				 sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
			}
			 if (StringUtils.isNotBlank(startTM)&&StringUtils.isNotBlank(endTM)) {
					sb.append(" and yiLianPayTime>'").append(startTM).append(" 00:00:00").append("'");
					sb.append(" and yiLianPayTime<'").append(endTM).append(" 23:59:59").append("'");
			 }
			 Query query1=session.createQuery("from  GetmoneyRecord a where a.isValid=1 and a.status=10"+sb+"order by id desc");   
			 List<GetmoneyRecord> list = query1.setFirstResult(start).setMaxResults(end).list();
			 return list;
	}

	


	

	
	}


