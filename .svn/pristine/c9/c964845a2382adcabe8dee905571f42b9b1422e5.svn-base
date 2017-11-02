package com.srd.ljzd.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 版权所有：2016 项目名称：ljzd1.4
 *
 * 类描述： 类名称：com.srd.ljzd.util.FileUtils 创建人：裴子辉 创建时间：2016年7月28日 下午4:09:14 修改人：
 * 修改时间：2016年7月28日 下午4:09:14 修改备注：
 * 
 * @version V1.3
 */

public class FileUtils {

	protected static Logger log = LogManager.getLogger(FileUtils.class.getName());
	
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	public static void Copy(String oldPath, String newPath) {

		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage(), e);
		}
	}

	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);
		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);
		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();
		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				copyFile(sourceFile, targetFile);
			}

			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}
	
	
	/**
	* @Title: downFileFromServer
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param  @param response
	* @param  @param outputFile 输出文件名及路径
	* @param  @param contentType 文件类型
	* @param  @param downLoadFileName 下载文件名称
	* @param  @param extensionFormateName 扩展格式名
	* @param  @throws FileNotFoundException
	* @param  @throws IOException
	* @param  @throws UnsupportedEncodingException  
	* @return void    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2016年9月7日 上午10:15:40
	*/
	public static void downFileFromServer(HttpServletResponse response,
			String outputFile,String contentType,String downLoadFileName,String extensionFormateName) throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		// 读取XML文件输入到页面
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		FileInputStream fis=new FileInputStream(outputFile);
		BufferedInputStream bis=new BufferedInputStream(fis);
		int c=bis.read();//读取bis流中的下一个字节
		while(c!=-1){
		     baos.write(c);
		     c=bis.read();
		}
		bis.close();
		fis.close();
		
		response.setContentType(contentType);  
		response.setContentLength(baos.size()); 
		// 设置文件名称；
		String reportName =new String(downLoadFileName.getBytes("gb2312"), "ISO8859-1" );
		String xlsName = reportName+"_"+DateUtils.getCurrentDateAllStr()+extensionFormateName;
		log.info("pdfName ==="+xlsName);
		response.setHeader("Content-disposition","attachment; filename="+xlsName); 
		//获取响应数据流 
		ServletOutputStream   servletout = response.getOutputStream(); 
		//将pdf数据流写入响应数据流中 
		baos.writeTo(servletout); 
		servletout.flush(); 
		servletout.close();
		baos.flush();
		baos.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// FileUtils.createDir("e:/pdf");

		String s = "E:\\tomcat8\\webapps\\ljzd\\images\\pdf\\report";
		String t = "E:\\htmlFileForPdf\\images\\pdf\\report";

		// FileUtils.Copy(s, t);
		try {
			FileUtils.copyDirectiory(s, t);
		} catch (IOException e) {
			LoggerUtils.error(e.getMessage(), e);
		}

	}

}
