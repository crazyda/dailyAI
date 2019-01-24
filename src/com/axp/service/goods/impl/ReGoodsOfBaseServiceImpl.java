package com.axp.service.goods.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.dao.ReGoodsDetailsDAO;
import com.axp.dao.ReGoodsofextendmallDao;
import com.axp.model.AdminUser;
import com.axp.model.CashshopGoodsLable;
import com.axp.model.CommodityType;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsDetails;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsOfBaseEditRecord;
import com.axp.model.ReGoodsOfChangeMall;
import com.axp.model.ReGoodsOfLocalSpecialtyMall;
import com.axp.model.ReGoodsOfLockMall;
import com.axp.model.ReGoodsOfMemberMall;
import com.axp.model.ReGoodsOfNineNineMall;
import com.axp.model.ReGoodsOfScoreMall;
import com.axp.model.ReGoodsOfSeckillMall;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.ReGoodsSnapshot;
import com.axp.model.ReGoodsStandard;
import com.axp.model.ReGoodsofextendmall;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.Seller;
import com.axp.query.PageResult;
import com.axp.service.goods.ReBaseGoodsService;
import com.axp.service.goods.ReGoodsOfBaseService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.DateUtil;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Service
public class ReGoodsOfBaseServiceImpl extends BaseServiceImpl implements ReGoodsOfBaseService {

    @Autowired
    private ReGoodsDetailsDAO reGoodsDetailsDAO;
    @Autowired
    private ReBaseGoodsService reBaseGoodsService;

    @Override
    public PageResult<ReGoodsOfBase> getGoodsForList(Integer sellerId, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord) {
        return reGoodsOfBaseDAO.getGoodsForList(sellerId, currentPage, pageSize, goodsType, searchWord);
    }

    @Override
    public Map<String, Object> doSaveOfBaseGoods(Integer id, Seller seller, String name,
            List<String> coverPic, List<Integer> typesId, List<Integer> lablesId, List<String> descriptionPics,
            String intro, String details, List<String> content, List<Integer> rightsId) {

        //返回数据；
        Map<String, Object> map = new HashMap<>();
        map.put("success", 0);

        try {
            //先保存数据到ReGoodsOfBase中；
            ReGoodsOfBase baseGoods = save(id, seller, name, coverPic, typesId, null, descriptionPics, intro, details, rightsId);

            //再保存到ReGoodsSnapshot表中；
            ReGoodsSnapshot snapshot = reGoodsSnapshotDAO.saveByBaseGoods(id, baseGoods);

            baseGoods.setSnapshotId(snapshot.getId());//最后将从数据得到的快照数据的id值设置到baseGoods中；
            
            //保存商品详情
            ReGoodsDetails reGoodsDetails;
            if (id == null) {
                reGoodsDetails = new ReGoodsDetails();
            } else {
                reGoodsDetails = reGoodsDetailsDAO.getByBaseGoods(baseGoods);
            }
            reGoodsDetails.setGoods(baseGoods);
            List<Map<String, Object>> imgList = new ArrayList<>();
            for (int i = 0; i < content.size(); i++) {
                String each = content.get(i).replaceAll("\\\\", "/");
                if (StringUtil.hasLength(each)) {
                    Map<String, Object> maps = new HashMap<>();
                    maps.put("detailType", "picture");
            		maps.put("picture", each);
            		imgList.add(maps);
                }
                
            }
            if(details != null){
        		Map<String,Object> maps = new HashMap<>();
        		maps.put("detailType", "text");
        		maps.put("text", details);
        		imgList.add(maps);
        	}
           
            reGoodsDetails.setContent(JSONObject.toJSONString(imgList).replaceAll("////", "/"));
            
            reGoodsDetails.setIsNew(1);
            reGoodsDetails.setIsValid(true);
            
            reGoodsDetailsDAO.save(reGoodsDetails);
            //返回数据；
            map.put("success", 1);
            map.put("msg", id == null ? "保存基础商品成功；" : "修改基础商品成功；");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", id == null ? "保存基础商品失败；" : "修改基础商品失败；");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
            return map;
        }
    }

    @Override
    public void delUnpassGoods(Integer mallType, Integer goodsId) throws Exception {
        ReBaseGoods reBaseGoods = reBaseGoodsService.getGoodsByMallTypeAndGoodsId(mallType, goodsId);
        reBaseGoods.setIsValid(false);
    }

