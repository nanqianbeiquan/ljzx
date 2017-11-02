/**   
* @Title: CompanyInfoController.java 
* @Package com.srd.ljzd.controller.company 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月16日 下午5:50:02 
* @version V1.0   
*/
package com.srd.ljzd.controller.company;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.company.PopulorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.company.PopulorCompanyService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.StringUtils;


/** 
 * @ClassName: CompanyInfoController
 * @Description: 公司信息Controller
 * @author shiyong
 * @date 2016年11月16日 下午5:50:02
 *  
 */
@Controller
@RequestMapping("/companyInfo")
public class CompanyInfoController {
	
	@Autowired
	private PopulorCompanyService populorCompanyService;
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	@Autowired
	private OperationLogService operationLogService;
	
	/** 
	* @Title: toCompanyInfoList 
	* @Description: 跳转到公司列表页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月16日 下午6:59:41
	*/
	@RequestMapping("/toCompanyInfoList")
	public String toCompanyInfoList(String deep,HttpServletRequest request, HttpServletResponse response, Model model){
		
		String keyword = "";
		
		try {
			keyword = URLDecoder.decode(request.getParameter("keyword"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error("根据关键字搜索企业出错，关键字：" + request.getParameter("keyword"), e);
		}
		
		model.addAttribute("keyword", keyword);
		
		List<PopulorCompany> populorCompanyList = populorCompanyService.queryRecentPopulorCompanyList(6);
		
		model.addAttribute("populorCompanyList", populorCompanyList);
		
		model.addAttribute("deep", deep);
		
		return "/modules/company/companyList";
	}
	
	/** 
	* @Title: queryCompanyInfoList 
	* @Description: 根据关键字通过接口搜索公司
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月17日 下午2:09:56
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping("/queryCompanyInfoList")
	@ResponseBody
	public JSONObject queryCompanyInfoList(HttpServletRequest request, HttpServletResponse response){
		String keyword = request.getParameter("keyword");
		int currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		Map<String, Object> result = companyInfoService.queryCompanyInfoListUnion(keyword, 60);
		
		List<CompanyInfo> companyInfoList = (List<CompanyInfo>) result.get("companyInfoList");
		
		int companyInfoNum = companyInfoList.size();
		
		result.put("companyInfoNum", companyInfoNum);
		
		if(companyInfoNum > 0){
			List<CompanyInfo> tempList = null;
			
			if(companyInfoNum < currentPageNo*pageSize){
				tempList = companyInfoList.subList((currentPageNo-1)*pageSize, companyInfoNum);
			}else{
				tempList = companyInfoList.subList((currentPageNo-1)*pageSize, currentPageNo*pageSize);
			}
			
			//查询企业是否已监控
			for(CompanyInfo companyInfo : tempList){
				MonitorCompany monitorCompany = new MonitorCompany();
				
				ClientAccount clientAccount = new ClientAccount();
				clientAccount.setAccountId(accountId);
				
				monitorCompany.setClientAccount(clientAccount);
				monitorCompany.setCompanyName(companyInfo.getRegisterInfo().getEnterpriseName());
				
				monitorCompany = monitorCompanyService.getMonitorCompany(accountId, companyInfo.getRegisterInfo().getEnterpriseName());
				
				if(null != monitorCompany && StringUtils.isNotEmpty(monitorCompany.getMonitorID())){
					companyInfo.setMonitorId(monitorCompany.getMonitorID());
					companyInfo.setMonitorDate(monitorCompany.getMonitorDate());
					companyInfo.setMonitorFlag("1");
				}else{
					companyInfo.setMonitorFlag("0");
				}
			}
			
			result.put("companyInfoList", tempList);
		}else{
			result.put("companyInfoList", companyInfoList);
		}
		
		//保存用户使用记录
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"搜索企业","企业名称 "+keyword,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return (JSONObject)JSONObject.toJSON(result);
	}
	
	/** 
	* @Title: queryCompanyInfoListFromCache 
	* @Description: 从缓存中获取搜索企业列表
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午6:17:38
	*/
	@RequestMapping("/queryCompanyInfoListFromCache")
	@ResponseBody
	public JSONObject queryCompanyInfoListFromCache(HttpServletRequest request, HttpServletResponse response){
		
		String keyword = request.getParameter("keyword");
		int currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		
		List<CompanyInfo> companyInfoList = companyInfoService.getCompanyInfoListFromCache(keyword);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		int companyInfoNum = companyInfoList.size();
		
		List<CompanyInfo> tempList = null;
		
		if(companyInfoNum < currentPageNo*pageSize){
			tempList = companyInfoList.subList((currentPageNo-1)*pageSize, companyInfoNum);
		}else{
			tempList = companyInfoList.subList((currentPageNo-1)*pageSize, currentPageNo*pageSize);
		}
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		//查询企业是否已监控
		for(CompanyInfo companyInfo : tempList){
			MonitorCompany monitorCompany = new MonitorCompany();
			
			ClientAccount clientAccount = new ClientAccount();
			clientAccount.setAccountId(accountId);
			
			monitorCompany.setClientAccount(clientAccount);
			monitorCompany.setCompanyName(companyInfo.getRegisterInfo().getEnterpriseName());
			
			monitorCompany = monitorCompanyService.getMonitorCompany(accountId, companyInfo.getRegisterInfo().getEnterpriseName());
			
			if(null != monitorCompany && StringUtils.isNotEmpty(monitorCompany.getMonitorID())){
				companyInfo.setMonitorId(monitorCompany.getMonitorID());
				companyInfo.setMonitorDate(monitorCompany.getMonitorDate());
				companyInfo.setMonitorFlag("1");
			}else{
				companyInfo.setMonitorFlag("0");
			}
		}
		
		result.put("companyInfoList", tempList);
		
		return (JSONObject)JSONObject.toJSON(result);
	}
	
	/** 
	* @Title: viewCompanyRegisterInfo 
	* @Description: 查看公司基本信息
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 上午9:33:49
	*/
	@RequestMapping("/viewCompanyRegisterInfo")
	@ResponseBody
	public ResultBean viewCompanyRegisterInfo(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String companyName = request.getParameter("companyName");
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		CompanyInfo companyInfo = companyInfoService.getCompanyRegisterInfoQiChaCha(companyName);
		
		if(companyInfo.getRegisterInfo() != null){
			result.setResultCode("0");
			result.setResultMsg("获取公司详情成功！");
			result.setResultData(companyInfo);
		}else{
			result.setResultCode("1");
			result.setResultMsg("获取公司详情失败！");
		}
		
		return result;
	}
	
	@RequestMapping("/getUsedName")
	@ResponseBody
	public Object getUsedName(String companyName,HttpServletRequest request, HttpServletResponse response){
		
		JSONArray target = companyInfoService.getUsedName(companyName);
		
		return target==null?"[]":target;
	}
	
	
	
}
