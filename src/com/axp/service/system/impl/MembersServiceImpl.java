package com.axp.service.system.impl;

import org.springframework.stereotype.Service;

import com.axp.service.system.MembersService;

@Service
public class MembersServiceImpl extends BaseServiceImpl implements MembersService {
	//
	//	
	//	public String checkOrSaveMembers(Integer userId, Integer membersType, Double money, String code,String alipayCode){
	//		String status = "1" ;
	//		Users user = null;
	//		user = usersDAO.findById(userId);
	//		if(user==null){
	//			status ="-1";
	//			return status;
	//		}
	//		//如果邀请码是会员本人，则默认为无邀请人
	//		if(code.equals(user.getMycode())){
	//			code = null;
	//		}
	//		MembersTypeIncome membersTypeIncome = null ;
	//		try{
	//		MembersTypeIncomeDAO mtidao = new MembersTypeIncomeDAO();
	//		membersTypeIncome = mtidao.findById(Integer.parseInt(membersType));
	//		if(membersTypeIncome==null){
	//			status = "-4";
	//			return status;
	//		}
	//		}catch(Exception ex){
	//			ex.printStackTrace();
	//		}
	//		Members members = null ;  //注册会员
	//		Users parentUser = null;  //邀请用户
	//		Seller parentSeller = null;  //邀请用户
	//		MembersDAO mdao = new MembersDAO();
	//		//判断粉丝是否已成为会员
	//		members = (Members) mdao.findByUserId(Integer.parseInt(userId));
	//		if(members!=null&&members.getIsActivate()==false){
	//			status ="-3";
	//			return status;
	//		}else if(members!=null&&members.getIsActivate()){
	//			status ="-2";
	//			return status;
	//		}else{
	//			members = new Members();
	//		}
	//		double sellerincome =0.0;
	//		double userincome =0.0;
	//		
	//		members.setAlipayCode(alipayCode);
	//		members.setUsers(user);
	//		members.setCash(0.00);
	//		members.setInviteCode(code);
	//		members.setMembersTypeIncome(membersTypeIncome);
	//		members.setRealPayMoney(money);
	//		members.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
	//		members.setLastTime(new java.sql.Timestamp(System.currentTimeMillis()));
	//		members.setIsValid(true);
	//		if(membersTypeIncome.getPayMoney().compareTo(money)!=0){
	//			members.setIsActivate(false);
	//			try{
	//				Utility.transactionBegin();
	//				mdao.save(members);
	//			    Utility.transactionCommit();
	//		        Utility.closeSession();
	//			}catch(Exception e){
	//				Utility.transactionRollback();
	//				Utility.closeSession();
	//				e.printStackTrace();
	//			}
	//			status = "-4";
	//			return status;
	//		}else{
	//			members.setIsActivate(true);
	//			
	//			if(StringUtils.isNotBlank(code)&&code.startsWith("3") ){//推荐会员为商家身份
	//				
	//				SellerDAO sdao = new SellerDAO();
	//				
	//				List<Seller> parentList = sdao.findByProperty("inviteCode", code);
	//				if(parentList.size()>0){
	//					parentSeller = parentList.get(0);
	//				}
	//				sellerincome=getMoneyForSeller(parentSeller,membersTypeIncome);
	//				
	//			}else if(StringUtils.isNotBlank(code)&&code.startsWith("2")){//推荐会员者为粉丝身份
	//				List<Users> parentList = udao.findByProperty("mycode", code);
	//				if(parentList.size()>0){
	//					parentUser = parentList.get(0);
	//				}
	//				userincome =getMoneyForParentUser(parentUser,membersTypeIncome);
	//			}else{
	//				//无人获得分佣
	//			}
	//			
	//			
	//		}
	//		WalletBIZ wbiz = WalletBizImpl.getInstance();
	//		try{
	//			Utility.transactionBegin();
	//			mdao.save(members);
	//			if(userincome>0 &&parentUser!=null ){
	//				wbiz.addMoneyNotCommit(parentUser, userincome, WalletBIZ.INVITEVIP, user);
	//			}else if(sellerincome>0 && parentSeller!=null){
	//				wbiz.addMoneyForSllerNotCommit(parentSeller,sellerincome,1);
	//			}
	//		    Utility.transactionCommit();
	//		    Utility.closeSession();
	//		    //会员获得免单券
	//		    FreeVoucherBIZ biz = FreeVoucherBizImpl.getInstance();
	//		    biz.addMembersVoucher(user, 1, membersTypeIncome.getAward());
	//		}catch(Exception e){
	//			Utility.transactionRollback();
	//			Utility.closeSession();
	//			e.printStackTrace();
	//		}
	//		return status;
	//	}
	//	
	//	
	//	private static  double getMoneyForParentUser(Users parentUser,MembersTypeIncome membersTypeIncome ){
	//		double returnmoney=0.00;
	//		
	//		Members parentMembers = null ;	//上级会员
	//		MembersDAO mdao = new MembersDAO();
	//		
	//		if(parentUser!=null){//是普通粉丝或者粉丝身份合法
	//			parentMembers =  mdao.findByUserId(parentUser.getId());
	//			if(parentMembers!=null){//上级粉丝是会员或者会员店
	//				parentMembers =  mdao.findByUserId(parentUser.getId());
	//				returnmoney=CalcUtil.mul(parentMembers.getMembersTypeIncome().getParentIncome(), membersTypeIncome.getPayMoney());
	//			}else{//上级用户为普通粉丝
	//				returnmoney=CalcUtil.mul(0.2020, membersTypeIncome.getPayMoney());
	//			}
	//		}
	//		
	//		
	//		
	//		return returnmoney;
	//	}
	//	
	//	private static  double getMoneyForSeller(Seller parentUser,MembersTypeIncome membersTypeIncome ){
	//		double returnmoney=0.00;
	//		
	//		if(parentUser!=null){//粉丝为用户身份
	//			returnmoney=CalcUtil.mul(0.202, membersTypeIncome.getPayMoney());
	//		}
	//		
	//		return returnmoney;
	//	}
	//	
	//	
	//	
	//	public List<Members> getParentMembers(String inviteCode,Integer level){
	//		List<Members > list = new ArrayList<Members>();
	//		QueryModel model = new QueryModel();
	//		model.combPreEquals("isValid", true);
	//		model.combPreEquals("users.mycode", inviteCode,"userCode");
	//		List<Members> parents = DateBaseDAO.findLists(Members.class, model);
	//		if(parents.size()>0){
	//			Members parent = parents.get(0);
	//			while(true){
	//				if(list.size()>=level)
	//					break;
	//				parent = parent.getParentMembers();
	//				if(parent==null)
	//					break;
	//				list.add(parent);
	//			}
	//		}
	//		return list;
	//	}
	//	
	//	public Members getVipByUserId(Integer userId){
	//		Members vip = null;
	//		QueryModel model = new QueryModel();
	//		model.combPreEquals("users.id", userId,"userId");
	//		model.combPreEquals("isValid", true);
	//		List<Members> list = DateBaseDAO.findLists(Members.class, model);
	//		if(list.size()>0){
	//			vip = list.get(0);
	//		}
	//		return vip;
	//	}
	//	
	//	
	//	public List<Members> getParentMembers(Members member,Integer level){
	//		List<Members > list = new ArrayList<Members>();
	//		Members child = member;
	//		while(true){
	//			if(list.size()>=level)
	//				break;
	//			Members parent = child.getParentMembers();
	//			if(parent==null)
	//				break;
	//			list.add(parent);
	//			child = child.getParentMembers();
	//		}
	//		return list;
	//	}
	//	/**
	//	 * 检测更换商家邀请
	//	 * @param user
	//	 */
	//	public void changeParentSeller(Users user){
	//		if(user.getLevel()==2){
	//			String inviteCode = user.getInvitecode();
	//			Users parentSeller = null;
	//			while(parentSeller==null){
	//				String sql = "select id,level,invitecode from users where mycode = "+inviteCode;
	//				List<Object[]> list = Utility.getSession().createSQLQuery(sql).list();
	//				if(list.size()>0){
	//					int id = Integer.parseInt(list.get(0)[0].toString());
	//					int level = Integer.parseInt(list.get(0)[1].toString());
	//					String code = list.get(0)[2].toString();
	//					if(level==1&&code.equals(user.getAdminUser().getInvitecode())){
	//						parentSeller = (Users) Utility.getSession().get(Users.class, id);
	//					}else{
	//						inviteCode = code;
	//					}
	//				}else{
	//					break;
	//				}
	//			}
	//			if(parentSeller!=null){
	//				QueryModel model = new QueryModel();
	//				model.combPreEquals("users.id", parentSeller.getId(), "userId");
	//				model.combPreEquals("isvalid", true);
	//				List<Seller> sellers = DateBaseDAO.findPageList(Seller.class, model, 0, 1);
	//				if(sellers.size()>0){
	//					Seller seller = sellers.get(0);
	//					user.setSellerId(seller.getId());
	//					user.setIsSeller(false);
	//					/*
	//					if(user.getInvitecode().equals(parentSeller.getMycode())){
	//						user.setInvitecode(seller.getInviteCode());
	//					}*/
	//					Utility.transactionBegin();
	//					Utility.getSession().update(user);
	//					Utility.transactionCommit();
	//					if(parentSeller.getSellerId()==null||parentSeller.getIsSeller()==false){
	//						try{
	//							Utility.transactionBegin();
	//							parentSeller.setSellerId(seller.getId());
	//							parentSeller.setIsSeller(true);
	//							Utility.getSession().update(parentSeller);
	//							Utility.transactionCommit();
	//						}catch(StaleObjectStateException e){
	//						}catch(Exception e){
	//							e.printStackTrace();
	//						}
	//					}
	//					Utility.closeSession();
	//				}
	//			}
	//		}else if(user.getLevel()==1&&user.getIsvalid()==false){
	//			user.setIsvalid(true);
	//			Utility.transactionBegin();
	//			try{
	//				Utility.getSession().update(user);
	//				Utility.transactionCommit();
	//			}catch(StaleObjectStateException e){
	//			}catch(Exception e){
	//				e.printStackTrace();
	//			}
	//			Utility.closeSession();
	//		}
	//		
	//	}

}
