package com.axp.action.tag;


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.impl.AdminUserDAOImpl;
import com.axp.model.AdminUser;
import com.axp.util.ImageUtil;


public class ImagehandleAction {

	private File imagefile;

	private String file_ext;
	
	private String isHoldExt;
	
	private int flag;
	
	private String filepath;
	
	private String mvtime; 
	private String errorstr;
	

	public String getErrorstr() {
		return errorstr;
	}


	public void setErrorstr(String errorstr) {
		this.errorstr = errorstr;
	}


	public String getMvtime() {
		return mvtime;
	}


	public void setMvtime(String mvtime) {
		this.mvtime = mvtime;
	}



	private int uploadtype;
	
	private int customer_id;
	

	public int getUploadtype() {
		return uploadtype;
	}


	public void setUploadtype(int uploadtype) {
		this.uploadtype = uploadtype;
	}


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public String getFile_ext() {
		return file_ext;
	}


	public void setFile_ext(String fileExt) {
		file_ext = fileExt;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public File getImagefile() {
		return imagefile;
	}


	public void setImagefile(File imagefile) {
		this.imagefile = imagefile;
	}

	

	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customerId) {
		customer_id = customerId;
	}



	private int upload_success = 0;
	
	public int getUpload_success() {
		return upload_success;
	}


	public void setUpload_success(int uploadSuccess) {
		upload_success = uploadSuccess;
	}
	


