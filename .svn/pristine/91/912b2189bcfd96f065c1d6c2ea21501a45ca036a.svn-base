/**   
* @Title: EncodeUtils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年5月11日 下午2:31:05 
* @version V1.0   
*/
package com.srd.ljzd.util;


/** 
 * @ClassName: EncodeUtils
 * @Description: 编码工具类
 * @author shiyong
 * @date 2016年5月11日 下午2:31:05
 *  
 */
public class EncodeUtils {
	/** 
     * 将字符串编码成 Unicode 。 
     * @param theString 待转换成Unicode编码的字符串。 
     * @param escapeSpace 是否忽略空格。 
     * @return 返回转换后Unicode编码的字符串。 
     */  
    public static String toUnicode(String theString, boolean escapeSpace) {  
        int len = theString.length();  
        int bufLen = len * 2;  
        if (bufLen < 0) {  
            bufLen = Integer.MAX_VALUE;  
        }  
        StringBuffer outBuffer = new StringBuffer(bufLen);  
  
        for(int x=0; x<len; x++) {  
            char aChar = theString.charAt(x);  
            // Handle common case first, selecting largest block that  
            // avoids the specials below  
            if ((aChar > 61) && (aChar < 127)) {  
                if (aChar == '\\') {  
                    outBuffer.append('\\'); outBuffer.append('\\');  
                    continue;  
                }  
                outBuffer.append(aChar);  
                continue;  
            }  
            switch(aChar) {  
                case ' ':  
                    if (x == 0 || escapeSpace)  
                        outBuffer.append('\\');  
                    outBuffer.append(' ');  
                    break;  
                case '\t':outBuffer.append('\\'); outBuffer.append('t');  
                          break;  
                case '\n':outBuffer.append('\\'); outBuffer.append('n');  
                          break;  
                case '\r':outBuffer.append('\\'); outBuffer.append('r');  
                          break;  
                case '\f':outBuffer.append('\\'); outBuffer.append('f');  
                          break;  
                case '=': // Fall through  
                case ':': // Fall through  
                case '#': // Fall through  
                case '!':  
                    outBuffer.append('\\'); outBuffer.append(aChar);  
                    break;  
                default:  
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {  
                        outBuffer.append('\\');  
                        outBuffer.append('u');  
                        outBuffer.append(toHex((aChar >> 12) & 0xF));  
                        outBuffer.append(toHex((aChar >>  8) & 0xF));  
                        outBuffer.append(toHex((aChar >>  4) & 0xF));  
                        outBuffer.append(toHex( aChar        & 0xF));  
                    } else {  
                        outBuffer.append(aChar);  
                    }  
            }  
        }  
        return outBuffer.toString();  
    }  
     
    /** 
     * 从 Unicode 码转换成编码前的特殊字符串。 
     * @param in Unicode编码的字符数组。 
     * @param off 转换的起始偏移量。 
     * @param len 转换的字符长度。 
     * @param convtBuf 转换的缓存字符数组。 
     * @return 完成转换，返回编码前的特殊字符串。 
     */  
    public String fromUnicode(char[] in, int off, int len, char[] convtBuf) {  
        if (convtBuf.length < len) {  
            int newLen = len * 2;  
            if (newLen < 0) {  
                newLen = Integer.MAX_VALUE;  
            }  
            convtBuf = new char[newLen];  
        }  
        char aChar;  
        char[] out = convtBuf;  
        int outLen = 0;  
        int end = off + len;  
  
        while (off < end) {  
            aChar = in[off++];  
            if (aChar == '\\') {  
                aChar = in[off++];  
                if (aChar == 'u') {  
                    // Read the xxxx  
                    int value = 0;  
                    for (int i = 0; i < 4; i++) {  
                        aChar = in[off++];  
                        switch (aChar) {  
                        case '0':  
                        case '1':  
                        case '2':  
                        case '3':  
                        case '4':  
                        case '5':  
                        case '6':  
                        case '7':  
                        case '8':  
                        case '9':  
                            value = (value << 4) + aChar - '0';  
                            break;  
                        case 'a':  
                        case 'b':  
                        case 'c':  
                        case 'd':  
                        case 'e':  
                        case 'f':  
                            value = (value << 4) + 10 + aChar - 'a';  
                            break;  
                        case 'A':  
                        case 'B':  
                        case 'C':  
                        case 'D':  
                        case 'E':  
                        case 'F':  
                            value = (value << 4) + 10 + aChar - 'A';  
                            break;  
                        default:  
                            throw new IllegalArgumentException(  
                                    "Malformed \\uxxxx encoding.");  
                        }  
                    }  
                    out[outLen++] = (char) value;  
                } else {  
                    if (aChar == 't') {  
                        aChar = '\t';  
                    } else if (aChar == 'r') {  
                        aChar = '\r';  
                    } else if (aChar == 'n') {  
                        aChar = '\n';  
                    } else if (aChar == 'f') {  
                        aChar = '\f';  
                    }  
                    out[outLen++] = aChar;  
                }  
            } else {  
                out[outLen++] = (char) aChar;  
            }  
        }  
        return new String(out, 0, outLen);  
    }
    
