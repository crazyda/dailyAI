package com.axp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SellerMallGoods extends AbstractSellerMallGoods implements java.io.Serializable, Comparator<SellerMallPicture> {
	private static final long serialVersionUID = -5089538536194120660L;

	// 构造函数；
	public SellerMallGoods() {
	}

	@Override
	public int compare(SellerMallPicture o1, SellerMallPicture o2) {
		if (o1.getId() < o2.getId()) {
			return -1;
		} else if (o1.getId() == o2.getId()) {
			return 0;
		} else if (o1.getId() > o2.getId()) {
			return 1;
		}
		return 0;
	}

	/**
	 * 提供图片的排序方法；
	 * @return
	 */
	public List<SellerMallPicture> getGoodsPictureOrderById() {
		List<SellerMallPicture> list = new ArrayList<SellerMallPicture>(goodsPicture);

		Comparator<SellerMallPicture> c = new SellerMallGoods();
		Collections.sort(list, c);
		return list;
	}

	/**
	 * 获取商品的360度，全景展示图片；
	 */
	public SellerMallPicture getArroundPicture() {
		for (Iterator<SellerMallPicture> i = goodsPicture.iterator(); i.hasNext();) {
			SellerMallPicture each = i.next();
			if (each.getStatus() != null && each.getStatus() == 1) {
				return each;
			}
		}
		return new SellerMallPicture();
	}
}
