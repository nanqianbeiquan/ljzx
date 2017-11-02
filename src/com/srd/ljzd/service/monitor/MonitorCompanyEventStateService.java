package com.srd.ljzd.service.monitor;

import java.util.List;

import com.srd.ljzd.entity.monitor.MonitorCompanyEventState;

/**
 * 
 * 版权所有：
 * 项目名称：ljzx 
 *
 * 类描述：TODO
 * 类名称：com.srd.ljzd.service.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20172017年2月28日下午2:01:53
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorCompanyEventStateService {
	boolean addMonitorComEveState(MonitorCompanyEventState monitorCompanyEventState,List<String> eventIdList);	
	public MonitorCompanyEventState getReadedEvent(
			MonitorCompanyEventState monitorCompanyEventState);
	MonitorCompanyEventState getReadedEvent(String accountId,String companyId,String eventId);
	/**
	 * 把所有未读的时间置为已读
	 * @param monitorId
	 * @param companyName
	 * @param infoShowDate
	 * @param eventSubType
	 * @return
	 */
	public int updateAllUnreadEventStatus(String monitorId,String companyName,String infoShowDate,String eventSubType,String accountId);
}
