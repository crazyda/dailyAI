package com.yilian.service.versionSMS;

import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import antlr.build.Tool;

import com.axp.dao.GetmoneyRecordDao;
import com.axp.model.GetmoneyRecord;
import com.axp.service.system.impl.BaseServiceImpl;
import com.push.ImpAppInformation;
import com.yilian.payeco.tools.Tools;
import com.yilian.service.encrypt.RSA;
import com.yilian.service.encrypt.TripleDes;
import com.yilian.service.util.Base64;
import com.yilian.service.util.SslConnection;
import com.yilian.service.util.Strings;
import com.yilian.service.util.Util;

public   class  PayMen  {

	private static Logger logger = Logger.getLogger(PayMen.class.getName());

	//���Թ�Կ
	//private static String dna_pub_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqWSfUW3fSyoOYzOG8joy3xldpBanLVg8gEDcvm9KxVjqvA/qJI7y0Rmkc1I7l9vAfWtNzphMC+wlulpaAsa/4PbfVj+WhoNQyhG+m4sP27BA8xuevNT9/W7/2ZVk4324NSowwWkaqo1yuZe1wQMcVhROz2h+g7j/uZD0fiCokWwIDAQAB";
	//��ʽ��Կ
	  private static String dna_pub_key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+L2JGUKlGtsFm2f/wuF2T6/8mc6yrN8tLPgsx7sxAatvMvunHLXKC8xjkChHqVfJgohV4OIWe8zCw7jPsJMiPvrNnFHJ2Mumg/zQ8eZOnzMA0LDqBNFvZnOpy2XtagQn4yxxzG9+9h4P5eNojC3vD2t3H/6q5V3Cd022/egIZQIDAQAB";
	
	//private static String mer_pfx_key =PayMen.class.getClassLoader().getResource("yilian.pfx").getPath(); //����
	private static String mer_pfx_key =PayMen.class.getClassLoader().getResource("104000000125584-Signature.pfx").getPath(); //��ʽ
	
	
	
	
	
	//private static String mer_pfx_pass = "11111111";//�̻�˽Կ�������
	private static String mer_pfx_pass = "06411230";//�̻�˽Կ���� ��ʽ
	
	//public static String userName="15901837011"; //����
	public static String userName="13514231919";  //��ʽ 
	
	//private static String url = "https://testagent.payeco.com:9444/service";//���Ի����µ���ַ
	private static String url = "https://agent.payeco.com/service";//����µ���ַ
	
	public static void main(String[] args) throws Exception {
		System.out.println(mer_pfx_key);
//		verify();//��֤
//		verify_query(); //��֤��ѯ
//		queryAccountInfo();//��ѯ���п������������
		
//		send_message();//���Ͷ�����֤��
//		gather();//����
//		gather_query();//��ѯ���ս��
			
	//	pay();
		//pay2();//��
		//	payQuery();
		pay_query2();//��ѯ����
		//getUserMoney();
	}
	
	
	public static MsgBean getUserMoney() throws Exception{
		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.1");
		req_bean.setMSG_TYPE("600001");
		req_bean.setUSER_NAME(PayMen.userName);
		String batchNo="AXP"+new Date().getTime()+new String(Base64.decode(Util.generateKey(99999,5)));
		req_bean.setBATCH_NO(batchNo);
		String res = sendAndRead(signANDencrypt(req_bean));
		MsgBean res_bean = decryptANDverify(res);
		//�ɹ� 1 ��״̬  2�淵����Ϣ  3 ����������
		if("0000".equals(res_bean.getTRANS_STATE())) {
			return res_bean;
		}else{
			System.out.println("����������ѯ�ӿ�ʧ��");
			System.out.println(res_bean.toXml());
			return null;
	}
		
	}
	
	

