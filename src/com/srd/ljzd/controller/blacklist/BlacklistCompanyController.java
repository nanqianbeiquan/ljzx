/**   
* @Title: BlacklistCompanyController.java 
* @Package com.srd.ljzd.controller.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:41:52 
* @version V1.0   
*/
package com.srd.ljzd.controller.blacklist;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.blacklist.BlacklistCompany;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.service.blacklist.BlacklistCompanyService;
import com.srd.ljzd.service.blacklist.BlacklistReasonService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.util.BlacklistReasonTypeEnum;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: BlacklistCompanyController
 * @Description: 黑名单企业Controller
 * @author shiyong
 * @date 2017年3月29日 下午4:41:52
 *  
 */
@Controller
@RequestMapping("/blacklistCompany")
public class BlacklistCompanyController extends BaseController{

	@Autowired
	BlacklistReasonService blacklistReasonService;
	@Autowired
	BlacklistCompanyService blacklistCompanyService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private OperationLogService operationLogService;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@RequestMapping("/toBlacklistView.do")
	public String toBlacklistView(String deep,HttpServletRequest request, HttpServletResponse response, Model model ){
		model.addAttribute("deep", deep);
		ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"查看失信记录","查看失信记录",DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		return "/modules/blacklist/blacklist";
	}
	@RequestMapping("/queryCompanyList.do")
	@ResponseBody
	public ResultBean queryCompanyList(String keyword,boolean fromCacheFlag,Integer pageNo,Integer pageSize,HttpServletRequest request){
		ResultBean rst = new  ResultBean();
		if(StringUtils.isEmpty(keyword)){
			rst.setResultCode("1");
			rst.setResultMsg("PARAM_KEYWORD_REQUIRED");
			return rst;
		}
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error("queryCompanyList.do,PARAM_KEYWORD_DECODE_FAILED,keyword="+keyword, e);
			rst.setResultCode("2");
			rst.setResultMsg("PARAM_KEYWORD_DECODE_FAILED");
			return rst;
			
		}
		if(pageNo==null||pageNo<0){
			pageNo = 1;
		}
		if(pageSize==null||pageSize<0){
			pageNo = Constant.DETAULT_PAGE_SIZE;
		}
		List<CompanyInfo> companyInfoList = null;
		
		if(fromCacheFlag){
			companyInfoList = companyInfoService.getCompanyInfoListFromCache(keyword);
		}
		if(companyInfoList==null){//
			//第一种情况是fromCacheFlag=false,第二种情况是没有从缓存中获取到
			Map<String, Object> result = companyInfoService.queryCompanyInfoListUnion(keyword, 60);
			companyInfoList = (List<CompanyInfo>) result.get("companyInfoList");
		}
		
