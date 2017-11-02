package com.srd.ljzd.dao.monitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.dto.monitor.MonitorComEventDTO;
import com.srd.ljzd.entity.monitor.MonitorCompanyEvent;
import com.srd.ljzd.util.Page;

/**
 * 
 * 版权所有：
 * 项目名称：lengjingzd2.0 
 *
 * 类描述：动态监控公司事件信息表
 * 类名称：com.srd.ljzd.dao.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月1日下午4:39:05
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorCompanyEventDao extends BaseDao<MonitorCompanyEvent,String>{
	
	/**
	 * 
		* @Title: getMonitorComEvent
		* @Description: 获取动态监控公司对应类型的事件详情
		* @param  @param monitorComEventDTO
		* @param  @param currentPageNo
		* @param  @param pageSize
		* @param  @return
		* @return Page<MonitorCompanyEvent>
		* @author jiang.zhou
		* @throws
		* @date 下午5:25:50
	 */
	//Page<MonitorCompanyEvent> getMonitorComEvent(MonitorComEventDTO monitorComEventDTO,int currentPageNo, int pageSize);
	Page<MonitorCompanyEvent> getMonitorComEvent(MonitorComEventDTO monitorComEventDTO,String tableName,int currentPageNo, int pageSize);
	
	public List<Map<String,Object>> queryUnReadEventCategoryNumList(String accountId, String companyId, String companyName, Date infoShowDate, String tableName);

	public List queryEventCategory(String companyName, Date infoShowDate, String tableName);

	/** 
	* @Title: getUnReadEventIdList 
	* @Description: 获取未读事件ID列表
	* @param @param accountId
	* @param @param monitorId
	* @param @param companyName
	* @param @param infoShowDate
	* @param @param eventSubType
	* @param @param tableName
	* @param @return 设定文件 
	* @return List<String> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年10月18日 上午9:54:57
	*/
	public List<String> getUnReadEventIdList(String accountId, String monitorId, String companyName, String infoShowDate, String eventSubType,String tableName);
	
}
