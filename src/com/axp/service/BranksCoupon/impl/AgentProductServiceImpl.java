/**
 * 2018-12-24
 * Administrator
 */
package com.axp.service.BranksCoupon.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AgentProduct;
import com.axp.model.Branks;
import com.axp.model.Product;
import com.axp.model.RedeemCode;
import com.axp.model.SJScoreMark;
import com.axp.model.Scorerecords;
import com.axp.model.Seller;
import com.axp.model.UserScoreMark;
import com.axp.model.Users;
import com.axp.service.BranksCoupon.AgentProductService;
import com.axp.service.BranksCoupon.BranksCouponService;
import com.axp.service.BranksCoupon.ProductService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

/**
 * @author da
 * @data 2018-12-24下午4:58:39
 */
@Service("agentProductService")
public class AgentProductServiceImpl extends BaseServiceImpl implements  AgentProductService{

	@Override
	public Map<String,Object> saveAgentProduct(AdminUser adminUser, String productId) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Product product = productDAO.findById(Integer.valueOf(productId));
			QueryModel  model = new QueryModel();
			model.clearQuery();
			model.combPreEquals("isValid", true);
			model.combPreEquals("product.id",product.getId(),"productId");
			model.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
			
			List<AgentProduct> aps = dateBaseDao.findLists(AgentProduct.class, model);
			if(aps != null && aps.size()>0){
				map.put("code", -1);
				map.put("msg", "该产品已经添加了!");
				return map;
			}
			AgentProduct ap = new AgentProduct();
			ap.setAdminUser(adminUser);
			ap.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ap.setIsValid(true);
			ap.setProduct(product);
			ap.setBrank(product.getBrank());
			agentProductDAO.save(ap);
			map.put("code", 1);
			map.put("msg", "添加成功,可通过代理列表查看");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", -1);
			map.put("msg", "添加失败");
		}
		return map;
	}

	
	@Override
	public Map<String, Object> del(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		AgentProduct ap = agentProductDAO.findById(Integer.valueOf(id));
		if(ap != null){
			ap.setIsValid(false);
			agentProductDAO.saveOrUpdate(ap);
			map.put("code", 1);
			map.put("msg", "删除成功");
		}else{
			map.put("code", -1);
			map.put("msg", "删除失败,请联系管理员");
		}
		return map;
	}


	@Override
	public Map<String, Object> addHhrProduct(AdminUser adminUser,
			String productId) {
		

		Map<String,Object> map = new HashMap<String,Object>();
		try {
			AgentProduct pr = agentProductDAO.findById(Integer.valueOf(productId));
			QueryModel  model = new QueryModel();
			model.clearQuery();
			model.combPreEquals("isValid", true);
			model.combPreEquals("agentProduct.id",pr.getId(),"agentProductId");
			model.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
			
			List<AgentProduct> aps = dateBaseDao.findLists(AgentProduct.class, model);
			if(aps != null && aps.size()>0){
				map.put("code", -1);
				map.put("msg", "该产品已经添加了!");
				return map;
			}
			AgentProduct ap = new AgentProduct();
			ap.setAdminUser(adminUser);
			ap.setCreateTime(new Timestamp(System.currentTimeMillis()));
			ap.setIsValid(true);
			ap.setProduct(pr.getProduct());
			ap.setBrank(pr.getProduct().getBrank());
			ap.setAgentProduct(pr);
			
			agentProductDAO.save(ap);
			map.put("code", 1);
			map.put("msg", "添加成功,可通过合伙人产品库查看");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", -1);
			map.put("msg", "添加失败");
		}
		return map;
	
		
		
		
	}

	@Override
	public void getHhrProduct(HttpServletRequest request, AdminUser adminUser,
			String search, Branks brank, String pagestr) {
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("isValid", true);
		model.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
		if(search != "" && search != null){
			model.combPreLike("product.productName", search,"productId");
			
		}
		if(brank != null){
			model.combPreEquals("product.brank.id", brank.getId(),"prodctId");
		}
		
		PageInfo pageInfo = new PageInfo();
        Utility utility = new Utility();
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        
        
        int count = dateBaseDao.findCount(AgentProduct.class, model);
        utility.setPageInfomation(pageInfo,pagestr, "50",count);	
	    List<AgentProduct> agentProducts = dateBaseDao.findPageList(AgentProduct.class, model, start, end);
	    request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	    request.setAttribute("agentProducts", agentProducts);
	    request.setAttribute("count", count);
	    request.setAttribute("search", search);
	    request.setAttribute("brank", brank);
		
		
	}


	@Override
	public Map<String, Object> addCode(AdminUser adminUser, Users user,
			String agentpr, String redeemCode) {
		Map<String,Object> status = new HashMap<String,Object>();
		AgentProduct ap = agentProductDAO.findById(Integer.valueOf(agentpr));
		AdminUser adminUserScore = adminUserDAO.findById(1735);//默认商家 后期需要改的 1768
		Timestamp time = new Timestamp(System.currentTimeMillis());
		try {
			//产品数量减少
			Product product = ap.getProduct();
			product.setUseNum(product.getUseNum()+1);
			product.setNum(product.getNum()-1);
			productDAO.saveOrUpdate(product);
			
			
			RedeemCode rc = new RedeemCode();
			rc.setAdminUser(adminUser);
			rc.setAdminUserShouFen(ap.getProduct().getAdminUser());
			rc.setIsRead(false);
			rc.setIsValid(true);
			rc.setRedeemCode(redeemCode);
			rc.setAgentProduct(ap);
			rc.setUser(user);
			rc.setCreateTime(time);
			rc.setAdminUserScore(adminUserScore);
			
			redeemCodeDAO.save(rc);	
			
			int score = ap.getProduct().getUseNum();
			int surplusScore = adminUserScore.getScore()-score;
			adminUserScore.setScore(surplusScore);
			adminUserDAO.saveOrUpdate(adminUserScore);
			
			user.setScore(user.getScore()+score);
			userDao.saveOrUpdate(user);
			
			QueryModel model = new QueryModel();
			model.clearQuery();
			model.combPreEquals("isValid", true);
			model.combPreEquals("adminUser.id", adminUser.getId(),"adminUserSellerId");
			model.combIsNull("users.id");
			List<SJScoreMark> sjsms = dateBaseDao.findPageList(SJScoreMark.class, model, 0, Integer.valueOf(score));
			
			Timestamp validityTime =  new Timestamp(DateUtil.addDay2Date(30, new Date()).getTime());
			List<UserScoreMark> usms = new ArrayList<UserScoreMark>();
			for(SJScoreMark sjsm : sjsms){
				sjsm.setUsers(user);
				sjsm.setRefreshTime(time);
				sjScoreMarkDAO.saveOrUpdate(sjsm);
				
				UserScoreMark usm = new UserScoreMark();
				usm.setRefreshTime(time);
				usm.setCreateTime(time);
				usm.setIsValid(true);
				usm.setSjScoreMark(sjsm);
				usm.setUsers(user);
				usm.setValidityTime(validityTime);
				usm.setAdminUser(adminUser);
				usms.add(usm);
			}
			userScoreMarkDao.saveList(usms);
			
			
			AdminUserScoreRecord scoreRecord2 = new AdminUserScoreRecord();
			scoreRecord2.setAdminUser(adminUserScore);
			scoreRecord2.setBeforeScore(surplusScore+score);
			scoreRecord2.setAfterScore(adminUserScore.getScore());
			scoreRecord2.setSurplusScore(surplusScore);
			scoreRecord2.setCreateTime(time);
			scoreRecord2.setScore(-score);
			scoreRecord2.setIsValid(true);
			scoreRecord2.setType(14);
			adminUserScoreRecordDAO.save(scoreRecord2);

			Scorerecords sc = new Scorerecords();
			sc.setCreatetime(time);
			sc.setUsers(user);
			sc.setBeforeScore(user.getScore()-score);
			sc.setAfterScore(user.getScore());
			sc.setIsvalid(true);
			sc.setRemark("提供兑换码:"+redeemCode+",得到"+score+"积分");
			sc.setScore(score);
			sc.setScoretype("提供兑换码:"+redeemCode+",得到"+score+"积分");
			sc.setType(17);
			sc.setAdminuserId(adminUserScore.getId());
			scoreRecordsDao.save(sc);
			status.put("code", 1);
			status.put("msg", "提交成功,等待审核通过");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status.put("code", -1);
			status.put("msg", "请求有误,请刷新重新请求");
		}
		
		return status;
	}
	
	
	
	
	
	
	
}
