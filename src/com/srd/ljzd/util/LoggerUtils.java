/**   
* @Title: LoggerUtils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年4月13日 上午10:51:26 
* @version V1.0   
*/
package com.srd.ljzd.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** 
 * @ClassName: LoggerUtils
 * @Description: 日志工具类
 * @author shiyong
 * @date 2017年4月13日 上午10:51:26
 *  
 */
public class LoggerUtils {
	
	/** 
	* @Title: findCaller 
	* @Description: 获取最原始被调用的堆栈信息
	* @param @return 设定文件 
	* @return StackTraceElement 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月13日 上午10:55:42
	*/
	public static StackTraceElement findCaller() {
	    // 获取堆栈信息
	    StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
	    
	    if(null == callStack){
	    	return null;
	    }

	    // 最原始被调用的堆栈信息
	    StackTraceElement caller = null;
	    
	    // 日志类名称
	    String logClassName = LoggerUtils.class.getName();
	    
	    // 循环遍历到日志类标识
	    boolean isEachLogClass = false;

	    // 遍历堆栈信息，获取出最原始被调用的方法信息
	    for (StackTraceElement s : callStack) {
	        // 遍历到日志类
	        if(logClassName.equals(s.getClassName())) {
	            isEachLogClass = true;
	        }
	        
	        // 下一个非日志类的堆栈，就是最原始被调用的方法
	        if(isEachLogClass) {
	            if(!logClassName.equals(s.getClassName())) {
	                isEachLogClass = false;
	                caller = s;
	                break;
	            }
	        }
	    }

	    return caller;
	}

	
	/** 
	* @Title: logger 
	* @Description: 自动匹配请求类名，生成logger对象，此处 logger name 值为 [className].[methodName]() Line: [fileLine]
	* @param @return 设定文件 
	* @return Logger 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月13日 上午10:55:56
	*/
	private static Logger logger() {
	    // 最原始被调用的堆栈对象
	    StackTraceElement caller = findCaller();
	    
	    if(null == caller){
	    	return LogManager.getLogger(LoggerUtils.class.getName());
	    }

	    Logger log = LogManager.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());

	    return log;
	}


	public static void trace(String msg) {
	    trace(msg, null);
	}
	
	public static void trace(String msg, Throwable e) {
	    logger().trace(msg, e);
	}
	
	public static void debug(String msg) {
	    debug(msg, null);
	}
	
	public static void debug(String msg, Throwable e) {
	    logger().debug(msg, e);
	}
	
	public static void info(String msg) {
	    info(msg, null);
	}
	
	public static void info(String msg, Throwable e) {
	    logger().info(msg, e);
	}
	
	public static void warn(String msg) {
	    warn(msg, null);
	}
	
	public static void warn(String msg, Throwable e) {
	    logger().warn(msg, e);
	}
	
	public static void error(String msg) {
	    error(msg, null);
	}
	
	public static void error(String msg, Throwable e) {
	    logger().error(msg, e);
	}
}
