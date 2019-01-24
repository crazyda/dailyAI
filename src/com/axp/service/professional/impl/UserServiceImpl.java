package com.axp.service.professional.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.AdminUserScoreRecordDAO;
import com.axp.dao.AdminUserZonesDAO;
import com.axp.dao.DLScoreMarkDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.ScoreMarkDAO;
import com.axp.dao.SellerAccountNumberDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.SellerMainPageDAO;
import com.axp.dao.ZonesDAO;
import com.axp.dao.impl.SellerMainPageDAOImpl;
import com.axp.model.AdminUser;
import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminUserZones;
import com.axp.model.DLScoreMark;
import com.axp.model.ProvinceEnum;
import com.axp.model.ScoreMark;
import com.axp.model.Seller;
import com.axp.model.SellerAccountNumber;
import com.axp.model.SellerMainPage;
import com.axp.model.Shoptypes;
import com.axp.model.Users;
import com.axp.model.Zones;
import com.axp.service.professional.UserService;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("userService")
public class UserServiceImpl extends ProfessionalServiceImpl implements UserService{

	@Resource
	private SellerDAO sellerDAO;
	@Autowired
	private AdminUserDAO adminUserDAO;
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private ZonesDAO zonesDAO;
	
	@Resource
	private ProvinceEnumDAO provinceEnumDAO;
	@Resource
	private SellerAccountNumberDAO sellerAccountNumberDao;
	
	@Resource
	private AdminUserZonesDAO auzDAO;
	
	@Resource
	private ScoreMarkDAO scoreMarkDAO;
	@Resource
	private DLScoreMarkDAO dlscoreMarkDAO;
	@Override
	public void saveAdminUser(AdminUser adminUser,Seller seller) {
		
		if(adminUser.getId()==null){
			adminUserDAO.save(adminUser);
		}else{
			adminUserDAO.merge(adminUser);
		}
		if(seller!=null){
			seller.setZones(zonesDAO.findById(1));
			if(seller.getId()==null){
				sellerDAO.save(seller);
			}else{
				sellerDAO.merge(seller);
			}
			adminUser.setSellerId(seller.getId());
			adminUserDAO.update(adminUser);
		}
	}

	@Override
	public void createSeller(AdminUser adminUser) {
		Shoptypes  shoptypes = new Shoptypes();
		shoptypes.setId(0);
		Zones zones = new Zones();
		zones.setId(0);
		Seller seller = new Seller();
		seller.setIsvalid(true);
		seller.setShoptypes(shoptypes);
		seller.setCreatetime(new Timestamp(System.currentTimeMillis()));
		seller.setZones(zones);
		seller.setCashPoints(0.0);
		seller.setHasVoucher(false);
		seller.setName(adminUser.getLoginname());
		seller.setAdminUser(adminUser);	
		sellerDAO.save(seller);
		adminUser.setSellerId(seller.getId());
		adminUserDAO.merge(adminUser);
	}

	

	public List<AdminUser> getAdminTwo(Integer zoneId){
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isvalid", true,"isValid");
		queryModel.combPreEquals("provinceEnum.provinceEnum.id", zoneId,"provinceEnumId");
		List<AdminUser>  list = (List<AdminUser>) dateBaseDAO.findList(AdminUser.class, queryModel);
		
		return list;
	}


	@Override
	public void saveSeller(Seller seller) {
		if(seller.getId()==null){
			sellerDAO.save(seller);
		}else{
			sellerDAO.merge(seller);
		}
	}

	@Override
	public String getChildsAdminUser(Integer current_user_id) {
		QueryModel model = new QueryModel();	
		model.combCondition("parentId = "+current_user_id);
		model.combPreEquals("isvalid", true);
		String ids = dateBaseDAO.findHQLGroupConcat(AdminUser.class, model, "id");
		return ids;
	}

	@Override
	public void updateAdPWD(Integer id) {
		AdminUser adminUser = adminUserDAO.findById(id);
		adminUser.setPassword(Utility.MD5("888888"));
		adminUserDAO.merge(adminUser);
	}

