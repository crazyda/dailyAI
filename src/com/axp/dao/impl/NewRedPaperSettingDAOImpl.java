package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.NewRedPaperSettingDAO;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperSetting;
import com.axp.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository("newRedPaperSettingDAO")
public class NewRedPaperSettingDAOImpl extends BaseDaoImpl<NewRedPaperSetting> implements NewRedPaperSettingDAO {

	public void updateEndTime(Timestamp timestamp, int id) {
		StringBuffer sb = new StringBuffer();
		sb.append("update NewRedPaperSetting set endTime=:endTime  where id=:id");
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setParameter("endTime", timestamp);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public List<NewRedPaperSetting> findTotalSetting(StringBuffer str, List<Object> objects, Timestamp beginTime, Timestamp endTime) {
		StringBuffer sb = new StringBuffer(str);
		sb.append("  and n.beginTime <='" + endTime + "' and n.endTime >= '" + beginTime + "'  ");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		for (int i = 0; i < objects.size(); i++) {
			query.setParameter(i, objects.get(i));
		}
		query.addEntity(NewRedPaperSetting.class);
		query.list();
		return query.list();
	}

	public void updateAllNunUsed(Integer id, Integer value, String string) {
		StringBuffer sb = new StringBuffer("update new_red_paper_setting set allNumUsed = allNumUsed" + string + "? where id = ?");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, value);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	/**
	* 根据后台用户寻找该用户能审核的红包；
	* 1，如果是联盟组，则寻找商家；
	* 2，如果不是联盟组，则寻找对应的下级后台用户；
	* 
	* @param adminUser
	* @return
	*/
	public List<NewRedPaperSetting> getCheckListByAdmin(AdminUser adminUser) {
		Session session = getSessionFactory().getCurrentSession();
		try {

			Boolean flag = (adminUser.getLevel() == StringUtil.ADVERTWO);

			/*if (flag) {// 如果是联盟组；
				Query query = session.createQuery("from NewRedPaperSetting where isValid=1 and status=0 and seller.adminUser=:adminUser");
				query.setParameter("adminUser", adminUser);
				return query.list();

			} else {*/// 如果不是联盟组；
				Query query = session
						.createQuery("from NewRedPaperSetting where isValid=1 and status=0 and adminUser.parentAdminUser.id=:adminUser");
				query.setParameter("adminUser", adminUser.getId());
				return query.list();
			//}

		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@Override
	public Integer updateDaySurplus(){
		StringBuffer redpaperHql = new StringBuffer();
		redpaperHql.append("update NewRedPaperSetting ");
		redpaperHql.append("set todaySurplus = (case when (allNum-allNumColl) > maxPutout then maxPutout else (allNum-allNumColl) end ) ");
		redpaperHql.append("where now() between beginTime and endTime and  allNum > allNumColl and isValid = true");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(redpaperHql.toString());
		return query.executeUpdate();
	}

}
