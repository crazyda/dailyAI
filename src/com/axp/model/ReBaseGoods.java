package com.axp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.service.goods.ReGoodsOfBaseService;
import com.axp.service.goods.ReGoodsSnapshotService;
import com.axp.util.StringUtil;

/**
 * 六个商城商品的基础表，包含商城都需要的通用内容；
 *
 * @author Administrator
 */
public class ReBaseGoods extends ReBaseDomain {
	
    public String getRightsProtect() {
		return rightsProtect;
	}

	public void setRightsProtect(String rightsProtect) {
		this.rightsProtect = rightsProtect;
	}

	private Integer baseGoodsId;//对应的基础商品id值；
    private ReGoodsSnapshot snapshotGoods;//此商城商品对应的基础商品的快照对象；
    private String standardDetails;//用户记录商品规格详细内容的json字符串；

    private Double displayPrice = 0d;//显示售价，即原价，或市场价；
    private Integer defaultRepertory = 0;//默认库存；
    private Double price = 0d;//售价，商品的真实售价，并且，这个售价是用来展示商品时候的售价，也就是所有规格中最便宜的那个商品规格售价；
    private Integer score = 0;//积分，商品所需要的积分；
    private Double redPaper = 0d;//商品需要的红包金额；

    private Integer transportationType = 1;//运输方式；
    private Double transportationPrice = 0d;//运输费用（如果需要运输费用的话）；

    private Timestamp addedTime; //上架时间
    private Timestamp shelvesTime;//下架时间
    private Timestamp endTime; //活动结束时间
    private String goodsOrder;//商品序号；形成规则就是商城前缀+商品id；
    private Integer commentCount = 0;//评论总数；
    private Integer salesVolume = 0;//销售总量；
    private Boolean isChecked = false;//审核状态；（false：为审核，true：审核通过，null：审核不通过）
    private String checkDesc;//审核描述；

    private Boolean isNoStandard = false;//无商品规格；
    private Integer noStandardRepertory = 0;//无商品规格时的库存；
    private Double noStandardPrice = 0d;//无商品规格时的真实售价；
    private Integer noStandardScore = 0;//无商品规格时的红包可抵扣；

    private Integer stickyNum = 0;//置顶号
    

    private String rightsProtect; //权益保障
    private ReGoodsOfSellerMall reGoodsOfSellerMall;//周边店铺
    private Integer exchangeArea;
    private Boolean isNotChange;
    
    public Boolean getIsNotChange() {
		return isNotChange;
	}

	public void setIsNotChange(Boolean isNotChange) {
		this.isNotChange = isNotChange;
	}

	/**
     * 运输方式
     */
    public static final Integer bao_you = 1;//包邮
    public static final Integer man_duo_shao_bao_you = 2;//满多少包邮；
    public static final Integer shang_men_zi_qu = 3;//上门自取；
    public static final Integer dao_dian_xiao_fei = 4;//到店消费；
    public static final Integer shuang_fang_xie_shang=5; // 双方协商 
    /**
     * 商城前缀；.
     */
    public static final String SellerMall = "sem";
    public static final String ScoreMall = "scm";
    public static final String SeckillMall = "skm";
    public static final String LocalSpecialtyMall = "lsm";
    public static final String NineNineMall = "nnm";
    public static final String MemberMall = "mem";
    public static final String ChangeMall = "cha";
    public static final String teamMall="tim";
    public static final String LockMall = "ldm";
    //=============有关商品详细规格的一些方法开始=========

    /**
     * 获取该商品的所有规格明细，及其每条规格明细对应的库存，价格，积分，红包信息；
     *
     * @return 返回的结果值是一个List集合；
     */
    public List<Map<String, Object>> getStandardDetailsList() {
    	
    	 //封装成List；
        List<Map<String, Object>> list = new ArrayList<>();
    	
    	try {
			
		
        //检查json的完整性；
        JSONObject parseObject = JSONObject.parseObject(this.standardDetails);
        Integer success = parseObject.getInteger("success");
        if (success != 1) {
            return null;
        }

        //获取需要解析的那个数组；
        JSONObject dataObject = parseObject.getJSONObject("data");
        JSONArray standardDetailsArray = dataObject.getJSONArray("standardDetails");

       
        for (int i = 0; i < standardDetailsArray.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            JSONObject each = standardDetailsArray.getJSONObject(i);
            map.put("id1", each.getString("id1"));
            map.put("name1", each.getString("name1"));
            map.put("id2", each.getString("id2"));
            map.put("name2", each.getString("name2"));
            map.put("id3", each.getString("id3"));
            map.put("name3", each.getString("name3"));
            map.put("repertory", each.getString("repertory"));
            map.put("price", each.getString("price"));
            map.put("redPaper", each.getString("redPaper"));
            map.put("score", each.getString("score"));
            list.add(map);
        }
        
    	} catch (Exception e) {
			return null;
		}

        return list;
    }
    
