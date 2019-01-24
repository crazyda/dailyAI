package com.axp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class AdminUserDAOImpl extends BaseDaoImpl<AdminUser> implements AdminUserDAO {

    /**
     * 获得登陆用户
     *
     * @param loginname
     * @param password
     * @return
     */
    @Override
    public AdminUser getLoginAdminUser(String loginname) {
        AdminUser adminUser = null;
        Session session = sessionFactory.getCurrentSession();
        String queryString = "from AdminUser as model where model.isvalid = true and model.loginname=:loginname";
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("loginname", loginname);
        List<AdminUser> list = queryObject.list();
        if (list.size() > 0) {
            adminUser = list.get(0);
        }
        return adminUser;
    }
    
    @Override
    public AdminUser getLoginAdminUserBySellerId(Integer sellerId) {
        AdminUser adminUser = null;
        Session session = sessionFactory.getCurrentSession();
        String queryString = "from AdminUser as model where model.sellerId=:sellerId and  model.isvalid = true ";
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("sellerId", sellerId);
        List<AdminUser> list = queryObject.list();
        if (list.size() > 0) {
            adminUser = list.get(0);
        }
        return adminUser;
    }

    /**
     * 保存用户邀请码
     *
     * @param adminUser
     */
    @Override
    public void updateInviteCode(AdminUser adminUser) {
        String invitecode = "1" + adminUser.getId();
        adminUser.setInvitecode(invitecode);
        saveOrUpdate(adminUser);
    }

    @Override
    public List<AdminUser> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "from AdminUser as model where model.isvalid=:isvalid";
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("isvalid", true);
        return queryObject.list();
    }

    /**
     * 获得当前已登录用户
     */
    @Override
    public AdminUser getCurrentUser(HttpServletRequest request) {
        Object id = request.getSession().getAttribute("currentUserId");
        int currentUserId = Integer.parseInt(id.toString());
        AdminUser currentUser = this.findById(currentUserId);
        return currentUser;
    }

    /**
     * 获得总部对象
     */
    @Override
    public AdminUser getHQ() {
        AdminUser admin = null;
        Session session = sessionFactory.getCurrentSession();
        String queryString = "from AdminUser as model where model.level = " + StringUtil.ADMIN + " and model.isvalid=:isvalid";
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("isvalid", true);
        List<AdminUser> list = queryObject.list();
        if (list.size() > 0) {
            admin = list.get(0);
        }
        return admin;
    }
    
    /**
     * 获得所有下级
     */
//    public AdminUser getSubUsers(List<AdminUser> list) {
//    	List<AdminUser> childs = new 
//    	Iterator<AdminUser> iterator = list.iterator();
//    	while(iterator.hasNext()){
//    		AdminUser current = iterator.next();
//    		
//            if (list.size() > 0) {
//            	subs.addAll(list);
//            }
//    	}
//		
//        return admin;
//    }
    
    //获取所有子用户id
    @Override
    public List<AdminUser> getChilds(Integer adminUserId){
    	List<Integer> all = new ArrayList<Integer>();
    	Session session = sessionFactory.getCurrentSession();
		String queryString = "select model.id from AdminUser as model where model.parentId = :parentId and model.isvalid=:isvalid";
		Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("parentId", adminUserId);
        queryObject.setParameter("isvalid", true);
        List<Integer> idList = queryObject.list();
        if(idList.size()==0){
        	return new ArrayList<AdminUser>();
        }else{
        	List<Integer> ids = getChildIds(idList,all);
        	ids.addAll(idList);
        	queryString = "from AdminUser as model where model.id in :ids";
    		queryObject = session.createQuery(queryString);
            queryObject.setParameterList("ids", ids);
            return queryObject.list();
        }
    }
    
  //获取所有子用户id
    @Override
    public List<AdminUser> getChildsByLevel(Integer adminUserId,Integer level){
    	List<Integer> all = new ArrayList<Integer>();
    	Session session = sessionFactory.getCurrentSession();
		String queryString = "select model.id from AdminUser as model where model.parentId = :parentId and model.isvalid=:isvalid and model.level=:level";
		Query queryObject = session.createQuery(queryString);
        queryObject.setParameter("parentId", adminUserId);
        queryObject.setParameter("isvalid", true);
        queryObject.setParameter("level", level);
        List<Integer> idList = queryObject.list();
        if(idList.size()==0){
        	return new ArrayList<AdminUser>();
        }else{
        	List<Integer> ids = getChildIds(idList,all);
        	ids.addAll(idList);
        	queryString = "from AdminUser as model where model.id in :ids";
    		queryObject = session.createQuery(queryString);
            queryObject.setParameterList("ids", ids);
            return queryObject.list();
        }
    }
    
    
    public List<Integer> getChildIds(List<Integer> ids,List<Integer> all){
    	Session session = sessionFactory.getCurrentSession();
		String queryString = "select model.id from AdminUser as model where model.parentId in :parentIds and model.isvalid=:isvalid";
		Query queryObject = session.createQuery(queryString);
        queryObject.setParameterList("parentIds", ids);
        queryObject.setParameter("isvalid", true);
        List<Integer> idList = queryObject.list();
        if(idList.size()==0){
        	return all;
        }else{
        	all.addAll(idList);
        	return getChildIds(idList,all);
        }
    }
    
    @Override
    public Long getCountByRoleId(Integer roleId) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createQuery("select count(id) from AdminUser where isvalid=1 and rePermissionRoleId=:roleId")
                .setParameter("roleId", roleId).uniqueResult();
    }
    
    @Override
    public List<AdminUser> findByRoleId(Integer roleId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AdminUser where rePermissionRoleId=:roleId and isvalid=1")
                .setParameter("roleId", roleId).list();
    }
    
    @Override
    public List<AdminUser> findByLevel(Integer level) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AdminUser where level=:level and isvalid=1")
                .setParameter("level", level).list();
    }

	
	@Override
	public List<AdminUser> findByunion(Integer current_user_id) {
		 Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from AdminUser where level=65 and isvalid=1 and parentId=:parentId")
		         .setParameter("parentId", current_user_id).list();
	}
}
