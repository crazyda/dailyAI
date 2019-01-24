package com.axp.service.goods.impl;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.dao.DataBaseDao;
import com.axp.dao.ICommodityTypeDao;
import com.axp.dao.ReGoodsOfSellerMallDAO;
import com.axp.dao.ReGoodsofextendmallDao;
import com.axp.model.CommodityType;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.ReGoodsofextendmall;
import com.axp.query.PageResult;
import com.axp.service.cashShop.impl.BaseServiceImpl;
import com.axp.service.goods.ReGoodsofextendmallService;
import com.axp.util.DateUtil;

import freemarker.template.SimpleDate;
@Service("reGoodsofextendmallService")
public class ReGoodsofextendmallServiceImpl extends BaseServiceImpl implements ReGoodsofextendmallService {

	@Autowired
	ReGoodsOfSellerMallDAO reGoodsOfSellerMallDao;
	@Autowired
	ReGoodsofextendmallDao reGoodsofextendmallDao;
	@Autowired
	ICommodityTypeDao commodityTypeDao;
	@Override
	public PageResult<ReGoodsofextendmall> getCheckPageresult(
			
			Integer currentPage, Integer pageSize,Integer currentUserId) {
		return reGoodsofextendmallDao.getCheckPageresult(currentPage, pageSize,currentUserId);
	}
	
	//推广商品置顶列表
	@Override
	public PageResult<ReGoodsofextendmall> findTopPageresult(
			Integer currentPage, Integer pageSize, Integer currentUserId,
			String searchName) {
		return reGoodsofextendmallDao.findTopPageresult(currentPage, pageSize,currentUserId,searchName);
	}
	
	@Override
	public ReGoodsofextendmall getcheckGoodsById(Integer checkGoodsId) {
		return reGoodsofextendmallDao.findById(checkGoodsId);
	}
	@Override
	public Map<String, Object> doCheck(Map<String, Object> returnMamp,
			Boolean isPass, String checkDesc, Integer goodsId) throws Exception {
		ReGoodsofextendmall reGoodsofextendmall = reGoodsofextendmallDao.findById(goodsId);
		if (isPass) {
			reGoodsofextendmall.setIsChecked(true);
		}else{
			reGoodsofextendmall.setIsChecked(null);
		}
		reGoodsofextendmall.setCheckDesc(checkDesc);
		reGoodsofextendmallDao.saveOrUpdate(reGoodsofextendmall);
        returnMamp.put("success", true);
        returnMamp.put("message", "操作完成；");
        return returnMamp;
	}
	
	//推广商品置顶
	@Override
	public void extendMallTop(Integer id) {
			
		ReGoodsofextendmall extendmall = reGoodsofextendmallDao.findByIdValid(id);
		if(extendmall.getIsTop()){
			extendmall.setIsTop(false);
		}else{
			extendmall.setIsTop(true);
		}
		reGoodsofextendmallDao.update(extendmall);
	}
	

	@Override
	public void rejectExtendMallTop(Integer id) {
		ReGoodsofextendmall extendmall = reGoodsofextendmallDao.findByIdValid(id);
		extendmall.setIsChecked(false);
		reGoodsofextendmallDao.update(extendmall);
	}

	@Override
	public void doSavePut(HttpServletRequest request) throws Exception {
		String price= request.getParameter("price");//券价
		String rewardFee = request.getParameter("rewardFee");//推广费用
		String validityTime = request.getParameter("validityTime");//有效期
		String region = request.getParameter("region");//区域
		String stock = request.getParameter("stock");//发券数量
		
		String goodsOrder=request.getParameter("goodsOrder");
		String goodsId = goodsOrder.substring(3,goodsOrder.length());
		
		
		ReGoodsofextendmall goodsofextendmall = null;
		ReGoodsOfSellerMall goodsOfSellerMall = null;
		CommodityType commodityType = null;
		
		goodsofextendmall = new ReGoodsofextendmall();
		goodsOfSellerMall = reGoodsOfSellerMallDao.findById(Integer.parseInt(goodsId));
		if (goodsOfSellerMall!=null) {
			Date date = new Date();
			if(validityTime.equals("1")){
				date=DateUtil.addDay2Date(3, new Date());
			}else if(validityTime.equals("2")){
				date=DateUtil.addDay2Date(7, new Date());
			}else if(validityTime.equals("3")){
				date=DateUtil.addDay2Date(15, new Date());
			}else if(validityTime.equals("4")){
				date=DateUtil.addDay2Date(30, new Date());
			}else if(validityTime.equals("5")){
				date=DateUtil.addDay2Date(90, new Date());
			}
			goodsofextendmall.setValidtime(new Timestamp(date.getTime()));
			
			goodsofextendmall.setTicketprice(Double.valueOf(price));
			goodsofextendmall.setCommission(Double.valueOf(rewardFee));
			goodsofextendmall.setTicketnum(Integer.parseInt(stock));
			goodsofextendmall.setDisplay(Integer.parseInt(region));
			goodsofextendmall.setName(goodsOfSellerMall.getSnapshotGoods().getName());
			goodsofextendmall.setSeller(goodsOfSellerMall.getSnapshotGoods().getSeller());
			goodsofextendmall.setCoverPic(goodsOfSellerMall.getSnapshotGoods().getCoverPic());
			goodsofextendmall.setIsActivity(0);
			goodsofextendmall.setIsValid(true);
			goodsofextendmall.setMall(1);
			goodsofextendmall.setMallId(Integer.valueOf(goodsId));
			goodsofextendmall.setGoodsMall(goodsOrder);
			goodsofextendmall.setSellerMallId(Integer.valueOf(goodsId));
			goodsofextendmall.setIsChecked(false);
			goodsofextendmall.setRightsProtect(goodsOfSellerMall.getRightsProtect());
			String type = goodsOfSellerMall.getSnapshotGoods().getType();
			JSONObject jsonObject = JSONObject.parseObject(type);
			for (int i = 0; i < jsonObject.size(); i++) {
				String id = jsonObject.getString("parentTypeId");
				commodityType = commodityTypeDao.findById(Integer.parseInt(id));
				goodsofextendmall.setCommodityType(commodityType);
			}
			goodsofextendmall.setCoupons(0);
			goodsofextendmall.setSign(1);
			goodsofextendmall.setLables(goodsOfSellerMall.getSnapshotGoods().getLables());
			goodsofextendmall.setType(goodsOfSellerMall.getSnapshotGoods().getType());
			goodsofextendmall.setCreateTime(new Timestamp(System.currentTimeMillis()));
			goodsofextendmall.setPrice(goodsOfSellerMall.getPrice());
			goodsofextendmall.setIsTop(false);
			goodsofextendmall.setIsRed(0);
			goodsofextendmall.setProvinceEnum(goodsOfSellerMall.getSnapshotGoods().getSeller().getProvinceEnum());
			goodsofextendmall.setDescriptionPics(goodsOfSellerMall.getSnapshotGoods().getDescriptionPics());
			reGoodsofextendmallDao.saveOrUpdate(goodsofextendmall);
		}
		
		
	}





}
