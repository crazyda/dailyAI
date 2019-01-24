package com.axp.service.cashShop;

import com.axp.model.Slides;

public interface SlideService extends ICashShopService{

	public void saveSlides(Slides slides);
	
	public void updateSlidesIsValidByID(Integer id);
}
