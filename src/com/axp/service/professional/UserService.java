package com.axp.service.professional;

import java.util.List;

import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.Seller;


public interface UserService extends IProfessionalService{
     //保存或修改商家账号
	 public void saveAdminUser(AdminUser adminUser,Seller seller);
	 public void saveAdminUserByP(AdminUser adminUser,Seller seller,String[] p);
	 //创建商家店铺
	 public void createSeller(AdminUser adminUser);
	 
	 public List<AdminUser> getAdminTwo(Integer zoneId);
	 
	 public void saveSeller(Seller seller);
	 
	 public String getChildsAdminUser(Integer current_user_id);
	 
	 public void updateAdPWD(Integer id);
	 
	 public void deleteAd(Integer id);
	 
	 public Integer getAdminUserNum(Integer current_user_id);
	 
	 public String getChildsAdminUserByLevel(int current_user_id, int parseInt);
	 
	 //获取地区
	 public List<ProvinceEnum> getZoneByAdmin(Integer id);
	/**
	 * da
	 * @param adminUser
	 * @param seller
	 * @param integral
	 */
	public void saveIntegralRecord(AdminUser adminUser, 
			Integer integral,Integer userLevel);
	/**
	 * 代理 创建的联盟合伙人关联到user
	 * @param adminUser
	 * @param seller
	 * @param phone
	 */
	public void saveAdminUserByUser(AdminUser adminUser, Seller seller,
			String phone);
	
}
