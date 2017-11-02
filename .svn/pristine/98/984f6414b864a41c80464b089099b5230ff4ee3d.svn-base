/**   
* @Title: MonitorUserConfigureService.java 
* @Package com.srd.ljzd.service.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月19日 下午3:51:23 
* @version V1.0   
*/
package com.srd.ljzd.service.monitor;

import java.util.List;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorUserConfigure;

/** 
 * @ClassName: MonitorUserConfigureService
 * @Description: 动态监控用户设置信息Service
 * @author shiyong
 * @date 2016年11月19日 下午3:51:23
 *  
 */
public interface MonitorUserConfigureService {
	
	/** 
	* @Title: saveMonitorUserConfigure 
	* @Description: 保存动态监控用户设置信息
	* @param @param monitorUserConfigure
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 下午3:53:31
	*/
	public ResultBean saveMonitorUserConfigure(MonitorUserConfigure monitorUserConfigure);
	
	/** 
	* @Title: updateMonitorUserConfigure 
	* @Description: 更新动态监控用户设置信息
	* @param @param monitorUserConfigure
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月24日 下午5:28:10
	*/
	public ResultBean updateMonitorUserConfigure(MonitorUserConfigure monitorUserConfigure);
	
	/** 
	* @Title: getMonitorUserConfigureByAccountId
	* @Description: 获取动态监控用户设置信息
	* @param @param accountId
	* @param @return 设定文件 
	* @return MonitorUserConfigure 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月25日 上午11:56:09
	*/
	public MonitorUserConfigure getMonitorUserConfigureByAccountId(String accountId);
	
	/** 
	* @Title: getUserAttentionEventClass 
	* @Description: 获取动态监控用户关注的事件类型
	* @param @param accountId
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月9日 上午9:27:45
	*/
	public List<String> getUserAttentionEventClass(String accountId);
	
	/** 
	* @Title: initMonitorUserConfigure 
	* @Description: 初始化动态监控用户设置
	* @param @param accountId
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月6日 上午9:41:53
	*/
	public ResultBean initMonitorUserConfigure(String accountId);
}