    @Override
    public ReGoodsOfBase save(Integer id, Seller seller, String name, List<String> coverPic, List<Integer> typesId, List<Integer> lablesId,
                              List<String> descriptionPics, String intro, String details,List<Integer> rightsId) {
        ReGoodsOfBase baseGoods;
        if (id == null) {//保存；
            baseGoods = new ReGoodsOfBase();
            reGoodsOfBaseDAO.save(baseGoods);
        } else {//编辑；
            baseGoods = reGoodsOfBaseDAO.findById(id);
        }
        baseGoods.setName(name);

        //保存商品置顶图的五张图片；
        List<Map<String, Object>> coverList = new ArrayList<>();
        for (int i = 0; i < coverPic.size(); i++) {
            String each = coverPic.get(i).replaceAll("\\\\", "/");
            if (StringUtil.hasLength(each)) {
                Map<String, Object> map = new HashMap<>();
                map.put("imgUrl", each);
                coverList.add(map);
            }
        }
        baseGoods.setCoverPic(JSONObject.toJSONString(coverList));

        //保存商品类别；
        List<Map<String, Object>> typeList = new ArrayList<>();
        for (int i = 0; i < typesId.size(); i++) {
            if (typesId.get(i) != null && typesId.get(i) > 0) {
                Map<String, Object> map = new HashMap<>();

                //商品分类拥有两个层级，如果第二层级没有值，那么typesId指代的就是商品的一级分类，否则就是二级商品分类；
                CommodityType childType = commodityTypeDAO.findById(typesId.get(i));//二级商品分类；
                CommodityType parentType = childType.getCommodityType();//一级商品分类；
                if (parentType == null) {//没有上一级的分类，说明当前就是一级分类；
                    map.put("parentTypeId", childType.getId());
                    map.put("parentTypeName", childType.getName());
                    map.put("childTypeId", "");
                    map.put("childTypeName", "");
                } else {
                    map.put("childTypeId", childType.getId());
                    map.put("childTypeName", childType.getName());
                    map.put("parentTypeId", parentType.getId());
                    map.put("parentTypeName", parentType.getName());
                }
                typeList.add(map);
            }
        }
        baseGoods.setType(JSONObject.toJSONString(typeList));

       /* //保存商品标签；
        List<Map<String, Object>> lableList = new ArrayList<>();
        for (int i = 0; i < lablesId.size(); i++) {
            if (lablesId.get(i) != null && lablesId.get(i) > 0) {
                Map<String, Object> map = new HashMap<>();
                CashshopGoodsLable lable = cashshopGoodsLableDAO.findById(lablesId.get(i));
                map.put("lableId", lable.getId());
                map.put("lableName", lable.getName());
                lableList.add(map);
            }
        }
        baseGoods.setLables(JSONObject.toJSONString(lableList));*/

        //保存商品简述的五张图片；
        List<Map<String, Object>> imgList = new ArrayList<>();
        for (int i = 0; i < descriptionPics.size(); i++) {
        	String each = descriptionPics.get(i).replaceAll("\\\\", "/");
            if (StringUtil.hasLength(each)) {
                Map<String, Object> map = new HashMap<>();
                map.put("imgUrl", each);
                imgList.add(map);
            }
        }
        baseGoods.setDescriptionPics(JSONObject.toJSONString(imgList));

        baseGoods.setSeller(seller);
        baseGoods.setIntro(intro);
        baseGoods.setDetails(details);

        return baseGoods;
    }

    @Override
    public ReGoodsOfBase get(Integer baseGoodsId) {
        return reGoodsOfBaseDAO.findById(baseGoodsId);
    }

    @Override
    public void update(ReGoodsOfBase baseGoods) {
        reGoodsOfBaseDAO.update(baseGoods);
    }

