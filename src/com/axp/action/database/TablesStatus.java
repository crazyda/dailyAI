package com.axp.action.database;

public class TablesStatus {

	
	private Long time; //执行时间
	
	private String tableName; //表明
	
	private Integer status=NOTRUN; // 
	
	private String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public static final Integer NOTRUN=0; //未运行
	public static final Integer RUN=1;		//正在运行
	public static final Integer FINISH=2;  //完成
	public static final Integer FAIL=-1;   //失败
	
	
	
	
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
