package com.srd.ljzd.controller.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.dto.monitor.MonitorCompanyDTO;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.company.CompanyInfo;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.client.ClientAccountService;
import com.srd.ljzd.service.company.CompanyInfoService;
import com.srd.ljzd.service.company.CompanyUsedNameService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.DownloadReportService;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupService;
import com.srd.ljzd.util.Constant;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;
import com.srd.ljzd.util.StringUtils4Dev;
import com.srd.ljzd.util.excel.ExportExcel;
import com.srd.ljzd.util.excel.ImportExcel;
/**
 * 处理监控公司的批量导入导出
 * @author vinta
 *
 */
@Controller
@RequestMapping("/ie")
public class ImportOrExportController extends BaseController{
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private DownloadReportService downloadReportService;
	
	@Autowired
	private MonitorGroupService monitorGroupService;
	
	@Autowired
	private CompanyUsedNameService companyUsedNameService;
	
	@Autowired
	private ClientAccountService clientAccountService;
	@Autowired
	private OperationLogService operationLogService;
	
	@Autowired
	RedisTemplate<String, Map<String,Object>> redisTemplate;
	
	protected static Logger log = LogManager.getLogger(ImportOrExportController.class.getName());
	
	@RequestMapping("/importBatch.do")
	@ResponseBody
	public Object importBatch(MultipartFile fileInput,String selectedGroupName, HttpServletRequest request){
		ResultBean result = new ResultBean();
    	int successNum = 0;
    	int failureNum = 0;
    	
    	List<String> successCompanyNameList = new ArrayList<String>();
    	Map<String,Object> failureCompanyMap = new HashMap<String,Object>();
		//获取当前用户
    	ClientAccount account =(ClientAccount)request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		
		try {
			ImportExcel ei = new ImportExcel(fileInput, 1, 0);
			
			MonitorGroup defaultGroup = monitorGroupService.getMonitorGroup(account.getAccountId(), Constant.DEFAULT_GROUP_NAME);
			MonitorGroup selectedGroup = null;
			String[] groupIds = null;
			
            if(selectedGroupName!=null&&!Constant.DEFAULT_GROUP_NAME.equals(selectedGroupName)){
	    		if(selectedGroupName.startsWith(",")){
	    			selectedGroupName = selectedGroupName.substring(1, selectedGroupName.length());
	    		}
	    		if(!"".equals(selectedGroupName)){
    				selectedGroup = monitorGroupService.getMonitorGroup(account.getAccountId(), selectedGroupName);
    			}
			}
			if(selectedGroup!=null){
				groupIds = new String[]{defaultGroup.getGroupId(),selectedGroup.getGroupId()};
			}else{
				groupIds = new String[]{defaultGroup.getGroupId()};
			}
			String companyName = null;
			for(int i=1;i<=ei.getLastDataRowNum();i++){
				Row row = ei.getRow(i);
				companyName = ei.getCellValue(row, 0).toString().trim();
				if(!StringUtils.isEmpty(companyName)){
					if(!isCompanyName(companyName)){
						failureCompanyMap.put(companyName, "非公司名称");
						failureNum++;
						continue;
					}
					//公司名称中包含半角的小括号，则替换为全角的小括号
					if(companyName.contains("(")||companyName.contains(")")){
						if(companyName.contains("(")){
							companyName = companyName.replaceAll("\\(", "（");
						}
						if(companyName.contains(")")){
							companyName = companyName.replaceAll("\\)", "）");
						}
					}
					
					//超过100行的数据，不处理
					if(i > 100){
						failureCompanyMap.put(companyName, "超过本次导入数量上限");
						failureNum++;
					}else{
						//判断是否已加入监控
						boolean isMonitor = monitorCompanyService.isMonitorOfCompany(account.getAccountId(),companyName);
						
						if(isMonitor){
							failureCompanyMap.put(companyName, "已经加入监控");
							failureNum++;
							continue;
						}
						
						//判断本次导入中是否已添加该公司
						for (String successCompanyName : successCompanyNameList) {
							if(companyName.equals(successCompanyName)){
								isMonitor = true;
								break;
							}
						}
						
						if(isMonitor){
							failureCompanyMap.put(companyName, "本次重复导入");
							failureNum++;
							continue;
						}
						
						//查询该公司注册信息
						CompanyInfo company = companyInfoService.buildCompanyInfoFromMultiChannel(companyName);
						
						if(company==null||company.getRegisterInfo()==null
								||company.getRegisterInfo().getEnterpriseName()==null
								||"".equals(company.getRegisterInfo().getEnterpriseName().trim())
								||"--".equals(company.getRegisterInfo().getEnterpriseName().trim())){
							failureCompanyMap.put(companyName, "该企业不存在!");
							failureNum++;
							continue;
						}
						if(company.getRegisterInfo().getProvince()==null
								||"".equals(company.getRegisterInfo().getProvince().trim())
								||"--".equals(company.getRegisterInfo().getProvince().trim())){
							failureCompanyMap.put(companyName, "该企业缺少省份信息!");
							failureNum++;
							continue;
						}
						if(!company.getRegisterInfo().getEnterpriseName().trim().equals(companyName)){
							//企业更名
							String newCompanyName = company.getRegisterInfo().getEnterpriseName().trim();
							StringBuffer msgBuf = new StringBuffer();
							msgBuf.append("该企业已更名为：")
							      .append(newCompanyName);
							//判断是否已加入监控
							isMonitor = monitorCompanyService.isMonitorOfCompany(account.getAccountId(),newCompanyName);
							if(isMonitor){
								msgBuf.append("，更名后企业加入失败，原因为：已经加入监控！");
								failureCompanyMap.put(companyName, msgBuf.toString());
								failureNum++;
							}else{
								//判断本次导入中是否已添加该公司
								for (String successCompanyName : successCompanyNameList) {
									if(newCompanyName.equals(successCompanyName)){
										isMonitor = true;
										break;
									}
								}
								if(isMonitor){
									msgBuf.append("，更名后企业加入失败，原因为：本次重复导入！");
									failureCompanyMap.put(companyName, msgBuf.toString());
									failureNum++;
								}else{
									ResultBean flag = monitorCompanyService.saveMonitorCompany(account.getAccountId(), company.getRegisterInfo().getCompanyId()
											,newCompanyName,company.getRegisterInfo().getProvince(),groupIds,"1");
									
									if("0".equals(flag.getResultCode())){
										//保存企业曾用名
										companyUsedNameService.saveCompanyUsedName(companyName);
										
										LoggerUtils.warn("import-in-batch-曾用名导入，companyName="+companyName
												+",newCompanyName="+newCompanyName+",accountId="+account.getAccountId());
										successCompanyNameList.add(newCompanyName);
										successNum++;
										msgBuf.append("，更名后企业已加入成功！");
										failureCompanyMap.put(companyName, msgBuf.toString());
										failureNum++;
										//保存用户使用记录
										operationLogService.save(account.getAccountId(),account.getAccountName()
								        		,"新增动态监控","新增方式 批量新增 企业名称 "+newCompanyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
									}else{
										msgBuf.append("，").append(flag.getResultMsg());
										failureCompanyMap.put(companyName, msgBuf.toString());
										failureNum++;
									}
								}
							}
							continue;
						}
						//加入动态监控
						ResultBean flag = monitorCompanyService.saveMonitorCompany(account.getAccountId(), company.getRegisterInfo().getCompanyId(),companyName,company.getRegisterInfo().getProvince(),groupIds,"0");
						
						if("0".equals(flag.getResultCode())){
							successCompanyNameList.add(companyName);
							successNum++;
							//保存用户使用记录
							operationLogService.save(account.getAccountId(),account.getAccountName()
					        		,"新增动态监控","新增方式 批量新增 企业名称 "+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
						}else{
							failureCompanyMap.put(companyName, flag.getResultMsg());
							failureNum++;
						}
					}
				}
			}
			
			result.setResultCode("0");
			result.setResultMsg("导入完成！");
		} catch (Exception e) {
			result.setResultCode("1");
			result.setResultMsg("站点异常，动态监控公司批量导入中断！");
			
			LoggerUtils.error("动态监控公司批量导入中断，中断原因：" + e.getMessage(), e);
		}
		
		ResultBean canMonitorResult = clientAccountService.canMonitorCompany(account.getAccountId());
//		
//		canMonitorResult = new ResultBean();
//		canMonitorResult.setResultCode("1");
//		canMonitorResult.setResultMsg("加入动态监控家数已达99家（当前加入主体及关系企业合计）；请合理安排，必要时申请充值！");
		
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("successNum", successNum);
		resultData.put("failureNum", failureNum);
		resultData.put("failureCompany", failureCompanyMap);
		resultData.put("canMonitorResult", canMonitorResult);
		result.setResultData(resultData);
		
		if(failureCompanyMap.size()>0){
			redisTemplate.delete(account.getAccountId()+"failureCompany");
			redisTemplate.opsForValue().set(account.getAccountId()+"failureCompany", failureCompanyMap, 30, TimeUnit.MINUTES);//半小时
		}
		
		return result;
	}
	
	@RequestMapping("/exportFailedCompany.do")
	public void exportFailedCompany(HttpServletRequest request, HttpServletResponse response){
		
		Map<String,Object> result = new HashMap<String, Object>();
		//获取当前用户
		ClientAccount account =(ClientAccount)request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		Map<String,Object> failureCompanyMap = (Map<String,Object>)redisTemplate.opsForValue().get(account.getAccountId()+"failureCompany");
	
		try {
            String fileName = "批量导入_失败公司列表_" + DateUtils.getCurrentDateStr(DateUtils.AllStr_PATTERN) + ".xlsx";
            String[] headers = {"公司名称","失败原因"};
            ExportExcel exportExcel = new ExportExcel("批量导入-失败公司列表", headers);
            
            for(Map.Entry<String,Object> entry : failureCompanyMap.entrySet()){
            	Row row = exportExcel.addRow();
            	int column = 0;
            	exportExcel.addCell(row, column++, entry.getKey());
            	exportExcel.addCell(row, column++, entry.getValue());
            	
            }
            
            exportExcel.write(response, fileName);
    		exportExcel.dispose();
    		redisTemplate.delete(account.getAccountId()+"failureCompany");
    		result.put("status", "0");
		} catch (Exception e) {
			log.error("导出失败公司列表失败！失败信息："+e.getMessage(), e);
			result.put("status", "1");
		}
		
	}
	
	@RequestMapping("/exportMonitorCompanys.do")
	public void exportMonitorCompanys(String sortAttr,boolean isDesc,String showGroupType,String currentAccountId,
		String groupName,String companyName,String riskLevel,String riskStatus,
			String eventLevel,String area,String label,String beginDate,String dueDate,String updateStatus,String groupStatus,
			String customRiskFlag,String todayMonitorFlag,HttpServletRequest request,HttpServletResponse response){

	        //登录账户
	  	ClientAccount loginAccount = (ClientAccount) request.getSession().getAttribute("account");
	  	//切换到的账户
	  	ClientAccount currentAccount = clientAccountService.getClientAccountInfoById(currentAccountId);
	  	if(null == loginAccount){
	  	     throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
	  	}
	  	try {
			if(!StringUtils.isEmpty(companyName)){
			   companyName = URLDecoder.decode(companyName, "UTF-8");
			}
			if(!StringUtils.isEmpty(groupName)){
				groupName = URLDecoder.decode(groupName, "UTF-8");
			}
			if(!StringUtils.isEmpty(area)){
				area = URLDecoder.decode(area, "UTF-8");
			}
			if(!StringUtils.isEmpty(label)){
				label = URLDecoder.decode(label, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
	        List<String> labelList=new ArrayList<String>();
		
		if(!StringUtils.isEmpty(label)){
			String [] comArr=label.split(",");
			labelList=Arrays.asList(comArr);
		}
		
		List<String> areaList=new ArrayList<String>();
		
		if(!StringUtils.isEmpty(area)){
			String [] comArr=area.split(",");
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
		monitorCompanyDTO.setEndDate(dueDate);
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
		
		Page<MonitorCompany> page = monitorCompanyService.findMonitorCompanyPage(monitorCompanyDTO, 1, Integer.MAX_VALUE,sortAttr,isDesc);
		
		List<MonitorCompany> monitorCompanyList = page.getResults();
		try {
            String fileName = "动态监控公司列表_" + DateUtils.getCurrentDateStr(DateUtils.AllStr_PATTERN) + ".xlsx";
            String[] headers = {"序号","目标公司","账户","地区","开始监控时间","事件(严重/异常/一般)","风险状况","风险变化","风险事件","企业风险标签"};
            ExportExcel exportExcel = new ExportExcel("动态监控公司列表", headers);
            if(monitorCompanyList != null && monitorCompanyList.size()>0){
            	MonitorCompany company = null;
            	
            	String lable = "";
            	String riskSituation = "";
            	riskStatus = "";
            	eventLevel = "";
            	String accountName = "";
	            for (int i=0;i<monitorCompanyList.size();i++) {
	            	company = monitorCompanyList.get(i);
	            	
	            	Row row = exportExcel.addRow();
	            	int column = 0;
	            	exportExcel.addCell(row, column++, (i+1));//序号
	            	exportExcel.addCell(row, column++, company.getCompanyName());//公司名称
	            	if(company.getClientAccount()!=null){
	            	    accountName = company.getClientAccount().getAccountName();
	            	}else{
	            	    accountName = "";
	            	}
	            	exportExcel.addCell(row, column++, accountName);//账户
	            	exportExcel.addCell(row, column++, company.getAreaName());//地区
	            	exportExcel.addCell(row, column++, DateUtils.getLocalStr(DateUtils.formatPattern, company.getMonitorDate()));//时间
	            	exportExcel.addCell(row, column++, company.getWarnEventNum()+"/"+company.getAttentionEventNum()+"/"+company.getNormalEventNum());//事件数量
	            	
	            	if("3".equals(company.getCustomRiskSituation())){
	            		riskSituation = "特别预警";
	            	}else if("2".equals(company.getCustomRiskSituation())){
	            		riskSituation = "一般预警";
	            	}else if("1".equals(company.getCustomRiskSituation())){
	            		riskSituation = "关注";
	            	}else if("0".equals(company.getCustomRiskSituation())){
	            		riskSituation = "正常";
	            	}else{
	            		riskSituation = "--";
	            	}
	            	
	            	exportExcel.addCell(row, column++, riskSituation);
	            	
	            	if("1".equals(company.getRiskStatus())){
	            		riskStatus = "升高";
	            	}else if("2".equals(company.getRiskStatus())){
	            		riskStatus = "降低";
	            	}else if("0".equals(company.getRiskStatus())){
	            		riskStatus = "不变";
	            	}else{
	            		riskStatus = "--";
	            	}
	            	
	            	exportExcel.addCell(row, column++, riskStatus);
	            	
	            	if("3".equals(company.getEventLevel())){
	            		eventLevel = "严重";
	            	}else if("2".equals(company.getEventLevel())){
	            		eventLevel = "异常";
	            	}else if("1".equals(company.getEventLevel())){
	            		eventLevel = "一般";
	            	}else{
	            		eventLevel = "--";
	            	}
	            	
	            	exportExcel.addCell(row, column++, eventLevel);
	            	
	            	if(StringUtils.isNotEmpty(company.getRiskLabel()) && StringUtils.isNotEmpty(company.getCustomRiskLabel())){
	            		lable = company.getRiskLabel() + "," + company.getCustomRiskLabel();
	            	}else if(StringUtils.isNotEmpty(company.getRiskLabel()) && StringUtils.isEmpty(company.getCustomRiskLabel())){
	            		lable = company.getRiskLabel();
	            	}else if(StringUtils.isEmpty(company.getRiskLabel()) && StringUtils.isNotEmpty(company.getCustomRiskLabel())){
	            		lable = company.getCustomRiskLabel();
	            	}else{
	            		lable = "";
	            	}
	            	
	            	exportExcel.addCell(row, column++, lable);//企业风险标签
	            }
            }
            exportExcel.write(response, fileName);
    		exportExcel.dispose();
		} catch (Exception e) {
			LoggerUtils.error("导出动态监控公司列表失败！失败信息："+e.getMessage(), e);
		}
		
	}
	
	//http://localhost:8080/ljzd/ie/exportCompanyMonitorReport.do?reportId=1000&companyName=上海亿瞻健康科技有限公司
	@RequestMapping("/exportCompanyMonitorReport.do")
	public void exportCompanyMonitorReport(String monitorId, HttpServletRequest request,HttpServletResponse response){
		
		String templatePath =new StringBuffer(request.getServletContext().getRealPath(""))
		   .append("excelTemplate").append(File.separator).append("非现场监测报告.xlsx").toString();   //这个是excel模板  
		
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		//保存用户使用记录
    	ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"下载报告","非现场监测报告 企业名称 "+monitorCompany.getCompanyName(),DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		//MonitorCompanyRisk monitorCompanyRisk = monitorCompanyRiskService.getMonitorCompanyRiskByMonitorId(monitorId);
		
		/**String lable = "";
		
    	if(StringUtils.isNotEmpty(monitorCompany.getRiskLabel()) && StringUtils.isNotEmpty(monitorCompany.getCustomRiskLabel())){
    		lable = monitorCompany.getRiskLabel() + "," + monitorCompany.getCustomRiskLabel();
    	}else if(StringUtils.isNotEmpty(monitorCompany.getRiskLabel()) && StringUtils.isEmpty(monitorCompany.getCustomRiskLabel())){
    		lable = monitorCompany.getRiskLabel();
    	}else if(StringUtils.isEmpty(monitorCompany.getRiskLabel()) && StringUtils.isNotEmpty(monitorCompany.getCustomRiskLabel())){
    		lable = monitorCompany.getCustomRiskLabel();
    	}else{
    		lable = "";
    	}
    	
		monitorCompanyRisk.setLabel(lable);
		monitorCompanyRisk.setRiskGrade(monitorCompany.getCustomRiskSituation());
		monitorCompanyRisk.setRiskStatus(monitorCompany.getRiskStatus());
		monitorCompanyRisk.setRiskResult(monitorCompany.getCustomRiskResult());
		**/
		//查询该公司注册信息
		CompanyInfo company = companyInfoService.getCompanyRegisterInfo(monitorCompany.getCompanyName());
		
		Map<String,Object> data= downloadReportService.buildReportData(monitorCompany,company);
		
		XSSFWorkbook wb = downloadReportService.buildReport(templatePath, data);
		//downloadReportService.testPrintTemplate(request, templatePath);
        try {
        	String targetFileName = "非现场监测报告_"+monitorCompany.getCompanyName()+"_"+DateUtils.getCurrentDateStr(DateUtils.AllStr_PATTERN) + ".xlsx";
    		response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(targetFileName, "UTF-8"));
			wb.write(response.getOutputStream());
			
        } catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("/downloadTemplate.do")
	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response){
		String templatePath =new StringBuffer(request.getServletContext().getRealPath(""))
		   .append("excelTemplate").append(File.separator).append("动态监控公司导入模板.xlsx").toString();   //这个是excel模板  
		
		XSSFWorkbook work =null;
		InputStream in = null;
		
        try {
            in = new FileInputStream(new File(templatePath));  
			
			work = new XSSFWorkbook(in); 
        	
        	String targetFileName = "动态监控公司导入模板.xlsx";
    		response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(targetFileName, "UTF-8"));
			work.write(response.getOutputStream());
			
        } catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				in.close();
				work.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean isCompanyName(String companyName){
		if(companyName==null||"".equals(companyName.trim())){
			return false;
		}
		companyName = companyName.trim();
		/**
		 * 1. 姓名是全数字的时候，不予加入动态监控
         * 2. 企业名称少于4个字的时候，不予加入动态监控 
         * 3. “公司名称” 不予加入动态监控  
		 */
		if(StringUtils4Dev.isNumeric(companyName)||companyName.length()<4
				|| "公司名称".equals(companyName)
				|| "《公司名称》".equals(companyName) 
				|| "<公司名称>".equals(companyName) ){
			return false;
		}
		
		return true;
	}
}
