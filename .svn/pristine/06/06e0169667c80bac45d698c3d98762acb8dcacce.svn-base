/**   
* @Title: MonitorCompanyController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月18日 下午4:51:03 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.dto.monitor.MonitorCompanyDTO;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.company.CompanyUsedNameService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorCompanyController
 * @Description: 动态监控企业Controller
 * @author shiyong
 * @date 2016年11月18日 下午4:51:03
 *  
 */
@Controller
@RequestMapping("/monitorCompany")
public class MonitorCompanyController extends BaseController{
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	
	@Autowired
	private MonitorCompanyEventService monitorCompanyEventService;
	
	@Autowired
	private BizService bizService;
	
	@Autowired
	private MonitorRelationCompanyService monitorRelationCompanyService;
	
	@Autowired
	private MonitorRelationPersonService monitorRelationPersonService;
	
	@Autowired
	private CompanyUsedNameService companyUsedNameService;
	@Autowired
	private ClientAccountService clientAccountService;
	@Autowired
	private OperationLogService operationLogService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** 
	* @Title: toMonitorCompanyList 
	* @Description: 打开监控企业列表页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月28日 上午9:25:47
	*/
	@RequestMapping("/toMonitorCompanyList")
	public String toMonitorCompanyList(HttpServletRequest request, HttpServletResponse response, Model model){
		
		int currentPageNo = 1;
		
		if(StringUtils.isNotEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		
		int pageSize = 10;
		
		if(StringUtils.isNotEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		//组名 
		String groupName = request.getParameter("groupName");
		String reportId = request.getParameter("reportId");
		//更新状态有无事件 默认是"" 0:无更新事件 1:有更新事件
		String updateStatus = request.getParameter("updateStatus");
		//风险状况 
		String riskLevel = request.getParameter("riskLevel");
		//事件等级
		String eventLevel = request.getParameter("eventLevel");
		//省份
		String province = request.getParameter("province");
		String beginDate = request.getParameter("beginDate");
		String dueDate = request.getParameter("dueDate");
		String flag = request.getParameter("flag");
		String move_index = request.getParameter("move_index");
		String steps = request.getParameter("steps");
		String companyName = request.getParameter("companyName");
		String extendFlag = request.getParameter("extendFlag");
		String groupStatus = request.getParameter("groupStatus");
		String key = request.getParameter("key");
		String label = request.getParameter("label");
		String area = request.getParameter("area");
		String riskStatus = request.getParameter("riskStatus");
		String deep = request.getParameter("deep");
		String customRiskFlag = request.getParameter("customRiskFlag");
		String todayMonitorFlag = request.getParameter("todayMonitorFlag");
		String showGroupType = request.getParameter("showGroupType");
		String currentAccountId = request.getParameter("currentAccountId");
		
		//登录账户
		ClientAccount loginAccount = (ClientAccount) request.getSession().getAttribute("account");
		
		if(null == loginAccount){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		try {
			if(companyName!=null&&!"".equals(companyName)){
				companyName = URLDecoder.decode(companyName, "UTF-8");
			}
			if(groupName!=null&&!"".equals(groupName)){
				groupName = URLDecoder.decode(groupName, "UTF-8");
			}
			if(label!=null&&!"".equals(label)){
				label = URLDecoder.decode(label, "UTF-8");
			}
			if(area!=null&&!"".equals(area)){
				area = URLDecoder.decode(area, "UTF-8");
			}
			if(province!=null&&!"".equals(province)){
				province = URLDecoder.decode(province, "UTF-8");
			}
			
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		//是否展示操作权限的按钮
		boolean showActionBtnFlag = loginAccount.getAccountId().equals(currentAccountId)
				                   && !"ACCOUNT".equals(showGroupType);
		model.addAttribute("showActionBtnFlag",showActionBtnFlag);
		
		model.addAttribute("groupName",groupName);
		model.addAttribute("reportId",reportId);
		model.addAttribute("updateStatus",updateStatus);
		model.addAttribute("riskStatus",riskStatus);
		model.addAttribute("riskLevel",riskLevel);
		model.addAttribute("eventLevel",eventLevel);
		model.addAttribute("currentPageNo",currentPageNo);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("companyName",companyName);
		model.addAttribute("beginDate",beginDate);
		model.addAttribute("dueDate",dueDate);
		model.addAttribute("flag",flag);
		model.addAttribute("move_index",move_index);
		model.addAttribute("steps",steps);
		model.addAttribute("key",key);
		model.addAttribute("label",label);
		model.addAttribute("area",area);
		model.addAttribute("extendFlag",extendFlag);
		model.addAttribute("groupStatus",groupStatus);
		model.addAttribute("showGroupType",showGroupType);
		model.addAttribute("currentAccountId",currentAccountId);
		
		StringBuffer provinceBuf = null;
		
		if(!StringUtils.isEmpty(province)){
			provinceBuf = new StringBuffer();
			String[] pros = province.split(",");
			if(pros!=null){
				for(int i=0;i<pros.length;i++){
					
					province = pros[i];
					if(!StringUtils.isEmpty(province)){
						if("安徽,福建,甘肃,广东,贵州,海南,河北,河南,黑龙江,湖北,湖南,吉林,江苏,江西,辽宁,青海,山东,山西,陕西,四川,云南,浙江,重庆".contains(province)){
							provinceBuf.append(province).append("省");
						}else if("北京,上海,天津,,".contains(province)){
							provinceBuf.append(province).append("市");
						}else if("广西".equals(province)){
							provinceBuf.append(province).append("壮族自治区");
						}else if("内蒙古".equals(province)){
							provinceBuf.append(province).append("自治区");
						}else if("宁夏".equals(province)){
							provinceBuf.append(province).append("回族自治区");
						}else if("西藏".equals(province)){
							provinceBuf.append(province).append("自治区");
						}else if("新疆".equals(province)){
							provinceBuf.append(province).append("维吾尔自治区");
						}else{
							provinceBuf.append(province);
						}
					}
				}
			}
		}
		
		if(provinceBuf!=null){
			province= provinceBuf.toString();
		}
		
		model.addAttribute("province", province);
		model.addAttribute("deep", deep);
		model.addAttribute("todayMonitorFlag", todayMonitorFlag);
		model.addAttribute("customRiskFlag", customRiskFlag);
		
		return "/modules/monitor/monitorCompany/monitorCompanyList";
	}
	
	/** 
	* @Title: getMonitorCompanyPagination 
	* @Description: 获取监控企业分页
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月27日 上午10:24:58
	*/
	@RequestMapping("/getMonitorCompanyPagination")
	public String getMonitorCompanyPagination(String sortAttr,boolean isDesc,String showGroupType,String currentAccountId,HttpServletRequest request, HttpServletResponse response, Model model){
		int currentPageNo = 1;
		
		if(!StringUtils.isEmpty(request.getParameter("currentPageNo"))){
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		
		int pageSize = 10;
		
		if(!StringUtils.isEmpty(request.getParameter("pageSize"))){
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		//登录账户
		ClientAccount loginAccount = (ClientAccount) request.getSession().getAttribute("account");
		//切换到的账户
		ClientAccount currentAccount = clientAccountService.getClientAccountInfoById(currentAccountId);
		
		if(null == loginAccount){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		//String accountId = loginAccount.getAccountId();
	
		String companyName=request.getParameter("companyName");
		String groupName=request.getParameter("groupName");
		String riskLevel=request.getParameter("riskLevel");
		String riskStatus=request.getParameter("riskStatus");
		String groupStatus=request.getParameter("groupStatus");
		String eventLevel=request.getParameter("eventLevel");
		String area=request.getParameter("area");
		String label=request.getParameter("label");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("dueDate");
		String updateStatus = request.getParameter("updateStatus");
		String customRiskFlag = request.getParameter("customRiskFlag");
		String todayMonitorFlag = request.getParameter("todayMonitorFlag");
		
		try {
			if(companyName!=null&&!"".equals(companyName)){
				companyName = URLDecoder.decode(companyName, "UTF-8");
			}
			
			if(groupName!=null&&!"".equals(groupName)){
				groupName = URLDecoder.decode(groupName, "UTF-8");
			}
			
			if(label!=null&&!"".equals(label)){
				label = URLDecoder.decode(label, "UTF-8");
			}
			
			if(area!=null&&!"".equals(area)){
				area = URLDecoder.decode(area, "UTF-8");
			}
		}catch(UnsupportedEncodingException e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		List<String> labelList=new ArrayList<String>();
		
		if(!StringUtils.isEmpty(label)){
			String[] comArr=label.split(",");
			labelList=Arrays.asList(comArr);
		}
		
		List<String> areaList=new ArrayList<String>();
		
		if(!StringUtils.isEmpty(area)){
			String[] comArr=area.split(",");
			areaList=Arrays.asList(comArr);
		}
		//构造选择的账户列表
		List<String> selectedAccountList = null;
		if(!StringUtils.isEmpty(showGroupType)){
			if("ACCOUNT".equals(showGroupType)){
				selectedAccountList = new ArrayList<String>();
				ClientAccount groupAccount = null;
				selectedAccountList = new ArrayList<String>();
				if("全部".equals(groupName)){
					groupAccount = currentAccount;
					
					selectedAccountList.add(groupAccount.getAccountId());
					List<ClientAccount> childAccountList = clientAccountService.getAllClientAccountListByParentId(groupAccount.getAccountId());
					if(childAccountList!=null&&childAccountList.size()>0){
						for(ClientAccount acc : childAccountList){
							selectedAccountList.add(acc.getAccountId());
						}
					}
					
				}else{
					groupAccount = clientAccountService.getAccountByName(groupName);
					selectedAccountList.add(groupAccount.getAccountId());
					if(!groupAccount.getAccountId().equals(currentAccount.getAccountId())){//点的是非本部账户
						List<ClientAccount> childAccountList = clientAccountService.getAllClientAccountListByParentId(groupAccount.getAccountId());
						if(childAccountList!=null&&childAccountList.size()>0){
							for(ClientAccount acc : childAccountList){
								selectedAccountList.add(acc.getAccountId());
							}
						}
					}
				}
				
			}
		}
		
		MonitorCompanyDTO monitorCompanyDTO = new MonitorCompanyDTO();
		monitorCompanyDTO.setBeginDate(beginDate);
		monitorCompanyDTO.setEndDate(endDate);
		monitorCompanyDTO.setRiskSituation(riskLevel);
		monitorCompanyDTO.setRiskStatus(riskStatus);
		monitorCompanyDTO.setEventLevel(eventLevel);
		monitorCompanyDTO.setClientAccount(currentAccount);
		monitorCompanyDTO.setGroupName(groupName);
		monitorCompanyDTO.setCompanyName(companyName);
		monitorCompanyDTO.setAreaList(areaList);
		monitorCompanyDTO.setLabelList(labelList);
		monitorCompanyDTO.setAreaName(area);
		monitorCompanyDTO.setRiskLabel(label);
		monitorCompanyDTO.setGroupStatus(groupStatus);
		monitorCompanyDTO.setNewEventFlag(updateStatus);
		monitorCompanyDTO.setCustomRiskFlag(customRiskFlag);
		monitorCompanyDTO.setTodayMonitorFlag(todayMonitorFlag);
		monitorCompanyDTO.setAccountList(selectedAccountList);
		Page<MonitorCompany> page = monitorCompanyService.findMonitorCompanyPage(monitorCompanyDTO, currentPageNo, pageSize,sortAttr,isDesc);

		//增加关系企业和关系自然人数量
		Page<MonitorCompanyDTO> monitorCompanyPage = new Page<MonitorCompanyDTO>();
		monitorCompanyPage.setPrePageNo(page.getPrePageNo());
		monitorCompanyPage.setNextPageNo(page.getNextPageNo());
		monitorCompanyPage.setCurrentPageNo(page.getCurrentPageNo());
		monitorCompanyPage.setPageSize(page.getPageSize());
		monitorCompanyPage.setRecordSum(page.getRecordSum());
		monitorCompanyPage.setPageSum(page.getPageSum());
		monitorCompanyPage.setBeginIndex(page.getBeginIndex());
		monitorCompanyPage.setHasPrePage(page.isHasPrePage());
		monitorCompanyPage.setHasNextPage(page.isHasNextPage());
		
		List<MonitorCompanyDTO> monitorCompanyDTOList = new ArrayList<MonitorCompanyDTO>();
		
		Map<String, Integer> relationCompanyNumMap = monitorRelationCompanyService.getRelationCompanyNum(page.getResults());
		
		Map<String, Integer> relationPersonNumMap = monitorRelationPersonService.getRelationPersonNum(page.getResults());
		
		for(MonitorCompany monitorCompany : page.getResults()){
			monitorCompanyDTO = new MonitorCompanyDTO();
			monitorCompanyDTO.setMonitorID(monitorCompany.getMonitorID());
			monitorCompanyDTO.setMonitorDate(monitorCompany.getMonitorDate());
			monitorCompanyDTO.setCustomRiskFlag(monitorCompany.getCustomRiskFlag());
			monitorCompanyDTO.setCompanyName(monitorCompany.getCompanyName());
			monitorCompanyDTO.setProvince(monitorCompany.getProvince());
			monitorCompanyDTO.setWarnEventNum(monitorCompany.getWarnEventNum());
			monitorCompanyDTO.setAttentionEventNum(monitorCompany.getAttentionEventNum());
			monitorCompanyDTO.setNormalEventNum(monitorCompany.getNormalEventNum());
			monitorCompanyDTO.setCustomRiskSituation(monitorCompany.getCustomRiskSituation());
			monitorCompanyDTO.setRiskStatus(monitorCompany.getRiskStatus());
			monitorCompanyDTO.setEventLevel(monitorCompany.getEventLevel());
			monitorCompanyDTO.setRiskLabel(monitorCompany.getRiskLabel());
			monitorCompanyDTO.setCustomRiskLabel(monitorCompany.getCustomRiskLabel());
			monitorCompanyDTO.setRelationCompanyNum(relationCompanyNumMap.get(monitorCompany.getMonitorID()));
			monitorCompanyDTO.setRelationPersonNum(relationPersonNumMap.get(monitorCompany.getMonitorID()));
			monitorCompanyDTO.setRenameStatus(monitorCompany.getRenameStatus());
			monitorCompanyDTO.setClientAccount(monitorCompany.getClientAccount());
			monitorCompanyDTOList.add(monitorCompanyDTO);
		}
		
		monitorCompanyPage.setResults(monitorCompanyDTOList);
		
		model.addAttribute("page", monitorCompanyPage);
		
		Calendar cal = Calendar.getInstance();
		
		String today = sdfDate.format(cal.getTime()) + " 00:00:00.0";
		
		model.addAttribute("today", today);
		
		if(StringUtils.isNotEmpty(sortAttr)){
			model.addAttribute(sortAttr+"IsDesc", !isDesc);
		}
		//是否展示操作权限的按钮
		boolean showActionBtnFlag = loginAccount.getAccountId().equals(currentAccountId)
				                   && !"ACCOUNT".equals(showGroupType);
		model.addAttribute("showActionBtnFlag",showActionBtnFlag);
				
		return "/modules/monitor/monitorCompany/monitorCompanyPagination";
	}
	
	/** 
	* @Title: viewMonitorCompanyDetail 
	* @Description: 查看监控企业详情
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月23日 下午1:38:29
	*/
	@RequestMapping("/viewMonitorCompanyDetail")
	public String viewMonitorCompanyDetail(HttpServletRequest request, HttpServletResponse response, Model model){
		String deep = request.getParameter("deep");
		String monitorId = request.getParameter("monitorId");
		
		//登录账户
		ClientAccount loginAccount = getClientAccountFromSession(request);
		
		//获取监控企业信息
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		
		if(loginAccount.getAccountId().equals(monitorCompany.getClientAccount().getAccountId())){
			model.addAttribute("showActionBtnFlag", true);
		}else{
			model.addAttribute("showActionBtnFlag", false);
		}
		
		//获取监控企业经营状态
		String entStatus = bizService.getEntStatus(monitorCompany.getCompanyName());
		
		if(StringUtils.isEmpty(entStatus)){
			entStatus = "";
		}
		
		model.addAttribute("entStatus", entStatus);
		model.addAttribute("monitorCompany", monitorCompany);
		model.addAttribute("deep", deep);
		
		//保存用户使用记录
		operationLogService.save(monitorCompany.getClientAccount().getAccountId(),monitorCompany.getClientAccount().getAccountName()
        		,"查看监控详情","企业名称 "+monitorCompany.getCompanyName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return "/modules/monitor/monitorCompany/monitorCompanyDetail";
	}
	
	/** 
	* @Title: getMonitorCompany 
	* @Description: 获取监控企业风险信息
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return MonitorCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月23日 下午3:46:38
	*/
	@RequestMapping("/getMonitorCompany")
	@ResponseBody
	public MonitorCompany getMonitorCompany(HttpServletRequest request, HttpServletResponse response) {

		String monitorId = request.getParameter("monitorId");
		
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		monitorCompany.setClientAccount(null);
		// 获取标签对标签进行加工处理
		String label = "";
		
		if(StringUtils.isNotEmpty(monitorCompany.getRiskLabel())){
			label = monitorCompany.getRiskLabel();
		}else{
			label = "无";
		}
		
		monitorCompany.setRiskLabel(label);
		
		monitorCompany.setRiskResult(monitorCompany.getCustomRiskResult());
		
		return monitorCompany;
	}
	
	/** 
	* @Title: addMonitorCompany 
	* @Description: 添加动态监控公司
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月18日 下午4:53:29
	*/
	@RequestMapping("/addMonitorCompany")
	@ResponseBody
	public ResultBean addMonitorCompany(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		String companyName = request.getParameter("companyName");
		String[] groupIds = request.getParameter("monitorGroup").split(",");
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		//判断是否已加入监控
		boolean isMonitor = monitorCompanyService.isMonitorOfCompany(account.getAccountId(),companyName);
		if(!isMonitor){
			//查询该公司注册信息
			CompanyInfo company = companyInfoService.buildCompanyInfoFromMultiChannel(companyName);
			
			if(company==null||company.getRegisterInfo()==null
					||company.getRegisterInfo().getEnterpriseName()==null
					||"".equals(company.getRegisterInfo().getEnterpriseName().trim())
					||"--".equals(company.getRegisterInfo().getEnterpriseName().trim())){
				
				result.setResultCode("1");
				result.setResultMsg("该企业不存在！");
			}else if(company.getRegisterInfo().getProvince()==null
					||"".equals(company.getRegisterInfo().getProvince().trim())
					||"--".equals(company.getRegisterInfo().getProvince().trim())){
				result.setResultCode("1");
				result.setResultMsg("没有查询到企业省份信息，不能添加！");
			}else if(!company.getRegisterInfo().getEnterpriseName().trim().equals(companyName)){
				
				//企业更名
				String newCompanyName = company.getRegisterInfo().getEnterpriseName().trim();
				StringBuffer msgBuf = new StringBuffer();
				msgBuf.append("该企业已更名为：")
				      .append(newCompanyName);
				//判断是否已加入监控
				isMonitor = monitorCompanyService.isMonitorOfCompany(account.getAccountId(),newCompanyName);
				if(isMonitor){
					msgBuf.append("，更名后企业加入失败，原因为：已经加入监控！");
				}else{
					ResultBean flag = monitorCompanyService.saveMonitorCompany(account.getAccountId(), company.getRegisterInfo().getCompanyId()
							,newCompanyName,company.getRegisterInfo().getProvince(),groupIds,"1");
					
					if("0".equals(flag.getResultCode())){
						//保存企业曾用名
						companyUsedNameService.saveCompanyUsedName(companyName);
						
						LoggerUtils.warn("import-in-search-曾用名导入，companyName="+companyName
								+",newCompanyName="+newCompanyName+",accountId="+account.getAccountId());
						msgBuf.append("，更名后企业已加入成功！监控详情将于次日展示");
						
					}else{
						msgBuf.append("，").append(flag.getResultMsg());
					}
				}
				result.setResultCode("2");
				result.setResultMsg(msgBuf.toString());
				companyName = newCompanyName;
			}else{
				result = monitorCompanyService.saveMonitorCompany(accountId, company.getRegisterInfo().getCompanyId(),companyName, company.getRegisterInfo().getProvince(), groupIds, "0");
			}
			if(result.getResultCode()!=null&&( "0".equals(result.getResultCode())
					                              || "2".equals(result.getResultCode() ) ) ){
				//保存用户使用记录
				operationLogService.save(account.getAccountId(),account.getAccountName()
		        		,"新增动态监控","新增方式 单个新增 企业名称 "+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("该企业已加入动态监控,不能重复添加!");
		}
		
		
		return result;
	}
	
	
	/** 
	* @Title: deleteMonitorCompany 
	* @Description: 取消动态监控公司
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 上午10:03:26
	*/
	@RequestMapping("/deleteMonitorCompany")
	@ResponseBody
	public ResultBean deleteMonitorCompany(HttpServletRequest request, HttpServletResponse response){
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		String companyName = request.getParameter("companyName");
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		
		MonitorCompany monitorCompany = new MonitorCompany();
		monitorCompany.setClientAccount(account);
		monitorCompany.setCompanyName(companyName);
		
		ResultBean result = monitorCompanyService.deleteMonitorCompany(monitorCompany);
		
		return result;
	}
	
	/** 
	* @Title: deleteMonitorCompanyBatch 
	* @Description: 批量删除监控企业
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午3:43:45
	*/
	@ResponseBody
	@RequestMapping("/deleteMonitorCompanyBatch")
	public ResultBean deleteMonitorCompanyBatch(HttpServletRequest request,HttpServletResponse response){
		String monitorIds = request.getParameter("monitorIds");
	
		List<String> monitorIdList = new ArrayList<String>();
		
		if(StringUtils.isNotEmpty(monitorIds)){
			String [] monitorIdArr = monitorIds.split(",");
			
			monitorIdList = Arrays.asList(monitorIdArr);
		}
		
		ResultBean result = monitorCompanyService.deleteMonitorCompanyBatch(monitorIdList);
		
		return result;
	}
	
	/** 
	* @Title: toTodayMonitorCompanyList 
	* @Description: 跳转到今日新增动态监控公司列表
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月9日 上午11:18:58
	*/
	@RequestMapping("/toTodayMonitorCompanyList")
	public String toTodayMonitorCompanyList(String deep, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("deep", deep);
		
		model.addAttribute("todayMonitorFlag", "1");
		
		return "/modules/monitor/monitorCompany/monitorCompanyList";
	}
	
	/** 
	* @Title: isMonitored 
	* @Description: 是否已监控
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月10日 下午5:19:06
	*/
	@RequestMapping("/isMonitorOfCompany")
	@ResponseBody
	public ResultBean isMonitorOfCompany(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String accountId = account.getAccountId();
		
		String companyName = request.getParameter("companyName");
		
		try {
			companyName = URLDecoder.decode(companyName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		
		MonitorCompany monitorCompany = new MonitorCompany();
		monitorCompany.setClientAccount(account);
		monitorCompany.setCompanyName(companyName);
		
		boolean isMonitored = monitorCompanyService.isMonitorOfCompany(monitorCompany);
		
		result.setResultCode("0");
		result.setResultData(isMonitored);
		
		return result;
	}
	
	/** 
	* @Title: getDistributionOfHighRiskCompany 
	* @Description: 获取特别预警公司区域分布
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return List<Map<String,Object>> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月26日 下午4:50:34
	*/
	@RequestMapping("/getDistributionOfHighRiskCompany")
	@ResponseBody
	public List<Map<String, Object>> getDistributionOfHighRiskCompany(HttpServletRequest request, HttpServletResponse response){
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("currentAccount");
		
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		String accountId = account.getAccountId();
		
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = monitorCompanyService.queryMonitorCompanyDistributionList(accountId);
		
		Map<String, Object> distributionMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> distributionList = new ArrayList<Map<String, Object>>();
		
		for (MonitorCompanyDistribution monitorCompanyDistribution : monitorCompanyDistributionList){
			distributionMap = new HashMap<String, Object>();
			
			distributionMap.put("name", monitorCompanyDistribution.getAreaName());
			distributionMap.put("total", monitorCompanyDistribution.getTotalCompanyNum());
			distributionMap.put("high", monitorCompanyDistribution.getHighRiskCompanyNum());
			distributionMap.put("value", monitorCompanyDistribution.getHighRiskCompanyPercent().intValue());
			
			distributionList.add(distributionMap);
		}
		
		return distributionList;
	}
	/** 
	* @Title: getDistributionOfHighRiskCompanyByParentId 
	* @Description: 获取父账号及其下一级子账号特别预警公司区域分布
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return List<Map<String,Object>> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月12日 下午2:08:58
	*/
	@RequestMapping("/getDistributionOfHighRiskCompanyByParent")
	@ResponseBody
	public List<Map<String, Object>> getDistributionOfHighRiskCompanyByParent(HttpServletRequest request, HttpServletResponse response){
		
		String accountId = getParameterFromRequest(request, "accountId");
		
		ClientAccount motherAccount = clientAccountService.getClientAccountInfoById(accountId);
		
		List<ClientAccount> accountList = clientAccountService.getAllClientAccountListByParentId(accountId);
		accountList.add(motherAccount);
		
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = monitorCompanyService.queryMonitorCompanyDistributionList(accountList);
		
		Map<String, Object> distributionMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> distributionList = new ArrayList<Map<String, Object>>();
		
		for (MonitorCompanyDistribution monitorCompanyDistribution : monitorCompanyDistributionList){
			distributionMap = new HashMap<String, Object>();
			
			distributionMap.put("name", monitorCompanyDistribution.getAreaName());
			distributionMap.put("total", monitorCompanyDistribution.getTotalCompanyNum());
			distributionMap.put("high", monitorCompanyDistribution.getHighRiskCompanyNum());
			distributionMap.put("value", monitorCompanyDistribution.getHighRiskCompanyPercent().intValue());
			
			distributionList.add(distributionMap);
		}
		
		return distributionList;
	}
	/** 
	* @Title: getMonitorComRiskEventNum 
	* @Description: 重新获取各分类未读事件的数量
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return Object 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月23日 下午2:39:59
	*/
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/getMonitorComRiskEventNum")
	public Object getMonitorComRiskEventNum(HttpServletRequest request, HttpServletResponse response) {
		
		String monitorId = request.getParameter("monitorId");
		
		String accountId = getAccountIdFromSession(request);
		
		//获取监控企业信息
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		
		List category = monitorCompanyEventService.queryAllEventCategory(monitorCompany.getCompanyName(),monitorCompany.getInfoShowDate());
		
		Map<String,Object> categoryMap = monitorCompanyEventService.queryUnReadEventCategoryNumList(accountId, monitorCompany.getMonitorID(), monitorCompany.getCompanyName(),monitorCompany.getInfoShowDate());
		
		if(category!=null&&category.size()>0){
			for(Object cate : category){
				if(!categoryMap.containsKey((String)cate)){
					categoryMap.put((String)cate, 0);//0表示有数据，1表示有未读数
				}
			}
		}
		
		//过滤掉没有开发的维度
		List<String> unDevelopCateList = Arrays.asList("06","10","27","28");
		
		for(String cate : unDevelopCateList){
			if(categoryMap.containsKey(cate)){
				categoryMap.remove(cate);
			}
		}
		
		return categoryMap;
	}
	
}
