/**
 * 2018-12-24
 * Administrator
 */
package com.axp.service.BranksCoupon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.axp.dao.BranksDAO;
import com.axp.model.AdminUser;
import com.axp.model.AgentProduct;
import com.axp.model.Branks;
import com.axp.model.RedeemCode;
import com.axp.service.BranksCoupon.RedeemCodeService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;


/**
 * @author da
 * @data 2018-12-24下午4:58:39
 */
@Service("redeemCodeService")
public class RedeemCodeServiceImpl extends BaseServiceImpl implements  RedeemCodeService{

	@Override
	public void getRedeemCodeList(HttpServletRequest request,AdminUser adminUser, String branksId,
			String search,String pagestr) {
		Branks brank = null ;
		if(branksId != null && branksId != ""){
			
			 brank = branksDAO.findById(Integer.valueOf(branksId));
		}
		
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("isValid", true);
		if(adminUser.getLevel() == 55){
			model.combPreEquals("adminUserShouFen.id", adminUser.getId(),"adminUserShouFen");
		}else{
			
			model.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
		}
		if(search != "" && search != null){
			model.combPreLike("agentProduct.product.productName", search,"agentProductId");
			
		}
		if(brank != null){
			model.combPreEquals("agentProduct.product.brank.id", brank.getId(),"agentProductId");
		}
		
		PageInfo pageInfo = new PageInfo();
        Utility utility = new Utility();
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        
        
        int count = dateBaseDao.findCount(RedeemCode.class, model);
        utility.setPageInfomation(pageInfo,pagestr, "50",count);	
	    List<RedeemCode> RedeemCodes = dateBaseDao.findPageList(RedeemCode.class, model, start, end);
	    request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	    request.setAttribute("RedeemCodes", RedeemCodes);
	    request.setAttribute("count", count);
	    request.setAttribute("search", search);
	    request.setAttribute("brank", brank);
		
		
		
	}

	@Override
	public Map<String, Object> getRedeemCode(Integer id,String isVerify) {
		Map<String ,Object> map = new HashMap<String,Object>();
		RedeemCode rc = redeemCodeDAO.findById(id);
		if(rc != null){
			rc.setIsRead(true);
			if("1".equals(isVerify)){
				rc.setIsVerify(true);
			}else if("2".equals(isVerify)){
				rc.setIsVerify(false);
				
			}
			redeemCodeDAO.saveOrUpdate(rc);
			map.put("code", 1);
			map.put("msg", "兑换码:"+rc.getRedeemCode());
			return map;
		}else{
			
		map.put("code", -1);
		map.put("msg", "请求失败!");
		}
		
		return map;
	}
	
	
	
	
	
	
	
}
