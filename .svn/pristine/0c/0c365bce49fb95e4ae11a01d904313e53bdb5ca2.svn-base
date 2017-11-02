/**   
* @Title: MonitorUserConfigureController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月24日 下午5:29:47 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorUserConfigure;
import com.srd.ljzd.service.monitor.MonitorUserConfigureService;
import com.srd.ljzd.service.sys.AuthService;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorUserConfigureController
 * @Description: 动态监控个人设置Controller
 * @author shiyong
 * @date 2016年11月24日 下午5:29:47
 *  
 */
@Controller
@RequestMapping("/monitorConfigure")
public class MonitorUserConfigureController extends BaseController {
	@Autowired
	private MonitorUserConfigureService monitorUserConfigureService;
	
	@Autowired
	private AuthService authService;

	/** 
	* @Title: toMonitorSetting 
	* @Description: 跳转到动态监控设置页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月17日 上午9:01:51
	*/
	@RequestMapping("/toMonitorSetting")
	public String toMonitorSetting(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String accountId = getAccountIdFromSession(request);
		
		MonitorUserConfigure monitorUserConfigure = monitorUserConfigureService.getMonitorUserConfigureByAccountId(accountId);
		
		model.addAttribute("monitorUserConfigure", monitorUserConfigure);
		
		String[] emails = null;
		
		if(StringUtils.isNotEmpty(monitorUserConfigure.getEmails())){
			emails = monitorUserConfigure.getEmails().split(",");
		}else{
			emails = new String[0];
		}
		
		List<String> emailList = new ArrayList<String>();
		
		for(int i=0;i<emails.length;i++){
			emailList.add(emails[i]);
		}
		
		model.addAttribute("emailList", emailList);
		model.addAttribute("emailNum", emailList.size()+1);
		
		return "/modules/monitor/monitor_setting";
	}
	
	/** 
	* @Title: updateMonitorUserConfigure 
	* @Description: 更新用户动态监控设置
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月25日 上午11:52:54
	*/
	@RequestMapping("/updateMonitorUserConfigure")
	@ResponseBody
	public ResultBean updateMonitorUserConfigure(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String monitorCycle = request.getParameter("monitorCycle");
		String attentionEventClass = request.getParameter("attentionEventClasses");
		String notifyType = request.getParameter("notifyType");
		String emails = request.getParameter("emails");
		
		notifyType = notifyType.substring(0, 1) + notifyType.substring(2);
		
		if(StringUtils.isNotEmpty(emails) && ",".equals(emails.substring(0, 1))){
			emails = emails.substring(1, emails.length());
		}
		
		if(StringUtils.isNotEmpty(emails) && ",".equals(emails.substring(emails.length()-1, emails.length()))){
			emails = emails.substring(0, emails.length()-1);
		}
		
		String accountId = getAccountIdFromSession(request);
		
		MonitorUserConfigure monitorUserConfigure = monitorUserConfigureService.getMonitorUserConfigureByAccountId(accountId);
		monitorUserConfigure.setMonitorCycle(monitorCycle);
		monitorUserConfigure.setAttentionEventClass(attentionEventClass);
		monitorUserConfigure.setNotifyType(notifyType);
		monitorUserConfigure.setEmails(emails);
		
		result = monitorUserConfigureService.updateMonitorUserConfigure(monitorUserConfigure);
		
		return result;
	}
	
	/** 
	* @Title: addMonitorConfigureEmail 
	* @Description: 添加邮箱
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月3日 下午5:03:14
	*/
	@RequestMapping("/addMonitorConfigureEmail")
	@ResponseBody
	public ResultBean addMonitorConfigureEmail(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String email = request.getParameter("email");
		String emailVerificationCode = request.getParameter("emailVerificationCode");
		
		if(StringUtils.isNotEmpty(email)){
			if(StringUtils.isNotEmpty(emailVerificationCode)){
				//验证码是否正确
				String cachedVerificationCode = authService.getCachedCode(email);
				
				if(StringUtils.isNotEmpty(cachedVerificationCode)){
					if(cachedVerificationCode.equals(emailVerificationCode)){
						String accountId = getAccountIdFromSession(request);
						
						MonitorUserConfigure monitorUserConfigure = monitorUserConfigureService.getMonitorUserConfigureByAccountId(accountId);
						
						String emails = monitorUserConfigure.getEmails();
						String notifyType = monitorUserConfigure.getNotifyType();
						
						String[] emailArray = null;
						
						boolean alreadyExists = false;
						
						if(StringUtils.isNotEmpty(emails)){
							emailArray = emails.split(",");
							
							for(int i=0;i<emailArray.length;i++){
								if(email.equals(emailArray[i])){
									alreadyExists = true;
								}
							}
						}
						
						if(alreadyExists){
							result.setResultCode("1");
				  			result.setResultMsg("邮箱已存在，请勿重复添加！");
						}else{
							if(StringUtils.isNotEmpty(emails)){
								emails += "," + email;
							}else{
								emails = email;
							}
							
							notifyType += "0";
							
							monitorUserConfigure.setEmails(emails);
							monitorUserConfigure.setNotifyType(notifyType);
							
							result = monitorUserConfigureService.updateMonitorUserConfigure(monitorUserConfigure);
							
							//添加完成后，将验证码置为无效
							authService.clearCachedCode(email);
						}
					}else{
						result.setResultCode("1");
			  			result.setResultMsg("验证码有误，请核实！");
					}
				}else{
					result.setResultCode("1");
		  			result.setResultMsg("验证码失效，请重新获取！");
				}
			}else{
				result.setResultCode("1");
				result.setResultMsg("验证码不能为空！");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("邮件地址不能为空！");
		}
		
		return result;
	}
	
	/** 
	* @Title: deleteMonitorConfigureEmail 
	* @Description: 删除动态监控邮件
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月4日 下午1:51:40
	*/
	@RequestMapping("/deleteMonitorConfigureEmail")
	@ResponseBody
	public ResultBean deleteMonitorConfigureEmail(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String email = request.getParameter("email");
		
		if(StringUtils.isNotEmpty(email)){
			String accountId = getAccountIdFromSession(request);
			
			MonitorUserConfigure monitorUserConfigure = monitorUserConfigureService.getMonitorUserConfigureByAccountId(accountId);
			
			String[] emails = monitorUserConfigure.getEmails().split(",");
			String notifyType = monitorUserConfigure.getNotifyType();
			
			StringBuffer newEmails = new StringBuffer("");
			StringBuffer newNotifyType = new StringBuffer(notifyType.substring(0, 1));
			
			for(int i=0;i<emails.length;i++){
				if(!email.equals(emails[i])){
					newEmails.append(emails[i] + ",");
					
					newNotifyType.append(notifyType.substring(i+1, i+2));
				}
			}
			
			if(newEmails.length() > 0){
				monitorUserConfigure.setEmails(newEmails.substring(0, newEmails.length()-1));
			}else{
				monitorUserConfigure.setEmails("");
			}
			
			monitorUserConfigure.setNotifyType(newNotifyType.toString());
			
			result = monitorUserConfigureService.updateMonitorUserConfigure(monitorUserConfigure);
		}else{
			result.setResultCode("1");
			result.setResultMsg("邮件地址不能为空！");
		}
		
		return result;
	}
	
}
