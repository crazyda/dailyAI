package com.axp.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义属性类：
 * 用来给商家自定义自己商品的属性，同一个商品可以有多个属性，每个属性可以有多个属性值；
 * 如：鞋子有，颜色，大小等属性，大小有40,41,42等属性值；
 */
public abstract class AbstractSellerMallDefinedAttribute implements
		java.io.Serializable {
	private static final long serialVersionUID = 8149511553589307623L;

	// 字段；
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private String name; // 属性名；
	private String details; // 具体的属性值；(属性值没有使用明细对象，而是拼接在一起用逗号隔开)
	private Seller seller; // 这个属性属于哪个商家；

	// getAndSet

	public List<String> getDetailsList() {// 根据details获取集合；
		String[] a = details.split(",");
		return Arrays.asList(a);
	}

	public void setListToDetails(List<String> list) {// 将list集合拼成字符串赋给details；
		if (list != null) {
			StringBuilder sb = new StringBuilder(100);
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i));
				if (i != (list.size() - 1)) {
					sb.append(",");
				}
			}
			this.details = sb.toString();
		}
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
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

}