	public String uploadimg(){
		int imgSize = 0; 
		int largeWigth = 480;
		int largeHeight = 360;
		int padWigth = 250;
		int padHeight = 150;
		int smallWigth = 120;
		int smallHeight = 120;
		String dirPath = "";
		switch(uploadtype){
		
			case 1:
				//模板图片
				dirPath = "goods";
				break;
			case 2:
				//广告图片
				dirPath = "goods_adver";
				break;
			case 3:
				//商家类型
				dirPath = "shoptype";
				break;
			case 4:
				//幻灯图片
				dirPath = "slide";
				break;
			case 6:
				//广告图片
				dirPath = "goods_adver_small";
				break;
			case 7:
				//广告图片
				dirPath = "seller";
				break;
			case 8:
				//广告图片
				dirPath = "logo";
				break;	
			case 9:
				//分屏广告图片
				dirPath = "advers";
				break;	
			case 10:
				//分屏广告竖向图片
				dirPath = "advers_vertical";
				break;
			case 11:
				//分屏广告竖向图片
				dirPath = "plays_share";
				break;	
			case 12:
				//分屏广告竖向图片
				dirPath = "plays_share_img";
				break;
			case 13:
				//分屏广告竖向图片
				dirPath = "plays_game";
				break;
			case 14:
				//分屏广告竖向图片
				dirPath = "plays_video";
				break;
			case 15:
				//现金商城商品图片
				imgSize = 15;
				dirPath = "cashshop_goods";
				break;
			case 16:
				//现金商城商品分类图片
				dirPath = "commodity_type";
				break;
			case 17:
				//消息中心封面图片
				dirPath = "messagecenter_cover";
				break;
			case 18:
				//用户头像
				dirPath = "user_img";
				break;
			case 19:
				//推广员商品缩略图
				dirPath = "promoter_goods";
				break;
			case 20:
				//推广员代金券图标
				dirPath = "promoter_coupon";
				break;
			case 21:
				//商城图文图标
				dirPath = "cashshop_type";
				break;
			case 22:
				//商城图文图标(按钮反色状态)
				dirPath = "cashshop_type_click";
				break;
			case 23:
				//红包头像
				dirPath = "new_red_paper_opera";
				break;
		}
			
		String saveAsFilePath = ServletActionContext.getServletContext().getRealPath("/");
		if (imagefile == null || !imagefile.exists()) {
			 
			return "";
		}
		//String path = imagefile.getPath();
		
		try{
			
			
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmss", Locale.US);
	        String filename = new String(formatter2.format(new Date()).getBytes("iso-8859-1"));
	       
	        int id = 1;
	       
	        
	        String ext = file_ext;
	        if(ext==null){
	        	ext = "jpg";
	        }
	        if("user_img".equals(dirPath)||"promoter_coupon".equals(dirPath)){
	        	ext = "png";
	        }
	        switch(uploadtype){
			case 15:
				padWigth = 480;
				padHeight = 260;
				smallWigth = 380;
				smallHeight = 200;
				ext = "jpg";
				break;
			case 19:
				padWigth = 200;
				padHeight = 200;
				smallWigth = 200;
				smallHeight = 200;
				ext = "jpg";
				break;
			case 21:
				padWigth = 400;
				padHeight = 400;
				smallWigth = 400;
				smallHeight = 400;
				ext = "png";
				break;
			case 22:
				padWigth = 400;
				padHeight = 400;
				smallWigth = 200;
				smallHeight = 200;
				ext = "png";
				break;
			}
	        if(isHoldExt!=null&&"1".endsWith(isHoldExt)){
	        	ext = filename.substring(filename.lastIndexOf(".")+1);
	        }
	        filename = filename + "_"+customer_id+"." + ext;
	        
	        //filename = filename.replace(".png", ".jpg");
	        
	        String big_saveAsFilePath = saveAsFilePath +dirPath+"big"+File.separator+id+File.separator+filename;
			String path3 = big_saveAsFilePath.substring(0,big_saveAsFilePath.lastIndexOf('.'));
            path3 = path3.substring(0,path3.lastIndexOf(File.separator));
            File f = new File(path3);
            if(!f.exists()){
                f.mkdirs();	 
            }
            File bigFile = new File(big_saveAsFilePath);
			ImageUtil.savePicture(imagefile, bigFile);
			//TODO 文件转换为jpg格式，此处需要重新确认
			//新增一般尺寸
			String large_saveAsFilePath = saveAsFilePath +dirPath+File.separator+id+File.separator+"large"+File.separator+filename;
			String path4 = large_saveAsFilePath.substring(0,large_saveAsFilePath.lastIndexOf('.'));
			path4 = path4.substring(0,path4.lastIndexOf(File.separator));
            f = new File(path4);
            if(!f.exists()){
                f.mkdirs();	 
            }
			File saveFile_large = new File(large_saveAsFilePath);
			ImageUtil.resizeFix2(imagefile, saveFile_large,largeWigth,largeHeight);
			
			String normal_saveAsFilePath = saveAsFilePath +dirPath+File.separator+id+File.separator+filename;
			String path2 = normal_saveAsFilePath.substring(0,normal_saveAsFilePath.lastIndexOf('.'));
            path2 = path2.substring(0,path2.lastIndexOf(File.separator));
            f = new File(path2);
            if(!f.exists()){
                f.mkdirs();	 
            }
			File saveFile = new File(normal_saveAsFilePath);
			List<Integer> arrWs= ImageUtil.resizeFix2(imagefile, saveFile,padWigth,padHeight);

			
			String small_saveAsFilePath = saveAsFilePath +dirPath+File.separator+id+File.separator+"small"+File.separator+filename;
			path2 = small_saveAsFilePath.substring(0,small_saveAsFilePath.lastIndexOf('.'));
            path2 = path2.substring(0,path2.lastIndexOf(File.separator));
            f = new File(path2);
            if(!f.exists()){
                f.mkdirs();	 
            }
            //缩略图
			File small_saveFile = new File(small_saveAsFilePath);
			
			ImageUtil.resizeByWidth(imagefile, small_saveFile, smallWigth, smallHeight);
			
			String abstrPath = id + "/" + filename;
			
			filepath = abstrPath;
			
			String small_abstrPath = id + "/small/"+filename;
		}catch(Exception e){

			e.printStackTrace();
		}
		upload_success = 1;
		return "showupload";
	}
	
public String uploadvideo(){
	try {
	HttpServletRequest request = ServletActionContext.getRequest();
		//提供解析时的一些缺省配置
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    
	    //创建一个解析器,分析InputStream,该解析器会将分析的结果封装成一个FileItem对象的集合
	    //一个FileItem对象对应一个表单域
	    ServletFileUpload sfu = new ServletFileUpload(factory);
			List<FileItem> items = sfu.parseRequest(request);
			
			if(request.getSession().getAttribute("current_user_id")==null){
	    		errorstr = "页面已过期！请重新登录";
	    		return "time_out_error";
	    	}
	    int current_user_id = (Integer)request.getSession().getAttribute("current_user_id");
		AdminUserDAO adao = new AdminUserDAOImpl(); 
		AdminUser ad = adao.findById(current_user_id);
        boolean flag = false;    //转码成功与否的标记
		String dirPath = "";
		switch(uploadtype){
			case 1:
				//模板视频
				dirPath = "video";
				break;
			case 2:
				//模板图片
				dirPath = "plays_video";
				break;
		}
			
		String saveAsFilePath = ServletActionContext.getServletContext()
		.getRealPath("/");
		if (imagefile == null || !imagefile.exists()) {
			 
			return "";
		}
		String path = imagefile.getPath();
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmss", Locale.US);
	        String filename = new String(formatter2.format(new Date()).getBytes("iso-8859-1"));
	       
	       String flyname = "";
	        int id = 1;
	       
	       String picname = filename + "_"+customer_id+".jpg";
	        String ext = file_ext;
	        flyname = filename + "_"+customer_id+".flv";
	        filename = filename + "_"+customer_id+"." + ext;
	        Date date = new Date();  
            String dirName = new SimpleDateFormat("yyyy-MM-dd").format(date);  
	        String big_saveAsFilePath = saveAsFilePath +dirPath+File.separator+dirName+File.separator+filename;
			String path3 = big_saveAsFilePath.substring(0,big_saveAsFilePath.lastIndexOf('.'));
            path3 = path3.substring(0,path3.lastIndexOf(File.separator));
            File f = new File(path3);
            if(!f.exists()){
                f.mkdirs();	 
            }
            
           
            
            File bigFile = new File(big_saveAsFilePath);
            FileUtils.copyFile(imagefile, bigFile);
            String abstrPath = dirName+"/"+filename;
            /*String big_saveAsFilePath_flv = big_saveAsFilePath+"flv"+File.separator;
            String big_saveAsFilePath_pic = big_saveAsFilePath+"pic"+File.separator;
            
            
            File f2 = new File(big_saveAsFilePath_flv);
            if(!f2.exists()){
                f2.mkdirs();	 
            }
            File f3 = new File(big_saveAsFilePath_pic);
            if(!f3.exists()){
                f3.mkdirs();	 
            }*/
            
            // 获取配置的转换工具（ffmpeg.exe）的存放路径
            String ffmpegPath=  saveAsFilePath+"tools"+File.separator+"ffmpeg.exe";
            //boolean f1 = this.executeCodecs(ffmpegPath, bigFile.getAbsolutePath(), big_saveAsFilePath_flv+flyname,big_saveAsFilePath_pic+picname);
            mvtime =getMvLenght(bigFile)+"";
			filepath = abstrPath;
		}catch(Exception e){

			e.printStackTrace();
		}
		upload_success = 1;
		return "showuploadvideo";
	}
	

public  long getMvLenght(File source){
    Encoder encoder = new Encoder();
    try {
         MultimediaInfo m = encoder.getInfo(source);
         long ls = m.getDuration();
         return (ls^000)/1000;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,String mediaPicPath) throws Exception {
    // 创建一个List集合来保存转换视频文件为flv格式的命令
    List<String> convert = new ArrayList<String>();
    convert.add(ffmpegPath); // 添加转换工具路径
    convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
    convert.add(upFilePath); // 添加要转换格式的视频文件的路径
    convert.add("-qscale");     //指定转换的质量
    convert.add("6");
    convert.add("-ab");        //设置音频码率
    convert.add("64");
    convert.add("-ac");        //设置声道数
    convert.add("2");
    convert.add("-ar");        //设置声音的采样频率
    convert.add("22050");
    convert.add("-r");        //设置帧频
    convert.add("24");
    convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
    convert.add(codcFilePath);
 // 创建一个List集合来保存从视频中截取图片的命令
    List<String> cutpic = new ArrayList<String>();
    cutpic.add(ffmpegPath);
    cutpic.add("-i");
    cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
    cutpic.add("-y");
    cutpic.add("-f");
    cutpic.add("image2");
    cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
    cutpic.add("17"); // 添加起始时间为第17秒
    cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
    cutpic.add("0.001"); // 添加持续时间为1毫秒
    cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
    cutpic.add("800*280"); // 添加截取的图片大小为350*240
    cutpic.add(mediaPicPath); // 添加截取的图片的保存路径


    boolean mark = true;
    ProcessBuilder builder = new ProcessBuilder();
    try {
       builder.command(convert);
       builder.redirectErrorStream(true);
        builder.start();
        
        builder.command(cutpic);
        builder.redirectErrorStream(true);
        // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
        //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
        builder.start();
       
    } catch (Exception e) {
        mark = false;
        System.out.println(e);
        e.printStackTrace();
    }
    return mark;
}

	private String imagepath;
	
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String showupload(){
		//System.out.println("shop img is ok!");
		return "showupload";
	}
	
	public String showuploadvideo(){
		//System.out.println("shop img is ok!");
		return "showuploadvideo";
	}
	
    public String showuploadimages_result(){
		return "";
	}


	public String getIsHoldExt() {
		return isHoldExt;
	}


	public void setIsHoldExt(String isHoldExt) {
		this.isHoldExt = isHoldExt;
	}
    
}