    @SuppressWarnings("all")
    @Override
    public PageResult getGoodsListInMall(Seller seller, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord) {

        PageResult result;//返回值；

        //根据商城类型选择查询方式；
        if (goodsType == ReGoodsOfBase.sellerMall) {
            result = reGoodsOfSellerMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord, goodsType);
        } else if (goodsType == ReGoodsOfBase.scoreMall) {
            result = reGoodsOfScoreMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord);
        } else if (goodsType == ReGoodsOfBase.seckillMall) {
            result = reGoodsOfSeckillMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord);
        } else if (goodsType == ReGoodsOfBase.localSpecialtyMall) {
            result = reGoodsOfLocalSpecialtyMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord);
        } else if (goodsType == ReGoodsOfBase.nineNineMall) {
            result = reGoodsOfNineNineMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord);
        } else if (goodsType == ReGoodsOfBase.memberMall) {
            result = reGoodsOfMemberMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord);
        } else if (goodsType == ReGoodsOfBase.changeMall) {
            result = reGoodsOfChangeMallDAO.getGoodsList(seller, currentPage, pageSize, searchWord,0);
        }else if(goodsType == ReGoodsOfBase.lockMall){
        	 result = reGoodsOfLockMallDao.getGoodsList(seller, currentPage, pageSize, searchWord,0);
        }else {
            result = null;
        }

        return result;
    }

    @Override
    public Map<String, Object> doUnPut(HttpServletRequest request,Integer mallId, Integer mallGoodsId) throws Exception {

        //返回值；
        Map<String, Object> map = new HashMap<>();
        map.put("success", 1);
        map.put("msg", "下架成功");
        try {
			
        	Integer adminUserId=Integer.parseInt(request.getSession().getAttribute("currentUserId").toString());
        	AdminUser adminUser = adminUserDAO.findById(adminUserId);
        
        //根据商城类型选择查询方式；
        if (mallId == ReGoodsOfBase.sellerMall) {

            //首先删除这条商品记录；
            ReGoodsOfSellerMall goods = reGoodsOfSellerMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);
            
            //下架该商品的优惠券 同时退还红包剩余金额
            reGoodsofextendmallDao.doUnPutCoupon(adminUser,goods.getGoodsOrder());

            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);
            
            //在下架的时候 拿到销量 重新上架后累加销量
            Integer salesVolume=goods.getSalesVolume()==null?0:goods.getSalesVolume();
            
            backUpBaseGoods(baseGoods.getId(),salesVolume,goods.getGoodsOrder().substring(0,3));
            
        } else if (mallId == ReGoodsOfBase.scoreMall) {
            //首先删除这条商品记录；
            ReGoodsOfScoreMall goods = reGoodsOfScoreMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);

            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);
            Integer salesVolume=goods.getSalesVolume()==null?0:goods.getSalesVolume();
            backUpBaseGoods(baseGoods.getId(),salesVolume,goods.getGoodsOrder().substring(0,4));
        } else if (mallId == ReGoodsOfBase.seckillMall) {

            //首先删除这条商品记录；
            ReGoodsOfSeckillMall goods = reGoodsOfSeckillMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);

            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);
            Integer salesVolume=goods.getSalesVolume()==null?0:goods.getSalesVolume();
            backUpBaseGoods(baseGoods.getId(),salesVolume,goods.getGoodsOrder().substring(0,4));
        } else if (mallId == ReGoodsOfBase.localSpecialtyMall) {

            //首先删除这条商品记录；
            ReGoodsOfLocalSpecialtyMall goods = reGoodsOfLocalSpecialtyMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);
            
            //下架该商品的优惠券
            reGoodsofextendmallDao.doUnPutCoupon(adminUser,goods.getGoodsOrder());

            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);
            Integer salesVolume=goods.getSalesVolume()==null?0:goods.getSalesVolume();
            backUpBaseGoods(baseGoods.getId(),salesVolume,goods.getGoodsOrder().substring(0,4));
        } else if (mallId == ReGoodsOfBase.nineNineMall) {

            //首先删除这条商品记录；
            ReGoodsOfNineNineMall goods = reGoodsOfNineNineMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);

            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);

        } else if (mallId == ReGoodsOfBase.memberMall) {

            //首先删除这条商品记录；
            ReGoodsOfMemberMall goods = reGoodsOfMemberMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);

            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);

        }else if (mallId == ReGoodsOfBase.changeMall) {

            //首先删除这条商品记录；
            ReGoodsOfChangeMall goods = reGoodsOfChangeMallDAO.findById(mallGoodsId);
            goods.setIsValid(false);
            
            //其次更改基础商品中的launchMal属性；
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);
            
        }else if(mallId == ReGoodsOfBase.lockMall){
        	//首先删除这条商品记录；
            ReGoodsOfLockMall goods = reGoodsOfLockMallDao.findById(mallGoodsId);
            //如果商品是还没有开奖的, 就要结算所有的积分退回给用户
            if(goods.getCommodityType().getId() == 265){ //积分夺宝
            	Integer openType = goods.getOpenType();
            	if(openType==1){ //设置的到时间开奖
            		if(goods.getEndTime().compareTo(new Date())>0){
            			 map.put("success", -1);
                         map.put("msg", "未到开奖时间不能下架");
                         return map;
            		}
            	}else if(openType==2){ //设置的到人数开奖
            		QueryModel model = new QueryModel();
            		model.clearQuery();
            		model.combPreEquals("isValid", true);
            		model.combPreEquals("goodsId", goods.getBaseGoodsId());
            		int count = dateBaseDao.findCount(ReGoodsorderItem.class, model);
            		if(count < goods.getPeopleNum()){
            			map.put("success", -1);
            			map.put("msg", "活动进行中,不能下架");
            			 return map;
            		}
            	}
            	
            }
        	//其次更改基础商品中的launchMal属性；
            goods.setIsValid(false);
            ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goods.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall(), mallId, false);
            baseGoods.setLaunchMall(changeLaunchMall);
            
            
            
            
        }
        } catch (Exception e) {
        	  map.put("success", -1);
              map.put("msg", "下架失败");
              TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
              e.printStackTrace();
		}
     
        return map;
    }

    
    /**
     * 备份baseGoods 审核回显使用
     */
	@Override
	public void backUpBaseGoods(Integer goodsId,int salesvolume,String mall) {
		if(goodsId==null){
			return ;
		}
		ReGoodsOfBaseEditRecord record=null;
		ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(goodsId);
		
		//两个参数
		
		//在商品在某个商场下架的时候 记住其销量  然后在重新投放的时候把销量回显
		
		
		List<ReGoodsOfBaseEditRecord> editRecord = reGoodsOfBaseEditRecordDao.findByProperty("reGoodsOfBase.id",goodsId);
		if(editRecord.size()>0){
			record=editRecord.get(0);
		}else{
			record=new ReGoodsOfBaseEditRecord();
		}
		if(salesvolume>0&&mall!=null){  //设置对应销量 日后该商场重新上架 累加销量
			  switch (mall) {
	            case ReBaseGoods.SellerMall:
	            	record.setSellerMallSalesVolume(salesvolume);
	            	break;
	            case ReBaseGoods.ScoreMall:
	            	record.setScoreMallSalesVolume(salesvolume);
	            	break;
	            case ReBaseGoods.SeckillMall:
	                record.setSeckillMallSalesVolume(salesvolume);
	                break;
	            case ReBaseGoods.LocalSpecialtyMall:
	                record.setLocalSpecialtyMallSalesVolume(salesvolume);
	                break;
	            default:
	            	record.setSalesvolume(salesvolume);
	                break;
	        }
			  record.setReGoodsOfBase(baseGoods);
			  reGoodsOfBaseEditRecordDao.saveOrUpdate(record);
			  return;
		}
		record.setReGoodsOfBase(baseGoods);
		record.setIsValid(baseGoods.getIsValid());
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		record.setSign(baseGoods.getSign());
		record.setName(baseGoods.getName());
		record.setCoverPic(baseGoods.getCoverPic());
		record.setType(baseGoods.getType());
		record.setLables(baseGoods.getLables());
		record.setDescriptionPics(baseGoods.getDescriptionPics());
		record.setSeller(baseGoods.getSeller());
		record.setIntro(baseGoods.getIntro());
		record.setDetails(baseGoods.getDetails());
		record.setSnapshotId(baseGoods.getSnapshotId());
		record.setCheckStatus(baseGoods.getCheckStatus());
		record.setLaunchMall(baseGoods.getLaunchMall());
		//record.setSalesvolume(reGoodsOfBaseDAO.getSalesvolume(goodsId));
		reGoodsOfBaseEditRecordDao.saveOrUpdate(record);
	}

	@Override
	public void setStandardDetails(JSONArray standardArray, Integer adminUserId, ReBaseGoods reBaseGoods) {
		List<Map<String, Object>> standardDetailsList=new ArrayList<>();
		for (int i = 0; i < standardArray.size(); i++) {
			Map<String, Object> standardMap=new HashMap<>();
			JSONObject standard = standardArray.getJSONObject(i);
			String specId = standard.getString("specId");
			ReGoodsStandard goodsStandard=null;
			goodsStandard=reGoodsStandardDAO.findById(Integer.parseInt(specId));
			
			goodsStandard.setAdminUserId(adminUserId);
			goodsStandard.setIsValid(true);
			goodsStandard.setCreateTime(new Timestamp(System.currentTimeMillis()));
			goodsStandard.setName(standard.getString("specStr"));
			goodsStandard.setParentStandardId(StringUtil.parentStandardId);
			reGoodsStandardDAO.saveOrUpdate(goodsStandard);
			
			if(i==0){
				reBaseGoods.setPrice(standard.getDouble("price"));  //好像是默认取第一个
			}
			standardMap.put("id1", goodsStandard.getId());
			standardMap.put("name1", goodsStandard.getName().trim());
			standardMap.put("price", standard.getDouble("price"));
			standardMap.put("repertory", standard.getInteger("stock")); 
			standardMap.put("redPaper", 0);
			standardMap.put("score", 0);
			standardDetailsList.add(standardMap);
		}
		
		if(!reBaseGoods.getIsNoStandard()){
			Map<String, Object> parentStandardMap=new HashMap<>();
			List<Map<String, Object>> parentStandList=new ArrayList<>();
			parentStandardMap.put("standardId",StringUtil.parentStandardId );
			parentStandardMap.put("standardName",StringUtil.parentStandardName);
			parentStandList.add(parentStandardMap);
			reBaseGoods.setStandardDetails(1, "操作成功",parentStandList, standardDetailsList);
		}
		
	}
}
