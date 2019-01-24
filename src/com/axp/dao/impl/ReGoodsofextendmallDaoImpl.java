package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import antlr.StringUtils;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.AdminuserCashpointRecordDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.ReGoodsofextendmallDao;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.AdminuserRedpaper;
import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.model.ProvinceEnum;
import com.axp.model.ReGoodsofextendmall;
import com.axp.query.PageResult;
import com.axp.service.system.SelectTagService;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
@Repository("reGoodsofextendmallDao")
public class ReGoodsofextendmallDaoImpl  extends BaseDaoImpl<ReGoodsofextendmall> implements ReGoodsofextendmallDao{

	@Autowired
 	private  DateBaseDAO  dateBaseDAO;
	@Autowired
	private SelectTagService selectTagService;
	@Autowired
	private ProvinceEnumDAO provinceEnumDAO;
	@Autowired
	private AdminuserCashpointRecordDAO adminuserCashpointRecordDAO;
	
	@Override
	public PageResult<ReGoodsofextendmall> getCheckPageresult(
			Integer currentPage, Integer pageSize,Integer currentUserId) {
		

		  	QueryModel model=new QueryModel();
		  	model.combPreEquals("isValid", true);
		  	model.combPreEquals("id", currentUserId);
		  	AdminUser adminUser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, model);
		  	model.clearQuery();
			model.combPreEquals("isValid", true);
			model.combPreEquals("isChecked", false);
			ProvinceEnum provinceEnum = adminUser.getProvinceEnum();
		  	if(adminUser.getLevel()>=65&&adminUser.getLevel()<95&&provinceEnum!=null){
		  		model.combPreEquals("mall", 1);// 周边
		  		String str=provinceEnumDAO.findLevel2ProvinceEnum(provinceEnum);
		  		if(StringUtil.hasLength(str)){
		  			model.combCondition(str);
		  		}
		  	}
		  
			Integer count = dateBaseDAO.findCount(ReGoodsofextendmall.class, model);
			model.setOrder("id desc");
			List<ReGoodsofextendmall> list=	dateBaseDAO.findPageList(ReGoodsofextendmall.class, model, (currentPage-1)*pageSize, pageSize);
			
	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

	@Override
	public PageResult<ReGoodsofextendmall> findTopPageresult(
			Integer currentPage, Integer pageSize, Integer currentUserId,
			String searchName) {
		
		QueryModel model=new QueryModel();
	  	model.combPreEquals("isValid", true);
	  	model.combPreEquals("id", currentUserId);
	  	AdminUser adminUser = (AdminUser) dateBaseDAO.findOne(AdminUser.class, model);
	  	model.clearQuery();
	  	if(StringUtil.hasLength(searchName)){
	  		model.combLike("name", searchName, QueryModel.MATCH_ALL);
	  	}
		model.combPreEquals("isValid", true);
		model.combPreEquals("isChecked", true);
		model.combCondition("validtime >= sysdate()");
		ProvinceEnum provinceEnum = adminUser.getProvinceEnum();
	  	if(adminUser.getLevel()>=65&&adminUser.getLevel()<95&&provinceEnum!=null){
	  		model.combPreEquals("mall", 1);// 周边
	  		String str=provinceEnumDAO.findLevel2ProvinceEnum(provinceEnum);
	  		if(StringUtil.hasLength(str)){
	  			model.combCondition(str);
	  		}
	  	}
	  	
		Integer count = dateBaseDAO.findCount(ReGoodsofextendmall.class, model);
		model.setOrder("id desc");
		List<ReGoodsofextendmall> list=	dateBaseDAO.findPageList(ReGoodsofextendmall.class, model, (currentPage-1)*pageSize, pageSize);

        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
		
	}
	
	/**
	 * 根据地区判断下级查询字符串
	 * @param provinceEnum
//	 * @return  combCondition(String str);
	 */
	public String findLevelProvinceEnum(ProvinceEnum provinceEnum){
		StringBuilder sb=new StringBuilder();
		if(provinceEnum!=null){
	  		if(provinceEnum.getLevel()==1){
	  			sb.append("( provinceEnum.id="+provinceEnum.getId()).
	  			append("or provinceEnum.provinceEnum.id="+provinceEnum.getId()).
	  			append("or provinceEnum.provinceEnum.provinceEnum.id="+provinceEnum.getId()).
	  			append(")");
	  		}
	  		else if(provinceEnum.getLevel()==2){
	  			sb.append("( provinceEnum.id="+provinceEnum.getId()).
	  			append("or provinceEnum.provinceEnum.id="+provinceEnum.getId()).append(")");
	  		}else if(provinceEnum.getLevel()==3){
	  			sb.append(" provinceEnum.id="+provinceEnum.getId());
	  		}
		}
  		return sb.toString();
	}

	@Override
	public void doUnPutCoupon(AdminUser adminUser,String goodsOrder) {
		
		  //判断是否有正在使用的优惠价 如果有则要先行下架
		QueryModel queryModel=new QueryModel();
		queryModel.combEquals("isValid", 1);
		queryModel.combEquals("sign", 1);
		queryModel.combEquals("goodsMall", goodsOrder);
	     List<ReGoodsofextendmall> ReGoodsofextendmallList = (List<ReGoodsofextendmall>) dateBaseDAO.findList(ReGoodsofextendmall.class, queryModel);
        if(ReGoodsofextendmallList.size()>0){
        	ReGoodsofextendmall reGoodsofextendmall = ReGoodsofextendmallList.get(0);
        	reGoodsofextendmall.setIsValid(false);
        	reGoodsofextendmall.setSign(2);
        	this.update(reGoodsofextendmall);
        	
        	if(reGoodsofextendmall.getAdminuserRedpaper()!=null&&reGoodsofextendmall.getAdminuserRedpaper().getId()>0){
        		AdminuserRedpaper adminuserRedpaper = reGoodsofextendmall.getAdminuserRedpaper();
        		double moeny=adminuserRedpaper.getSurplusMoney();
        		
        		//删除红包
        		adminuserRedpaper.setIsvalid(false); 
        		
        		AdminuserCashpointRecord record=new AdminuserCashpointRecord();
        		record.setAdminUser(adminUser);
        		record.setBeforepoint(adminUser.getMoney());
        		record.setAfterpoint(CalcUtil.add(adminUser.getMoney(), moeny));
        		record.setCashpoint(moeny);
        		record.setType(1);
        		record.setRemark("活动优惠券红包剩余钱返回");
        		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
        		record.setIsValid(true);
        		adminuserCashpointRecordDAO.save(record);
        		adminUser.setMoney(CalcUtil.add(adminUser.getMoney(), moeny));
        	}
        	dateBaseDAO.updateByHQL(" UPDATE UserCoupons set sign=2 where goodsMall='"+goodsOrder+"' and sign=1");
        }
	}
}
