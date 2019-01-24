package com.axp.service.system.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.model.AdminUser;
import com.axp.model.Seller;
import com.axp.service.system.SelectTagService;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Service("selectTagService")
public class SelectTagServiceImpl extends BaseServiceImpl implements SelectTagService {
	/**
	 * 初始化自定义级联选择列表
	 * au 操作用户
	 * objectAminUser 目标用户
	 */
	@Resource
	private AdminUserDAO adminUserDAO;

	@Override
	public void initSelectTag(HttpServletRequest request, AdminUser au, AdminUser objectAminUser) {
		// 运营中心查询条件
		StringBuffer con_s = new StringBuffer(" isvalid = true ");
		// 城市代理查询条件
		StringBuffer con_p = new StringBuffer(" isvalid = true ");
		// 联盟组查询条件
		StringBuffer con_a = new StringBuffer(" isvalid = true ");
		// 商家查询条件
		StringBuffer con_seller = new StringBuffer(" isvalid = true ");
		// 供应商查询条件
		StringBuffer con_pro = new StringBuffer(" isvalid = true ");
		if (au.getLevel() >= StringUtil.ADMIN) {
			con_s.append(" and  level > 80 and level <= 90");
			con_pro.append(" and  level = 60 ");
		} else if (au.getLevel() >= StringUtil.ADVERCENTER && au.getLevel() <= StringUtil.GIFTCENTER) {
			con_s.append(" and  level > 80 and level <= 90").append(" and id = ").append(au.getId());
			con_pro.append(" and  level = 60 ").append(" and parentId = ").append(au.getId());
		} else if (au.getLevel() >= StringUtil.ADVERONE && au.getLevel() <= StringUtil.GIFTONE) {
			con_s.append(" and id = ").append(au.getParentId()).append(" and level > 80 and level <= 90");
			con_p.append(" and id = ").append(au.getId()).append(" and level >70 and level <=80");
		} else {
			con_s.append(" and id = ").append(au.getParentAdminUser().getParentId()).append(" and level >80 and level <= 90");
			con_p.append(" and id = ").append(au.getParentId()).append(" and level >70 and level <=80");
			con_a.append(" and id = ").append(au.getId()).append(" and level >60 and level <=70");
		}
		if (objectAminUser != null) {
			if (objectAminUser.getLevel() < 75) {
				//商家上级为联盟组
				request.setAttribute("alliance_id", objectAminUser.getId());
				AdminUser proxyUser = adminUserDAO.findById(objectAminUser.getParentId());
				request.setAttribute("proxy_id", proxyUser.getId());
				AdminUser centerUser = adminUserDAO.findById(proxyUser.getParentId());
				request.setAttribute("center_id", centerUser.getId());
			}
			if (objectAminUser.getLevel() > 70 && objectAminUser.getLevel() < 85) {
				//商家上级为一级代理
				request.setAttribute("proxy_id", objectAminUser.getId());
				AdminUser centerUser = adminUserDAO.findById(objectAminUser.getParentId());
				request.setAttribute("center_id", centerUser.getId());
			}
			if (objectAminUser.getLevel() > 80 && objectAminUser.getLevel() < 95) {
				request.setAttribute("center_id", objectAminUser.getId());
			}
		}

		request.setAttribute("condition", con_s.toString());
		request.setAttribute("condition_proxy", con_p.toString());
		request.setAttribute("condition_alliance", con_a.toString());
		request.setAttribute("condition_seller", con_seller.toString());
		request.setAttribute("condition_provider", con_pro.toString());

	}

