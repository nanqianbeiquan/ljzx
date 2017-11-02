package com.srd.ljzd.service.monitor;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.util.Page;

/**
 * 
 * 版权所有：
 * 项目名称：ljzd 
 *
 * 类描述：MonitorRelationCompany 动态监控关联企业信息表
 * 类名称：com.srd.ljzd.service.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月25日下午2:53:49
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
public interface MonitorRelationCompanyService {
	
	
	List<String> queryRelationCompanyNameByMonitorId(String monitorId);
	
	/** 
	* @Title: getMonitorRelationCompanyPage 
	* @Description: 获取关系企业信息分页
	* @param @param monitorId
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorRelationCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午10:41:23
	*/
	public Page<MonitorRelationCompany> getMonitorRelationCompanyPage(String monitorId, int currentPageNo, int pageSize);

	/** 
	* @Title: deleteMonitorRelationCompanys 
	* @Description: 根据id删除关系企业
	* @param @param relationCompanyIdList
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 上午10:56:29
	*/
	public ResultBean deleteMonitorRelationCompanys(List<String> relationCompanyIdList);
	
	/**
	 * 
		* @Title: saveMonitorRelationCompany
		* @Description: 添加关联企业
		* @param  @param monitorRelationCompany
		* @param  @return
		* @return boolean
		* @author jiang.zhou
		* @throws
		* @date 下午5:39:18
	 */
	public ResultBean saveMonitorRelationCompany(MonitorRelationCompany monitorRelationCompany);
	
	
	/** 
	* @Title: getMonitorRelationCompany 
	* @Description: 获取关系企业信息
	* @param @param monitorId
	* @param @param companyName
	* @param @return 设定文件 
	* @return MonitorRelationCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月4日 上午10:53:12
	*/
	public MonitorRelationCompany getMonitorRelationCompany(String monitorId, String companyName);
	
	/** 
	* @Title: getMonitorRelationCompanyById 
	* @Description: 根据id获取关系企业信息
	* @param @param id
	* @param @return 设定文件 
	* @return MonitorRelationCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午11:36:29
	*/
	public MonitorRelationCompany getMonitorRelationCompanyById(String id);
	
	/** 
	* @Title: getRelationCompanyNum 
	* @Description: 获取关系企业数量
	* @param @param monitorCompanyList
	* @param @return 设定文件 
	* @return Map<String,Integer> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月13日 上午10:58:57
	*/
	public Map<String, Integer> getRelationCompanyNum(List<MonitorCompany> monitorCompanyList);
	
	/** 
	* @Title: getRelationCompanyNum 
	* @Description: 获取关系企业数量
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月28日 下午2:22:31
	*/
	public int getRelationCompanyNum(String accountId);
	
	/** 
	* @Title: getRelationCompanyNumByParentId 
	* @Description: 获取父账号及下一级子账号的关系企业总数
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 下午7:59:58
	*/
	public int getTotalRelationCompanyNum(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: getMonitorRelationCompanyEventNum 
	* @Description: 获取关系企业事件数量
	* @param @param monitorId
	* @param @param eventSubTypes
	* @param @param eventLevels
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午3:55:31
	*/
	public int getMonitorRelationCompanyEventNum(String monitorId, String[] eventSubTypes, String[] eventLevels);
}
