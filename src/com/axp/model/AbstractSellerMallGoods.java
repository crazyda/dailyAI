package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 商家商城商品：
 * 此类用来记录商家商城中所有的商品；
 */
public abstract class AbstractSellerMallGoods implements java.io.Serializable {
	private static final long serialVersionUID = 6890618648329046525L;

	// 字段；
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private String name; // 商品名称；
	private SellerMallGoodsType type; // 此商品的二级类型；
	private SellerMallGoodsType parentType; // 此商品的一级类型；
	private String coverPicture; // 商品封面图片；
	private String url; // 商品连接；
	private String introduction; // 商品简介；
	private String unit; // 单位；
	private Integer saleVolume; // 真实的销量；
	private Integer displayVolume; // 显示销量(显示销量只是起始值不为0，增长与saleVolume同步)；
	private Double price;// 商品售价；
	private Double commissionMoney;// 商品的分佣，顾客能得到的钱；
	private Double displayPrice;// 商品显示售价，即原价；
	private Integer saleType = 0; // 商品上架属性：0表示普通商品；1表示热销商品；2表示新上市商品；3表示爆款产品；

	public String getDisplaySaleType(Integer i) {
		if (i == 0) {
			return "普通商品";
		} else if (i == 1) {
			return "热销商品";
		} else if (i == 2) {
			return "新上市商品";
		} else if (i == 3) {
			return "爆款产品";
		} else {
			return "";
		}
	}

	public String getDisplaySaleType() {
		if (saleType == 0) {
			return "普通商品";
		} else if (saleType == 1) {
			return "热销商品";
		} else if (saleType == 2) {
			return "新上市商品";
		} else if (saleType == 3) {
			return "爆款产品";
		} else {
			return "";
		}
	}

	private Double customerCommission = 0.02;// 购买者分佣比例；
	private Double fansCommission = 0.01;// 发展此购买者的粉丝分佣比例；
	private Double sellerCommission = 0.01;// 此购买者对应的商家的分佣比例；
	private Double platCommission = 0.01;// 平台分佣的比例；

	private Seller seller; // 这个商品属于哪个商家；
	private Set<SellerMallDefinedAttribute> classify = new HashSet<SellerMallDefinedAttribute>();// 商品属性---此属性指的是该商品有哪些属性分类，如：品牌，大小，款式；
	private Set<SellerMallGoodsAttribute> goodsAttributes = new HashSet<SellerMallGoodsAttribute>(); // 商品属性---此属性值得是确定属性，如：耐克，42码，运动鞋；
	protected Set<SellerMallPicture> goodsPicture = new HashSet<SellerMallPicture>(); // 商品对应的图片；每个商品最多可以对应五张简述图片；但是介绍商品详情的图片数量不限；

	private Boolean isChecked = false;// 该商品是否审核：

	/** 
	 * 1:为默认展示类型，即只使用商品的coverPicture做商品详情的主展示图；
	 * 2:使用360度的全景展示模式，需要使用者将连续变化的十六张图片，横向拼接成一张图片；
	 */
	private Integer showType = 1;// 商品的展示类型；

	// getAndSet
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SellerMallGoodsType getType() {
		return type;
	}

	public void setType(SellerMallGoodsType type) {
		this.type = type;
	}

	public SellerMallGoodsType getParentType() {
		return parentType;
	}

	public void setParentType(SellerMallGoodsType parentType) {
		this.parentType = parentType;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getSaleVolume() {
		return saleVolume;
	}

	public void setSaleVolume(Integer saleVolume) {
		this.saleVolume = saleVolume;
	}

	public Integer getDisplayVolume() {
		return displayVolume;
	}

	public void setDisplayVolume(Integer displayVolume) {
		this.displayVolume = displayVolume;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Set<SellerMallGoodsAttribute> getGoodsAttributes() {
		return goodsAttributes;
	}

	public void setGoodsAttributes(Set<SellerMallGoodsAttribute> goodsAttributes) {
		this.goodsAttributes = goodsAttributes;
	}

	public Set<SellerMallPicture> getGoodsPicture() {
		return goodsPicture;
	}

	public void setGoodsPicture(Set<SellerMallPicture> goodsPicture) {
		this.goodsPicture = goodsPicture;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(Double displayPrice) {
		this.displayPrice = displayPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<SellerMallDefinedAttribute> getClassify() {
		return classify;
	}

	public void setClassify(Set<SellerMallDefinedAttribute> classify) {
		this.classify = classify;
	}

	public Double getCustomerCommission() {
		return customerCommission;
	}

	public void setCustomerCommission(Double customerCommission) {
		this.customerCommission = customerCommission;
	}

	public Double getFansCommission() {
		return fansCommission;
	}

	public void setFansCommission(Double fansCommission) {
		this.fansCommission = fansCommission;
	}

	public Double getSellerCommission() {
		return sellerCommission;
	}

	public void setSellerCommission(Double sellerCommission) {
		this.sellerCommission = sellerCommission;
	}

	public Double getPlatCommission() {
		return platCommission;
	}

	public void setPlatCommission(Double platCommission) {
		this.platCommission = platCommission;
	}

	public Double getCommissionMoney() {
		return commissionMoney;
	}

	public void setCommissionMoney(Double commissionMoney) {
		this.commissionMoney = commissionMoney;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

}