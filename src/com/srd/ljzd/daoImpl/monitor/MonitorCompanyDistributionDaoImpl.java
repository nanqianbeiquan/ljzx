/**   
* @Title: MonitorCompanyDistributionDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月16日 上午10:29:25 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.monitor.MonitorCompanyDistributionDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;


/** 
 * @ClassName: MonitorCompanyDistributionDaoImpl
 * @Description: 动态监控风险公司分布信息Dao实现
 * @author shiyong
 * @date 2016年11月16日 上午10:29:25
 *  
 */
@Repository("monitorCompanyDistributionDao")
public class MonitorCompanyDistributionDaoImpl extends BaseDaoImpl<MonitorCompanyDistribution,String> implements MonitorCompanyDistributionDao{

	@Override
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(String reportId) {
		String hql = "from MonitorCompanyDistribution where monitorDailyReport.reportID = '" + reportId + "' order by highRiskCompanyPercent desc";
		
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = getListByHql(hql);
		
		return monitorCompanyDistributionList;
	}
	
	
	
}
