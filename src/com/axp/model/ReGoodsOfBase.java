package com.axp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 重构项目的基础商品表；
 * 用来记录基础的商品数据，就是在投放各个商城前的商品；
 *
 * @author Administrator
 */
public class ReGoodsOfBase extends ReBaseDomain {

    private String sign = UUID.randomUUID().toString();//商品的唯一编号；
    private String name;//商品名称；

    private String coverPic;//商品的封面图片；
    private String descriptionPics;//商品简述图；1，最多五张；2，使用“&&&”符号隔开，拼成字符串保存，不关联任何表；

    private String type;//商品的类别；1，商品的类别是由我们定好的，商家不能自定义类别；2，最多可以选择三个商品类别；3，使用json的格式记录商品的类别；
    private String lables;//商品的标签；1，标签也是预先定义好的；2，同样使用json的格式记录标签数据；
    private Seller seller;//商品对应的商家id值；//商品对应的商家id值；
    private String intro;//商品的描述文字；
    private String details;//商品的详细介绍内容；
    private Integer snapshotId;//此商品目前对应的最新的快照区域的商品id值；
    private Integer checkStatus = 0;//审核状态；0：表示未审核，1：审核通过；2：审核未通过；
    private String launchMall = "000000000";//该商品投放到哪个商城；使用六个字符的组成的字符串表示；

    private String coverPicOne = "";
    private String lablesChar;

    //==============针对投放商城的一些方法======================================================================================
    public static final Integer sellerMall = 1;//商家商城
    public static final Integer scoreMall = 2;//积分商城
    public static final Integer seckillMall = 3;//秒杀商城
    public static final Integer localSpecialtyMall = 4;//总部商城
    public static final Integer nineNineMall = 5;//99特惠商城
    public static final Integer memberMall = 6;//会员免单商城
    public static final Integer changeMall = 7;// 换货商城
    public static final Integer teamMall = 8;//拼团商城
    public static final Integer couponMall = 9;//普通优惠券商城
    public static final Integer lockMall = 8;//积分抽奖

    /**
     * 根据商城的编号，获取商城的名称；
     *
     * @param mallId 商城的编号值；
     * @return
     */
    public String getMallName(Integer mallId) {
        switch (mallId) {
            case 1:
                return "周边店铺";
            case 2:
                return "积分兑换";
            case 3:
                return "限时秒杀";
            case 4:
                return "总部商城";
            case 5:
                return "99特惠";
            case 6:
                return "免单专区";
            case 7:
                return "换货专区";
            case 11:
            	return "游戏专区";
            default:
                return "未知参数";
        }
    }

    /**
     * 上传到某个商城，或者从某个商城下架；
     *
     * @param mall     商城对应的值，就是定义的六个值；
     * @param isLaunch 是否是上架，若为否，则是下架，不填默认为true，即上架；
     * @throws Exception
     */
    public String changeLaunchMall(String launchMall, Integer mall, Boolean isLaunch) throws Exception {
        if (mall > 9 || mall < 1) {//检查是否超出定义界限；
            throw new Exception("您输入的商城不在系统定义的商城范围中");
        }
        if (isLaunch == null) {//isLaunch默认值为True；
            isLaunch = true;
        }
        byte[] bs = launchMall.getBytes();//更改字段中对应位置的状态值；
        if (isLaunch) {//商品上架；
            bs[mall - 1] = '1';
        } else {//商品下架；
            bs[mall - 1] = '0';
        }
        return new String(bs);
    }

