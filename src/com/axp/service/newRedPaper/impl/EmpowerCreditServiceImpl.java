package com.axp.service.newRedPaper.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperAssetlog;
import com.axp.model.Seller;
import com.axp.service.newRedPaper.EmpowerCreditService;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Service("empowerCreditService")
public class EmpowerCreditServiceImpl extends BaseNewRedPaperServiceImpl implements EmpowerCreditService {

	//根据当前父adminId和等级获取adminId拼接的字符串
	public String getChild(Integer id, Integer level) {
		QueryModel model = new QueryModel();
		model.combCondition("parentId = " + id);
		model.combPreEquals("isvalid", true);
		String ids = dateBaseDao.findHQLGroupConcat(AdminUser.class, model, "id");
		return ids;
	}

	//根据当前父adminId获取sellerId拼接的字符串
	public String getSeller(String parentIds) {
		String ids = "-1";
		if (!"-1".equals(parentIds)) {
			QueryModel model = new QueryModel();
			//model.combCondition("adminUser.id in ("+parentIds+")");
			model.combCondition("(" + model.subVals("adminUser.id", new StringBuffer(parentIds)) + ")");
			model.combPreEquals("isvalid", true);
			ids = dateBaseDao.findHQLGroupConcat(Seller.class, model, "id");
		}
		return ids;
	}

	@Override
	public NewRedPaperAsset findValidAssetByAdminUserId(Integer id) {
		NewRedPaperAsset newRedPaperAsset = newRedPaperAssetDAO.findValidAssetByAdminUserId(id);
		if (newRedPaperAsset == null) {
			newRedPaperAsset = new NewRedPaperAsset();
			newRedPaperAsset.setPositionSurplus(0.00);
		}

		return newRedPaperAsset;
	}

	@Override
	public String saveAssetDetail(Integer current_user_id, NewRedPaperAsset asset) {
		String error = "";
		try {
			AdminUser admin = adminUserDAO.findById(current_user_id);

			NewRedPaperAsset parentAsset = newRedPaperAssetDAO.findValidAssetByAdminUserId(current_user_id);
			if (admin.getLevel() < StringUtil.ADMIN && (parentAsset == null || parentAsset.getPositionSurplus() < asset.getPositionTotal())) {
				error = "当前额度不足，请先向上级申请额度发放！";
				return error;
			}

			NewRedPaperAsset newAsset = null;
			if (asset.getId() != null) {
				newAsset = newRedPaperAssetDAO.findById(asset.getId());
				double positionTotal = newAsset.getPositionTotal();
				double positionSurplus = newAsset.getPositionSurplus();
				positionTotal = CalcUtil.add(asset.getPositionTotal(), positionTotal, 2);//增加总额度
				positionSurplus = CalcUtil.add(asset.getPositionTotal(), positionSurplus, 2);//增加总额度
				newAsset.setPositionSurplus(positionSurplus);
				newAsset.setPositionTotal(positionTotal);
			} else {
				newAsset = new NewRedPaperAsset();
				AdminUser assettor = null;
				Seller seller = null;
				if (asset.getAdminUser().getId() != null)
					assettor = adminUserDAO.findById(asset.getAdminUser().getId());
				if (asset.getSeller().getId() != null)
					seller = sellerDAO.findById(asset.getSeller().getId());
				newAsset.setAdminUser(assettor);
				newAsset.setSeller(seller);
				newAsset.setBeginTime(new java.sql.Timestamp(System.currentTimeMillis()));
				newAsset.setIsValid(true);
				newAsset.setPositionUsed(0.00);
				newAsset.setPositionSurplus(asset.getPositionTotal());
				newAsset.setPositionTotal(asset.getPositionTotal());
			}
			newAsset.setEndTime(asset.getEndTime());
			newAsset.setRemark(asset.getRemark());
			newAsset.setStatus(VALIDSTATUS);
			if (asset.getId() != null) {
				newRedPaperAssetDAO.update(newAsset);
			} else {
				newRedPaperAssetDAO.save(newAsset);
			}
			if (admin.getLevel() < StringUtil.ADMIN) {
				parentAsset.setPositionSurplus(parentAsset.getPositionSurplus() - asset.getPositionTotal());
				newRedPaperAssetDAO.update(parentAsset);
				saveLog(parentAsset, admin, asset.getPositionTotal() * -1, "授权额度扣除余额");
			}
			//saveLog(newAsset,admin,asset.getPositionTotal(),asset.getRemark());
			saveLog(newAsset, admin, asset.getPositionTotal(), "获得上级的额度发放(" + asset.getRemark() + ")");

		} catch (Exception e) {
			e.printStackTrace();
			error = "系统出错！";
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return error;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updatePropertyByID(String propertyName, Object value, int id, Class model) {
		newRedPaperAssetDAO.updatePropertyByID(propertyName, value, id, model);
	}

}