	//��֤
	public static void verify() throws Exception {
		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.0");
		req_bean.setMSG_TYPE("300001");
		String batch_no = new String(Base64.decode(Util.generateKey(99999,14)));//ÿ�ʶ��������ظ������飺��˾�����д+yymmdd+��ˮ��
		req_bean.setBATCH_NO(batch_no);
		req_bean.setUSER_NAME("13728096874");//ϵͳ��̨��¼��
								
		MsgBody body = new MsgBody();
		body.setSN("101000001");//��ˮ�ţ�ͬһ��β��ظ�����
		body.setACC_NO("6222023602076055577");//����
		body.setACC_NAME("����123");//����
//		body.setACC_PROVINCE("�㶫");
//		body.setACC_CITY("����");
		body.setAMOUNT("1");//�������дһ��(0,5)֮�����ֵ������дʱ���ȡ(0,5)֮�����ֵ
		body.setCNY("CNY");
		body.setREMARK("�����ӿ�2 Test");
		body.setMOBILE_NO("13316818027");//֧���ֻ�ţ�����
		body.setID_NO("");//����
		body.setID_TYPE("0");//����
		body.setRETURN_URL("");
		body.setMER_ORDER_NO("MON"+batch_no);
		body.setTRANS_DESC("��֤����");//�ױ����������������
		req_bean.getBODYS().add(body);
		
		/*	MsgBody body2 = new MsgBody();
		body2.setSN("101000002");
		body2.setACC_NO("6222023602076096878");
		body2.setACC_NAME("����");
		body2.setAMOUNT("2");
		req_bean.getBODYS().add(body2);
		
		MsgBody body3 = new MsgBody();
		body3.setSN("0000000000000003");
		body3.setACC_NO("6225887800100101");
		body3.setACC_NAME("����");
		body3.setAMOUNT("2.2");
		req_bean.getBODYS().add(body3);*/

		String res = sendAndRead(signANDencrypt(req_bean));

		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		logger.info(res_bean.toXml());
	}
	
	//��֤��ѯ
	public static void verify_query() throws Exception {

		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.0");
		req_bean.setMSG_TYPE("300002");
		String batch_no = new String(Base64.decode(Util.generateKey(99999,14)));
		req_bean.setBATCH_NO(batch_no);
		req_bean.setUSER_NAME("13728096874");//ϵͳ��̨��¼��
								
		MsgBody body = new MsgBody();
		body.setSN("101000001");
		body.setACC_NO("6222023602076055577");
		body.setACC_NAME("����123");
//		body.setACC_PROVINCE("�㶫");
//		body.setACC_CITY("����");

		body.setID_NO("");
		body.setID_TYPE("0");
		body.setRESERVE("Y");
		req_bean.getBODYS().add(body);
		
		/*	MsgBody body2 = new MsgBody();
		body2.setSN("101000002");
		body2.setACC_NO("6222023602076096878");
		body2.setACC_NAME("����");
		body2.setAMOUNT("2");
		req_bean.getBODYS().add(body2);
		
		MsgBody body3 = new MsgBody();
		body3.setSN("0000000000000003");
		body3.setACC_NO("6225887800100101");
		body3.setACC_NAME("����");
		body3.setAMOUNT("2.2");
		req_bean.getBODYS().add(body3);*/

		String res = sendAndRead(signANDencrypt(req_bean));
		
		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		logger.info(res_bean.toXml());
	}

	//��ѯ���п���������
	public static void queryAccountInfo() {

		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.0");
		req_bean.setMSG_TYPE("400001");
		req_bean.setBATCH_NO("99EE936559D864");
		req_bean.setUSER_NAME("13728096874");//ϵͳ��̨��¼��
		
		MsgBody body = new MsgBody();
		body.setSN("101000004");
		body.setACC_NO("6225380048403812");
		req_bean.getBODYS().add(body);
		
		String res = sendAndRead(signANDencrypt(req_bean));

		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		logger.info(res_bean.toXml());

	}

