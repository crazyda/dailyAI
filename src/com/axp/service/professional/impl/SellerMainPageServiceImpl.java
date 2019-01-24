package com.axp.service.professional.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.SellerMainPageDAO;
import com.axp.model.ProvinceEnum;
import com.axp.model.SellerMainPage;
import com.axp.service.professional.SellerMainPageService;

@Service("sellerMainPageService")
public class SellerMainPageServiceImpl extends ProfessionalServiceImpl implements SellerMainPageService{

	@Resource
	private SellerMainPageDAO sellerMainPageDAO;
	public void saveSellerMainPage(SellerMainPage sellerMainPage){
		if(sellerMainPage.getId()==null){
			sellerMainPageDAO.save(sellerMainPage);
		}else{
			sellerMainPageDAO.merge(sellerMainPage);
		}
	}
	@Override
	public List<ProvinceEnum> getZoneById(Integer id, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}
}
