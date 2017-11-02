/**   
* @Title: MonitorRelationCompanyRiskAnalysisService.java 
* @Package com.srd.ljzd.service.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:20:02 
* @version V1.0   
*/
package com.srd.ljzd.service.monitor;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: MonitorRelationCompanyRiskAnalysisService
 * @Description: 动态监控关系企业风险分析Service
 * @author shiyong
 * @date 2017年5月12日 下午2:20:02
 *  
 */
public interface MonitorRelationCompanyRiskAnalysisService {
	/** 
	* @Title: getMonitorRelationCompanyRiskAnalysisList 
	* @Description: 获取关系企业风险分析列表
	* @param @param monitorId
	* @param @return 设定文件 
	* @return List<Map<String,Object>> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月12日 下午5:19:32
	*/
	public List<Map<String, Object>> getMonitorRelationCompanyRiskAnalysisList(String monitorId);
}
