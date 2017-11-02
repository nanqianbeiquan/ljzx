/**   
* @Title: MonitorRelationCompanyRiskAnalysisDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:02:49 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorRelationCompanyRiskAnalysisDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorRelationCompanyRiskAnalysis;

/** 
 * @ClassName: MonitorRelationCompanyRiskAnalysisDaoImpl
 * @Description: 动态监控关系企业风险分析DAO实现类
 * @author shiyong
 * @date 2017年5月12日 下午2:02:49
 *  
 */
@Repository
public class MonitorRelationCompanyRiskAnalysisDaoImpl extends BaseDaoImpl<MonitorRelationCompanyRiskAnalysis, String> implements MonitorRelationCompanyRiskAnalysisDao{

	@Override
	public List<MonitorRelationCompanyRiskAnalysis> queryMonitorRelationCompanyRiskAnalysisListByMonitorId(
			String relationCompanyMonitorId) {
		
		String hql = "from MonitorRelationCompanyRiskAnalysis where relationCompanyMonitorId = '" + relationCompanyMonitorId + "'";
		
		return getListByHql(hql);
		
	}
	
}
