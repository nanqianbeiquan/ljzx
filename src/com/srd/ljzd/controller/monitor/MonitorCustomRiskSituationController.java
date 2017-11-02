/**   
* @Title: MonitorCustomRiskSituationController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年4月17日 下午4:58:23 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskInfo;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskSituation;
import com.srd.ljzd.exception.BusinessException;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorCustomRiskSituationService;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.LoggerUtils;

/** 
 * @ClassName: MonitorCustomRiskSituationController
 * @Description: 客户自定义风险状况控制器
 * @author shiyong
 * @date 2017年4月17日 下午4:58:23
 *  
 */
@Controller
@RequestMapping("/monitorCustomRiskSituation")
public class MonitorCustomRiskSituationController {
	@Autowired
	MonitorCompanyService monitorCompanyService;
	@Autowired
	MonitorCustomRiskSituationService monitorCustomRiskSituationService;
	
	/** 
	* @Description: 获取最近一次客户自定义风险状况
	*/
	@RequestMapping("/getLastCustomRisk")
	@ResponseBody
	public ResultBean getLastMonitorCustomRiskSituation(String monitorId,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		List<MonitorCustomRiskSituation> list = monitorCustomRiskSituationService.getLCustomRiskList(monitorId);
		if(list!=null&&list.size()>0){
			MonitorCustomRiskSituation record = list.get(0);
			if(record!=null&&record.getMonitorCompany()!=null
					&&"1".equals(record.getMonitorCompany().getCustomRiskFlag().trim())){
				result.setResultData(record);
				result.setResultCode("0");
				result.setResultMsg("SUCCESS");
			}else{
				result.setResultCode("1");
				result.setResultMsg("FAILED");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("FAILED");
		}
		return result;
	}
	
	/** 
	* @Description: 获取客户自定义风险状况
	*/
	@RequestMapping("/getCustomRisk")
	@ResponseBody
	public ResultBean getMonitorCustomRiskSituation(String customRiskId,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		if(StringUtils.isEmpty(customRiskId)){
			result.setResultCode("3");
			result.setResultMsg("PARAM_REQUIRED");
			return result;
		}
		MonitorCustomRiskSituation record = monitorCustomRiskSituationService.getById(customRiskId);
		if(record!=null){
			if(!"1".equals(record.getDeleteFlag())){//
				result.setResultData(record);
				result.setResultCode("0");
				result.setResultMsg("SUCCESS");
			}else{//已删除
				result.setResultCode("2");
				result.setResultMsg("ALREADY_DELETE");
			}
			
		}else{
			result.setResultCode("1");
			result.setResultMsg("NOT_EXIST");
		}
		return result;
	}
	
	/** 
	* @Description: 删除客户自定义风险状况
	*/
	@RequestMapping("/deleteCustomRisk")
	@ResponseBody
	public ResultBean deleteMonitorCustomRiskSituation(String customRiskId,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		MonitorCustomRiskSituation record = monitorCustomRiskSituationService.getById(customRiskId);
		if(record!=null){
			if(!"1".equals(record.getDeleteFlag())){//
				
				boolean flag = monitorCustomRiskSituationService.deleteCustomRisk(record);
				
				if(flag){
					result.setResultCode("0");
					result.setResultMsg("SUCCESS");
				}else{
					result.setResultCode("3");
					result.setResultMsg("MAY_BE_DB_EXCEPTION");
				}
			}else{
				result.setResultCode("2");
				result.setResultMsg("ALREADY_DELETE");
			}
		}else{
			result.setResultCode("1");
			result.setResultMsg("NOT_EXIST");
		}
		return result;
	}
	
	/** 
	* @Description: 添加客户自定义风险状况
	* @param  oldCustomRiskId 原自定义风险ID,如果为空，说明是第一次自定义，可以直接新增数据；不为空则要验证是否做了修改
	* @param  systemRiskId 系统风险Id,monitorCompanyRisk 主键
	* @param  monitorId monitorCompany主键
	* @param  riskSituation 风险状况
	*/
	@RequestMapping("/addCustomRisk")
	@ResponseBody
	public ResultBean addMonitorCustomRiskSituation(String oldCustomRiskId, String monitorId,String riskSituation,
			String riskInfoSet, HttpServletRequest request, HttpServletResponse response){
		
		List<MonitorCustomRiskInfo> tempList = JSONObject.parseArray(riskInfoSet,MonitorCustomRiskInfo.class);
		Set<MonitorCustomRiskInfo> targetRiskInfoSet = new HashSet<MonitorCustomRiskInfo>();
		targetRiskInfoSet.addAll(tempList);
		tempList.clear();
		ResultBean result = new ResultBean();
		
		MonitorCompany company = monitorCompanyService.queryMonitorCompanyById(monitorId);
		
		if(company==null){
			throw new BusinessException(ErrorCode.ER_UNKNOWN_EXCEPTION, "addMonitorCustomRiskSituation,未获取到监控企业信息(monitorCompany),param[monitorId="+monitorId+"]", 
					null, this.getClass());
		}
		
		if(StringUtils.isEmpty(oldCustomRiskId)){//第一次自定义风险
			result = monitorCustomRiskSituationService.addCustomRisk(riskSituation,company.getRiskSituation(),company,targetRiskInfoSet);
		}else{
			MonitorCustomRiskSituation oldCustomRisk = monitorCustomRiskSituationService.getById(oldCustomRiskId);
			
			if(oldCustomRisk==null){
				 throw new BusinessException(ErrorCode.ER_UNKNOWN_EXCEPTION, "addMonitorCustomRiskSituation,未获取到自定义风险信息(MonitorCustomRiskSituation),param[oldCustomRiskId="+oldCustomRiskId+"]", 
							null, this.getClass());
			}
			
			//验证是否做了修改
			boolean isModified = false;
			
			if(!isModified&&!oldCustomRisk.getRiskSituation().equals(riskSituation)){
				isModified = true;
			}
			
			if(!isModified&&targetRiskInfoSet.size()!=oldCustomRisk.getRiskInfoSet().size()){
				isModified = true;
			}
			
			if(!isModified&&!targetRiskInfoSet.containsAll(oldCustomRisk.getRiskInfoSet())){
				isModified = true;
			}
			
			if(isModified){
				result = monitorCustomRiskSituationService.addCustomRisk(riskSituation,oldCustomRisk.getRiskSituation(),company,targetRiskInfoSet);
			}else{
				result.setResultCode("1");
				result.setResultMsg("NOT_MODIFY_ANYTHING");
				return result;
			}
		}
		
		return result;
	}
	
	
	
	
	/**
	* @Description: 编辑客户自定义风险状况
	*/
	@RequestMapping("/updateCustomRisk")
	@ResponseBody
	public ResultBean updateMonitorCustomRiskSituation(String oldCustomRiskId,String riskSituation,
			String riskInfoSet,HttpServletRequest request, HttpServletResponse response){
		
		List<MonitorCustomRiskInfo> tempList = JSONObject.parseArray(riskInfoSet,MonitorCustomRiskInfo.class);
		Set<MonitorCustomRiskInfo> targetRiskInfoSet = new HashSet<MonitorCustomRiskInfo>();
		targetRiskInfoSet.addAll(tempList);
		tempList.clear();
		
		ResultBean result = new ResultBean();
		MonitorCustomRiskSituation customRisk = monitorCustomRiskSituationService.getById(oldCustomRiskId);
		if(customRisk==null){
			LoggerUtils.error("updateCustomRisk,未获取到自定义风险信息(MonitorCustomRiskSituation),param[oldCustomRiskId="+oldCustomRiskId+"]");
			result.setResultCode("1");
			result.setResultMsg("NOT_EXSIT");
			return result;
		}else{
			result = monitorCustomRiskSituationService.update(customRisk,riskSituation,targetRiskInfoSet);
		}
		return result;
	}
	
	/** 
	* @Description: 获取客户历史自定义风险状况列表
	*/
	@RequestMapping("/getHistoryMonitorCustomRiskSituationList")
	@ResponseBody
	public ResultBean getHistoryMonitorCustomRiskSituationList(String monitorId,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		List<MonitorCustomRiskSituation> list = monitorCustomRiskSituationService.getLCustomRiskList(monitorId);
		
		MonitorCompany monitorCompany = monitorCompanyService.queryMonitorCompanyById(monitorId);
		
		if(list.size() > 0){
			if("1".equals(monitorCompany.getCustomRiskFlag())){
				list.remove(0);
			}
		}
		
		result.setResultCode("0");
		result.setResultMsg("SUCCESS");
		result.setResultData(list);
		return result;
	}
	
	/** 
	* @Description: 清楚客户自定义风险状况
	*/
	@RequestMapping("/cleanCustomRisk")
	@ResponseBody
	public ResultBean cleanMonitorCustomRiskSituation(String monitorId,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = null;
		MonitorCompany company = monitorCompanyService.queryMonitorCompanyById(monitorId);
		result = monitorCustomRiskSituationService.clearCustomRisk(company);
		
		return result;
	}
	
}
