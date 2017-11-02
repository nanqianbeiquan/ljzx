/**   
* @Title: MonitorCompanyRiskAnalysisDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月12日 下午2:03:25 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorCompanyRiskAnalysisDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompanyRiskAnalysis;

/** 
 * @ClassName: MonitorCompanyRiskAnalysisDaoImpl
 * @Description: 动态监控企业风险分析Dao实现类
 * @author shiyong
 * @date 2017年5月12日 下午2:03:25
 *  
 */
@Repository
public class MonitorCompanyRiskAnalysisDaoImpl extends BaseDaoImpl<MonitorCompanyRiskAnalysis, String> implements MonitorCompanyRiskAnalysisDao{

	@Override
	public List<MonitorCompanyRiskAnalysis> queryMonitorCompanyRiskAnalysisListByMonitorId(String monitorId) {
		
		String hql = "from MonitorCompanyRiskAnalysis where monitorId = '" + monitorId + "'";
		
		return getListByHql(hql);
	}

	
}
