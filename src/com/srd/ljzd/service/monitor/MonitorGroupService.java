package com.srd.ljzd.service.monitor;

import java.util.List;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorGroup;

public interface MonitorGroupService {

	/** 
	* @Title: addMonitorGroup 
	* @Description: 增加动态监控分组信息
	* @param @param monitorGroup
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月14日 上午10:14:08
	*/
	public ResultBean addMonitorGroup(MonitorGroup monitorGroup);
	
	/** 
	* @Title: editMonitorGroup 
	* @Description: 编辑动态监控分组信息
	* @param @param monitorGroup
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月14日 上午10:14:44
	*/
	public ResultBean editMonitorGroup(MonitorGroup monitorGroup);
	
	/** 
	* @Title: getMonitorGroupById 
	* @Description: 根据ID获取动态监控分组信息
	* @param @param groupID
	* @param @return 设定文件 
	* @return MonitorGroup 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月14日 上午10:15:35
	*/
	public MonitorGroup getMonitorGroupById(String groupID);
	
	public MonitorGroup getMonitorGroup(String accountId,String name);
	
	/** 
	* @Title: deleteMonitorGroupById 
	* @Description: 根据ID删除动态监控分组信息
	* @param @param groupID
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月14日 上午10:15:58
	*/
	public ResultBean deleteMonitorGroupById(String groupID);
	
	/** 
	* @Title: queryMonitorGroupListByAccount 
	* @Description: 根据账号获取动态监控分组信息列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorGroup> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月14日 上午10:16:22
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
	* @date 2017年4月5日 上午11:06:49
	*/
	public List<MonitorGroup> queryMonitorGroupListByAccountExceptAll(String accountId);
	
	/** 
	* @Title: isHaveGroup 
	* @Description: 是否已存在分组名称
	* @param @param accountId
	* @param @param groupName
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月29日 下午3:17:14
	*/
	public boolean isHaveGroup(String accountId, String groupName);
	
	/**
	 * 
		* @Title: updateGroup
		* @Description: 更新分组
		* @param  @param monitorGroup
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 下午4:21:45
	 */
	public ResultBean updateGroup(MonitorGroup monitorGroup);
	
}
