package com.axp.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ReGoodsOfTeamMallDao;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.ReGoodsOfTeamMall;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.query.PageResult;
import com.axp.util.QueryModel;
@Repository
public class ReGoodsOfTeamMallDaoImpl extends BaseDaoImpl<ReGoodsOfTeamMall> implements ReGoodsOfTeamMallDao {

	
	@Autowired
 	private  DateBaseDAO  dateBaseDAO;
	@Autowired 
	private AdminUserDAO adminUserDAO;
	@Override
	public PageResult<ReGoodsOfTeamMall> getCheckPageresult(Integer currentPage, Integer pageSize,
			Integer adminUserId) {
		AdminUser adminUser= adminUserDAO.findById(adminUserId);
		ProvinceEnum provinceEnum = adminUser.getProvinceEnum();
		StringBuffer sb = new StringBuffer();
		List<ReGoodsOfTeamMall> teamlist = null;
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("isChecked", false);
		if (adminUser.getLevel()>=65&&adminUser.getLevel()<95) {
			if(provinceEnum.getLevel()==1){
				sb.append("( seller.provinceEnum.id="+provinceEnum.getId()).
				append(" or seller.provinceEnum.provinceEnum.id="+provinceEnum.getId()).
				append(" or seller.provinceEnum.provinceEnum.provinceEnum.id="+provinceEnum.getId()).
				append(")");
			}else if (provinceEnum.getLevel()==2) {
				queryModel.combCondition("  seller.provinceEnum.provinceEnum.id= "+provinceEnum.getId()+""
						+ "  and '区'=SUBSTRING(REVERSE(seller.provinceEnum.name),1,1) or seller.provinceEnum.id="+provinceEnum.getId());
			}else if(provinceEnum.getLevel()==3){
				sb.append("( seller.provinceEnum.id="+provinceEnum.getId()).
				append(")");
			}
			queryModel.combCondition(sb.toString());
		}
		Integer count = dateBaseDAO.findCount(ReGoodsOfTeamMall.class, queryModel);
		teamlist=	dateBaseDAO.findPageList(ReGoodsOfTeamMall.class, queryModel, (currentPage-1)*pageSize, pageSize);
        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, teamlist);
	}
	
	
	/**
	 * 查询超过拼团时间的订单
	 * @return
	 */
	public List<ReGoodsorderItem> getTeamOrderOverdue(){
		// 72 hour 1 MINUTE
		Session session = sessionFactory.getCurrentSession();
		
		String sql="select i.* from re_goodsorder_item i inner join re_goodsorder o on i.order_id=o.id"
		+ "   where date_add(o.teamPayTime, INTERVAL 72 hour)<now() and o.status=5 and o.isValid=1 and i.isValid=1 and i.isBack =10	"
		+ " and o.isHasItems=1  group by i.id";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(ReGoodsorderItem.class);
		return sqlQuery.list();
	}
	
}
