package com.axp.dao;

import java.util.List;

import com.axp.model.ImageType;

public interface ImageTypeDAO extends IBaseDao<ImageType>{
	public void save(ImageType transientInstance);
	public void delete(ImageType persistentInstance);
	public ImageType findById(Integer id);
	public void merge(ImageType detachedInstance);
	public void attachDirty(ImageType instance);
	public void attachClean(ImageType instance);
	List<ImageType> findByType(Integer type);
	
	
	
}
