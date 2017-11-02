/**   
* @Title: MonitorRelationPersonRiskAnalysisDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:03:05 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorRelationPersonRiskAnalysisDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorRelationPersonRiskAnalysis;

/** 
 * @ClassName: MonitorRelationPersonRiskAnalysisDaoImpl
 * @Description: 动态监控关系个人风险分析Dao实现类
 * @author shiyong
 * @date 2017年5月12日 下午2:03:05
 *  
 */
@Repository
public class MonitorRelationPersonRiskAnalysisDaoImpl extends BaseDaoImpl<MonitorRelationPersonRiskAnalysis, String> implements MonitorRelationPersonRiskAnalysisDao{

	@Override
	public List<MonitorRelationPersonRiskAnalysis> queryMonitorRelationPersonRiskAnalysisListByMonitorId(
			String relationPersonMonitorId) {
		String hql = "from MonitorRelationPersonRiskAnalysis where relationPersonMonitorId = '" + relationPersonMonitorId + "'";
		
		return getListByHql(hql);
	}

}
