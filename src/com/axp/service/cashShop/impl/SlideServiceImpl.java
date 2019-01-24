package com.axp.service.cashShop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.SlidesDAO;
import com.axp.model.Slides;
import com.axp.service.cashShop.SlideService;
import com.axp.service.system.impl.BaseServiceImpl;

@Service("slideService")
public class SlideServiceImpl extends BaseServiceImpl implements SlideService {

	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private SlidesDAO slidesDAO;

	public void saveSlides(Slides slides) {
		if (slides.getId() == null)
			slidesDAO.save(slides);
		else
			slidesDAO.merge(slides);
	}

	@Override
	public void updateSlidesIsValidByID(Integer id) {
		//		QueryModel model = new QueryModel();
		//		model.combPreEquals("id",id );
		//		Slides slides = (Slides) dateBaseDAO.findOne(Slides.class, model);
		//		slides.setIsvalid(false);
		//		slidesDAO.merge(slides);
		slidesDAO.updatePropertyByID("isvalid", false, id, Slides.class);
	}
}
