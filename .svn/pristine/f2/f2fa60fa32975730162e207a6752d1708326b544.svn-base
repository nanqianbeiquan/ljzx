package com.srd.ljzd.util;

import org.apache.commons.lang.StringUtils;

/**   
 * 版权所有：2016
 * 项目名称：ljzdV1.2   
 *
 * 类描述：
 * 类名称：com.srd.ljzd.util.StringEscapeEditor     
 * 创建人：裴子辉
 * 创建时间：2016年5月6日 上午11:27:53   
 * 修改人：
 * 修改时间：2016年5月6日 上午11:27:53   
 * 修改备注：   
 * @version   V1.0    
 */
  
public class StringEscapeEditor  {

	/** 
	 * @Title: string2Json 
	 * @Description: TODO(JSON 特殊字符转义) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author zihui.pei
	 * @return String 返回类型 
	 * @date 2016年5月6日 上午11:28:01
	 * @throws 
	 */ 
	public static String string2Json(String s) {       
	    StringBuffer sb = new StringBuffer ();       
	    for (int i=0; i<s.length(); i++) {       
	     
	        char c = s.charAt(i);       
	        switch (c) {       
	        case '\"':       
	            sb.append("\\\"");       
	            break;       
	        case '\\':       
	            sb.append("\\\\");       
	            break;       
	        case '/':       
	            sb.append("\\/");       
	            break;       
	        case '\b':       
	            sb.append("\\b");       
	            break;       
	        case '\f':       
	            sb.append("\\f");       
	            break;       
	        case '\n':       
	            sb.append("\\n");       
	            break;       
	        case '\r':       
	            sb.append("\\r");       
	            break;       
	        case '\t':       
	            sb.append("\\t");       
	            break;
	            
	        default:       
	            sb.append(c);       
	        }  
	       
	 }
	    return sb.toString();    
	}
	
	public static boolean isNotNullAndBlack(String s) { 
		
		return StringUtils.isNotBlank(s)&&StringUtils.isNotEmpty(s);
	}  
	
	public static void main(String[] args) {
		String s= "s";
		System.out.println("s="+StringEscapeEditor.isNotNullAndBlack(s));
	}
}
