/**
 * 2018-12-24
 * Administrator
 */
package com.axp.service.BranksCoupon.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.axp.model.AgentProduct;
import com.axp.model.Branks;
import com.axp.model.Product;
import com.axp.model.Seller;
import com.axp.service.BranksCoupon.BranksCouponService;
import com.axp.service.BranksCoupon.ProductService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

/**
 * @author da
 * @data 2018-12-24下午4:58:39
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl implements  ProductService{

	@Override
	public Map<String, Object> saveProduct(String id, String adminUserId,
			String brankId,String productName, String[] productImg, String score, String num,
			String remark) {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			Product product = new Product();
			AdminUser adminUser = adminUserDAO.findById(Integer.valueOf(adminUserId));
			Branks branks = null;
			if(!"".equals(id)){
				product = productDAO.findById(Integer.valueOf(id));
			}
			branks = branksDAO.findById(Integer.valueOf(brankId));
			product.setAdminUser(adminUser);
			product.setBrank(branks);
			product.setProductName(productName);
			product.setCreateTime(new Timestamp(System.currentTimeMillis()));
			product.setIsValid(true);
			product.setNum(Integer.valueOf(num));
			product.setRemark(remark);
			product.setScore(Integer.valueOf(score));
			product.setUseNum(product.getUseNum()==null?0:product.getUseNum());
			//保存商品置顶图的五张图片；
	        List<Map<String, Object>> coverList = new ArrayList<>();
	        for (int i = 0; i < productImg.length; i++) {
	            String each = productImg[i].replaceAll("\\\\", "/");
	            if (StringUtil.hasLength(each)) {
	                Map<String, Object> map = new HashMap<>();
	                map.put("imgUrl", each);
	                coverList.add(map);
	            }
	        }
	        product.setProductImg(JSONObject.toJSONString(coverList));
			productDAO.saveOrUpdate(product);
			dataMap.put("success", 1);
			dataMap.put("msg", "添加成功");
			return dataMap;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dataMap.put("success", -1);
			dataMap.put("msg", "请求错误!");
			return dataMap;
		}
	}

	
	@Override
	public Map<String, Object> delete(String id) {
		Map<String, Object> result= new HashMap<String,Object>();
		try {
			Product product = productDAO.findById(Integer.valueOf(id));
			product.setIsValid(false);
			productDAO.saveOrUpdate(product);
			QueryModel model = new QueryModel();
			model.clearQuery();
			model.combPreEquals("isValid", true);
			model.combPreEquals("product.id", product.getId(),"productId");
			List<AgentProduct> aps = dateBaseDao.findLists(AgentProduct.class, model);
			for(AgentProduct p :aps){
				p.setIsValid(false);
				agentProductDAO.saveOrUpdate(p);
			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("success",1);
			result.put("msg","删除失败");
		}
		result.put("success",1);
		result.put("msg", "删除成功");
		
		
		return result;
	}


	@Override
	public void getProductToAgent(HttpServletRequest request,AdminUser adminUser, String search,
			Branks brank,String pagestr) {
		
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
	
	
	
	
	
	
}
