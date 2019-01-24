package com.axp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.axp.util.StringUtil;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements com.axp.dao.IBaseDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	private Class<T> targetClass;

	public BaseDaoImpl() {
		// 获取类型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		targetClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
	}

	@Override
	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
	}

	@Override
	public void merge(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(t);

	}

	@Override
	public void saveOrUpdate(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	@Override
	public List<T> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(targetClass).list();
	}

	@Override
	public T findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(targetClass, id);
	}
	
	@Override
	public T findByIdValid(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from " + targetClass.getName() + " where id=? and IsValid=true";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter(0, id);
		return (T) queryObject.uniqueResult();
	}


	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from " + targetClass.getName() + " as model where model." + propertyName + "=:propertyName";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("propertyName", value);
		return queryObject.list();
	}

	@Override
	public List<T> findByPropertyWithValid(String propertyName, Object value) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from " + targetClass.getName() + " as model where model." + propertyName + "=:propertyName and model.isValid = true";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("propertyName", value);
		return queryObject.list();
	}
	
	@Override
	public List<T> findByPropertyWithvalid(String propertyName, Object value) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from " + targetClass.getName() + " as model where model." + propertyName + "=:propertyName and model.isvalid = true";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("propertyName", value);
		return queryObject.list();
	}
	
	
	@Override
	public Integer findCountByQueryChar(String queryChar) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select count(id) from " + targetClass.getName() + " where " + queryChar;
		Query queryObject = session.createQuery(queryString);
		return Integer.parseInt(queryObject.uniqueResult().toString());
	}

	@Override
	public List<T> findListByQueryChar(String queryChar, Integer startIndex, Integer pageSize) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from " + targetClass.getName() + " where " + queryChar;
		queryString = queryString + " order by id desc";
		Query queryObject = session.createQuery(queryString);
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(pageSize);
		return queryObject.list();
	}

	@Override
	public List<T> findListByQueryChar(String queryChar) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from " + targetClass.getName() + " where " + queryChar;
		queryString = queryString + " order by id desc";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}

	public void updatePropertyByID(String propertyName, Object value, int id, Class<T> model) {
		String className = model.getName();
		String queryString = "update " + className + " set " + propertyName + " = ?0 where id = ?1"; // 01 
		Session session = sessionFactory.getCurrentSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("0", value);
		queryObject.setInteger("1", id);
		queryObject.executeUpdate();
	}

	public List<T> findByCon(Class<T> model, String condition, int start, int end) {
		//Integer count = 0;
		Session session = getSessionFactory().getCurrentSession();
		try {

			String queryString = "from " + model.getName() + " where " + condition;

			Query queryObject = session.createQuery(queryString);
			queryObject.setFirstResult(start);  
			queryObject.setMaxResults(end);
			return queryObject.list();
		} catch (RuntimeException re) {
			if (session.isConnected() || session.isOpen()) {
				session.close();
			}
			String queryString = "from " + model.getName() + " where " + condition;

			Query queryObject = session.createQuery(queryString);
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(end);
			return queryObject.list();
		}
	}

	public List<T> findByCon(Class<T> model, String condition) {
		//Integer count = 0;
		Session session = getSessionFactory().getCurrentSession();
		try {

			String queryString = "from " + model.getName() + " where " + condition;

			Query queryObject = session.createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			// throw re;
			if (session.isConnected() || session.isOpen()) {
				//session.close();
			}
			String queryString = "from " + model.getName() + " where " + condition;
			Query queryObject = session.createQuery(queryString);

			return queryObject.list();
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
	 *            orderBy
	 * @return
	 */
	public <E> int getCountsByCon(Class<E> model, String con, Object... values) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "select count(id) from " + model.getName() + " where " + con;
		Query query = session.createQuery(hql);
		if (values != null && values.length != 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
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
	 *            orderBy(如果不需要排序，则传一个空字符串)
	 * @return
	 */
	public <E> List<E> getListByCon(Class<E> model, String con, Integer start, Integer end, String orderBy, Object... values) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = " from " + model.getName() + " where " + con + orderBy;
		Query query = session.createQuery(hql);
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

	public <E> int getAllCountAndCon(Class<E> model, String condition) {
		Session session = getSessionFactory().getCurrentSession();
		String className = model.getName();
		String queryString = "select count(id) from " + className + " where " + condition;
		Query queryObject = session.createQuery(queryString);
		return Integer.parseInt(queryObject.uniqueResult().toString());
	}

	public <E> List<E> findByPageOrderAndCon(Class<E> model, String conditionValue, String orderPropertyName, String value, int start,
			int end) {
		List<E> list = null;
		Session session = getSessionFactory().getCurrentSession();
		try {
			String queryString = "from " + model.getName() + " where " + conditionValue + " order by " + orderPropertyName + " " + value;
			Query queryObject = session.createQuery(queryString);
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(end);
			list = queryObject.list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			if (session.isConnected() || session.isOpen()) {
				session.close();
			}
			String queryString = "from " + model.getName() + " where " + conditionValue + " order by " + orderPropertyName + " " + value;
			Query queryObject = session.createQuery(queryString);
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(end);
			list = queryObject.list();
			// throw re;
		}
		return list;
	}

	public <E> void updatePropertyByIDs(String propertyName, Object value, String ids, Class<E> model) {
		try {
			Session session = getSessionFactory().getCurrentSession();
			String className = model.getName();
			String queryString = "update " + className + " set " + propertyName + " = ? where id in(" + ids + ")";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);

			queryObject.executeUpdate();
		} catch (RuntimeException re) {

			throw re;
		}
	}
	
	/**
	 * 根据条件查询总记录数
	 * 
	 * @param model
	 *            要查询的实体类
	 * @param keys
	 *            要查询的字段
	 * @param values
	 *            要查询的字段
	 * @return
	 */
	public <E> Object findCountByCustom(Class<E> model, List<String> keys, List<Object> values) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuffer sb = new StringBuffer("select count(*) from " + model.getName() + " where 1=1 ");
		if (keys != null && values != null && keys.size() == values.size()) {
			for (int i = 0; i < keys.size(); i++) {
				sb.append(" and " + keys.get(i));
			}
		}
		Query query = session.createQuery(sb.toString());
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query.uniqueResult();
	}
	
	/**
	 * 
	 * 自己给条件的查询
	 * @param model
	 * @param keys
	 * @param values
	 * @param condition
	 * @return
	 */
	public <E> Object findCountByCustom(Class<E> model, List<String> keys, List<Object> values,List<String>condition) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuffer sb = new StringBuffer("select count(*) from " + model.getName() + " where 1=1 ");
		if (keys != null && values != null && keys.size() == values.size()&& keys.size() ==condition.size() ) {
			for (int i = 0; i < keys.size(); i++) {
				sb.append(" "+condition.get(i) +" "+ keys.get(i));
			}
		}
		Query query = session.createQuery(sb.toString());
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query.uniqueResult();
	}

	/**
	 * 根据条件查询分页数据
	 * 
	 * @param model
	 *            要查询的实体类
	 * @param keys
	 *            要查询的字段
	 * @param values
	 *            值
	 * @param startIndex
	 *            开始下标
	 * @param pageSize
	 *            页面大小
	 * @param orderby
	 *            排序
	 * @return
	 */
	public <E> List<E> findListByCustom(Class<E> model, List<String> keys, List<Object> values, Integer startIndex, Integer pageSize,
			String orderby) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
		if (keys != null && values != null && keys.size() == values.size()) {
			for (int i = 0; i < keys.size(); i++) {
				sb.append(" and " + keys.get(i));
			}
		}
		if (orderby != null && !"".equals(orderby)) {
			sb.append(" " + orderby);
		}
		Query query = session.createQuery(sb.toString());
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
		return query.list();
	}

	/**
	 * 根据自己给的条件查询分页数据
	 * 
	 * @param model
	 *            要查询的实体类
	 * @param keys
	 *            要查询的字段
	 * @param values
	 *            值
	 * @param startIndex
	 *            开始下标
	 * @param pageSize
	 *            页面大小
	 * @param orderby
	 *            排序
	 * @return
	 */
	
	public <E> List<E> findListByCustom(Class<E> model, List<String> keys, List<Object> values,List<String> condition, Integer startIndex, Integer pageSize,
			String orderby) {
		Session session = getSessionFactory().getCurrentSession();
		StringBuffer sb = new StringBuffer("from " + model.getName() + " where 1=1 ");
		if (keys != null && values != null && keys.size() == values.size() && keys.size() == condition.size()) {
			for (int i = 0; i < keys.size(); i++) {
				sb.append(" "+condition.get(i) +" "+ keys.get(i));
			}
		}
		if (orderby != null && !"".equals(orderby)) {
			sb.append(" " + orderby);
		}
		Query query = session.createQuery(sb.toString());
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
		return query.list();
	}

	@Override
	public List<T> findByIds(String ids) {
		if(StringUtil.hasLength(ids)&&ids.split(",").length>0){
			String [] idss = ids.split(",");
			Integer [] idsi = new Integer[idss.length];
			for(int i=0;i<idss.length;i++){
				idsi[i] = Integer.parseInt(idss[i]);
			}
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(targetClass);
			criteria.add(Restrictions.in("id", idsi));
			return criteria.list();
		}else{
			return new ArrayList<T>();
		}
	}
	@Override
	public void saveList(List<T> list) {
		Session session = sessionFactory.getCurrentSession();
		try {
			 for (int i = 0; i < list.size(); i++) {
				 T pzflList = (T) list.get(i);
				 
			    session.save(list.get(i));
			    if(i%1000 == 0){   
			        session.flush();  
			        session.clear();  
			    }
			 }
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
