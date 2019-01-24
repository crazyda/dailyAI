package com.axp.service.yilian.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.IReBackOrderDao;
import com.axp.model.ReBackOrder;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.service.order.ReBackOrderService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.yilian.YiLianRefundService;
import com.axp.util.CalcUtil;
import com.yilian.Constants;
import com.yilian.payeco.client.TransactionClient;
import com.yilian.payeco.tools.Tools;
import com.yilian.payeco.tools.Xml;
@Service("yiLianRefundService")
public class YiLianRefundServiceImpl extends BaseServiceImpl implements YiLianRefundService{

	
	@Resource
	private IReBackOrderDao reBackOrderDao;
	
	@Autowired
	private ReBackOrderService reBackOrderService;
	
	@Override
	public Map<String, Object> refundByYiLian(Integer backOrderId) {
		Map<String, Object> statusMap = new HashMap<String, Object>();
		ReBackOrder backOrder = reBackOrderDao.findById(backOrderId);
		if(!backOrder.getBackstate().equals(ReBackOrder.BACKSTATE_yishenhe)){
			statusMap.put("FAIL","退单状态不符合要求");
			return statusMap;
		}
		statusMap.put("SUCCESS","已成功退款！");
		
		ReGoodsorderItem item = backOrder.getOrderItem();
		ReGoodsorder goodsorder=backOrder.getOrderItem().getOrder();
		Integer fee =item.getOrder().getTotalFee();  //订单总价 单位分1280 退款订单总额
		Double b_fee =backOrder.getBackmoney(); // 单个商品价值 12.8  退款总额
		Double walletSum=goodsorder.getWalletPay()==null?0d:goodsorder.getWalletPay();//订单中钱包支付的金额
		Double w_fee=0d; //钱包退款金额
				
		
		if(goodsorder.getIsMerge()!=null&&goodsorder.getIsMerge()==1&&walletSum>0){ //如果是合并支付  
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
		
		if(b_fee>0){  //易联
				// 设置参数
				String amount = b_fee.toString();
				String merchantId = Constants.MERCHANT_ID;
				String merchOrderId =  backOrder.getOrderItem().getOrder().getId().toString();  //需要填写已经存在的商户订单号
				String merchRefundId = "" + System.currentTimeMillis(); // 退款申请号
				String tradeTime = Tools.getSysTime();

				// 调用冲正接口
				System.out.println("-------订单退款申请接口测试-------------------------");
				try {
					Xml retXml = new Xml();
					//接口参数请参考TransactionClient的参数说明
					String ret = TransactionClient.OrderRefundReq(merchantId, merchOrderId, merchRefundId, amount, tradeTime, 
							Constants.MERCHANT_RSA_PRIVATE_KEY, Constants.PAYECO_RSA_PUBLIC_KEY, Constants.PAYECO_URL, retXml);
					if(!"0000".equals(ret)){
						System.out.println("订单退款申请接口测试失败！：retCode="+ret+"; msg="+retXml.getRetMsg());
						statusMap.put("FAIL","订单退款申请接口失败！");
						if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
						}
						//return statusMap;
					}else{
						statusMap.put("SUCCESS","已成功退款！");
					}
				} catch (Exception e) {
					System.out.println("订单退款申请接口异常！：");
					e.printStackTrace();
					statusMap.put("FAIL","订单退款申请接口异常！");
					if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
					}
					return statusMap;
				}
				System.out.println("订单退款申请接口测试----ok");
				System.out.println("------------------------------------------------");
		}
		return statusMap;
	}
}