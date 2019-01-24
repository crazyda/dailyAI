package com.axp.util;



public class PageInfo {
	private int pageSize;

	private int currentPage;

	private int totalPage;

	private String param = "pageindex=";
	
	//总记录条数
	private int recordCount;

	private String hrefClickFunction = "getOnePageSalesQuestion";
	private static int FOOT_NUM = 12;
	private static int FOOT_PRE_NUM = 2;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 后台分页算法
	 * 
	 * @return
	 */
	public String createDefaultPageFoot() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("共");
		buffer.append(this.totalPage);
		buffer.append("页");
		buffer.append("&nbsp;");
		buffer.append(this.currentPage + 1);
		buffer.append("/");
		buffer.append(this.totalPage);
		buffer.append("&nbsp;");

		if (this.currentPage > 0) {
			buffer.append("<a href=\"?" + this.param + (this.currentPage - 1)
					+ "\">上一页</a>");
		} else {
			buffer.append("上一页");
		}
		buffer.append("&nbsp;");
		if (this.currentPage + 1 < this.totalPage) {
			buffer.append("<a href=\"?" + this.param + (this.currentPage + 1)
					+ "\">后一页</a>");
		} else {
			buffer.append("后一页");
		}
		buffer.append("&nbsp;&nbsp;");
		buffer.append("<select  onchange=\"javascript:window.location.href='?"
				+ this.param + "' + this.value;\">");
		for (int i = 0; i < this.totalPage; i++) {
			if (i == this.currentPage) {
				buffer.append("<option value='" + i + "' selected>" + (i + 1)
						+ "</option>");
			} else {
				buffer.append("<option value='" + i + "'>" + (i + 1)
						+ "</option>");
			}

		}
		buffer.append("</select>");

