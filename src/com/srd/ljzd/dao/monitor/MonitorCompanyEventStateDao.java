package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyEventState;

/**
 * 
 * 版权所有：
 * 项目名称：ljzx 
 *
 * 类描述：TODO
 * 类名称：com.srd.ljzd.dao.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20172017年2月28日下午1:44:15
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorCompanyEventStateDao extends BaseDao<MonitorCompanyEventState,String>{
	boolean addMonitorComEveState(MonitorCompanyEventState monitorCompanyEventState,List<String> eventIdList);
	
	List<MonitorCompanyEventState> getReadedEvent(MonitorCompanyEventState monitorCompanyEventState);
	List<MonitorCompanyEventState> getReadedEvent(String accountId,String companyId,String eventId);
}
