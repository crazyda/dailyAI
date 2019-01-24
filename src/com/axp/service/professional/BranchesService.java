package com.axp.service.professional;

import com.axp.model.Seller;

public interface BranchesService extends IProfessionalService{

	public void saveSeller(Seller seller,String[] nums);
	
	public void deleteSeller(Seller seller);
}
