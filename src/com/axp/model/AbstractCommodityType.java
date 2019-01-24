package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AbstractCommodityType entity provides the base persistence definition of the
 * CommodityType entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCommodityType implements java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;
	private Integer level;
	private Boolean isLocal;

	private String name;//商品分类的名称；
	private String imgUrl;
	private CommodityType commodityType;//一级商品分类的名称；
	private Set commodityTypes = new HashSet(0);//这个一级商品分类拥有的二级商品分类们；
	private Set cashshops = new HashSet(0);
	private Integer modelId;
	// Constructors

	/** default constructor */
	public AbstractCommodityType() {
	}

	/** minimal constructor */
	public AbstractCommodityType(String name, Boolean isValid) {
		this.name = name;
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractCommodityType(Integer id, Boolean isValid,
			Timestamp createTime, Integer level, String name, String imgUrl,
			CommodityType commodityType, Set commodityTypes, Set cashshops,
			Integer modelId) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.createTime = createTime;
		this.level = level;
		this.name = name;
		this.imgUrl = imgUrl;
		this.commodityType = commodityType;
		this.commodityTypes = commodityTypes;
		this.cashshops = cashshops;
		this.modelId = modelId;
	}
	
	public Boolean getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(Boolean isLocal) {
		this.isLocal = isLocal;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	@JsonIgnore
	public CommodityType getCommodityType() {
		return this.commodityType;
	}

	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@JsonIgnore
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@JsonIgnore
	public Set getCommodityTypes() {
		return this.commodityTypes;
	}

	public void setCommodityTypes(Set commodityTypes) {
		this.commodityTypes = commodityTypes;
	}
	@JsonIgnore
	public Set getCashshops() {
		return this.cashshops;
	}

	public void setCashshops(Set cashshops) {
		this.cashshops = cashshops;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

}