package com.axp.dao;

import com.axp.model.ReGoodsDetails;
import com.axp.model.ReGoodsOfBase;

public interface ReGoodsDetailsDAO extends IBaseDao<ReGoodsDetails>{

    /**
     * 根据基本商品对象，获取商品详情对象
     * @param baseGoods
     * @return
     */
    ReGoodsDetails getByBaseGoods(ReGoodsOfBase baseGoods) throws Exception;
}
