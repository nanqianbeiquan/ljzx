package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorGroup;

public interface MonitorGroupDao extends BaseDao<MonitorGroup, String> {

	/** 
	* @Title: queryMonitorGroupListByAccount 
	* @Description: 根据账号获取动态监控分组信息列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorGroup> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月14日 上午10:54:00
	*/
	public List<MonitorGroup> queryMonitorGroupListByAccount(String accountId);
	
	/** 
	* @Title: queryMonitorGroupListByAccountExceptAll 
	* @Description: 根据账号获取除了全部分组的动态监控分组信息列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorGroup> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月5日 上午11:07:39
	*/
	public List<MonitorGroup> queryMonitorGroupListByAccountExceptAll(String accountId);
	
	/** 
	* @Title: getMaxSortOfGroup 
	* @Description: 获取某个账号当前最大的分组排序值
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 下午1:00:39
	*/
	public int getMaxSortOfGroup(String accountId);
	/**
	 * 根据名称查询分组
	 * @param accountId
	 * @param name
	 * @return
	 */
	public MonitorGroup getMonitorGroup(String accountId,String name);
	
	/** 
	* @Title: isHaveGroup 
	* @Description: 判断分组是否存在
	* @param @param accountId
	* @param @param groupName
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月29日 下午4:17:12
	*/
	public boolean isHaveGroup(String accountId, String groupName);
	
	/** 
	* @Title: isHaveGroup 
	* @Description: 判断分组是否存在
	* @param @param groupId
	* @param @param accountId
	* @param @param groupName
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月6日 下午3:38:23
	*/
	public boolean isHaveGroup(String groupId, String accountId, String groupName);
	
}
