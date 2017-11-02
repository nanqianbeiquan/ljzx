package com.srd.ljzd.service.monitor;

import java.util.List;
import java.util.Map;

import com.srd.ljzd.dto.monitor.MonitorCompanyDTO;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.util.Page;

/** 
* @ClassName: MonitorCompanyService
* @Description: 动态监控企业Service接口
* @author shiyong
* @date 2017年5月26日 下午3:49:49
*  
*/
public interface MonitorCompanyService {

	/** 
	* @Title: queryRecentMonitorCompanyList 
	* @Description: 获取最近加入动态监控的公司列表
	* @param @param accountId
	* @param @param num
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月16日 下午3:56:19
	*/
	public List<MonitorCompany> queryRecentMonitorCompanyList(String accountId, int num);
	
	/** 
	* @Title: saveMonitorCompany 
	* @Description: 保存动态监控公司
	* @param @param accountId
	* @param @param companyName
	* @param @param groupIds
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月23日 上午11:42:49
	*/
	public ResultBean saveMonitorCompany(String accountId,String companyId, String companyName, String province, String[] groupIds, String renameStatus);
	
	/** 
	* @Title: deleteMonitorCompany 
	* @Description: 删除动态监控公司
	* @param @param monitorCompany
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 上午9:59:39
	*/
	public ResultBean deleteMonitorCompany(MonitorCompany monitorCompany);
	
	/** 
	* @Title: deleteMonitorCompanyBatch 
	* @Description: 批量删除动态监控企业 
	* @param @param monitorIdList
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午3:49:11
	*/
	public ResultBean deleteMonitorCompanyBatch(List<String> monitorIdList);
	
	/** 
	* @Title: isMonitorOfCompany 
	* @Description: 检查公司是否已监控
	* @param @param monitorCompany
	* @param @return 设定文件 
	* @return boolean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月19日 上午10:58:53
	*/
	public boolean isMonitorOfCompany(MonitorCompany monitorCompany);
	
	public boolean isMonitorOfCompany(String accountId,String companyName);
	
	/** 
	* @Title: queryTodayMonitorCompany 
	* @Description: 查询今日加入动态监控的公司信息
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月3日 上午11:31:50
	*/
	public List<MonitorCompany> queryTodayMonitorCompany(String accountId);
	
