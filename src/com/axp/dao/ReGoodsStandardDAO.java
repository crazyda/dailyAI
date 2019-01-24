package com.axp.dao;

import java.util.List;

import com.axp.model.ReGoodsStandard;
import com.axp.query.PageResult;

public interface ReGoodsStandardDAO extends IBaseDao<ReGoodsStandard> {

    /**
     * 根据后台用户的id，寻找这个用户所有商品规格；
     *
     * @param id          后台用户id；
     * @param pageSize    每页显示的条数；
     * @param currentPage 当前页数；
     * @return
     */
    PageResult<ReGoodsStandard> getReGoodsStandardsByAdminUserId(Integer id, Integer currentPage, Integer pageSize);

    /**
     * 将parentId对应的一级规格底下的所有对应的二级规格拼接成字符串返回；
     * 1，二级规格之间使用分号隔开；
     * 2，如果字符串长度超过界限，则使用...表示省略；
     *
     * @param adminUserId
     * @param id
     * @return
     */
    String getGoodsStandardDetails(Integer adminUserId, Integer parentId);

    /**
     * 根据一级商品规格，获取所有的与之对应的二级商品规格；
     *
     * @param parent 一级商品规格；
     * @return
     */
    List<ReGoodsStandard> getChildrenGoodsStandardByParent(ReGoodsStandard parent);

    /**
     * 根据登录用户id，寻找所有的商品规格；
     * 要求：
     * 1，不区分一级二级商品规格；
     * 2，不分页；
     *
     * @param adminUserId
     * @return
     */
    List<ReGoodsStandard> getReGoodsStandardsByAdminUserId(Integer adminUserId);

    /**
     * 检查一级商品规格是否已经存在；
     *
     * @param name        一级商品规格名称；
     * @param adminUserId 用户id；
     * @return
     */
    boolean checkExist(String name, Integer adminUserId);
}
