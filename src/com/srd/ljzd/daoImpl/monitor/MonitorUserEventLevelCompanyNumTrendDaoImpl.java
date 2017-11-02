/**   
* @Title: MonitorUserEventLevelCompanyNumTrendDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月10日 上午10:01:05 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorUserEventLevelCompanyNumTrendDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorUserEventLevelCompanyNumTrend;

/** 
 * @ClassName: MonitorUserEventLevelCompanyNumTrendDaoImpl
 * @Description: 用户动态监控各事件类型企业数量趋势Dao实现类
 * @author shiyong
 * @date 2017年3月10日 上午10:01:05
 *  
 */
@Repository("monitorUserEventLevelCompanyNumTrendDao")
public class MonitorUserEventLevelCompanyNumTrendDaoImpl extends BaseDaoImpl<MonitorUserEventLevelCompanyNumTrend, String> implements MonitorUserEventLevelCompanyNumTrendDao{

	@Override
	public List<MonitorUserEventLevelCompanyNumTrend> queryMonitorUserEventLevelCompanyNumTrendListByCycle(
			String accountId, String cycle) {
		
		String hql = "from MonitorUserEventLevelCompanyNumTrend where cycle = '" + cycle + "' and clientAccount.accountId = '" + accountId + "' order by reportDate desc";
		
		return getListByHql(hql);
	}

	
}
