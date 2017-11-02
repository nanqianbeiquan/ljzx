/**   
* @Title: MonitorRelationPersonRiskAnalysisDao.java 
* @Package com.srd.ljzd.dao.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午1:59:27 
* @version V1.0   
*/
package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorRelationPersonRiskAnalysis;

/** 
 * @ClassName: MonitorRelationPersonRiskAnalysisDao
 * @Description: 动态监控关系个人风险分析Dao
 * @author shiyong
 * @date 2017年5月12日 下午1:59:27
 *  
 */
public interface MonitorRelationPersonRiskAnalysisDao extends BaseDao<MonitorRelationPersonRiskAnalysis,String>{
	
	/** 
	* @Title: queryMonitorRelationPersonRiskAnalysisListByMonitorId 
	* @Description: 根据关系个人id获取关系个人风险分析
	* @param @param relationPersonMonitorId
	* @param @return 设定文件 
	* @return List<MonitorRelationPersonRiskAnalysis> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月15日 下午12:32:13
	*/
	public List<MonitorRelationPersonRiskAnalysis> queryMonitorRelationPersonRiskAnalysisListByMonitorId(String relationPersonMonitorId);
}
