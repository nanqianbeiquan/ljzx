/**   
* @Title: LogController.java 
* @Package com.srd.ljzd.controller.log 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年9月17日 上午8:23:53 
* @version V1.0   
*/
package com.srd.ljzd.controller.log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: LogController
 * @Description: 操作日志控制器
 * @author shiyong
 * @date 2017年9月17日 上午8:23:53
 *  
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController{
    private static final String ALL_ACTION_TYPE="新增动态监控,取消动态监控,新增失信记录,删除失信记录,新增风险自定义,修改风险自定义,删除风险自定义,新增账户,修改账户,删除账户,禁用账户,启用账户";
	@Autowired
	ClientAccountService clientAccountService;
	@Autowired
	OperationLogService operationLogService;
	/** 
	* @Title: toLogList 
	* @Description: 打开操作日志查询页面
	*/
	@RequestMapping("/toLogListView")
	public String toLogList(HttpServletRequest request, HttpServletResponse response, Model model){
		
		ClientAccount loginAccount = super.getClientAccountFromSession(request);
		
		List<ClientAccount> accList = clientAccountService.getChildAccountListByParentId(loginAccount.getAccountId());
		
		if(accList==null){
			accList = new ArrayList<ClientAccount>();
		}
		accList.add(0, loginAccount);
		
		model.addAttribute("logAccountList", accList);
		if(loginAccount.getClientCompany()!=null){
			model.addAttribute("clientCompanyName", loginAccount.getClientCompany().getCompanyName());
		}
		model.addAttribute("currentDateStr", DateUtils.getCurrentDateStr());
		//获取第一页
		StringBuffer accBuff = new StringBuffer();
		for(int i =0;i<accList.size()-1;i++){
			accBuff.append(accList.get(i).getAccountId()).append(",");
		}
		accBuff.append(accList.get(accList.size()-1).getAccountId());
		
		Calendar c = Calendar.getInstance();  
		String fromDate = DateUtils.getLocalStrNew(DateUtils.formatPattern, c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
        String toDate = DateUtils.getLocalStrNew(DateUtils.formatPattern, c.getTime());
		Page<Map<String,Object>> target = operationLogService.getLog(accBuff.toString(),ALL_ACTION_TYPE,fromDate,toDate,1,20);
		
		model.addAttribute("logListPage", target);
		
		
		return "/modules/log/log_list";
	}
	
	@RequestMapping("/getLogList")
	public String getLogList(String fromDate,String toDate,String accountId,String actionType,Integer pageNo,Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model){
		
		if(StringUtils.isEmpty(accountId)||"全部".equals(accountId)){
			ClientAccount loginAccount = super.getClientAccountFromSession(request);
			
			List<ClientAccount> accList = clientAccountService.getChildAccountListByParentId(loginAccount.getAccountId());
			
			if(accList==null){
				accList = new ArrayList<ClientAccount>();
			}
			accList.add(0, loginAccount);
			StringBuffer accBuff = new StringBuffer();
			for(int i =0;i<accList.size()-1;i++){
				accBuff.append(accList.get(i).getAccountId()).append(",");
			}
			accBuff.append(accList.get(accList.size()-1).getAccountId());
			accountId = accBuff.toString();
		}
		if(StringUtils.isEmpty(actionType)||"全部".equals(actionType)){
			actionType = ALL_ACTION_TYPE;
		}
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize = 20;
		}
		Date tmpDate = DateUtils.getLocalDate(toDate);
		Calendar c = Calendar.getInstance();  
        c.setTime(tmpDate);  
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
		toDate = DateUtils.getLocalStrNew(DateUtils.formatPattern, c.getTime());
		Page<Map<String,Object>> target = operationLogService.getLog(accountId,actionType,fromDate,toDate,pageNo,pageSize);
		
		model.addAttribute("logListPage", target);
		return "/modules/log/log_list_page";
	}
}
