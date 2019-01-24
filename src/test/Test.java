package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.axp.dao.SharePartnerDao;
import com.axp.dao.TkldPidDao;
import com.axp.model.SharePartner;
import com.axp.model.TkldPid;
import com.axp.model.Users;
import com.axp.service.system.impl.BaseServiceImpl;

/*@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:application.xml","classpath:springMVC.xml"})
public class Test extends BaseServiceImpl{
	/*@Autowired
	private TkldPidDao tkldPidDao;
	@Autowired
	private SharePartnerDao sharePartnerDao;*/
	/*@org.junit.Test
	public void test() {
		TkldPid findById = tkldPidDao.findById(12);
		SharePartner sha = sharePartnerDao.findById(1);
		System.out.println(sha);
	}*/

	/*@Autowired
	private SessionFactory sessionFactory;
	@org.junit.Test
	public void test01(){
		//批量添加2张表
	Session	session=sessionFactory.openSession();	
	Transaction transaction = session.getTransaction();
	
	try {
		transaction.begin();
		Users users= (Users) session.createQuery("from Users where id="+811).uniqueResult();
		long start=	System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			TkldPid tkldPid=new TkldPid();
			tkldPid.setpId("测试1");
			tkldPid.setUsers(users);
			SharePartner partner=new SharePartner();
			partner.setUsers(users);
			session.save(tkldPid);
			session.save(partner);
			System.out.println("添加第"+i+"次");
			if(i%1000==0){
				session.flush();
				session.clear();
				System.out.println("添加第"+i+"次刷新");
			}
		}
		transaction.commit();
		System.out.println("添加完成");
		long end=System.currentTimeMillis();
		System.out.println("总计时间"+((end-start)/1000));
	} catch (Exception e) {
		e.printStackTrace();
		transaction.rollback();
		System.out.println("批量新增失败");
	}
	finally{
		session.close();
		
	}
	}*/
	
	
	@Transactional
	@org.junit.Test
	public void test03(){
		try {
		//	Integer count = usersDAO.findCountByQueryChar("isvalid = true and userid is not null and length(userid) = 32");
			TkldPid t=new TkldPid();
			for(int i=0;i<10;i++){
				t.setpId(""+i);
				tkldPidDao.save(t);
				//t.setId(null);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
		

	}
	
	public static void main(String[] args) {
		/*
		int count=10;
		int size=3000;
		
		int num= (count%size==0)?(count/size):(count/size+1);  
		System.out.println(num);
		System.out.println(1%3000);
		*/
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", "ds");
	String s=	JSONObject.toJSONString(map);
	System.out.println(s);
		
	}
	
	@org.junit.Test
	public void test04(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("a", "ds");
	String s=	JSONObject.toJSONString(map);
	System.out.println(s);
	
	}
	
	
}
