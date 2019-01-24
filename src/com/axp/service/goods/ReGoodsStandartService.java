package com.axp.service.goods;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.axp.model.ReGoodsStandard;
import com.axp.query.PageResult;

public interface ReGoodsStandartService {

    /**
     * 根据后台用户的id，寻找这个用户所有商品规格,并完成分页；
     *
     * @param id          后台用户id值；
     * @param pageSize    每页显示条数；
     * @param currentPage 当前页数；
     * @return
     */
    PageResult<ReGoodsStandard> getReGoodsStandardsByAdminUserId(Integer id, Integer currentPage, Integer pageSize);

    /**
     * 保存商品规格；
     *
     * @param name        一级商品规格名；
     * @param details     二级商品规格名们；
     * @param adminUserId 用户id值；
     * @return
     */
    Boolean doSave(String name, List<String> details, Integer adminUserId);

    /**
     * 保存修改的商品规格名；
     *
     * @param id          商品的一级规格的id值；
     * @param adminUserId 用户id值；
     * @param name        一级规格名；
     * @param details     二级规格名们；
     * @return
     */
    Boolean doEdit(Integer id, Integer adminUserId, String name, List<String> details,List<String> detailsId);

    /**
     * 删除数据：
     * 1，删除id为id值的一级商品规格对象；
     * 2，删除与这个一级商品规格对象对应的二级商品规格对象；
     *
     * @param id
     * @return
     */
    Boolean doDelete(Integer id);

    /**
     * 获取一级商品规格，及其对应的二级商品规格 的一个集合；
     * 1，集合的第一位是一级商品规格；
     * 2，后面是商品的二级商品规格；
     *
     * @param id
     * @return
     */
    List<ReGoodsStandard> getGoodsStandardAndDetails(Integer id);

    /**
     * 查询用户的一级商品规格；
     * 要求：
     * 1：这个一级商品规格的所有的二级商品规格，要设置到冗余自动中；
     *
     * @param adminUserId 登录用户id值；
     * @return
     */
    List<ReGoodsStandard> getFirstStandardWithSecondStandard(Integer adminUserId);

    /**
     * 检查一级商品规格是否已经存在；
     *
     * @param name        一级商品规格名称；
     * @param adminUserId 用户id；
     * @return
     */
    boolean checkExist(String name, Integer adminUserId);
}
