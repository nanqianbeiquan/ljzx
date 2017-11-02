/**   
* @Title: ShortMessageServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月10日 上午11:26:09 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.sys;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.sys.ShortMessageService;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.LoggerUtils;

/** 
 * @ClassName: ShortMessageServiceImpl
 * @Description: 短消息服务Service实现类
 * @author shiyong
 * @date 2017年9月10日 上午11:26:09
 *  
 */
@Service("shortMessageService")
public class ShortMessageServiceImpl implements ShortMessageService {

	private String messageService = Global.getConfig("messageService");
	private String sendSMS = Global.getConfig("sendSMS");
	
	@Override
	public ResultBean sendShortMessage(String cellphone, String msg){
		ResultBean result = new ResultBean();
		
		String url = messageService + sendSMS;
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cellphone", cellphone);
		paramMap.put("msg", msg);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);
		
		if(jsonResult != null){
			if(jsonResult.containsKey("errorCode")){
				if(jsonResult.getString("errorCode").equals("0000")){
					result.setResultCode("0");
					result.setResultMsg("发送短信成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("发送短信失败！");
					
					LoggerUtils.error("短信发送失败(" + jsonResult.getString("errorCode") + ": " + jsonResult.getString("message") +  ")，手机号:" + cellphone + ", 内容：" + msg);
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("发送短信失败！");
				
				LoggerUtils.error("未获取到状态码，jsonResult：" + jsonResult.toJSONString());
			}
		}
		
		return result;
	}
	
}
