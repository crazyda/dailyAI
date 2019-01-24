package com.axp.service.system.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.MessageType;
import com.axp.model.OrderMessageList;
import com.axp.model.ProvinceEnum;
import com.axp.model.ReGoodsorder;
import com.axp.model.SystemMessageList;
import com.axp.model.UserLoginRecord;
import com.axp.model.UserOrderMessage;
import com.axp.model.UserSystemMessage;
import com.axp.model.Users;
import com.axp.service.professional.UserService;
import com.axp.service.system.UserSystemMessageService;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.ToolSpring;
import com.axp.util.UrlUtil;
import com.axp.util.Utility;
import com.push.ImpAppInformation;


@Service("UserSystemMessageService")
public class UserSystemMessageServiceImpl extends BaseServiceImpl implements UserSystemMessageService {

	@Source 
	private UserService userService;

	@Override
	public void saveallmessage(Integer userId, Integer orderId, String typeId,
			String content, String title,HttpServletRequest request){
		this.saveSystemMessage(content, Integer.parseInt(typeId), title, 0d, 11,null);
	}
	
	@Override
	public void saveallmessageGame(Integer userId, Integer orderId, String typeId,
			String content, String title,HttpServletRequest request,List<String> gameBroadContents){
		this.saveSystemMessage(content, Integer.parseInt(typeId), title, 0d, 11,gameBroadContents);
	}
	
	

	/**
	 * @param content
	 * @param parseInt
	 * @param title
	 * @param d
	 * @param i
	 * @param gameBroadContents
	 */
	private void saveSystemMessageGame(String content, int parseInt,
			String title, double d, int i, List<String> gameBroadContents) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savesysmessage( String typeId, String content,
			String title, HttpServletRequest request) {
		
				
	}

	@Override
	public void saveordmessage(Integer userId,Integer orderId, String typeId, String content,
			String title, HttpServletRequest request) {
		MessageType messageType = messageTypeDao.findById(Integer.parseInt(typeId));
		OrderMessageList orm=new OrderMessageList();   
		UserOrderMessage uom=new UserOrderMessage();
		orm.setMessageType(messageType);		
		orm.setContent(content);
		orm.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		orm.setIsValid(true);
		orm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		orm.setTitle(title);
		Users user = usersDAO.findById(userId);			
		orm.setUsers(user);
		ReGoodsorder regoodsorder =regoodsorderDAO.findById(orderId);
		orm.setReGoodsorder(regoodsorder);		
		orderMessageListDao.save(orm);
		

		uom.setOrderMessageLists(orm);
		uom.setUsers(user);
		uom.setIsRead(0);
		uom.setIsValid(true);
		uom.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		uom.setMessageType(messageType);
		userOrderMessageDao.save(uom);
		
		
		
	}

	@Override
	public void saveAssetsmessage(Integer userId, String typeId, String content,
			String title, HttpServletRequest request) {

		MessageType messageType = messageTypeDao.findById(Integer.parseInt(typeId));
		SystemMessageList sml = new SystemMessageList();
		UserSystemMessage usm=new UserSystemMessage();
        sml.setMessageType(messageType);
        sml.setContent(content);
        sml.setIsValid(true);
		sml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setTitle(title);
				
		if(sml.getId()==null){
		   systemMessageListDao.save(sml);
		}else{
			systemMessageListDao.merge(sml);
		}
		Users user = usersDAO.findById(userId);
        usm.setUsers(user);
		usm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		usm.setIsValid(true);
		usm.setSystemMessageList(sml);
		usm.setIsRead(0);
		usm.setMessageType(messageType);
		userSystemMessageDao.save(usm);		
	}

