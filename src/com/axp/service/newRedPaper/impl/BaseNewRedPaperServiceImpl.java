package com.axp.service.newRedPaper.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.NewRedPaperAssetDAO;
import com.axp.dao.NewRedPaperAssetlogDAO;
import com.axp.dao.NewRedPaperSettingDAO;
import com.axp.dao.SellerDAO;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperAssetlog;
import com.axp.model.NewRedPaperSetting;
import com.axp.model.Seller;
import com.axp.service.newRedPaper.IBaseNewRedPaperService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@SuppressWarnings("all")
public abstract class BaseNewRedPaperServiceImpl extends BaseServiceImpl implements IBaseNewRedPaperService {
	@Resource
	public NewRedPaperAssetDAO newRedPaperAssetDAO;
	@Resource
	public NewRedPaperAssetlogDAO newRedPaperAssetlogDAO;
	@Resource
	public NewRedPaperSettingDAO newRedPaperSettingDAO;
	@Resource
	public AdminUserDAO adminUserDAO;
	@Resource
	public SellerDAO sellerDAO;

	public Utility utility = new Utility();
	//红包额度
	public static final Integer INVALIDSTATUS = 0;//失效状态
	public static final Integer VALIDSTATUS = 1;//有效状态
	public static final Integer TIMEOUTSTATUS = 5;//过期状态
	//红包发放
	public static final Integer CHECKING = 0;//未审核
	public static final Integer PASS = 1;//审核通过
	public static final Integer FAIL = 2;//审核不通过
	public static final Integer ISSUEING = 3;//发放中
	public static final Integer TIMEOUT = 5;//过期
	public static final Integer INVALID = 10;//失效

	private QueryModel model = new QueryModel();

	public Object findById(Class clazz, Integer id) {
		model.clearQuery();
		model.combPreEquals("id", id);
		List<Object> list = dateBaseDao.findLists(clazz, model);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public NewRedPaperAsset getAssetDetail(HttpServletRequest request, Integer id, Integer adminUserId, Integer sellerId) {
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		NewRedPaperAsset asset = null;

		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("id", current_user_id);
		AdminUser au = dateBaseDao.findLists(AdminUser.class, queryModel).get(0);

		//如果Id不为空，说明是进入额度编辑；为空说明是进入新增页面
		if (id != null) {
			queryModel.clearQuery();
			queryModel.combPreEquals("id", id);
			asset = dateBaseDao.findLists(NewRedPaperAsset.class, queryModel).get(0);
			return asset;
		} else {
			asset = new NewRedPaperAsset();
			if (adminUserId != null) {
				queryModel.clearQuery();
				queryModel.combPreEquals("id", adminUserId);
				AdminUser adminUser = dateBaseDao.findLists(AdminUser.class, queryModel).get(0);
				asset.setAdminUser(adminUser);
			}
			if (sellerId != null) {
				queryModel.clearQuery();
				queryModel.combPreEquals("id", sellerId);
				Seller seller = dateBaseDao.findLists(Seller.class, queryModel).get(0);
				asset.setSeller(seller);
			}

			//运营中心查询条件
			StringBuffer con_s = new StringBuffer(" isvalid = true ");
			//城市代理查询条件
			StringBuffer con_p = new StringBuffer(" isvalid = true ");
			//联盟组查询条件
			StringBuffer con_a = new StringBuffer(" isvalid = true ");
			//商家查询条件
			StringBuffer con_seller = new StringBuffer(" isvalid = true ");
			//供应商查询条件
			StringBuffer con_pro = new StringBuffer(" isvalid = true ");
			if (au.getLevel() >= StringUtil.ADMIN) {
				con_s.append(" and  level > 80 and level <= 90");
				con_pro.append(" and  level = 60 ");
			} else if (au.getLevel() >= StringUtil.ADVERCENTER && au.getLevel() <= StringUtil.GIFTCENTER) {
				con_s.append(" and  level > 80 and level <= 90").append(" and id = ").append(au.getId());
				con_pro.append(" and  level = 60 ").append(" and parentId = ").append(au.getId());
			} else if (au.getLevel() >= StringUtil.ADVERONE && au.getLevel() <= StringUtil.GIFTONE) {
				con_s.append(" and id = ").append(au.getParentId()).append(" and level > 80 and level <= 90");
				con_p.append(" and id = ").append(au.getId()).append(" and level >70 and level <=80");
			} else {
				con_s.append(" and id = ").append(au.getParentAdminUser().getParentId()).append(" and level >80 and level <= 90");
				con_p.append(" and id = ").append(au.getParentId()).append(" and level >70 and level <=80");
				con_a.append(" and id = ").append(au.getId()).append(" and level >60 and level <=70");
			}

			request.setAttribute("condition", con_s.toString());
			request.setAttribute("condition_proxy", con_p.toString());
			request.setAttribute("condition_alliance", con_a.toString());
			request.setAttribute("condition_seller", con_seller.toString());
			request.setAttribute("condition_provider", con_pro.toString());

			return asset;
		}
	}

	/**
	 * 获得当前有效额度
	 */
	@Override
	public NewRedPaperAsset getAsset(String sellerId, String adminUserId) {

		NewRedPaperAsset surplus = null;
		QueryModel model = new QueryModel();
		if (StringUtils.isBlank(sellerId) && StringUtils.isBlank(adminUserId)) {
			return surplus;
		}
		if (StringUtils.isNotBlank(sellerId)) {
			model.combPreEquals("seller.id", Integer.parseInt(sellerId), "sellerId");
		} else if (StringUtils.isNotBlank(adminUserId)) {
			model.combPreEquals("adminUser.id", Integer.parseInt(adminUserId), "adminuserId");
		}
		model.combPreEquals("status", VALIDSTATUS);
		model.combPreEquals("isValid", true);
		List<NewRedPaperAsset> list = dateBaseDao.findPageList(NewRedPaperAsset.class, model, 0, 1);
		if (list.size() > 0) {
			surplus = list.get(0);
		}
		return surplus;
	}

	public void updateSetting(NewRedPaperSetting setting) {
		newRedPaperSettingDAO.update(setting);
	}

	public void mergeSetting(NewRedPaperSetting setting) {
		newRedPaperSettingDAO.merge(setting);
	}

	public void updateAsset(NewRedPaperAsset asset) {
		newRedPaperAssetDAO.update(asset);
	}

	public void saveLog(NewRedPaperAsset asset, AdminUser remitter, Double addValue, String remark) {
		NewRedPaperAssetlog log = new NewRedPaperAssetlog();
		log.setAdminUser(asset.getAdminUser());
		log.setSeller(asset.getSeller());
		log.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		log.setIsValid(true);
		log.setPositions(addValue);
		log.setRemitter(remitter);
		log.setAsset(asset);
		log.setRemark(remark);
		newRedPaperAssetlogDAO.save(log);
	}
}
