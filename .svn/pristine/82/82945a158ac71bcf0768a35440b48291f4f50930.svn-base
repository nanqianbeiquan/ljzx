/**   
* @Title: MonitorCompanyEventController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月31日 下午2:57:45 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.monitor.MonitorCompanyEventStateService;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;

/** 
 * @ClassName: MonitorCompanyEventController
 * @Description: 企业事件控制器
 * @author shiyong
 * @date 2017年5月31日 下午2:57:45
 *  
 */
@Controller
@RequestMapping("/monitorCompanyEvent")
public class MonitorCompanyEventController extends BaseController {
	
	@Autowired
	private MonitorCompanyEventStateService monitorCompanyEventStateService;
	
	/** 
	* @Title: ignoreAllUnreadEventBySubType 
	* @Description: 根据事件列表将所有未读事件设为已读
	* @param @param monitorId
	* @param @param companyName
	* @param @param infoShowDate
	* @param @param eventSubType
	* @param @param model
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return Object 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午3:05:48
	*/
	@RequestMapping("/ignoreAllUnreadEventBySubType")
	@ResponseBody
	public Object ignoreAllUnreadEventBySubType(String monitorId,String companyName,String infoShowDate,String eventSubType,Model model,HttpServletRequest request,HttpServletResponse response){
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		
		ClientAccount account = getClientAccountFromSession(request);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int count = 0;
		
		if(account!=null&&account.getAccountId()!=null){
			count = monitorCompanyEventStateService.updateAllUnreadEventStatus(monitorId, companyName, infoShowDate, eventSubType, account.getAccountId());
		}
		
		resultMap.put("count", count);
		
		return resultMap;
	}
}
