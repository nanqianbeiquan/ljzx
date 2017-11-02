package com.srd.ljzd.service.monitor;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.entity.monitor.MonitorCompanyFinanceSituation;

public interface MonitorCompanyFinanceService {

	public Map<String,Object> getSummaryAnalysisData(JSONObject source);
	
	public Map<String,Object> getEachItemAnalysisData(JSONObject source);
	
	public Map<String,Object> buildSummaryAnalysis(Map<String,Object> data);
	
	public Map<String,Object> buildEachItemAnalysis(Map<String,Object> data);
	
	/** 
	* @Title: getCompanyFinanceSituationByCompanyId 
	* @Description: 根据企业id获取企业财务状况
	* @param @param companyId
	* @param @return 设定文件 
	* @return MonitorCompanyFinanceSituation 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月27日 下午2:00:17
	*/
	public MonitorCompanyFinanceSituation getCompanyFinanceSituationByCompanyId(String companyId);
}
