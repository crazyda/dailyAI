package com.axp.service.cashShop.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.VoucherDao;
import com.axp.model.Voucher;
import com.axp.service.cashShop.VoucherService;
import com.axp.util.DateUtil;
import com.axp.util.ExcelUtil;
import com.axp.util.MD5;
import com.axp.util.PageInfo;
import com.axp.util.QRcode;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

import jsx3.gui.Image;


@Service
public class VoucherServiceImpl extends BaseServiceImpl implements VoucherService{
	@Autowired
	DateBaseDAO dateBaseDao;
	@Autowired
	VoucherDao voucherDao;
	
	
	public String RESOURCE_LOCAL_URL;
	@Override
	public void getList(HttpServletResponse response, HttpServletRequest request) {
		String page = request.getParameter("pageindex");
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("isValid", true);
		queryModel.combCondition(" status>=5");
		PageInfo pageInfo = new PageInfo();
		int count = 0;
		count = dateBaseDao.findCount(Voucher.class, queryModel);
		Utility.setPageInfomation(pageInfo, page, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		List<Voucher> list = dateBaseDao.findPageList(Voucher.class, queryModel, start, end);

		request.setAttribute("list", list);
		request.setAttribute("count",count);
		request.setAttribute("pageFoot",
				pageInfo.getCommonDefaultPageFootView());
		
	}
	@Override
	public void save(HttpServletRequest request, HttpServletResponse response, String Num, String faceValue,
			String remark) {
		RESOURCE_LOCAL_URL = request.getServletContext()
				.getAttribute("RESOURCE_LOCAL_URL").toString();
		int num = 0;
		int password = 0;
		
		if (StringUtils.isNotBlank(Num)) {
			 num = Integer.parseInt(Num);
		}
		
		QueryModel queryModel = new QueryModel();
		
		for (int i = 0; i < num; i++) {
			Voucher voucher = new Voucher();
			QRcode rcode = new QRcode();
			password = (int) ((Math.random() * 9 + 1) * 100000);//随机密码
			
			int random = (int) ((Math.random() * 9 + 1) * 1000);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
			
			String date = dateFormat.format(new Date()); 
			String code = date+random;//序列号
			
			/*queryModel.combPreEquals("code", code);
			Voucher voucher2 = (Voucher) dateBaseDao.findOne(Voucher.class, queryModel);
			if (voucher2!=null) {
				random = (int) ((Math.random() * 9 + 1) * 1000);
				date = dateFormat.format(new Date()); 
				code = date+random;//序列号
			}
			*/
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
	    	String d = sf.format(new Date()); 
			String pathUrl = "http://jifen.aixiaoping.cn:8080/dailyRes/QRcode/"+d;
			
			
			String imageUrl ="QRcode/"+d+"/"+code+".png";
			
			try {
				//rcode.addImageQRcode(code+".png","axpCode:"+code, 400, 400, "png", pathUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			voucher.setImgUrl(imageUrl);
			voucher.setDescription(remark);
			voucher.setIsValid(true);
			voucher.setIsRecharge(false);
			voucher.setFaceValue(Double.valueOf(faceValue));
			voucher.setPassword(String.valueOf(password));
			voucher.setEncryption(MD5.GetMD5Code(String.valueOf(password)));
			voucher.setCreateTime(new Timestamp(System.currentTimeMillis()));
			voucher.setLastTime(new Timestamp(DateUtil.addYear2Date(2, new Date()).getTime()));
			voucher.setStatus(0);
			voucher.setCode(code);
			voucherDao.save(voucher);
		}
		
		
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("status", 0);
		
		List<Voucher> list = dateBaseDao.findLists(Voucher.class, queryModel);
		
		//excel文件的表头
    	List<String> titleList = new ArrayList<String>();
    	titleList.add("卡号");
    	titleList.add("密码");
        titleList.add("面值");
        titleList.add("说明");
        titleList.add("二维码内容");
      
        //excel 文件的数据内容
        List<Object[]> value = new ArrayList<Object[]>();
        FileInputStream fis=null;
        byte[] read=null;
        ByteArrayOutputStream byteArrayOut =null;
        BufferedImage bufferImg = null;  
        String facevalue ="";
        
		for (Voucher vouchers : list) {
			//String url = RESOURCE_LOCAL_URL+voucher.getImgUrl();
			String url = "http://jifen.aixiaoping.cn:8080/dailyRes/"+vouchers.getImgUrl();
			FileOutputStream stream = null;
			BufferedImage image = null;
			facevalue=String.valueOf(vouchers.getFaceValue());
			try {
				byteArrayOut = new ByteArrayOutputStream();
				 bufferImg = ImageIO.read(new File(url));   
				 ImageIO.write(bufferImg, "png", byteArrayOut);
				  fis = new FileInputStream(new File(url));  
				  read = new byte[4000];  
			        int len = 0;  
			        while((len = fis.read(read))!= -1){  
			        	
			        }  
			      
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			Object[] temp = new Object[5];
			temp[0] = vouchers.getCode();
			temp[1] = vouchers.getPassword();
            temp[2] = String.valueOf(vouchers.getFaceValue());
            temp[3] = vouchers.getDescription();
            temp[4] = "axpCode:"+vouchers.getCode();
            value.add(temp);
		}
        
        int[] dataType = new int[5];
        String fileName = "面值"+facevalue+"数量"+list.size();
        ExcelUtil.ExportFile(ExcelUtil.getWorkBook(), titleList, value, response, fileName, dataType,false,read);
		
        
        
        for (Voucher voucher:list) {
			voucher.setStatus(5);
			voucherDao.saveOrUpdate(voucher);
		}
        
        
	}

}
