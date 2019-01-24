package com.axp.interceptor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.axp.model.PartnerInform;
import com.axp.model.TkldPid;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.ToolSpring;

public class PartneryListener extends BaseServiceImpl {

	
	private MyThread myThread; 

//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		if (myThread != null && myThread.isInterrupted()) {  
//	            myThread.interrupt();  
//	        }  
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//			if(myThread==null){
//				myThread=new MyThread();
//				myThread.sessionFactory=(SessionFactory) ToolSpring.getBean("sessionFactory");
//				myThread.start();
//			}
//			
//	}
}


	 class MyThread extends Thread{
		public SessionFactory  sessionFactory;
		public void run(){
			while(!this.isInterrupted()){
				Session session = null;
				Transaction transaction =null;
				Map<Integer, PartnerInform> map=new HashMap<Integer, PartnerInform>();
				try {
					//半小时执行一次
					MyThread.sleep(1800000);
					session=sessionFactory.openSession();
					transaction= session.getTransaction();
					transaction.begin();
					
					
					
					System.out.println("自动分配合伙人开始!");
					//找到所有没有分配的合伙人
					List<PartnerInform> partnerInformList =session.createQuery("from PartnerInform where isValid=true ").list();
					
					List<TkldPid> tkldPid =session.createQuery("from TkldPid where isValid=true  and level=3 and TkldPid.id=0").list();
					
					if(partnerInformList.size()>0){

						for (PartnerInform partnerInform : partnerInformList) {
							Query createQuery = session.createQuery("from TkldPid where isValid=true and users.id=:userId" );
								createQuery.setParameter("userId", partnerInform.getUsers().getId());
								List<TkldPid> list = createQuery.list();
								if(list.size()>0){
									partnerInform.setIsValid(false);
									session.update(partnerInform);
									return;
								}
							 
							map.put(partnerInform.getCauseUsers().getId(),partnerInform);
						}
						
						
						int i=0;
						
						for (PartnerInform partnerInform:  partnerInformList) {
						
							if(partnerInform.getMode()==0){
								if(map.containsKey(partnerInform.getCauseUsers().getId())){
									Query createQuery = session.createQuery("from TkldPid where isValid=true and tkldPid.users.id=:userId and tkldPid.isValid=true  and users.id is null and level=3" );
									createQuery.setParameter("userId",partnerInform.getCauseUsers().getId());

									List<TkldPid> tkldPidList =createQuery.list();
									if(tkldPidList.size()>0){
										partnerInform.setCreatetime(new Timestamp(new Date().getTime()));
										partnerInform.setIsValid(false);
										tkldPidList.get(0).setUsers(partnerInform.getUsers());
										tkldPidList.get(0).setUsersRemark("自动分配合伙人");
										tkldPidList.get(0).setBindingTime(new Timestamp(new Date().getTime()));
										session.update(partnerInform);
										session.update(tkldPidList.get(0));
									}
								}
							}else if(partnerInform.getMode()==1){
								
								if(i<tkldPid.size()){
									
									partnerInform.setCreatetime(new Timestamp(new Date().getTime()));
									partnerInform.setIsValid(false);
									tkldPid.get(i).setLevel(partnerInform.getLevel());
									if(partnerInform.getCauseUsers()==null){
										tkldPid.get(i).setTkldPid(null);
									}else{
										Query createQuery = session.createQuery("from TkldPid where isValid=true and tkldPid.users.id=:userId and tkldPid.isValid=true   and level=2" );
										createQuery.setParameter("userId",partnerInform.getCauseUsers().getId());

										List<TkldPid> tkldPidList =createQuery.list();
										if(tkldPidList.size()>0){
											tkldPid.get(i).setTkldPid(tkldPidList.get(0));
										}else{
											tkldPid.get(i).setTkldPid(null);
										}
									}
									
									
									tkldPid.get(i).setUsers(partnerInform.getUsers());
									tkldPid.get(i).setUsersRemark("自动分配合伙人");
									tkldPid.get(i).setBindingTime(new Timestamp(new Date().getTime()));
									session.update(partnerInform);
									session.update(tkldPid.get(i));
								}
								
								
								Query createQuery = session.createQuery("from TkldPid where isValid=true and tkldPid.users.id=:userId and tkldPid.isValid=true  and users.id is null" );
								createQuery.setParameter("userId",partnerInform.getCauseUsers().getId());

								List<TkldPid> tkldPidList =createQuery.list();
								if(tkldPidList.size()>0){
									
								}
								
								
								
							}
							
							
							
						}
					}
				} catch (Exception e) {
					transaction.rollback();
					System.out.println("自动分配合伙人出错!"+e.getMessage());
				}
				transaction.commit();
				session.flush();
				session.close();
				System.out.println("自动分配合伙人结束!");
			}
		}
	}
	 
	