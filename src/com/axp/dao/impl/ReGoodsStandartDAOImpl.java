package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ReGoodsStandardDAO;
import com.axp.model.ReGoodsStandard;
import com.axp.query.PageResult;

@Repository
@SuppressWarnings("unchecked")
public class ReGoodsStandartDAOImpl extends BaseDaoImpl<ReGoodsStandard> implements ReGoodsStandardDAO {

    @Override
    public PageResult<ReGoodsStandard> getReGoodsStandardsByAdminUserId(Integer id, Integer currentPage, Integer pageSize) {
        Session session = sessionFactory.getCurrentSession();

        //先计算总条数；
        Query query = session.createQuery("select count(r) from ReGoodsStandard r where r.adminUserId=:id and isValid=1 and parentStandardId is null");
        query.setParameter("id", id);
        Long count = (Long) query.uniqueResult();

        //再计算当前页的数据；
        Query query2 = session.createQuery("from ReGoodsStandard where adminUserId=:id and isValid=1 and parentStandardId is null order by createTime desc");
        query2.setParameter("id", id).setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
        List<ReGoodsStandard> list = query2.list();

        //对ReGoodsStandard中的goodsStandardDetails冗余字段进行赋值操作；
        for (ReGoodsStandard each : list) {
            each.setGoodsStandardDetails(getGoodsStandardDetails(each.getAdminUserId(), each.getId()));
        }

        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
    }

    @Override
    public String getGoodsStandardDetails(Integer adminUserId, Integer parentId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ReGoodsStandard where adminUserId=:adminUserId and isValid=1 and parentStandardId=:parentId order by id asc");
        List<ReGoodsStandard> list = query.setParameter("adminUserId", adminUserId).setParameter("parentId", parentId).list();
        if (list == null || list.size() == 0) {
            return null;
        }

        //拼接二级分类；
        StringBuilder sb = new StringBuilder(60);
        for (int i = 0; i < list.size(); i++) {
            ReGoodsStandard each = list.get(i);
            sb.append(each.getName()).append(";");
            if (sb.length() >= 60) {
                sb = new StringBuilder(sb.substring(0, 58)).append("...");
                break;
            }
        }
        return sb.toString();
    }

    @Override
    public List<ReGoodsStandard> getChildrenGoodsStandardByParent(ReGoodsStandard parent) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ReGoodsStandard where isValid=1 and parentStandardId=:parentId");
        return query.setParameter("parentId", parent.getId()).list();
    }

    @Override
    public List<ReGoodsStandard> getReGoodsStandardsByAdminUserId(Integer adminUserId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ReGoodsStandard where isValid=1 and adminUserId=:adminUserId");
        return query.setParameter("adminUserId", adminUserId).list();
    }

    @Override
    public boolean checkExist(String name, Integer adminUserId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ReGoodsStandard where isValid=1 and name=:name and adminUserId=:adminUserId and parentStandardId is null");
        List<ReGoodsStandard> list = query.setParameter("name", name).setParameter("adminUserId", adminUserId).list();

        if (list != null && list.size() >= 1) {
            return true;
        }
        return false;
    }

}