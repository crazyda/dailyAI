package com.axp.service.weixin.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jsx3.app.Model;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.IReBackOrderDao;
import com.axp.dao.WeiXinBangWechatDAO;
import com.axp.model.ReBackOrder;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.WeiXinBangWechat;
import com.axp.service.order.ReBackOrderService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.weixin.WeixinRefundService;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
import com.weixin.bean.RefundParameter;
import com.weixin.config.WeixinConfig;
import com.weixin.util.WeixinUtil;
import com.weixin.util.WeixinWebUtil;

@Service("wxbranchesService")
public class WeixinRefundServiceImpl extends BaseServiceImpl implements WeixinRefundService{
	
	@Resource
	private IReBackOrderDao reBackOrderDao;
	
	@Autowired
	private ReBackOrderService reBackOrderService;
	//微信退款
	@Override
	public Map<String, Object> refundByWeixin(Integer backOrderId) {
		Map<String, Object> statusMap = new HashMap<String, Object>();
			ReBackOrder backOrder = reBackOrderDao.findById(backOrderId);
			if(!backOrder.getBackstate().equals(ReBackOrder.BACKSTATE_yishenhe)){
				statusMap.put("FAIL","退单状态不符合要求");
				return statusMap;
			}
			statusMap.put("SUCCESS","已成功退款！");
			
			ReGoodsorderItem item = backOrder.getOrderItem();
			ReGoodsorder goodsorder=backOrder.getOrderItem().getOrder();
			Integer fee =item.getOrder().getTotalFee();  //订单总价 单位分2280 退款订单总额 2单
			Double b_fee =backOrder.getBackmoney(); // 单个商品价值 12.8  退款总额
			Double walletSum=goodsorder.getWalletPay()==null?0d:goodsorder.getWalletPay();//订单中钱包支付的金额
			Double w_fee=0d; //钱包退款金额
					
			
			
			if(goodsorder.getIsMerge()!=null&&goodsorder.getIsMerge()!=null&&goodsorder.getIsMerge()==1&&walletSum>0){ //如果是合并支付  
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
					List<ReGoodsorder> orders = regoodsorderDAO.findByPropertyWithValid("weixinCode", backOrder.getOrderItem().getOrder().getWeixinCode());
					for (ReGoodsorder o : orders) {
						o.setWalletPay(goodsorder.getWalletPay());
						regoodsorderDAO.update(o);
					}
			}
			
		
			
			if(w_fee>0){
				reBackOrderService.addMoneyByUser( goodsorder.getUser(),w_fee, backOrder);
			}
			
			
			if(b_fee>0){  //微信退钱 
				b_fee=(b_fee*100); 
				String orderCode = item.getOrder().getOrderCode();
				String backCode = backOrder.getBackCode();
				String transaction_id = item.getOrder().getWeixinCode();
				if(StringUtils.isNotEmpty(transaction_id)){//微信订单号与平台订单号二选一
					orderCode = null;
				}
				
				//=============================
				String result=null;
				RefundParameter refund =null;
				if(goodsorder.getWebType()!=null&&goodsorder.getWebType()==1){
					refund=new RefundParameter(true);
					refund.setOut_trade_no(orderCode);
					refund.setTransaction_id(transaction_id);
					refund.setOut_refund_no(backCode);
					refund.setTotal_fee(fee);
					refund.setRefund_fee(b_fee.intValue()); 
					refund.setNonce_str(WeixinWebUtil.getNonceStr(refund));
					refund.setSign(WeixinWebUtil.getParameterSign(refund));
					result =WeixinWebUtil.sendXmlWithSSL(WeixinConfig.HTTPS_REFUND_URL, WeixinWebUtil.getXmlStr(refund));
					System.out.println("goodsorder.getWebType()==1:"+result);
				}else if(goodsorder.getAdminId()!=null&&goodsorder.getAdminId()>0){
					
					List<WeiXinBangWechat> withValidList = weiXinBangWechatDAO.findByPropertyWithValid("adminUser.id", goodsorder.getAdminId());
					if(withValidList.size()>0){
						WeiXinBangWechat wechat = withValidList.get(0);
						refund=new RefundParameter();
						refund.setMch_id(wechat.getMchId());
						refund.setOp_user_id(wechat.getMchId());
						
						if(goodsorder.getVisitor().equals("XCX")){
							refund.setAppid(wechat.getXcx_AppId());
						}else{
							refund.setAppid(wechat.getAppId());
						}
						
						String filePath=WeixinConfig.SSLPath_Prefix+wechat.getMchCert();
						refund.setOut_trade_no(orderCode);
						refund.setTransaction_id(transaction_id);
						refund.setOut_refund_no(backCode);
						refund.setTotal_fee(fee);
						refund.setRefund_fee(b_fee.intValue()); 
						refund.setNonce_str(WeixinUtil.getNonceStr(refund));
						refund.setSign(WeixinUtil.getParameterSign(refund,wechat.getMchKey()));
						result =WeixinUtil.sendXmlWithSSL(WeixinConfig.HTTPS_REFUND_URL, WeixinUtil.getXmlStr(refund),filePath,refund.getMch_id());
						System.out.println("withValidList.size()>0:"+result);
					}else{
						statusMap.put("FAIL","退款找不到adminId");
						result="退款找不到adminId";
						return statusMap;
					}
					
					
				}else{
					refund=new RefundParameter();
					refund.setOut_trade_no(orderCode);
					refund.setTransaction_id(transaction_id);
					refund.setOut_refund_no(backCode);
					refund.setTotal_fee(fee);
					refund.setRefund_fee(b_fee.intValue()); 
					refund.setNonce_str(WeixinUtil.getNonceStr(refund));
					refund.setSign(WeixinUtil.getParameterSign(refund));
					result =WeixinUtil.sendXmlWithSSL(WeixinConfig.HTTPS_REFUND_URL, WeixinUtil.getXmlStr(refund));
					System.out.println("refund=new RefundParameter();"+result);
				}
				
				//refund.setRefund_account("REFUND_SOURCE_RECHARGE_FUNDS");
				System.out.println("退款"+result);
				if(StringUtils.isNotEmpty(result)){
					Map<String, String> responseMap = WeixinUtil.xmlElements(result);
					if("SUCCESS".equals(responseMap.get("result_code"))){
						statusMap.put("SUCCESS","已成功退款！");
					}else{
						statusMap.put("FAIL","退款失败，"+responseMap.get("err_code_des"));
					}
				}else{
					statusMap.put("FAIL","未知错误！");
				}
			}
		return statusMap;
	}
}