	public ReGoodsOfSellerMall getReGoodsOfSellerMall() {
		return reGoodsOfSellerMall;
	}

	public void setReGoodsOfSellerMall(ReGoodsOfSellerMall reGoodsOfSellerMall) {
		this.reGoodsOfSellerMall = reGoodsOfSellerMall;
	}
    
    /**
     * 通过商城字符串表示获取商城前序标识；
     * 说明：
     * 商城有Integer的表示在ReGoodsOfBase类中；
     * 还有字符串表示，在ReBaseGoods类中；
     *
     * @param s
     * @return
     */
    public static String getMallType(Integer mallType) {
        if (mallType == null) {
            return SellerMall;
        }
        int s = mallType.intValue();
        if (s == ReGoodsOfBase.sellerMall) {
            return SellerMall;
        } else if (s == ReGoodsOfBase.scoreMall) {
            return ScoreMall;
        } else if (s == ReGoodsOfBase.seckillMall) {
            return SeckillMall;
        } else if (s == ReGoodsOfBase.localSpecialtyMall) {
            return LocalSpecialtyMall;

        } else if (s == ReGoodsOfBase.nineNineMall) {
            return NineNineMall;

        } else if (s == ReGoodsOfBase.memberMall) {
            return MemberMall;
        }
        return "";
    }

    /**
     * 通过goodsOrder获取商城的名称；
     */
    public static String getMallNameByGoodsOrder(String goodsOrder) {
        if (StringUtil.isEmpty(goodsOrder)) {
            return null;
        }
        if (goodsOrder.startsWith(SellerMall)) {
            return "ReGoodsOfSellerMall";
        } else if (goodsOrder.startsWith(ScoreMall)) {
            return "ReGoodsOfScoreMall";
        } else if (goodsOrder.startsWith(SeckillMall)) {
            return "ReGoodsOfSeckillMall";
        } else if (goodsOrder.startsWith(LocalSpecialtyMall)) {
            return "ReGoodsOfLocalSpecialtyMall";
        } else if (goodsOrder.startsWith(NineNineMall)) {
            return "ReGoodsOfNineNineMall";
        } else if (goodsOrder.startsWith(MemberMall)) {
            return "ReGoodsOfMemberMall";
        }
        return null;
    }
    
    
    /**
     * 通过商城字符串表示获取商城Integer标识；
     * 说明：
     * 商城有Integer的表示在ReGoodsOfBase类中；
     * 还有字符串表示，在ReBaseGoods类中；
     *
     * @param s
     * @return
     */
    public static Integer getMallTypeId(String s) {
        if (SellerMall.equals(s)) {
            return ReGoodsOfBase.sellerMall;
        } else if (ScoreMall.equals(s)) {
            return ReGoodsOfBase.scoreMall;

        } else if (SeckillMall.equals(s)) {
            return ReGoodsOfBase.seckillMall;

        } else if (LocalSpecialtyMall.equals(s)) {
            return ReGoodsOfBase.localSpecialtyMall;

        } else if (NineNineMall.equals(s)) {
            return ReGoodsOfBase.nineNineMall;

        } else if (MemberMall.equals(s)) {
            return ReGoodsOfBase.memberMall;
        }
        return 0;
    }

    /**
     * 获取当前商品对应的一级商品规格的详细信息；
     *
     * @return
     */
    public List<Map<String, Object>> getParentStandardList() {
    	 //封装成List；
        List<Map<String, Object>> list = new ArrayList<>();
    	try {
        //检查json的完整性；
        JSONObject parseObject = JSONObject.parseObject(this.standardDetails);
        Integer success = parseObject.getInteger("success");
        if (success != 1) {
            return null;
        }

        //获取需要解析的那个数组；
        JSONObject dataObject = parseObject.getJSONObject("data");
        JSONArray standardDetailsArray = dataObject.getJSONArray("parentStandard");

       
        for (int i = 0; i < standardDetailsArray.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            JSONObject each = standardDetailsArray.getJSONObject(i);
            map.put("standardId", each.getString("standardId"));
            map.put("standardName", each.getString("standardName"));
            list.add(map);
        }

     
    	} catch (Exception e) {
			return null;
		}
    	   return list;
    }

