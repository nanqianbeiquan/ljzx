/**   
* @Title: EmailServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.sys 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月10日 上午11:37:28 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.sys.EmailService;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: EmailServiceImpl
 * @Description: 邮件服务实现类
 * @author shiyong
 * @date 2017年9月10日 上午11:37:28
 *  
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	private String messageService = Global.getConfig("messageService");
	private String sendEmail = Global.getConfig("sendEmail");
	
	@Override
	public ResultBean sendEmail(List<String> recipientList, String msg, String subject){
		ResultBean result = new ResultBean();
		
		String url = messageService + sendEmail;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tos", recipientList);
		
		if(StringUtils.isEmpty(msg)){
			paramMap.put("messageText", "正文为空！");
		}else{
			paramMap.put("messageText", msg);
		}
		
		paramMap.put("subject", subject);
		
		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest1(url, paramMap);
		
		if(jsonResult != null){
			if(jsonResult.containsKey("errorCode")){
				if(jsonResult.getString("errorCode").equals("0000")){
					result.setResultCode("0");
					result.setResultMsg("发送邮件成功！");
				}else{
					result.setResultCode("1");
					result.setResultMsg("发送邮件失败！");
					
					LoggerUtils.error("邮件发送失败(" + jsonResult.getString("errorCode") + ": " + jsonResult.getString("message"));
				}
			}else{
				LoggerUtils.error("未获取到状态码，jsonResult：" + jsonResult.toJSONString());
			}
		}
		
		return result;
	}
}