	//���Ͷ�����֤��
	public static void send_message() throws Exception{
		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.0");
		req_bean.setMSG_TYPE("500001");//���Ͷ���
		req_bean.setBATCH_NO(new String(Base64.decode(Util.generateKey(99999,14))));//ÿ�ʶ��������ظ������飺��˾�����д+yymmdd+��ˮ��
		req_bean.setUSER_NAME("13728096874");//ϵͳ��̨��¼��
						
		MsgBody body = new MsgBody();
		body.setACC_NO("6222023602076055577");//����
		body.setMOBILE_NO("13800138000");//ϵͳĬ�Ϸ��Ͷ�����֤�뵽�û�������ܰ󶨵��ֻ��
		body.setTRANS_DESC("�������ݷ���");//�������ݲ�����40��
		req_bean.getBODYS().add(body);
		
		String res = sendAndRead(signANDencrypt(req_bean));
		
		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		logger.info(res_bean.toXml());
	}

	//����
	public static void gather() throws Exception {

		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.0");
		req_bean.setMSG_TYPE("200001");
		req_bean.setBATCH_NO("D023D92EBC6CBA");//1.��send_message�ӿڵ�BATCH_NOһ�£�2.ÿ�ʶ��������ظ������飺��˾�����д+yymmdd+��ˮ��
		req_bean.setUSER_NAME("13728096874");//ϵͳ��̨��¼��
						
		MsgBody body = new MsgBody();
		body.setSN("101000001");//��ˮ�ţ�ͬһ��β��ظ�����
		body.setACC_NO("6222023602076055577");//����
		body.setACC_NAME("����");//����
		body.setID_NO("");//����֤����
		body.setID_TYPE("0");//֤������
		body.setAMOUNT("1");//����
		body.setCNY("CNY");
		body.setREMARK("����2");
		body.setMOBILE_NO("");//֧���ֻ��
		body.setRETURN_URL("");//�첽֪ͨ��ַ
		body.setMER_ORDER_NO("");
		body.setMER_SEQ_NO("");
		body.setTRANS_DESC("�����ӿ� ��������");//�ױ����������������
		body.setSMS_CODE("123456");//������
		req_bean.getBODYS().add(body);
		
		/*	MsgBody body2 = new MsgBody();
		body2.setSN("101000002");
		body2.setACC_NO("6222023602076096878");
		body2.setACC_NAME("����");
		body2.setAMOUNT("2");
		req_bean.getBODYS().add(body2);
		
		MsgBody body3 = new MsgBody();
		body3.setSN("0000000000000003");
		body3.setACC_NO("6225887800100101");
		body3.setACC_NAME("����");
		body3.setAMOUNT("2.2");
		req_bean.getBODYS().add(body3);*/

		String res = sendAndRead(signANDencrypt(req_bean));
		
		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		System.out.println("������"+res_bean.getBODYS().get(0).getPAY_STATE()+"-"+res_bean.getBODYS().get(0).getREMARK());
		logger.info(res_bean.toXml());
	}

	//���ղ�ѯ
	public static void gather_query() {

		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.0");
		req_bean.setMSG_TYPE("200002");
		req_bean.setBATCH_NO("A43A424B50D87B"); //ͬ���ս����������κ�
		req_bean.setUSER_NAME("13728096874");//ϵͳ��̨��¼��
		
		/*MsgBody body1 = new MsgBody();
		body1.setQUERY_NO_FLAG("1");
		body1.setMER_ORDER_NO("");
		body1.setMER_SEQ_NO("");
		body1.setRETURN_URL("http://10.123.18.44:8080/notifyasyn?beanName=PayEcoNotifyHome&amp;ENCODING=utf-8");
		req_bean.getBODYS().add(body1);*/
			
//		MsgBody body2 = new MsgBody();
//		body2.setQUERY_NO_FLAG("1");
//		body2.setMER_ORDER_NO("MONBE932A83421E6C");//KK78965421354
//		req_bean.getBODYS().add(body2);
		
		String res = sendAndRead(signANDencrypt(req_bean));

		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		logger.info(res_bean.toXml());

	}	
	