    /**
     * 根绝二级规格的id去寻找该条二级规格的全部信息，如果二级商品规格数量不足，使用null填充；
     *
     * @param id1 第一个二级商品规格的id；
     * @param id2 第二个二级商品规格的id；
     * @param id3 第三个二级商品规格的id；
     * @return
     */
    public Map<String, Object> getStandardDetailByIds(Integer id1, Integer id2, Integer id3) {

        //检查参数，并确定，该条明细由几个二级商品规格组成；
        if (id1 == null) {
            return null;
        }
        int idLength = 1;
        if (id2 != null && id2 != 0) {
            idLength++;
            if (id3 != null && id3 != 0) {
                idLength++;
            }
        }

        //寻找匹配元素并返回；
        List<Map<String, Object>> standardDetailsList = getStandardDetailsList();
        for (int i = 0; i < standardDetailsList.size(); i++) {
            Map<String, Object> eachMap = standardDetailsList.get(i);
            //根据传入的参数长度进行匹配；
            if (idLength == 1) {
                Integer i1 = Integer.parseInt((String) eachMap.get("id1"));
                if (id1 == i1) {
                    return eachMap;
                }
            } else if (idLength == 2) {
                Integer i1 = Integer.parseInt((String) eachMap.get("id1"));
                Integer i2 = Integer.parseInt((String) eachMap.get("id2"));
                if (id1 == i1 && id2 == i2) {
                    return eachMap;
                }
            } else if (idLength == 3) {
                Integer i1 = Integer.parseInt((String) eachMap.get("id1"));
                Integer i2 = Integer.parseInt((String) eachMap.get("id2"));
                Integer i3 = Integer.parseInt((String) eachMap.get("id3"));
                if (id1 == i1 && id2 == i2 && id3 == i3) {
                    return eachMap;
                }
            }
        }
        return null;
    }


	/**
     * 根据二级商品规格的id去寻找对应的库存信息，如果二级商品规格数量不足，使用null填充；
     *
     * @param id1 第一个二级商品规格的id；
     * @param id2 第二个二级商品规格的id；
     * @param id3 第三个二级商品规格的id；
     * @return
     */
    public Integer getRepertoryByIds(Integer id1, Integer id2, Integer id3) {
        Map<String, Object> detailMap = getStandardDetailByIds(id1, id2, id3);
        if (detailMap != null) {
            return Integer.parseInt((String) detailMap.get("repertory"));
        }
        return null;
    }

    /**
     * 编辑商品具体商品规格明细的库存；
     * 返回值：
     * 1：表示一切正常；
     * 2：表示库存不足；
     *
     * @param id1
     * @param id2
     * @param id3
     * @param number 修改数量；
     * @param isAdd  null：表示直接更改值；true：在原有值上增加；false：在原有值上减少；
     * @return
     */
    public Integer editGoodsRepertory(Integer id1, Integer id2, Integer id3, Integer number, Boolean isAdd) {

        //检查参数；
        int idLength = 1;
        if (id2 != null && id2 != 0) {
            idLength++;
            if (id3 != null && id3 != 0) {
                idLength++;
            }
        }
        if (number == null || number < 1) {
            number = 0;
        }

        //一级商品规格信息；
        List<Map<String, Object>> parentStandardList = getParentStandardList();
        //商品的具体规格对应的明细信息；
        List<Map<String, Object>> standardDetailsList = getStandardDetailsList();

        //对standardDetailsList进行遍历，找出那条需要修改的数据进行编辑操作；
        if (parentStandardList != null && standardDetailsList != null) {
            for (int i = 0; i < standardDetailsList.size(); i++) {
                Map<String, Object> eachDetails = standardDetailsList.get(i);
                //根据传入的参数长度进行匹配；
                if (idLength == 1) {
                    Integer i1 = Integer.parseInt((String) eachDetails.get("id1"));

                    if (id1.equals(i1)) {
                        //现有库存量；
                        int repertory = Integer.parseInt((String) eachDetails.get("repertory"));
                        //判断是增加还是减少库存；
                        if (isAdd == null) {
                            eachDetails.put("repertory", number);
                        } else {
                            if (isAdd) {
                                eachDetails.put("repertory", number + repertory);
                            } else {
                                //如果库存不足，则返回数据提醒；
                                if (repertory < number) {
                                    return 2;
                                }
                                //扣减库存；
                                eachDetails.put("repertory", repertory - number);
                            }
                        }
                        break;
                    }
                } else if (idLength == 2) {
                    Integer i1 = Integer.parseInt((String) eachDetails.get("id1"));
                    Integer i2 = Integer.parseInt((String) eachDetails.get("id2"));

                    if (id1.equals(i1) && id2.equals(i2)) {
                        //现有库存量；
                        int repertory = Integer.parseInt((String) eachDetails.get("repertory"));
                        //判断是增加还是减少库存；
                        if (isAdd == null) {
                            eachDetails.put("repertory", number);
                        } else {
                            if (isAdd) {
                                eachDetails.put("repertory", number + repertory);
                            } else {
                                //如果库存不足，则返回数据提醒；
                                if (repertory < number) {
                                    return 2;
                                }
                                //扣减库存；
                                eachDetails.put("repertory", repertory - number);
                            }
                        }
                        break;
                    }
                } else if (idLength == 3) {
                    Integer i1 = Integer.parseInt((String) eachDetails.get("id1"));
                    Integer i2 = Integer.parseInt((String) eachDetails.get("id2"));
                    Integer i3 = Integer.parseInt((String) eachDetails.get("id3"));

                    if (id1.equals(i1) && id2.equals(i2) && id3.equals(i3)) {
                        //现有库存量；
                        int repertory = Integer.parseInt((String) eachDetails.get("repertory"));
                        //判断是增加还是减少库存；
                        if (isAdd == null) {
                            eachDetails.put("repertory", number);
                        } else {
                            if (isAdd) {
                                eachDetails.put("repertory", number + repertory);
                            } else {
                                //如果库存不足，则返回数据提醒；
                                if (repertory < number) {
                                    return 2;
                                }
                                //扣减库存；
                                eachDetails.put("repertory", repertory - number);
                            }
                        }
                        break;
                    }
                }
            }
        }

        //将更改后的数据重新设置回去；
        setStandardDetails(1, "一切正常", parentStandardList, standardDetailsList);

        return 1;
    }

