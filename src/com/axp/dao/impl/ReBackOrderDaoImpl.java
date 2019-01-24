package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.IReBackOrderDao;
import com.axp.model.ReBackOrder;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.Users;
import com.axp.util.DateUtil;

@Repository
@SuppressWarnings("unchecked")
public class ReBackOrderDaoImpl  extends BaseDaoImpl<ReBackOrder> implements IReBackOrderDao{
	
	@Override
	public List<ReBackOrder> getBackListBySeller(Integer sellerId,Integer backState){
		Session session = getSessionFactory().getCurrentSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("from ReBackOrder where 1=1 ");
		if(sellerId!=null)
			queryString.append("and seller.id = :sellerId ");
		if(backState!=null)
			queryString.append("and backState = :backState ");
		queryString.append("and isValid = true");
		Query queryObject = session.createQuery(queryString.toString());
		if(sellerId!=null)
			queryObject.setParameter("sellerId", sellerId);
		if(backState!=null)
			queryObject.setParameter("backState", backState);
		
		return queryObject.list();
	}
	
	
	@Override
	public ReBackOrder saveBackOrder(ReGoodsorderItem item, Users user, String reason, Integer type, String imageJson,Integer backState ){
		String backCode = DateUtil.formatDate("yyyyMMddHHmmssss", DateUtil.getNow());
		backCode = backCode + (new Random().nextInt(90)+10);
		
		ReBackOrder back = new ReBackOrder();
		back.setBackCode(backCode);
		back.setBackstate(backState);
		back.setCreateTime(new Timestamp(System.currentTimeMillis()));
		back.setGoodid(item.getGoodsId());
		back.setGoodQuantity(item.getGoodQuantity());
		back.setImage(item.getGoodPic());
		back.setIsValid(true);
		back.setBackmoney(item.getPayPrice());
		back.setMallClass(item.getMallClass());
		back.setMallId(item.getMallId());
		back.setOrderItem(item);
		back.setPayCashpoint(item.getPayCashpoint());
		back.setPaymoney(item.getPayPrice());
		back.setPayScore(item.getPayScore());
		back.setPaytype(item.getOrder().getPayType());
		back.setReason(reason);
		back.setSeller(item.getOrder().getSeller());
		back.setSellerCode(item.getOrder().getSeller().getId());
		back.setType(type);
		back.setUser(user);
		back.setImage(imageJson);
		back.setExOrderStatus(item.getStatus());//申请退单的原单状态
		save(back);
		return back;
	}
	
}
