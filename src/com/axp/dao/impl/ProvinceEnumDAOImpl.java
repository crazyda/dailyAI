package com.axp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
@Repository
public class ProvinceEnumDAOImpl extends BaseDaoImpl<ProvinceEnum> implements ProvinceEnumDAO {


	@Autowired
	private AdminUserDAO adminUserDAO;
	 
	@Override
	public String findLevel2ProvinceEnum(ProvinceEnum provinceEnum) {
		StringBuilder sb=new StringBuilder();
		if(provinceEnum!=null){
			if(provinceEnum.getLevel()==1){
				sb.append(" ( provinceEnum.provinceEnum.id=").append(provinceEnum.getId()).
				append(" or provinceEnum.provinceEnum.provinceEnum.id=").append(provinceEnum.getId()).append(" )");
			}else if(provinceEnum.getLevel()==2){
				sb.append(" ( provinceEnum.id=").append(provinceEnum.getId()).
				append(" or provinceEnum.provinceEnum.id=").append(provinceEnum.getId()).append(" )");
			}else if(provinceEnum.getLevel()==3&&provinceEnum.getLevel2()==2
					||provinceEnum.getLevel()==3&&provinceEnum.getLevel2()==3){
				sb.append(" ( provinceEnum.id=").append(provinceEnum.getId()).append(" )");
			}/*else if(provinceEnum.getLevel2()==3){
				sb.append(" provinceEnum.id=").append(provinceEnum.getId());
			}*/
		}
  		return sb.toString();
	}

	
	@Override
	public String findProvinceEnumByAdminUser(ProvinceEnum provinceEnum) {
		StringBuilder sb=new StringBuilder();
		if(provinceEnum!=null){
			if(provinceEnum.getLevel()==1){
				sb.append(" ( adminUser.provinceEnum.provinceEnum.id=").append(provinceEnum.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.provinceEnum.id=").append(provinceEnum.getId()).append(" )");
			}else if(provinceEnum.getLevel()==2){
				sb.append(" ( adminUser.provinceEnum.id=").append(provinceEnum.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.id=").append(provinceEnum.getId()).append(" )");
			}else if(provinceEnum.getLevel()==3&&provinceEnum.getLevel2()==2
					||provinceEnum.getLevel()==3&&provinceEnum.getLevel2()==3){
				sb.append(" ( adminUser.provinceEnum.id=").append(provinceEnum.getId()).append(" )");
			}
		}
  		return sb.toString();
	}
	
	/**
	 * 根据用户查找管辖范围 或 根据传入的地区查找管辖范围
	 * @param adminUserId
	 * @param zoneId
	 * @return
	 */
	@Override
	public List<ProvinceEnum> findProvinceEnumByZoneid(Integer adminId,Integer zoneId){
 		Session session = sessionFactory.getCurrentSession();
			StringBuilder sb=new StringBuilder();
			ProvinceEnum zone=null;
			
			if(adminId!=null){
				AdminUser adminUser = adminUserDAO.findById(adminId);
				zone=adminUser.getProvinceEnum();
			}else if(zoneId!=null){
				zone= this.findByIdValid(zoneId);
			}
			if(zone!=null){   
				sb.append(" from ProvinceEnum where 1=1 ");
				if(zone.getLevel2()==1){ //省 
					sb.append(" AND provinceEnum2.id=").append(zone.getId()).
					append(" OR provinceEnum2.provinceEnum2.id=").append(zone.getId());
				}else if(zone.getLevel()==2&&zone.getLevel2()==2){
					sb.append(" AND provinceEnum2.id=").append(zone.getId());
				}else if(zone.getLevel2()==3||zone.getLevel()==3&&zone.getLevel2()==2){
					sb.append("  AND id=").append(zone.getId());
				}
			}
			Query createQuery = session.createQuery(sb.toString());
					
			
			List<ProvinceEnum> list =createQuery.list(); 
			/*Map<Integer, String> map=new HashMap<Integer, String>();
			for (ProvinceEnum p : list) {
				map.put(p.getId(),p.getName());
			}*/
			
		return list;
	}
	
	
	
}