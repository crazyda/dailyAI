package com.axp.util;

public class AjaxResult {
	private Boolean success = false;// 返回的信息性质；
	private String msg;// 返回的信息内容

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AjaxResult() {
		super();
	}

	// 同时设置两个参数，一般用来创建true的ajax信息；
	public AjaxResult(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	// 用来创建错误的ajax信息，因为success默认为false；
	public AjaxResult(String msg) {
		super();
		this.msg = msg;
	}

}
