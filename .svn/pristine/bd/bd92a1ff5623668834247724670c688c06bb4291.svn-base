/**   
* @Title: Global.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年5月6日 上午10:17:57 
* @version V1.0   
*/
package com.srd.ljzd.config;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.srd.ljzd.util.PropertiesLoader;

/** 
 * @ClassName: Global
 * @Description: 全局配置类
 * @author shiyong
 * @date 2016年5月6日 上午10:17:57
 *  
 */
public class Global {
	
	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("/resources/config/interface.properties","/resources/data/constant.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		
		if (value == null || value.equals("")){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		
		return value;
	}
	
}
