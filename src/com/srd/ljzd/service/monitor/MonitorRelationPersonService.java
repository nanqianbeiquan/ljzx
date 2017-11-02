package com.srd.ljzd.service.monitor;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.util.Page;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：MonitorRelationPerson 动态监控关联个人信息表
 * 类名称：com.srd.ljzd.service.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月25日下午2:54:35
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorRelationPersonService {
	
	/** 
	* @Title: getMonitorRelationPersonPage 
	* @Description: 获取关系自然人信息列表
	* @param @param monitorId
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorRelationPerson> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午10:03:40
	*/
	public Page<MonitorRelationPerson> getMonitorRelationPersonPage(String monitorId, int currentPageNo, int pageSize);

	/** 
	* @Title: getMonitorRelationPersonList 
	* @Description: 获取关系自然人信息列表
	* @param @param monitorId
	* @param @return 设定文件 
	* @return List<MonitorRelationPerson> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月25日 下午5:27:03
	*/
	public List<MonitorRelationPerson> getMonitorRelationPersonList(String monitorId);
	
	/** 
	* @Title: deleteMonitorRelationPersons 
	* @Description: 根据id删除关系自然人
	* @param @param relationPersonIdList
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 上午11:08:18
	*/
	public ResultBean deleteMonitorRelationPersons(List<String> relationPersonIdList);
	
	/** 
	* @Title: getMonitorRelationPerson 
	* @Description: 获取关系个人信息
	* @param @param monitorRelationPerson
	* @param @return 设定文件 
	* @return MonitorRelationPerson 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月4日 下午2:37:31
	*/
	public MonitorRelationPerson getMonitorRelationPerson(MonitorRelationPerson monitorRelationPerson);
	
	/** 
	* @Title: getMonitorRelationPersonById 
	* @Description: 根据id获取关系自然人信息
	* @param @param id
	* @param @return 设定文件 
	* @return MonitorRelationPerson 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 下午4:26:41
	*/
	public MonitorRelationPerson getMonitorRelationPersonById(String id);
	
	/** 
	* @Title: saveMonitorRelationPerson 
	* @Description: 保存关系个人信息
	* @param @param monitorRelationPerson
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月4日 下午2:56:41
	*/
	public ResultBean saveMonitorRelationPerson(MonitorRelationPerson monitorRelationPerson);
	
	/** 
	* @Title: getRelationPersonNum 
	* @Description: 获取关系自然人数量
	* @param @param monitorCompanyList
	* @param @return 设定文件 
	* @return Map<String,Integer> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月13日 下午1:30:09
	*/
	public Map<String, Integer> getRelationPersonNum(List<MonitorCompany> monitorCompanyList);
	
	/** 
	* @Title: getMonitorRelationPersonEventNum 
	* @Description: 获取关系个人事件数量
	* @param @param monitorId
	* @param @param eventSubTypes
	* @param @param eventLevels
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午3:54:37
	*/
	public int getMonitorRelationPersonEventNum(String monitorId, String[] eventSubTypes, String[] eventLevels);
	
}
