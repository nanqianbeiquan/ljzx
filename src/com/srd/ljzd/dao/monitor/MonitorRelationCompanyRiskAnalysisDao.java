/**   
* @Title: MonitorRelationCompanyRiskAnalysisDao.java 
* @Package com.srd.ljzd.dao.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:01:37 
* @version V1.0   
*/
package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorRelationCompanyRiskAnalysis;

/** 
 * @ClassName: MonitorRelationCompanyRiskAnalysisDao
 * @Description: 动态监控关系企业风险分析Dao
 * @author shiyong
 * @date 2017年5月12日 下午2:01:37
 *  
 */
public interface MonitorRelationCompanyRiskAnalysisDao extends BaseDao<MonitorRelationCompanyRiskAnalysis,String>{
	
	/** 
	* @Title: queryMonitorRelationCompanyRiskAnalysisListByMonitorId 
	* @Description: 根据关系企业id获取关系企业风险分析
	* @param @param relationCompanyMonitorId
	* @param @return 设定文件 
	* @return List<MonitorRelationCompanyRiskAnalysis> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月15日 上午11:00:22
	*/
	public List<MonitorRelationCompanyRiskAnalysis> queryMonitorRelationCompanyRiskAnalysisListByMonitorId(String relationCompanyMonitorId);
}
