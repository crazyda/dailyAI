package com.axp.service.goods.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.model.ReGoodsStandard;
import com.axp.query.PageResult;
import com.axp.service.goods.ReGoodsStandartService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Service
public class ReGoodsStandardServiceImpl extends BaseServiceImpl implements ReGoodsStandartService {

    @Override
    public PageResult<ReGoodsStandard> getReGoodsStandardsByAdminUserId(Integer id, Integer currentPage, Integer pageSize) {
        return reGoodsStandardDAO.getReGoodsStandardsByAdminUserId(id, currentPage, pageSize);
    }

    @Override
    public Boolean doSave(String name, List<String> details, Integer adminUserId) {
        try {
          //保存一级商品规格；
           ReGoodsStandard r = new ReGoodsStandard();
           r.setName(name);
            r.setAdminUserId(adminUserId);
            reGoodsStandardDAO.save(r);

            //保存二级商品规格；
            for (String each : details) {
                if (StringUtil.hasLength(each)) {
                    ReGoodsStandard s = new ReGoodsStandard();
                    s.setName(each);
                    s.setAdminUserId(adminUserId);
                    s.setParentStandardId(r.getId());
                    reGoodsStandardDAO.save(s);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    

            return false;
        }
    }

  /*  @Override
    public Boolean doEdit(Integer id, Integer adminUserId, String name, List<String> details) {
        try {
            //先把所有这个一级商品规格，及其与其关联的二级商品规格删掉；
            Boolean d = doDelete(id);
            if (!d) {
                return false;
            }

            //重新保存更改后的一级，二级商品规格；
            Boolean b = doSave(name, details, adminUserId);
            if (b) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    

            return false;
        }
    }*/
    
    
    @Override
    public Boolean doEdit(Integer id, Integer adminUserId, String name, List<String> details,List<String> detailsId) {
        try {
        	//一级规格
        	ReGoodsStandard r = reGoodsStandardDAO.findById(id);
        	for (String detail : detailsId) {
        		Map<String, String> map=new HashMap<String, String>();
        		String[] split = detail.split(",");
        		map.put(split[1],split[0]);
        		Set<String> keySet = map.keySet();
        		for (String key : keySet) {
        			String value = map.get(key); 
        			if(value.equals("0")){
        				ReGoodsStandard goodsStandard = new ReGoodsStandard();
        				 goodsStandard.setName(key);
   	                	 goodsStandard.setAdminUserId(adminUserId);
   	                	 goodsStandard.setParentStandardId(r.getId());
   	                     reGoodsStandardDAO.saveOrUpdate(goodsStandard);
        			}else{
        				ReGoodsStandard goodsStandard = reGoodsStandardDAO.findById(Integer.parseInt(value));
    					goodsStandard.setName(key);
    	   	            goodsStandard.setAdminUserId(adminUserId);
    	   	            goodsStandard.setParentStandardId(r.getId());
    	   	            reGoodsStandardDAO.saveOrUpdate(goodsStandard);
    					}
        				 
        			}
					
				}
        		
        	return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    

            return false;
        }
    }

    @Override
    public Boolean doDelete(Integer id) {
        try {
            ReGoodsStandard parent = reGoodsStandardDAO.findById(id);//一级商品规格；
            parent.setIsValid(false);
            if (parent != null) {
                List<ReGoodsStandard> childList = reGoodsStandardDAO.getChildrenGoodsStandardByParent(parent);//对应的二级商品规格；
                if (childList != null && childList.size() > 0) {//删除所有二级商品规格；
                    for (ReGoodsStandard each : childList) {
                        each.setIsValid(false);
                    }
                }
            }
            return true;
        } catch (Exception e) {
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    

            return null;
        }
    }

    @Override
    public List<ReGoodsStandard> getGoodsStandardAndDetails(Integer id) {
        ReGoodsStandard r = reGoodsStandardDAO.findById(id);
        if (r == null) {
            return null;
        }
        List<ReGoodsStandard> childList = reGoodsStandardDAO.getChildrenGoodsStandardByParent(r);
        childList.add(0, r);//将一级商品规格插入到集合的首位；
        return childList;
    }

    @Override
    public List<ReGoodsStandard> getFirstStandardWithSecondStandard(Integer adminUserId) {
        List<ReGoodsStandard> standardsList = reGoodsStandardDAO.getReGoodsStandardsByAdminUserId(adminUserId);

        //对这个list进行分组；
        List<ReGoodsStandard> returnList = new ArrayList<>();
        for (ReGoodsStandard each : standardsList) {
            if (each.getParentStandardId() == null) {
                returnList.add(each);
            }
        }
        for (ReGoodsStandard each : standardsList) {
            if (each.getParentStandardId() != null) {
                Integer id = each.getParentStandardId();
                for (ReGoodsStandard each2 : returnList) {
                    if (each2.getId().equals(id)) {
                        each2.getSecondStandards().add(each);
                    }
                }
            }
        }
        for (int i = 0; i < returnList.size(); i++) {
            ReGoodsStandard each = returnList.get(i);
            if (each.getSecondStandards().size() == 0) {
                returnList.remove(each);
                i--;
            }
        }
        Iterator<ReGoodsStandard> iterator = returnList.iterator();
        while (iterator.hasNext()) {
            ReGoodsStandard next = iterator.next();
            if (next.getSecondStandards().size() == 0) {
                iterator.remove();
            }
        }
        return returnList;
    }

    @Override
    public boolean checkExist(String name, Integer adminUserId) {
        return reGoodsStandardDAO.checkExist(name, adminUserId);
    }

}