	@Override
	public void initSelectTagBySeller(HttpServletRequest request, AdminUser au, Seller objectSeller) {
		// 运营中心查询条件
		StringBuffer con_s = new StringBuffer(" isvalid = true ");
		// 城市代理查询条件
		StringBuffer con_p = new StringBuffer(" isvalid = true ");
		// 联盟组查询条件
		StringBuffer con_a = new StringBuffer(" isvalid = true ");
		// 商家查询条件
		StringBuffer con_seller = new StringBuffer(" isvalid = true ");
		// 供应商查询条件
		StringBuffer con_pro = new StringBuffer(" isvalid = true ");
		if (au.getLevel() >= StringUtil.ADMIN) {
			con_s.append(" and  level > 80 and level <= 90");
			con_pro.append(" and  level = 60 ");
		} else if (au.getLevel() >= StringUtil.ADVERCENTER && au.getLevel() <= StringUtil.GIFTCENTER) {
			con_s.append(" and  level > 80 and level <= 90").append(" and id = ").append(au.getId());
			con_pro.append(" and  level = 60 ").append(" and parentId = ").append(au.getId());
		} else if (au.getLevel() >= StringUtil.ADVERONE && au.getLevel() <= StringUtil.GIFTONE) {
			con_s.append(" and id = ").append(au.getParentId()).append(" and level > 80 and level <= 90");
			con_p.append(" and id = ").append(au.getId()).append(" and level >70 and level <=80");
		} else {
			con_s.append(" and id = ").append(au.getParentAdminUser().getParentId()).append(" and level >80 and level <= 90");
			con_p.append(" and id = ").append(au.getParentId()).append(" and level >70 and level <=80");
			con_a.append(" and id = ").append(au.getId()).append(" and level >60 and level <=70");
		}

		if (objectSeller != null) {

			AdminUser objectAminUser = objectSeller.getAdminUser();
			request.setAttribute("seller_id", objectSeller.getId());
			if (objectAminUser.getLevel() < 75) {
				//商家上级为联盟组
				request.setAttribute("alliance_id", objectAminUser.getId());
				AdminUser proxyUser = adminUserDAO.findById(objectAminUser.getParentId());
				request.setAttribute("proxy_id", proxyUser.getId());
				AdminUser centerUser = adminUserDAO.findById(proxyUser.getParentId());
				request.setAttribute("center_id", centerUser.getId());
			}
			if (objectAminUser.getLevel() > 70 && objectAminUser.getLevel() < 85) {
				//商家上级为一级代理
				request.setAttribute("proxy_id", objectAminUser.getId());
				AdminUser centerUser = adminUserDAO.findById(objectAminUser.getParentId());
				request.setAttribute("center_id", centerUser.getId());
			}
			if (objectAminUser.getLevel() > 80 && objectAminUser.getLevel() < 95) {
				request.setAttribute("center_id", objectAminUser.getId());
			}
		}

		request.setAttribute("condition", con_s.toString());
		request.setAttribute("condition_proxy", con_p.toString());
		request.setAttribute("condition_alliance", con_a.toString());
		request.setAttribute("condition_seller", con_seller.toString());
		request.setAttribute("condition_provider", con_pro.toString());
	}

	
	  /**
     * 根据当前登录用户查询所有下级
     * @param adminUser
     * @return 所有下级集合 key为SellerId 返回Seller对象  预想是这样的  最后返回了 list 
     */
    public List<AdminUser> findNextLevel(AdminUser adminUser){
    	QueryModel queryModel=new QueryModel();
    	queryModel.combPreEquals("isvalid", true);
    	StringBuilder sb=new StringBuilder();
    	if(adminUser.getLevel()==85){
    		sb.append("( parentAdminUser.id=").append(adminUser.getId()).
    			append(" and parentAdminUser.isvalid=1 and level=75)").
    		   append("  or (parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
    		   append("  and parentAdminUser.isvalid=1 and level=65)").
    		   append("  or (parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
    		   append("  and parentAdminUser.parentAdminUser.isvalid=1 and level=60)").
    		   append("  or (parentAdminUser.parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
    		   append("  and parentAdminUser.parentAdminUser.parentAdminUser.isvalid=1 and level=60)");
    		queryModel.combCondition(sb.toString());
    	}
    	else if(adminUser.getLevel()==75){
    		sb.append("( parentAdminUser.id=").append(adminUser.getId()).
    		append("    parentAdminUser.isvalid=1 and level=65 )").
		    append("  or (parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
		    append("  and parentAdminUser.parentAdminUser.isvalid=1 and level=60)");
    		queryModel.combCondition(sb.toString());
    	}else if(adminUser.getLevel()==65){
    		sb.append("( parentAdminUser.id=").append(adminUser.getId()).
    		append("  parentAdminUser.isvalid=1 and level=60 )");
    	}
    	
    	List<AdminUser> list = dateBaseDao.findLists(AdminUser.class, queryModel);
    	
    	return list;
    }
	
    /**
     * 查找下级查询字符串  用于queryModel.combCondition(sb.toString());
     * 目标表为Seller  如果想从adminUser表开始 那么只需要去掉 seller.adminuser即可
     * @param adminUser
     * @return String 拼接字符串
     */
    public String findNextLevelStr(AdminUser adminUser){
		StringBuilder sb=new StringBuilder();
		
	    	sb.append(" seller.adminUser.isvalid=true");
	    	if(adminUser.getLevel()==85){
	    		sb.append(" and ( seller.adminUser.parentAdminUser.id=").append(adminUser.getId()).
	    			append(" and  seller.adminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=75)").
	    		   append("  or (seller.adminUser.parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
	    		   append("  and seller.adminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=65)").
	    		   append("  or (seller.adminUser.parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
	    		   append("  and seller.adminUser.parentAdminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=60)").
	    		   append("  or (seller.adminUser.parentAdminUser.parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
	    		   append("  and seller.adminUser.parentAdminUser.parentAdminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=60)");
	    	}
	    	else if(adminUser.getLevel()==75){
	    		sb.append(" and ( seller.adminUser.parentAdminUser.id=").append(adminUser.getId()).
	    		append("    seller.adminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=65 )").
			    append("  or (seller.adminUser.parentAdminUser.parentAdminUser.id=").append(adminUser.getId()).
			    append("  and seller.adminUser.parentAdminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=60)");
	    	}else if(adminUser.getLevel()==65){
	    		sb.append(" and ( seller.adminUser.parentAdminUser.id=").append(adminUser.getId()).
	    		append("  seller.adminUser.parentAdminUser.isvalid=1 and seller.adminUser.level=60 )");
	    	}
		
    	return sb.toString();
	}
    
}