	//��
	public static Map<String, Object> pay() throws Exception {
		
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("status", 1);
			map.put("message", "��ɹ�");
			try {
			MsgBean req_bean = new MsgBean();
			req_bean.setVERSION("2.1");
			req_bean.setMSG_TYPE("100001");
			req_bean.setUSER_NAME(userName);//ϵͳ��̨��¼��
			//	String batchNo="AXP"+new Date().getTime()+new String(Base64.decode(Util.generateKey(99999,5)));
				String batchNo="AXP150295652363879F47";
				
				req_bean.setBATCH_NO(batchNo);//ÿ�ʶ��������ظ������飺��˾�����д+yymmdd+��ˮ��
				MsgBody body = new MsgBody();
				//body.setSN(String.valueOf((int)(Math.random()*6*100000)));//��ˮ�ţ�ͬһ��β��ظ�����
				body.setSN("811167917");//��ˮ�ţ�ͬһ��β��ظ�����
				body.setACC_NO("6217003230032453403xxxx");
				body.setACC_NAME("������");
				body.setAMOUNT("0.02");
				req_bean.getBODYS().add(body);
				String res = sendAndRead(signANDencrypt(req_bean));
				MsgBean res_bean = decryptANDverify(res);
				if("0000".equals(res_bean.getTRANS_STATE())) {
					System.out.println("����ɹ�");
					System.out.println(res_bean.toXml());
				}
				else{
					System.out.println("����ʧ��");
					System.out.println(res_bean.toXml());
				}
				req_bean.getBODYS().clear();
				
			} catch (Exception e) {
				map.put("status",- 1);
				map.put("message", "����ʧ��");
			}
			return map;
	}

	
	//���ѯ
		public static void payQuery() {
			MsgBean req_bean = new MsgBean();
			req_bean.setVERSION("2.1");
			req_bean.setMSG_TYPE("100002");
			req_bean.setUSER_NAME(userName);//ϵͳ��̨��¼��
				req_bean.setBATCH_NO("AXP1502963639425B1771");//ͬ����������κ�
				String res = sendAndRead(signANDencrypt(req_bean));
				MsgBean res_bean = decryptANDverify(res);
				//�ɹ� 1 ��״̬  2�淵����Ϣ  3 ����������
				if("0000".equals(res_bean.getTRANS_STATE())) {
					if("0000".equals(res_bean.getPAY_STAT())){
						System.out.println("���ֳɹ�");
					}else if("00A4".equals(res_bean.getPAY_STAT())){
						System.out.println("֧����");
					}else{
						System.out.println("����ʧ��");
					}
				}else{
					System.out.println("����������ѯ�ӿ�ʧ��");
					System.out.println(res_bean.toXml());
			}
		}
	
	
	//���ѯ
	public static void pay_query2() {

		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.1");
		req_bean.setMSG_TYPE("100002");
		req_bean.setBATCH_NO("AXP1504777220525DDA7C");//ͬ����������κ� axp3A3E4E17 A7762217
		req_bean.setUSER_NAME(userName);//ϵͳ��̨��¼��

//		MsgBody body = new MsgBody();
//		body.setQUERY_NO_FLAG("0");
//		body.setMER_ORDER_NO("DF123456789");
//		req_bean.getBODYS().add(body);
		String res = sendAndRead(signANDencrypt(req_bean));

		MsgBean res_bean = decryptANDverify(res);
		
		if("0000".equals(res_bean.getTRANS_STATE())) {
			logger.info("����ɹ�");
		}
		logger.info(res_bean.toXml());

	}
	
