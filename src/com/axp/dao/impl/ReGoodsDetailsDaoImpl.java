package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ReGoodsDetailsDAO;
import com.axp.model.ReGoodsDetails;
import com.axp.model.ReGoodsOfBase;

@Repository
public class ReGoodsDetailsDaoImpl extends BaseDaoImpl<ReGoodsDetails> implements ReGoodsDetailsDAO {

    @Override
    public ReGoodsDetails getByBaseGoods(ReGoodsOfBase baseGoods) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ReGoodsDetails where isValid=1 and goods=:goods");
        List<ReGoodsDetails> list = query.setParameter("goods", baseGoods).list();
        if (list == null || list.size() != 1) {
            throw new Exception("找不到baseGoodsId为" + baseGoods.getId() + "的ReGoodsDetails对象；");
        }
        return list.get(0);
    }
}