	@Override
	public void deleteAd(Integer id) {
		AdminUser adminUser = adminUserDAO.findById(id);
		if(adminUser.getLevel()==60){//删除对应的seller 
			List<Seller> slsit = sellerDAO.findByPropertyWithvalid("adminUser.id", adminUser.getId());
			if(slsit!=null && slsit.size()>0){
				for(Seller seller :slsit){
					List<SellerMainPage> smplist = sellerMainPageDao.findByPropertyWithValid("seller.id", seller.getId());
					for(SellerMainPage smp:smplist){
						smp.setIsValid(false);
						sellerMainPageDao.update(smp);
					}
					
					List<SellerAccountNumber> sanlist = sellerAccountNumberDao.findByPropertyWithValid("seller.id", seller.getId());
					for(SellerAccountNumber san:sanlist){
						san.setIsValid(false);
						sellerAccountNumberDao.update(san);
					}
					
					seller.setIsvalid(false);
					sellerDAO.update(seller);
				}
			}
		}
		
		
		
		adminUser.setIsvalid(false);
		adminUserDAO.merge(adminUser);
	}

	@Override
	public Integer getAdminUserNum(Integer current_user_id) {
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("isValid",true);
		queryModel.combPreEquals("parentId", current_user_id);
		int count = dateBaseDAO.findCount(AdminUser.class, queryModel);
		return count;
	}

	@Override
	public String getChildsAdminUserByLevel(int current_user_id, int parseInt) {
		QueryModel model = new QueryModel();	
		model.combCondition("parentId = "+current_user_id);
		model.combPreEquals("level", parseInt);
		model.combPreEquals("isvalid", true);
		String ids = dateBaseDAO.findHQLGroupConcat(AdminUser.class, model, "id");
		return ids;
	}

	@Override
	public void saveAdminUserByP(AdminUser adminUser, Seller seller, String[] p) {
		if(adminUser.getId()==null){
			adminUserDAO.save(adminUser);
		}else{
			adminUserDAO.merge(adminUser);
		}
		if(seller!=null){
			seller.setZones(zonesDAO.findById(1));
			if(seller.getId()==null){
				sellerDAO.save(seller);
			}else{
				sellerDAO.merge(seller);
			}
			adminUser.setSellerId(seller.getId());
			adminUserDAO.update(adminUser);
		}
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isvalid", true,"isValid");
		queryModel.combPreEquals("adminUser.id", adminUser.getId(),"adminUser");
		List<AdminUserZones>  list = (List<AdminUserZones>) dateBaseDAO.findList(AdminUserZones.class, queryModel);
		
		if(p!=null && p.length>0){
			if(list!=null & list.size()>0){
				for(int i =0;i<list.size();i++){
					auzDAO.delete(list.get(i));
				}
			}
			
			for(int i =0;i<p.length;i++){
				int pid = Integer.parseInt(p[i]);
				ProvinceEnum provinceEnum = provinceEnumDAO.findById(pid);
				if(provinceEnum!=null){
					AdminUserZones auz = new AdminUserZones();
					auz.setAdminUser(adminUser);
					auz.setProvinceEnum(provinceEnum);
					auz.setIsvalid(true);
					auz.setCreatetime(nowTime);
					auzDAO.save(auz);
				}
			}
		}
		
	}
	//分配了积分  并做保存记录
	@Override
	public void saveIntegralRecord(AdminUser adminUser,Integer integral,Integer userLevel){
		
		QueryModel model = new QueryModel();
		//新增下级的的时候 adminUser 上级--总部 积分直接新增 记录
		AdminUserScoreRecord ausr = new AdminUserScoreRecord(); //积分流通交易  谁给了谁
		ausr.setAdminUser(adminUser);
		ausr.setAfterScore((adminUser.getScore()==null?0:adminUser.getScore())+integral);
		ausr.setBeforeScore(adminUser.getIntegral());
		ausr.setCreateTime(new Timestamp(System.currentTimeMillis()));
		ausr.setIsValid(true);
		ausr.setScore(integral);
		ausr.setValidityTime(new Timestamp(System.currentTimeMillis()));
		ausr.setRemark("获得上级"+adminUser.getParentAdminUser().getLoginname()+"分配积分"+integral);
		ausr.setFromAdminUser(adminUser.getParentAdminUser()); //上级
		adminUserScoreRecordDAO.save(ausr);
		
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		Timestamp validityTime =  new Timestamp(DateUtil.addDay2Date(30, new Date()).getTime());
		if(userLevel == 95){ //总部 直接发放到代理手上
			AdminUser zb = adminUser.getParentAdminUser();
			Integer score = zb.getScore();
			zb.setScore(score+integral);
			adminUserDAO.merge(zb);
			model.clearQuery();
			model.combPreEquals("isValid", true);
			//model.combLike("newHands", "z-"+zb.getId());
			model.combPreEquals("adminUser.id", zb.getId(),"adminuserId");
			model.combIsNull("adminUserDL");
			List<ScoreMark> scoreMarks = dateBaseDAO.findPageList(ScoreMark.class, model,0,integral);
			List<DLScoreMark> dlsmList = new ArrayList<DLScoreMark>();
			for(ScoreMark sm : scoreMarks){
				sm.setAdminUserDL(adminUser);
				sm.setRefreshTime(createTime);
				scoreMarkDAO.saveOrUpdate(sm);
				
				DLScoreMark dlsm = new DLScoreMark();
				dlsm.setAdminUser(adminUser);
				dlsm.setCreateTime(createTime);
				dlsm.setIsValid(true);
				dlsm.setScoreMark(sm);
				//dlsmList.add(dlsm);
				dlscoreMarkDAO.save(dlsm);
			}
		
		}else if(userLevel == 99) {  //adminUser 发给总部作为总积分
			List<ScoreMark> smList = new ArrayList<ScoreMark>();
			for(int i=0;i<integral ;i++){
				ScoreMark sm = new ScoreMark();
				sm.setAdminUser(adminUser);
				sm.setCreateTime(createTime);
				sm.setIsValid(true);
				sm.setRefreshTime(createTime);
				smList.add(sm);
			}
			scoreMarkDAO.saveList(smList);
			
			
			
			
		}
		
		
	}
	
	
	
