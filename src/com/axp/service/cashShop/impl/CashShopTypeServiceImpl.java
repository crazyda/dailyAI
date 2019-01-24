package com.axp.service.cashShop.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.CashShopTypeDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ImageTypeDAO;
import com.axp.model.AdminUser;
import com.axp.model.CashshopType;
import com.axp.model.ImageType;
import com.axp.service.cashShop.CashShopTypeService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Service("cashShopTypeService")
public class CashShopTypeServiceImpl extends BaseServiceImpl implements CashShopTypeService{

	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private ImageTypeDAO imageTypeDAO;
	@Resource
	private CashShopTypeDAO cashShopTypeDAO;
	
	@Override
	public List<ImageType> getImageType(Integer current_user_id) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("id", current_user_id);
		AdminUser user = (AdminUser) dateBaseDAO.findOne(AdminUser.class, queryModel);		
		List<ImageType> typeList = null;
		if(user.getLevel()>=StringUtil.ADMIN){
			typeList = imageTypeDAO.findAll();
		}else if(user.getLevel()==StringUtil.ADVERONE){
			
			typeList = imageTypeDAO.findByCon(ImageType.class, "id=1 or id=3");
		}else if(user.getLevel()==StringUtil.ADVERTWO){
			typeList = imageTypeDAO.findByCon(ImageType.class, "id=3");
		}else{
			typeList = imageTypeDAO.findByCon(ImageType.class, "id=1");
		}
		
		return typeList;
	}

	public void saveCashShopType(CashshopType cashshopType){
		if(cashshopType.getId()==null){
			cashShopTypeDAO.save(cashshopType);
		}else{
			cashShopTypeDAO.merge(cashshopType);
		}
	}

	
	@Override
	public List<CashshopType> getGameType() {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("imageType.id", 2,"imagetype_id");
		List<CashshopType> types = dateBaseDAO.findLists(CashshopType.class, queryModel);
		
		return types;
	};
	
}
