package com.axp.query;

import java.util.ArrayList;
import java.util.List;

/*
 * 封装结果集：
 * 1，将查询的结果封装到result中；
 * 2，将前台需要的各种分页信息同时封装进来；
 */
public class PageResult<T> {
	private Integer totalCount;// 总条数；
	private Integer pageSize = 10;// 每页条数；
	private Integer currentPage = 1;// 当前页；
	private List<T> result;// 查询的结果集合；（这里针对的只是结果集为集合的情况）

	public PageResult() {
	}

	public static <K> PageResult<K> empty(int pageSize) {
		return new PageResult<K>(0, pageSize, 1, new ArrayList<K>());
	}

	/*
	 * 必须要给定的参数，通过构造函数注入；
	 */
	public PageResult(Integer totalCount, Integer pageSize, Integer currentPage, List<T> result) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.result = result;
	}

	/**
	 * 获取总页数；
	 */
	public Integer getTotalPage() {
		return Integer.valueOf(Math.max((this.totalCount.intValue() + this.pageSize.intValue() - 1) / this.pageSize.intValue(), 1));
	}

	/**
	 * 获取上一页；
	 */
	public Integer getPrev() {
		return Integer.valueOf(Math.max(this.currentPage.intValue() - 1, 1));
	}

	/**
	 * 获取下一页；
	 */
	public Integer getNext() {
		return Integer.valueOf(Math.min(this.currentPage.intValue() + 1, getTotalPage().intValue()));
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public List<T> getResult() {
		return this.result;
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