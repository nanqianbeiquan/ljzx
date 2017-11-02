/**   
* @Title: MessageUtils.java 
* @Package com.srd.ljzd.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年10月13日 上午11:17:06 
* @version V1.0   
*/
package com.srd.ljzd.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;

/** 
 * @ClassName: MessageUtils
 * @Description: 消息发送工具类
 * @author shiyong
 * @date 2016年10月13日 上午11:17:06
 *  
 */
public class MessageUtils {

	/** 
	* @Title: sendSMS 
	* @Description: 发送短消息
	* @param @param cellphone
	* @param @param msg 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年10月13日 上午11:18:36
	*/
	public static void sendSMS(String cellphone, String msg){
		
		String url = Global.getConfig("messageService") + Global.getConfig("sendSMS");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cellphone", cellphone);
		params.put("msg", msg);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest1(url, params);
		
		if(jsonResult != null){
			if(jsonResult.containsKey("errorCode")){
				if(!jsonResult.getString("errorCode").equals("0000")){
					LoggerUtils.error("短信发送失败(" + jsonResult.getString("errorCode") + ": " + jsonResult.getString("message") +  ")，手机号:" + cellphone + ", 内容：" + msg);
				}
			}else{
				LoggerUtils.error("未获取到状态码，jsonResult：" + jsonResult.toJSONString());
			}
		}
	}
	
	/** 
	* @Title: sendEmail 
	* @Description: 发送电子邮件
	* @param @param recipients
	* @param @param msg
	* @param @param subject 设定文件 
	* @return void 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年10月13日 上午11:22:41
	*/
	public static void sendEmail(List<String> recipientList, String msg, String subject){
		
		String url = Global.getConfig("messageService") + Global.getConfig("sendEmail");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tos", recipientList);
		
		if(StringUtils.isEmpty(msg)){
			params.put("messageText", "正文为空！");
		}else{
			params.put("messageText", msg);
		}
		
		params.put("subject", subject);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest1(url, params);
		
		if(jsonResult != null){
			if(jsonResult.containsKey("errorCode")){
				if(!jsonResult.getString("errorCode").equals("0000")){
					LoggerUtils.error("邮件发送失败(" + jsonResult.getString("errorCode") + ": " + jsonResult.getString("message"));
					
					LoggerUtils.error("收件人：");
					
					for(int i=0;i<recipientList.size();i++){
						LoggerUtils.error(recipientList.get(i));
					}
					
					LoggerUtils.error("主题：" + subject);
					LoggerUtils.error("正文：" + msg);
					
				}
			}else{
				LoggerUtils.error("未获取到状态码，jsonResult：" + jsonResult.toJSONString());
			}
		}
	}
	
	
	
}
