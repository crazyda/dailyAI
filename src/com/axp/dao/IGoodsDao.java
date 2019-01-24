package com.axp.dao;

import com.axp.model.Goods;

public interface IGoodsDao extends IBaseDao<Goods> {

	void del(String ids);

	void saveAdverImgs(Integer id, Integer playtotal, String adver_imageurls,
			String adver_imageurls_small, String adver_imgurl_size);

}
