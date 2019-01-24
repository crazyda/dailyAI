package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.UserAddressConfigDAO;
import com.axp.model.UserAddressConfig;

@Repository
public class UserAddressConfigDAOImpl extends BaseDaoImpl<UserAddressConfig> implements UserAddressConfigDAO {

	/**
	 * 获取指定用户所有的地址信息；
	 * @param user
	 * @return
	 */
	@Override
	public List<UserAddressConfig> findCurrentUserAddress(Integer userId) {
			if (userId != null) {
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery("from UserAddressConfig where isValid=1 and userId=" + userId);
				@SuppressWarnings("unchecked")
				List<UserAddressConfig> adds = query.list();
				return adds;
			}

		return null;
	}

	/**
	 * 更改默认的收货地址
	 * @param userId
	 * @param addressId 
	 */
	@Override
	public void changeDefaultAddress(Integer userId, Integer addressId) {
			if (userId != null && addressId != null) {
				Session session = sessionFactory.getCurrentSession();
				session.beginTransaction();
				SQLQuery s1 = session.createSQLQuery("update user_address_config set isDefaul=0 where isValid=1 and userId=" + userId);
				s1.executeUpdate();
				SQLQuery s2 = session.createSQLQuery("update user_address_config set isDefaul=1 where isValid=1 and userId=" + userId
						+ " and id=" + addressId);
				s2.executeUpdate();
				session.getTransaction().commit();
			}

	}

	/**
	 * 获取默认收货地址；
	 */
	@Override
	public UserAddressConfig findUserDefinedAddress(Integer userId) {
		UserAddressConfig userAddressConfig = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserAddressConfig where isValid=1 and isDefaul=1 and userId=:userId");
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<UserAddressConfig> list = query.list();
		if (list.size() > 0) {
			userAddressConfig = list.get(0);
		}
		return userAddressConfig;
	}
}