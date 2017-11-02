package com.srd.ljzd.service.monitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.srd.ljzd.dto.monitor.MonitorComEventDTO;
import com.srd.ljzd.entity.monitor.MonitorCompanyEvent;
import com.srd.ljzd.entity.monitor.Penalty;
import com.srd.ljzd.util.Page;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：事件service
 * 类名称：com.srd.ljzd.service.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月22日下午5:24:21
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorCompanyEventService {
	
	
	/**
	 * 
		* @Title: getMonitorEveContentKey
		* @Description: 获取contentKey拼接而成的字符串
		* @param  @param monitorComEventDTO
		* @param  @return
		* @return String
		* @author jiang.zhou
		* @throws
		* @date 上午11:57:31
	 */
	Page<MonitorCompanyEvent> getMonitorEveList(MonitorComEventDTO monitorComEventDTO,int currentPageNo,
			int pageSize);
	
	/**
	 * 
		* @Title: getMonitorComEveDetailsList
		* @Description: 调用大数据接口查看公司详情列表
		* @param  @param contentKey
		* @param  @param eventSubType
		* @param  @return
		* @return Map<String,Object>
		* @author jiang.zhou
		* @throws
		* @date 上午10:51:56
	 */
	public List getMonitorComEveDetailsList(Map<String,MonitorCompanyEvent> paramEventsMap,String eventSubType,Boolean flag);

	
	/**
	 * 
		* @Title: getMonitorComSfEveDetails
		* @Description: 获取司法事件详情
		* @param  @param judgmentId
		* @param  @return
		* @return Penalty
		* @author jiang.zhou
		* @throws
		* @date 上午10:44:25
	 */
	public Penalty getMonitorComSfEveDetails(String judgmentId,String companyName);

	//查询各分类未读事件数量
	public Map<String,Object> queryUnReadEventCategoryNumList(String accountId, String companyId, String companyName, Date infoShowDate);

	public List queryAllEventCategory(String companyName, Date infoShowDate);
	
	public List<String> getUnReadEventIdList(String accountId, String monitorId,String companyName,String infoShowDate,String eventSubType);

	Map<String, Object> getMoneyPlaintiffDefendant(String judgmentId,
			String companyName);
	
}
