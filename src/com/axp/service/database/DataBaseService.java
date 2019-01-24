package com.axp.service.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.axp.action.database.TablesStatus;

public interface DataBaseService {

	
	
	List<String> findAllTable(String DBName);
	List<Map<String,String>> findAllTableListMap(String DBName) throws SQLException;
	void batchSynchro(HttpServletRequest request,String[] tableName);
	
	Map<String, TablesStatus> getTableStatus(HttpSession session);
}
