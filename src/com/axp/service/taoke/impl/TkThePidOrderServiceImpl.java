package com.axp.service.taoke.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.model.CommissionRate;
import com.axp.model.TkThePidOrder;
import com.axp.model.TkThePidOrderInfo;
import com.axp.model.TkldPid;
import com.axp.service.system.CommissionRateService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.TkThePidOrderService;
import com.axp.service.taoke.TkldPidService;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
@Service
public class TkThePidOrderServiceImpl  extends BaseServiceImpl implements TkThePidOrderService{

	@Autowired
	private TkldPidService tkldPidService;
	
	@Autowired
	private CommissionRateService commissionRateService;
	
	
	//分配收益
	public void distributeEarnings(TkThePidOrder tkThePidOrder,CommissionRate commissionRate){
		 TkldPid tkldPid = tkThePidOrder.getTkldPid();
		 TkThePidOrderInfo orderInfo3=null; //合伙人
		 TkThePidOrderInfo orderInfo2=null; //事业
		 TkThePidOrderInfo orderInfo1=null; //代理
		 Double money=tkThePidOrder.getDistributeMoney();
		 Double hhr=CalcUtil.div(CalcUtil.mul(money,commissionRate.getPartner()), 100, 2);
		 Double sy= CalcUtil.div(CalcUtil.mul(money,commissionRate.getCareer()), 100, 2);
		 Double sy2= CalcUtil.div(CalcUtil.mul(money,commissionRate.getCareer()), 100, 2);
		 Double dl= CalcUtil.div(CalcUtil.mul(money,commissionRate.getAgent()), 100, 2);
		 hhr=hhr>0.1?hhr:0d;
		 sy=sy>0.1?sy:0d;
		 sy2=sy2>0.1?sy2:0d;
		 dl=dl>0.1?dl:0d;
		 if(tkldPid.getLevel()==3&&tkldPid.getTkldPid()!=null&&tkldPid.getTkldPid().getTkldPid()!=null){
			 orderInfo3=buildOrderInfo(tkThePidOrder,hhr,tkldPid);
			 orderInfo2=buildOrderInfo(tkThePidOrder,sy,tkldPid.getTkldPid());
			 orderInfo1=buildOrderInfo(tkThePidOrder,dl,tkldPid.getTkldPid().getTkldPid());
		 }else if(tkldPid.getLevel()==3&&tkldPid.getTkldPid()!=null){
			 orderInfo3=buildOrderInfo(tkThePidOrder,hhr,tkldPid);
			 orderInfo2=buildOrderInfo(tkThePidOrder,sy,tkldPid.getTkldPid());
		 }else if(tkldPid.getLevel()==2&&tkldPid.getTkldPid()!=null){
			 orderInfo2=buildOrderInfo(tkThePidOrder,sy2,tkldPid);
			 orderInfo1=buildOrderInfo(tkThePidOrder,dl,tkldPid.getTkldPid());
		 }else if(tkldPid.getLevel()==2&&tkldPid.getTkldPid()==null){
			 orderInfo2=buildOrderInfo(tkThePidOrder, sy2,tkldPid);
		 }else if(tkldPid.getLevel()==1){
			 money=dl+sy+hhr;
			 orderInfo1=buildOrderInfo(tkThePidOrder,money,tkldPid);
		 }
		 
		 if(orderInfo3!=null){ //合伙人
			 orderInfo3.setCommissionRate(commissionRate);
			 tkThePidOrderInfoDao.save(orderInfo3);
		 }
		 if(orderInfo2!=null){ //事业
			 orderInfo2.setCommissionRate(commissionRate);
			 tkThePidOrderInfoDao.save(orderInfo2);
		 }
		 if(orderInfo1!=null){ //代理
			 orderInfo1.setCommissionRate(commissionRate);
			 tkThePidOrderInfoDao.save(orderInfo1);
		 }
		 
		 tkThePidOrder.setIsAmount(1);
		 tkThePidOrderDao.update(tkThePidOrder);
	}

	
	//构建OrderInfo对象
	private TkThePidOrderInfo buildOrderInfo(TkThePidOrder order,Double money,TkldPid tkldPid){
		TkThePidOrderInfo orderInfo=new TkThePidOrderInfo();
		orderInfo.setTkldPid(tkldPid);
		orderInfo.setOrderNumber(order.getOrderNumber());
		orderInfo.setPayMoney(order.getPayMoney());
		orderInfo.setDistributeMoney(money);
		orderInfo.setOrderStatus(order.getOrderStatus());
		orderInfo.setOrderDate(order.getOrderDate());
		orderInfo.setOrderName(order.getOrderName());
		orderInfo.setOrderPic(order.getOrderPic());
		orderInfo.setIsAmount(1);
		orderInfo.setLevel(order.getLevel());
		orderInfo.setStatusDesc(order.getStatusDesc());
		orderInfo.setUserAccount(order.getUserAccount());
		orderInfo.setUserName(order.getUserName());
		orderInfo.setUserIcon(order.getUserIcon());
		orderInfo.setGoodsId(order.getGoodsId());
		return orderInfo;
	}
	