    /**
     * 批量修改库存；
     *
     * @param idList1
     * @param idList2
     * @param idList3
     */
    public void editGoodsRepertoryForBatch(List<Integer> idList1, List<Integer> idList2, List<Integer> idList3, List<Integer> numList) throws Exception {

        //检查参数；
        if (idList1 == null || idList1.size() == 0) {
            throw new Exception("批量修改库存出错：参数检查时，第一个id集合为空或长度为0；");
        }
        if (numList == null | numList.size() == 0 || numList.size() != idList1.size()) {
            throw new Exception("批量修改库存出错：参数检查时，库存集合的长度为空或长度为0，或与第一个id集合的长度不等；");
        }

        //批量修改；
        synchronized (ReBaseGoods.class) {
            for (int i = 0; i < idList1.size(); i++) {
                Integer id1 = idList1.get(i);
                Integer id2 = (idList2 == null || idList2.size() == 0) ? null : idList2.get(i);
                Integer id3 = (idList3 == null || idList3.size() == 0) ? null : idList3.get(i);
                Integer result = editGoodsRepertory(id1, id2, id3, numList.get(i), true);
                if (result == null || result == 2) {
                    throw new Exception("批量修改库存时出错，三个id分别为" + id1 + "," + id2 + "," + id3);
                }
            }
        }
    }

    /**
     * 根据所给参数，生成json,并重新设置回去；
     *
     * @param success
     * @param msg
     * @param parentStandardList
     * @param standardDetailsList
     */
    public void setStandardDetails(Integer success, String msg, List<Map<String, Object>> parentStandardList, List<Map<String, Object>> standardDetailsList) {

        //封装到map中，并生成json；
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("msg", msg);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("parentStandard", parentStandardList);
        map2.put("standardDetails", standardDetailsList);
        map.put("data", map2);

        //重新设置standardD字段；
        standardDetails = JSONObject.toJSONString(map);
    }

    /**
     * 内部类：用来封装商品规格明细的对象；
     *
     * @author Administrator
     */
    public class StandardDetails {
        private List<Integer> parentStandardIds;//选择的一级商品规格的id值；
        private List<String> parentStandardNames;//选择的一级商品规格的name值；

        private List<Integer> place1Ids;//第一个位置的商品规格明细的id值；
        private List<String> place1Names;//第一个位置的商品规格明细的name值；

        private List<Integer> place2Ids;//第二个位置的商品规格明细的id值；
        private List<String> place2Names;//第二个位置的商品规格明细的name值；

        private List<Integer> place3Ids;//第三个位置的商品规格明细的id值；
        private List<String> place3Names;//第三个位置的商品规格明细的name值；

        private List<Integer> repertory;//库存量；
        private List<Double> price;//售价；
        private List<Double> redPaper;//红包；
        private List<Integer> score;//积分；

        //有参构造函数；
        public StandardDetails(List<Integer> parentStandardIds, List<String> parentStandardNames,
                               List<Integer> place1Ids, List<String> place1Names, List<Integer> place2Ids, List<String> place2Names, List<Integer> place3Ids,
                               List<String> place3Names, List<Integer> repertory, List<Double> price, List<Integer> score, List<Double> redPaper) {
            super();
            this.parentStandardIds = parentStandardIds;
            this.parentStandardNames = parentStandardNames;
            this.place1Ids = place1Ids;
            this.place1Names = place1Names;
            this.place2Ids = place2Ids;
            this.place2Names = place2Names;
            this.place3Ids = place3Ids;
            this.place3Names = place3Names;
            this.repertory = repertory;
            this.price = price;
            this.redPaper = redPaper;
            this.score = score;
        }

