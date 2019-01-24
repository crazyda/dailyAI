package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ReGoodsOfBaseDAO;
import com.axp.model.ReGoodsOfBase;
import com.axp.query.PageResult;
import com.axp.util.StringUtil;

@Repository
@SuppressWarnings("unchecked")
public class ReGoodsOfBaseDAOImpl extends BaseDaoImpl<ReGoodsOfBase> implements ReGoodsOfBaseDAO {

    @Override
    public PageResult<ReGoodsOfBase> getGoodsForList(Integer sellerId, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord) {
        Session session = sessionFactory.getCurrentSession();

        //先获取查询条件；
        String queryCount = "";
        String queryList = "";

        if (StringUtil.hasLength(searchWord)) {
            searchWord = " and name like '%" + searchWord + "%' ";
        } else {
            searchWord = "";
        }

        if (goodsType == 0) {
        	//r.seller.id=:id and 
            queryCount = "select count(r) from ReGoodsOfBase r where isValid=1" + searchWord;
            queryList = "from ReGoodsOfBase r where  isValid=1 " + searchWord + " order by id desc";
        } else {
            String condition = "";
            for (int i = 0; i < 6; i++) {
                if (i == goodsType - 1) {
                    condition += 1;
                } else {
                    condition += "_";
                }
            }
            condition = "'" + condition + "'";
            queryCount = "select count(r) from ReGoodsOfBase r where isValid=1 and launchMall like " + condition + searchWord;
            queryList = "from ReGoodsOfBase r where isValid=1 and launchMall like " + condition + searchWord
                    + " order by id desc";
        }

        //先查询总条数；
        Query query1 = session.createQuery(queryCount);
        Long count = (Long) query1.uniqueResult();

        //计算当前页的数据；
        Query query2 = session.createQuery(queryList).setFirstResult((currentPage - 1) * pageSize)
                .setMaxResults(pageSize);
        List<ReGoodsOfBase> list = query2.list();

        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
    }

    @Override
    public Integer getSalesvolume(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        
        Query createQuery = session.createSQLQuery("select salesvolume from re_goodsofbase where id="+id);
        Object obj =createQuery.uniqueResult();
        return obj==null?0:Integer.parseInt(obj.toString());
        
    }
    
    
    @Override
    public void updateIsValid(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query createQuery = session.createQuery("update ReGoodsOfBase set isvalid = " + false + " where id = " + id);
        createQuery.executeUpdate();
    }
}
