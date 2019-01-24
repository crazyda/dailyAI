package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.CashshopTimesDAO;
import com.axp.model.CashshopTimes;
import com.axp.util.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class CashshopTimesDAOImpl extends BaseDaoImpl<CashshopTimes> implements
        CashshopTimesDAO {

    @Override
    public List<CashshopTimes> findTimesOfHQ() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "from CashshopTimes as model where model.user.level=:level and model.isValid = :isValid";
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("level", StringUtil.ADMIN);
        queryObject.setParameter("isValid", true);
        return queryObject.list();
    }

    @Override
    public List<CashshopTimes> searchCashshopTimesByIds(List<Integer> timesList) {

        //拼接查询条件；
        StringBuilder sb = new StringBuilder(100).append("(");
        for (int i = 0; i < timesList.size(); i++) {
            sb.append(timesList.get(i));
            if (i != timesList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");

        Session session = sessionFactory.getCurrentSession();
        String queryString = "from CashshopTimes where isValid =1 and id in" + sb.toString();
        Query queryObject = session.createQuery(queryString);
        return queryObject.list();
    }


}