	@Override
	public List<ProvinceEnum> getZoneById(Integer id,Integer level) {
		// TODO Auto-generated method stub
		
		
		
		ProvinceEnum provinceEnum = provinceEnumDAO.findById(id);
		List<ProvinceEnum> pl = new ArrayList<ProvinceEnum>();
		if(provinceEnum.getLevel()==1){
			pl.add(provinceEnum);
		}else if(provinceEnum.getLevel()==2){
			pl.add(provinceEnum.getProvinceEnum());
		}else{
			pl.add(provinceEnum.getProvinceEnum().getProvinceEnum());
		}
		
		return pl;
	}
//============start-zhanglu===========//
 public List<ProvinceEnum> getZoneByAdmin(Integer id){
	 
	 ProvinceEnum provinceEnum=provinceEnumDAO.findById(id);
	 List<ProvinceEnum> pl=new ArrayList<ProvinceEnum>();
	 if (provinceEnum.getLevel()==2||provinceEnum.getLevel2()==2) {
		 pl.add(provinceEnum);
	 }else if(provinceEnum.getLevel()==1){
		 pl= provinceEnumDAO.findByPropertyWithValid("provinceEnum.id", id);
	 }
	 return pl;
 }
//============end===========//	


	@Override
	public void saveAdminUserByUser(AdminUser adminUser, Seller seller, String phone) {
		Users user = null;
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isvalid", true,"isValid");
		queryModel.combPreEquals("phone", phone);
		List<Users> users = dateBaseDAO.findLists(Users.class, queryModel);
		if(users != null && users.size()>0){ //存在用户
			user = users.get(0);
		}else { //不存在改用户 
			user = new Users();
			user.setPhone(phone);
			user.setName(adminUser.getLoginname());
			user.setPwd(Utility.MD5("888888"));
			user.setIsvalid(true);
			user.setScore(adminUser.getScore());
			user.setMoney(adminUser.getMoney());
			user.setCreatetime(nowTime);
			user.setLevel(adminUser.getLevel());
			
		}
		user.setLevel(65);
		userDao.saveOrUpdate(user);
		adminUser.setUsers(user); //原本对应的是粉丝Id
		adminUserDAO.saveOrUpdate(adminUser);
}
}
