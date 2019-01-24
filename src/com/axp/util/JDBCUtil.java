package com.axp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class JDBCUtil {
	
	private static String url;
	
	private static String userName;
	
	private static String password;
	
	/**
     * 建立数据库连接
     * 
     * @return
     * @throws SQLException
     */
    public static Connection getConnection()  {
        Connection conn = null;
        try {
        	if(conn==null){
        		Properties pps = new Properties();
        		pps.load(new FileInputStream("db2.properties"));
        		url= pps.getProperty("url");
        		url+="?characterEncoding=utf8&amp;useUnicode=true&amp;autoReconnect=true&amp;zeroDateTimeBehavior=convertToNull";
        		userName= pps.getProperty("userName");
        		password= pps.getProperty("pwd");
        	}
        	conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return conn;
    }
 
    public static void Close(Connection c,ResultSet resultSet,PreparedStatement pre,Statement st){
    	try {
	    	if(resultSet!=null){
	    		resultSet.close();
	    	}
	    	if(pre!=null){
	    		pre.close();
	    	}
	    	if(c!=null){
	    		c.close();
	    	}
	    	if(st!=null){
	    		st.close();
	    	}
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    private static Connection conn = null;
    private static PreparedStatement preparedStatement = null;
    private static CallableStatement callableStatement = null;
 
    /**
     * 用于查询，返回结果集
     * 
     * @param sql
     *            sql语句
     * @return 结果集
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    public static List query(String sql) throws SQLException {
 
        ResultSet rs = null;
        try {
            getPreparedStatement(sql);
            rs = preparedStatement.executeQuery();
            return ResultToListMap(rs);
 
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
 
    }
    
    
    
    
 
    /**
     * 用于带参数的查询，返回结果集
     * 
     * @param sql
     *            sql语句
     * @param paramters
     *            参数集合
     * @return 结果集
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    public static List query(String sql, Object... paramters)
            throws SQLException {
 
        ResultSet rs = null;
        try {
            getPreparedStatement(sql);
 
            for (int i = 0; i < paramters.length; i++) {
                preparedStatement.setObject(i + 1, paramters[i]);
            }
            rs = preparedStatement.executeQuery();
            return ResultToListMap(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
    }
 
    /**
     * 返回单个结果的值，如count\min\max等等
     * 
     * @param sql
     *            sql语句
     * @return 结果集
     * @throws SQLException
     */
    public  static synchronized Object getSingle(String sql) throws SQLException {
        Object result = null;
        ResultSet rs = null;
      
        try {
            getPreparedStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
        }
 
    /**
     * 返回单个结果值，如count\min\max等
     * 
     * @param sql
     *            sql语句
     * @param paramters
     *            参数列表
     * @return 结果
     * @throws SQLException
     */
    public static Object getSingle(String sql, Object... paramters)
            throws SQLException {
        Object result = null;
        ResultSet rs = null;
        try {
            getPreparedStatement(sql);
 
            for (int i = 0; i < paramters.length; i++) {
                preparedStatement.setObject(i + 1, paramters[i]);
            }
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
    }
 
    /**
     * 用于增删改
     * 
     * @param sql
     *            sql语句
     * @return 影响行数
     * @throws SQLException
     */
    public static int update(String sql) throws SQLException {
 
        try {
            getPreparedStatement(sql);
 
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free();
        }
    }
 
    /**
     * 用于增删改（带参数）
     * 
     * @param sql
     *            sql语句
     * @param paramters
     *            sql语句
     * @return 影响行数
     * @throws SQLException
     */
    public static int update(String sql, Object... paramters)
            throws SQLException {
        try {
            getPreparedStatement(sql);
 
            for (int i = 0; i < paramters.length; i++) {
                preparedStatement.setObject(i + 1, paramters[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free();
        }
    }
 
    /**
     * 插入值后返回主键值
     * 
     * @param sql
     *            插入sql语句
     * @return 返回结果
     * @throws Exception
     */
    public static Object insertWithReturnPrimeKey(String sql)
            throws SQLException {
        ResultSet rs = null;
        Object result = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
 
    /**
     * 插入值后返回主键值
     * 
     * @param sql
     *            插入sql语句
     * @param paramters
     *            参数列表
     * @return 返回结果
     * @throws SQLException
     */
    public static Object insertWithReturnPrimeKey(String sql,
            Object... paramters) throws SQLException {
        ResultSet rs = null;
        Object result = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < paramters.length; i++) {
                preparedStatement.setObject(i + 1, paramters[i]);
            }
            preparedStatement.execute();
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
 
    }
 
    /**
     * 调用存储过程执行查询
     * 
     * @param procedureSql
     *            存储过程
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    public static List callableQuery(String procedureSql) throws SQLException {
        ResultSet rs = null;
        try {
            getCallableStatement(procedureSql);
            rs = callableStatement.executeQuery();
            return ResultToListMap(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
    }
 
    /**
     * 调用存储过程（带参数）,执行查询
     * 
     * @param procedureSql
     *            存储过程
     * @param paramters
     *            参数表
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    public static List callableQuery(String procedureSql, Object... paramters)
            throws SQLException {
        ResultSet rs = null;
        try {
            getCallableStatement(procedureSql);
 
            for (int i = 0; i < paramters.length; i++) {
                callableStatement.setObject(i + 1, paramters[i]);
            }
            rs = callableStatement.executeQuery();
            return ResultToListMap(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
    }
 
    /**
     * 调用存储过程，查询单个值
     * 
     * @param procedureSql
     * @return
     * @throws SQLException
     */
    public static Object callableGetSingle(String procedureSql)
            throws SQLException {
        Object result = null;
        ResultSet rs = null;
        try {
            getCallableStatement(procedureSql);
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
    }
 
    /**
     * 调用存储过程(带参数)，查询单个值
     * 
     * @param procedureSql
     * @param parameters
     * @return
     * @throws SQLException
     */
    public static Object callableGetSingle(String procedureSql,
            Object... paramters) throws SQLException {
        Object result = null;
        ResultSet rs = null;
        try {
            getCallableStatement(procedureSql);
 
            for (int i = 0; i < paramters.length; i++) {
                callableStatement.setObject(i + 1, paramters[i]);
            }
            rs = callableStatement.executeQuery();
            while (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free(rs);
        }
    }
 
    public static Object callableWithParamters(String procedureSql)
            throws SQLException {
        try {
            getCallableStatement(procedureSql);
            callableStatement.registerOutParameter(0, Types.OTHER);
            callableStatement.execute();
            return callableStatement.getObject(0);
 
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free();
        }
 
    }
 
    /**
     * 调用存储过程，执行增删改
     * 
     * @param procedureSql
     *            存储过程
     * @return 影响行数
     * @throws SQLException
     */
    public static int callableUpdate(String procedureSql) throws SQLException {
        try {
            getCallableStatement(procedureSql);
            return callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free();
        }
    }
 
    /**
     * 调用存储过程（带参数），执行增删改
     * 
     * @param procedureSql
     *            存储过程
     * @param parameters
     * @return 影响行数
     * @throws SQLException
     */
    public static int callableUpdate(String procedureSql, Object... parameters)
            throws SQLException {
        try {
            getCallableStatement(procedureSql);
            for (int i = 0; i < parameters.length; i++) {
                callableStatement.setObject(i + 1, parameters[i]);
            }
            return callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            free();
        }
    }
 
    /**
     * 批量更新数据
     * 
     * @param sqlList
     *            一组sql
     * @return
     */
    public static int[] batchUpdate(List<String> sqlList) {
 
        int[] result = new int[] {};
        Statement statenent = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statenent = conn.createStatement();
            for (String sql : sqlList) {
                statenent.addBatch(sql);
            }
            result = statenent.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                throw new ExceptionInInitializerError(e1);
            }
            throw new ExceptionInInitializerError(e);
        } finally {
            free(statenent, null);
        }
        return result;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List ResultToListMap(ResultSet rs) throws SQLException {
        List list = new ArrayList();
        while (rs.next()) {
            ResultSetMetaData md = rs.getMetaData();
            Map map = new HashMap();
            for (int i = 1; i <= md.getColumnCount(); i++) {
            		
            		
            		try {
            			map.put(md.getColumnLabel(i),rs.getObject(i));
					} catch (SQLException e) {
						map.put(md.getColumnLabel(i),null);
					}
            		
            }
            list.add(map);
        }
        return list;
    }
    
    public static boolean findIdColumn(ResultSet rs){
    		try {
	   			while(rs.next()){
	   			   ResultSetMetaData md = rs.getMetaData();
	   			   for (int j = 1; j <= md.getColumnCount(); j++) {
	   				if(rs.getObject(j).equals("id")||rs.getObject(j).equals("ID")){
	   	    			return true;
	   	    		}
	   			   }
	   			}
    		
    		} catch (Exception e) {
				// TODO: handle exception
			}
    	return false;
    }
    
    
    //
 
    /**
     * 获取PreparedStatement
     * 
     * @param sql
     * @throws SQLException
     */
    private static void getPreparedStatement(String sql) throws SQLException {
        conn = getConnection();
        preparedStatement = conn.prepareStatement(sql);
    }
 
    /**
     * 获取CallableStatement
     * 
     * @param procedureSql
     * @throws SQLException
     */
    private static void getCallableStatement(String procedureSql)
            throws SQLException {
        conn = getConnection();
        callableStatement = conn.prepareCall(procedureSql);
    }
 
    /**
     * 释放资源
     * 
     * @param rs
     *            结果集
     */
    public static void free(ResultSet rs) {
 
        free(conn, preparedStatement, rs);
    }
 
    /**
     * 释放资源
     * 
     * @param statement
     * @param rs
     */
    public static void free(Statement statement, ResultSet rs) {
        free(conn, statement, rs);
    }
 
    /**
     * 释放资源
     */
    public static void free() {
 
        free(null);
    }
    
    /**
     * 释放连接
     * @param conn
     */
    private static void freeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    /**
     * 释放statement
     * @param statement
     */
    private static void freeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    /**
     * 释放resultset
     * @param rs
     */
    private static void freeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    /**
     * 释放资源
     * 
     * @param conn
     * @param statement
     * @param rs
     */
    public static void free(Connection conn, Statement statement, ResultSet rs) {
        if (rs != null) {
            freeResultSet(rs);
        }
        if (statement != null) {
            freeStatement(statement);
        }
        if (conn != null) {
            freeConnection(conn);
        }
    }
	
    
    /**
	 * 设置目标数据源数据库连接最大数
	 */
	public static void maxConnections(){
		String sql="set global max_connections=1000;";
		Connection connection =null;
		PreparedStatement prepareStatement=null;
		try {
			 connection = JDBCUtil.getConnection();
			 prepareStatement = connection.prepareStatement(sql);
			 prepareStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			JDBCUtil.Close(connection, null, prepareStatement, null);
		}
	}
    
    public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		JDBCUtil.url = url;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		JDBCUtil.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		JDBCUtil.password = password;
	}
}
