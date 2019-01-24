package com.axp.action.webUpload;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.axp.util.ImageUtil;
import com.axp.util.StringUtil;


@Controller
public class ImagehandleAction {
	
	private static int largeWigth = 720;
	private static int largeHeight = 1280;
	private static int padWigth = 480;
	private static int padHeight = 860;
	private static int smallWigth = 360;
	private static int smallHeight = 650;
	private static String[] VIDEO_EXT = new String[] { ".mp4", ".flv"};
	private static String[] PIC_EXT = new String[] { ".jpg", ".jpeg", ".png", ".gif"};
	
	
	@RequestMapping("/fileHandle/test")
	private String test(Integer amountLimit,String uploadBaseUrl,String submitName, String uploadPath, String thumbW,String thumbH, HttpServletRequest request){
		request.setAttribute("amount", amountLimit);
		request.setAttribute("uploadBaseUrl", uploadBaseUrl);
		request.setAttribute("submitName", submitName);
		request.setAttribute("url", uploadPath);
		request.setAttribute("thumbW", thumbW);
		request.setAttribute("thumbH", thumbH);
		
		return "/webUploader/uploadPage";
	}
	
	@RequestMapping("/fileHandle/upload")
	private String upload(String dirName,String uploadtype, HttpServletRequest request,HttpServletResponse response) throws IOException, InterruptedException{
		try{
		response.setContentType("text/html; charset=UTF-8");
		MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
		MultipartFile file = mreq.getFile("uploadFile");
		if(file==null){
			file = mreq.getFile("imagefile");
		}
		String refFaleName = file.getOriginalFilename();
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmssSSS",Locale.US);
		String filename = new String(formatter2.format(System.nanoTime()).getBytes("iso-8859-1"));//文件名
		String ext = refFaleName.substring(refFaleName.lastIndexOf("."),refFaleName.length());//文件后序
		 String relativePath = "";
		if(StringUtil.hasLength(uploadtype)){
			relativePath = getDivPath(Integer.parseInt(uploadtype))+"/1" + "/";//相对路径
		}else{
			relativePath = "upload-res/"+dirName+"/" + request.getParameter("currentUserId") + "/";//相对路径
		}
       
        
        CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		String fileType = "";
		
		Map<String,Object> map;
		if(isVideo(ext)){
			map = saveVideo(relativePath,filename,ext,fi,request);
			fileType = "video";
		}else if(isPic(ext)){
			map = saveImage(relativePath,filename,ext,fi,request);
			fileType = "image";
		}else{
			String saveAsFilePath = request.getRealPath("/");
			this.checkDir(saveAsFilePath + relativePath);
			FileOutputStream outputStream = new FileOutputStream(saveAsFilePath + relativePath + filename + ext);
			InputStream inputStream = fi.getInputStream();
			IOUtils.copy(inputStream, outputStream);
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
			map = new HashMap<String, Object>();
			map.put("isSuccess", true + "");
			map.put("picPath", relativePath + filename + ext);
			fileType = "file";
		}
		map.put("fileType", fileType);
		JSONObject ob = new JSONObject(map);
		if(StringUtil.hasLength(uploadtype)){
			request.setAttribute("filepath", map.get("picPath"));
			request.setAttribute("uploadtype", uploadtype);
			request.setAttribute("upload_success", 1);
			return "uploadimg/showupload";
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(ob);
			Thread.sleep(10);
			return null;
		}
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("/fileHandle/uploadimg")
	private Map<String,Object> saveImage(String relativePath, String fileName, String ext, DiskFileItem file, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			@SuppressWarnings("deprecation")
			String saveAsFilePath = request.getRealPath("/");
			saveAsFilePath = saveAsFilePath + relativePath + "/";
			fileName = fileName + ext;
			String small_relativePath = relativePath + "160" + "/" + fileName;//原图
			String nomal_saveAsFilePath = saveAsFilePath + "nomal" + "/" + fileName;//原图
			String large_saveAsFilePath = saveAsFilePath + "320" + "/" + fileName;//320dpi
			String pad_saveAsFilePath = saveAsFilePath + "240" + "/" + fileName;//240dpi
			String small_saveAsFilePath = saveAsFilePath + "160" + "/" + fileName;//160dpi
			this.checkDir(nomal_saveAsFilePath);
			this.checkDir(large_saveAsFilePath);
			this.checkDir(pad_saveAsFilePath);
			this.checkDir(small_saveAsFilePath);
			
			// 当图片大小小于DiskFileItem预设的大小时，将保存在内存中而非缓存到硬盘里
			File imagefile = file.getStoreLocation();
			if (imagefile == null || !imagefile.exists()) {
				FileOutputStream outputStream = new FileOutputStream(nomal_saveAsFilePath);
				InputStream inputStream = file.getInputStream();
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
			
			map.put("isSuccess", true + "");
			map.put("picPath", small_relativePath);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			map.put("isSuccess", false + "");
			map.put("error", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			map.put("isSuccess", false + "");
			map.put("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("isSuccess", false + "");
			map.put("error", e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping("/fileHandle/uploadvideo")
	public Map<String,Object> saveVideo(String relativePath, String fileName, String ext, DiskFileItem file, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			File bigFile = file.getStoreLocation();
			String saveAsFilePath = request.getRealPath("/");
			relativePath = relativePath + "/" + "video" + "/";
			String originalname = fileName + ext;
			String picname = fileName + ".jpg";
			String flyname = fileName + ".flv";

			String originalPath = saveAsFilePath + relativePath + originalname;
			String flvPath = saveAsFilePath + relativePath + flyname;
			String picPath = saveAsFilePath + relativePath + picname;

			this.checkDir(originalPath);
			this.checkDir(flvPath);
			this.checkDir(picPath);
			
			//保存源文件
			/*
			FileOutputStream outputStream = new FileOutputStream(originalPath);
			InputStream inputStream = file.getInputStream();
			IOUtils.copy(inputStream, outputStream);
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);*/

			// 获取配置的转换工具（ffmpeg.exe）的存放路径
			String ffmpegPath = saveAsFilePath + "tools" + "/" + "ffmpeg.exe";
			boolean isSuccess = this.executeCodecs(ffmpegPath,bigFile.getAbsolutePath(),
					flvPath, picPath);
			
			map.put("mvtime", getMvLenght(bigFile) + "");
			map.put("isSuccess", isSuccess + "");
			map.put("picPath", relativePath + picname);
			map.put("videoPath", relativePath + flyname);
		} catch (Exception e) {
			map.put("isSuccess", false + "");
			map.put("error", e.getMessage());
		}
		return map;
	}
	
	/*
	 * 检测上传文件夹是否存在
	 */
	private void checkDir(String uploadPath){
		String path = uploadPath.substring(0, uploadPath.lastIndexOf("/"));
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	private boolean isVideo(String ext){
		boolean re = false;
		for(String s : VIDEO_EXT){
			if(ext.equals(s)){
				re = true;
				break;
			}
		}
		return re;
	}
	
	private boolean isPic(String ext){
		boolean re = false;
		for(String s : PIC_EXT){
			if(ext.equals(s)){
				re = true;
				break;
			}
		}
		return re;
	}

	public long getMvLenght(File source) {
		Encoder encoder = new Encoder();
		try {
			MultimediaInfo m = encoder.getInfo(source);
			long ls = m.getDuration();
			return (ls ^ 000) / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean executeCodecs(String ffmpegPath, String upFilePath,
			String codcFilePath, String mediaPicPath) throws Exception {
		// 创建一个List集合来保存转换视频文件为flv格式的命令
		List<String> convert = new ArrayList<String>();
		convert.add(ffmpegPath); // 添加转换工具路径
		convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
		convert.add(upFilePath); // 添加要转换格式的视频文件的路径
		convert.add("-qscale"); // 指定转换的质量
		convert.add("6");
		convert.add("-ab"); // 设置音频码率
		convert.add("64");
		convert.add("-ac"); // 设置声道数
		convert.add("2");
		convert.add("-ar"); // 设置声音的采样频率
		convert.add("22050");
		convert.add("-r"); // 设置帧频
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
			// 因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
			builder.start();

		} catch (Exception e) {
			mark = false;
			//System.out.println(e);
			e.printStackTrace();
		}
		return mark;
	}

	@RequestMapping("imagehandle/showupload")
	public String showupload(Integer uploadtype,HttpServletRequest request) {
		// System.out.println("shop img is ok!");
 		request.setAttribute("uploadtype", uploadtype);
		return "/uploadimg/showupload";
	}
 
	public String showuploadvideo(HttpServletRequest request, int uploadtype) {
		// System.out.println("shop img is ok!");
		return "showuploadvideo";
	}

	public String showuploadimages_result() {
		return "";
	}
	
	private String getDivPath(Integer uploadtype){
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
		return dirPath;
	}
	
}
