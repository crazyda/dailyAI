package com.axp.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.JdbcUtils;
import com.axp.action.database.TablesStatus;
import com.axp.dao.DataBaseDao;
import com.axp.util.JDBCUtil;

@Repository
public class DataBaseDaoImpl implements DataBaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	DataSource dataSource;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllTable(String DBName) {
		Session session = sessionFactory.getCurrentSession();
		List<String> list = session.createSQLQuery(
				"select table_name from information_schema.tables where table_schema='"
						+ DBName + "'").list();
		return list;
	}

	@SuppressWarnings("null")
	public void findTableByName(Map<String, TablesStatus> tStatus,TablesStatus tablesStatus, String tableName,String type) throws SQLException {
		tablesStatus.setStatus(TablesStatus.RUN);
		tStatus.put(tablesStatus.getTableName(), tablesStatus);
		Connection connection =dataSource.getConnection();
		Connection targetConn=null;
		PreparedStatement prepareStatement=null; 
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from ").append(tableName);
		Object maxCountStr=null;
		int maxCount=0;
		synchronized (this) {
			//查询表是否有id列
			boolean flag = findIdColumn(tableName, connection); //查询本地是否有ID列
			if(!flag||"clear".equals(type)){
				type="clear";
						targetConn = JDBCUtil.getConnection(); //清除目标源 X表
						DataBaseDaoImpl.cleanTable(tableName, targetConn); //自带关闭conn
			}
		}
		
		
		//如果是增量 那么就要获的写入数据源的起始行数
		if(type.equals("increment")){
			//查处目标数据最大行数
			maxCountStr =JDBCUtil.getSingle("select id from "+tableName+" order by id desc"); //自带关闭connection
			maxCount=maxCountStr==null?0:Integer.parseInt(maxCountStr.toString());
			sb.append(" where id>"+maxCount);
		}
		
		
		prepareStatement = connection.prepareStatement(sb.toString()); //本地数据源查询数据
		 
		ResultSet rsCount =prepareStatement.executeQuery();
		rsCount.next();
		int count = rsCount.getInt(1);
		
		
		JDBCUtil.Close(connection, rsCount, prepareStatement,null);
		
		//单表的总数据行数 
		
		int index = 0;
		int size = 2000;
		int pageCount = count %size == 0 ? count / size : count / size + 1;
		
		for (int i = 0; i < pageCount; i++) {
				readData(tableName,maxCount, index++, size,type);
		}
				
	}				

	/**
	 * 读写数据
	 * 
	 * @param tableName
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void readData(final String tableName,int maxId, int index, int size,String type)
			throws SQLException {
		 Connection connection = dataSource.getConnection();
		
		 
		StringBuilder sb=new StringBuilder("select * from ").append(tableName);
		if(type.equals("increment")){
			sb.append(" where id>").append(maxId+size*index).append(" and ")
			.append(" id <=").append(maxId+size*(index+1));
		}else{
			sb.append(" limit ").append( index*size).append(",").append(size);
		}
		PreparedStatement prepareStatement = connection
				.prepareStatement(sb.toString());
		
		ResultSet resultSet = prepareStatement.executeQuery();
		final List<Map<String, Object>> listMap = JDBCUtil	.ResultToListMap(resultSet);
		JDBCUtil.Close(null, resultSet, prepareStatement, null);
			if(listMap.size()>0){

		// 写入数据
		//new Thread(new Runnable() {
		//	@Override
	//		public void run() {
		//		try {
					writeData(listMap, tableName);
	//		} catch (Exception e) {
					
	//				return;
					/*File file=new File("c://x.txt");
				
					try {
							OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8");
			                BufferedWriter out = new BufferedWriter(writerStream);  
			                out.write(tableName+"========"+e.getMessage()+"\r\n"); // \r\n即为换行  
			                out.flush(); // 把缓存区内容压入文件  
			                out.close(); // 最后记得关闭文件
			                writerStream.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
					
					// 写入异常
				//	throw new RuntimeException(tableName+"========"+e.getMessage());
					//e.printStackTrace();
				}
	//		}
	//	}).start();
			
	//	}
		
	}

	/**
	 * 写入数据
	 * 
	 * @param list
	 * @throws SQLException
	 */
	public void writeData(List<Map<String, Object>> list, String tableName
			) throws SQLException {
		PreparedStatement ps = null;
		Connection connection=null;
		
		try {
			connection=JDBCUtil.getConnection();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			removeRestrain(statement); //解除外键
			
			StringBuffer sb = new StringBuffer();
			StringBuffer key = new StringBuffer();
			StringBuffer val = new StringBuffer();
			
			Map<String, Object> map = list.get(0);
			int k=0;
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while(iterator.hasNext()){
				String keys=iterator.next();
				key.append(k==0?"":",");
				if(keys.equals("describe")){
					keys="`describe`";
				}
					key.append(keys);
				
				val.append(k==0?"":",");
				val.append("?");
				k++;
			}
			sb.append("insert into ").append(tableName).append(" (").
			append(key.toString()).append(") values(").
			append(val.toString()).append(")");
			
			ps = connection.prepareStatement(sb.toString());
			for (int i = 0; i <list.size(); i++) {
				Set<Entry<String, Object>> entrySet = list.get(i).entrySet();
				int index=0;
				for (Entry<String, Object> entry : entrySet) {
						ps.setObject(index+1,entry.getValue());
					index++;
				}
				ps.addBatch();
				// 批量提交
				if ((i+1) % 500 == 0) {
					ps.executeBatch();
					connection.commit();
				}
			}
			ps.executeBatch();
			connection.commit();

		} catch (Exception e) {
			 connection.rollback();
			 throw new RuntimeException(e.getMessage());
			//出现写入异常
		} finally {
			//recoverRestrain(connection);
			JDBCUtil.Close(connection, null, ps, null);
		}
	}
	
	/**
	 * 解除所有外键约束
	 */
	public static void removeRestrain(Statement statement){
		String sql="SET FOREIGN_KEY_CHECKS=0;";
		try {
			statement.executeUpdate(sql);
			//connection.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtil.Close(null, null, null, statement);
		}
	}
	/**
	 * 解除所有外键约束
	 */
	public static void removeRestrain(Connection connection){
		String sql="SET FOREIGN_KEY_CHECKS=0;";
		try {
			//statement.executeUpdate(sql);
			connection.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 恢复所有外键约束
	 */
	public static void recoverRestrain(Connection connection){
		String sql="SET FOREIGN_KEY_CHECKS=1;";
		try {
			connection.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 修改mysql内存大小 批量插入过多会报错
	 */
	public static void updateRAM(Connection connection){
		String sql="SET FOREIGN_KEY_CHECKS=1;";
		try {
			connection.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空表数据
	 * @param tableName
	 * @param connection
	 */
	public static void cleanTable(String tableName,Connection connection){
		String sql="truncate table "+tableName;
		PreparedStatement prepareStatement=null;
		try {
				removeRestrain(connection);
			 prepareStatement = connection.prepareStatement(sql);
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.Close(connection, null, prepareStatement,null);
		
	}


	/**
	 * 查询是否有id列
	 * @throws  
	 */
	public static boolean findIdColumn(String tableName,Connection connection)  {
		boolean flag=false;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("DESCRIBE "+tableName);
			
			ResultSet resultSet = prepareStatement.executeQuery();
			
			flag= JDBCUtil.findIdColumn(resultSet);
			
		} catch (Exception e) {

		}
		return flag;
		
		
	}
	
	
}
