package com.srd.ljzd.dao.monitor;

import java.util.Map;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorPersonEvent;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.util.Page;

public interface MonitorPersonEventDao extends BaseDao<MonitorPersonEvent, String>{
	public Page<MonitorPersonEvent> getPersonEvents(int currentPageNo,int pageSize,String name, String idNumber,String province, String city,String eventSubType); 
	
	/** 
	* @Title: getPersonEventNumByType 
	* @Description: 按事件类别获取自然人事件数量
	* @param @param monitorRelationPerson
	* @param @return 设定文件 
	* @return Map<String, Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月27日 下午5:28:52
	*/
	public Map<String, Object> getPersonEventNumByType(MonitorRelationPerson monitorRelationPerson);
}
