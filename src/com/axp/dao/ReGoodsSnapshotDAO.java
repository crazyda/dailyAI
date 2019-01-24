package com.axp.dao;

import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsSnapshot;

public interface ReGoodsSnapshotDAO extends IBaseDao<ReGoodsSnapshot> {

	/**
	 * 根据所给的ReGoodsOfBase对象，保存这个对象的快照对象；
	 * @param baseGoods 基本商品对象；
	 * @return
	 */
	ReGoodsSnapshot saveByBaseGoods(Integer id,ReGoodsOfBase baseGoods);

}