package com.srd.ljzd.daoImpl.monitor;



import java.util.HashMap;
import java.util.List;



import java.util.Map;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorCompanyEventStateDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompanyEventState;

/**
 * 
 * 版权所有：
 * 项目名称：ljzx 
 *
 * 类描述：TODO
 * 类名称：com.srd.ljzd.daoImpl.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20172017年2月28日下午1:48:13
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
@Repository
public class MonitorCompanyEventStateDaoImpl extends BaseDaoImpl<MonitorCompanyEventState,String> implements MonitorCompanyEventStateDao {

	@Override
	public boolean addMonitorComEveState(
			MonitorCompanyEventState monitorCompanyEventState,
			List<String> eventIdList) {
		MonitorCompanyEventState temp = null;
		for(int i=0;i<eventIdList.size();i++){
			temp=new MonitorCompanyEventState();
			temp.setAccountId(monitorCompanyEventState.getAccountId());
			temp.setEventId(eventIdList.get(i));
			temp.setState(monitorCompanyEventState.getState());
			temp.setCompanyId(monitorCompanyEventState.getCompanyId());
			save(temp);
		}
		return true;
	}

	@Override
	public List<MonitorCompanyEventState> getReadedEvent(
			MonitorCompanyEventState monitorCompanyEventState) {
		
		String hql = "from MonitorCompanyEventState where accountId = '" + monitorCompanyEventState.getAccountId() + "' and companyId = '" + monitorCompanyEventState.getCompanyId() + "' "
				+ " and eventId = '" + monitorCompanyEventState.getEventId()+ "'";
		
		return getListByHql(hql);
	}
	
	@Override
	public List<MonitorCompanyEventState> getReadedEvent(String accountId, String companyId, String eventId) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "from MonitorCompanyEventState where accountId = :accountId and companyId = :companyId and eventId = :eventId";
		
		parameMap.put("accountId", accountId);
		parameMap.put("companyId", companyId);
		parameMap.put("eventId", eventId);
		
		return getListByHql(hql, parameMap);
	}
}