        /**
         * 获取包含所有数据的json格式的字符串；
         *
         * @return
         */
        public String getJson() {

            //最终需要返回的数据；

            Map<String, Object> map = new HashMap<>();
            map.put("success", 0);

            //检查参数；
            int size = repertory.size();
            if (size < 0 ||
                    parentStandardIds.size() != parentStandardNames.size() ||
                    place1Ids.size() != size ||
                    place1Names.size() != size) {
                map.put("msg", "参数异常");
                return JSONObject.toJSONString(map);
            }

            try {
                //装载所有的规格信息的data；
                Map<String, Object> dataMap = new HashMap<>();

                //将这个商品拥有的一级商品规格放到data的parentStandard中；
                List<Map<String, Object>> parentStandardList = new ArrayList<>();
                for (int i = 0; i < parentStandardIds.size(); i++) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("standardId", parentStandardIds.get(i));
                    m.put("standardName", parentStandardNames.get(i));
                    parentStandardList.add(m);
                }
                dataMap.put("parentStandard", parentStandardList);

                //将这个商品拥有的所有规格明细，放到data中standardDetails中；
                List<Map<String, Object>> standardDetailsList = new ArrayList<>();
                for (int i = 0; i < repertory.size(); i++) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("id1", place1Ids.get(i));
                    m.put("name1", place1Names.get(i));

                    if (place2Ids != null && place2Ids.size() > 0) {
                        m.put("id2", place2Ids.get(i));
                        m.put("name2", place2Names.get(i));
                    }

                    if (place3Ids != null && place3Ids.size() > 0) {
                        m.put("id3", place3Ids.get(i));
                        m.put("name3", place3Names.get(i));
                    }
                    m.put("repertory", repertory.get(i));

                    if (price != null && price.size() > 0) {//如果有价格就添加价格，没有就设置为0；
                        m.put("price", price.get(i));
                    } else {
                        m.put("price", 0);
                    }

                    if (score != null && score.size() > 0) {//如果有积分就添加积分，没有就设置为0；
                        m.put("score", score.get(i));
                    } else {
                        m.put("score", 0);
                    }

                    if (redPaper != null && redPaper.size() > 0) {//如果有红包就添加红包，如果没有就设置为0；
                        m.put("redPaper", redPaper.get(i));
                    } else {
                        m.put("redPaper", 0);
                    }

                    standardDetailsList.add(m);
                }
                dataMap.put("standardDetails", standardDetailsList);

