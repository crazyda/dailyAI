package com.axp.dao;

import java.util.List;

import com.axp.model.UserAddressConfig;

public interface UserAddressConfigDAO extends IBaseDao<UserAddressConfig> {
	/**
	 * 获取指定用户所有的地址信息；
	 * @param user
	 * @return
	 */
	public List<UserAddressConfig> findCurrentUserAddress(Integer userId);

	/**
	 * 更改默认的收货地址
	 * @param userId
	 * @param addressId 
	 */
	public void changeDefaultAddress(Integer userId, Integer addressId);

	/**
	 * 获取默认收货地址；
	 */
	public UserAddressConfig findUserDefinedAddress(Integer userId);
}