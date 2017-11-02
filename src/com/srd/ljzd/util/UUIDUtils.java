/**   
* @Title: UUIDUtils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月19日 下午4:06:53 
* @version V1.0   
*/
package com.srd.ljzd.util;

import java.util.UUID;

/** 
 * @ClassName: UUIDUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月19日 下午4:06:53
 *  
 */
public class UUIDUtils {
	/** 
	* @Title: getUUID 
	* @Description: 生成UUID
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年9月13日 下午1:13:07
	*/
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		
        //去掉“-”符号 
        return uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24); 
	}
}
