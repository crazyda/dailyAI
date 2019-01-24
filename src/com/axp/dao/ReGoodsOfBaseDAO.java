package com.axp.dao;

import com.axp.model.ReGoodsOfBase;
import com.axp.query.PageResult;

public interface ReGoodsOfBaseDAO extends IBaseDao<ReGoodsOfBase> {

    /**
     * 为商品的列表展示界面，提供需要的商品列表，以供展示；
     *
     * @param adminUserId 用户的id值；
     * @param currentPage 当前页；
     * @param pageSize    每页展示的数据量；
     * @param goodsType   商品的类型（具体对应的类型在list方法中能找到）；
     * @param searchWord  搜索词；
     * @return
     */
    PageResult<ReGoodsOfBase> getGoodsForList(Integer adminUserId, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord);

    void updateIsValid(Integer id);
    
    Integer getSalesvolume(Integer id);

}