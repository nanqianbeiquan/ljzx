/**   
* @Title: MonitorCompanyRiskAnalysisController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:27:43 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.service.monitor.MonitorCompanyRiskAnalysisService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyRiskAnalysisService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonRiskAnalysisService;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorCompanyRiskAnalysisController
 * @Description: 动态监控企业风险分析Controller
 * @author shiyong
 * @date 2017年5月12日 下午2:27:43
 *  
 */
@Controller
@RequestMapping("/monitorCompanyRiskAnalysis")
public class MonitorCompanyRiskAnalysisController {

	@Autowired
	private MonitorCompanyRiskAnalysisService monitorCompanyRiskAnalysisService;
	
	@Autowired
	private MonitorRelationCompanyRiskAnalysisService monitorRelationCompanyRiskAnalysisService;
	
	@Autowired
	private MonitorRelationPersonRiskAnalysisService monitorRelationPersonRiskAnalysisService;
	
	/** 
	* @Title: getMonitorCompanyRiskAnalysis 
	* @Description: 获取监控企业风险分析
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月12日 下午2:36:11
	*/
	@RequestMapping("/getMonitorCompanyRiskAnalysis")
	@ResponseBody
	public ResultBean getMonitorCompanyRiskAnalysis(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		
		String monitorId = request.getParameter("monitorId").trim();
		
		if(StringUtils.isNotEmpty(monitorId)){
			Map<String, Object> riskAnalysisMap = new HashMap<String, Object>();
			
			Map<String, Object> monitorCompanyRiskAnalysisMap = monitorCompanyRiskAnalysisService.getMonitorCompanyRiskAnalysis(monitorId);
			
			List<Map<String, Object>> monitorRelationCompanyRiskAnalysisList = monitorRelationCompanyRiskAnalysisService.getMonitorRelationCompanyRiskAnalysisList(monitorId);
			
			List<Map<String, Object>> monitorRelationPersonRiskAnalysisList = monitorRelationPersonRiskAnalysisService.getMonitorRelationPersonRiskAnalysisList(monitorId);
			
			riskAnalysisMap.put("monitorCompanyRiskAnalysis", monitorCompanyRiskAnalysisMap);
			riskAnalysisMap.put("monitorRelationCompanyRiskAnalysis", monitorRelationCompanyRiskAnalysisList);
			riskAnalysisMap.put("monitorRelationPersonRiskAnalysis", monitorRelationPersonRiskAnalysisList);
			
			result.setResultCode("0");
			result.setResultMsg("获取监控企业风险分析成功！");
			result.setResultData(riskAnalysisMap);
		}else{
			result.setResultCode("1");
			result.setResultMsg("参数错误， 获取监控企业风险分析失败！");
		}
		
		return result;
	}
	
	
	
	
}
