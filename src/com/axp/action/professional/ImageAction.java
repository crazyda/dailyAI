package com.axp.action.professional;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.axp.dao.DateBaseDAO;
import com.axp.util.ImageUtil;
import com.axp.util.StringUtil;

@Controller
@RequestMapping("/professional/image")
public class ImageAction {
	
	private static int largeWigth = 720;
	private static int largeHeight = 1280;
	private static int padWigth = 480;
	private static int padHeight = 860;
	private static int smallWigth = 360;
	private static int smallHeight = 650;
	
	@Resource
	private DateBaseDAO dateBaseDAO;
	
		
	@RequestMapping("/uploadimg")
	private String saveImage(Integer uploadtype, String fileExt, HttpServletRequest request){
		try{
			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
			MultipartFile file = mreq.getFile("imagefile");
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File imagefile = fi.getStoreLocation();
			
			String dirPath = this.getSaveDirName(uploadtype);
			String saveAsFilePath = request.getSession().getServletContext().getRealPath("/");
			String refFaleName = file.getOriginalFilename();
			fileExt = StringUtil.hasLength(fileExt)?fileExt:refFaleName.substring(refFaleName.lastIndexOf(".")+1, refFaleName.length());
					
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmss", Locale.US);
			String filename = new String(formatter2.format(new Date()).getBytes("iso-8859-1"));
			filename = filename + "_" + "1" + "." + fileExt;

			saveAsFilePath = saveAsFilePath + dirPath + File.separator;
			String nomal_saveAsFilePath = saveAsFilePath + "nomal" + File.separator + filename;//原图
			String large_saveAsFilePath = saveAsFilePath + "320" + File.separator + filename;//320dpi
			String pad_saveAsFilePath = saveAsFilePath + "240" + File.separator + filename;//240dpi
			String small_saveAsFilePath = saveAsFilePath + "160" + File.separator + filename;//160dpi
			this.checkDir(nomal_saveAsFilePath);
			this.checkDir(large_saveAsFilePath);
			this.checkDir(pad_saveAsFilePath);
			this.checkDir(small_saveAsFilePath);
			
			// 当图片大小小于DiskFileItem预设的大小时，将保存在内存中而非缓存到硬盘里
			if (imagefile == null || !imagefile.exists()) {
				FileOutputStream outputStream = new FileOutputStream(nomal_saveAsFilePath);
				InputStream inputStream = fi.getInputStream();
				IOUtils.copy(inputStream, outputStream);
				IOUtils.closeQuietly(inputStream);
				IOUtils.closeQuietly(outputStream);
			} else {
				ImageUtil.savePicture(imagefile, new File(nomal_saveAsFilePath));
			}
			File bigFile = new File(nomal_saveAsFilePath);
			
			File largeFile = new File(large_saveAsFilePath);
			ImageUtil.resizeFix(bigFile, largeFile,largeWigth,largeHeight);
			
			File padFile = new File(pad_saveAsFilePath);
			ImageUtil.resizeFix(bigFile, padFile,padWigth,padHeight);
			
			File smallFile = new File(small_saveAsFilePath);
			ImageUtil.resizeFix(bigFile, smallFile,smallWigth,smallHeight);
			
			request.setAttribute("filepath", "nomal/" + filename);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/professional/sellerMainPage/showupload";
	}
	@RequestMapping("/uploadvideo")
	public String saveVideo(Integer uploadtype, String fileExt, HttpServletRequest request) {
		try {
			String dirPath = this.getSaveDirName(uploadtype);
			String saveAsFilePath = ServletActionContext.getServletContext()
					.getRealPath("/");
			String customer_id = request.getSession()
					.getAttribute("currentUserId").toString();

			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
			MultipartFile file = mreq.getFile("imagefile");
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File bigFile = fi.getStoreLocation();

			SimpleDateFormat formatter2 = new SimpleDateFormat(
					"yyyyMMddhhmmss", Locale.US);
			String filename = new String(formatter2.format(new Date())
					.getBytes("iso-8859-1"));

			String flyname = "";

			String picname = filename + "_" + customer_id + ".jpg";
			flyname = filename + "_" + customer_id + ".flv";
			filename = filename + "_" + customer_id + "." + fileExt;
			Date date = new Date();
			String dirName = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String big_saveAsFilePath = saveAsFilePath + dirPath + "_video"
					+ File.separator + dirName + File.separator;

			String big_saveAsFilePath_flv = big_saveAsFilePath;
			String big_saveAsFilePath_pic = big_saveAsFilePath;

			File f2 = new File(big_saveAsFilePath_flv);
			if (!f2.exists()) {
				f2.mkdirs();
			}
			File f3 = new File(big_saveAsFilePath_pic);
			if (!f3.exists()) {
				f3.mkdirs();
			}

			// 获取配置的转换工具（ffmpeg.exe）的存放路径
			String ffmpegPath = saveAsFilePath + "tools" + File.separator
					+ "ffmpeg.exe";
			boolean isSuccess = this.executeCodecs(ffmpegPath,
					bigFile.getAbsolutePath(),
					big_saveAsFilePath_flv + flyname, big_saveAsFilePath_pic
							+ picname);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("mvtime", getMvLenght(bigFile) + "");
			map.put("isSuccess", isSuccess + "");
			map.put("picPath", big_saveAsFilePath_pic + picname);
			map.put("videoPath", big_saveAsFilePath_flv + flyname);
			request.setAttribute("videoSubmitMap", map);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "/professional/sellerMainPage/showupload";
	}
	
	/*
	 * 检测上传文件夹是否存在
	 */
	private void checkDir(String uploadPath){
		String path = uploadPath.substring(0,uploadPath.lastIndexOf('.'));
		path = path.substring(0, path.lastIndexOf(File.separator));
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	private String getSaveDirName(Integer uploadtype){
		String dirPath = null;
		switch(uploadtype){
			case 24:
				//店铺名LOGO
				dirPath = "seller_logo";
				break;
			case 25:
				//顶部轮播广告
				dirPath = "top_carousel_ad";
				break;
			case 26:
				//店铺视频
				dirPath = "seller_view";
				break;
			case 27:
				//横幅广告
				dirPath = "banner_ad";
				break;
		}
		return dirPath;
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
    cutpic.add("480*320"); // 添加截取的图片大小为350*240
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
        e.printStackTrace();
    }
    return mark;
}

	
	@RequestMapping("/showupload")
	public String showupload(){
		return "/professional/sellerMainPage/showupload";
	}
	
	public String showuploadvideo(HttpServletRequest request,int uploadtype){
		return "showuploadvideo";
	}
	
    public String showuploadimages_result(){
		return "";
	}


	
}
