package com.axp.dao;

import java.util.List;

import com.axp.model.Seller;
import com.axp.model.Users;

public interface UsersDAO extends IBaseDao<Users> {

	Users findByIdIgnoreValid(Integer id);

	List<Users> findInIds(String propertyName, String ids);
	
	List<String> findInId(String propertyName, String ids);

	Users findByLoginName(String loginName);

	List<Users> findFans(Users user);

	List<String> findAllUsersid();
	
	void del(String ids);
	
	List<Users> findAllUsers();

	
	List<Users> findAllIsValidUsers();

	
	Users findByPhone(String phone);

	Integer updateUsersScore();
	
}