	@Override
	public void push(String content,String title,String cid,HttpServletRequest request) {
		  //  int id = (Integer) request.getSession().getAttribute("id");
			try {
				//String cidString="100,20,200";
				List<String> cidIOS = usersDAO.findInId("id", cid);
				ImpAppInformation push = new ImpAppInformation();
				push.pushSystemMessageToListAPP(title, content, cidIOS);
				push.pushSystemMessageCenterToApp(title, content);
				System.out.println("推送成功");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("推送失败");
			}
			
	}
	public void push_message(String content,String title,String cid) {
	    
		try {
			List<String> cidIOS = new ArrayList<String>();
			cidIOS.add(cid);
			ImpAppInformation push = new ImpAppInformation();
			push.pushSystemMessageToListAPP(title, content, cidIOS);
			push.pushSystemMessageCenterToApp(title, content);
			System.out.println("推送成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("推送失败");
		}
		
    }

	
	@Override
	public void list(HttpServletRequest request, HttpServletResponse response) {
		
		AdminUser adminUser = adminUserDAO.getCurrentUser(request);
		String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		String sql="isValid=true";
		if(adminUser.getLevel()<95){
			sql+=" and senAdminUser.id="+adminUser.getId();
		}
		int count = 0;
	    count = systemMessageListDao.getAllCountAndCon(SystemMessageList.class,sql);
		Utility.setPageInfomation(pageInfo, pagestr, "20",count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		pageInfo.setParam("&page=");
		List<SystemMessageList> messagelist = systemMessageListDao.findByPageOrderAndCon(SystemMessageList.class, sql, "id", "desc",start,end);
		request.setAttribute("messagelist", messagelist);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public void add(Integer id, HttpServletRequest request) {
		QueryModel queryModel = new QueryModel();
		if(id!=null){
			queryModel.clearQuery();
			queryModel.combEquals("parentId", id);
			queryModel.combPreEquals("isValid", true);
			List<MessageType> mlist1= dateBaseDao.findLists(MessageType.class, queryModel);
			request.setAttribute("mlist", mlist1);
		}else {
			queryModel.clearQuery();
			queryModel.combPreEquals("isValid", true);
			queryModel.combInStr("id", "1,12");
			List<MessageType> mlist= dateBaseDao.findLists(MessageType.class, queryModel);
			request.setAttribute("mlist", mlist);			
		}

		

			
		
	}

	@Override
	public Map<String, Object> saveMessage(String content, Integer type, String title,
			List<Users> ulist, String orderId, double money,Integer state) {
		Map<String,Object> statusMap = new HashMap<String, Object>();
		MessageType messageType=new MessageType();
		messageType =messageTypeDao.findById(type);	
		for(Users user:ulist){
			if (messageType.getIsorder()==0) {
				SystemMessageList sml = new SystemMessageList();
				UserSystemMessage usm=new UserSystemMessage();
		        sml.setMessageType(messageType);
		        sml.setContent(content);
		        sml.setIsValid(true);
				sml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
				sml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				sml.setTitle(title);
				sml.setMoney(money);
				sml.setMoneyState(state==null?1:state);
				systemMessageListDao.save(sml);
				usm.setUsers(user);	        
				usm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				usm.setIsValid(true);
				usm.setSystemMessageList(sml);
				usm.setIsRead(0);	
				usm.setMessageType(messageType);
				userSystemMessageDao.save(usm);	
				ImpAppInformation push = new ImpAppInformation();
				push.pushMessage(user.getId()+"", user.getUserid(), title, content,sml.getId()+"",messageType.getMessageType().getId()+"","1",messageType.getTitle());
			}else if (messageType.getIsorder()==1){
				OrderMessageList oml = new OrderMessageList();
				UserOrderMessage uom = new UserOrderMessage();
				ReGoodsorder rgs = regoodsorderDAO.findById(Integer.parseInt(orderId));
				if(rgs!=null){
				oml.setReGoodsorder(rgs);
				oml.setMessageType(messageType);
				oml.setContent(content);
				oml.setIsValid(true);
				oml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
				oml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				oml.setTitle(title);
				orderMessageListDao.save(oml);
				uom.setUsers(user);
				uom.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				uom.setIsValid(true);
				uom.setIsRead(0);
				uom.setMessageType(messageType);
				uom.setOrderMessageLists(oml);
				userOrderMessageDao.save(uom);
				ImpAppInformation push = new ImpAppInformation();
				push.pushMessage(user.getId()+"", user.getUserid(), title, content,oml.getId()+"",messageType.getMessageType().getId()+"","1",messageType.getTitle());
				}
			}
		}
		return statusMap;
	}
	
	
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED) //不要事务
	public void saveSystemMessage(String content, Integer type, String title,
			 double money,Integer state,List<String> gameBroadContents){
		if(type==4){ //用户系统消息
			SenMessage impl=new SenMessage();
			Thread thread=new Thread(impl);
			impl.content=content; 
			impl.typeId=type;
			impl.title=title;
			impl.sml=new SystemMessageList();
			impl.usm=new UserSystemMessage();
			thread.start();
		}else if(type==14){ //商家系统消息
			SendSellerMessage sellerMessage=new SendSellerMessage();
			Thread thread=new Thread(sellerMessage);
			sellerMessage.content=content; 
			sellerMessage.typeId=type;
			sellerMessage.title=title;
			sellerMessage.sml=new SystemMessageList();
			sellerMessage.usm=new UserSystemMessage();
			thread.start();
		}else if(type == 15){
			SendUserMessage userMessage=new SendUserMessage();
			Thread thread=new Thread(userMessage);
			userMessage.content=content; 
			userMessage.typeId=type;
			userMessage.title=title;
			userMessage.gameBroadContents = gameBroadContents;
			userMessage.sml=new SystemMessageList();
			userMessage.usm=new UserSystemMessage();
			thread.start();
		
			
			
		}
		
		
		
	}

class SendUserMessage implements Runnable{

	public String content;
	
	public Integer typeId;
	
	public String title;
	public List<String> gameBroadContents;
	
	public SystemMessageList sml=null;
	
	public UserSystemMessage usm=null;
	
	@Override
	public void run() {


		SessionFactory	sessionFactory= (SessionFactory) ToolSpring.getBean("sessionFactory");
		Session	session=sessionFactory.openSession();	
		Transaction transaction = session.getTransaction();
//		System.out.println("开始发送用户系统消息线程,开启时间"+ new Date()); 
		long start=	System.currentTimeMillis();
		try {
		transaction.begin();  //开启事务 	

		int count=Integer.parseInt(session.createQuery("select count(*) from Users where isvalid = true  ").uniqueResult().toString());

		MessageType messageType =(MessageType) session.createQuery("from MessageType where isValid=true and id="+typeId).uniqueResult();
		
		sml.setMessageType(messageType);
	    sml.setContent(content);
	    sml.setIsValid(true);
		sml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setTitle(title);
		sml.setMoney(0d);
		sml.setMoneyState(1);
		session.save(sml);
		transaction.commit();
		session.close();
		List<Users> alist=new ArrayList<Users>(3000);
		int size=100;
		int num=(count%size==0)?(count/size):(count/size+1);   
		int k=0;
		for(int i=0;i<num;i++){
			Session messageSession=sessionFactory.openSession();
			Transaction messageTransaction=messageSession.getTransaction();
			messageTransaction.setTimeout(120000); //2分钟
			messageTransaction.begin();

			alist= messageSession.createQuery("from Users where isvalid = true "  ).setFirstResult(i*size).setMaxResults(size).list();
			
			for(Users user:alist){
					UserSystemMessage usm=new UserSystemMessage();
					usm.setUsers(user);       
					usm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					usm.setIsValid(true);
					usm.setSystemMessageList(sml);
					usm.setIsRead(0);	
					usm.setMessageType(messageType);
					messageSession.save(usm);
					if(k%1000==0){
						messageSession.flush();
						messageSession.clear();
					}
					k++;
//					System.out.println("保存第"+k+"条,用户消息!");
			}

			messageTransaction.commit();
			messageSession.close();
			//发送消息线程
			PushInfo pushInfo=new PushInfo();
			Thread pushThread=new Thread(pushInfo);
			pushInfo.content=content;
			pushInfo.sml=sml;
			pushInfo.title=title;
			pushInfo.gameBroadContents = gameBroadContents;
			pushInfo.ulist = alist;
			pushInfo.messageType=messageType;
			pushInfo.typeId=typeId;
			pushThread.start();
			Thread.currentThread().sleep(100); 

		}
		long end=System.currentTimeMillis();
		System.out.println("发送全局用户系统消息完成,总条数:"+count+",花费时间"+((end-start)/1000));
		} catch (Exception e) {
			//transaction.rollback();
			e.printStackTrace();
			System.out.println("发送全局用户系统消息失败"+e.getMessage());
		}
	}
}
		
	

class SendSellerMessage implements Runnable{

	public String content;
	
	public Integer typeId;
	
	public String title;
	
	public SystemMessageList sml=null;
	
	public UserSystemMessage usm=null;
	
	@Override
	public void run() {


		SessionFactory	sessionFactory= (SessionFactory) ToolSpring.getBean("sessionFactory");
		Session	session=sessionFactory.openSession();	
		Transaction transaction = session.getTransaction();
//		System.out.println("开始发送商家系统消息线程,开启时间"+ new Date()); 
		long start=	System.currentTimeMillis();
		try {
		transaction.begin();  //开启事务 	

		int count=Integer.parseInt(session.createQuery("select count(*) from AdminUser where isvalid = true and channelid is not null and length(channelid) = 32 ").uniqueResult().toString());

		MessageType messageType =(MessageType) session.createQuery("from MessageType where isValid=true and id="+typeId).uniqueResult();
		
		sml.setMessageType(messageType);
	    sml.setContent(content);
	    sml.setIsValid(true);
		sml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setTitle(title);
		sml.setMoney(0d);
		sml.setMoneyState(1);
		session.save(sml);
		transaction.commit();
		session.close();
		List<AdminUser> alist=new ArrayList<AdminUser>(3000);
		int size=100;
		int num=(count%size==0)?(count/size):(count/size+1);   
		int k=0;
		for(int i=0;i<num;i++){
			Session messageSession=sessionFactory.openSession();
			Transaction messageTransaction=messageSession.getTransaction();
			messageTransaction.setTimeout(120000); //2分钟
			messageTransaction.begin();

			alist= messageSession.createQuery("from AdminUser where isvalid = true and channelid is not null and length(channelid) = 32  "  ).setFirstResult(i*size).setMaxResults(size).list();
			
			for(AdminUser user:alist){
					UserSystemMessage usm=new UserSystemMessage();
					usm.setAdminUser(user);	        
					usm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					usm.setIsValid(true);
					usm.setSystemMessageList(sml);
					usm.setIsRead(0);	
					usm.setMessageType(messageType);
					messageSession.save(usm);
					if(k%1000==0){
						messageSession.flush();
						messageSession.clear();
					}
					k++;
//					System.out.println("保存第"+k+"条,商家消息!");
			}

			messageTransaction.commit();
			messageSession.close();
			//发送消息线程
			PushInfo pushInfo=new PushInfo();
			Thread pushThread=new Thread(pushInfo);
			pushInfo.content=content;
			pushInfo.sml=sml;
			pushInfo.title=title;
			pushInfo.alist=alist;
			pushInfo.messageType=messageType;
			pushInfo.typeId=typeId;
			pushThread.start();
			Thread.currentThread().sleep(100); 

		}
		long end=System.currentTimeMillis();
		System.out.println("发送全局商家系统消息完成,总条数:"+count+",花费时间"+((end-start)/1000));
		} catch (Exception e) {
			//transaction.rollback();
			e.printStackTrace();
			System.out.println("发送全局商家系统消息失败"+e.getMessage());
		}
	}
}
	
	

class SenMessage  implements  Runnable{
	
	public String content;
	
	public Integer typeId;
	
	public String title;
	
	public SystemMessageList sml=null;
	
	public UserSystemMessage usm=null;
	
	 
	@Override
	public void run() {
		SessionFactory	sessionFactory= (SessionFactory) ToolSpring.getBean("sessionFactory");
		Session	session=sessionFactory.openSession();	
		Transaction transaction = session.getTransaction();
//		System.out.println("开始发送系统消息线程,开启时间"+ new Date()); 
		long start=	System.currentTimeMillis();
		try {
		transaction.begin();  //开启事务 	

		int count=Integer.parseInt(session.createQuery("select count(*) from Users where isvalid = true and userid is not null and length(userid) = 32 ").uniqueResult().toString());

		MessageType messageType =(MessageType) session.createQuery("from MessageType where isValid=true and id="+typeId).uniqueResult();
		
		sml.setMessageType(messageType);
	    sml.setContent(content);
	    sml.setIsValid(true);
		sml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sml.setTitle(title);
		sml.setMoney(0d);
		sml.setMoneyState(1);
		session.save(sml);
		transaction.commit();
		session.close();
		List<Users> ulist=new ArrayList<Users>(3000);
		int size=100;
		int num=(count%size==0)?(count/size):(count/size+1);
		int k=0;
		for(int i=0;i<num;i++){

			Session messageSession=sessionFactory.openSession();
			Transaction messageTransaction=messageSession.getTransaction();
			messageTransaction.setTimeout(120000); //2分钟
			messageTransaction.begin();
			ulist= messageSession.createQuery("from Users where isvalid = true and userid is not null and length(userid) = 32 "  ).setFirstResult(i*size).setMaxResults(size).list();
			for(Users user:ulist){
					UserSystemMessage usm=new UserSystemMessage();
					usm.setUsers(user);	        
					usm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					usm.setIsValid(true);
					usm.setSystemMessageList(sml);
					usm.setIsRead(0);	
					usm.setMessageType(messageType);
					messageSession.save(usm);
					if(k%1000==0){
						messageSession.flush();
						messageSession.clear();
					}
					k++;
//					System.out.println("保存第"+k+"条,用户消息!");
			}

			messageTransaction.commit();
			messageSession.close();
			//发送消息线程
			PushInfo pushInfo=new PushInfo();
			Thread pushThread=new Thread(pushInfo);
			pushInfo.content=content;
			pushInfo.sml=sml;
			pushInfo.title=title;
			pushInfo.ulist=ulist;
			pushInfo.messageType=messageType;
			pushInfo.typeId=typeId;
			pushThread.start();
			Thread.currentThread().sleep(100); 

		}
		long end=System.currentTimeMillis();
		System.out.println("发送全局系统完成,总条数:"+count+",花费时间"+((end-start)/1000));
		} catch (Exception e) {
			//transaction.rollback();
			e.printStackTrace();
			System.out.println("发送系统消息失败"+e.getMessage());
		}
	}
		
	}


	@Override
	public void saveMessages(HttpServletRequest request, Integer orderId, Integer userId, String typeId, String content,
			String title, String province, String city, String county,String image,String icon) {
		this.saveActivityMessage(request,content, Integer.parseInt(typeId), title, 0d, 0, province, city, county,image,icon);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void saveActivityMessage(HttpServletRequest request,String content, Integer type, String title,
			 double money,Integer state,String province, String city, String county,String image,String icon){
		Integer zoneId;
		if(!county.equals("-1")){
			zoneId = Integer.parseInt(county);
		}else if(!city.equals("-1")){
			zoneId = Integer.parseInt(city);
		}else{
			zoneId = Integer.parseInt(province);
		}
		AdminUser currentUser = adminUserDAO.getCurrentUser(request);
		SendActiviMessage impl=new SendActiviMessage();
		Thread thread=new Thread(impl);
		impl.content=content; 
		impl.typeId=type;
		impl.title=title;
		impl.zoneId = zoneId;
		impl.image = image;
		impl.icon = icon;
		impl.sml=new SystemMessageList();
		impl.usm=new UserSystemMessage();
		impl.senAdminUser=currentUser;
		thread.start();
		}
	
	
	
	class SendActiviMessage  implements  Runnable{
		
		public String content;
		
		public Integer typeId;
		
		public String title;
		
		public Integer zoneId;
		
		public String image;
		
		public String icon;
		
		public SystemMessageList sml=null;
		
		public UserSystemMessage usm=null;
		
		public AdminUser senAdminUser;
		 
		@Override
		public void run() {
			SessionFactory	sessionFactory= (SessionFactory) ToolSpring.getBean("sessionFactory");
			Session	session=sessionFactory.openSession();	
			Transaction transaction = session.getTransaction();
//			System.out.println("开始发送系统消息线程,开启时间"+ new Date()); 
			long start=	System.currentTimeMillis();
			
			try {
			transaction.begin();  //开启事务 	
			Date endDate = new Date();
			endDate=DateUtil.addDay2Date(1, endDate);
			 
			
			 Calendar cl = Calendar.getInstance();
			 cl.setTime(endDate);
			 cl.add(Calendar.DATE, -7);
			 Date startDate = cl.getTime();
			 
			 SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
			 String startTime = dd.format(startDate);
			 String endTime = dd.format(endDate);
			 
			 
			int count=0;
			List<UserLoginRecord> Ulist=new ArrayList<UserLoginRecord>(3000);
			ProvinceEnum provinceEnum =null;
			if(zoneId!=null&&zoneId!=-1){
				provinceEnum= (ProvinceEnum) session.createQuery("from ProvinceEnum where isValid=true and id="+zoneId).uniqueResult();
				if (provinceEnum.getLevel()==1) {
					Query q = session.createQuery("  from UserLoginRecord"
							+ " where users.userid is not null and provinceEnum.provinceEnum.id=? or provinceEnum.provinceEnum.provinceEnum.id=?"
							+ " and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id ");
					q.setParameter(0, provinceEnum.getId());
					q.setParameter(1, provinceEnum.getId());
					count = q.list().size();  
				}else if(provinceEnum.getLevel2()==2){
					Query q = session.createQuery("from UserLoginRecord"
							+ " where users.userid is not null and provinceEnum.provinceEnum2.id=? or provinceEnum.id=?"
							+ " and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id");
					q.setParameter(0, provinceEnum.getId());
					q.setParameter(1, provinceEnum.getId());
					count =q.list().size();
				}else{
					count=session.createQuery(" from UserLoginRecord where users.userid is not null  and provinceEnum.id="+zoneId+" "
							+ "  and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id").list().size();
				}
			}else{
				 count=session.createQuery(" from UserLoginRecord where users.userid is not null  "
				 		+ "  and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id").list().size();
			}

			MessageType messageType =(MessageType) session.createQuery("from MessageType where isValid=true and id="+typeId).uniqueResult();
			
			
			sml.setMessageType(messageType);
		    sml.setContent(content);
		    sml.setIsValid(true);
			sml.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
			sml.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sml.setTitle(title);
			sml.setMoney(0d);
			sml.setMoneyState(1);
			sml.setProvinceEnum(provinceEnum);
			sml.setImage(image);
			sml.setIcon(icon);
			sml.setSenAdminUser(senAdminUser);
			session.save(sml);
			transaction.commit();
			session.close();
		
			List<Users> ulist =null;
			int size=100;
			int num=(count%size==0)?(count/size):(count/size+1);
			for(int i=0;i<num;i++){

				Session messageSession=sessionFactory.openSession();
				Transaction messageTransaction=messageSession.getTransaction();
				messageTransaction.setTimeout(120000); //2分钟
				messageTransaction.begin();
				
				if(provinceEnum==null){
					Ulist= messageSession.createQuery("from UserLoginRecord where users.userid is not null "
							+ " group by users.id order by lasttime ").setFirstResult(i*size).setMaxResults(size).list();
				}
				else if (provinceEnum.getLevel()==1) {
					Query q = messageSession.createQuery("from UserLoginRecord"
							+ " where users.userid is not null  and provinceEnum.provinceEnum.id="+provinceEnum.getId()+" or provinceEnum.provinceEnum.provinceEnum.id="+provinceEnum.getId()+""
									+ "  and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id ");
					Ulist = q.setFirstResult(i*size).setMaxResults(size).list();
				}else if(provinceEnum.getLevel()==2){
					Query q = messageSession.createQuery("from UserLoginRecord  "
							+ " where users.userid is not null and provinceEnum.provinceEnum2.id=? or provinceEnum.id=? "
							+ "  and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id ");
					//先找在这个区域内的人  
					q.setParameter(0, provinceEnum.getId());
					q.setParameter(1, provinceEnum.getId());
					
					Ulist = q.setFirstResult(i*size).setMaxResults(size).list();
				}else{
					Ulist= messageSession.createQuery("from UserLoginRecord where users.userid is not null  and provinceEnum.id="+zoneId+" "
							+ "  and lasttime>= '"+startTime+"'  and lasttime<= '"+endTime+"' group by users.id ").setFirstResult(i*size).setMaxResults(size).list();
				}
				int k=0; 
				ulist=new ArrayList<Users>();
				for(UserLoginRecord loginRecord:Ulist){
						loginRecord.getUsers().getUserid(); //勿删
						UserSystemMessage usm=new UserSystemMessage();
						usm.setUsers(loginRecord.getUsers());	        
						usm.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
						usm.setIsValid(true);
						usm.setSystemMessageList(sml);
						usm.setIsRead(0);	
						usm.setMessageType(messageType);
						usm.setProvinceEnum(provinceEnum);
						messageSession.save(usm);
						ulist.add(loginRecord.getUsers());
						k++;
//						System.out.println("保存第"+k+"条,活动消息!");
						
						
				}

				messageTransaction.commit();
				messageSession.close();
				//发送消息线程
				PushInfo pushInfo=new PushInfo();
				Thread pushThread=new Thread(pushInfo);
				pushInfo.content=content;
				pushInfo.sml=sml;
				pushInfo.title=title;
				pushInfo.ulist=ulist;
				pushInfo.messageType=messageType;
				pushInfo.typeId=typeId;
				pushThread.start();
				Thread.currentThread().sleep(100); 

			}
			long end=System.currentTimeMillis();
			System.out.println("发送活动消息完成,总条数:"+count+",花费时间"+((end-start)/1000));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("发送活动消息失败"+e.getMessage());
			}
		}
			
		}


	




}



	
class PushInfo implements Runnable{
	
	public List<UserLoginRecord> Ulist = null;

	 public List<Users> ulist=null;
	 
	 public List<AdminUser> alist=null;
	 public List<String> gameBroadContents = null;
	 
	 public String content;
		
	 public String title;
		
	 public SystemMessageList sml=null;
		
	 public MessageType messageType;
	
	 public Integer typeId;
	 
	 ImpAppInformation push = new ImpAppInformation();
	 
	@Override
	public void run() {
		if(typeId==4){
			for (Users user : ulist) {
				push.pushMessage(user.getId()+"", user.getUserid(), title, content,sml.getId()+"",messageType.getMessageType().getId()+"","1",messageType.getTitle());
				 if(user.getUnionId() != null && user.getUnionId() != ""){
					String param = "unionId="+user.getUnionId()+"&title="+title+"&text="+content+"&url=pages/index/index";
					UrlUtil.sendGzhMsg(7, param);
				}
			}
		}else if(typeId==14){
			for (AdminUser adminUser : alist) {
				push.pushMessageToAdminUser(adminUser.getId()+"", adminUser.getChannelid(), title, content,sml.getId()+"",messageType.getMessageType().getId()+"","1",messageType.getTitle());
				if(adminUser.getUsers().getUnionId() != null && adminUser.getUsers().getUnionId() != ""){
					String param = "unionId="+adminUser.getUsers().getUnionId()+"&title="+title+"&text="+content+"&url=pages/index/index";
					UrlUtil.sendGzhMsg(7, param);
				}
			}
		}else if(typeId ==13){
			for (Users record :ulist) {
					push.pushMessage(record.getId()+"", record.getUserid(), title, content,sml.getId()+"",messageType.getMessageType().getId()+"","1",messageType.getTitle());
					 if(record.getUnionId() != null && record.getUnionId() != ""){
							String param = "unionId="+record.getUnionId()+"&title="+title+"&text="+content+"&url=pages/index/index";
							UrlUtil.sendGzhMsg(7, param);
						}
			}
		}else if(typeId == 15){
			for (Users user :ulist) {
				push.pushMessageForUsersGame(user.getId()+"", user.getUserid(), title, content, sml.getId()+"", String.valueOf(typeId), messageType.getMessageType().getId().toString(), "1", messageType.getTitle(),gameBroadContents);
				 if(user.getUnionId() != null && user.getUnionId() != ""){
						String param = "unionId="+user.getUnionId()+"&title="+title+"&text="+content+"&url=pages/index/index";
						UrlUtil.sendGzhMsg(7, param);
					}
			}
		}
		
		
		
	}
}