                map.put("data", dataMap);
                map.put("success", 1);
                map.put("msg", "操作成功");
                return JSONObject.toJSONString(map);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("msg", "生成json过程中抛出异常；");
                return JSONObject.toJSONString(map);
            }
        }

        //===========getAndSetter===========
        public List<Integer> getParentStandardIds() {
            return parentStandardIds;
        }

        public void setParentStandardIds(List<Integer> parentStandardIds) {
            this.parentStandardIds = parentStandardIds;
        }

        public List<String> getParentStandardNames() {
            return parentStandardNames;
        }

        public void setParentStandardNames(List<String> parentStandardNames) {
            this.parentStandardNames = parentStandardNames;
        }

        public List<Integer> getPlace1Ids() {
            return place1Ids;
        }

        public void setPlace1Ids(List<Integer> place1Ids) {
            this.place1Ids = place1Ids;
        }

        public List<String> getPlace1Names() {
            return place1Names;
        }

        public void setPlace1Names(List<String> place1Names) {
            this.place1Names = place1Names;
        }

        public List<Integer> getPlace2Ids() {
            return place2Ids;
        }
        

        public void setPlace2Ids(List<Integer> place2Ids) {
            this.place2Ids = place2Ids;
        }

        public List<String> getPlace2Names() {
            return place2Names;
        }

        public void setPlace2Names(List<String> place2Names) {
            this.place2Names = place2Names;
        }

        public List<Integer> getPlace3Ids() {
            return place3Ids;
        }

        public void setPlace3Ids(List<Integer> place3Ids) {
            this.place3Ids = place3Ids;
        }

        public List<String> getPlace3Names() {
            return place3Names;
        }

        public void setPlace3Names(List<String> place3Names) {
            this.place3Names = place3Names;
        }

        public List<Integer> getRepertory() {
            return repertory;
        }

        public void setRepertory(List<Integer> repertory) {
            this.repertory = repertory;
        }

        public List<Double> getPrice() {
            return price;
        }

        public void setPrice(List<Double> price) {
            this.price = price;
        }

        public List<Double> getRedPaper() {
            return redPaper;
        }

        public void setRedPaper(List<Double> redPaper) {
            this.redPaper = redPaper;
        }

        public List<Integer> getScore() {
            return score;
        }

        public void setScore(List<Integer> score) {
            this.score = score;
        }

    }

    //=============有关商品详细规格的一些方法结束=========

    public Integer getBaseGoodsId() {
        return baseGoodsId;
    }

    public void setBaseGoodsId(Integer baseGoodsId) {
        this.baseGoodsId = baseGoodsId;
    }

    public String getStandardDetails() {
        return standardDetails;
    }

    public void setStandardDetails(String standardDetails) {
        this.standardDetails = standardDetails;
    }

    public Integer getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(Integer transportationType) {
        this.transportationType = transportationType;
    }

    public ReGoodsSnapshot getSnapshotGoods() {
        return snapshotGoods;
    }

    public void setSnapshotGoods(ReGoodsSnapshot snapshotGoods) {
        this.snapshotGoods = snapshotGoods;
    }

    public Double getTransportationPrice() {
        return transportationPrice;
    }

    public void setTransportationPrice(Double transportationPrice) {
        this.transportationPrice = transportationPrice;
    }

    public Double getDisplayPrice() {
    	if(displayPrice==null){
    		displayPrice = price;
    	}
        return displayPrice;
    }

    public void setDisplayPrice(Double displayPrice) {
        this.displayPrice = displayPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getRedPaper() {
        return redPaper;
    }

    public void setRedPaper(Double redPaper) {
        this.redPaper = redPaper;
    }

    public String getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(String goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Timestamp getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Timestamp addedTime) {
        this.addedTime = addedTime;
    }

    public Timestamp getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(Timestamp shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    public Integer getDefaultRepertory() {
        return defaultRepertory;
    }

    public void setDefaultRepertory(Integer defaultRepertory) {
        this.defaultRepertory = defaultRepertory;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public Boolean getIsNoStandard() {
        return isNoStandard;
    }

    public void setIsNoStandard(Boolean noStandard) {
        isNoStandard = noStandard;
    }

    public Integer getNoStandardRepertory() {
        return noStandardRepertory;
    }

    public void setNoStandardRepertory(Integer noStandardRepertory) {
        this.noStandardRepertory = noStandardRepertory;
    }

    public Double getNoStandardPrice() {
        return noStandardPrice;
    }

    public void setNoStandardPrice(Double noStandardPrice) {
        this.noStandardPrice = noStandardPrice;
    }

    public Integer getNoStandardScore() {
        return noStandardScore;
    }

    public void setNoStandardScore(Integer noStandardScore) {
        this.noStandardScore = noStandardScore;
    }

    public Integer getStickyNum() {
        return stickyNum;
    }

    public void setStickyNum(Integer stickyNum) {
        this.stickyNum = stickyNum;
    }
    
    

    public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getExchangeArea() {
        return exchangeArea;
    }

    public void setExchangeArea(Integer exchangeArea) {
        this.exchangeArea = exchangeArea;
    }

    /**
     * 这个内部类，用来记录所有的商品投放时的参数，防止在六个商城投放时的代码重复；
     */
    public class MallParamter {

        private HttpServletRequest request;//包含所有参数的请求对象；
        private ReGoodsOfBaseService reGoodsOfBaseService;
        private ReGoodsSnapshotService reGoodsSnapshotService;

        //和商品规格有关的一些字段；
        private List<String> detailsName3;
        private List<String> detailsName2;
        private List<String> detailsName1;
        private List<Integer> detailsValue3;
        private List<Integer> detailsValue2;
        private List<Integer> detailsValue1;
        private List<Integer> repertoryList;
        private List<Double> priceList;
        private List<Double> redPaperList;
        private List<Integer> scoreList;
        private List<Integer> firstStandardKey;
        private List<String> firstStandardName;


        //将String类型的数组转为Integer的集合；
        private List<Integer> getIngerList(String[] s) {
            if (s == null || s.length == 0) {
                return null;
            }
            List<Integer> returnList = new ArrayList<>();
            for (String each : Arrays.asList(s)) {
                try {
                    returnList.add(Integer.parseInt(each));
                } catch (Exception e) {
                    returnList.add(0);
                }
            }
            return returnList;
        }

        //将String类型的数组转为Double类型的集合；
        private List<Double> getDoubleList(String[] s) {
            if (s == null || s.length == 0) {
                return null;
            }
            List<Double> returnList = new ArrayList<>();
            for (String each : Arrays.asList(s)) {
                try {
                    returnList.add(Double.parseDouble(each));
                } catch (Exception e) {
                    returnList.add(0D);
                }
            }
            return returnList;
        }

        //构造函数；
        public MallParamter(HttpServletRequest request,
                            ReGoodsOfBaseService reGoodsOfBaseService, ReGoodsSnapshotService reGoodsSnapshotService,List<Integer> keylist,List<String> namelist) {
            this.request = request;
            this.reGoodsOfBaseService = reGoodsOfBaseService;
            this.reGoodsSnapshotService = reGoodsSnapshotService;

            //和商品规格有关的一些字段；
            detailsName3 = request.getParameterValues("detailsName3[]") == null ? null : Arrays.asList(request.getParameterValues("detailsName3[]"));
            detailsName2 = request.getParameterValues("detailsName2[]") == null ? null : Arrays.asList(request.getParameterValues("detailsName2[]"));
            detailsName1 = request.getParameterValues("detailsName1[]") == null ? null : Arrays.asList(request.getParameterValues("detailsName1[]"));
            detailsValue3 = request.getParameterValues("detailsValue3[]") == null ? null : getIngerList(request.getParameterValues("detailsValue3[]"));
            detailsValue2 = request.getParameterValues("detailsValue2[]") == null ? null : getIngerList(request.getParameterValues("detailsValue2[]"));
            detailsValue1 = request.getParameterValues("detailsValue1[]") == null ? null : getIngerList(request.getParameterValues("detailsValue1[]"));
            repertoryList = request.getParameterValues("repertory[]") == null ? null : getIngerList(request.getParameterValues("repertory[]"));
            priceList = request.getParameterValues("price[]") == null ? null : getDoubleList(request.getParameterValues("price[]"));
            redPaperList = request.getParameterValues("redPaper[]") == null ? null : getDoubleList(request.getParameterValues("redPaper[]"));
            scoreList = request.getParameterValues("score[]") == null ? null : getIngerList(request.getParameterValues("score[]"));
            if(keylist!=null && keylist.size()>0){
            	firstStandardKey = keylist;
            	firstStandardName=namelist;
            }else{
            	firstStandardKey = request.getParameterValues("firstStandardKey[]") == null ? null : getIngerList(request.getParameterValues("firstStandardKey[]"));
            	 firstStandardName = request.getParameterValues("firstStandardName[]") == null ? null : Arrays.asList(request.getParameterValues("firstStandardName[]"));
            }
           
            
        }

       
        
        
        
        
        /**
         * 以下为获取方法，就是提供出去的获取方法；
         */
        //获取继承商品id值；
        public Integer getBaseGoodsId() {
            return Integer.parseInt(request.getParameter("baseGoodsId"));
        }

        //获取基础商品对象；
        public ReGoodsOfBase getBaseGoods() {
            Integer baseGoodsId = getBaseGoodsId();
            return reGoodsOfBaseService.get(baseGoodsId);
        }

        //获取快照商品；
        public ReGoodsSnapshot getSnapshotGoods() {
            Integer snapshotId = getBaseGoods().getSnapshotId();
            ReGoodsSnapshot reGoodsSnapshot = reGoodsSnapshotService.get(snapshotId);
            return reGoodsSnapshot;
        }

        //获取商品规格详细内容；
        public String getStandardDetails() {
            if (!getIsNoStandard()) {
                ReBaseGoods.StandardDetails standardDetails = new ReBaseGoods().new StandardDetails(firstStandardKey, firstStandardName,
                        detailsValue1, detailsName1, detailsValue2, detailsName2, detailsValue3, detailsName3,
                        repertoryList, priceList, scoreList, redPaperList);
                return standardDetails.getJson();
            } else {
                return "";
            }
        }

        //获取原价；
        public Double getDisplayPrice() {
            String displayPrice = request.getParameter("displayPrice");
            try {
                if (StringUtil.isEmpty(displayPrice)) {
                    return 0d;
                }
                return Double.parseDouble(displayPrice);
            } catch (Exception e) {
                return null;
            }
        }

        //获取最低售价；
        public Double getPrice() {
            if (getIsNoStandard()) {
                return getNoStandardPrice();
            } else {
                Integer index = StringUtil.getIndexOfMinValueOfList(priceList);
                if (index != -1) {
                    return priceList.get(index);
                }
                //                return getDisplayPrice();
                return 0d;
            }
        }

        //获取最低积分；
        public Integer getScore() {
            if (scoreList != null && scoreList.size() > 0) {
                Integer index = StringUtil.getIndexOfMinValueOfList(scoreList);
                Integer r = scoreList.get(index);
                r = r == null ? 0 : r;
                return r;
            }
            return 0;
        }

        //获取最低售价对应的红包金额；
        public Double getRedPaper() {
            if (getIsNoStandard()) {
                return 0d;
            } else {
                Integer index = StringUtil.getIndexOfMinValueOfList(priceList);
                Double r = redPaperList.get(index);
                r = r == null ? 0 : r;
                return r;
            }
        }

        //获取运输方式；
        public Integer getTransportationType() {
            return Integer.parseInt(request.getParameter("transportationType"));
        }

        //获取运输费用；
        public Double getTransportationPrice() {
            try {
                if (getTransportationType() == 2) {
                    return Double.parseDouble(request.getParameter("transportationPrice"));
                }
                return 0d;
            } catch (Exception e) {
                System.out.println("输入的邮费内容不能被解析；");
                return 0d;
            }
        }
      //获取赠送积分 da
        public Integer getSendScore() {
        	Integer score = 0;
        	if(request.getParameter("score") != "" && request.getParameter("score") != null){
        		
        		score = Integer.valueOf(request.getParameter("score"));
        	}
            return score;
        }
        
        public List<Integer> getRightsProtect() {
            String[] parameterValues = request.getParameterValues("rightsId[]");
            if (parameterValues == null || parameterValues.length == 0) {
                return null;
            } else {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < parameterValues.length; i++) {
                    list.add(Integer.parseInt(parameterValues[i]));
                }
                return list;
            }
        }
        
        
        
        
        
        //获取上架时间；
        public Timestamp getAddedTime() {
            String time = request.getParameter("addedTime");
            return Timestamp.valueOf(time);
        }

        //获取下架时间；
        public Timestamp getShelvesTime() {
            String time = request.getParameter("shelvesTime");
            return Timestamp.valueOf(time);
        }

        //获取是否有商品规格；
        public Boolean getIsNoStandard() {
            return "1".equals(request.getParameter("isNoStandard"));
        }

        //获取无商品规格时的库存；
        public Integer getNoStandardRepertory() {
            String o = request.getParameter("noStandardRepertory");
            return o == null ? 0 : Integer.parseInt(o);
        }

        //获取无商品规格时的真实售价；
        public Double getNoStandardPrice() {
            String o = request.getParameter("noStandardPrice");
            return o == null ? 0.00 : Double.parseDouble(o);
        }

        //获取商品的序号；（此字段的总用在于区分商城商品是初次投放，还是进行编辑；）
        public String getGoodsOrder() {
            return request.getParameter("goodsOrder");
        }

        /**
         * 以下获取参数的方法，都是商城特有而不是公共的参数；
         */

        //商家商城-获取商品无规格时的红包可折扣；
        public Double getNoStandardRedPaper() {
            String noStandardRedPaper = request.getParameter("noStandardRedPaper");
            return StringUtils.isEmpty(noStandardRedPaper) ? 0d : Double.parseDouble(noStandardRedPaper);
        }


        //积分商城-获取商品无规格时的积分
        public Integer getNoStandardScore() {
            String o = request.getParameter("noStandardScore");
            return StringUtils.isEmpty(o) ? 0 : Integer.parseInt(o);
        }


        //获取没人限购商品数量；
        public Integer getCountLimit() {
            return Integer.parseInt(request.getParameter("countLimit"));
        }

        //获取兑换区域；
        public Integer getExchangeArea() {
            return Integer.parseInt(request.getParameter("exchangeArea"));
        }

        //获取描述内容；
        public String getDesc() {
            return request.getParameter("desc");
        }


        //秒杀商城-每人秒杀限购
        public Integer getSeckillCountLimit() {
            return Integer.parseInt(request.getParameter("seckillCountLimit"));
        }

        //秒杀商城-秒杀区域
        public Integer getSeckillArea() {
            return Integer.parseInt(request.getParameter("seckillArea"));
        }

        //秒杀商城-秒杀详情
        public String getSeckillDesc() {
            String seckillDesc = request.getParameter("seckillDesc");
            return StringUtil.hasLength(seckillDesc) ? seckillDesc : "";
        }

        //秒杀商城-时间段
        public List<Integer> getTimesList() {
            String[] parameterValues = request.getParameterValues("times");
            if (parameterValues == null || parameterValues.length == 0) {
                return null;
            } else {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < parameterValues.length; i++) {
                    list.add(Integer.parseInt(parameterValues[i]));
                }
                return list;
            }
        }


        //当地特产-99商城-获取无商品规格时的返现比例；
        public Double getCashBack() {
            String cashBack = request.getParameter("cashBack");
            if (!StringUtils.isEmpty(cashBack)) {
                return Double.parseDouble(cashBack);
            } else {
                return 0d;
            }
        }

        //当地特产-99商城-获取商品所在地；
        public String getPlaceOfProduction() {
            return request.getParameter("placeOfProduction");
        }

        //当地特产-99商城-获取商品包装
        public String getPack() {
            return request.getParameter("pack");
        }
        
        public String getWant(){
        	return request.getParameter("want");
        }
        
        public Integer getStartQuantity(){
        	String start= request.getParameter("startQuantity");
        	 if (!StringUtils.isEmpty(start)) {
                 return Integer.parseInt(start);
             } else {
                 return 0;
             }
        }
        public Integer getIsChange(){
        	String start= request.getParameter("isChange");
        	 if (!StringUtils.isEmpty(start)) {
                 return Integer.parseInt(start);
             } else {
                 return 0;
             }
        }
        
        
    }
}
