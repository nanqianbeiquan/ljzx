/**   
* @Title: MonitorCompanyEventMappingServiceImpl.java 
* @Package com.srd.ljzd.serviceImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月23日 上午11:12:05 
* @version V1.0   
*/
package com.srd.ljzd.serviceImpl.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorCompanyEventMappingDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyEventMapping;
import com.srd.ljzd.service.monitor.MonitorCompanyEventMappingService;

/** 
 * @ClassName: MonitorCompanyEventMappingServiceImpl
 * @Description: 动态监控企业事件存储映射Service实现类
 * @author shiyong
 * @date 2017年3月23日 上午11:12:05
 *  
 */
@Service
public class MonitorCompanyEventMappingServiceImpl implements MonitorCompanyEventMappingService{

	@Autowired
	private MonitorCompanyEventMappingDao monitorCompanyEventMappingDao;
	
	@Override
	public int getNumOfMonitorCompanyEvent(String companyName) {
		
		int num = 0;
		
		MonitorCompanyEventMapping monitorCompanyEventMapping = monitorCompanyEventMappingDao.getMonitorCompanyEventMapping(companyName);
		
		num = monitorCompanyEventMapping.getId()/2000;
		
		return num;
	}

	@Override
	public String getTableNameOfMonitorCompanyEvent(String companyName) {
		StringBuffer tableName = new StringBuffer("monitorcompanyevent");
		
		MonitorCompanyEventMapping monitorCompanyEventMapping = monitorCompanyEventMappingDao.getMonitorCompanyEventMapping(companyName);
		
		if(null != monitorCompanyEventMapping){
			int num = monitorCompanyEventMapping.getId()/2000;
			
			tableName.append(num);
		}
		
		return tableName.toString();
	}
	
}
