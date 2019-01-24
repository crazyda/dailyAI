package com.axp.service.professional.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.axp.model.MessageType;
import com.axp.model.OrderMessageList;
import com.axp.model.SystemMessageList;
import com.axp.service.professional.MessageTypeService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("MessageTypeService")
public class MessageTypeServiceImpl extends BaseServiceImpl implements MessageTypeService {

	@Override
	public void list(HttpServletRequest request,HttpServletResponse response) {
		 	String pagestr = request.getParameter("page");
			PageInfo pageInfo = new PageInfo();
			int count = 0;
		    count = messageTypeDao.getAllCountAndCon(MessageType.class,"isValid=true");
			Utility.setPageInfomation(pageInfo, pagestr, "20",count);
			int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
			int end = pageInfo.getPageSize();
			pageInfo.setParam("&page=");
			List<MessageType> typelist = messageTypeDao.findByPageOrderAndCon(MessageType.class, " isValid=true ", "id", "desc",start,end);
			request.setAttribute("typelist", typelist);
			request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public void add(Integer id,HttpServletRequest request) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("id", id);
		queryModel.combPreEquals("isValid", true);
		if(id!=null){
			MessageType mt = (MessageType) dateBaseDao.findOne(MessageType.class, queryModel);	
			request.setAttribute("mt", mt);			
			queryModel.clearQuery();
			id=null;			
			List<MessageType> mlist= dateBaseDao.findPageList(MessageType.class, queryModel, 0, 0);
			request.setAttribute("mlist", mlist);			
		}else{
			List<MessageType> mlist= dateBaseDao.findPageList(MessageType.class, queryModel, 0, 0);
			request.setAttribute("mlist", mlist);
		}
	}

	@Override
	public void save(Integer id,Integer level,Integer isorder,String iconPic,HttpServletRequest request) {
		String title = request.getParameter("title");
		QueryModel queryModel = new QueryModel();
		MessageType mt = null;
		if(id!=null&&id>0){
			queryModel.clearQuery();
			queryModel.combPreEquals("id",id);
			mt = (MessageType) dateBaseDao.findOne(MessageType.class, queryModel);
		}else{
			mt = new MessageType();
		}
		mt.setTitle(title);
		mt.setIcon(iconPic);
		mt.setIsValid(true);
		mt.setLevel(1);		
		if(level!=null&&level>0){
			mt.setLevel(2);			
			queryModel.clearQuery();
			queryModel.combPreEquals("id",level);
			MessageType temp = (MessageType) dateBaseDao.findOne(MessageType.class, queryModel);
			if(temp.getMessageType()==null){
				mt.setIsorder(temp.getIsorder());	
			}
			mt.setMessageType(temp);
		}
		mt.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		if(mt.getId()==null){
			messageTypeDao.save(mt);
		}else{
			messageTypeDao.merge(mt);
		}
	}

	@Override
	public void del(Integer id,HttpServletRequest request) {
		MessageType messageType=messageTypeDao.findById(id);
		QueryModel model=new QueryModel();
	
		if (messageType.getLevel()==1) {//删除一级类别，连同下级子类一起删除
			model.clearQuery();
			model.combEquals("parentId", id);
			List<MessageType> list =dateBaseDao.findLists(MessageType.class, model);
			
			if (list.size()==0) {
				messageType.setIsValid(false);
				messageTypeDao.merge(messageType);				
			}else{
				Integer i=0;
			if (list!=null&&list.size()>0) {
				for (MessageType messageType2:list) {
					model.clearQuery();
					model.combPreEquals("isValid", true);
					model.combEquals("typeId", messageType2.getId());
			List<SystemMessageList> syslist =dateBaseDao.findLists(SystemMessageList.class, model);	
					model.clearQuery();
					model.combPreEquals("isValid", true);
					model.combEquals("typeId", messageType2.getId());
		    List<OrderMessageList> ormlist =dateBaseDao.findLists(OrderMessageList.class, model);		
		    i=syslist.size()+ormlist.size()+i;
			   if (i>0) {				
                 break;
				}    
				}
				
			}
			if (i==0) {	
				for (MessageType messageType2:list) {
				messageType2.setIsValid(false);
				messageTypeDao.merge(messageType2);
				}
				messageType.setIsValid(false);
				messageTypeDao.merge(messageType);
				      }			
			}
	
		}else{		
				model.clearQuery();
				model.combPreEquals("isValid", true);
				model.combEquals("typeId", messageType.getId());
		List<SystemMessageList> syslist =dateBaseDao.findLists(SystemMessageList.class, model);	
				model.clearQuery();
				model.combPreEquals("isValid", true);
				model.combEquals("typeId", messageType.getId());
		List<OrderMessageList> ormlist =dateBaseDao.findLists(OrderMessageList.class, model);		
		
		if (syslist.size()==0&&ormlist.size()==0) {		
		messageType.setIsValid(false);
		messageTypeDao.merge(messageType);
		}
		}
	}

}
