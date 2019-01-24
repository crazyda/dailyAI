package com.axp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.axp.util.QueryModel;

@Repository
@SuppressWarnings("all")
public class DateBaseDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	public final int PAGESIZE = 20;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 根据条件查询分页数据
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public <T> List<T> findPageList(Class<?> model, QueryModel queryModel, Integer startIndex, Integer pageSize) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
			String hasOrder = queryModel.toString();
			if (hasOrder.indexOf("order") == -1)
				queryModel.setOrder("id desc");
			sb.append(queryModel.toString());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
			if (startIndex != null) {
				query.setFirstResult(startIndex);
			}
			if (pageSize != null) {
				query.setMaxResults(pageSize);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.list();
	}

	/**
	 * 根据条件查询分页数据（没有orderBy条件）
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public <T> List<T> findPageListWithoutOrderBy(Class<?> model, QueryModel queryModel, Integer startIndex, Integer pageSize) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
			sb.append(queryModel.toString());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
			if (startIndex != null) {
				query.setFirstResult(startIndex);
			}
			if (pageSize != null) {
				query.setMaxResults(pageSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.list();
	}

	/**
	 * 根据条件查询数据集合(占位符)
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public List<?> findList(Class<?> model, QueryModel queryModel) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.list();
	}

	/**
	 * 根据条件查询数据集合(占位符)
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public <T> List<T> findLists(Class<T> model, QueryModel queryModel) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.list();
	}

	/**
	 * 根据条件查询数据属性集合(占位符)
	 * @param <T>
	 * @param <T>
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public <T> List<T> findPropertyLists(Class<?> model, String T, String property, QueryModel queryModel) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("select " + property + " from " + model.getName() + " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.list();
	}

	/**
	 * 根据条件查询数据
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public Object findOne(Class<?> model, QueryModel queryModel) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("from " + model.getName() + " where isvalid=true ");
			sb.append(queryModel.getSb());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.uniqueResult();
	}

	/**
	 * 根据条件查询数据
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public String findGroupConcat(String formName, QueryModel queryModel, String propertyName) {
		Query query = null;
		Object object = null;
		try {
			StringBuffer sb = new StringBuffer("select GROUP_CONCAT(DISTINCT " + propertyName + ") from " + formName + " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createSQLQuery(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		object = query.uniqueResult();
		return object == null ? "" : (String) object;
	}

	/**
	 * 根据条件查询某字段参数列
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public String findTheGroupConcat(String formName, QueryModel queryModel, String propertyName) {
		Query query = null;
		Object object = null;
		try {
			StringBuffer sb = new StringBuffer("select GROUP_CONCAT(DISTINCT " + propertyName + ") from " + formName + " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createSQLQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		object = query.uniqueResult();
		return object == null ? "" : (String) object;
	}

	/**
	 * 根据条件查询某字段参数列
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public String findHQLGroupConcat(Class<?> model, QueryModel queryModel, String propertyName) {
		Query query = null;
		String result = "";
		try {
			StringBuffer sb = new StringBuffer("select distinct " + propertyName + " from " + model.getName() + " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
			result = String.valueOf(query.list()).replace(" ", "");
			result = result.substring(1, result.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtils.isBlank(result) ? "-1" : result;
	}

	/**
	 * 根据条件查询某字段参数列
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public List<Object[]> findHQLGroupConcat(Class<?> model, QueryModel queryModel, String propertyName1, String propertyName2) {
		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("select distinct " + propertyName1 + "," + propertyName2 + " from " + model.getName()
					+ " where 1=1 ");
			sb.append(queryModel.getSb());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.list();
	}

	/**
	 * 根据条件查询总记录数
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public Integer findCount(Class<?> model, QueryModel queryModel) {
		StringBuffer sb = new StringBuffer("select count(*) from " + model.getName() + " where 1=1 ");
		sb.append(queryModel.getSb());
		Query query = getSession().createQuery(sb.toString());
		query.setProperties(queryModel.getPreConditionMap());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	/**
	 * 根据条件查询某字段值总和
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public Double findSum(Class<?> model, String property, QueryModel queryModel) {
		StringBuffer sb = new StringBuffer("select sum(" + property + ") from " + model.getName() + " where 1=1 ");
		sb.append(queryModel.getSb());
		Query query = getSession().createQuery(sb.toString());
		query.setProperties(queryModel.getPreConditionMap());
		Object object = query.uniqueResult();
		return object == null ? 0.0 : Double.parseDouble(object.toString());
	}

	/**
	 * 根据条件查询某字段值总和(分组)
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public List<Object> findSumByGroup(Class<?> model, String property, String group, QueryModel queryModel) {
		StringBuffer sb = new StringBuffer("select sum(" + property + ")," + group + " from " + model.getName() + " where 1=1 ");
		sb.append(queryModel.getSb());
		sb.append(" group by " + group);
		Query query = getSession().createQuery(sb.toString());
		query.setProperties(queryModel.getPreConditionMap());
		return query.list();
	}

	/**
	 * 根据条件查询某字段值的平均值
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public Object findAvg(Class<?> model, String property, QueryModel queryModel) {
		StringBuffer sb = new StringBuffer("select avg(" + property + ") from " + model.getName() + " where 1=1 ");
		sb.append(queryModel.getSb());
		Query query = getSession().createQuery(sb.toString());
		query.setProperties(queryModel.getPreConditionMap());
		return query.uniqueResult();
	}

	/**
	 * 根据条件查询某字段值的最大值
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public Object findMax(Class<?> model, String property, QueryModel queryModel) {
		StringBuffer sb = new StringBuffer("select max(" + property + ") from " + model.getName() + " where 1=1 ");
		sb.append(queryModel.getSb());
		Query query = getSession().createQuery(sb.toString());
		query.setProperties(queryModel.getPreConditionMap());
		return query.uniqueResult();
	}

	/**
	 * 根据条件查询某字段值的最小值
	 * @param model 要查询的实体类
	 * @param queryModel 查询模型
	 * @return
	 */
	public Object findMin(Class<?> model, String property, QueryModel queryModel) {
		StringBuffer sb = new StringBuffer("select min(" + property + ") from " + model.getName() + " where 1=1 ");
		sb.append(queryModel.getSb());
		Query query = getSession().createQuery(sb.toString());
		query.setProperties(queryModel.getPreConditionMap());
		return query.uniqueResult();
	}

	/**通过本地sql查询列表
	 * @param sql
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-6
	 */
	public List<Object> findBySql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	/**通过本地sql查询列表
	 * @param sql
	 * @return
	 *@author Maijial
	 *@time 2015-6-6
	 */
	public <T> List<T> findListBySql(String sql, List<Object> values, Class<?> clazz) {
		Query query = getSession().createSQLQuery(sql).addEntity(clazz);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query.list();
	}

	/**
	 * 根据原生sql返回查询总数
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Integer findCountBySqlBase(String sql, List<Object> values) {
		Query query = null;
		Integer count = 0;
		try {
			query = getSession().createSQLQuery(sql);

			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					query.setParameter(i, values.get(i));
				}
			}

			Object o = query.uniqueResult();
			if (o != null) {
				count = Integer.parseInt(o.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	public List findBySqlZLP(String sql) {
		Query query = getSession().createSQLQuery(sql);

		return query.list();
	}

	/**本地sql修改数据库
	 * @param sql
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-6
	 */
	public boolean saveOrUpdateBySql(String sql) {
		try {

			Query query = getSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**新增数据（默认创建时间，isvaid等）
	 * @param model
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-9
	 */
	public Object addData(Object entity) {
		try {

			Class cl = entity.getClass();
			//创建时间
			java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
			java.lang.reflect.Method method1 = cl.getMethod("setCreateTime", java.sql.Timestamp.class);
			method1.invoke(entity, time);
			//是否有效
			java.lang.reflect.Method method2 = cl.getMethod("setIsValid", Boolean.class);
			method2.invoke(entity, true);
			//添加默认数据
			getSession().save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	/**修改数据（默认创建时间，isvaid等）
	 * @param model
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-9
	 */
	public Object editData(Object entity) {
		try {

			Class cl = entity.getClass();
			//修改时间
			java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
			java.lang.reflect.Method method1 = cl.getMethod("setLastTime", java.sql.Timestamp.class);
			method1.invoke(entity, time);
			//添加默认数据
			getSession().update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	/**
	 * 根据原生sql返回查询总数
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Integer findCountBySql(String sql, List<Object> values) {
		Query query = null;
		Integer count = 0;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" select count(*) from (").append(sql).append(") t ");

			query = getSession().createSQLQuery(sb.toString());

			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					query.setParameter(i, values.get(i));
				}
			}

			Object o = query.uniqueResult();
			if (o != null) {
				count = Integer.parseInt(o.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	/**
	 * 根据原生sql分页返回结果集
	 * @param sql
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public <T> List<Map<String, Object>> findPageListBySql(String sql, List<Object> values, Integer startIndex, Integer pageSize,
			String orderBy) {
		Query query = null;
		List<Map<String, Object>> result = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select t.* from (").append(sql).append(") t");

			if (StringUtils.isNotBlank(orderBy)) {
				sb.append(" " + orderBy);
			}

			query = getSession().createSQLQuery(sb.toString());

			if (values != null) {
				for (int i = 0; i < values.size(); i++) {
					query.setParameter(i, values.get(i));
				}
			}

			if (startIndex != null) {
				query.setFirstResult(startIndex);
			}
			if (pageSize != null) {
				query.setMaxResults(pageSize);
			}

			query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			result = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**通过本地sql查询列表
	 * @param sql
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-6
	 */
	public List<Object> findBySql(String sql, List<Object> values) {
		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query.list();
	}

	/**
	 * 通过本地sql查询单个对象
	 * @param sql
	 * @return
	 */
	public Object findOneBySql(String sql, List<Object> values) {
		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query.uniqueResult();
	}

	/**
	 * 通过本地sql查询单个对象 JPA标准占位符
	 * @param sql
	 * @return
	 */
	public Object findOneBySqlJPA(String sql, List<Object> values) {
		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(String.valueOf(i), values.get(i));
			}
		}
		return query.uniqueResult();
	}

	/**
	 * 查询指定第x条数据
	 * @param model
	 * @param queryModel
	 * @param index 从0开始
	 * @return
	 */
	public <T> T findAppointRec(Class<?> model, QueryModel queryModel, int index) {

		Query query = null;
		try {
			StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
			String hasOrder = queryModel.toString();
			if (hasOrder.indexOf("order") == -1)
				queryModel.setOrder("id desc");
			sb.append(queryModel.toString());
			query = getSession().createQuery(sb.toString());
			query.setProperties(queryModel.getPreConditionMap());
			query.setFirstResult(index);
			query.setMaxResults(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<T> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据查询语句 查询
	 * 
	 * @param model
	 *            实体
	 * @param con
	 *            查询语句
	 * @param values
	 *            参数
	 * @param start
	 *            分页
	 * @param end
	 * @param orderBy
	 *            orderBy(如果不需要排序，则传一个空字符串)
	 * @return
	 */
	public <T> List<T> getListByCon(Class<T> model, String con, Integer start, Integer end, String orderBy, Object... values) {
		String hql = " from " + model.getName() + " where " + con + orderBy;
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		if (start != null) {
			query.setFirstResult(start);
		}
		if (end != null) {
			query.setMaxResults(end);
		}
		return query.list();
	}

	/**
	 * 根据查询语句 查询 JPA标准占位符
	 * 
	 * @param model
	 *            实体
	 * @param con
	 *            查询语句
	 * @param values
	 *            参数
	 * @param start
	 *            分页
	 * @param end
	 * @param orderBy
	 *            orderBy
	 * @return
	 */
	public <T> int getCountsByConJPA(Class<T> model, String con, Object... values) {
		String hql = "select count(id) from " + model.getName() + " where " + con;
		Query query = getSession().createQuery(hql);
		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(String.valueOf(i), values[i]);
			}
		}
		return ((Long) (query.uniqueResult())).intValue();
	}

	/**
	 * 根据查询语句 查询
	 * 
	 * @param model
	 *            实体
	 * @param con
	 *            查询语句
	 * @param values
	 *            参数
	 * @param start
	 *            分页
	 * @param end
	 * @param orderBy
	 *            orderBy
	 * @return
	 */
	public <T> int getCountsByCon(Class<T> model, String con, Object... values) {
		String hql = "select count(id) from " + model.getName() + " where " + con;
		Query query = getSession().createQuery(hql);
		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return ((Long) (query.uniqueResult())).intValue();
	}
	
	
	/**本地sql修改数据库
	 * @param sql
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-6
	 */
	public Integer updateByHQL(String hql){
		try {
			Query query = getSession().createQuery(hql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}