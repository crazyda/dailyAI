package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ICashmoneyRecordDao;
import com.axp.model.CashmoneyRecord;
import com.axp.model.Scorerecords;
import com.axp.model.SellerMoneyRecord;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.util.StringUtil;

@Repository
public class CashmoneyRecordDaoImpl extends BaseDaoImpl<CashmoneyRecord> implements ICashmoneyRecordDao{
	private static Map<Integer  , String > descripConfig; 
	private static Map<Integer  , String > titleDescripConfig; //标题
	
	@PostConstruct
	private void init() {
		if(descripConfig==null){
			descripConfig = new HashMap<Integer, String>();
			descripConfig.put(BACK, "退货返还现金");
			descripConfig.put(BUY, "购买商品扣除钱包余额");
			descripConfig.put(CASH, "提现扣除钱包余额");
		}
		if(titleDescripConfig == null){
			titleDescripConfig = new HashMap<Integer, String>();
			titleDescripConfig.put(BUY, "购物");
			titleDescripConfig.put(BACK, "退货");
			titleDescripConfig.put(CASH, "提现");
		}
	}
	

	@Override
	public CashmoneyRecord updateRecord(Users user,Double updateValue,Integer type,Integer relateId, String relateClass){
		if(updateValue==null||updateValue==0){
			return null;
		}
		CashmoneyRecord record = new CashmoneyRecord();
		record.setBeforeMoney(user.getMoney());
		record.setMoney(updateValue);
		record.setAfterMoney(user.getMoney()+updateValue);
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		record.setIsValid(true);
		record.setRemark(descripConfig.get(type));
		record.setRelateId(relateId);
		record.setRelateName(relateClass);
		record.setType(type);
		record.setUsersByUserId(user);
		super.save(record);
		return record;
		
	}

	@Override
	public PageResult<CashmoneyRecord> getCashMoneyChangeRecord(
			Integer userId, Integer currentPage, Integer pageSize,HttpServletRequest request) {
		 String type = request.getParameter("type");
		 Session session = sessionFactory.getCurrentSession();
		 StringBuffer sb = new StringBuffer();
		 String queryCount = "";
	     String queryList = "";
		 if (StringUtils.isNotBlank(type)&&!type.equals("请选择")) {
			 if("1".equals(type)){
				sb.append(" and remark like'%"+"红包"+"%'");
			}else if("2".equals(type)){
				sb.append(" and remark like'%"+"退货"+"%'");
			}else if("3".equals(type)){
				sb.append(" and remark like'%"+"提现"+"%'");
			}else if("4".equals(type)){
				sb.append(" and remark like'%"+"充值"+"%'");
			}else if("5".equals(type)){
				sb.append(" and remark like'%"+"购买商品"+"%'");
			}else if("6".equals(type)){
				sb.append(" and remark like'%"+"推广者获得收益"+"%'");
			}else if("7".equals(type)){
				sb.append(" and remark like'%"+"合伙人"+"%'");
			}else if("-1".equals(type)){
				sb.append("");
			}
			 
			 queryCount = "select count(r) from CashmoneyRecord r where r.isValid=1 and r.usersByUserId.id=" + userId + sb;
		     queryList = "from CashmoneyRecord r where r.isValid=1 and r.usersByUserId.id=" + userId + sb +" order by r.id desc";
		 }else{
			 //先获取查询条件；
		       queryCount = "select count(r) from CashmoneyRecord r where r.isValid=1 and r.usersByUserId.id=" + userId;
		       queryList = "from CashmoneyRecord r where r.isValid=1 and r.usersByUserId.id=" + userId +" order by r.id desc";
		 }
	       

	        //先查询总条数；
	        Long count = (Long) session.createQuery(queryCount).uniqueResult();

	        //计算当前页的数据；usersByUserId
	        List<CashmoneyRecord> list = session.createQuery(queryList)
	                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                .list();

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}


	@Override
	public Object[] getTotalIncomeAndExpend(Integer userId, Integer type) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT SUM(case when money > 0 then money else 0 end),SUM(case when money < 0 then money else 0 end) from CashmoneyRecord where userId=:userId AND type=:type AND isValid=true";
		Object[] obj = (Object[]) session.createQuery(hql).setParameter("userId", userId).setParameter("type", type).uniqueResult();
	
		if(obj[0]==null){
			obj[0]=0.0;
		}
		if(obj[1]==null){
			obj[1]=0.0;
		}
		return obj;
	}
	
	
	@Override
	public void updateMoneyById(Integer itemId) {
			Session session = getSessionFactory().getCurrentSession();
			
			String queryString = "update CashmoneyRecord set isValid = false where relateId =:itemId ";
			Query queryObject = session.createQuery(queryString);
			
			queryObject.setParameter("itemId", itemId);

			queryObject.executeUpdate();
	}
//da

	@Override
	public PageResult<CashmoneyRecord> getCashMoneyRecord(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		String queryCount = "";
	    String queryList = "";
	    
	    queryList = "select  r,SUM(CASE when money>0 then money else 0 END)  ,SUM(CASE when money<0 then money else 0 END) "
	    								+ "from CashmoneyRecord r where r.isValid=1 GROUP BY r.usersByUserId.id ";
	    	//queryList = "select  r,SUM(money) from CashmoneyRecord r where r.isValid=1 GROUP BY r.usersByUserId.id ";
	    	queryCount = "select count(r) from CashmoneyRecord r where r.isValid=1 and r.usersByUserId.id="+userId;
	  //先查询总条数；
        Long count = (Long) session.createQuery(queryCount).uniqueResult();
        //计算当前页的数据；usersByUserId
        List<CashmoneyRecord> list = session.createQuery(queryList)
                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
                .list();
       
      
        
       
		return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

//da
	@Override
	public PageResult<CashmoneyRecord> getOneCashMoneyRecord(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		String queryCount = "";
	    String queryList = "";
	    queryCount = "select count(r) from CashmoneyRecord r where r.isValid=1 and r.usersByUserId.id="+userId;
	    queryList = "from CashmoneyRecord r where r.isValid=1 and r.usersByUserId="+userId+"ORDER BY createTime DESC";
	    Long count = (Long) session.createQuery(queryCount).uniqueResult();
	    List<CashmoneyRecord> list = session.createQuery(queryList).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
                .list();
	    
	  
	    
		return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

}
