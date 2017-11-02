package com.srd.ljzd.serviceImpl.monitor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srd.ljzd.dao.monitor.MonitorCompanyEventStateDao;
import com.srd.ljzd.entity.monitor.MonitorCompanyEventState;
import com.srd.ljzd.service.monitor.MonitorCompanyEventStateService;
import com.srd.ljzd.service.monitor.MonitorCompanyEventService;
/**
 * 
 * 版权所有：
 * 项目名称：ljzx 
 *
 * 类描述：TODO
 * 类名称：com.srd.ljzd.serviceImpl.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20172017年2月28日下午2:04:00
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
@Service
public class MonitorCompanyEventStateServiceImpl implements MonitorCompanyEventStateService {
	@Autowired
	MonitorCompanyEventStateDao monitorComEveStateDao;
	@Autowired
	MonitorCompanyEventService monitorComEventService;
	@Override
	public boolean addMonitorComEveState(
			MonitorCompanyEventState monitorCompanyEventState,
			List<String> eventIdList) {
		
		MonitorCompanyEventState temp = null;
		for(int i=0;i<eventIdList.size();i++){
			
			temp = this.getReadedEvent(monitorCompanyEventState.getAccountId(),monitorCompanyEventState.getCompanyId(),eventIdList.get(i));
			if(temp==null){
				temp=new MonitorCompanyEventState();
				temp.setAccountId(monitorCompanyEventState.getAccountId());
				temp.setEventId(eventIdList.get(i));
				temp.setState(monitorCompanyEventState.getState());
				temp.setCompanyId(monitorCompanyEventState.getCompanyId());
				monitorComEveStateDao.save(temp);
			}
		}
		
		return true;
	}
	@Override
	public MonitorCompanyEventState getReadedEvent(
			MonitorCompanyEventState monitorCompanyEventState) {
		List<MonitorCompanyEventState> list = monitorComEveStateDao.getReadedEvent(monitorCompanyEventState);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public MonitorCompanyEventState getReadedEvent(String accountId,String companyId,String eventId) {
		List<MonitorCompanyEventState> list = monitorComEveStateDao.getReadedEvent(accountId, companyId, eventId);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 把所有未读的事件置为已读
	 * @param monitorId
	 * @param companyName
	 * @param infoShowDate
	 * @param eventSubType
	 * @return
	 */
	@Override
	public int updateAllUnreadEventStatus(String monitorId,String companyName,String infoShowDate,String eventSubType,String accountId){
		List<String> list = monitorComEventService.getUnReadEventIdList(accountId, monitorId, companyName, infoShowDate, eventSubType);
		
		int count = 0;
		
		if(list!=null&&list.size()>0){
			MonitorCompanyEventState statue = null;
			
			for(int i=0;i<list.size();i++){
				statue = this.getReadedEvent(accountId,monitorId,list.get(i));
				
				if(null == statue){
					statue = new MonitorCompanyEventState();
					statue.setEventId(list.get(i));
					statue.setAccountId(accountId);
					statue.setCompanyId(monitorId);
					statue.setState("1");
					
					monitorComEveStateDao.save(statue);
					
					count++;
				}
			}
		}
		
		return count;
	}
	
}
