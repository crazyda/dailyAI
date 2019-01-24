package com.axp.util;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {
	// 统计字符串

	public static String RealPath;

	public final static String resourceUrl="dailyRes";

	
	private static Hashtable<String, String> faceHash;

	public final static String CCP_SERVER_IP = "app.cloopen.com";

	public final static String CCP_SERVER_PORT = "8883";

	public final static String CCP_ACCOUNT_SID = "aaf98f894ac24cc7014ac38db07500fd";

	public final static String CCP_ACCOUNT_TOKEN = "209638136986426ebc5b8599e55bf71b";

	public final static String CCP_APP_ID = "8a48b5514ac24e1b014ac390be570136";

	public final static String CCP_API_CALLBACK_MAKE = "Calls/Callback";

	public final static String CCP_API_CALL_CALCEL = "Calls/CallCancel";

	/**补充真实姓名*/
	public final static String KEY_REPLENISH_REAL_NAME = "keyReplenishRealName";
	/**补充电话号码*/
	public final static String KEY_REPLENISH_PHONE_NUMBER = "keyReplenishPhoneNumber";
	/**补充地址*/
	public final static String KEY_REPLENISH_ADDRESS = "keyReplenishAddress";
	/**补充出生日期*/
	public final static String KEY_REPLENISH_BIRTHDAY = "keyReplenishBirthday";
	/**补充性别*/
	public final static String KEY_REPLENISH_SEX = "keyReplenishSex";
	/**网络通话*/
	public final static String KEY_PHONE_SPEND_SCORE = "keyPhoneSpendScore";

	
	/**默认父级规格*/
	public final static Integer parentStandardId=1;
	/**默认父级规格名*/
	public final static String parentStandardName="规格";
	/**
	 * 保存用户最新的分数增加的日期
	 */
	private static Hashtable<Integer, String> userScoreDayHash;

	/**
	 * 保存用户的实时的分数
	 */
	private static Hashtable<Integer, Integer> userStoreScoresHash;

	/**
	 * 保存字母的顺序，用于按字母检索楼盘
	 */
	private static Hashtable<String, Integer> spellcharHash = null;
	private static Hashtable<String, String> levelHash = null;
	private static Hashtable<String, String> superadmin = null;
	private static Hashtable<String, String> admin = null;
	private static Hashtable<String, String> giftcenter = null;
	private static Hashtable<String, String> advercenter = null;
	private static Hashtable<String, String> giftporxyone = null;
	private static Hashtable<String, String> adverporxyone = null;
	private static Hashtable<String, String> adverporxytwo = null;
	private static Hashtable<String, String> addscore = null;
	private static Hashtable<String, String> offscore = null;
	private static Hashtable<String, String> position = null;
	private static Hashtable<String, Integer> quantity = null;
	private static Hashtable<Integer, String> type = null;
	
	
	public static void setType(Hashtable<Integer, String> type) {
		StringUtil.type = type;
	}
	

	private static Hashtable<Integer, String> status;
	private static Hashtable<Integer, String> adver_type;
	private static Hashtable<Integer, String> max_str;
	private static Hashtable<Integer, String> machine_show_type;
	private static Hashtable<Integer, String> all_adver_type;
	private static Hashtable<Integer, String> plays_type;
	private static Hashtable<Integer, String> gift_show;
	private static Hashtable<Integer, String> quantityHash = null;
	private static Hashtable<Integer, String> freeVoucherHash = null;

	private static Hashtable<Integer, String> paymentHash = null;
	private static Hashtable<Integer, String> mallTypeHash = null;
	private static Hashtable<Integer, String> moduleHash = null;

	private static int sellerAdminLevel = 62;
	
	public final static Integer MESSAGE_FENYONG = 9;
	public final static Integer MESSAGE_TUIKUAN = 7;
	public final static Integer MESSAGE_CHONGZHI = 8;
	public final static Integer MESSAGE_TIXIAN = 6;
	public final static Integer MESSAGE_DINGDAN = 5;
	public final static Integer MESSAGE_PINGTAIZIXUN = 4;

	/**久久人后台id*/
	public final static int JJRID = 325;

	/**
	 * 代金券记录类型
	 */
	private static Hashtable<String, String> cashpointRecordHash = null;

	public static Hashtable<Integer, String> getGift_show() {
		if (gift_show == null) {
			gift_show = new Hashtable<Integer, String>();
			gift_show.put(5, "全民抢拍");
			gift_show.put(4, "今日特惠");
			gift_show.put(3, "猜你喜欢");
			gift_show.put(2, "值得兑换");
			gift_show.put(1, "推荐兑换");
		}
		return gift_show;
	}

	public static void setGift_show(Hashtable<Integer, String> gift_show) {
		StringUtil.gift_show = gift_show;
	}

	/**
	 * 判断字符串是否有长度；
	 * 1，不是为空；
	 * 2，有长度；
	 */
	public static Boolean hasLength(String s) {
		return s != null && s.length() > 0;
	}

	public static Hashtable<Integer, String> getAll_adver_type() {
		if (all_adver_type == null) {
			all_adver_type = new Hashtable<Integer, String>();
			all_adver_type.put(1, "广告机二维码广告");
			all_adver_type.put(2, "广告机二维码分销");
			all_adver_type.put(3, "广告机图片广告");
			all_adver_type.put(4, "广告机全屏图片广告");
			all_adver_type.put(5, "滑屏图片广告");
			all_adver_type.put(6, "积分商城图片广告");
			all_adver_type.put(7, "个人中心图片广告");
			all_adver_type.put(8, "商家联盟图片广告");
			all_adver_type.put(9, "十秒视频广告");
			all_adver_type.put(10, "二十秒视频广告");
			all_adver_type.put(11, "三十秒视频广告");
			all_adver_type.put(12, "四十五秒视频广告");
			all_adver_type.put(13, "六十秒视频广告");
			all_adver_type.put(14, "九十秒视频广告");
			all_adver_type.put(15, "礼品");
			all_adver_type.put(16, "分享");
			all_adver_type.put(17, "视频展播");
			all_adver_type.put(18, "游戏");

		}
		return all_adver_type;
	}

	public static Hashtable<Integer, String> getPlays_type() {
		if (plays_type == null) {
			plays_type = new Hashtable<Integer, String>();
			plays_type.put(1, "分享");
			plays_type.put(2, "视频");
			plays_type.put(3, "游戏");
		}
		return plays_type;
	}

	public static void setPlays_type(Hashtable<Integer, String> plays_type) {
		StringUtil.plays_type = plays_type;
	}

	public static void setAll_adver_type(Hashtable<Integer, String> all_adver_type) {
		StringUtil.all_adver_type = all_adver_type;
	}

	public static Hashtable<Integer, String> getMachine_show_type() {
		if (machine_show_type == null) {
			machine_show_type = new Hashtable<Integer, String>();
			machine_show_type.put(0, "广告横屏");
			machine_show_type.put(1, "广告竖屏");
			machine_show_type.put(2, "广告机横屏");
			machine_show_type.put(3, "广告单屏");
			//machine_show_type.put(4,"广告全屏");
		}
		return machine_show_type;
	}

	public static void setMachine_show_typee(Hashtable<Integer, String> machine_show_type) {
		StringUtil.machine_show_type = machine_show_type;
	}

	public static Hashtable<Integer, String> getMax_str() {

		if (max_str == null) {
			max_str = new Hashtable<Integer, String>();
			max_str.put(1, "000000");
			max_str.put(10, "00000");
			max_str.put(100, "0000");
			max_str.put(1000, "000");
			max_str.put(10000, "00");
			max_str.put(100000, "0");

		}
		return max_str;
	}

	public static void setMax_str(Hashtable<Integer, String> max_str) {
		StringUtil.max_str = max_str;
	}

	public static Hashtable<Integer, String> getAdver_type() {
		if (adver_type == null) {
			adver_type = new Hashtable<Integer, String>();
			adver_type.put(1, "底图");
			adver_type.put(2, "二维码");
			adver_type.put(3, "视频");
			adver_type.put(4, "广告");
			adver_type.put(5, "全屏广告");

		}
		return adver_type;
	}

	public static void setAdver_type(Hashtable<Integer, String> adver_type) {
		StringUtil.adver_type = adver_type;
	}

	public static Hashtable<Integer, String> getStatus() {
		if (status == null) {
			status = new Hashtable<Integer, String>();
			status.put(0, "待审核");
			status.put(-1, "不通过");
			status.put(1, "审核通过");
			status.put(2, "待播");
			status.put(3, "播放中");
			status.put(4, "已播");
			status.put(9, "禁播");
		}
		return status;
	}

	public static void setStatus(Hashtable<Integer, String> status) {
		StringUtil.status = status;
	}

	public static Hashtable<Integer, String> getType() {
		if (type == null) {
//			type = new Hashtable<Integer, String>();
//			type.put(1, "积分兑换轮播图");
//			type.put(2, "商家联盟");
//			type.put(3, "个人中心");
//			type.put(4, "会员商城轮播图");
//			type.put(5, "本地商城轮播图");
//			type.put(6, "商家详情页");
//			type.put(7, "会员商城单页");
//			type.put(8, "本地商城单页");
//			type.put(9, "积分兑换单页");
//			type.put(10, "全民抢拍");
			type = new Hashtable<Integer, String>();
			type.put(1,"商家版首页");
			type.put(10, "用户版首页");
			type.put(20, "本地特产");
			type.put(30, "久久人置顶");
			type.put(40, "久久人商品");
			type.put(50, "商家广告");
			type.put(60, "活动广告");
			type.put(70, "积分兑换");
			type.put(80, "免单专区");
			type.put(90, "换货会");
			
		}
		return type;
	}
	public static Hashtable<Integer, String> getTypes() {
		if (type == null || type.size()>2) {
			type = new Hashtable<Integer, String>();
			type.put(1,"商家版首页");
			type.put(10, "用户版首页");
		}
		return type;
	}
	

	public static Hashtable<String, String> getAddscore() {
		if (addscore == null) {
			addscore = new Hashtable<String, String>();
			addscore.put("referee", "邀请推荐");
			addscore.put("register", "注册");
			addscore.put("huaping", "滑屏");
			addscore.put("guanwang", "访问官网");
			addscore.put("add", "后台加分");
			addscore.put(KEY_REPLENISH_REAL_NAME, "补充真实姓名");
			addscore.put(KEY_REPLENISH_PHONE_NUMBER, "补充电话号码");
			addscore.put(KEY_REPLENISH_ADDRESS, "补充地址");
			addscore.put(KEY_REPLENISH_BIRTHDAY, "补充出生日期");
			addscore.put(KEY_REPLENISH_SEX, "补充性别");
			addscore.put(KEY_PHONE_SPEND_SCORE, "网络通话");
		}
		return addscore;
	}

	public static void setAddscore(Hashtable<String, String> addscore) {
		StringUtil.addscore = addscore;
	}

	public static Hashtable<String, String> getOffscore() {
		if (offscore == null) {
			offscore = new Hashtable<String, String>();
			offscore.put("changer", "积分兑换商品");
		}
		return offscore;
	}

	public static void setOffscore(Hashtable<String, String> offscore) {
		StringUtil.offscore = offscore;
	}

	public static Hashtable<String, String> getSuperadmin() {
		if (superadmin == null) {
			superadmin = new Hashtable<String, String>();
			//superadmin.put("99", "超级管理员");
			superadmin.put("95", "总部");
			//superadmin.put("85", "运营中心");
			superadmin.put("75", "城市代理");
			//superadmin.put("65", "联盟合伙人");
			//superadmin.put("60", "商家");

		}
		return superadmin;
	}

	public static void setSuperadmin(Hashtable<String, String> superadmin) {

		StringUtil.superadmin = superadmin;
	}

	public static Hashtable<String, String> getAdmin() {
		if (admin == null) {
			admin = new Hashtable<String, String>();
			admin.put("75", "城市代理");
			admin.put("55", "上游收分用户");
		}
		return admin;
	}

	public static void setAdmin(Hashtable<String, String> admin) {
		StringUtil.admin = admin;
	}

	public static Hashtable<String, String> getGiftcenter() {
		if (giftcenter == null) {
			giftcenter = new Hashtable<String, String>();
			giftcenter.put("85", "当前级别");
			giftcenter.put("75", "城市代理");
			giftcenter.put("65", "联盟合伙人");
			giftcenter.put("60", "商家");
		}
		return giftcenter;
	}

	public static void setGiftcenter(Hashtable<String, String> giftcenter) {
		StringUtil.giftcenter = giftcenter;
	}

	public static Hashtable<String, String> getAdvercenter() {
		if (advercenter == null) {
			advercenter = new Hashtable<String, String>();
			advercenter.put("85", "当前级别");
			advercenter.put("75", "城市代理");
			advercenter.put("65", "联盟合伙人");
			advercenter.put("60", "商家");
		}
		return advercenter;
	}

	public static void setAdvercenter(Hashtable<String, String> advercenter) {
		StringUtil.advercenter = advercenter;
	}

	public static Hashtable<String, String> getGiftporxyone() {
		if (giftporxyone == null) {
			giftporxyone = new Hashtable<String, String>();
			giftporxyone.put("75", "当前级别");
			giftporxyone.put("65", "联盟合伙人");
			giftporxyone.put("60", "商家");
		}
		return giftporxyone;
	}

	public static void setGiftporxyone(Hashtable<String, String> giftporxyone) {
		StringUtil.giftporxyone = giftporxyone;
	}

	public static Hashtable<String, String> getAdverporxyone() {
		if (adverporxyone == null ) {
			adverporxyone = new Hashtable<String, String>();
			adverporxyone.put("65", "联盟合伙人");
		}
		return adverporxyone;
	}

	public static Hashtable<String, String> getAdverporxytwo() {
		if (adverporxytwo == null) {
			adverporxytwo = new Hashtable<String, String>();
			adverporxytwo.put("65", "当前级别");
			adverporxytwo.put("60", "商家");
		}
		return adverporxytwo;
	}
	
	public static void setAdverporxyone(Hashtable<String, String> adverporxyuser) {
		StringUtil.adverporxyone = adverporxyone;
	}

	public static final int SUPERADMIN = 99;
	public static final int ADMIN = 95; //总部
	public static final int ASSESSOR = 94; //审核专员
	public static final int MANAGER = 93; //管理员
	public static final int TREASURER = 92; //财务
	public static final int GIFTCENTER = 90;
	public static final int ADVERCENTER = 85;//分公司
	public static final int GIFTONE = 80;
	public static final int ADVERONE = 75;//代理
	public static final int GIFTTWO = 70;
	public static final int ADVERTWO = 65;//联盟组（商圈）
	public static final int SUPPLIER = 60;//商家（以前是供应商）
	public static final int SHOUFEN = 55;//收分用户

	public static final int CATEGORY_QR = 2;
	public static final int CATEGORY_AD = 4;
	public static final int CATEGORY_MV = 3;
	public static final int CATEGORY_PH = 5;

	public static final int OPEROTION_DELETE = 0;
	public static final int OPEROTION_ADD = 1;
	public static final int OPEROTION_UPDATE = 2;

	public static final int referee4 = 4;
	public static final int referee3 = 3;
	public static final int referee2 = 2;
	public static final int referee1 = 1;

	public static Hashtable<String, String> getLevelsHash() {
		if (levelHash == null) {
			levelHash = new Hashtable<String, String>();
			levelHash.put("99", "超级管理员");
			levelHash.put("95", "平台管理员");
			levelHash.put("94", "审核专员");
			levelHash.put("93", "管理员");
			levelHash.put("92", "财务");
			//	levelHash.put("90", "礼品分公司");
			//	levelHash.put("85","广告分公司");
			levelHash.put("85", "运营中心");
			//	levelHash.put("80","礼品市级代理");
			//	levelHash.put("75","广告市级代理");
			levelHash.put("75", "城市代理");
			//	levelHash.put("70","礼品联盟商");
			//	levelHash.put("65","广告联盟商");
			levelHash.put("65", "联盟合伙人");
			levelHash.put("60", "商家");
		}
		return levelHash;
	}

	public static Hashtable<String, String> getCashpointRecordHash() {
		if (cashpointRecordHash == null) {
			levelHash = new Hashtable<String, String>();
			levelHash.put("1", "充值代金券");
			levelHash.put("2", "在新支付");
			levelHash.put("3", "推广收益");
			levelHash.put("4", "红包");
			levelHash.put("5", "商家代金券提现");
			levelHash.put("6", "注册收益");
			levelHash.put("7", "直接邀请收益");
			levelHash.put("8", "间接邀请收益");
			levelHash.put("9", "退订返还");
			levelHash.put("10", "发放红包");
			levelHash.put("11", "获得赞助红包");
			levelHash.put("12", "兑换返现");
		}
		return levelHash;
	}

	public static Hashtable<Integer, String> getFreeVoucherHash() {
		if (freeVoucherHash == null) {
			freeVoucherHash = new Hashtable<Integer, String>();
			freeVoucherHash.put(1, "会员商城免单券");
		}
		return freeVoucherHash;
	}

	public static Hashtable<Integer, String> getQuantityHash() {
		if (quantityHash == null) {
			quantityHash = new Hashtable<Integer, String>();
			quantityHash.put(0, "广告天数");
			quantityHash.put(95, "平台广告天数");
			quantityHash.put(85, "分公司广告天数");
			quantityHash.put(75, "代理广告天数");
			quantityHash.put(65, "联盟组广告天数");
		}
		return quantityHash;
	}

	public static void setLevelHash(Hashtable<String, String> levelHash) {
		StringUtil.levelHash = levelHash;
	}

	public static Hashtable<String, Integer> getSpellcharHash() {
		if (spellcharHash == null) {
			spellcharHash = new Hashtable<String, Integer>();
			spellcharHash.put("a", 1);
			spellcharHash.put("b", 1);
			spellcharHash.put("c", 1);
			spellcharHash.put("d", 1);

			spellcharHash.put("e", 2);
			spellcharHash.put("f", 2);
			spellcharHash.put("g", 2);
			spellcharHash.put("h", 2);

			spellcharHash.put("i", 3);
			spellcharHash.put("j", 3);
			spellcharHash.put("k", 3);
			spellcharHash.put("l", 3);
			spellcharHash.put("m", 3);
			spellcharHash.put("n", 3);

			spellcharHash.put("o", 4);
			spellcharHash.put("p", 4);
			spellcharHash.put("q", 4);
			spellcharHash.put("r", 4);
			spellcharHash.put("s", 4);
			spellcharHash.put("t", 4);
			spellcharHash.put("u", 4);
			spellcharHash.put("v", 4);

			spellcharHash.put("w", 5);
			spellcharHash.put("x", 5);
			spellcharHash.put("y", 5);
			spellcharHash.put("z", 5);
		}
		return spellcharHash;
	}

	public static Hashtable<Integer, String> getMallTypeHash() {
		if (mallTypeHash == null) {
			mallTypeHash = new Hashtable<Integer, String>();
			mallTypeHash.put(0, "城市精品");
			mallTypeHash.put(1, "促销特惠");
			mallTypeHash.put(2, "会员商城");
			mallTypeHash.put(3, "秒杀专区");
			mallTypeHash.put(4, "名店优品");
			mallTypeHash.put(5, "会员免单区");
			mallTypeHash.put(6, "会员商城置顶区");
		}
		return mallTypeHash;
	}

	public static Hashtable<Integer, String> getPaymentHash() {
		if (paymentHash == null) {
			paymentHash = new Hashtable<Integer, String>();
			paymentHash.put(0, "全红包");
			paymentHash.put(1, "红包加现金");
			paymentHash.put(2, "现金");
		}
		return paymentHash;
	}

	public static void setSpellcharHash(Hashtable<String, Integer> spellcharHash) {
		StringUtil.spellcharHash = spellcharHash;
	}

	public static int getCount(String sresoue, String ssub) {
		int count = 0;
		Matcher matcher = Pattern.compile(ssub).matcher(sresoue);
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	// 取字符串
	public static String[] getStringsub(String org, String sub) {
		String[] a = new String[2];
		int of = org.indexOf(sub);
		String subs = org.substring(0, of);
		a[0] = subs;
		String resubs = org.substring(of + sub.length(), org.length());
		a[1] = resubs;

		return a;
	}

	// 左填充12位数字字符串
	public static String getStringByFilling12(Integer id) {
		String str_m = String.valueOf(id);
		String str = "000000000000";
		str_m = str.substring(0, 12 - str_m.length()) + str_m;

		return str_m;
	}

	//取得文件的类型
	public static String[] getStringsubWithFileType(String org, String sub) {
		String[] a = new String[2];
		int of = org.lastIndexOf(sub);
		String subs = org.substring(0, of);
		a[0] = subs;
		String resubs = org.substring(of + sub.length(), org.length());
		a[1] = resubs;

		return a;
	}

	//取总页数
	public static int getPagenumberCount(int count, int pagesize) {

		int pagenumcount = count / pagesize;
		if (count % (pagesize) != 0) {
			pagenumcount = pagenumcount + 1;
		}
		if (pagenumcount == 0) {
			pagenumcount = 1;
		}
		return pagenumcount;
	}

	public static String encodePwd(String pwd) {

		return pwd;
	}

	/**
	 * 解码url
	 * @param s
	 * @return
	 */
	public static String DecodeUrl(String s) {
		try {
			s = java.net.URLDecoder.decode(s, "UTF-8");
			//System.out.println("s new =============="+s);
			//s=new String(s.getBytes("ISO-8859-1"));
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String getStringGBKEncode(String str) {
		try {
			byte[] bytes1 = str.getBytes("UTF-8");
			byte[] bytes2 = str.getBytes("ISO-8859-1");
			byte[] bytes3 = str.getBytes("Unicode");
			byte[] bytes4 = str.getBytes("GB2312");
			if (str.equals(new String(bytes1))) {
				return new String(str.getBytes("GBK"), "UTF-8");
			}
			if (str.equals(new String(bytes2))) {
				return new String(str.getBytes("GBK"), "ISO-8859-1");
			}
			if (str.equals(new String(bytes3))) {
				return new String(str.getBytes("GBK"), "Unicode");
			}
			if (str.equals(new String(bytes4))) {
				return new String(str.getBytes("GBK"), "GB2312");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 编码url
	 * @param s
	 * @return
	 */
	public static String EncodeUrl(String s) {

		try {
			//必须要编码两次才能成功 反编码，适用于Url包含中文字符
			s = java.net.URLEncoder.encode(s, "UTF-8");
			s = java.net.URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	//生成18位长的随机数
	public static String randomString() {
		String s = "";
		Random random = new Random();
		s += random.nextInt(9) + 1;
		for (int i = 0; i < 18 - 1; i++) {
			s += random.nextInt(10);
		}
		return s;

	}

	//生成6位长的随机数
	public static String randomString6() {
		String s = "";
		Random random = new Random();
		s += random.nextInt(9) + 1;
		for (int i = 0; i < 6 - 1; i++) {
			s += random.nextInt(10);
		}
		return s;

	}

	public static String getPushMessagelist(Map<String, String> map) {

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"machinecode\":\"" + getNullValue(map.get("code")) + "\"");
		sb.append(",\"category\":\"" + getNullValue(map.get("category")) + "\"");
		sb.append(",\"operation\":\"" + getNullValue(map.get("operation")) + "\"");
		sb.append(",\"imgurl\":\"" + getNullValue(map.get("imgurl")) + "\"");
		sb.append(",\"new_imgurl\":\"" + getNullValue(map.get("newimgurl")) + "\"");
		sb.append(",\"imgurl_small\":\"" + getNullValue(map.get("imgurl_small")) + "\"");
		sb.append(",\"new_imgurl_small\":\"" + getNullValue(map.get("new_imgurl_small")) + "\"");
		sb.append(",\"url\":\"" + getNullValue(map.get("url")) + "\"");
		sb.append(",\"new_url\":\"" + getNullValue(map.get("new_url")) + "\"");
		sb.append(",\"description\":\"" + getNullValue(map.get("description")) + "\"");
		sb.append("}");
		return sb.toString();
	}

	public static String checkZoneName(String zoneName) {
		return zoneName.replace("中山市市辖区", "市辖区");
	}

	/**
	 * 关键字过滤条件不能包含全角或半角的单引号、双引号和百分号等，一般SQL语句用
	 * @param str SQL语句
	 * @return 转义后的SQL语句
	 * @author zhangpeng
	 */
	public static String replaceStr(String str) {
		if (str == null) {
			return "";
		}
		//for sql server
		//		String replaceStr = str.replace("[", "[[]");// 此句一定要在最前面
		//		replaceStr = replaceStr.replace("_", "[_]");
		//		replaceStr = replaceStr.replace("%", "[%]");
		//		replaceStr = replaceStr.replace("'", "''");
		//		replaceStr = replaceStr.replace("‘", "\\‘");
		//		replaceStr = replaceStr.replace("’", "\\’");
		//		replaceStr = replaceStr.replace("{", "[{]");

		//for mysql
		String replaceStr = str.replace("\\", "\\\\\\\\");
		replaceStr = replaceStr.replace("_", "\\_");
		replaceStr = replaceStr.replace("%", "\\%");
		replaceStr = replaceStr.replace("'", "''");
		return replaceStr;
	}

	public static String getAPPurl(String basePath) {
		return basePath + "LoveSmallScreen-3.2.3.apk";
	}

	public static String getIosAPPurl() {
		return "http://www.wwcode.net/s/hkTe2";
	}

	public static String getSpread() {
		return "亲！下载每天积分,滑屏送积分，礼品免费送！";
	}

	public static String getQr(String basePath) {
		return basePath + "qr.png";
	}

	public static String getWebQr(String basePath) {
		return basePath + "webqr.png";
	}

	public static String getLogo(String basePath) {
		return basePath + "logo.png";
	}

	public static String getNullValue(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}

	public static Hashtable<Integer, String> GetUserScoreDayHash() {

		if (userScoreDayHash == null) {
			userScoreDayHash = new Hashtable<Integer, String>();

		}
		return userScoreDayHash;
	}

	public static Hashtable<Integer, Integer> GetUserStoreScoreHash() {

		if (userStoreScoresHash == null) {
			userStoreScoresHash = new Hashtable<Integer, Integer>();

		}
		return userStoreScoresHash;
	}

	/**过滤特殊字符
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 *@author zhangpeng
	 *@time 2015-6-6
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static void main(String[] args) {
		System.out.println(StringFilter("$23&44@"));
	}

	public static String escapeUtil(String txt) {
		return txt.replace("&amp;", "&").replace("&ensp;", "").replace("&emsp;", "").replace("&nbsp;", "").replace("&quot;", "\"");
	}

	/**
	 * 检查字符串是否为空
	 * 检查规则：
	 * 1，为null；
	 * 2，是空字符串；
	 * 3，是全空格字符串；
	 * @param param 被检查的字符串
	 * @return
	 */
	public static Boolean isEmpty(String param) {
		return !hasLength(param);
	}

	/**
	 * 将给定的字符串首字母大写；
	 * @param param 传入的参数；
	 * @return 返回首字母大写的字符串；
	 */
	public static String upperFirstChar(String param) {
		if (isEmpty(param)) {//先判断是否为空；
			return null;
		}
		if (param.length() == 1) {//判断是否只有一个字符；
			return param.toUpperCase();
		}
		return String.valueOf(param.charAt(0)).toUpperCase() + param.substring(1);
	}

	/**
	 * 将给定的字符串的首字母小写；
	 * @param param 给定的字符串；
	 * @return 首字母小写的字符串；
	 */
	public static String lowerFirstChar(String param) {
		if (isEmpty(param)) {//先判断是否为空；
			return null;
		}
		if (param.length() == 1) {//判断是否只有一个字符；
			return param.toLowerCase();
		}
		return String.valueOf(param.charAt(0)).toLowerCase() + param.substring(1);
	}

	/**
	 * 获取一个集合中最小值的角标；
	 * @param <T>
	 * @param list 操作的那个集合；
	 * @return
	 */
	public static <T> Integer getIndexOfMinValueOfList(List<T> list) {

		//检查参数；
		if (list == null || list.size() == 0) {
			return -1;
		}

		//如果只有一个值，则不用判断了；
		if (list.size() == 1) {
			return 0;
		}

		//循环遍历；
		if (list.get(0) instanceof Integer) {
			Integer min = (Integer) list.get(0);
			Integer minIndex = 0;
			for (int i = 1; i < list.size(); i++) {
				Integer current = (Integer) list.get(i);
				if (min > current) {
					min = current;
					minIndex = i;
				}
			}
			return minIndex;
		} else if (list.get(0) instanceof Double) {
			Double min = (Double) list.get(0);
			Integer minIndex = 0;
			for (int i = 1; i < list.size(); i++) {
				Double current = (Double) list.get(i);
				if (min > current) {
					min = current;
					minIndex = i;
				}
			}
			return minIndex;
		}
		return -1;
	}
	
	public static Hashtable<Integer, String> getModule(){
		if(moduleHash == null){
			moduleHash = new Hashtable<Integer,String>();
			moduleHash.put(1, "周边店铺");
			moduleHash.put(2, "本地特产");
		}
		return moduleHash;
	}
}