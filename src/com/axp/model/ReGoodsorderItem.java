package com.axp.model;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * ReGoodsorderItem entity. @author MyEclipse Persistence Tools
 */

public class ReGoodsorderItem implements java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private Integer goodsId;//商品id；
	private String preferential;//优惠信息；
	private String goodName;//商品名称；
	private String goodImg;//商品图片；
	private String goodsStandardIds;//商品对应的规格明细id；
	private String goodsStandardString;//商品对应的规格明细的name；
	private Double goodPrice;//商品售价；
	private Integer goodScore;//商品积分；
	private Double goodCashpoint;//商品红包；
	private Integer goodQuantity;//商品数量；
	private Double payPrice;//需要支付的现金；
	private Integer payScore;//需要支付的积分；
	private Double payCashpoint;//需要支付的红包；
	private Integer mallId;//商城id；
	private String mallClass;//商城前缀（由三个字母组成）；
	private Integer status;//订单状态；
	private Integer isBack;//退单状态；
	private Integer logisticsType;//物流方式；
	private Double logisticsPrice;//物流费用；
	private ReGoodsOfSellerMall reGoodsOfSellerMall; //所有商城都引用了 周边
	private Integer isLock;//是否中奖
	private Integer lotteryYards;//随机码,(对比开奖的)
	private CommodityType gameType;//游戏类型 ldm
	private Integer isGame;//幸运抽奖 中奖用户
	
	
	
	
	public Integer getIsGame() {
		return isGame;
	}

	public void setIsGame(Integer isGame) {
		this.isGame = isGame;
	}

	public CommodityType getGameType() {
		return gameType;
	}

	public void setGameType(CommodityType gameType) {
		this.gameType = gameType;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getLotteryYards() {
		return lotteryYards;
	}

	public void setLotteryYards(Integer lotteryYards) {
		this.lotteryYards = lotteryYards;
	}

	public ReGoodsOfSellerMall getReGoodsOfSellerMall() {
		return reGoodsOfSellerMall;
	}

	public void setReGoodsOfSellerMall(ReGoodsOfSellerMall reGoodsOfSellerMall) {
		this.reGoodsOfSellerMall = reGoodsOfSellerMall;
	}

	private ReShoppingCar car;//关联的购物车对象；
	private Users user;//关联的用户；
	private ReGoodsorder order;//关联的订单对象；
	private String imgUrl;

	private Boolean isStockEnough = true; //是否有足够的库存
	// Constructors

	/** default constructor */
	public ReGoodsorderItem() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@JsonIgnore
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	@JsonIgnore
	public ReGoodsorder getOrder() {
		return order;
	}

	
	public void setOrder(ReGoodsorder order) {
		this.order = order;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getPreferential() {
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public String getGoodName() {
		return this.goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	@JsonIgnore
	public String getGoodImg() {
		return this.goodImg;
	}
	
	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}
	
	public String getGoodPic() {
		return this.goodImg;
	}
	public void setGoodPic(String basePath) {
		this.goodImg = basePath+getGoodImg();
	}
	public String getGoodsStandardIds() {
		return goodsStandardIds;
	}

	public void setGoodsStandardIds(String goodsStandardIds) {
		this.goodsStandardIds = goodsStandardIds;
	}

	public String getGoodsStandardString() {
		return goodsStandardString;
	}

	public void setGoodsStandardString(String goodsStandardString) {
		this.goodsStandardString = goodsStandardString;
	}

	public Double getGoodPrice() {
		return this.goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Integer getGoodScore() {
		return this.goodScore;
	}

	public void setGoodScore(Integer goodScore) {
		this.goodScore = goodScore;
	}

	public Double getGoodCashpoint() {
		return this.goodCashpoint;
	}

	public void setGoodCashpoint(Double goodCashpoint) {
		this.goodCashpoint = goodCashpoint;
	}

	public Integer getGoodQuantity() {
		return this.goodQuantity;
	}

	public void setGoodQuantity(Integer goodQuantity) {
		this.goodQuantity = goodQuantity;
	}

	public Double getPayPrice() {
		return this.payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public Integer getPayScore() {
		return this.payScore;
	}

	public void setPayScore(Integer payScore) {
		this.payScore = payScore;
	}

	public Double getPayCashpoint() {
		return this.payCashpoint;
	}

	public void setPayCashpoint(Double payCashpoint) {
		this.payCashpoint = payCashpoint;
	}

	public Integer getMallId() {
		return this.mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public String getMallClass() {
		return this.mallClass;
	}

	public void setMallClass(String mallClass) {
		this.mallClass = mallClass;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsBack() {
		return this.isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}

	public Integer getLogisticsType() {
		return this.logisticsType;
	}

	public void setLogisticsType(Integer logisticsType) {
		this.logisticsType = logisticsType;
	}

	public Double getLogisticsPrice() {
		return this.logisticsPrice;
	}

	public void setLogisticsPrice(Double logisticsPrice) {
		this.logisticsPrice = logisticsPrice;
	}

	public ReShoppingCar getCar() {
		return car;
	}

	@JsonIgnore
	public void setCar(ReShoppingCar car) {
		this.car = car;
	}

	public Boolean getIsStockEnough() {
		return isStockEnough;
	}

	public void setIsStockEnough(Boolean isStockEnough) {
		this.isStockEnough = isStockEnough;
	}

	public String getStyle(){
		String str = "" ;
		JSONObject jsonObject = JSONObject.parseObject(this.goodsStandardString);
		JSONObject firstjson = jsonObject.getJSONObject("firstStandard");
		String name1 = firstjson.getString("name1")==null?"":firstjson.getString("name1");
		String name2 = firstjson.getString("name2")==null?"":firstjson.getString("name2");
		String name3 = firstjson.getString("name3")==null?"":firstjson.getString("name3");
		str += name1==""?"":name1+":"+jsonObject.getJSONObject("secondStandard").getString("name1")+" ";
		str += name2==""?"":name2+":"+jsonObject.getJSONObject("secondStandard").getString("name2")+" ";
		str += name3==""?"":name3+":"+jsonObject.getJSONObject("secondStandard").getString("name3")+" ";

		return str;
	}

	public String getOrderStatus(){
		if(isBack==10){
			switch (status) {
			case 10:return "待确认";
			case 20:return "待发货";
			case 25:return "待兑换";
			case 30:return "待收货";
			case 40:return "已收货";
			case 50:return "已评价";
			default:
				break;
			}
		}
		else{
			switch (isBack) {
			case 20:return "正在退单";
			case 30:return "已退单";
			case 40:return "不可退单";
			default:
				break;
			}
		}
		return null;
	}
	
	public String getImgUrl(){
		try {
			JSONArray parseArray = JSONArray.parseArray(this.goodImg);
			JSONObject jsonObject = parseArray.getJSONObject(0);
			imgUrl = jsonObject.getString("imgUrl").replace("/nomal", "/160");
		} catch (JSONException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getFirstImgUrl(){
		 if (StringUtils.isEmpty(goodImg)||!goodImg.startsWith("[{"))
	            return "";
	        JSONArray list = JSONObject.parseArray(goodImg);
	        if (list.size() == 0)
	            return "";
	        return list.getJSONObject(0).getString("imgUrl");
	}
}