		return buffer.toString();
	}

	/**
	 * 前台分页算法,相关新闻
	 * 
	 * @return
	 */
	public String createDefaultPageFootView() {
		if (this.totalPage ==0) return "";
		int preStart = 0, preEnd = 0, endStart = 0, endEnd = 0;
		StringBuffer htmStr = new StringBuffer();
		// htmStr.append("<div class='badoo'>");
		if (this.currentPage == 0)
			htmStr.append("<span class='page_disable'>上一页</span>");
		else
			htmStr.append("<a class='common_link' href='#ajaxLoadScrollToPoint' onclick=\""+hrefClickFunction+"("+ (this.currentPage - 1) + ")\">上一页</a>");

		if (this.totalPage <= FOOT_NUM) {
			for (int i = 0; i < this.totalPage; i++) {
				if (i != this.currentPage)
					htmStr
							.append("<a class='common_link' href='#ajaxLoadScrollToPoint' onclick=\""+hrefClickFunction+"("
									+ i + ")\">" + (i+1) + "</a>");
				else
					htmStr
							.append("<span class='page_current'>" + (i+1)
									+ "</span>");
			}
		} else if (this.currentPage <= FOOT_PRE_NUM) {
			preStart = 0;
			preEnd = 2 * FOOT_PRE_NUM + 1;
			endStart = this.totalPage - FOOT_NUM + (FOOT_PRE_NUM * 2 + 1);
			endEnd = this.totalPage;
		} else if (this.currentPage < this.totalPage - 2) {
			preStart = this.currentPage - 2;
			preEnd = this.currentPage + 2;
			endStart = this.totalPage - FOOT_NUM + (FOOT_PRE_NUM * 2 + 1);
			endEnd = this.totalPage;
			if (endStart <= preEnd) {
				endStart = endStart + (preEnd - endStart + 1 + 1);
				preStart = preStart - (preEnd - endStart + 1 + 1);
			}
			if (endStart >= endEnd)
				endStart = endStart - 2;

			if (endStart == preEnd)
				preEnd = preEnd - 1;
		} else {
			preStart = 0;
			preEnd = 2 * FOOT_PRE_NUM + 1;
			endStart = this.totalPage - FOOT_NUM + (FOOT_PRE_NUM * 2 + 1);
			endEnd = this.totalPage;
		}
		for (int i = endEnd - endStart + preEnd - preStart; i <= FOOT_NUM; i++) {
			if (preStart < 2)
				break;
			preStart = preStart - 1;
		}
		for (int i = preStart; i < preEnd; i++) {
			if (i != this.currentPage)
				htmStr
						.append("<a class='common_link' href='#ajaxLoadScrollToPoint' onclick=\""+hrefClickFunction+"("
								+ i + ")\">" + (i+1) + "</a>");
			else
				htmStr.append("<span class='page_current'>" + (i+1) + "</span>");
		}
		if (endStart - preEnd > 2)
			htmStr.append("...");
		for (int i = endStart; i < endEnd; i++) {
			if (i != this.currentPage)
				htmStr
						.append("<a class='common_link' href='#ajaxLoadScrollToPoint' onclick=\""+hrefClickFunction+"("
								+ i + ")\">" + (i+1) + "</a>");
			else
				htmStr.append("<span class='page_current'>" + (i+1) + "</span>");
		}

		if (this.currentPage + 1 == this.totalPage)
			htmStr.append("<span class='page_disable'>后一页</span>");
		else
			htmStr
					.append("<a class='common_link' href='#ajaxLoadScrollToPoint' onclick=\""+hrefClickFunction+"("
							+ (this.currentPage + 1) + ")\">后一页</a>");
		return htmStr.toString();
	}
	
	/**
	 * 前台分页算法,公共
	 * 
	 * @return
	 */
	public String createCommonDefaultPageFootView() {
		if (this.totalPage ==0) return "";
		int preStart = 0, preEnd = 0, endStart = 0, endEnd = 0;
		StringBuffer htmStr = new StringBuffer();
		// htmStr.append("<div class='badoo'>");
		if (this.currentPage == 0)
			htmStr.append("<span class='page_disable'>上一页</span>");
		else
			htmStr.append("<a class='common_link' href='?" + this.param + (this.currentPage - 1) + "' rel='?" + this.param + (this.currentPage - 1) + "' >上一页</a>");

		if (this.totalPage <= FOOT_NUM) {
			for (int i = 0; i < this.totalPage; i++) {
				if (i != this.currentPage)
					htmStr.append("<a class='common_link' href='?" + this.param + i + "' rel='?" + this.param + i + "'>" + (i+1) + "</a>");
				else
					htmStr.append("<span class='page_current'>" + (i+1)+ "</span>");
			}
		} else if (this.currentPage <= FOOT_PRE_NUM) {
			preStart = 1;
			preEnd = 2 * FOOT_PRE_NUM + 1;
			endStart = this.totalPage - FOOT_NUM + (FOOT_PRE_NUM * 2 + 1);
			endEnd = this.totalPage;
		} else if (this.currentPage < this.totalPage - 2) {
			preStart = this.currentPage - 2;
			preEnd = this.currentPage + 2;
			endStart = this.totalPage - FOOT_NUM + (FOOT_PRE_NUM * 2 + 1);
			endEnd = this.totalPage;
			if (endStart <= preEnd) {
				endStart = endStart + (preEnd - endStart + 1 + 1);
				preStart = preStart - (preEnd - endStart + 1 + 1);
			}
			if (endStart >= endEnd)
				endStart = endStart - 2;

			if (endStart == preEnd)
				preEnd = preEnd - 1;
		} else {
			preStart = 1;
			preEnd = 2 * FOOT_PRE_NUM + 1;
			endStart = this.totalPage - FOOT_NUM + (FOOT_PRE_NUM * 2 + 1);
			endEnd = this.totalPage;
		}
		for (int i = endEnd - endStart + preEnd - preStart; i <= FOOT_NUM; i++) {
			if (preStart < 1)
				break;
			preStart = preStart - 1;
		}
		for (int i = preStart; i < preEnd; i++) {
			if (i != this.currentPage)
				htmStr.append("<a class='common_link' href='?" + this.param + i + "' rel='?" + this.param + i + "'>" + (i+1) + "</a>");
			else
				htmStr.append("<span class='page_current'>" + (i+1) + "</span>");
		}
		if (endStart - preEnd > 2)
			htmStr.append("...");
		for (int i = endStart; i < endEnd; i++) {
			if (i != this.currentPage)
				htmStr
						.append("<a class='common_link' href='?" + this.param + i + "' rel='?" + this.param + i + "'>" + (i+1) + "</a>");
			else
				htmStr.append("<span class='page_current'>" + (i+1) + "</span>");
		}

		if (this.currentPage + 1 == this.totalPage)
			htmStr.append("<span class='page_disable'>下一页</span>");
		else
			htmStr.append("<a class='common_link' href='?" + this.param + (this.currentPage + 1) + "' rel='?" + this.param + (this.currentPage + 1) + "'>下一页</a>");
		return htmStr.toString();
	}

	public String getDefaultPageFoot() {
		return this.createDefaultPageFoot();
	}

	public void setDefaultPageFoot(String defaultPageFoot) {
	}

	public String getDefaultPageFootView() {
		return this.createDefaultPageFootView();
	}

	public void setDefaultPageFootView(String defaultPageFootView) {
	}

	public String getCommonDefaultPageFootView() {
		return this.createCommonDefaultPageFootView();
	}

	public void setCommonDefaultPageFootView(String commonDefaultPageFootView) {
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getHrefClickFunction() {
		return hrefClickFunction;
	}

	public void setHrefClickFunction(String hrefClickFunction) {
		this.hrefClickFunction = hrefClickFunction;
	}

}
