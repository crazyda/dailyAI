package com.axp.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.axp.action.database.TablesStatus;

public interface DataBaseDao  {

	
	
	
	/**
	 * 查询所有表
	 */
	List<String> findAllTable(String DBName);
	
	 void findTableByName(Map<String, TablesStatus> tStatus,TablesStatus tablesStatus,String tableName,String type) throws SQLException;
}
