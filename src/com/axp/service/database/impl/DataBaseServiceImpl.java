package com.axp.service.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.stat.TableStat;
import com.axp.action.database.TablesStatus;
import com.axp.dao.DataBaseDao;
import com.axp.dao.impl.DataBaseDaoImpl;
import com.axp.service.database.DataBaseService;
import com.axp.util.JDBCUtil;

@Service
public class DataBaseServiceImpl implements DataBaseService{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DataBaseDao baseDao;
	
	@Override
	public List<String> findAllTable(String DBName) {
		return baseDao.findAllTable(DBName);
	}

	@Autowired
	DataSource dataSource;
	@Override
	public List<Map<String, String>> findAllTableListMap(String DBName)
			throws SQLException {
		
		String filterTable=" and table_name!='rerole_repermission' and table_name!='authorityrole_authority' "
				+ " and table_name!='tk_hhh_config'";
		
		Connection connection = dataSource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("select  table_name,table_rows from information_schema.tables where table_schema='axp2' and table_rows>0 "+filterTable+"  "
				+ " order by table_rows desc");
		ResultSet rs = prepareStatement.executeQuery();
		List<Map<String, String>> list = JDBCUtil.ResultToListMap(rs);
		    return list;
	}
	
	/**
	 * 现在已经拿到所有表明了 开始同步数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void batchSynchro(HttpServletRequest request, String[] tableName) {
		 ExecutorService executorService =null;
		 String type = request.getParameter("type");
		 HttpSession session = request.getSession();
		session.removeAttribute("tablesStatus");
		Map<String, TablesStatus> tablesMap = new HashMap<String, TablesStatus>();
		for (String name : tableName) {
			TablesStatus status = new TablesStatus();
			status.setTableName(name);
			tablesMap.put(name, status);
		}
		session.setAttribute("tablesStatus", tablesMap);

		try {
			
			JDBCUtil.maxConnections(); //设置目标数据源最大连接数
			
			 executorService = Executors.newCachedThreadPool();

			Semaphore semaphore = new Semaphore(1);       
			
			 Date startTime = null;
			 Date endTime = null;
			for ( String name : tableName) {
				Runnable runnable=new RestrictThread(type,startTime,endTime,name,baseDao,semaphore,tablesMap);
				executorService.execute(runnable);
			}
		} catch (Exception e1) {   
			e1.printStackTrace();
		} finally {
			executorService.shutdown();
		}

	}

	@Override
	public Map<String, TablesStatus> getTableStatus(HttpSession session) {
		
		Map<String, TablesStatus> tablesStatusMap = (Map<String, TablesStatus>) session.getAttribute("tablesStatus");
		return tablesStatusMap;
	}
}


class RestrictThread implements Runnable{

	public Connection connection=null;
	public  String type=null;
	public Date startTime=null;
	public Date endTime=null;
	public String name=null;
	public Map<String, TablesStatus> tStatus=null;
	//public  HttpSession session=null;
	public DataBaseDao baseDao;
	public Semaphore semaphore;
	//public ConcurrentHashMap<String, TablesStatus> tStatus;
	

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		TablesStatus tablesStatus = null;
		try {
		semaphore.acquire();
	
			startTime = new Date();
			tablesStatus = tStatus.get(name);
			tablesStatus.setTime(System.currentTimeMillis());
			baseDao.findTableByName(tStatus, tablesStatus, name, type);

			endTime = new Date();
			tablesStatus.setStatus(TablesStatus.FINISH);
			tablesStatus.setTime((endTime.getTime() - startTime
					.getTime()) / 1000);
			tStatus.put(tablesStatus.getTableName(), tablesStatus);
		} catch (Exception e) {
			tablesStatus.setStatus(TablesStatus.FAIL);
			tablesStatus.setErrorMsg(e.getMessage());
			tStatus.put(tablesStatus.getTableName(), tablesStatus);
		}
		finally{
			semaphore.release();
		}
	
	}



	public RestrictThread( String type, Date startTime,
			Date endTime, String name, DataBaseDao baseDao,
			Semaphore semaphore, Map<String, TablesStatus>  tStatus) {
		super();
		
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.baseDao = baseDao;
		this.semaphore = semaphore;
		this.tStatus = tStatus;
	}
	
	
	
	
}

