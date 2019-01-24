package com.axp.service.order.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.ChangeOrderDao;
import com.axp.dao.ReBaseGoodsDAO;
import com.axp.dao.ScorerecordsDAO;
import com.axp.dao.UsersDAO;
import com.axp.model.ChangeOrder;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsOfChangeMall;
import com.axp.model.Scorerecords;
import com.axp.model.Users;
import com.axp.service.order.ChangeOrderService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
@Service
public class ChangeOrderServiceImpl extends BaseServiceImpl implements ChangeOrderService{

	@Autowired
	ChangeOrderDao changeOrderDao;
	@Autowired
	ReBaseGoodsDAO reBaseGoodsDao;
	@Autowired
	ScorerecordsDAO scorerecordsDao;
	@Autowired
	UsersDAO usersDao;
	
	/**
	 * 换货会订单自动任务(确认收货,取消订单)
	 */
	@Override
	public void ChangeOrderAutomaticTask() {
		try {
			
		
		//未处理超时订单
		List<ChangeOrder> changeList = changeOrderDao.getChangeOrderOverdueUntreated();
		
		//自动确认收货订单
		List<ChangeOrder> changeAutoList = changeOrderDao.getChangeOrderAutomaticReceipt();
		
		for (ChangeOrder changeOrder : changeList) {
		
			//未处理和拒绝一样操作
			//ReGoodsOfChangeMall inviteGoods = changeOrder.getInviteGoods();
			Users inviteUsers = changeOrder.getInviteUsers();
			if(changeOrder.getChangeType()==ChangeOrder.CHANGEGOODS){
				//退邀请方库存
				
			}else{
				//退积分
				Scorerecords scorerecords = new Scorerecords();
				scorerecords.setBeforeScore(inviteUsers.getScore());
				scorerecords.setAfterScore((int)CalcUtil.add(inviteUsers.getScore(), changeOrder.getChangeScore()));
				scorerecords.setIsvalid(true);
				scorerecords.setCreatetime(new Timestamp(System.currentTimeMillis()));
				scorerecords.setScore(changeOrder.getChangeScore());
				scorerecords.setScoretype("换货订单超时,退还积分:"+changeOrder.getChangeScore());
				scorerecords.setRemark("换货订单超时,退还积分:"+changeOrder.getChangeScore());
				scorerecords.setType(17);
				scorerecords.setUsers(inviteUsers);
				scorerecords.setAdminuserId(1);
				scorerecordsDao.save(scorerecords);
				inviteUsers.setScore(inviteUsers.getScore()+changeOrder.getChangeScore());
				usersDao.merge(inviteUsers);
			}
			
			changeOrder.setStatus(ChangeOrder.REFUSE);
			changeOrderDao.merge(changeOrder);
		}
		
		//正式要改的有 这个时间 还有sql查询的时间 还有 xml定时时间
		
		
			//收货超时订单
			for (ChangeOrder changeOrder : changeAutoList) {
				boolean isInvite=true; //默认:甲方(邀请方) 收货超时方   如乙方发货超过72小时 那么要修改的就是甲方 反之
				
			   if(changeOrder.getInviteSendGoodsTime()!=null&&new Date().after(DateUtil.addHour2Date(ChangeOrder.HOUR,changeOrder.getInviteSendGoodsTime()))){
					isInvite=false;
			   }
				
				 //如果是积分换   超时收货的一定是甲方  因为乙方没有收货这个选择
				if(changeOrder.getChangeType()==ChangeOrder.CHANGEGOODS){ //如果是物物换
						
						if(isInvite){  //甲方收货
							changeOrder.setInviteStatus(ChangeOrder.orderStatus_yi_shou_huo); //甲方状态设为已收货
							changeOrder.setInviteConfirmTime(new Timestamp(System.currentTimeMillis()));
							//如果乙方已收货那么订单完成
							if(changeOrder.getAcceptStatus()==ChangeOrder.orderStatus_yi_shou_huo){
								changeOrder.setInviteStatus(ChangeOrder.orderStatus_jiao_yi_wan_cheng);  //甲方完成
								changeOrder.setAcceptStatus(ChangeOrder.orderStatus_jiao_yi_wan_cheng);	//乙方完成
							}
						}else{  //乙方收货
							changeOrder.setAcceptStatus(ChangeOrder.orderStatus_yi_shou_huo);
							changeOrder.setAcceptConfirmTime(new Timestamp(System.currentTimeMillis())); //收货时间
							if(changeOrder.getInviteStatus()==ChangeOrder.orderStatus_yi_shou_huo){ //如果访问者是乙方且收货 那么就看看甲方是否已收货
								changeOrder.setAcceptStatus(ChangeOrder.orderStatus_jiao_yi_wan_cheng);  //双方交易完成
								changeOrder.setInviteStatus(ChangeOrder.orderStatus_jiao_yi_wan_cheng);
							}
						}
				}else if(changeOrder.getChangeType()==ChangeOrder.CHANGESCORE){ //如果是积分换
						
						if(isInvite){ //甲方收货    甲方是积分支付 所以乙方不存在收货这个选择
							changeOrder.setInviteStatus(ChangeOrder.orderStatus_yi_shou_huo); 
							changeOrder.setInviteConfirmTime(new Timestamp(System.currentTimeMillis())); //收货时间
							
							if(changeOrder.getInviteStatus()==ChangeOrder.orderStatus_que_ren_zhi_fu){  //如果甲方确认支付积分状态 则双方完成
								changeOrder.setInviteStatus(ChangeOrder.orderStatus_jiao_yi_wan_cheng);  //甲方完成
								changeOrder.setAcceptStatus(ChangeOrder.orderStatus_jiao_yi_wan_cheng);	//乙方完成
								
								//交易完成 乙方获得积分
								Users acceptUsers = changeOrder.getAcceptUsers();
								Scorerecords scorerecords = new Scorerecords();
								scorerecords.setBeforeScore(acceptUsers.getScore());
								scorerecords.setAfterScore((int)CalcUtil.add(acceptUsers.getScore(), changeOrder.getChangeScore()));
								scorerecords.setIsvalid(true);
								scorerecords.setCreatetime(new Timestamp(System.currentTimeMillis()));
								scorerecords.setScore(changeOrder.getChangeScore());
								scorerecords.setScoretype("换货订单已完成,获得积分:"+changeOrder.getChangeScore());
								scorerecords.setRemark("换货订单已完成,获得积分:"+changeOrder.getChangeScore());
								scorerecords.setType(17);
								scorerecords.setUsers(acceptUsers);
								scorerecordsDao.save(scorerecords);
								acceptUsers.setScore(acceptUsers.getScore()+changeOrder.getChangeScore());
								usersDao.merge(acceptUsers);
								
							}
						}
				}
				
				changeOrderDao.merge(changeOrder);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
		
		
		
		
		
		
	}
	
	//修改库存
			public void updateGoodsStock(Boolean isStandard,ReGoodsOfChangeMall changeMall,Integer value, boolean isAdd,Integer StandardId) throws Exception{
				ReBaseGoods good = (ReBaseGoods)changeMall;
				ReBaseGoods newGoods = new ReBaseGoods();
				newGoods.setStandardDetails(good.getStandardDetails());
				newGoods.setNoStandardRepertory(good.getNoStandardRepertory());
				//订单项规格集合
				if(isStandard){
					newGoods.editGoodsRepertory(StandardId, null, null, value, isAdd);
				}else{
					Integer repertory = good.getNoStandardRepertory();
					if(isAdd){
						//repertory += value;
						repertory = (int) CalcUtil.add(repertory, value);
					}else{
						//repertory -= value;
						repertory = (int) CalcUtil.sub(repertory, value);
					}
					newGoods.setNoStandardRepertory(repertory);
				}
				reBaseGoodsDao.updateRepertory(newGoods.getStandardDetails(),newGoods.getNoStandardRepertory(),
						changeMall.getGoodsOrder());
				good = null;
				newGoods = null;
			}
	

}
