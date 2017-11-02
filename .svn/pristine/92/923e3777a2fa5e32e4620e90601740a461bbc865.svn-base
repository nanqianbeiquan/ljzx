package com.srd.ljzd.service.monitor;

import java.util.List;

import com.srd.ljzd.entity.monitor.MonitorGroupCompany;

public interface MonitorGroupCompanyService {

	public List<MonitorGroupCompany> getTodayGroupCompany(String accountId);
	
	/** 
	* @Title: updateMonitorCompanyGroupBatch 
	* @Description: 批量更新监控企业分组
	* @param @param monitorIdList
	* @param @param groupIdList
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午4:13:43
	*/
	public boolean updateMonitorCompanyGroupBatch(String accountId, List<String> monitorIdList,List<String> groupIdList);
	
	/**
	 * 
		* @Title: deleteBatch
		* @Description: 批量删除对应分组中含有的监控公司
		* @param  @param groupId
		* @param  @param monitorIdList
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 上午11:28:35
	 */
	public boolean deleteBatch(String groupId,List<String> monitorIdList);
	
	/**
	 * 
		* @Title: deleteMonitorReal
		* @Description: 取消动态监控 取消所有分组中的动态监控关联
		* @param  @param groupIdList
		* @param  @param monitorID
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 下午5:49:04
	 */
	public boolean deleteMonitorReal(List<String> groupIdList,String monitorID);
	
	/**
	 * 
		* @Title: deleteMonitorReal
		* @Description: 删除分组引用关联
		* @param  @param groupId
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 下午12:54:39
	 */
	public boolean deleteMonitorReal(String groupId);
	
	public boolean getGroupCompanyList(List<String> groupIdList,String monitorID);
}
