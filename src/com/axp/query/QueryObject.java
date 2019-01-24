package com.axp.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 10;

	private List<String> conditions = new ArrayList<String>();
	private List<Object> params = new ArrayList<Object>();
	private boolean isInit = false;

	/**
	 * 给继承类调用，实现查询条件和参数的上传；
	 */
	public void customerQuery() {
	}

	/**
	 * 初始化操作；
	 * （在获取查询条件和查询参数时，都会调用，但是只执行一次customerQuery方法）
	 */
	private void init() {
		if (!this.isInit) {
			customerQuery();
			this.isInit = true;
		}
	}

	/**
	 * 添加查询条件和查询条件中的参数；
	 * （给继承类使用）
	 */
	public void addCondition(String condition, Object[] params) {
		this.conditions.add(condition);
		this.params.addAll(Arrays.asList(params));
	}

	/**
	 * 获取查询条件；
	 */
	public String getQuery() {
		init();
		if (this.conditions.size() > 0) {
			StringBuilder where = new StringBuilder(200).append(" WHERE ").append(StringUtils.join(this.conditions.toArray(), " AND "));

			return where.toString();
		}
		return "";
	}

	/**
	 * 获取参数；
	 */
	public List<Object> getParams() {
		init();
		return this.params;
	}

	public Integer getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
