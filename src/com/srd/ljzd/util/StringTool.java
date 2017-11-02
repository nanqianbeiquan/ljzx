package com.srd.ljzd.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 版权所有：2016 项目名称：ljzdV1.2
 *
 * 类描述： 类名称：com.srd.ljzd.util.StringTool 创建人：裴子辉 创建时间：2016年5月6日 上午11:27:53 修改人：
 * 修改时间：2016年5月6日 上午11:27:53 修改备注：
 * 
 * @version V1.0
 */

public class StringTool {
	
	public static boolean isNotNullAndBlack(String s) {

		return StringUtils.isNotBlank(s) && StringUtils.isNotEmpty(s)
				&& (!s.equalsIgnoreCase("null"));
	}

	public static String ifNullToBlack(String s) {

		if (s == null) {
			s = "";
		} else if (s.equalsIgnoreCase("null")) {
			s = "";
		}
		return s;
	}

	/**
	 * <pre>
	 * 将字符串从右至左每三位加一逗号
	 * </pre>
	 * 
	 * @param str
	 *            需要加逗号的字符串
	 * @return 以从右至左每隔3位加一逗号显示
	 */
	public static String displayWithComma(String str) {

		String intStr = "";
		String smallStr = "";
		String str2 = "";

		try {
			Double d = Double.parseDouble(str);
		} catch (Exception e) {
			return str;

		}

		if (StringTool.isNotNullAndBlack(str)) {
			if(str.lastIndexOf(".")>0){
				intStr = str.substring(0, str.lastIndexOf("."));
				smallStr = str.substring(str.lastIndexOf("."), str.length());
				str = intStr;
			}
		
			
			
			str = new StringBuffer(str).reverse().toString(); // 先将字符串颠倒顺序
			//System.out.println("str=="+str);

			int size = (str.length() % 3 == 0) ? (str.length() / 3) : (str
					.length() / 3 + 1); // 每三位取一长度

			/*
			 * 比如把一段字符串分成n段,第n段可能不是三个数,有可能是一个或者两个,
			 * 现将字符串分成两部分.一部分为前n-1段,第二部分为第n段.前n-1段，每一段加一",".而第n段直接取出即可
			 */
			for (int i = 0; i < size - 1; i++) { // 前n-1段
				str2 += str.substring(i * 3, i * 3 + 3) + ",";
			}

			for (int i = size - 1; i < size; i++) { // 第n段
				str2 += str.substring(i * 3, str.length());
			}

			str2 = new StringBuffer(str2).reverse().toString();
			str2 += smallStr;
		}
		return str2;
	}

	private static int chineseNumber2Int(String chineseNumber){
		  int result = 0;
		  int temp = 1;//存放一个单位的数字如：十万
		  int count = 0;//判断是否有chArr
		  char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
		  char[] chArr = new char[]{'十','百','千','万','亿'};
		  for (int i = 0; i < chineseNumber.length(); i++) {
		    boolean b = true;//判断是否是chArr
		    char c = chineseNumber.charAt(i);
		    for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
		      if (c == cnArr[j]) {
		        if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
		          result += temp;
		          temp = 1;
		          count = 0;
		        }
		        // 下标+1，就是对应的值
		        temp = j + 1;
		        b = false;
		        break;
		      }
		    }
		    if(b){//单位{'十','百','千','万','亿'}
		      for (int j = 0; j < chArr.length; j++) {
		        if (c == chArr[j]) {
		          switch (j) {
		          case 0:
		            temp *= 10;
		            break;
		          case 1:
		            temp *= 100;
		            break;
		          case 2:
		            temp *= 1000;
		            break;
		          case 3:
		            temp *= 10000;
		            break;
		          case 4:
		            temp *= 100000000;
		            break;
		          default:
		            break;
		          }
		          count++;
		        }
		      }
		    }
		    if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
		      result += temp;
		    }
		  }
		  return result;
  }

	 
	public static String formartStatus(String status){
 		if(isNotNullAndBlack(status)){ 	
 			if(status.indexOf("存续")>-1){
 				status ="存续";
 			}else if(status.indexOf("吊销")>-1)
 			{
 				status ="吊销";
 			}
 		}
 		
 		return status;
 	}
	
	/** 
	* @Title: ToSBC 
	* @Description: 半角转全角
	* @param @param input
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年7月4日 下午1:30:53
	*/
	public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
          if (c[i] == ' ') {
            c[i] = '\u3000';
          } else if (c[i] < '\177') {
            c[i] = (char) (c[i] + 65248);
          }
        }
        return new String(c);
	}
	
	
	
	/** 
	* @Title: ToDBC 
	* @Description: 全角转半角
	* @param @param input
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年7月4日 下午1:30:24
	*/
	public static String ToDBC(String input) {
		
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
          if (c[i] == '\u3000') {
            c[i] = ' ';
          } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
            c[i] = (char) (c[i] - 65248);
          }
        }
        
        String returnString = new String(c);
        
        return returnString;
	}
	 
	/**
	* @Title: formartForPdf
	* @Description: TODO(格式化字段，剔除特殊字符，否则容易报错)
	* @param  @param str
	* @param  @return  
	* @return String    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2016年8月4日 下午3:37:27
	*/
	public static String formartForPdf(String str) {
		if(!isNotNullAndBlack(str)){
			return "";
		}else{
			//str = str.replace("<br>", "");
			//str = str.replace("&", "");
			str = str.replaceAll("[\\x00-\\x08\\x0B-\\x1F<br>&\\\\b]", "");
			return str;
		}
	}
	
	
	
	/**
	* @Title: formartForPdfName
	* @Description: TODO(格式化字段，剔除特殊字符，否则容易报错)
	* @param  @param str
	* @param  @return  
	* @return String    返回类型
	* @author zihui.pei  
	* @throws
	* @date 2016年8月4日 下午3:37:27
	*/
	public static String formartForPdfName(String str) {
		if(!isNotNullAndBlack(str)){
			return "";
		}else{
			str = str.replace("<br>", "");
			str = str.replace("&", "");
			str = str.replace(",", "");
			str = str.replace("*", "");
			return str;
		}
	}
	    
	public static void main(String[] args) {
		String s = "null";
		//System.out.println("s=" + StringTool.isNotNullAndBlack(s));

		String str = "5000.00";
		//System.out.println("test ===" + StringTool.displayWithComma(str));
		// addComma
		// //System.out.println(StringTool.addComma(str));
/*
		String t = "abc.def";
		//System.out.println("length ==" + t.split(".").length);
		*/
		
		//System.out.println("name==="+StringTool.formartForPdfName("(ab*你好)" ));
		//System.out.println("name==="+StringTool.formartForPdf("(a<br>b*你<br>好)" ));
		//System.out.println("name==="+"(ab*你好)".replace("*", ""));
	}
	
	//判断字符串是否为数字，不能识别负数和科学计数法的数字，
	public final static boolean isNumeric(String s) {  
        if (s != null && !"".equals(s.trim()))  
            return s.matches("^[0-9.]*$");  
        else  
            return false;  
    }
	
	public static String formartForSqlInSentence(String str){
		String [] typeArray = new String []{};
		typeArray = str.split(",");
		StringBuffer sb = new StringBuffer("(");
		for(int i=0;i<typeArray.length;i++){
			sb.append("'"+typeArray[i]+"'");
			if(i!=typeArray.length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
