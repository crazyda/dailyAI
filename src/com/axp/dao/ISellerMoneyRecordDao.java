package com.axp.dao;

import java.util.List;

import com.axp.model.Seller;
import com.axp.model.SellerMoneyRecord;
import com.axp.query.PageResult;

public interface ISellerMoneyRecordDao extends IBaseDao<SellerMoneyRecord> {

    public static final int BUY = 0x01;//购物
    public static final int BACK = 0x02;//退货
    public static final int CASH = 0x03;//提现
    public static final int UNPASS = 0x04;//提现审核未通过，返还现金
    int SHOUXUFEI = 0X05;//提现时的手续费；

    Double getDisComfirmedSum(Integer sellerId);

    void saveRecord(Double money, Double balance, Seller seller,
                    Boolean isConfirmed, Integer type, Integer relateId,
                    Class<?> relateObject);

    void activateRecord(Seller seller, Integer type, Integer relateId,
                        Class<?> relateObject);

    List<SellerMoneyRecord> getRecordsByConfirmed(Integer sellerId,
                                                  Boolean isConfirmed);

    /**
     * 获取商家金额变动情况；
     *
     * @param sellerId    商家id值；
     * @param currentPage 当前页；
     * @param pageSize    每页显示条数；
     * @return
     */
    PageResult<SellerMoneyRecord> getSellerMoneyChangeRecord(Integer sellerId, Integer currentPage, Integer pageSize);

    /**
     * 根据商家提现记录的id值和类型，寻找对应的sellerMoneyRecord表中的数据；
     *
     * @param recordId 商家提现记录id值；
     * @param type     商家金钱变动的类型；
     * @return
     */
    SellerMoneyRecord getSellerMoneyRecordBySellerWithdrawRecordIdAndType(Integer recordId, Integer type);

}