	/** 
	* @Title: queryTodayMonitorCompanyNumByParentId 
	* @Description: 根据父ID获取父账号和下一级子账号今日新增监控企业数
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 下午6:56:57
	*/
	public int queryTodayMonitorCompanyNum(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryMonitorCompanyNum 
	* @Description: 根据父ID获取父账号和子账号监控企业数
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月29日 下午4:54:35
	*/
	public int queryMonitorCompanyNum(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryMonitorCompanyNumByAccountId 
	* @Description: 获取账号监控主体企业数
	* @param @param accountId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月29日 下午5:09:54
	*/
	public int queryMonitorCompanyNumByAccountId(String accountId);
	
	/** 
	* @Title: findTodayMonitorCompanyPage 
	* @Description: 查询今日加入动态监控的企业页面
	* @param @param monitorCompany
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年12月9日 下午3:45:43
	*/
	public Page<MonitorCompany> findTodayMonitorCompanyPage(MonitorCompany monitorCompany, int currentPageNo, int pageSize);
	
	/** 
	* @Title: findMonitorCompanyPage 
	* @Description: 获取监控企业分页
	* @param @param monitorCompany
	* @param @param currentPageNo
	* @param @param pageSize
	* @param @return 设定文件 
	* @return Page<MonitorCompanyRiskDTO> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月27日 上午10:57:45
	*/
	public Page<MonitorCompany> findMonitorCompanyPage(MonitorCompanyDTO monitorCompanyRiskDTO, int currentPageNo, int pageSize,String sortAttr,boolean isDesc);

	/** 
	* @Title: getMonitorCompanyList 
	* @Description: 根据条件获取动态监控企业列表
	* @param @param monitorCompanyRiskDTO
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午7:48:48
	*/
	public List<MonitorCompany> getMonitorCompanyList(MonitorCompanyDTO monitorCompanyRiskDTO);
	
	/** 
	* @Title: queryMonitorCompanyById 
	* @Description: 根据id获取监控企业信息
	* @param @param monitorId
	* @param @return 设定文件 
	* @return MonitorCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月23日 下午1:42:22
	*/
	public MonitorCompany queryMonitorCompanyById(String monitorId);
	
	/**
	 *  
		* @Title: getMonitorGroupComInfo
		* @Description: 获取动态监控对应分组下面的公司
		* @param  @param sysAccount
		* @param  @param groupName
		* @param  @param currentPageNo
		* @param  @param pageSize
		* @param  @return
		* @return Page<MonitorCompany>
		* @author jiang.zhou
		* @throws
		* @date 上午11:02:27
	 */
	public Page<MonitorCompany> getMonitorGroupComInfo(String sysAccount,String groupName,int currentPageNo, int pageSize);
	
	/**
	 * 
		* @Title: getMonitorComListByAccId
		* @Description: 获取对应名字的公司信息
		* @param  @param accountId
		* @param  @param nameList
		* @param  @return
		* @return List<MonitorCompany>
		* @author jiang.zhou
		* @throws
		* @date 下午4:23:26
	 */
	public List<MonitorCompany> getMonitorComListByAccId(String accountId,List<String> nameList);
	
	
	/** 
	* @Title: getMonitorCompany 
	* @Description: 获取动态监控企业信息
	* @param @param accountId
	* @param @param companyName
	* @param @return 设定文件 
	* @return MonitorCompany 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 下午4:58:43
	*/
	public MonitorCompany getMonitorCompany(String accountId, String companyName);
	
	/**
	 * 获取最大的sortId
		* @Title: getMaxSortOfGroup
		* @Description: TODO
		* @param  @param monitorId
		* @param  @return
		* @return int
		* @author jiang.zhou
		* @throws
		* @date 上午11:02:27
	 */
	public int getMaxSortOfGroup(String monitorId);
	
	public boolean updateGroupStatus(String monitorId, String groupStatus) ;
	
	public List<MonitorCompany> getGroupCompanyList(String groupId);
	
	/** 
	* @Title: queryMonitorCompanySituation 
	* @Description: 查询监控企业概况
	* @param @param accountId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月25日 上午11:32:43
	*/
	public Map<String, Object> queryMonitorCompanySituation(String accountId);
	
	/** 
	* @Title: queryMonitorCompanySituationByParentId 
	* @Description: 根据父账号ID获取父账号和下一级子账号的监控概况
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月11日 下午3:00:21
	*/
	public Map<String, Object> queryMonitorCompanySituation(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: queryMonitorCompanyDistributionList 
	* @Description: 查询企业分布情况列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorCompanyDistribution> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年4月26日 下午3:13:42
	*/
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(String accountId);
	
	/** 
	* @Title: queryMonitorCompanyDistributionListByParentId 
	* @Description: 查询父账号及下一级子账号的企业分布情况列表
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return List<MonitorCompanyDistribution> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月12日 上午9:12:39
	*/
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: updateMonitorCompany 
	* @Description: 更新动态监控企业信息
	* @param @param monitorCompany
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午5:05:43
	*/
	public ResultBean updateMonitorCompany(MonitorCompany monitorCompany);
	
	/** 
	* @Title: queryNewEventCompanyList 
	* @Description: 获取有新事件的企业信息列表
	* @param @param accountId
	* @param @return 设定文件 
	* @return List<MonitorCompany> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月3日 下午5:48:04
	*/
	public List<MonitorCompany> queryNewEventCompanyList(String accountId, int num);
	
	/** 
	* @Title: addBlacklistFlag 
	* @Description: 添加黑名单标签
	* @param @param accountId
	* @param @param companyName
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月13日 下午2:00:23
	*/
	public ResultBean addBlacklistFlag(String accountId, String companyName);
	
	/** 
	* @Title: deleteBlacklistFlag 
	* @Description: 删除黑名单标签
	* @param @param accountId
	* @param @param companyName
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年6月13日 下午2:00:41
	*/
	public ResultBean deleteBlacklistFlag(String accountId, String companyName);
	
	/** 
	* @Title: getRiskSituationList 
	* @Description: 获取账号列表中账号的风险状况
	* @param @param clientAccountList
	* @param @return 设定文件 
	* @return List<Map<String,Object>> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月12日 上午10:07:30
	*/
	public List<Map<String, Object>> getRiskSituationList(List<ClientAccount> clientAccountList);
	
	/** 
	* @Title: getSelfRiskSituation 
	* @Description: 获取账号自身的风险状况
	* @param @param accountId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月14日 下午2:08:17
	*/
	public Map<String, Object> getSelfRiskSituation(String accountId);
	
	/** 
	* @Title: getRiskSituationByParent 
	* @Description: 获取账号自身和所有子孙账号的风险状况
	* @param @param parentId
	* @param @return 设定文件 
	* @return Map<String,Object> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月14日 下午2:12:05
	*/
	public Map<String, Object> getRiskSituationByParent(String parentId);

}
