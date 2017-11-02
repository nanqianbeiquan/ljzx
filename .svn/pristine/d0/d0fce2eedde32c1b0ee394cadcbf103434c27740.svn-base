/**   
* @Title: MonitorUserConfigureDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月19日 下午3:47:47 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.monitor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srd.ljzd.dao.monitor.MonitorUserConfigureDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorUserConfigure;

/** 
 * @ClassName: MonitorUserConfigureDaoImpl
 * @Description: 动态监控用户设置信息Dao实现类
 * @author shiyong
 * @date 2016年11月19日 下午3:47:47
 *  
 */
@Repository("monitorUserConfigureDao")
public class MonitorUserConfigureDaoImpl extends BaseDaoImpl<MonitorUserConfigure, String> implements MonitorUserConfigureDao{

	@Override
	public MonitorUserConfigure getMonitorUserConfigureByAccountId(String accountId) {
		
		String hql = "from MonitorUserConfigure where clientAccount.accountId = '" + accountId + "'";
		
		return getByHql(hql);
	}
	
}
