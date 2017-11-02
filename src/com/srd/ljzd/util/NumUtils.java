/**   
* @Title: NumUtils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年8月17日 下午3:25:17 
* @version V1.0   
*/
package com.srd.ljzd.util;

import java.util.Random;

/** 
 * @ClassName: NumUtils
 * @Description: 数字处理工具类
 * @author shiyong
 * @date 2016年8月17日 下午3:25:17
 *  
 */
public class NumUtils {
	
	/** 
	* @Title: getRandomInteger 
	* @Description: 获取一定范围内的随机数
	* @param @param start
	* @param @param end
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年8月17日 下午3:33:49
	*/
	public static int getRandomInteger(int start, int end){
		int result = 0;
		
		Random rand = new Random();
		result = rand.nextInt(end) + start;
		
		return result;
	}
	
	
	
}
