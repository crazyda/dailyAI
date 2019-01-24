package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ISellerMoneyRecordDao;
import com.axp.model.Seller;
import com.axp.model.SellerMoneyRecord;
import com.axp.query.PageResult;
import com.axp.util.CalcUtil;

@Repository
public class SellerMoneyRecordDaoImpl extends BaseDaoImpl<SellerMoneyRecord> implements ISellerMoneyRecordDao {

    private static Map<Integer, String> descripConfig;
    private static Map<Integer, String> titleDescripConfig; //标题

    @PostConstruct
    private void init() {
        if (descripConfig == null) {
            descripConfig = new HashMap<Integer, String>();
            descripConfig.put(BACK, "退货扣除现金");
            descripConfig.put(BUY, "商品售出获得现金");
            descripConfig.put(CASH, "提现扣除收益");
            descripConfig.put(UNPASS, "提现审核未通过，返还提现");
            descripConfig.put(SHOUXUFEI, "提现收取手续费");
        }
        if (titleDescripConfig == null) {
            titleDescripConfig = new HashMap<Integer, String>();
            titleDescripConfig.put(BUY, "售出");
            titleDescripConfig.put(BACK, "退货");
            titleDescripConfig.put(CASH, "提现");
            titleDescripConfig.put(UNPASS, "提现返还");
            titleDescripConfig.put(SHOUXUFEI, "提现手续费");
        }
    }

    /**
     * 获得未确认收益总额
     */
    @Override
    public Double getDisComfirmedSum(Integer sellerId) {
        String queryString = "select sum(money) from SellerMoneyRecord where seller.id = :sellerId and isConfirmed = :isConfirmed";
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("sellerId", sellerId);
        queryObject.setParameter("isConfirmed", false);
        return (Double) queryObject.uniqueResult();
    }

    @Override
    public List<SellerMoneyRecord> getRecordsByConfirmed(Integer sellerId, Boolean isConfirmed) {
        String queryString = "from SellerMoneyRecord where seller.id = :sellerId and isConfirmed = :isConfirmed";
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("sellerId", sellerId);
        queryObject.setParameter("isConfirmed", isConfirmed);
        return queryObject.list();
    }

    @Override
    public PageResult<SellerMoneyRecord> getSellerMoneyChangeRecord(Integer sellerId, Integer currentPage, Integer pageSize) {
        Session session = sessionFactory.getCurrentSession();

        //先获取查询条件；
        String queryCount = "select count(r) from SellerMoneyRecord r where r.isValid=1 and r.seller.id=" + sellerId;
        String queryList = "from SellerMoneyRecord r where r.isValid=1 and r.seller.id=" + sellerId + " order by r.id desc";

        //先查询总条数；
        Long count = (Long) session.createQuery(queryCount).uniqueResult();

        //计算当前页的数据；
        List<SellerMoneyRecord> list = session.createQuery(queryList)
                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
                .list();

        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
    }

    @Override
    public SellerMoneyRecord getSellerMoneyRecordBySellerWithdrawRecordIdAndType(Integer recordId, Integer type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SellerMoneyRecord r where r.isValid=1 and r.relateId=:relateId and r.type=:type")
                .setParameter("relateId", recordId).setParameter("type", type);
        List<SellerMoneyRecord> list = query.list();

        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }


    /**
     * 保存商家收益与支出明细
     *
     * @param money        额度
     * @param balance      最终余额
     * @param seller       商家
     * @param isConfirmed  是否激活记录
     * @param type         明细类型
     * @param relateId     关联对象id
     * @param relateObject 关联对象
     */
    @Override
    public void saveRecord(Double money, Double balance, Seller seller, Boolean isConfirmed,
                           Integer type, Integer relateId, Class<?> relateObject) {
        String relateName = null;
        if (relateObject != null) {
            relateName = relateObject.getName();
        }

        SellerMoneyRecord record = new SellerMoneyRecord();
        record.setCreateTime(new Timestamp(System.currentTimeMillis()));
        record.setIsConfirmed(isConfirmed);
        record.setIsValid(true);
        record.setMoney(money);
        record.setBalance(balance);
        record.setRelateId(relateId);
        record.setRelateObject(relateName);
        record.setRemark(descripConfig.get(type));
        record.setSeller(seller);
        record.setTitle(titleDescripConfig.get(type));
        record.setType(type);
        record.setUsers(seller.getUsers());
        save(record);
    }

    /**
     * 激活记录并加钱
     */
    @Override
    public void activateRecord(Seller seller, Integer type, Integer relateId, Class<?> relateObject) {
        String queryString = "select money,id from SellerMoneyRecord " +
                "where seller.id = :sellerId and relateId = :relateId " +
                "and relateObject = :relateObject and isConfirmed = false";
        Session session = sessionFactory.getCurrentSession();
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("sellerId", seller.getId());
        queryObject.setParameter("relateId", relateId);
        queryObject.setParameter("relateObject", relateObject.getName());
        List<Object[]> list = queryObject.list();
        Double totalMoney = 0d;
        String ids = "-1";
        for (Object[] data : list) {
            totalMoney = CalcUtil.add(totalMoney, Double.parseDouble(data[0].toString()));
            ids = ids + "," + data[1];
        }
        if (totalMoney > 0) {
            String recordString = "update SellerMoneyRecord set isConfirmed = true " +
                    "where seller.id = :sellerId and relateId = :relateId " +
                    "and relateObject = :relateObject and isConfirmed = false";
            Query queryRecord = session.createQuery(recordString);
            queryRecord.setParameter("sellerId", seller.getId());
            queryRecord.setParameter("relateId", relateId);
            queryRecord.setParameter("relateObject", relateObject.getName());
            int result = queryRecord.executeUpdate();

            if (result > 0) {
                String sellerString = "update Seller set money = money + :money where id = :sellerId";
                Query querySeller = session.createQuery(sellerString);
                querySeller.setParameter("sellerId", seller.getId());
                querySeller.setParameter("money", totalMoney);
                querySeller.executeUpdate();
            }
        }
    }

}