	public static MsgBean decryptANDverify(String res) {
		
		String msg_sign_enc = res.split("\\|")[0];
		String key_3des_enc = res.split("\\|")[1];
		
		//������Կ
		String key_3des = RSA.decrypt(key_3des_enc,mer_pfx_key,mer_pfx_pass);
		
		//���ܱ���
		String msg_sign = TripleDes.decrypt(key_3des, msg_sign_enc);
		MsgBean res_bean = new MsgBean();
		
	//	System.out.println("���ر���"+msg_sign);
		res_bean.toBean(msg_sign);
		res_bean.toOneXml(msg_sign);
	//	System.out.println("��ϸ"+msg_sign);
		
		int start = msg_sign.indexOf("<REMARK>");
		int end=msg_sign.indexOf("</REMARK>");
		//<PAY_STATE>
		int start2 =msg_sign.indexOf("<PAY_STATE>");
		int end2=msg_sign.indexOf("</PAY_STATE>");
		int start3=msg_sign.indexOf("<AMOUNT>");
		int end3=msg_sign.indexOf("</AMOUNT>");
		
		int time1= msg_sign.indexOf("<SUCCESS_DATE>");
		int time2= msg_sign.indexOf("</SUCCESS_DATE>");
			try {
				String time=msg_sign.substring(time1+14,time2);
				if(StringUtils.isNotBlank(time)&&time.length()>0){
					res_bean.setYiLianPayTime(Timestamp.valueOf(time));
				}
			} catch (Exception e) {
				//如果时间异常 有可能第一次请求 还没有成功时间
			}
		
		res_bean.setREMARK(msg_sign.substring(start+8,end));
		
		res_bean.setPAY_STAT(msg_sign.substring(start2+11,end2));
		
		res_bean.setAMOUNT(msg_sign.substring(start3+8,end3));
		
		//��ǩ
		String dna_sign_msg = res_bean.getMSG_SIGN();
		res_bean.setMSG_SIGN("");
		String verify = Strings.isNullOrEmpty(res_bean.getVERSION())? res_bean.toXml(): res_bean.toSign() ;
		logger.info("verify:" + verify);
		if(!RSA.verify(dna_sign_msg, dna_pub_key, verify)) {
			logger.error("��ǩʧ��");
			res_bean.setTRANS_STATE("00A0");
		}
		return res_bean;
	}

	public static String signANDencrypt(MsgBean req_bean) {
		
		//�̻�ǩ��
		
		//System.out.println("before sign xml =="+ req_bean.toSign());
		//System.out.println("msg sign = "+RSA.sign(req_bean.toSign(),mer_pfx_key,mer_pfx_pass));
		req_bean.setMSG_SIGN(RSA.sign(req_bean.toSign(),mer_pfx_key,mer_pfx_pass));
		//System.out.println("req:" + req_bean.toXml());
		//System.out.println("����"+req_bean.toXml());
		//���ܱ���
		String key = Util.generateKey(9999,24);
		//logger.info("key:" + key);
		String req_body_enc = TripleDes.encrypt(key, req_bean.toXml());
	//	logger.info("req_body_enc:" + req_body_enc);
		//������Կ
		String req_key_enc = RSA.encrypt(key, dna_pub_key);
		//logger.info("req_key_enc:" + req_key_enc);
	//	logger.info("signANDencrypt:" + req_body_enc+"|"+req_key_enc);
		return req_body_enc+"|"+req_key_enc;

	}

	public static String sendAndRead(String req) {

		try {
			HttpURLConnection connect = new SslConnection().openConnection(url);
			
	        connect.setReadTimeout(120000);
			connect.setConnectTimeout(30000);

			connect.setRequestMethod("POST");
			connect.setDoInput(true);
			connect.setDoOutput(true);
			connect.connect();

			byte[] put = req.getBytes("UTF-8");
			connect.getOutputStream().write(put);

			connect.getOutputStream().flush();
			connect.getOutputStream().close();
			String res = SslConnection.read(connect);

			connect.getInputStream().close();
			connect.disconnect();
			
//			String res = new SslConnection().connect(url);

			return res;
		} catch(Exception e) {
			logger.error(Strings.getStackTrace(e));
		}
		return "";
	}
}
