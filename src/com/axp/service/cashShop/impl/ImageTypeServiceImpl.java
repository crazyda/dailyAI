package com.axp.service.cashShop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.ImageTypeDAO;
import com.axp.model.ImageType;
import com.axp.service.cashShop.ImageTypeService;
import com.axp.service.system.impl.BaseServiceImpl;

@Service("imageTypeService")
public class ImageTypeServiceImpl extends BaseServiceImpl implements ImageTypeService{

	@Resource
	private ImageTypeDAO imageTypeDAO;
	
	@Override
	public void saveImageType(ImageType imageType) {
		if(imageType.getId()==null){
			imageTypeDAO.save(imageType);
		}else{
			imageTypeDAO.merge(imageType);
		}
	}

}