    /**
     * 获取该商品已在哪些商城投放，返回这些商城的编号值；
     *
     * @return
     */
    public List<Integer> getLaunchMalls() {
        List<Integer> list = new ArrayList<>();

        //将状态为1，即已上架的商城,返回编号；
        byte[] bytes = this.launchMall.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == '1') {
                list.add(i + 1);
            }
        }
        return list;
    }

    public String getLaunchMallsName() {
        List<Integer> list = getLaunchMalls();
        StringBuilder sb = new StringBuilder(100);

        int size = list.size();
        if (size == 0) {
            return "暂无上传的商城；";
        }
        for (int i = 0; i < size; i++) {
            if (list.get(i) == sellerMall) {
                sb.append("周边店铺");
            } else if (list.get(i) == scoreMall) {
                sb.append("积分兑换");
            } else if (list.get(i) == seckillMall) {
                sb.append("限时秒杀");
            } else if (list.get(i) == localSpecialtyMall) {
                sb.append("总部商城");
            } else if (list.get(i) == nineNineMall) {
                sb.append("99特惠");
            } else if (list.get(i) == memberMall) {
                sb.append("免单专区");
            } else if (list.get(i) == changeMall) {
                sb.append("换货专区");
            }else if(list.get(i) == lockMall){
            	sb.append("游戏专区");
            }
            if (i != size - 1) {
                sb.append(" - ");
            }
        }
        return sb.toString();
    }

    /**
     * 获取该商品没有在哪些商城商家，返回这些商城的编号值；
     *
     * @return
     */
    public List<Integer> getUnLaunchMalls() {
        List<Integer> list = new ArrayList<>();

        //将状态为0，即为未上架商城，返回编号；
        byte[] bytes = this.launchMall.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == '0') {
                list.add(i + 1);
            }
        }
        return list;
    }

    public String getUnLaunchMallsName() {
        List<Integer> list = getUnLaunchMalls();
        StringBuilder sb = new StringBuilder(100);

        int size = list.size();
        if (size == 0) {
            return "暂无未上架的商城；";
        }
        for (int i = 0; i < size; i++) {
            if (list.get(i) == sellerMall) {
                sb.append("周边店铺");
            } else if (list.get(i) == scoreMall) {
                sb.append("积分兑换");
            } else if (list.get(i) == seckillMall) {
                sb.append("限时秒杀");
            } else if (list.get(i) == localSpecialtyMall) {
                sb.append("总部商城");
            } else if (list.get(i) == nineNineMall) {
                sb.append("99特惠");
            } else if (list.get(i) == memberMall) {
                sb.append("免单专区");
            }else if (list.get(i) == changeMall) {
                sb.append("换货专区");
            }else if(list.get(i) == lockMall){
            	sb.append("游戏专区");
            }
            if (i != size - 1) {
                sb.append(" - ");
            }
        }
        return sb.toString();
    }

    /**
     * 获取商品简述图片集合；
     */
    public List<String> getListOfDescriptionPics() {
        JSONArray jsonArray = JSONObject.parseArray(this.descriptionPics);
        if (jsonArray == null) {
            return null;
        } else {
            List<String> returnList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                returnList.add(jsonArray.getJSONObject(i).getString("imgUrl"));
            }
            return returnList;
        }
    }

    //===================================================================================================================

    //=================get/set==================
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLables() {
        return lables;
    }

    public void setLables(String lables) {
        this.lables = lables;
    }

    public String getDescriptionPics() {
        return descriptionPics;
    }

    public void setDescriptionPics(String descriptionPics) {
        this.descriptionPics = descriptionPics;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getLaunchMall() {
        return launchMall;
    }

    public void setLaunchMall(String launchMall) {
        this.launchMall = launchMall;
    }

    public Integer getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * 将以json存储的lables标签内容，转换为前台展示的字符串形式；
     *
     * @return
     */
    public String getLablesString() {
        StringBuilder text = new StringBuilder();
        if(this.lables!=null){
	        try {
	            JSONArray list = JSONObject.parseArray(this.lables);
	            for (int i = 0; i < list.size(); i++) {
	                text.append(list.getJSONObject(i).getString("lableName")).append(" ");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
        }else{
        	text.append("");
        }
        
        return text.toString();
    }

    public String getCoverPicOne() {
        if (StringUtils.isEmpty(coverPic))
            return "";
        JSONArray list = JSONObject.parseArray(coverPic);
        if (list.size() == 0)
            return "";
        return list.getJSONObject(0).getString("imgUrl");
    }

    public void setCoverPicOne(String coverPicOne) {
        this.coverPicOne = coverPicOne;
    }


}