	//保存订单
	public void saveOrUpdateOrder(Integer pid){
		
		QueryModel model=new QueryModel();
		 String ids = findNextLevelPid(pid,new StringBuffer());
		 model.combInStr("id", ids);
		 List<TkldPid> list = (List<TkldPid>) dateBaseDao.findList(TkldPid.class, model);
		 for (int i = 0; i < list.size(); i++) {
			 model.clearQuery();
			 model.combPreEquals("tkldPid.id",list.get(i).getId(),"pid");    
			 model.combEquals("isAmount",0);
			 List<TkThePidOrder> savePidOrderList = dateBaseDao.findLists(TkThePidOrder.class, model);
			 model.clearQuery();
			 model.combPreEquals("tkldPid.id",list.get(i).getId(),"pid");
			 model.combEquals("isAmount",1);
			 List<TkThePidOrder> updatePidOrderList = dateBaseDao.findLists(TkThePidOrder.class, model);
			 CommissionRate commissionRate = commissionRateService.findCommissionRate();
			 if(commissionRate!=null){
				 for (TkThePidOrder tkThePidOrder : savePidOrderList) {
				 	 distributeEarnings(tkThePidOrder,commissionRate);
				 }
				 for (TkThePidOrder tkThePidOrder : updatePidOrderList) {
					 	 updateOrderInfo(tkThePidOrder);
				 }
			 }
		 }
	}
	
	
	 public String findNextLevelPid(Integer pid,StringBuffer sb){
			sb.append(",").append(pid);
			QueryModel model=new QueryModel();
			 model.combPreEquals("tkldPid.id", pid,"pid");
			 model.combEquals("isValid", 1);
			 List<TkldPid> list = (List<TkldPid>) dateBaseDao.findList(TkldPid.class, model);
			 if(list.size()>0){
				 for (TkldPid tkldPid : list) {
					  findNextLevelPid(tkldPid.getId(),sb);
				}
			 }
				 return sb.substring(1,sb.length());
	}
	
	
		//更新orderInfo
		public void  updateOrderInfo(TkThePidOrder order){
			QueryModel model=new QueryModel();
			model.combEquals("orderNumber",order.getOrderNumber());
			List<TkThePidOrderInfo> orderInfoList = dateBaseDao.findLists(TkThePidOrderInfo.class, model);
			if(orderInfoList.size()>0){
				for (TkThePidOrderInfo tkThePidOrderInfo : orderInfoList) {
					if(order.getOrderNumber().equals(tkThePidOrderInfo.getOrderNumber())&&!tkThePidOrderInfo.getOrderStatus().equals(order.getOrderStatus())){
							tkThePidOrderInfo.setOrderStatus(order.getOrderStatus());
							tkThePidOrderInfo.setStatusDesc(order.getStatusDesc());
							tkThePidOrderInfoDao.update(tkThePidOrderInfo);
					}
				}
			}
		}     
}
