package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 商家商城-订单对象：
 * 此类用来记录商家商城中用户购买商品的订单对象；
 */
public abstract class AbstractSellerMallOrder implements java.io.Serializable {
	private static final long serialVersionUID = 6890618648329046525L;

	// 字段；
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private Boolean isPay;// 订单是否已支付； 这个状态暂时保留，可能会被下面的status替换；
	private String orderNumber;// 订单编号

	private Integer status = 0;
	// 订单状态；
	// 0：创建状态，即待支付状态；
	// 1：已支付状态，等待商家确认；
	// 2：配送状态，即商家确认，准备发货；
	// 3：物流状态，商家已发货；
	// 4：确认状态，即用户收到货，订单完成；
	// 5：退货状态，即用户申请退货；
	// 13:换货状态：即用户申请换货状态；
	// 9：同意退货状态：即商家同意退货状态；
	// 10：不能退货状态：即商家拒绝退货状态；
	// 11：同意换货状态；
	// 12：不能换货状态；
	// 6：退货完成状态，即商家确认，完成退货流程；

	// 7：此状态表示用户提交的未支付订单（因为用户提交的应该是一张订单，但是后台会将提交的信息以商家分类，分解成多张订单；）
	// 8：此状态表示用户提交的已支付订单（若用户用户提交的订单支付完成，则此订单状态为8；）

	private String orderSign;// 因为订单分两种，一种是用户看的整体的订单，一种是被分散到每个商家的真实订单，但是这两种是同时生成的，需要有个标记来关联状态为0的每个商家的订单，和状态为7的显示给用户的订单；

	private Double price;// 订单金额；
	private Double freightage;// 运费；
	private Integer userId;// 这个订单属于的用户ID；
	private String address;// 此张订单的收货地址；
	private String buyerNumber;// 此张订单的购买者的电话；

	private Seller seller;// 这张订单属于哪个商家；
	private Set<SellerMallShoppingCarDetail> details = new HashSet<SellerMallShoppingCarDetail>();// 订单中包含的购物车中的明细；

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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsPay() {
		return isPay;
	}

	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getFreightage() {
		return freightage;
	}

	public void setFreightage(Double freightage) {
		this.freightage = freightage;
	}

	public Set<SellerMallShoppingCarDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<SellerMallShoppingCarDetail> details) {
		this.details = details;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderSign() {
		return orderSign;
	}

	public void setOrderSign(String orderSign) {
		this.orderSign = orderSign;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuyerNumber() {
		return buyerNumber;
	}

	public void setBuyerNumber(String buyerNumber) {
		this.buyerNumber = buyerNumber;
	}

}