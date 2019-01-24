package com.axp.service.alipay.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.alipay.bean.AlipayRefund;
import com.alipay.util.AlipayRefundUtil;
import com.axp.dao.IReBackOrderDao;
import com.axp.model.ReBackOrder;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.service.alipay.AlipayRefundService;
import com.axp.service.order.ReBackOrderService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.weixin.bean.RefundParameter;
import com.weixin.config.WeixinConfig;
import com.weixin.util.WeixinUtil;

@Service("alipaybranchesService")
public class AlipayRefundServiceImpl extends BaseServiceImpl implements AlipayRefundService{
	
	@Resource
	private IReBackOrderDao reBackOrderDao;
	
	@Autowired
	private ReBackOrderService reBackOrderService;
	//支付宝
	@Override
	public Map<String, Object> refund(Integer backOrderId) {
		Map<String, Object> statusMap = new HashMap<String, Object>();
		try{
			
			ReBackOrder backOrder = reBackOrderDao.findById(backOrderId);
			if(!backOrder.getBackstate().equals(ReBackOrder.BACKSTATE_yishenhe)){
				statusMap.put("FAIL","退单状态不符合要求");
				return statusMap;
			}
			
			ReGoodsorderItem item = backOrder.getOrderItem();
			ReGoodsorder goodsorder=backOrder.getOrderItem().getOrder();
			Integer fee =item.getOrder().getTotalFee();  //订单总价 单位分2280 退款订单总额 2单
			Double b_fee =backOrder.getBackmoney(); // 单个商品价值 12.8  退款总额
			Double walletSum=goodsorder.getWalletPay()==null?0d:goodsorder.getWalletPay();//订单中钱包支付的金额
			Double w_fee=0d; //钱包退款金额
			ReGoodsorder order = backOrder.getOrderItem().getOrder();
			
			
			
			if(order.getIsMerge()!=null&&order.getIsMerge()==1&&walletSum>0){ //如果是合并支付  
					if(CalcUtil.sub(walletSum,b_fee)<0){ //如果钱包支付金额小于商品单价 
						b_fee=CalcUtil.sub(b_fee, walletSum);
						goodsorder.setWalletPay(0d);
						w_fee=walletSum;
					}else{
						w_fee=b_fee; 
						goodsorder.setWalletPay(CalcUtil.sub(walletSum, b_fee));
						b_fee=0d;
					}
					regoodsorderDAO.update(goodsorder);
					//修改钱包支付金额
					List<ReGoodsorder> orders = regoodsorderDAO.findByPropertyWithValid("weixinCode",order.getWeixinCode());
					for (ReGoodsorder o : orders) {
						o.setWalletPay(goodsorder.getWalletPay());
						regoodsorderDAO.update(o);
					}
			}	
			
			
			//退到钱包的金额
			if(w_fee>0){
				reBackOrderService.addMoneyByUser( goodsorder.getUser(),w_fee, backOrder);
			}
			
			if(b_fee>0){  //微信退钱
				b_fee=(b_fee*100);
			
			String orderCode = item.getOrder().getOrderCode();
			String backCode = backOrder.getBackCode();
			String trade_no = item.getOrder().getAlipayCode();
			if(StringUtils.isNotEmpty(trade_no)){//支付宝订单号与平台订单号二选一
				orderCode = null;
			}
			
				String result = AlipayRefundUtil.refund(orderCode, trade_no, backCode, fee, b_fee, "");
				
				if(StringUtils.isNotEmpty(result)){
					if(result.equals("SUCCESS")){
						statusMap.put("SUCCESS","已成功退款！");
					}else{
						statusMap.put("FAIL","退款失败，"+result);
						if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
						}
						return statusMap;
					}
				}
				
				statusMap.put("SUCCESS","已成功退款,支付宝金额请手动转账！");
			
			}
		}catch(Exception e){
			e.printStackTrace();
			statusMap.put("FAIL","未知错误！");
			if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
			}  
		}
		return statusMap;
	}
}
