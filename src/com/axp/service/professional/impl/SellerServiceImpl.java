package com.axp.service.professional.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.AdminUserScoreRecordDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.SellerAccountNumberDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.TkldPidDao;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.ProvinceEnum;
import com.axp.model.RePermissionRole;
import com.axp.model.ReSellerWithdrawData;
import com.axp.model.SeLive;
import com.axp.model.Seller;
import com.axp.model.SellerAccountNumber;
import com.axp.model.TkldPid;
import com.axp.model.Users;
import com.axp.service.permission.RePermissionRoleService;
import com.axp.service.professional.SellerService;
import com.axp.service.system.AdminUserService;
import com.axp.service.taoke.TkldPidService;
import com.axp.util.CalcUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.UrlUtil;
import com.axp.util.Utility;

@Service("sellerService")
public class SellerServiceImpl extends ProfessionalServiceImpl implements SellerService {

	@Resource
	private SellerDAO sellerDAO;
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private SellerAccountNumberDAO sellerAccountNumberDAO;
	@Resource
	private UsersDAO usersDAO;
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private SellerService sellerService;
	@Resource
	private RePermissionRoleService rePermissionRoleService;
	@Resource
	private TkldPidDao tkldPidDao;
	@Resource
	private AdminUserScoreRecordDAO adminUserScoreRecordDAO;
	
	
	@Override
	public Seller getSellerByAdmin(Integer current_user_id) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("edition", Seller.EDITION_NEW);
		queryModel.combPreEquals("adminUser.id", current_user_id, "adId");
		queryModel.combEquals("isvalid", 1);
		Seller seller = (Seller) dateBaseDAO.findOne(Seller.class, queryModel);
		return seller;
	}

	@Override
	public void updateSeller(Seller seller, String mainnum, String[] nums) {

		QueryModel queryModel = new QueryModel();
		if (seller.getId() != null) {
			//修改店铺
			sellerDAO.merge(seller);
			//保存或修改主账号
			if (StringUtils.isNotBlank(mainnum)) {
				queryModel.combPreEquals("name", mainnum);
				queryModel.combPreEquals("isValid", true);
				Users users = (Users) dateBaseDAO.findOne(Users.class, queryModel);
				if (users != null) {
					seller.setUsers(users);
					sellerDAO.merge(seller);
					queryModel.clearQuery();
					queryModel.combPreEquals("seller.id", seller.getId(), "sellerId");
					queryModel.combPreEquals("level", SellerAccountNumber.LEVEL_MAIN);
					SellerAccountNumber sanMain = (SellerAccountNumber) dateBaseDAO.findOne(SellerAccountNumber.class, queryModel);
					if (sanMain == null) {
						sanMain = new SellerAccountNumber();
						sanMain.setIsValid(true);
						sanMain.setLevel(SellerAccountNumber.LEVEL_MAIN);
						sanMain.setName(mainnum);
						sanMain.setSeller(seller);
						sanMain.setUser(users);
						sellerAccountNumberDAO.save(sanMain);
						users.setLevel(1);
						usersDAO.merge(users);
					} else {
						users.setLevel(1);
						usersDAO.merge(users);
						Users users2 = sanMain.getUser();
						users2.setLevel(2);
						usersDAO.merge(users2);
						sanMain.setUser(users);
						sanMain.setName(mainnum);
						sellerAccountNumberDAO.merge(sanMain);
					}

				}
			}
			if (nums != null) {
				if (nums.length > 0) {
					nums = removeRepeat(nums);
					Users user = null;
					for (int i = 0; i < nums.length; i++) {
						if (StringUtils.isNotBlank(nums[i])) {
							queryModel.clearQuery();
							queryModel.combPreEquals("seller.id", seller.getId(), "sellerId");
							queryModel.combPreEquals("isValid", true);
							queryModel.combPreEquals("name", nums[i]);
							SellerAccountNumber s = (SellerAccountNumber) dateBaseDAO.findOne(SellerAccountNumber.class, queryModel);
							//获取粉丝账号，并修改粉丝级别
							queryModel.clearQuery();
							queryModel.combPreEquals("name", nums[i]);
							user = (Users) dateBaseDAO.findOne(Users.class, queryModel);
							user.setLevel(1);
							usersDAO.merge(user);
							if (s == null) {
								s = new SellerAccountNumber();
								s.setIsValid(true);
								s.setLevel(SellerAccountNumber.LEVEL_SECOND);
								s.setName(nums[i]);
								s.setSeller(seller);
								s.setUser(user);
								sellerAccountNumberDAO.save(s);

							} else {
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
			queryModel.combPreEquals("seller.id", seller.getId(), "sellerId");
			queryModel.combPreEquals("level", SellerAccountNumber.LEVEL_SECOND);
			List<SellerAccountNumber> sList = dateBaseDAO.findLists(SellerAccountNumber.class, queryModel);
			for (SellerAccountNumber s : sList) {
				Boolean b = false;
				if (nums != null) {
					for (int i = 0; i < nums.length; i++) {
						if (nums[i] == s.getName()) {
							b = true;
							break;
						}
					}
				}
				if (b == false) {
					Users u = s.getUser();
					u.setLevel(2);
					usersDAO.merge(u);
					//sellerAccountNumberDAO.deleteSellerAccountNumber(s.getId());
					sellerAccountNumberDAO.deleteSellerAccNumById(s.getId());
				}
			}
		}
	}

    @Override
    public Seller findById(Integer sellerId) {
        return sellerDAO.findById(sellerId);
    }

    @Override
    public Integer withdrawStatus(Seller seller) {

        //如果withdrawStatus本身就有值；
        Integer withdrawStatus = seller.getWithdrawStatus();
        if (withdrawStatus != null) {
            return withdrawStatus;
        }

        //如果没有值，并且没有绑定手机号；
        String phone = seller.getPhone();
        if (phone != null && phone.length() == 11) {
            withdrawStatus = Seller.未填写提现资料;
        } else {
            withdrawStatus = Seller.未确认手机;
            return withdrawStatus;
        }

        //查看是否有申请提现资料审核；
        List<ReSellerWithdrawData> list = reSellerWithdrawDataDAO.getWithdrawDataBySeller(seller);
        if (list.size() != 0) {
            withdrawStatus = Seller.提现资料审核中;
            for (ReSellerWithdrawData each : list) {
                if (each.getIsValid() && each.getCheckStatus() == ReSellerWithdrawData.pass) {
                    withdrawStatus = Seller.提现资料审核通过;
                    break;
                }
                withdrawStatus = Seller.提现资料审核不通过;
            }
        }

        return withdrawStatus;
    }

    public String[] removeRepeat(String[] nums) {
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < nums.length; i++) {
            if (StringUtils.isNotBlank(nums[i])) {
                set.add(nums[i]);
            }
        }
        String[] num = new String[set.size()];
        int i = 0;
        for (String str : set) {
            num[i] = str;
            i++;
        }
        return num;
    }

	@Override
	public List<ProvinceEnum> getZoneById(Integer id, Integer level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeLive getSeliveBySeller(Integer current_seller_id) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("seller.id", current_seller_id, "seId");
		SeLive seLive = (SeLive) dateBaseDAO.findOne(SeLive.class, queryModel);
		return seLive;
	}

	
	/*
	 * 店铺资料审核列表  旧的是按地理位置查找,新的加上 合伙人邀请码
	 * */
	@Override
	public void getSellerList(HttpServletRequest request,HttpServletResponse response) {
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Integer current_level= adminUser.getLevel();
        StringBuffer param = new StringBuffer();//页码条件
        QueryModel model = new QueryModel();
        model.clearQuery();
		if(current_level>=95 ){
			model.combPreEquals("isvalid", true);
			model.combPreEquals("verifyStatus", 0,"verifyStatus");
		}else{
			
			 String zoneStr = "!@#$%%^^&*";
			 String zoneStr2 = "!@#$%%^^&*";
		        ProvinceEnum zone = adminUser.getProvinceEnum();
		        if(zone.getLevel()==1){
		        	zoneStr= zone.getName();
		        	zoneStr2= zone.getName()+"省";
		        	model.combPreEquals("isvalid", true);
					model.combPreEquals("verifyStatus", 0,"verifyStatus");
					model.combCondition(" (address like '%"+zoneStr+"%' or address like '%"+zoneStr2+"%' or  replace(zone,'-','') like '%"+zoneStr+"%' or  replace(zone,'-','') like '%"+zoneStr2+"%'  )");
		        }
		       if(zone.getLevel()==2 && zone.getLevel2()==2){
		        	ProvinceEnum zone2= zone.getProvinceEnum();//运营商
		        	
		        	 QueryModel pmodel = new QueryModel();
		        	pmodel.combPreEquals("isvalid", true);
		        	pmodel.combEquals("level", 3);
		        	pmodel.combEquals("level2", 3);
		        	pmodel.combPreEquals(" provinceEnum.id", zone.getId(),"PId");
		        	zoneStr= zone2.getName()+zone.getName();  
		        	zoneStr2= zone2.getName()+"省"+zone.getName();
		        	List<ProvinceEnum> plist =dateBaseDao.findLists(ProvinceEnum.class,pmodel);
		        	
		        	String con ="(  ";
		        	boolean falg=false;
		        	int i=0;
		        	if(plist!=null && plist.size()>0){
		        		for(ProvinceEnum pe :plist){
		        			con=	con.concat(" address like '%").concat(zone.getName()+pe.getName()).concat("%'");
		        			if(i<plist.size()-1){
		        				con=con.concat(" or ");
		        			}
		        			i++;
		        		}
		        	}else{
		        		falg =true;
		        	}
		        	
		        	con =con.concat(")");
		        	
		        	if(falg){
		        		model.combCondition(" 1 > 2 ");
		        	}else{
		        		model.combPreEquals("isvalid", true);
	        			model.combPreEquals("verifyStatus", 0,"verifyStatus");
		        		model.combCondition(con);
		        	}
		        	
		        	/*if(adminUser.getProvinceEnum().getId()==634){//松原特殊处理
    					model.combPreEquals("isvalid", true);
    					model.combPreEquals("verifyStatus", 0,"verifyStatus");
            			model.combCondition(" (address like '%松原市宁江区%' or address like '%松原市前郭尔罗斯%')");
    					
    				}else{
            			
            			model.combCondition(" (address like '%"+zoneStr2+"%' or address like '%"+zoneStr+"%' or  replace(zone,'-','') like '%"+zoneStr+"%' or  replace(zone,'-','') like '%"+zoneStr2+"%')");
            			model.combCondition(" (address not like '%县%')");
    				}*/
		       }else if(zone.getLevel()==3 && zone.getLevel2()==2){
		    	   		
		    	       zoneStr = adminUser.getProvinceEnum().getProvinceEnum2().getName()+adminUser.getProvinceEnum().getName();
		    	       zoneStr2 = adminUser.getProvinceEnum().getProvinceEnum2().getName()+"省"+adminUser.getProvinceEnum().getProvinceEnum().getName()+adminUser.getProvinceEnum().getName();
		    	      String zoneStr3=adminUser.getProvinceEnum().getProvinceEnum2().getName()+adminUser.getProvinceEnum().getProvinceEnum().getName()+adminUser.getProvinceEnum().getName();
	        			model.combPreEquals("isvalid", true);
	        			model.combPreEquals("verifyStatus", 0,"verifyStatus");
	        			model.combCondition(" (address like '%"+zoneStr2+"%' or address like '%"+zoneStr+"%'  or address like '%"+zoneStr3+"%' or  replace(zone,'-','') like '%"+zoneStr+"%' or  replace(zone,'-','') like '%"+zoneStr2+"%' or  replace(zone,'-','') like '%"+zoneStr3+"%')");
		    	   
		       }
		       
		       //通过邀请码查询 合伙人列表
		       QueryModel model1 = new QueryModel();
		       model1.combPreEquals("parentId", adminUser.getParentId());
		       model1.combPreEquals("isvalid", true);
		       List<AdminUser> hhr = dateBaseDao.findLists(AdminUser.class, model1);
		       List<Integer> sellerIds =  new ArrayList<Integer>();
		       for(AdminUser h: hhr){
		    	   if(h.getSellerId()!=null){
		    		   sellerIds.add(h.getSellerId());
		    	   }
		       }
		       
		      model.orIn("id", (sellerIds.toString()).replace("[", "").replace("]", "")); 
			 
		}
		String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        
        int count = dateBaseDao.findCount(Seller.class, model);
        
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<Seller> slist = dateBaseDao.findPageList(Seller.class, model, start, end);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("slist", slist);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		
		
	}

	
	/*
	 * 审核保存提交
	 * */
	@Override
	public Map<String, Object> doReview(Map<String, Object> returnMap,
			Integer id, Integer verifyStatus,Integer adminuserId, HttpServletRequest request,Integer zoneId)
			 {
		ProvinceEnum enum1 =null;
		
		enum1 = provinceEnumDAO.findById(zoneId);
		
		
		Seller seller=sellerDAO.findById(id);
		if (seller==null) {
			returnMap.put("message", "店铺信息错误；");
			return returnMap;
		}
		
		try{
		
        
        RePermissionRole role=rePermissionRoleDAO.findById(adminuserId);
        if(role==null){
        	returnMap.put("message", "管理权限选择错误！");
			return returnMap;
        }
		
		 AdminUser au = role.getAdminusers();
		
		
		seller.setVerifyStatus(verifyStatus);
		
		 if (verifyStatus==Seller.pass) {
			 SellerAccountNumber sellerAccountNumber=null;
			 List<SellerAccountNumber> san = sellerAccountNumberDAO.findByPropertyWithValid("user.id", seller.getUsers().getId());
			 
			 if(san!=null && san.size()>0){
				 sellerAccountNumber=san.get(0);
			 }else{
				 sellerAccountNumber=new SellerAccountNumber();
			 }
			sellerAccountNumber.setSeller(seller);
			sellerAccountNumber.setIsValid(true);
			sellerAccountNumber.setLevel(0);
			sellerAccountNumber.setName(seller.getUsers().getName());
			sellerAccountNumber.setUser(seller.getUsers());
			sellerAccountNumberDAO.saveOrUpdate(sellerAccountNumber);
			
			List<AdminUser> aulist = adminUserDAO.findByPropertyWithvalid("loginname", "axp"+seller.getUsers().getName());
			if(aulist !=null && aulist.size()>0){
				returnMap.put("message", "已存在后台用户：axp"+seller.getUsers().getName());
				return returnMap;
			}
			
			AdminUser adminUser= new AdminUser();
			adminUser.setSellerId(id);
			adminUser.setUsername(seller.getName());
			adminUser.setLoginname(seller.getUsers().getPhone());
			adminUser.setPassword(seller.getUsers().getPwd());
			adminUser.setPlatformQuantity(0);
			adminUser.setCenterQuantity(0);
			adminUser.setProxyOne(au.getId());
			adminUser.setProxyQuantity(0);
			adminUser.setParentId(au.getId());
			adminUser.setIsvalid(true);
			adminUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			adminUser.setIsZoneLimit(false);
			adminUser.setIsOpenRedpaper(true);
			adminUser.setIsOpenRebate(false);
			adminUser.setPhone(seller.getUsers().getPhone());
			adminUser.setCenter(au.getParentId());
			adminUser.setHasVoucher(false);
			adminUser.setLevel(60);
			adminUser.setQuantity(0);
			adminUser.setRePermissionRoleId(role.getId());
			adminUser.setRole(role);
			adminUser.setProvinceEnum(au.getProvinceEnum());
			adminUser.setParentAdminUser(au);
			adminUser.setProvinceEnum(enum1);
			adminUser.setIsTeam(true);
			adminUser.setScore(0);
			adminUser.setShowScore(0);
			adminUser.setShowMoney(0d);
			adminUser.setMoney(0d);
			adminUser.setDeposit(0.0);
			adminUser.setUsers(seller.getUsers());
			adminUserDAO.save(adminUser);
			
			seller.setVerifyStatus(2);//审核通过，开店成功(用户未确认)
			seller.setAdminUser(adminUser);
			seller.setLevel(0);
			seller.setProvinceEnum(enum1);
			seller.setEdition(1);
			sellerDAO.update(seller);
			QueryModel queryModel = new QueryModel();
			Users user = seller.getUsers();
			user.setIsSeller(true);
			user.setSellerId(seller.getId());
			userDao.update(user);
			TkldPid a_tlp=null;
			
			
			
			if(user!=null){
				queryModel.clearQuery();
				queryModel.combPreEquals("isValid", true);
				queryModel.combPreEquals("tkldPid.id", 0,"parent_pid_id");
				queryModel.combPreEquals("users.id",user.getId(),"uid");
				queryModel.combEquals("level",2);
				List<TkldPid> tklist =  dateBaseDAO.findLists(TkldPid.class, queryModel);
				if(tklist!=null && tklist.size()>0){
					queryModel.clearQuery();
					queryModel.combPreEquals("isValid", true);
					queryModel.combPreEquals("adminUser.id",au.getId(),"auid");
					queryModel.combEquals("level",1);
					List<TkldPid> atklist =  dateBaseDAO.findLists(TkldPid.class, queryModel);
					if(atklist!=null && atklist.size()>0){
						a_tlp = atklist.get(0);
					}else{
						a_tlp=tkldPidDao.findById(501);
					}
					
					
					TkldPid tlp = tklist.get(0);
					//tlp.setAdminUser(au);
					tlp.setTkldPid(a_tlp);
					tkldPidDao.saveOrUpdate(tlp);
					double money = adminUser.getMoney()==null?0d:adminUser.getMoney();
					
					adminUser.setMoney(CalcUtil.add(money, 100));
					
					//adminUser.setShowScore(1000);
					adminUserDAO.saveOrUpdate(adminUser);
					AdminuserCashpointRecord asr = new AdminuserCashpointRecord();
					
					asr.setAdminUser(adminUser);
					asr.setCashpoint(100d);
					asr.setType(1);
					asr.setRemark("开通店铺送"+100);
					asr.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
					asr.setIsValid(true);
					adminuserCashpointRecordDAO.save(asr);
					
				}
				if(user.getUnionId() != null){
					
					String unionId = user.getUnionId();
					String param = "unionId="+unionId+"&linkType=1";
					UrlUtil.sendGzhMsg(9, param);
				}
			}
			
			
			
			
		}else if(verifyStatus==Seller.unPass){
			seller.setVerifyStatus(-2);
			sellerDAO.update(seller);
		}
		 returnMap.put("message", "审核操作完成；");
		}catch(Exception e){
			e.printStackTrace();
			returnMap.put("message", "审核出错；"+seller.getUsers().getName()+"已存在；不能新增后台用户");
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	     return returnMap;
	}

	@Override
	public void getVerifycompletedList(HttpServletRequest request,
			HttpServletResponse response) {
		String id = (String)request.getParameter("seller_id");
		String search_name = (String)request.getParameter("search_name");
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Integer current_level= adminUser.getLevel();
        if (adminUser.getProvinceEnum()!=null) {
            StringBuffer param = new StringBuffer();//页码条件
            QueryModel model = new QueryModel();
    		
    		model.clearQuery();
    		if(current_level>=95 ){
    			model.combPreEquals("isvalid", true);
    			model.combCondition("( verifyStatus=-1 or verifyStatus=2 or verifyStatus=1  )");
    		}else{
    			String zoneStr = "";
    			String parentzoneStr ="";
    			if (adminUser.getProvinceEnum().getLevel()==2 || adminUser.getProvinceEnum().getLevel2()==2) {//市级代理
    				zoneStr = adminUser.getProvinceEnum().getProvinceEnum2().getName()+adminUser.getProvinceEnum().getName();
        			parentzoneStr = adminUser.getProvinceEnum().getProvinceEnum2().getName()+"省"+adminUser.getProvinceEnum().getName();
    				
    				if(adminUser.getProvinceEnum().getId()==634){//松原特殊处理
    					model.combPreEquals("isvalid", true);
            			model.combCondition("( verifyStatus=-1 or verifyStatus=2 or verifyStatus=1  )");
            			model.combCondition(" (address like '%宁江区%' or address like '%前郭尔罗斯%')");
    					
    				}else{
            			model.combPreEquals("isvalid", true);
            			model.combCondition("( verifyStatus=-1 or verifyStatus=2 or verifyStatus=1  )");
            			model.combCondition(" (address like '%"+parentzoneStr+"%' or address like '%"+zoneStr+"%')");
            			model.combCondition(" (address not like '%县%')");
    				}
    				
    				
				}else if(adminUser.getProvinceEnum().getLevel()==3 || adminUser.getProvinceEnum().getLevel2()==2){//县级代理
					zoneStr = adminUser.getProvinceEnum().getProvinceEnum2().getName()+adminUser.getProvinceEnum().getName();
        			parentzoneStr = adminUser.getProvinceEnum().getProvinceEnum2().getName()+"省"+adminUser.getProvinceEnum().getProvinceEnum().getName()+adminUser.getProvinceEnum().getName();
        			model.combPreEquals("isvalid", true);
        			model.combCondition("( verifyStatus=-1 or verifyStatus=2 or verifyStatus=1  )");
        			model.combCondition(" (address like '%"+parentzoneStr+"%' or address like '%"+zoneStr+"%')");
//da 运营中心级别的查询
				}else if(adminUser.getProvinceEnum().getLevel()==1 || adminUser.getProvinceEnum().getLevel2()==1){
					zoneStr = adminUser.getProvinceEnum().getName();
					parentzoneStr = adminUser.getProvinceEnum().getName()+"省";
					model.combPreEquals("isvalid", true);
					model.combCondition("( verifyStatus=-1 or verifyStatus=2 or verifyStatus=1  )");
					model.combCondition(" (address like '%"+parentzoneStr+"%' or address like '%"+zoneStr+"%')");
				}
    			 
    		
    			
    		}
    		
    		
    		//id
    	    if(!StringUtils.isEmpty(id )){
    	    	model.combPreEquals("id", Integer.parseInt(id ));
    	    	param.append("&seller_id="+id );
    	    }
    	    //搜索
    	    if(!StringUtils.isEmpty(search_name)){
    	    	model.combCondition("name like '%"+search_name+"%'");
    	    	param.append("&search_name="+search_name);
    	    }
    	   
    		String pagestr = request.getParameter("page");
            PageInfo pageInfo = new PageInfo();
            int count = dateBaseDao.findCount(Seller.class, model);
            Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
            int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
            int end = pageInfo.getPageSize();
            pageInfo.setParam(param + "&page=");
            model.setOrder("zhidingTime desc ,createtime DESC ");
            List<Seller> slist1 = dateBaseDao.findPageList(Seller.class, model, start, end);
            request.setAttribute("page", pagestr);
            request.setAttribute("count", count);
            request.setAttribute("list", slist1);
            request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		}
        
   
		
	}

	
	/*
	 * 获取所属联盟组数据
	 * 
	 * */
	@Override
	public void detailOfStoreVerify(HttpServletRequest request,
			Integer sellerId) {
		Seller seller=sellerService.findById(sellerId);
		 request.setAttribute("seller", seller);
		 Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		 List<AdminUser> agencylist =null;
		 AdminUser  au= adminUserDAO.findById(current_user_id);
		 if (au.getLevel()>=95) {
			 agencylist=adminUserService.getlevel(75);
		 }else if(au.getLevel()<95 && au.getLevel()>=85){
			 agencylist = adminUserService.getUserByZone(75, current_user_id);
		 }else if(au.getLevel()>=75){
			 QueryModel queryModel = new QueryModel();
			 List<ProvinceEnum> list =null;
			 if (au.getProvinceEnum()!=null  && au.getProvinceEnum().getLevel()!=3) {
				queryModel.combPreEquals("isValid", true);
				queryModel.combPreEquals("parentId", au.getProvinceEnum().getId());
				list = dateBaseDao.findLists(ProvinceEnum.class, queryModel);
			}else{
				queryModel.clearQuery();
				queryModel.combPreEquals("isValid", true);
				queryModel.combPreEquals("id", au.getProvinceEnum().getId());
				list = dateBaseDao.findLists(ProvinceEnum.class, queryModel);
			}
			 List<RePermissionRole> levelMap = rePermissionRoleDAO.findByPropertyWithValid("adminusers.id", current_user_id);
			 request.setAttribute("level", levelMap);
			 request.setAttribute("list", list);
		 }else if(au.getLevel()==65){
			 List<RePermissionRole> levelMap = rePermissionRoleDAO.findByPropertyWithValid("adminusers.id", current_user_id);
			 request.setAttribute("level", levelMap);
		 }
		 request.setAttribute("agencylist", agencylist);
		 request.setAttribute("adminuser", au);
		 
	}

	
	@Override
	public List<Seller> findByAll() {
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("isValid", true);
		model.combPreEquals("verifyStatus", 1);
		
		return dateBaseDao.findLists(Seller.class, model);
	}

	@Override
	public Map<String,Object>  findAdminUserToSeller(HttpServletRequest request,
			HttpServletResponse response) {
		 //返回数据；
        Map<String, Object> returnMap = new HashMap<>();
		
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
		String sellerPhone = request.getParameter("sellerPhone");
        QueryModel model = new QueryModel();
        model.clearQuery();
        model.combPreEquals("isvalid", true);
        model.combPreEquals("level", 60);
     //   model.combPreEquals("parentId", adminUserId);
        model.combPreEquals("phone", sellerPhone);
        List<AdminUser> adminUsers = dateBaseDao.findLists(AdminUser.class, model);
        AdminUser adminUser = null;
        if(adminUsers !=null && adminUsers.size()>0){
        	adminUser = adminUsers.get(0);
        	
        	if(adminUser.getDeposit()<2000){
        		 returnMap.put("message", "该商家押金不足2000元,请重新选择");
        		 returnMap.put("code", -1);
        	}else{
        		returnMap.put("message", "确认选择该商家:"+adminUser.getUsername());
        		returnMap.put("sellerName", adminUser.getUsername());
        		returnMap.put("sellerId", adminUser.getId());
        		returnMap.put("code", 1);
        		
        	}
        }else{
        	returnMap.put("code", -1);
        	 returnMap.put("message", "没有找到该商家,请重新选择");	
        }
		return returnMap;
		
        
        
	}




}
