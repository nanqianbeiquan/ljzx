/**   
* @Title: MonitorCompanyRiskAnalysisDao.java 
* @Package com.srd.ljzd.dao.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午1:57:27 
* @version V1.0   
*/
package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyRiskAnalysis;

/** 
 * @ClassName: MonitorCompanyRiskAnalysisDao
 * @Description: 动态监控企业风险分析Dao
 * @author shiyong
 * @date 2017年5月12日 下午1:57:27
 *  
 */
public interface MonitorCompanyRiskAnalysisDao extends BaseDao<MonitorCompanyRiskAnalysis,String> {

	/** 
	* @Title: queryMonitorCompanyRiskAnalysisListByMonitorId 
	* @Description: 根据监控企业id获取企业风险分析
	* @param @param monitorId
	* @param @return 设定文件 
	* @return List<MonitorCompanyRiskAnalysis> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月12日 下午4:09:15
	*/
	public List<MonitorCompanyRiskAnalysis> queryMonitorCompanyRiskAnalysisListByMonitorId(String monitorId);
	
}
