/**   
* @Title: MonitorCompanyEventMappingDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月23日 上午10:56:44 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorCompanyEventMappingDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompanyEventMapping;

/** 
 * @ClassName: MonitorCompanyEventMappingDaoImpl
 * @Description: 动态监控企业事件存储映射Dao实体类
 * @author shiyong
 * @date 2017年3月23日 上午10:56:44
 *  
 */
@Repository
public class MonitorCompanyEventMappingDaoImpl extends BaseDaoImpl<MonitorCompanyEventMapping, String> implements MonitorCompanyEventMappingDao{

	@Override
	public MonitorCompanyEventMapping getMonitorCompanyEventMapping(String companyName) {
		
		String hql = "from MonitorCompanyEventMapping where companyName = '" + companyName + "'";
		
		return getByHql(hql);
	}

	
	
}
