package com.axp.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import sun.misc.BASE64Decoder;

public class StringToPicUtil {

	// 图片转化成base64字符串
	/*public static String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = System.getProperty("user.dir") + "/uploadPic/" + UUID.randomUUID() + ".jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}*/

	// base64字符串转化成图片
	public static String StringToImage(String imgStr, String realPath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return null;

		// 裁剪不需要的部分；
		int indexOf = imgStr.indexOf(",/");
		imgStr = imgStr.substring(indexOf + 1);

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String newName = UUID.randomUUID() + ".jpg";
			String imgFilePath = realPath + "/" + newName;

			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return newName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