    private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    
    private static char toHex(int nibble) {
    	return hexDigit[(nibble & 0xF)];
	} 
    
    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
     
        StringBuffer unicode = new StringBuffer();
     
        for (int i = 0; i < string.length(); i++) {
     
            // 取出每一个字符
           char c = string.charAt(i);
     
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
     
        return unicode.toString();
    }

    /**
     * unicode 转字符串 %u77F3类型
    */
    public static String unicode2String(String unicode) {
     
        StringBuffer string = new StringBuffer();
        
        String[] hex = unicode.split("\\\\u");
        
        for (int i = 1; i < hex.length; i++) {
        	
            // 转换出每一个代码点
           int data = Integer.parseInt(hex[i], 16);
     
            // 追加成string
            string.append((char) data);
        }
        
        return string.toString();
    }
    
    public static String tmptrans(String s){
    	int i=s.length();
    	int j=0;
	    String s5="";
	    String s6="";
	    
	    for(;j<i;j++){
	    	String s2 = s.substring(j, j + 1);
	    	
	    	if(s2.equals("%")){
	    		String s4 = s.substring(j, j + 3);
	    		if(s4.equals("%5C")){
	    			s5 = "\\";
	    		}
	    		if(s4.equals("%22")){
	    			s5 = "\"";
	    		}
    
			    if(s4.equals("%3C"))
			    s5 = "<";
			    if(s4.equals("%3E"))
			    s5 = ">";
			    if(s4.equals("%3A"))
			    s5 = ":";
			    if(s4.equals("%3B"))
			    s5 = ";";
			    if(s4.equals("%27"))
			    s5 = "'";
			    if(s4.equals("%21"))
			    s5 = "!";
			    if(s4.equals("%23"))
			    s5 = "#";
			    if(s4.equals("%24"))
			    s5 = "$";
			    if(s4.equals("%25"))
			    s5 = "%";
			    if(s4.equals("%5E"))
			    s5 = "^";
			    if(s4.equals("%26"))
			    s5 = "&";
			    if(s4.equals("%28"))
			    s5 = "(";
			    if(s4.equals("%29"))
			    s5 = ")";
			    if(s4.equals("%3D"))
			    s5 = "=";
			    if(s4.equals("%7B"))
			    s5 = "{";
			    if(s4.equals("%7D"))
			    s5 = "}";
			    if(s4.equals("%5B"))
			    s5 = "[";
			    if(s4.equals("%5D"))
			    s5 = "]";
			    if(s4.equals("%20"))
			    s5 = " ";
			    if(s4.equals("%2C"))
			    s5 = ",";
			    if(s4.equals("%3F"))
			    s5 = "?";
			    if(s4.equals("%7C"))
			    s5 = "|";
			    if(s4.equals("%60"))
			    s5 = "`";
			    if(s4.equals("%7E"))
			    s5 = "~";
			    if(s4.equals("%0A"))
			    s5 = " ";
			    if(s4.equals("%0D"))
			    s5 = " ";
			    if(s4.equals("%09"))
			    s5 = " ";
			    
			    s6 += s5;
			    j += 3;
	    	}else{
	    		s6 += s2;
	    		j++;
	    	}
	    }
	    return s6;
    }
    
    public static String unescape(String s)
    {
    	StringBuffer result = new StringBuffer();
    	
    	if(s == null){
    		return s;
    	}
    	
    	if(s.equals("")){
    		return s;
    	}
    	
    	String s1 = s.replaceAll("_%2C", ",");
    	String s2 = s1.replaceAll("%2C", ",");
    	String s3 = s2.replace('%', '\\');
    	
    	String array[] = s3.split(",");
    	
    	for(int i=0;i<array.length;i++){
    		String array2[] = array[i].split("_");
    		
    		for(int j=0;j<array2.length;j++){
    			int flag = array2[j].indexOf("\\u");
    			
    			if(flag >= 0){
    				result.append(unicode2String(array2[j])+",");
    			}else{
    				result.append(array2[j]);
    			}
    		}
    		
    		result.append("_");
    	}
    	
    	return result.toString();
    }
}

