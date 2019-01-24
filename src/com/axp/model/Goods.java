package com.axp.model;

import java.sql.Timestamp;
import java.util.List;

import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
public class Goods extends AbstractGoods implements java.io.Serializable {
	
	private GoodsAuction goodsAuction;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String imgurl, String name, Integer needScore, Double money,
			Integer shopId, Boolean isvalid, Timestamp createtime,
			Integer tcount) {
		// super(imgurl, name, needScore, money, shopId, isvalid, createtime,
		// tcount);
	}

	/** full constructor */
	public Goods(String imgurl, String name, Integer needScore, Double money,
			Integer shopId, Boolean isvalid, Timestamp createtime,
			Integer tcount, String description, String remark) {
		// super(imgurl, name, needScore, money, shopId, isvalid, createtime,
		// / tcount, description, remark);
	}

	private String showtype;

	public String getShowtype() {
		if(this.getAsort()!=null){
			showtype= StringUtil.getGift_show().get(this.getAsort());
		}
		return showtype;
	}

	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}

	public GoodsAuction getGoodsAuction() {
		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		model.combPreEquals("goods.id", this.getId(), "goodsId");
		List<GoodsAuction> list = dateBaseDAO.findLists(GoodsAuction.class, model);
		if(list.size()>0){
			goodsAuction = list.get(0);
		}
		return goodsAuction;
	}

	public void setGoodsAuction(GoodsAuction goodsAuction) {
		this.goodsAuction = goodsAuction;
	}
	
	
	
}
