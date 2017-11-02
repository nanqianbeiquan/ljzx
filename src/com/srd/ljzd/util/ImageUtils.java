package com.srd.ljzd.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.security.crypto.codec.Base64;
import sun.misc.BASE64Encoder;


public class ImageUtils {
	// 图片转化成base64字符串
	public static String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "E:/image/xl.jpg";// 待处理的图片
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
	}

	// base64字符串转化成图片
	@SuppressWarnings("static-access")
	public static String GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) { // 图像数据为空
			return null;
		}
		Base64 decoder = new Base64();
		try {
			// Base64解码
			byte[] b = decoder.decode(imgStr.getBytes());
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			String  strMing = new String(b);
			
			// 生成jpeg图片
			String imgFilePath = "E:/image/test720.jpg";// 新生成的图片
			FileOutputStream  out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return strMing;
		} catch (Exception e) {
			return null;
		}
	}
/*	public static void main(String[] args) {
		String reultKey= GetImageStr();
		System.out.println(reultKey);
		Boolean flag= GenerateImage(reultKey);
		System.out.println(flag);
		
	}	*/
}
