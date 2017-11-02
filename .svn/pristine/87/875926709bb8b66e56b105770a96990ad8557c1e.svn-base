/**   
* @Title: StringUtils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年8月17日 下午3:25:00 
* @version V1.0   
*/
package com.srd.ljzd.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @ClassName: StringUtils
 * @Description: 字符串处理工具类
 * @author shiyong
 * @date 2016年8月17日 下午3:25:00
 *  
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/** 
	* @Title: isNotEmpty 
	* @Description: 判断字符串是否为空
	* @param @param str
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年8月17日 下午3:57:00
	*/
	public static boolean isNotEmpty(String str){
		boolean result = false;
		
		if(null != str && !"null".equalsIgnoreCase(str) && !"".equals(str) && !"".equals("--")){
			if(!"".equals(str.trim())){
				result = true;
			}
		}
		
		return result;
	}
	
	public static boolean isEmpty(String str){
		boolean result = false;
		
		if(null == str || "null".equalsIgnoreCase(str) || "".equals(str)){
			result = true;
		}
		
		return result;
	}
	
	/** 
	* @Title: getRandomString 
	* @Description: 获取固定长度的字符串
	* @param @param length
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年8月17日 下午3:43:19
	*/
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	    
	    Random random = new Random();
	    
	    StringBuffer sb = new StringBuffer();
	    
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(base.length());
	        
	        sb.append(base.charAt(number));
	    }
	    
	    return sb.toString();
	 }  
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
