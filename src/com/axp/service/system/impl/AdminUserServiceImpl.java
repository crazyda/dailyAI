package com.axp.service.system.impl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.impl.ReGoodsofextendmallDaoImpl;
import com.axp.model.AdminUser;
import com.axp.model.Coin;
import com.axp.model.MembersBonus;
import com.axp.model.MembersBonusList;
import com.axp.model.MembersTypeIncome;
import com.axp.model.ProviderBonus;
import com.axp.model.ProvinceEnum;
import com.axp.model.Seller;
import com.axp.model.Shoptypes;
import com.axp.model.Zones;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.AdverDaysLogService;
import com.axp.thread.AdvertDaysChangeLogThread;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class AdminUserServiceImpl extends BaseServiceImpl implements AdminUserService {
    //列表
    @Override
    public void list(HttpServletRequest request) {
        String levelId = request.getParameter("levelId");
        String searchName = request.getParameter("searchName");
        AdminUser user = adminUserDAO.getCurrentUser(request);
        StringBuffer buffer = new StringBuffer();
        buffer.append("isvalid=true and parentId=" + user.getId());
        if (StringUtils.isNotEmpty(levelId) && StringUtils.isNotEmpty(searchName) && !"-1".equals(levelId)) {
            buffer.append(" and " + levelId + " like '%" + StringUtil.StringFilter(searchName) + "%'");
        } else if (StringUtils.isNotEmpty(searchName)) {
            buffer.append(" and (loginname like '%" + StringUtil.StringFilter(searchName) + "%' or username like '%" + StringUtil.StringFilter(searchName) + "%')");
        }
        getList(buffer.toString(), request);
    }

    //新增
    @Override
    public void add(Integer id, HttpServletRequest request) {
        Coin coin = coinDAO.findById(1);
        AdminUser currentUser = adminUserDAO.getCurrentUser(request);
        //找出所有会员类型
        List<MembersTypeIncome> vipTypeList = membersTypeIncomeDAO.findAll();
        request.setAttribute("vipTypeList", vipTypeList);
        AdminUser newUser = null;
        if (id != null) {
            newUser = adminUserDAO.findById(id);
            //供应商逻辑
            if (newUser.getLevel() == 60) {
                //找出与供应商绑定的商家
                Seller seller = sellerDAO.findById(newUser.getSellerId());
                request.setAttribute("sellerName", seller.getName());
                //找出供应商的分佣设置
                ProviderBonus adminScale = providerBonusDAO.getProviderBonus(id);
                HashMap<Integer, MembersBonus> vipScaleMap = new HashMap<>();
                for (MembersTypeIncome in : vipTypeList) {
                    List<MembersBonus> list = membersBonusDAO.getMembersBonusByVipType(id, in.getId());
                    if (list.size() > 0) {
                        vipScaleMap.put(in.getId(), list.get(0));
                    }
                }
                request.setAttribute("adminScale", adminScale);
                request.setAttribute("vipScaleMap", vipScaleMap);
            }
        }
        //根据用户等级定义可新建的等级用户
        Hashtable<String, String> leveltable = null;
        switch (currentUser.getLevel().intValue()) {
            case StringUtil.SUPERADMIN:
                leveltable = StringUtil.getSuperadmin();
                break;
            case StringUtil.ADMIN:
                leveltable = StringUtil.getAdmin();
                break;
            case StringUtil.GIFTCENTER:
                break;
            case StringUtil.ADVERCENTER:
                List<ProvinceEnum> provinceList = provinceEnumDAO.findListByQueryChar("isValid = true and level = 1");
                request.setAttribute("provinceList", provinceList);
                leveltable = StringUtil.getAdvercenter();
                break;
            case StringUtil.GIFTONE:
                leveltable = StringUtil.getGiftporxyone();
                break;
            case StringUtil.ADVERONE:
                leveltable = StringUtil.getAdverporxyone();
                break;
            case StringUtil.ADVERTWO:
                leveltable = StringUtil.getAdverporxyone();
                break;
            default:
                break;

        }
        request.setAttribute("auser", newUser);
        request.setAttribute("coin", coin);
        request.setAttribute("leveltable", leveltable);
    }

    @Override
    public String save(Integer id, Integer quantity, Integer levelname, Integer isZoneLimit, Integer zonesId, Integer sellerId, Double latitude, Double longitude, Double radius, String username, String phone, String contacts, String address, String loginName, ProviderBonus adminScale, MembersBonusList membersBonusList, HttpServletRequest request) {

        AdminUser currentUser = adminUserDAO.getCurrentUser(request);// 当前用户
        Coin coin = coinDAO.findById(1);
        request.setAttribute("coin", coin);

        AdminUser editUser = null;
        if (id != null) {
            editUser = adminUserDAO.findById(id);
        } else {
            // 先判断是否存在同名用户
            editUser = adminUserDAO.getLoginAdminUser(loginName);
            if (editUser != null) {
                return "ERROR_SAME_NAME";
            }
            editUser = new AdminUser();
            editUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
            editUser.setMoney(0.00);
            editUser.setLevel(levelname);
            editUser.setIsvalid(true);
            editUser.setLoginname(loginName);
            editUser.setPassword(Utility.MD5("888888"));
            editUser.setParentId(currentUser.getId());
            editUser.setParentName(currentUser.getUsername());
            editUser.setHasVoucher(false);
            editUser.setIsOpenCashshopType(false);
            if (currentUser.getLevel() < StringUtil.ADMIN) {
                if ((currentUser.getQuantity() - quantity) >= 0) {
                    currentUser.setQuantity(currentUser.getQuantity() - quantity);
                    editUser.setQuantity(quantity);
                } else {
                    return "ERROR_NOT_ENOUPH_QUANTITY";
                }
            } else {
                editUser.setQuantity(quantity);
            }
            switch (levelname) {
                case StringUtil.ADMIN:
                    editUser.setCenter(0);
                    editUser.setProxyOne(0);
                    break;
                case StringUtil.ADVERCENTER:
                    editUser.setCenter(0);
                    editUser.setProxyOne(0);
                    break;
                case StringUtil.ADVERONE:
                    editUser.setCenter(currentUser.getId());
                    editUser.setProxyOne(0);
                    break;
                case StringUtil.ADVERTWO:
                    editUser.setCenter(currentUser.getCenter().intValue());
                    editUser.setProxyOne(currentUser.getId());
                    break;
                case StringUtil.SUPPLIER:
                    editUser.setCenter(currentUser.getCenter().intValue());
                    editUser.setProxyOne(currentUser.getProxyOne().intValue());
                    break;
            }
        }
        if (zonesId != null) {
            ProvinceEnum provinceEnum = provinceEnumDAO.findById(zonesId);
            editUser.setProvinceEnum(provinceEnum);
        }
        editUser.setHasVoucher(false);
        editUser.setUsername(username);
        editUser.setPhone(phone);
        editUser.setContacts(contacts);
        editUser.setAddress(address);
        editUser.setInvitecode("");
        editUser.setIsOpenPromoter(false);
        editUser.setIsOpenRebate(false);
        if (isZoneLimit == 1) {
            editUser.setIsZoneLimit(true);
            editUser.setLatitude(latitude);
            editUser.setLongitude(longitude);
            editUser.setRadius(radius);
        } else {
            editUser.setIsZoneLimit(false);
        }
        editUser.setHighLevelCount(0);// 跨级广告个数
        // 供应商绑定商家id
        if (editUser.getLevel() == 60) {
            if (sellerId != null) {
                if (editUser.getSellerId() != null && !editUser.getSellerId().equals(sellerId)) {
                    Seller beforSeller = editUser.getProviderSeller();
                    Seller nowSeller = sellerDAO.findById(sellerId);
                    beforSeller.setLevel(0);
                    nowSeller.setLevel(1);
                    sellerDAO.update(nowSeller);
                    sellerDAO.update(beforSeller);
                }
                editUser.setSellerId(sellerId);
            } else {
                Shoptypes type = shoptypesDAO.findById(1);
                Zones zone = zonesDAO.findById(1);
                Seller seller = new Seller();
                seller.setName(editUser.getLoginname());
                seller.setIsvalid(true);
                seller.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
                seller.setAdminUser(currentUser);
                seller.setShoptypes(type);
                seller.setZones(zone);
                seller.setHasVoucher(false);
                seller.setCashPoints(0.0);
                seller.setAddress(editUser.getAddress());
                seller.setPhone(editUser.getPhone());
                seller.setContacts(editUser.getContacts());
                sellerDAO.save(seller);
                editUser.setSellerId(seller.getId());
            }
        }
        adminUserDAO.save(editUser);
        adminUserDAO.save(currentUser);
        editUser.setInvitecode("1" + editUser.getId());
        adminUserDAO.update(editUser);
        // 保存分佣设置
        if (editUser.getLevel() == 60) {
            try {
                // 后台用户
                adminScale.setHqscale(adminScale.getHqscale() * 0.01);
                adminScale.setCenterScale(adminScale.getCenterScale() * 0.01);
                adminScale.setProviderScale(adminScale.getProviderScale() * 0.01);
                adminScale.setOtherSellerScale(0 * 0.01);
                if (adminScale.getId() == null) {
                    adminScale.setAdminUser(editUser);
                    adminScale.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
                    adminScale.setIsValid(true);
                    providerBonusDAO.save(adminScale);
                } else {
                    adminScale.setAdminUser(editUser);
                    providerBonusDAO.update(adminScale);
                }
                for (int vipType : membersBonusList.getMembersBonusList().keySet()) {
                    MembersBonus vipScale = membersBonusList.getMembersBonusList().get(vipType);
                    if (vipScale != null) {
                        vipScale.setLevelOneScale(vipScale.getLevelOneScale() * 0.01);
                        vipScale.setLevelTwoScale(vipScale.getLevelTwoScale() * 0.01);
                        vipScale.setLevelThreeScale(vipScale.getLevelThreeScale() * 0.01);
                        if (vipScale.getId() == null) {
                            MembersTypeIncome type = new MembersTypeIncome();
                            type.setId(vipType);
                            vipScale.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
                            vipScale.setIsValid(true);
                            vipScale.setMembersTypeIncome(type);
                            vipScale.setProvider(editUser);
                            membersBonusDAO.save(vipScale);
                        } else {
                            MembersTypeIncome type = new MembersTypeIncome();
                            type.setId(vipType);
                            vipScale.setMembersTypeIncome(type);
                            vipScale.setProvider(editUser);
                            membersBonusDAO.update(vipScale);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

        }

        /** 添加天数日志 */

        Map<Integer, String> data = new HashMap<Integer, String>();
        data.put(AdverDaysLogService.UNIONTYPE, quantity.toString());
        new Thread(new AdvertDaysChangeLogThread(editUser.getId(), data, AdverDaysLogService.ADDUSER)).start();
        return "SUCCESS";
    }

    @Override
    public AdminUser findById(Integer adminUserId) {
        return adminUserDAO.findById(adminUserId);
    }

    @Override
    public boolean isSuperAdmin(HttpServletRequest request) {

        //获取当前用户对象；
        AdminUser currentUser = adminUserDAO.getCurrentUser(request);

        return currentUser.getLevel() == StringUtil.SUPERADMIN;
    }

    @Override
    public Boolean bindPhone(Integer adminUserId, String phoneNumber) {

        //绑定手机号；
        AdminUser adminUser = adminUserDAO.findById(adminUserId);
        if (adminUser == null) {
            return false;
        }
        Integer sellerId = adminUser.getSellerId();
        if (sellerId == null) {
            return false;
        }
        Seller seller = sellerDAO.findById(sellerId);
        adminUser.setPhone(phoneNumber);

        //修改商家的提现资料填写状态；
        seller.setWithdrawStatus(Seller.未填写提现资料);

        return true;
    }

    @Override
    public List<AdminUser> getAllCildAdminUsers(AdminUser adminUser) {
        List<AdminUser> list = new ArrayList<>();//存储后台用户的对象；
        findAdminUser(adminUser, list);//递归寻找所有
        return list;
    }

    /**
     * 通过递归操作，获取某个adminUser的所有的子adminUser对象；
     *
     * @param adminUser 给定的adminUser对象；
     * @param list      装载adminUser对象的集合；
     */
    private void findAdminUser(AdminUser adminUser, List<AdminUser> list) {
        List<AdminUser> childAdminUserList = new ArrayList<>(adminUser.getChildren());//获取给定adminUser的子adminUser；

        if (childAdminUserList != null && childAdminUserList.size() > 0) {
            list.addAll(childAdminUserList);//将查找出的子adminUser放到集合中；

            //对这些子adminUser进行递归操作；
            for (AdminUser eachChildAdminUser : childAdminUserList) {
                findAdminUser(eachChildAdminUser, list);
            }
        }
    }

    private void getList(String where, HttpServletRequest request) {
        String mode = StringUtil.getNullValue(request.getParameter("mode"));
        String searchType = StringUtil.getNullValue(request.getParameter("searchType"));
        String searchName = StringUtil.getNullValue(request.getParameter("searchName"));
        String level = StringUtil.getNullValue(request.getParameter("level"));
        String loginname = StringUtil.getNullValue(request.getParameter("loginname"));
        String proxyonename = StringUtil.getNullValue(request.getParameter("proxyonename"));
        String centername = StringUtil.getNullValue(request.getParameter("centername"));
        String pagestr = StringUtil.getNullValue(request.getParameter("page"));

        Coin coin = coinDAO.findById(1);
        request.setAttribute("coin", coin);

        PageInfo pageInfo = new PageInfo();
        int count = adminUserDAO.findCountByQueryChar(where);
        Utility.setPageInfomation(pageInfo, pagestr, "20", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        String param = "&mode=" + mode + "&searchType=" + searchType + "&searchName=" + searchName + "&level=" + level + "&loginname" + loginname + "&proxyonename=" + proxyonename + "&centername=" + centername;
        pageInfo.setParam(param + "&page=");
        List<AdminUser> userList = adminUserDAO.findListByQueryChar(where, start, end);

        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("userList", userList);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
    }

    @Override
	public List<AdminUser> getLevel(Integer level) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("level",85,"level");
		queryModel.combPreEquals("isValid", true);
		List<AdminUser> adminUser=dateBaseDao.findLists(AdminUser.class, queryModel);
		return adminUser;
	}

	@Override
	public List<AdminUser> getlevel(Integer level) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("level",level,"level");
		queryModel.combPreEquals("isValid", true);
		List<AdminUser> adminUser=dateBaseDao.findLists(AdminUser.class, queryModel);
		return adminUser;
	}

	@Override
	public List<AdminUser> getUserByZone(Integer level, Integer adminUserId) {
		ReGoodsofextendmallDaoImpl daoImpl = new ReGoodsofextendmallDaoImpl();
		AdminUser adminUser = adminUserDAO.findById(adminUserId);
		ProvinceEnum provinceEnum = adminUser.getProvinceEnum();
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("level",level,"level");
		queryModel.combPreEquals("isvalid", true);
		String str = daoImpl.findLevelProvinceEnum(provinceEnum);
		if (StringUtil.hasLength(str)) {
			queryModel.combCondition(str);
		}
		List<AdminUser> adminUsers = dateBaseDao.findLists(AdminUser.class, queryModel);
		return adminUsers;
	}

	@Override
	public void addGroup(AdminUser adminUser) {
		adminUserDAO.saveOrUpdate(adminUser);
		
	}

	
	@Override
	public List<AdminUser> findBySeller() {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		
		List<AdminUser> adminUsers = dateBaseDao.findLists(AdminUser.class, queryModel);
		return adminUsers;
	}
}