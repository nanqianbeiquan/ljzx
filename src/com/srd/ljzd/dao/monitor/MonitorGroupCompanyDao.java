package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorGroupCompany;

public interface MonitorGroupCompanyDao extends BaseDao<MonitorGroupCompany, String> {

	public List<MonitorGroupCompany> getTodayGroupCompany(String accountId,String createTime);
	
	/** 
	* @Title: deleteByMonitorCompany 
	* @Description: 删除监控公司的分组关系
	* @param @param monitorCompany
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 上午10:21:58
	*/
	public boolean deleteByMonitorCompany(MonitorCompany monitorCompany);
	
	/** 
	* @Title: deleteMonitorGroupCompanyByMonitorId 
	* @Description: 根据monitorId删除监控企业和分组的关系
	* @param @param monitorId
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午3:58:05
	*/
	public boolean deleteMonitorGroupCompanyByMonitorId(String monitorId);
	
	/** 
	* @Title: deleteMonitorGroupCompanyByMonitorId 
	* @Description: 根据monitorId删除监控企业和分组的关系(除groupId外)
	* @param @param monitorId
	* @param @param groupId
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午4:25:22
	*/
	public boolean deleteMonitorGroupCompanyByMonitorId(String monitorId, String groupId);
	
	/** 
	* @Title: getGroupCompany 
	* @Description: 获取动态监控公司和分组关联关系列表
	* @param @param monitorId
	* @param @return 设定文件 
	* @return List<MonitorGroupCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月1日 下午3:23:32
	*/
	public List<MonitorGroupCompany> getGroupCompany(String monitorId);
	
	/** 
	* @Title: getCompanyNumByGroup 
	* @Description: 获取分组下的企业数量
	* @param @param groupId
	* @param @return 设定文件 
	* @return Integer 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 下午5:03:26
	*/
	public Integer getCompanyNumByGroup(String groupId);
	
	/**
	 * 
		* @Title: addGroupCompanyReal
		* @Description: 追加关联
		* @param  @param groupId
		* @param  @param monitorId
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 下午3:56:58
	 */
	public boolean addGroupCompanyReal(String groupId,String monitorId);
	
	
	/**
	 * 
		* @Title: deleteAllReal
		* @Description: 批量删除 清空对应各组中所有的监控公司
		* @param  @param groupIdList
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 上午10:55:41
	 */
	public boolean deleteAllReal(List<String> groupIdList);
	
	/**
	 * 
		* @Title: deleteBatch
		* @Description: 批量删除对应分组中的监控公司
		* @param  @param groupId
		* @param  @param monitorIdList
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 上午11:30:08
	 */
	public boolean deleteByBatch(String groupId,List<String> monitorIdList);
	
	
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
		* @date 下午12:55:47
	 */
	public boolean deleteMonitorReal(String groupId);

	/**
	 * 
		* @Title: getGroupCompany
		* @Description: 主要是用于验证该分组下面有没有该公司
		* @param  @param groupId
		* @param  @param monitorId
		* @param  @return
		* @return MonitorGroupCompany
		* @author jiang.zhou
		* @throws
		* @date 下午2:21:20
	 */
	public Boolean getGroupCompany(String groupId,String monitorId);

	public List<MonitorGroupCompany> getGroupCompanyList(List<String> groupIdList, String monitorID) ;
}
