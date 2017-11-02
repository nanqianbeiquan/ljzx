/**   
* @Title: MonitorUserEventLevelCompanyNumTrendServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月10日 上午10:06:14 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.service.monitor.MonitorUserEventLevelCompanyNumTrendService;
import com.srd.ljzd.dao.monitor.MonitorUserEventLevelCompanyNumTrendDao;
import com.srd.ljzd.entity.monitor.MonitorUserEventLevelCompanyNumTrend;

/** 
 * @ClassName: MonitorUserEventLevelCompanyNumTrendServiceImpl
 * @Description: 用户动态监控各事件类型企业数量趋势Service实现类
 * @author shiyong
 * @date 2017年3月10日 上午10:06:14
 *  
 */
@Service("monitorUserEventLevelCompanyNumTrendService")
public class MonitorUserEventLevelCompanyNumTrendServiceImpl implements MonitorUserEventLevelCompanyNumTrendService{
	@Autowired
	private MonitorUserEventLevelCompanyNumTrendDao monitorUserEventLevelCompanyNumTrendDao;

	@Override
	public List<MonitorUserEventLevelCompanyNumTrend> queryMonitorUserEventLevelCompanyNumTrendListByCycle(
			String accountId, String cycle) {
		
		return monitorUserEventLevelCompanyNumTrendDao.queryMonitorUserEventLevelCompanyNumTrendListByCycle(accountId, cycle);
	}
	
	
}
