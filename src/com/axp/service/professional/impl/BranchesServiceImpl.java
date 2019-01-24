package com.axp.service.professional.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.SellerAccountNumberDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.UsersDAO;
import com.axp.model.ProvinceEnum;
import com.axp.model.Seller;
import com.axp.model.SellerAccountNumber;
import com.axp.model.Users;
import com.axp.service.professional.BranchesService;
import com.axp.util.QueryModel;

@Service("axpbranchesService")
public class BranchesServiceImpl extends ProfessionalServiceImpl implements BranchesService{

	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private SellerAccountNumberDAO sellerAccountNumberDAO;
	@Resource
	private UsersDAO usersDAO;
	@Override
	public void saveSeller(Seller seller, String[] nums) {
		QueryModel queryModel = new QueryModel();
		if(seller.getId()==null){
			sellerDAO.save(seller);
		}else{
			sellerDAO.merge(seller);
		}
		if(nums!=null){
			if(nums.length>0){
				nums = removeRepeat(nums);
				Users user = null;
				for(int i=0;i<nums.length;i++){
					if(StringUtils.isNotBlank(nums[i])){
						queryModel.clearQuery();
						queryModel.combPreEquals("seller.id", seller.getId(),"sellerId");
						queryModel.combPreEquals("isValid", true);
						queryModel.combPreEquals("name", nums[i]);
						SellerAccountNumber s = (SellerAccountNumber) dateBaseDAO.findOne(SellerAccountNumber.class, queryModel);
						//获取粉丝账号，并修改粉丝级别
						queryModel.clearQuery();
						queryModel.combPreEquals("name", nums[i]);
						user = (Users) dateBaseDAO.findOne(Users.class, queryModel);
						user.setLevel(1);
						usersDAO.merge(user);
						if(s==null){
							s = new SellerAccountNumber();
							s.setIsValid(true);
							s.setLevel(SellerAccountNumber.LEVEL_SECOND);
							s.setName(nums[i]);
							s.setSeller(seller);
							s.setUser(user);
							sellerAccountNumberDAO.save(s);
							
						}else{
							Users user3 = s.getUser();
							user3.setLevel(2);
							usersDAO.merge(user3);
							s.setName(nums[i]);
							sellerAccountNumberDAO.merge(s);
						}	
					}
				}	
			}
		}
		//获取所有商家
		queryModel.clearQuery();
		queryModel.combPreEquals("seller.id",seller.getId(),"sellerId" );
		queryModel.combPreEquals("level",SellerAccountNumber.LEVEL_SECOND);
		List<SellerAccountNumber> sList = dateBaseDAO.findLists(SellerAccountNumber.class, queryModel);
		for(SellerAccountNumber s:sList){
			Boolean b = false;
			if(nums!=null){
				for(int i=0;i<nums.length;i++){
					if(nums[i]==s.getName()){
						b = true;
						break;
					}
				}
			}
			if(b==false){
				Users u = s.getUser();
				u.setLevel(2);
				usersDAO.merge(u);
				sellerAccountNumberDAO.deleteSellerAccountNumber(s.getId());
			}
		}
		
	}
		
		public String[] removeRepeat(String [] nums){
			Set<String> set = new HashSet<String>();
			for(int i=0;i<nums.length;i++){
				if(StringUtils.isNotBlank(nums[i])){
					set.add(nums[i]);
				}
			}
			String[] num = new String[set.size()];
			int i = 0;
			for(String str:set){
				num[i] = str;
				i++;
			}
			return num;
		}

		@Override
		public void deleteSeller(Seller seller) {
			seller.setIsvalid(false);
			sellerDAO.merge(seller);
			QueryModel queryModel = new QueryModel();
			queryModel.combPreEquals("seller.id",seller.getId(),"sellerId" );
			queryModel.combPreEquals("level",SellerAccountNumber.LEVEL_SECOND);
			List<SellerAccountNumber> sList = dateBaseDAO.findLists(SellerAccountNumber.class, queryModel);
			for(SellerAccountNumber s:sList){
				sellerAccountNumberDAO.deleteSellerAccountNumber(s.getId());
			}
		}

		@Override
		public List<ProvinceEnum> getZoneById(Integer id, Integer level) {
			// TODO Auto-generated method stub
			return null;
		}

}