		Page<Map<String,Object>> targetPage = companyInfoService.buildCompanyInfoPage(companyInfoList,pageNo,pageSize);
		rst.setResultCode("0");
		rst.setResultMsg("SUCCESS");
		rst.setResultData(targetPage);
		//保存用户使用记录
		ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"搜索失信信息","企业名称 "+keyword,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return rst;
		
	}
	/**
	 * 检查企业是否在黑名单中，
	 * 是否在系统黑名单中，
	 * 是否在用户自定义黑名单中
	 */
	@RequestMapping("/checkBlacklist.do")
	@ResponseBody
	public ResultBean checkBlacklist(String monitorId,String companyName,String cnt,HttpServletRequest request){
		ResultBean rst = new  ResultBean();
		rst.setResultCode("0");
		rst.setResultMsg("SUCCESS");
		if(StringUtils.isEmpty(companyName)){
			rst.setResultCode("1");
			rst.setResultMsg("PARAM_REQUIRED");
			return rst;
		}
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error("checkBlacklist.do,PARAM_DECODE_FAILED,companyName="+companyName, e);
			rst.setResultCode("2");
			rst.setResultMsg("PARAM_DECODE_FAILED");
			return rst;
		}
		
		ClientAccount loginAccount = (ClientAccount)request.getSession().getAttribute("account");
		if(loginAccount==null){
			rst.setResultCode("3");
			rst.setResultMsg("NEED_LOGIN");
			return rst;
		}
		
		String accountId = null;
		
		if(StringUtils.isEmpty(monitorId)){
		    accountId = loginAccount.getAccountId();
		}else{
		    //获取监控企业信息
		    MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		    if(monitorCompany!=null&&monitorCompany.getClientAccount()!=null){
			accountId = monitorCompany.getClientAccount().getAccountId();
		    }
		}
		
		
		Map<String,Object> checkMap = blacklistCompanyService.checkSystemBlacklist(companyName,cnt);
		if(checkMap==null){
			checkMap = new HashMap<String, Object>();
			checkMap.put("systemBlacklistFlag", false);
			checkMap.put("message", "");
		}
		BlacklistCompany customBlackCompany = blacklistCompanyService.checkCustomBlacklist(accountId,companyName);
		if(customBlackCompany!=null){
			customBlackCompany.setAccount(null);
		}
		checkMap.put("customBlacklistFlag", customBlackCompany!=null?true:false);
		checkMap.put("customBlackCompany", customBlackCompany);
		rst.setResultData(checkMap);
		return rst;
	}
	/**
	 * 获取加入黑名单原因列表
	 */
	@RequestMapping("/getAddReasonList.do")
	@ResponseBody
	public Object getAddReasonList(HttpServletRequest request, HttpServletResponse response, Model model ){
		
		return blacklistReasonService.getReasonList(BlacklistReasonTypeEnum.ADD);
	}
	/**
	 * 获取移出黑名单原因列表
	 */
	@RequestMapping("/getRemoveReasonList.do")
	@ResponseBody
	public Object getRemoveReasonList(HttpServletRequest request, HttpServletResponse response, Model model ){
		
		return blacklistReasonService.getReasonList(BlacklistReasonTypeEnum.REMOVE);
	}
	/**
	 * 获取黑名单列表
	 */
	@RequestMapping("/applyBlackList.do")
	@ResponseBody
	public ResultBean applyBlackList(Integer pageNo,Integer pageSize,String selectedAccountId,HttpServletRequest request, HttpServletResponse response, Model model ){
		ResultBean rst = new  ResultBean();
		rst.setResultCode("0");
		rst.setResultMsg("SUCCESS");
		if(pageNo==null){
			pageNo = 1;
		}
		if(pageSize==null){
			pageSize = Constant.DETAULT_PAGE_SIZE;
		}
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			rst.setResultCode("1");
			rst.setResultMsg("NEED_LOGIN");
			return rst;
		}
		Page<Map<String,Object>> page = null;
		
		if(!StringUtils.isEmpty(selectedAccountId)){
			page = blacklistCompanyService.getBlacklistCompanyByPage(account.getAccountId(), pageNo, pageSize);
		}else{
			String companyId = null;
			if(account.getClientCompany()!=null){
				companyId = account.getClientCompany().getCompanyId();
			}
			if(!StringUtils.isEmpty(companyId)){
				page = blacklistCompanyService.getBlacklistCompanyByPageWithCompanyId(companyId, pageNo, pageSize);
				
			}else{
				
			}
		}
		
		rst.setResultData(page);
		return rst;
	}
	
	/**
	 * 加入黑名单
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public ResultBean add(String companyName,String reason,String otherReason,HttpServletRequest request, HttpServletResponse response, Model model ){
		ResultBean rst = new  ResultBean();
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			rst.setResultCode("1");
			rst.setResultMsg("NEED_LOGIN");
			return rst;
		}
		try {
			if(companyName!=null){
				companyName = URLDecoder.decode(companyName, "UTF-8");
			}
			if(otherReason!=null){
				otherReason = URLDecoder.decode(otherReason, "UTF-8");
			}
			
			Map<String,Object> result = blacklistCompanyService.addToBlacklist(account.getAccountId(), companyName, reason, otherReason);
			if(result!=null){
				
				if((boolean)result.get("isSuccess")){
					//保存用户使用记录
					operationLogService.save(account.getAccountId(),account.getAccountName()
			        		,"新增失信记录","企业名称："+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
				}
				rst.setResultCode((boolean)result.get("isSuccess")?"0":"4");
				rst.setResultMsg((String)result.get("msg"));
				rst.setResultData(result.get("data"));
			}else{
				rst.setResultCode("5");
				rst.setResultMsg("UNKNOWN_EXCEPTION");
			}
			
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error("add.do,PARAM_DECODE_FAILED,companyName="+companyName+",otherReason="+otherReason,e);
			rst.setResultCode("2");
			rst.setResultMsg("PARAM_DECODE_FAILED");
			return rst;
		}catch (Exception e) {
			LoggerUtils.error("添加黑名单异常，param=[companyName"+companyName+"]", e);
			rst.setResultCode("3");
			rst.setResultMsg("RUNTIME_EXCETION");
			return rst;
		}
		return rst;
	}
	/**
	 * 移出黑名单
	 */
	@RequestMapping("/remove.do")
	@ResponseBody
	public Object remove(String companyName,String reason,String otherReason,HttpServletRequest request, HttpServletResponse response, Model model ){
		ResultBean rst = new  ResultBean();
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null){
			rst.setResultCode("1");
			rst.setResultMsg("NEED_LOGIN");
			return rst;
		}
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
			otherReason = URLDecoder.decode(otherReason, "UTF-8");
			
			Map<String,Object> result = blacklistCompanyService.removeFromBlacklist(account.getAccountId(), companyName, reason, otherReason);
			if(result!=null){
				if((boolean)result.get("isSuccess")){
					//保存用户使用记录
					operationLogService.save(account.getAccountId(),account.getAccountName()
			        		,"删除失信记录","企业名称："+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
				}
				rst.setResultCode((boolean)result.get("isSuccess")?"0":"4");
				rst.setResultMsg((String)result.get("msg"));
				rst.setResultData(result.get("data"));
			}else{
				rst.setResultCode("3");
				rst.setResultMsg("UNKNOWN_EXCEPTION");
			}
			
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error("remove.do,PARAM_DECODE_FAILED,companyName="+companyName+",otherReason="+otherReason,e);
			rst.setResultCode("2");
			rst.setResultMsg("PARAM_DECODE_FAILED");
			return rst;
		}
		
		return rst;
	}
}
