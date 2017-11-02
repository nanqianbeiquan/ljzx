package com.srd.ljzd.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 版权所有：
 * 项目名称：ljzx 
 *
 * 类描述：对于空字符串的处理
 * 类名称：com.srd.ljzd.util  
 * 创建人：jiang.zhou
 * 创建时间：20162016年12月22日下午1:15:29
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public final class StringUtils4Dev {

	public static String stringEmptyDeal(String str){
		if(StringUtils.isEmpty(str)||"\\N".equals(str)||"null".equals(str)){
			return "--";
		}else{
			return str;
		}
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
		   Pattern pattern = Pattern.compile("[0-9.]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
}
