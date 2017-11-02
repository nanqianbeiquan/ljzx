/**   
* @Title: Base64Utils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年8月18日 上午9:34:19 
* @version V1.0   
*/
package com.srd.ljzd.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * @ClassName: Base64Utils
 * @Description: Base64加密工具类
 * @author shiyong
 * @date 2016年8月18日 上午9:34:19
 *  
 */
public class Base64Utils {
	/** 
    * @Title: encryptByBase64 
    * @Description: 使用Base64进行加密
    * @param @param str
    * @param @return 设定文件 
    * @return String 返回类型 
    * @throws 
    * @author shiyong
    * @date 2016年7月11日 下午2:18:12
    */
    public static String encryptByBase64(String str) {  
        byte[] b = null;
        String s = null;
        
        try {
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        
        return s;  
    }
  
    /** 
    * @Title: decryptByBase64 
    * @Description: 使用Base64进行解密
    * @param @param s
    * @param @return 设定文件 
    * @return String 返回类型 
    * @throws 
    * @author shiyong
    * @date 2016年7月11日 下午2:19:10
    */
    public static String decryptByBase64(String s) {
        byte[] b = null;
        String result = null;
        
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String str = System.currentTimeMillis() + "4028819c54dc38460154e139a1b000c0Function8ForAjax";
		
		System.out.println(encryptByBase64(str));
    	
    	System.out.println(decryptByBase64("MTQ3MTUxNTQ4NzE3NDQwMjg4MTljNTRkYzM4NDYwMTU0ZTEzOWExYjAwMGMwMDLmlq/nkZ7lvrcs6aOO5aOw5L+h55So"));
	}
}
